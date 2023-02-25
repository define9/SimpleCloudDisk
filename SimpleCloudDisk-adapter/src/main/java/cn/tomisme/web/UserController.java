package cn.tomisme.web;

import cn.tomisme.annotation.VerifyCaptcha;
import cn.tomisme.domain.domainservice.UserService;
import cn.tomisme.domain.model.request.user.LoginParam;
import cn.tomisme.domain.model.request.user.RegisterParam;
import cn.tomisme.domain.model.response.R;
import cn.tomisme.domain.model.response.user.UserDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 1. 登录、登出、注册
    @VerifyCaptcha
    @PostMapping("/login")
    public R<String> login(@RequestBody LoginParam param) {
        String token = userService.login(param);
        return R.success(token);
    }

    @VerifyCaptcha(paramName = "emailCode")
    @PostMapping("/register")
    public R<String> register(@RequestBody RegisterParam param) {
        String token = userService.register(param);
        return R.success(token);
    }

    @GetMapping("/userList")
    public R<Page<UserDto>> userList(Integer index, Integer size) {
        return R.success(userService.list(index, size));
    }

    @PutMapping("/status")
    public R<Boolean> updateStatus(Integer userId, Boolean disableStatus) {
        userService.updateStatus(userId, disableStatus);
        return R.success();
    }

    @PutMapping("/")
    public R<Boolean> update(@RequestBody UserDto userDto) {
        userService.update(userDto);
        return R.success();
    }

    // 2. 权限配置

    // 3. 用户信息的更新
}
