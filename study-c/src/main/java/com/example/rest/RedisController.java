package com.example.rest;

import com.example.biz.RedisTest;
import com.example.entity.Foo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RedisController {

    @Resource
    private RedisTest redisTest;

    @Resource
    private Foo foo;

    @GetMapping("/monitor")
    public void redisTest() {
        System.out.println("进入rest");
        foo.setA(1);
        foo.setB(2);
        foo.setB(22);

        List<String> ss = new ArrayList<>();
        ss.stream();
        ss.parallelStream();
    }


    @GetMapping("/redis/{type}")
    public String redisTest(@PathVariable int type) throws InterruptedException {

        if (type == 1) {
            redisTest.redisTestList();
        }
        if (type == 2) {
            redisTest.redisTestString();
        }

        if (type == 3) {
            redisTest.redisTestZset();
        }
        return "道路通畅，随时开车";
    }


}
