package cn.tomisme.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
public class ResourcesMapperTest {

    @Resource
    private ResourcesMapper resourcesMapper;

    @Test
    public void getIdByHashAndSize() {
        Integer idByHashAndSize = resourcesMapper.getIdByHashAndSize("", 1L);
        System.out.println(idByHashAndSize);
    }

}
