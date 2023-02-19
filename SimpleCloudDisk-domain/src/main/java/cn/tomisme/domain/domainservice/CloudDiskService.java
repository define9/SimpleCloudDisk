package cn.tomisme.domain.domainservice;

import cn.tomisme.dataobject.Folder;
import cn.tomisme.dataobject.Resources;
import cn.tomisme.dataobject.StorageNodeConfig;
import cn.tomisme.dataobject.UserResources;
import cn.tomisme.domain.model.request.cloud.FileUploadParam;
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
        int userId = 100;

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
        int userId = 100;
        StdFolderDto res = new StdFolderDto();
        Folder folder = folderMapper.selectById(folderId);
        if (folder == null || folder.getDeleted()) {
            return res;
        }

        List<Folder> folders = folderMapper.selectByParentId(folderId);


        return res;
    }

    private void setPath(Integer folderId, StdPathDto folderDto) {

    }
}
