package com.example.rest;

import com.example.biz.RedisTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RedisController {

    @Resource
    private RedisTest redisTest;


    @GetMapping("/redis/{type}")
    public String redisTest(@PathVariable int type) throws InterruptedException {

        if(type==1){
            redisTest.redisTestList();
        }
        if(type==2){
            redisTest.redisTestString();
        }

        if(type==3){
            redisTest.redisTestZset();
        }
        return "道路通畅，随时开车";
    }


}
