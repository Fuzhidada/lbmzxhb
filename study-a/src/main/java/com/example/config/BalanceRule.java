package com.example.config;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BalanceRule {
    @Bean
    public IRule ribbon() {
//        return new WeightedResponseTimeRule(); //响应时间
        return new RoundRobinRule(); //轮询
//        return new AvailabilityFilteringRule(); //服务是否死掉或者服务处于高并发来分配权重

//        return new RandomRule(); //随机

    }
}
