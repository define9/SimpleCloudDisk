package cn.tomisme.domain.domainservice;

import cn.tomisme.config.EMailConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Slf4j
@Service
@RequiredArgsConstructor
public class CoreService {

    /**
     * Spring Boot 提供了一个发送邮件的简单抽象，使用的是下面这个接口，这里直接注入即可使用
     */
    private final JavaMailSender mailSender;
    private final EMailConfig eMailConfig;

    public void sendMailCode(String to, String code) {
        sendSimpleMail(to, "SimpleCloudDisk邮件验证",
                "您正在进行SimpleCloudDisk的邮件验证: 您的验证码为: " + code);
    }

    private void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(eMailConfig.getFrom());
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    private void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //邮件发送人
            helper.setFrom(eMailConfig.getFrom());
            //邮件接收人
            helper.setTo(to);
            //邮件主题
            message.setSubject(subject);
            //邮件内容，html格式
            helper.setText(content, true);
            //发送
            mailSender.send(message);
            //日志信息
            log.info("邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常！", e);
        }
    }
}
