package cn.tomisme.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StorageNodeType {
    LOCAL(0, "本地"),  // 0 本地
    OSS(1, "三方OSS");

    @EnumValue
    private final Integer value;
    @EnumValue
    private final String str;
}
