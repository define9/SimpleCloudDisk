package cn.tomisme.customer.convert;

import cn.tomisme.dataobject.User;
import cn.tomisme.dto.user.RegisterParam;

public class UserConvert {
    public User fromRegisterParam(RegisterParam param) {
        User user = new User();
        user.setUsername(param.getUsername());
        user.setPassword(param.getPassword());
        user.setEmail(param.getEmail());

        user.setNick(param.getUsername());
        user.setRoleId(2);
        user.setDisable(false);

        return user;
    }
}
