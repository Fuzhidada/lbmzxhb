package com.epoch.dentsumcgb.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class ThreadUtil {
    private ThreadUtil() {
    }

    //数据量分片大小
    public static final int SKIP_SIZE = 5000;
    public static final int WAIT_TIME = 60 * 10;

    private static final ExecutorService executor = new ThreadPoolExecutor(4, 4,
            60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(5000),
            new ThreadFactoryBuilder().setNameFormat("dentsu-start-%d").build());

    public static ExecutorService getExecutor() {
        return executor;
    }

    private static final ExecutorService cacheExecutor = Executors.newCachedThreadPool(
            new ThreadFactoryBuilder().setNameFormat("dentsu-insert-%d").build());

    public static ExecutorService getCacheExecutor() {
        return cacheExecutor;
    }

    private static final ExecutorService fixedExecutor = Executors.newFixedThreadPool(2,
            new ThreadFactoryBuilder().setNameFormat("dentsu-download-%d").build());

    public static ExecutorService fixedExecutor() {
        return fixedExecutor;
    }
}
