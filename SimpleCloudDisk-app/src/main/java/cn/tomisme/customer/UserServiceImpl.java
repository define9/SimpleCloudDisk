package cn.tomisme.customer;


import cn.tomisme.api.IUserService;
import cn.tomisme.config.TokenConfig;
import cn.tomisme.dataobject.User;
import cn.tomisme.dto.user.LoginParam;
import cn.tomisme.mapper.UserMapper;
import com.alibaba.cola.exception.BizException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;
    private final TokenConfig tokenConfig;

    @Override
    public String login(LoginParam param) throws BizException {
        String password = DigestUtils.md5DigestAsHex(param.getPassword().getBytes());

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, param.getUsername());
        wrapper.eq(User::getPassword, password);
        User user = baseMapper.selectOne(wrapper);
        if (user == null) {
            throw new BizException("账号或密码错误");
        }

        return tokenConfig.createToken(user);
    }
}
