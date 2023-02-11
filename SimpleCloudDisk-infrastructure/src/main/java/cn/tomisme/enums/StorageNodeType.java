package cn.tomisme.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StorageNodeType {
    LOCAL(0),  // 0 本地
    OSS(1);

    @EnumValue
    private final Integer value;
}
