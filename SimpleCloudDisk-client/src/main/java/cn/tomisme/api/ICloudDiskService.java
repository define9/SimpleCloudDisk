package cn.tomisme.api;

import cn.tomisme.model.request.cloud.FileUploadParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ICloudDiskService {

    boolean upload(MultipartFile file, FileUploadParam param) throws IOException;
}
