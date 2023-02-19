package cn.tomisme.web;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.tomisme.annotation.VerifyCaptcha;
import cn.tomisme.constant.CoreConstant;
import cn.tomisme.domain.domainservice.CoreService;
import cn.tomisme.domain.model.response.R;
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
    private final CoreService coreService;

    // 1. 发送邮件
    @VerifyCaptcha
    @GetMapping("/sendEmail")
    public R<String> sendEmail(String email, HttpSession session) {
        String code = new RandomGenerator(6).generate();
        session.setAttribute(CoreConstant.CAPTCHA_KEY, code);
        coreService.sendMailCode(email, code);
        return R.success("发送成功", null);
    }

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
    public R<String> random() {
        return R.success("我是一言");
    }
}
