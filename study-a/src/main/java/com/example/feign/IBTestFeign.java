package com.example.feign;

import com.example.config.FallBackFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "test-b", fallback = FallBackFeign.class)
public interface IBTestFeign {
    @GetMapping("/testb")
    String testB();
}
