package cn.tomisme.customer;


import cn.tomisme.api.IUserService;
import cn.tomisme.config.TokenConfig;
import cn.tomisme.customer.convert.UserConvert;
import cn.tomisme.dataobject.User;
import cn.tomisme.model.request.user.LoginParam;
import cn.tomisme.model.request.user.RegisterParam;
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

    @Override
    public String register(RegisterParam param) throws BizException {
        // 1. 先查一下 此邮箱有没有注册过
        if (baseMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getEmail, param.getEmail())
                .or()
                .eq(User::getUsername, param.getUsername())) != 0) {
            throw new BizException("邮箱地址 或 用户名 已存在");
        }

        String password = DigestUtils.md5DigestAsHex(param.getPassword().getBytes());
        param.setPassword(password);
        User user = new UserConvert().fromRegisterParam(param);

        baseMapper.insert(user);

        return tokenConfig.createToken(user);
    }
}
