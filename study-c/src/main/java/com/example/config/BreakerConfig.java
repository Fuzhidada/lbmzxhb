package com.example.config;

import com.netflix.hystrix.*;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
public class BreakerConfig extends HystrixCommand {
    private String param;

    public BreakerConfig(String param) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(""))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("fuzhi-fallbackPool"))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withCoreSize(2)
                                .withMaxQueueSize(2) // 设置等待队列大小为10
                                .withQueueSizeRejectionThreshold(1)
                ).andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withCircuitBreakerEnabled(true)
                                .withCircuitBreakerRequestVolumeThreshold(20)
                                .withCircuitBreakerErrorThresholdPercentage(40)
                                .withCircuitBreakerSleepWindowInMilliseconds(3000)
                                // 设置超时时间
                                .withExecutionTimeoutInMilliseconds(300)
                                // 设置fallback最大请求并发数
                                .withFallbackIsolationSemaphoreMaxConcurrentRequests(30))
        );

        this.param=param;
    }

    @Override
    protected String run() {
        System.out.println(Thread.currentThread().getName());

        try {
            Thread.sleep(100);
            //withExecutionTimeoutInMilliseconds(300)
            //设置超时时间300 业务执行时间100 空闲200
            // 若当前有200个请求 每个请求间隔200 执行完的线程在100后会得到释放 则刚好能执行完 不会熔断
            //若间隔50  则应该会有一半请求被熔断---错误 因为设置了2个线程 刚好100能执行两个不发生熔断
            // 间隔25 则一半熔断-- 正确

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return param;
    }

    @Override
    protected String getFallback() {
        System.out.println("降级");
        return "请稍后重试";
    }
}
