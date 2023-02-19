package cn.tomisme.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    private Date date;

    private Integer userId;

    private Integer parentId;

    private Boolean deleted;
}
