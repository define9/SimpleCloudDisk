package cn.tomisme.model.request.cloud;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadParam {

    /**
     * 文件夹ID
     */
    private Integer folderId;

}
