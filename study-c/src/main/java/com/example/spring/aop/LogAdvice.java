package com.example.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Aspect//标注为aop
@Component //交由spring处理
@Scope(value = "singleton")

public class LogAdvice {
    @Pointcut(value = "execution(public * com.example.biz.*.*(..))")
    // public 表示public匹配public方法  * 返回类型，int之类的
    // 之后是直到方法的路径 ， 这里表示所有的方法，(..)表示所有参数，可以 是int
    public void  cut(){
      //pointcut should have enpty body
        //这里的方法也不会被执行。
    }

    @Before("cut()")
    public void check(){
        System.out.println("xx dd");
    }
    @After("cut()")
    public void commonResult(){
        System.out.println("统一处理返回题-----");
    }

}
