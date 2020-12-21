package com.example;

import com.example.entity.Foo;
import com.example.entity.FooListener;
import com.example.entity.LogEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 开启多个实例 测试负载均衡
 */

@SpringBootApplication
@MapperScan(value = "com.example.mapper")
public class CApplication {

    public static void main(String[] args) {

        SpringApplication.run(CApplication.class, args);
/*
        SpringApplication application = new SpringApplication(CApplication.class);
       //需要把监听器加入到spring容器中
         application.addListeners(new FooListener());
        ConfigurableApplicationContext context =  application.run(args);
       //发布事件
        context.publishEvent(new LogEvent(new Foo()));

      context.close();*/

    }

}
