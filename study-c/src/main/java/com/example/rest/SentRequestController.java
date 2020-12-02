package com.example.rest;

import com.example.biz.FuzhiBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@Slf4j

public class SentRequestController {
    @Resource
    private FuzhiBiz biz;

    @GetMapping("/testb")
    public void testB(){
     biz.save("22");
    }

    @GetMapping("/hash")
    public void test(){
        //测试hashmap频繁resize
        HashMap map=new HashMap(2);
        for (int i = 0; i <300 ; i++) {
            map.put(i,3);
        }
    }

}
