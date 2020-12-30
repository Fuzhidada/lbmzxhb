package com.example.biz.email;


import org.springframework.mail.SimpleMailMessage;

public interface IEmail {
    SimpleMailMessage send();
}
