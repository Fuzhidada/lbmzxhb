package com.example.thread;

import java.util.concurrent.*;

/**
 * 固定线程池数
 *
 * maxpol-core=临时工用完 0s后就被销毁..外包惨啊
 * LinkedBlockingQueue 有序 有界（2的31次方-1）
 *
 *
 */
public class ThreadPoolFixed {

    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(2, SingleThreadFactory.getThreadFactory());
        try {
            Future task = executors.submit(new Task1());
            task.get();// 调用get方法主线程会被阻塞 直到任务执行完毕， 但是其他线程并不会被阻塞
            executors.submit(new Task1());
            executors.submit(new Task1());
            executors.submit(new Task1());
            executors.submit(new Task1());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executors.shutdown();
        }
    }


}
