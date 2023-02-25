package cn.tomisme.domain.helper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class CommonHelper {

    /**
     * 返回路径剩余字节数
     * @param path 路径
     * @return long
     */
    public static Long getRemainingCapacity(String path) {
        try {
            File file = new File(path);
            return file.getUsableSpace();
        } catch (Exception ignore) {
            log.error("获取路径空间失败: {}", path);
        }
        return 0L;
    }
}
