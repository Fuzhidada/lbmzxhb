package com.example.rest;

import com.example.biz.TemplateBiz;
import com.example.feign.IBTestFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SentRequestController {
    @Resource
    private TemplateBiz templateBiz;
    @Resource
    private IBTestFeign testFeign;

//    @GetMapping("/send")
//    public String send(){
//        templateBiz.sendPost();
//        return "";
//    }

    @GetMapping("/testA")
    public String sendA() {

        System.out.println("开始调用11");
        return testFeign.testB();
    }

}
