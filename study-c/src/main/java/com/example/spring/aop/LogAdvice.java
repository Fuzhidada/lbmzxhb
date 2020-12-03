package com.example.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *  aop的实现 为动态代理模式
 *
 *  动态代理分为 jdk动态代理 和 cglib（code generator library）动态代理
 *   jdk动态代理的实现要求代理的类要有接口 cglib不要求
 *
 */

@Aspect//切面 标注为aop
@Component //交由spring处理
@Scope(value = "singleton")

public class LogAdvice {
    @Pointcut(value = "execution(public void com.example.biz.*.*(..))")
    // public 表示public匹配public方法  * 返回类型，int之类的
    // 之后是直到方法的路径 ， 这里表示所有的方法，(..)表示所有参数，可以 是int
    //定义切入的面 pointcut
    public void  cut(){
      //pointcut should have enpty body
        //这里的方法也不会被执行。
    }

    @Pointcut(value = "execution(public * com.example.biz.*.*(..))")
    public void  cuts1(){
    }

    // jointpot
    @Before("cut()")
    public void check(JoinPoint t){
        System.out.println("进入方法之前-----");
        System.out.println(t.getArgs()[0]);
    }


    @After("cut()")
    public void commonResult(){
        System.out.println("统一处理返回题-----");
    }

    @AfterThrowing("cut()")
    public void afterThrowing(){
    }
    @AfterReturning("cut()")
    public void AfterReturning(){
    }
}
