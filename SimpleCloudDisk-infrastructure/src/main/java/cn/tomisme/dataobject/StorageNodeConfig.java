package cn.tomisme.dataobject;

import cn.tomisme.enums.StorageNodeType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("storage_node_config")
@NoArgsConstructor
@AllArgsConstructor
public class StorageNodeConfig {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String nodeName;

    private StorageNodeType nodeType;

    private String nodeLocalPath;

    private Boolean disable;

    private Boolean deleted;
}
