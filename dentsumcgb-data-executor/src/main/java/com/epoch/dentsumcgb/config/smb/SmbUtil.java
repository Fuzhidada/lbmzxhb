package com.epoch.dentsumcgb.config.smb;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.xxl.job.core.context.XxlJobHelper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.*;

@Service
public class SmbUtil {

    @Resource
    @Qualifier("d365")
    Smb d365;

    @Resource
    @Qualifier("hyp")
    Smb hyp;

    public boolean start() {
        ExecutorService pool = Executors.newFixedThreadPool(2,
                new ThreadFactoryBuilder().setNameFormat("smb-download-%d").build()) ;
        Future<Boolean> d365Task = pool.submit(d365);
        Future<Boolean> hypTask = pool.submit(hyp);

        try {
            if (d365Task.get() && hypTask.get()) {
                return true;
            }
        } catch (ExecutionException | InterruptedException e) {
            XxlJobHelper.handleResult(500, "共享文件下载任务执行失败" + ExceptionUtils.getStackTrace(e));
            Thread.currentThread().interrupt();
            return false;
        } finally {
            pool.shutdown();
        }
        return true;
    }
}
