package com.jacknoob.blog.config;

import com.jacknoob.blog.properties.LeanCloud;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * @author JackJun
 * 2019/10/6 20:09
 * Life is not just about survival.
 */
@Component
public class MailServer {
    private final LeanCloud leanCloud;
    private final JavaMailSender mailSender;

    /**
     * 邮件发送者
     */
    @Value("${spring.mail.username}")
    private String MAIL_SENDER;

    public MailServer(JavaMailSender mailSender, LeanCloud leanCloud) {
        this.leanCloud = leanCloud;
        this.mailSender = mailSender;
    }

    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(MAIL_SENDER);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    public void notify(String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(MAIL_SENDER);
        helper.setTo(leanCloud.getToMail());
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }
}
