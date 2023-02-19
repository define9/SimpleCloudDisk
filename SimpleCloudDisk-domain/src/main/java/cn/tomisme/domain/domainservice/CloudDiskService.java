package cn.tomisme.domain.domainservice;

import cn.tomisme.constant.CloudConstant;
import cn.tomisme.dataobject.Folder;
import cn.tomisme.dataobject.Resources;
import cn.tomisme.dataobject.StorageNodeConfig;
import cn.tomisme.dataobject.UserResources;
import cn.tomisme.domain.model.request.cloud.FileUploadParam;
import cn.tomisme.domain.model.response.cloud.StdFolderContentDto;
import cn.tomisme.domain.model.response.cloud.StdFolderDto;
import cn.tomisme.domain.model.response.cloud.StdPathDto;
import cn.tomisme.domain.utils.MD5Util;
import cn.tomisme.mapper.FolderMapper;
import cn.tomisme.mapper.ResourcesMapper;
import cn.tomisme.mapper.StorageNodeConfigMapper;
import cn.tomisme.mapper.UserResourcesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudDiskService {

    private final StorageNodeConfigMapper storageNodeConfigMapper;
    private final ResourcesMapper resourcesMapper;
    private final FolderMapper folderMapper;
    private final UserResourcesMapper userResourcesMapper;

    public boolean upload(MultipartFile file, FileUploadParam param) throws IOException {
        int userId = 2;

        // 0. 先获取一下文件大小和 hash 值, 保证相同文件的唯一存储
        String hash = MD5Util.getMd5(file.getBytes());
        long size = file.getSize();
        Integer resourcesId = resourcesMapper.getIdByHashAndSize(hash, size);

        // 1. 存储并设置资源ID
        if (resourcesId == null) {
            // 没有这个资源, 开始存储
            List<StorageNodeConfig> configs = storageNodeConfigMapper.selectAll();
            // 找到符合大小的
            StorageNodeConfig applyConfig = getSuitableConfig(configs, size);
            if (applyConfig == null) {
                // 获取失败, 所有存储节点不可用
                return false;
            }
            // 1. 重新命名文件(实际存储的名字)
            String filename = UUID.randomUUID().toString()
                    .replace("-", "") + "." + file.getOriginalFilename();

            // 2. 文件的保存
            File realFile = new File(applyConfig.getNodeLocalPath() + filename);
            file.transferTo(realFile);

            Resources resources = new Resources();
            resources.setResourcesName(filename);
            resources.setResourcesSize(file.getSize());
            resources.setHash(hash);
            resources.setConfigNodeId(applyConfig.getId());
            resources.setCreateUser(userId);
            resourcesMapper.insert(resources);

            resourcesId = resources.getId();
        }

        // 2. 存储资源映射
        UserResources userResources = new UserResources();
        userResources.setResourcesId(resourcesId);
        userResources.setFolderId(param.getFolderId());
        userResources.setFileName(file.getOriginalFilename());
        userResources.setUserId(userId);
        userResourcesMapper.insert(userResources);
        return true;
    }

    private StorageNodeConfig getSuitableConfig(List<StorageNodeConfig> configs, long size) {
        for (StorageNodeConfig config : configs) {
            try {
                File path = new File(config.getNodeLocalPath());
                long space = path.getUsableSpace();
                if (space - 1024 * 1024 > size) {
                    return config;
                }
            } catch (Exception ignore){
            }
        }
        return null;
    }

    public StdFolderDto directory(Integer folderId) {
        int userId = 2;
        StdFolderDto res = new StdFolderDto();

        // 1. 查一下这个目录
        Folder folder = folderMapper.selectById(folderId);
        if (folder == null || folder.getDeleted()) {
            log.warn("Folder不存在: {}", folderId);
            return res;
        }

        // 2. 查询他的子目录
        List<StdFolderContentDto> contentList = new ArrayList<>();
        List<Folder> folders = folderMapper.selectByParentId(userId, folderId);
        if (folders != null && folders.size() != 0) {
            for (Folder f : folders) {
                StdFolderContentDto dto = new StdFolderContentDto();
                dto.setId(f.getId());
                dto.setType(CloudConstant.TYPE_FOLDER);
                dto.setName(f.getName());
                dto.setDate(f.getDate());

                contentList.add(dto);
            }
        }

        // 3. 查询这个文件夹下的文件
        List<UserResources> userResources =  userResourcesMapper.selectByFolderId(userId, folder.getId());
        for (UserResources userResource : userResources) {
            StdFolderContentDto dto = new StdFolderContentDto();
            dto.setId(userResource.getResourcesId());
            dto.setType(CloudConstant.TYPE_FILE);
            dto.setName(userResource.getFileName());
            dto.setDate(userResource.getCreateTime());

            contentList.add(dto);
        }
        res.setContent(contentList);

        // 4. 设置通用
        res.setRoot(folder.getParentId() == null);
        res.setId(folderId);
        res.setName(folder.getName());

        // 5. 如果不是根路径, 设置路径
        List<StdPathDto> path = new ArrayList<>();
        int max = 10;
        Folder parent;
        do {
            parent = folderMapper.selectById(folderId);
            if (parent == null || parent.getDeleted() || parent.getUserId() != userId || max++ > 15) break;
            path.add(new StdPathDto(parent.getId(), parent.getName()));
            folderId = parent.getParentId();
        } while (parent.getParentId() != null);

        Collections.reverse(path);
        res.setPath(path);

        return res;
    }
}
