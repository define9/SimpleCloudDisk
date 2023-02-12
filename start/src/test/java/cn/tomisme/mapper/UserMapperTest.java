package cn.tomisme.mapper;

import cn.tomisme.customer.convert.UserConvert;
import cn.tomisme.dataobject.User;
import cn.tomisme.dto.user.RegisterParam;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void insert() {
        RegisterParam param = new RegisterParam();
        param.setUsername("notDefine9");
        param.setPassword("password");
        param.setEmail("define9@qq.com");

        User user = new UserConvert().fromRegisterParam(param);
        System.out.println(userMapper.insert(user));
    }
}
