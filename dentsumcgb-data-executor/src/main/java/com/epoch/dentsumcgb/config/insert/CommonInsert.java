package com.epoch.dentsumcgb.config.insert;

import com.epoch.dentsumcgb.config.BizMapper;
import com.epoch.dentsumcgb.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static com.epoch.dentsumcgb.util.ThreadUtil.SKIP_SIZE;

@Slf4j
@Service
public class CommonInsert {

    @Autowired
    private PlatformTransactionManager transactionManager;



    public void setDB(ArrayList list, BizMapper mapper) {

        int k = list.size() / SKIP_SIZE + 1;
        Future[] futures = new Future[k];
        //回滚线程
        CountDownLatch rollBackLatch = new CountDownLatch(1);
        //任务线程
        CountDownLatch runLatch = new CountDownLatch(k);
        //线程执行情况 true:成功 false: 失败
        AtomicBoolean flag = new AtomicBoolean(true);
        for (int i = 0; i < k; i++) {
            futures[i] = ThreadUtil.getExecutor().submit(new InsertTask((ArrayList) list.stream()
                    .skip(i * SKIP_SIZE)
                    .limit(SKIP_SIZE)
                    .collect(Collectors.toList()), mapper, rollBackLatch, runLatch, flag, transactionManager));
        }
        //主线程等待子线程都执行完毕 即提交事务
        try {
            runLatch.await();
        } catch (InterruptedException e) {
            log.error("插入任务执行超时 {}", ExceptionUtils.getStackTrace(e));
            Thread.currentThread().interrupt();
        }

        // 全部执行成功则提交事务
        if (flag.get()) {
            rollBackLatch.countDown();
        }
        log.debug(list.size() + ": insert-result");

    }
}
