package cn.tomisme.interceptor;

import cn.tomisme.domain.annotation.VerifyCaptcha;
import cn.tomisme.domain.model.constant.CoreConstant;
import com.alibaba.cola.exception.BizException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
@Configuration
public class CaptchaInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod))
            return true;

        Method method = ((HandlerMethod) handler).getMethod();
        VerifyCaptcha captcha = method.getAnnotation(VerifyCaptcha.class);
        if (captcha == null) {
            return true;
        }

        String sessionCode = (String) request.getSession().getAttribute(CoreConstant.CAPTCHA_KEY);
        String requestParameter = request.getParameter(captcha.paramName());

        // 验证码已经取出，废弃验证码
        request.getSession().removeAttribute(CoreConstant.CAPTCHA_KEY);

        if (StringUtils.isBlank(sessionCode)) {
            throw new BizException("验证码过期");
        }

        if (StringUtils.isBlank(requestParameter)) {
            throw new BizException("验证码为空");
        }

        if (!sessionCode.equalsIgnoreCase(requestParameter)) {
            throw new BizException("验证码错误");
        }

        return true;
    }

}
