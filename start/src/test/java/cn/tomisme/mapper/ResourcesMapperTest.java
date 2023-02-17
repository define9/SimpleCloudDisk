package cn.tomisme.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ResourcesMapperTest {

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Test
    public void getIdByHashAndSize() {
        Integer idByHashAndSize = resourcesMapper.getIdByHashAndSize("", 1L);
        System.out.println(idByHashAndSize);
    }

}
