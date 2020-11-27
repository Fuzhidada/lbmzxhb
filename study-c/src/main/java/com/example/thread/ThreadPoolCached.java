package com.example.thread;

import java.util.concurrent.*;


/**
 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。示例代码如下
 * 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
 * SynchronousQueue 同步队列 内部容量是0 插入即移除
 * cached 线程池默认保存60s才进行回收
 *
 * 1.ArrayBlockingQueue 是一个基于数组结构的有界阻塞队列,此队列按 FIFO(先进先出)原则对元素进行排序。
 */


public class ThreadPoolCached {

    public static void main(String[] args) {
        ExecutorService executors = Executors.newCachedThreadPool( SingleThreadFactory.getThreadFactory());
        try {
            Future task = executors.submit(new Task1());
//            task.get();// 调用get方法主线程会被阻塞 直到任务执行完毕， 但是其他线程并不会被阻塞
            executors.submit(new Task1());
            executors.submit(new Task1());
            executors.submit(new Task1());
            executors.submit(new Task1());

        } finally {
            executors.shutdown();
        }
    }


}
