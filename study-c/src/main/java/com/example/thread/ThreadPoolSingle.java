package com.example.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *  此线程为单例线程，只开启一个线程，其他等待中的任务放到
 *  LinkedBlockingQueue
 *  execute 和 submit的区别
 *  submit有返回值future ，线程抛异常时 try catch {future.get} 方法可以捕获到异常
 *  若使用 execute 则 可在ThreadFactory 中 setUncaughtExceptionHandler 对异常进行处理
 *
 */
public class ThreadPoolSingle {

    public static void main(String[] args) {
        ExecutorService executors = Executors.newSingleThreadExecutor(SingleThreadFactory.getThreadFactory());
        try {
            executors.execute(new Task1());
//            Future task = executors.submit(new Task1());
//            task.get();// 调用get方法主线程会被阻塞 直到任务执行完毕， 但是其他线程并不会被阻塞

//            executors.submit(new Task1());
      /*  } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();*/

        } finally {
            executors.shutdown();
        }
    }


}
