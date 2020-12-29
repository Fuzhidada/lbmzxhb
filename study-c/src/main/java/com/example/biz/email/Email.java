package com.example.biz.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 这就是模板模式？
 * 有点麻烦哦
 */

@Service
@Slf4j
public class Email extends AbstractEmail{

    @Override
    public boolean toSend() {
        return super.toSend();
    }

    @Override
    public boolean initSender() {
        return false;
    }

    @Override
    public boolean initReceiver() {
        return false;
    }

    @Override
    public boolean send() {
        return false;
    }
}
