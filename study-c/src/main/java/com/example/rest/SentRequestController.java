package com.example.rest;

import com.example.biz.FuzhiBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class SentRequestController {
    @Resource
    private FuzhiBiz biz;

    @GetMapping("/testb")
    public void testB(){
     biz.save("22");
    }
}
