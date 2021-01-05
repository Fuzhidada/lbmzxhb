package com.example.spring;

import com.example.spring.circledependency.entity.Teacher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * 理解测试循环依赖
 */
public class run {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanConfig.class);
        applicationContext.getBean(Teacher.class);
        ThreadLocal a = new ThreadLocal();
        a.set("ThreadLocal");

    }
}
