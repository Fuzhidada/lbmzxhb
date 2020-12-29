package com.example.biz.email;

public abstract class AbstractEmail implements IEmail {
    public boolean toSend() {
        initSender();
        initReceiver();
        send();
        return true;
    }
}
