package com.example.biz.email;

public interface IEmail {
    boolean initSender();
    boolean initReceiver();
     boolean send();
}
