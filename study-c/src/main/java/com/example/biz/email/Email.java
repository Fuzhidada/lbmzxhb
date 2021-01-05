package com.example.biz.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


/**
 * 这就是模板模式？
 * 有点麻烦哦
 */

@Slf4j
public class Email extends AbstractEmail {

    private final static String pit = ";";

    private EmailBean emailBean;

    public Email(EmailBean emailBean) {
        super();
        this.emailBean = emailBean;
    }

    @Override
    public boolean toSend() {
        return super.toSend();
    }

    @Override
    public SimpleMailMessage send() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String[] receiver = this.emailBean.getReceiver().split(pit);

        //第一个为发送人
        mailMessage.setTo(receiver[0]);

        for (int i = 1; i < receiver.length; i++) {
            if (receiver[i] != null) {
                mailMessage.setCc(receiver[i]);
            }
        }

        mailMessage.setSubject(emailBean.getSubject());
        mailMessage.setText(emailBean.getText());

        return mailMessage;
    }

    public static void main(String[] args) {
        Email email = new Email(new EmailBean("1316286513@qq.com", "提醒", "每晚锻炼身体哦"));
        email.toSend();
    }


}
