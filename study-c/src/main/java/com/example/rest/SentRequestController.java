package com.example.rest;

import com.example.biz.FuzhiBiz;
import com.example.util.CommonResult;
import com.example.vo.DefaultQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SentRequestController {
    @Resource
    private FuzhiBiz biz;

    @GetMapping("/testb/{t}")
    public void testB(@PathVariable String t) {
        biz.save(t);
    }


    /**
     * 测试自定义注解
     * 自定义的校验注解写完了 后面就是 controllerAdvice了
     */
    @GetMapping("/testc")
    public CommonResult testB(@RequestBody DefaultQueryVo vo) {
        int a = 1 / (vo.getDd() - 2);  //
        return CommonResult.success("testc 测试数据");
    }

    @GetMapping("/hash")
    public void test() {

        ConcurrentHashMap mm = new ConcurrentHashMap(8);
        mm.put(1, 2);


        //测试hashmap频繁resize
      /*  HashMap map = new HashMap(2);
        for (int i = 0; i < 300; i++) {
            map.put(i, 3);
        }*/
    }

    @GetMapping("beanTest")
    public CommonResult beanTest() {
        return CommonResult.success(biz.beanTest());
    }

}
