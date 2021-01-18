package com.epoch.dentsumcgb.job;

import com.epoch.dentsumcgb.biz.ReadAndInsertBiz;
import com.epoch.dentsumcgb.entity.THypFinData;
import com.epoch.dentsumcgb.entity.THypHrData;
import com.epoch.dentsumcgb.mapper.THypFinDataMapper;
import com.epoch.dentsumcgb.mapper.THypHrDataMapper;
import com.epoch.dentsumcgb.util.ThreadUtil;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.util.stream.Stream;


@Component
@Slf4j
public class HYPTask {
    @Value("${smb.localPath}")
    private String localPath;
    @Value("${smb.hyp}")
    private String hyp;

    @Resource
    private ReadAndInsertBiz biz;

    @Resource
    private THypFinDataMapper finDataMapper;
    @Resource
    private THypHrDataMapper hypHrDataMapper;


    @XxlJob(value = "HYPTask")
    public void start() {
        XxlJobHelper.handleResult(200, "xxxxxx");
        System.out.println("任务--->");
    }

    public void dealFinData() {
        File localParentDir = new File(localPath + hyp);
        File[] files = localParentDir.listFiles();

        assert files != null;
        Stream.of(files).forEach(file -> {
            String name = file.getName();
            if (name.contains("HYP_FIN_DATA")) {
                ThreadUtil.getCacheExecutor().submit(() ->
                        biz.readAndInsert(file, THypFinData.class, finDataMapper, THypFinData.getMapping()));
            }
            if (name.contains("HYP_HR_DATA")) {
                ThreadUtil.getCacheExecutor().submit(() ->
                        biz.readAndInsert(file, THypHrData.class, hypHrDataMapper, THypHrData.getMapping()));
            }
        });

    }


}




    
