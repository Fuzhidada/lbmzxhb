package com.example.biz;

import com.example.biz.listener.BizEvent;
import com.example.entity.Fuzhi;
import com.example.mapper.FuzhiMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
//@Scope("singleton")
//@Scope("prototype")
//@Scope("request")
//@Scope("session")
@Scope("application")
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
        /*测试spring的单例*/
        //测试发现 每次请求 k都会 -- 说明是 biz这个类是单例的
        return k--;
    }


}
