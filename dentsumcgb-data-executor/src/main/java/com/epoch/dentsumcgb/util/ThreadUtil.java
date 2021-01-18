package com.epoch.dentsumcgb.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class ThreadUtil {
    private ThreadUtil() {
    }

    //数据量分片大小
    public static final int SKIP_SIZE = 10000;
    // 设置每个线程执行插入的数据量为10000 * 10 个线程 即每个文件最大的数据量应为10*10000 超出会产生阻塞问题
    private static final ExecutorService executor = new ThreadPoolExecutor(1, 100,
            60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000),
            new ThreadFactoryBuilder().setNameFormat("dentsu-insert-%d").build());

    public static ExecutorService getExecutor() {
        return executor;
    }

    private static final ExecutorService cacheExecutor = Executors.newCachedThreadPool(new ThreadFactoryBuilder().setNameFormat("dentsu-insert-%d").build());

    public static ExecutorService getCacheExecutor() {
        return cacheExecutor;
    }

}
