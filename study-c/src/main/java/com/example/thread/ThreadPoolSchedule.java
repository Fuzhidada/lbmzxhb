package com.example.thread;

import java.util.concurrent.*;


/**
 * 使用DelayedWorkQueue 无边界 内部元素有过期时间 只有过期的才会被取出
 * executors.schedule(new Task1(), 5, TimeUnit.SECONDS); 可以设置延时时间 不需要 submit
 */


public class ThreadPoolSchedule {

    public static void main(String[] args) {
        ScheduledExecutorService executors = Executors.newScheduledThreadPool(2, SingleThreadFactory.getThreadFactory());
        try {
            executors.schedule(new Task1(), 5, TimeUnit.SECONDS);
//            task.get();// 调用get方法主线程会被阻塞 直到任务执行完毕， 但是其他线程并不会被阻塞
//            executors.submit(new Task1());
//            executors.submit(new Task1());
//            executors.submit(new Task1());
//            executors.submit(new Task1());
        } finally {
            executors.shutdown();
        }
    }


}
