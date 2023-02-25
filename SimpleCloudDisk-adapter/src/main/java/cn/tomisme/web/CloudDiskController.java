package cn.tomisme.web;

import cn.tomisme.domain.domainservice.CloudDiskService;
import cn.tomisme.domain.model.request.cloud.FileUploadParam;
import cn.tomisme.domain.model.response.R;
import cn.tomisme.domain.model.response.cloud.StdConfigNodeDto;
import cn.tomisme.domain.model.response.cloud.StdFolderDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 云盘相关
 */
@Slf4j
@RestController
@RequestMapping("/cloud")
@RequiredArgsConstructor
public class CloudDiskController {
    private final CloudDiskService cloudDiskService;

    // 1. 配置的增删改查
    @GetMapping("/configList")
    public R<Page<StdConfigNodeDto>> configList(Integer index, Integer size) {
        return R.success(cloudDiskService.configList(index, size));
    }
    @PutMapping("/configStatus")
    public R<Boolean> updateConfigStatus(Integer id, Boolean disable) {
        cloudDiskService.updateConfigStatus(id, disable);
        return R.success();
    }

    @GetMapping("/verifyPath")
    public R<Long> verifyPath(String path) {
        var size = cloudDiskService.verifyPath(path);
        if (size == null || size <= 0) {
            return R.error("节点不可用, 或容量为0");
        }
        return R.success(size);
    }

    // 2. 文件的传输（若保存到本地）
    @PostMapping("/upload")
    public R<Boolean> upload(MultipartFile file, FileUploadParam param) throws IOException {
        boolean res = cloudDiskService.upload(file, param);
        return R.success(res);
    }
    // 3. 文件夹等资源
    @GetMapping("/directory")
    public R<StdFolderDto> directory(Integer folderId, Boolean needPath) {
        StdFolderDto directory = cloudDiskService.directory(folderId, needPath);
        return R.success(directory);
    }

    // 4. OSS授权
}
