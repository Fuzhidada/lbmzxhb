package com.example.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * 1.8新增的线程池
 * 一个具有抢占式操作的线程池，任务会并发的执行
 * 不保证任务的执行顺序， 参数为并发执行的线程数
 */


public class ThreadPoolWorkStealing {

    public static void main(String[] args) {
        ExecutorService executors = Executors.newWorkStealingPool(2);
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
