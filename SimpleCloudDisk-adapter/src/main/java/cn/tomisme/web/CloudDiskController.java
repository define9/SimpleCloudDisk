package cn.tomisme.web;

import cn.tomisme.domain.domainservice.CloudDiskService;
import cn.tomisme.domain.model.request.cloud.FileUploadParam;
import cn.tomisme.domain.model.response.R;
import cn.tomisme.domain.model.response.cloud.StdFolderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    // 2. 文件的传输（若保存到本地）
    @PostMapping("/upload")
    public R<Boolean> upload(MultipartFile file, FileUploadParam param) throws IOException {
        boolean res = cloudDiskService.upload(file, param);
        return R.success(res);
    }
    // 3. 文件夹等资源
    @GetMapping("/directory")
    public R<StdFolderDto> directory(Integer folderId) {
        StdFolderDto directory = cloudDiskService.directory(folderId);
        return R.success(directory);
    }

    // 4. OSS授权
}
