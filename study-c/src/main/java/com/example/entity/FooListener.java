package com.example.entity;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FooListener implements ApplicationListener<LogEvent> {
    public FooListener() {
    }

    @Override
    public void onApplicationEvent(LogEvent logEvent) {
        String depy = logEvent.getMessage();
        logEvent.getSource().getClass();
        System.out.println(depy);
    }

}
