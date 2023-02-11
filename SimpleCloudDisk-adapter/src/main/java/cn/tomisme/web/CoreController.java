package cn.tomisme.web;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.tomisme.domain.annotation.VerifyCaptcha;
import cn.tomisme.domain.model.constant.CoreConstant;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class CoreController {

    // 1. 发送邮件

    // 2. 获取图片验证码
    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(150, 40, 4, 4);
        captcha.write(response.getOutputStream());
        String code = captcha.getCode();
        session.setAttribute(CoreConstant.CAPTCHA_KEY, code);
    }

    // 3. 每日一言，公告
    @VerifyCaptcha
    @GetMapping("/random")
    public SingleResponse<String> random() {
        return SingleResponse.of("我是一言");
    }
}
