package com.example.biz;

import com.example.entity.Fuzhi;
import com.example.mapper.FuzhiMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class FuzhiBiz {

    @Resource
    FuzhiMapper mapper;


    @Validated
    public void save(String a) {
        Fuzhi fuzhi = new Fuzhi();
        fuzhi.setA(new Date().toString());
        fuzhi.setB("2");
//        mapper.insert(fuzhi);   // sqllite 数据库 本地测试使用
    }



}
