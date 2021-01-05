package com.example.zuul;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * fallBack 返回信息
 */
@RestController
public class FallBackConfig {

    @GetMapping("/fallback_Test_A")
    public String fallBackConfig() {
        return "服务发生异常,开启降级处理";
    }


}
