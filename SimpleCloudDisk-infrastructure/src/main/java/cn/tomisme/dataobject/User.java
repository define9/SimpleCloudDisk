package cn.tomisme.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer roleId;
    /**
     * 容量，以兆为单位
     */
    private Long capacity;

    private String nick;

    private String avatar;

    private String username;

    private String password;

    private String email;

    private Boolean disable;

    private String remark;
}
