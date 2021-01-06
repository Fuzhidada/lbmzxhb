package com.example.biz;

import com.example.biz.listener.BizEvent;
import com.example.config.BreakerConfig;
import com.example.entity.Fuzhi;
import com.example.mapper.FuzhiMapper;
import com.example.common.util.CommonResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.Resource;
import java.util.Date;

@Service
//@Scope("singleton")
//单独在service层添加了此注解后,并没有生效
//在controller也添加了就生效了
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//@Scope(WebApplicationContext.SCOPE_REQUEST)
// 这样写是无效的 只有singleton和prototype能这样写
//@Scope("session")
//@Scope("application")
//@RequestScope//每个请求k都是3
//@SessionScope//每个session k都会从头开始
//@ApplicationScope
public class FuzhiBiz {

    private int k = 3;

    @Resource
    private FuzhiMapper mapper;

    @Resource
    private ApplicationContext context;

    @EventListener
    public void save(String a) {
        Fuzhi fuzhi = new Fuzhi();
        fuzhi.setA(new Date().toString());
        fuzhi.setB(a);

        // 看看arraylist的源码
      /*  ArrayList list = new ArrayList();
        list.add(1);*/

        mapper.insert(fuzhi);   // sqllite 数据库 本地测试使用
        context.publishEvent(new BizEvent<>(fuzhi, "insert"));
    }

    public int beanTest() {
        return k--;
    }

    @HystrixCommand
    public CommonResult fallTest() {
        //配置中
        BreakerConfig breakerConfig = new BreakerConfig("xxda");
        return CommonResult.success(breakerConfig.execute());
    }
/*
    public String getFallback(){
        return "稍后重试下哦";
    }*/

}
