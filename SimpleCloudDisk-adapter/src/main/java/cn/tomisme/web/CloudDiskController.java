package cn.tomisme.web;

import cn.tomisme.api.ICloudDiskService;
import cn.tomisme.model.request.cloud.FileUploadParam;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final ICloudDiskService cloudDiskService;

    // 1. 配置的增删改查

    // 2. 文件的传输（若保存到本地）
    @PostMapping("/upload")
    public Response upload(MultipartFile file, FileUploadParam param) throws IOException {
        boolean res = cloudDiskService.upload(file, param);
        SingleResponse<Boolean> response = new SingleResponse<>();
        response.setData(res);
        return response;
    }

    // 3. OSS授权
}
