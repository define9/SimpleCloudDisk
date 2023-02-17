package cn.tomisme.utils;

import org.springframework.util.DigestUtils;

public class MD5Util {
    public static String getMd5(byte[] bytes) {
        return DigestUtils.md5DigestAsHex(bytes).toUpperCase();
    }
}
