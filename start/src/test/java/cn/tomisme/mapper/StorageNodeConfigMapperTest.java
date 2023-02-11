package cn.tomisme.mapper;

import cn.tomisme.dataobject.StorageNodeConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class StorageNodeConfigMapperTest {

    @Resource
    private StorageNodeConfigMapper mapper;

    @Test
    public void insert() {
        StorageNodeConfig config = new StorageNodeConfig();
        config.setNodeName("我是一个本地路径节点配置");
        config.setNodeLocalPath("/data");

        int res = mapper.insert(config);
        System.out.println(res);
    }

    @Test
    public void list() {
        StorageNodeConfig conf = mapper.selectById(1);
        System.out.println(conf);
    }
}