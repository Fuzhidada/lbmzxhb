package com.example.thread;

import java.util.concurrent.Semaphore;

public class SemaphoreTask implements Runnable {
    //赛摩佛
    private Semaphore semaphore = new Semaphore(2);

    @Override
    public void run() {
        try {
            semaphore.acquire();


            semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


    }


}
