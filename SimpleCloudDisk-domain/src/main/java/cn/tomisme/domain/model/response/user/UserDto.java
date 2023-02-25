package cn.tomisme.domain.model.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer userId;
    private String role;
    private Integer roleId;
    private String avatar;
    private Long capacity;
    private String nick;
    private String username;
    private String email;
    private Boolean disable;
    private String remark;
}
