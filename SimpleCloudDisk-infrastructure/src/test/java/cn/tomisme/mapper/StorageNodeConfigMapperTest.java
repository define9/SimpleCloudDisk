package cn.tomisme.mapper;

import cn.tomisme.dataobject.StorageNodeConfig;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;

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
}