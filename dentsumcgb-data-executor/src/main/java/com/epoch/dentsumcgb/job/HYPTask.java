package com.epoch.dentsumcgb.job;

import com.epoch.dentsumcgb.biz.ReadAndInsertBiz;
import com.epoch.dentsumcgb.config.smb.Smb;
import com.epoch.dentsumcgb.constant.SysType;
import com.epoch.dentsumcgb.entity.THypFinData;
import com.epoch.dentsumcgb.entity.THypHrData;
import com.epoch.dentsumcgb.mapper.THypFinDataMapper;
import com.epoch.dentsumcgb.mapper.THypHrDataMapper;
import com.epoch.dentsumcgb.util.ThreadUtil;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.MalformedURLException;

@Component
@Slf4j
public class HYPTask {

    @Resource
    private ReadAndInsertBiz biz;

    @Resource
    private THypFinDataMapper finDataMapper;
    @Resource
    private THypHrDataMapper hypHrDataMapper;

    @Resource
    @Qualifier("hyp")
    Smb hyp;

    @XxlJob(value = "HYPTask")
    public void start() {
        dealData();
    }

    public void dealData() {
        SmbFile[] files;
        try {
            files = hyp.getSmbFile().listFiles();

            for (SmbFile file : files) {
                if (!file.isFile()) {
                    continue;
                }
                String name = file.getName();
                if (name.contains("HYP_FIN_DATA")) {
                    ThreadUtil.getCacheExecutor().submit(() ->
                            biz.readAndInsert(file, THypFinData.class, finDataMapper, THypFinData.getMapping(), SysType.hyp.getValue(), hyp));
                }
                if (name.contains("HYP_HR_DATA")) {
                    ThreadUtil.getCacheExecutor().submit(() ->
                            biz.readAndInsert(file, THypHrData.class, hypHrDataMapper, THypHrData.getMapping(), SysType.hyp.getValue(), hyp));
                }
            }
        } catch (MalformedURLException | SmbException e) {
            XxlJobHelper.handleResult(500, "操作共享文件时出现异常" + ExceptionUtils.getStackTrace(e));
        }
    }


}




    
