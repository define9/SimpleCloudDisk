package cn.tomisme.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 资源表, 实际存储的文件
 */
@Data
@TableName("resources")
@NoArgsConstructor
@AllArgsConstructor
public class Resources {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 实际存储的 配置节点的 ID
     */
    private Integer configNodeId;
    /**
     * 实际存储的 资源文件 文件名
     */
    private String resourcesName;

    /**
     * 实际存储的文件 hash 值
     */
    private String hash;

    /**
     * 实际存储的文件字节大小
     */
    private Long resourcesSize;

    /**
     * 实际存储文件 的 创建时间
     */
    private Date createTime;

    /**
     * 这个文件是谁上传的
     */
    private Integer createUser;

    /**
     * 是否删除
     */
    private Boolean deleted;
}
