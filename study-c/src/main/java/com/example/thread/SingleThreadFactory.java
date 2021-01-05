package com.example.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ThreadFactory;


/**
 * 写个单例模式的thread工厂
 */
public class SingleThreadFactory {
    private static volatile ThreadFactory factory;

    private SingleThreadFactory() {
    }

    public static ThreadFactory getThreadFactory() {
        if (factory == null) {
            synchronized (SingleThreadFactory.class) {
                if (factory == null) {
                    factory = new ThreadFactoryBuilder()
                            .setNameFormat("fuzhi-%d")
                            .setUncaughtExceptionHandler((thread, throwable) ->
                                    System.out.println(thread.getName() + "异常： " + throwable))
                            .build();
                }
            }
        }
        return factory;
    }


}
