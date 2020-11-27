package com.example.thread;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Task1 implements Runnable {
    int[] k = {1, 2, 3, 4};

    @Override
    public void run() {
        String PATTERN = "yyyy-MM-dd HH:mm:ss";

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN)));
        System.out.println(Thread.currentThread().getName());
      /*  try {
            Thread.sleep((long) ((Math.random())*1000*3));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        int a=1/0;
        Arrays.stream(k).forEach(System.out::print);
        System.out.println();
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN)));
    }

    int[] getK() {
        return k;
    }
}
