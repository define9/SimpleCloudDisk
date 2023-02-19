package cn.tomisme.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 加此注解, captcha拦截器会从参数中获取验证码与session校验
 * paramName 为 request.getAttr 的参数
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyCaptcha {
    String paramName() default "captcha";
}
