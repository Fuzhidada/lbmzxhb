package com.example.schedule;

import com.example.biz.FuzhiBiz;
import com.example.biz.email.Email;
import com.example.biz.email.EmailBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

@Configuration
@EnableScheduling// 一般放在springboot启动类中
@EnableAsync //开启异步
@Slf4j
public class ScheduleJob {

    @Resource
    private FuzhiBiz biz;

    @Async //开启异步（多线程）执行
    @Scheduled(cron = "30 0/30 * * * ?")
    public void start1(){
        log.info("定时任务开始执行,thread-name:"+Thread.currentThread().getName());
        biz.save(String.valueOf(Math.random() * 10000));
    }

    @Async //开启异步（多线程）执行
//    @Scheduled(cron = "30 0/30 * * * ?")
    public void start2(){
        log.info("定时任务开始执行,thread-name:"+Thread.currentThread().getName());
        Email email = new Email(new EmailBean("1316286513@qq.com","提醒","每晚锻炼身体哦"));
        email.toSend();
    }
}
