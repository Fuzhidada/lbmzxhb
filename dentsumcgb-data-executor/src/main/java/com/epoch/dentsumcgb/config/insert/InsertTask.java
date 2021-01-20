package com.epoch.dentsumcgb.config.insert;

import com.epoch.dentsumcgb.config.BizMapper;
import com.epoch.dentsumcgb.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;


@Slf4j
public class InsertTask implements Callable<Integer> {
    private ArrayList list;
    private BizMapper mapper;

    private PlatformTransactionManager transactionManager;
    private AtomicBoolean flag;
    private CountDownLatch rollBackLatch;
    private CountDownLatch runLatch;


    InsertTask(ArrayList list, BizMapper mapper, CountDownLatch rollBackLatch, CountDownLatch runLatch,
               AtomicBoolean flag, PlatformTransactionManager transactionManager) {
        this.list = list;
        this.mapper = mapper;
        this.rollBackLatch = rollBackLatch;
        this.runLatch = runLatch;
        this.flag = flag;
        this.transactionManager = transactionManager;
    }

    @Override
    public Integer call() {
        log.info("开始执行入库操作");
        int result = 0;
        if (!flag.get()) {
            runLatch.countDown();
            return 0;
        }

        DefaultTransactionDefinition transaction = new DefaultTransactionDefinition();
        transaction.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(transaction);

        try {
            result = mapper.batchInsert(list);

            runLatch.countDown();

            // 等待其他线程执行完毕,若60秒内还没执行完毕，即抛异常
            boolean wait = rollBackLatch.await(ThreadUtil.WAIT_TIME, TimeUnit.SECONDS);
            if (!wait) {
                throw new RuntimeException("执行insert语句时超时 {}" + list.size());
            }

            // 提交还是回滚
            if (flag.get()) {
                transactionManager.commit(status);
            } else {
                transactionManager.rollback(status);
                log.error("批次提交过程中有任务执行失败，执行回滚操作");
            }
        } catch (Exception e) {
            log.error("处理失败,进行数据回滚" + ExceptionUtils.getStackTrace(e));
            flag.set(false);
            runLatch.countDown();
            rollBackLatch.countDown();
            transactionManager.rollback(status);
        }
        return result;

    }


}
