package com.example.biz.email;

import lombok.Data;

@Data
public class EmailBean {
    private String receiver;
    private String subject;
    private String text;

    public EmailBean(String receiver, String subject, String text) {
        this.receiver = receiver;
        this.subject = subject;
        this.text = text;
    }
}
