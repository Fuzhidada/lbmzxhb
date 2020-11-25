package com.example.config;

import com.example.feign.IBTestFeign;
import org.springframework.stereotype.Component;

@Component
public class FallBackFeign implements IBTestFeign {
    public String testB() {
        return "调用B超时";
    }

}
