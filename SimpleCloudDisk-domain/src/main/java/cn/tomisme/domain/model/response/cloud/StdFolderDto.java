package cn.tomisme.domain.model.response.cloud;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StdFolderDto {
    /**
     * 文件夹ID
     */
    private int id;
    /**
     * 文件夹名字
     */
    private String name;
    /**
     * 文件夹创建日期
     */
    private Date date;
    /**
     * 是一个根目录
     */
    private boolean isRoot;
    /**
     * 不是根目录, 上层的路径
     */
    private List<StdPathDto> path;
    /**
     * 包含的内容, 文件夹 和 文件
     */
    private List<StdFolderContentDto> content;
}
