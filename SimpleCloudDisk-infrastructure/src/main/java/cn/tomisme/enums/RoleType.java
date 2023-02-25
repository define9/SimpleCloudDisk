package cn.tomisme.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RoleType {
    ADMIN(1, "管理员"),
    NORMAL(2, "普通用户");

    @EnumValue
    private final Integer value;

    @EnumValue
    private final String str;
}
