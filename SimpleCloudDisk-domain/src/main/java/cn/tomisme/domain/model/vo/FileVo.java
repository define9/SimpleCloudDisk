package cn.tomisme.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileVo {
    private String virtualPath; //动态变化的路径
    private String fileName;    //文件名称  uuid.pdf
}
