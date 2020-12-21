package com.example.rest;

import com.example.biz.FuzhiBiz;
import com.example.config.Moom;
import com.example.util.CommonResult;
import com.example.vo.DefaultQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j

public class SentRequestController {
    @Resource
    private FuzhiBiz biz;

    @GetMapping("/testb/{t}")
    public void testB(@Valid @Moom String t) {
        biz.save("22");
    }


    /**
     * 测试自定义注解
     * 自定义的校验注解写完了 后面就是 controllerAdvice了
     */
    @GetMapping("/testc")
    public CommonResult testB(@Valid @RequestBody DefaultQueryVo vo) {
        int a = 1 / (vo.getDd() - 2);  //
        return CommonResult.success("testc 测试数据");
    }


    @GetMapping("/hash")
    public void test() {

        ConcurrentHashMap mm=new ConcurrentHashMap(8);
        mm.put(1,2);


        //测试hashmap频繁resize
      /*  HashMap map = new HashMap(2);
        for (int i = 0; i < 300; i++) {
            map.put(i, 3);
        }*/
    }

}
