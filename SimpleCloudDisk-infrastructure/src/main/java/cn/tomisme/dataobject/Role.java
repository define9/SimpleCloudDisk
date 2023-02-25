package cn.tomisme.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@TableName("role")
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String code;
    /**
     * 默认容量
     */
    private Long capacity;
}
