package cn.tomisme.api;

import cn.tomisme.dataobject.User;
import cn.tomisme.model.request.user.LoginParam;
import cn.tomisme.model.request.user.RegisterParam;
import com.alibaba.cola.exception.BizException;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IUserService extends IService<User> {
    String login(LoginParam param) throws BizException;

    String register(RegisterParam param) throws BizException;
}
