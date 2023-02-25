package cn.tomisme.domain.domainservice;


import cn.tomisme.config.TokenConfig;
import cn.tomisme.dataobject.Role;
import cn.tomisme.dataobject.User;
import cn.tomisme.domain.convert.UserConvert;
import cn.tomisme.domain.helper.CommonHelper;
import cn.tomisme.domain.helper.PageHelper;
import cn.tomisme.domain.model.request.user.LoginParam;
import cn.tomisme.domain.model.request.user.RegisterParam;
import cn.tomisme.domain.model.response.user.UserDto;
import cn.tomisme.mapper.UserMapper;
import com.alibaba.cola.exception.BizException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService
        extends ServiceImpl<UserMapper, User>
        implements IService<User> {
    private final TokenConfig tokenConfig;
    private final RoleService roleService;

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

    public Page<UserDto> list(int index, int size) {
        Page<User> p = new Page<>(index, size);
        p = baseMapper.selectPage(p, null);
        Page<UserDto> page = new Page<>();
        PageHelper.convertPage(p, page);

        List<UserDto> records = p.getRecords().stream().map(user -> {

            Role role = roleService.getRoleWithDefault(user.getRoleId());
            Long capacity = user.getCapacity();
            if (capacity == null || capacity <= 0) {
                capacity = role.getCapacity();
            }

            UserDto userDto = new UserDto();
            userDto.setUserId(user.getId());
            userDto.setRole(role.getName());
            userDto.setRoleId(role.getId());
            userDto.setAvatar(user.getAvatar());
            userDto.setCapacity(capacity);
            userDto.setNick(user.getNick());
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setDisable(user.getDisable());
            userDto.setRemark(user.getRemark());
            
            return userDto;
        }).collect(Collectors.toList());
        page.setRecords(records);
        return page;
    }

    public void updateStatus(Integer userId, Boolean disableStatus) {
        User user = new User();
        user.setId(userId);
        user.setDisable(disableStatus);
        baseMapper.updateById(user);
    }

    public void update(UserDto dto) {
        User user = new User();
        user.setId(dto.getUserId());
        user.setRoleId(dto.getRoleId());
        user.setCapacity(dto.getCapacity());
        user.setNick(dto.getNick());
        user.setAvatar(dto.getAvatar());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setDisable(dto.getDisable());
        user.setRemark(dto.getRemark());

        baseMapper.updateById(user);
    }
}
