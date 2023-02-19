package cn.tomisme.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("user_resources")
@AllArgsConstructor
@NoArgsConstructor
public class UserResources {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String fileName;

    private Integer resourcesId;

    private Date createTime;

    private Integer userId;

    private Integer folderId;

    private Boolean deleted;

    /**
     * 文件的类型
     */
    @TableField(exist = false)
    private String mimeType;
}
