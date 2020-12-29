package com.example.biz.listener;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;


public class BizEvent<T> extends ApplicationEvent {

    @Getter
    private String operate;

    public BizEvent(T source, String operate) {
        super(source);
        this.operate = operate;
    }

}
