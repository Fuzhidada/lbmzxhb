package com.epoch.dentsumcgb.job;


import com.epoch.dentsumcgb.entity.TSysRecordlist;
import com.epoch.dentsumcgb.mapper.TSysRecordlistMapper;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/**
 * 上面几个任务都是异步的，
 * 可以在做一个任务去读取上面的任务执行结果
 */

@Component
@Slf4j
public class JobResultTask {

    @Resource
    private TSysRecordlistMapper mapper;

    @XxlJob(value = "JobResultTask")
    public void start() {

        int day;
        String param = XxlJobHelper.getJobParam();
        if (StringUtils.isBlank(param)) {
            day = 30;
        } else {
            day = Integer.parseInt(param);
        }

        //day 天前的数据
        LocalDateTime now = LocalDateTime.now();
        now = now.minus(day, ChronoUnit.DAYS);

        List<TSysRecordlist> recordlists = mapper.selectByDate(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));

        StringBuilder result = new StringBuilder();
        recordlists.forEach(s -> result.append(s.toString()).append("\n"));

        XxlJobHelper.handleResult(200, result.toString());
    }
}
