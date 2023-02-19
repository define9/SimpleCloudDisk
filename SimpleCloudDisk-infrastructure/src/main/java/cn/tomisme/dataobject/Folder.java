package cn.tomisme.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件夹表
 */
@Data
@TableName("folder")
@NoArgsConstructor
@AllArgsConstructor
public class Folder {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer userId;

    private Boolean root;

    private Integer parentId;

    private Boolean deleted;
}
