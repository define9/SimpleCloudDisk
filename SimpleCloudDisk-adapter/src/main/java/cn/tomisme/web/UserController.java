package cn.tomisme.web;

import cn.tomisme.api.IUserService;
import cn.tomisme.domain.annotation.VerifyCaptcha;
import cn.tomisme.dto.user.LoginParam;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    // 1. 登录、登出、注册
    @VerifyCaptcha
    @PostMapping("/login")
    public SingleResponse login(@RequestBody LoginParam param) {

        String token = userService.login(param);

        return SingleResponse.of(token);
    }

    // 2. 权限配置

    // 3. 用户信息的更新
}
