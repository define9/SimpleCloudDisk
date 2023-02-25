package cn.tomisme.domain.convert;

import cn.tomisme.constant.CoreConstant;
import cn.tomisme.dataobject.User;
import cn.tomisme.domain.model.request.user.RegisterParam;

public class UserConvert {
    public User fromRegisterParam(RegisterParam param) {
        User user = new User();
        user.setUsername(param.getUsername());
        user.setPassword(param.getPassword());
        user.setAvatar(CoreConstant.DEFAULT_AVATAR);
        user.setEmail(param.getEmail());

        user.setNick(param.getUsername());
        user.setRoleId(2);
        user.setDisable(false);

        return user;
    }
}
