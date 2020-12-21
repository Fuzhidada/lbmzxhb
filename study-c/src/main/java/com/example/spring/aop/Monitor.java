package com.example.spring.aop;

import com.example.entity.Foo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;


@Aspect
@Component
public class Monitor {

    @Resource
    private Foo foo;


    @Pointcut(value = "execution(public void com.example.entity.*.set*(..))")
    public void cut() {
    }

    @Before("cut()")
    public void commonResult(JoinPoint result) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String methodName = result.getSignature().getName().replace("set", "");

        Object before = foo.getClass()
                .getDeclaredMethod("get" + methodName)
                .invoke(foo);

        List<Object> args = Arrays.asList(result.getArgs());
        System.out.println("pointcut：" + methodName + ",参数：" + before + "--->" + args.get(0));
    }

}
