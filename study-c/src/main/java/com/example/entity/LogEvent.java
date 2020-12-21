package com.example.entity;


import lombok.Data;
import org.springframework.context.ApplicationEvent;


@Data
public class LogEvent extends ApplicationEvent {
    public LogEvent(int source) {
        super(source);
    }

    private String message;

    private void init(String a, Object c){

    }

}
