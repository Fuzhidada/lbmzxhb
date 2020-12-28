package com.example.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * ReentrantLock 是对象锁--
 * 为什么是可重入的:
 * 父类 AbstractQueuedSynchronizer 里面有个 int state变量
 * 若不为0 判断是否为当前锁线程 是则 +1 否则 未获得锁
 * 公平锁里面实现的是个队列--
 * int c = this.getState();
 * if (c == 0) {
 * if (!this.hasQueuedPredecessors() && this.compareAndSetState(0, acquires)) {
 * this.setExclusiveOwnerThread(current);
 * return true;
 * }
 * } else if (current == this.getExclusiveOwnerThread()) {
 * int nextc = c + acquires;
 * if (nextc < 0) {
 * throw new Error("Maximum lock count exceeded");
 * }
 * <p>
 * this.setState(nextc);
 * return true;
 * }
 */

public class ReentrantLockTask {
    //瑞恩崔inlock
    private Lock reentrantLock = new ReentrantLock();
    static int m;

    private void showLost(int k) {
        if (m >= 0) {
            try {

                long t = (long) (Math.random() * 10000);
                System.out.println(k + " :模拟耗时: " + t);
//                Thread.sleep(t);

                reentrantLock.tryLock(100, TimeUnit.SECONDS);
                System.out.println("修改前" + m);
                m += k;

//                Semaphore
                reentrantLock.unlock();
                System.out.println("修改后" + m);
            } catch (InterruptedException e) {
                System.out.println("未获取到锁额" + e.getLocalizedMessage());
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockTask task = new ReentrantLockTask();
        new Thread(() -> task.showLost(2)).start();
        new Thread(() -> task.showLost(5)).start();
        new Thread(() -> task.showLost(1)).start();
        new Thread(() -> task.showLost(3)).start();


      /*
      //为对象锁 多个线程没有锁住哦
       //ReentrantLockTask task = new ReentrantLockTask();
        new Thread(() -> new ReentrantLockTask().showLost(2)).start();
        new Thread(() -> new ReentrantLockTask().showLost(5)).start();
        new Thread(() -> new ReentrantLockTask().showLost(1)).start();
        new Thread(() -> new ReentrantLockTask().showLost(3)).start();*/
        /*
         *
         * 未加锁前的效果
         * 修改前0
         * 修改前1
         * 模拟耗时: 107
         * 模拟耗时: 836
         * 修改前2
         * 模拟耗时: 721
         * 修改前3
         * 模拟耗时: 102
         * 修改后4
         * 修改后4
         * 修改后4
         * 修改后4
         */

        /*
         *
         * 加锁后
         * 修改前0
         * 模拟耗时: 500
         * 修改后1
         * 修改前1
         * 模拟耗时: 139
         * 修改后2
         * 修改前2
         * 模拟耗时: 240
         * 修改后3
         * 修改前3
         * 模拟耗时: 697
         * 修改后4
         */


    }


}
