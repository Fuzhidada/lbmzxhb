package com.example.biz.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Slf4j
public abstract class AbstractEmail implements IEmail {

    private static JavaMailSenderImpl mail;

    private static final String EMAIL_HOST = "smtp.163.com";
    private static final int EMAIL_PORT = 25;
    private static final String EMAIL_USER = "fuzhi66g@163.com";
    private static final String EMAIL_PASSWORD = "FZ1316286513";

     AbstractEmail() {
        if (mail == null) {
            synchronized (AbstractEmail.class) {
                if (mail == null) {
                    log.debug("次数");
                    mail = new JavaMailSenderImpl();
                    mail.setHost(EMAIL_HOST);
                    mail.setPort(EMAIL_PORT);
                    mail.setUsername(EMAIL_USER);
                    mail.setPassword(EMAIL_PASSWORD);
                }
            }
        }

    }

    public boolean toSend() {
        SimpleMailMessage mailMessage = send();
        mailMessage.setFrom(EMAIL_USER);
        mail.send(mailMessage);
        log.info("邮件发送完毕");
        return true;
    }

}
