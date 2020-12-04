package com.example.biz;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisTest {
    @Resource
    RedisTemplate<String, String> redisTemplate;

    public boolean redisTestString() throws InterruptedException {

        redisTemplate.opsForValue().setIfAbsent("fuzhi","valu测试");

        System.out.println(redisTemplate.opsForValue().get("fuzhi"));

        // 存在key则不操作 否则操作
      boolean dd= redisTemplate.opsForValue().setIfAbsent("fuzhi","ddsa");

        System.out.println(dd);

        //可以看到值 被覆盖了
        redisTemplate.opsForValue().set("fuzhi","cccccc",10,TimeUnit.SECONDS);

        System.out.println(redisTemplate.opsForValue().get("fuzhi"));

        //10s 后再次取值
        Thread.sleep(10001);

        System.out.println(redisTemplate.opsForValue().get("fuzhi"));

        return true;
    }


    public boolean redisTestList() {

        // 在集合的指定位置插入元素,如果指定位置已有元素，则覆盖，没有则新增，超过集合下标+n则会报错。
//        redisTemplate.opsForList().set("fuzhi", 1300, "d");

        //  操作list 从list的左边插入
        redisTemplate.opsForList().leftPush("fuzhi", "1号元素");

        // 在元素 v 的后面添加 v1  --》 若v 不存在 则  添加失败
        redisTemplate.opsForList().rightPush("fuzhi", "22号元素", "dddd");

        System.out.println(redisTemplate.opsForList().size("fuzhi"));

        // 偏移量从0开始计算  取出 0-2的元素
        List<String> list1 = redisTemplate.opsForList().range("fuzhi", 0, 2);
        list1.forEach(System.out::println);

        //检出最左边的元素 ，
        String mm = redisTemplate.opsForList().leftPop("fuzhi");
        System.out.println(mm);

        // 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
        // 此处被阻塞了 最初设置10s 配置文件中设置超时时间5s 会抛出异常，程序不会走到下面区
        String mm1 = redisTemplate.opsForList().leftPop("fuzhi", 10, TimeUnit.SECONDS);
        System.out.println(mm);

        // 移动fuzhi的最右边的元素到 fuzhi2 的左边开头
        String mm12 = redisTemplate.opsForList().rightPopAndLeftPush("fuzhi", "fuzhi2");

        System.out.println(mm12);
        //

        return true;
    }

    public boolean redisTestZset(){

        // zset 会自动排序哦
        Set<ZSetOperations.TypedTuple<String>> z=new HashSet<>();
        ZSetOperations.TypedTuple<String> typedTuple1 = new DefaultTypedTuple<>("E",6.0);
        ZSetOperations.TypedTuple<String> typedTuple2 = new DefaultTypedTuple<>("F",7.0);
        ZSetOperations.TypedTuple<String> typedTuple3 = new DefaultTypedTuple<>("G",5.0);

        z.add(typedTuple1);
        z.add(typedTuple2);
        z.add(typedTuple3);

        redisTemplate.opsForZSet().add("fuzhi",z);

        System.out.println(redisTemplate.opsForZSet().range("fuzhi",0,-1));

        return true;
    }

}
