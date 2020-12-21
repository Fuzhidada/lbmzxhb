package com.example.biz;

import com.example.entity.Fuzhi;
import com.example.mapper.FuzhiMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

@Service
public class FuzhiBiz {

    @Resource
    FuzhiMapper mapper;

    public void save(String a) {
        Fuzhi fuzhi = new Fuzhi();
        fuzhi.setA(new Date().toString());
        fuzhi.setB("2");

        // 看看arraylist的源码
        ArrayList list=new ArrayList();
        list.add(1);
//        mapper.insert(fuzhi);   // sqllite 数据库 本地测试使用
    }



}
