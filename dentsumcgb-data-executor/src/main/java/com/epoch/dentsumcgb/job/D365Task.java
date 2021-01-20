package com.epoch.dentsumcgb.job;

import com.epoch.dentsumcgb.biz.ReadAndInsertBiz;
import com.epoch.dentsumcgb.config.smb.Smb;
import com.epoch.dentsumcgb.constant.SysType;
import com.epoch.dentsumcgb.entity.TD365ActCpsData;
import com.epoch.dentsumcgb.entity.TD365AglBsplData;
import com.epoch.dentsumcgb.entity.THypFinData;
import com.epoch.dentsumcgb.entity.THypHrData;
import com.epoch.dentsumcgb.mapper.TD365ActCpsDataMapper;
import com.epoch.dentsumcgb.mapper.TD365AglBsplDataMapper;
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
public class D365Task {
    @Resource
    private ReadAndInsertBiz biz;

    @Resource
    @Qualifier("d365")
    Smb d365;

    @Resource
    private TD365AglBsplDataMapper bsplDataMapper;

    @Resource
    private TD365ActCpsDataMapper actCpsDataMapper;

    @XxlJob(value = "D365Task")
    public void start() {
        dealData();
    }

    public void dealData() {
        SmbFile[] files;
        try {
            files = d365.getSmbFile().listFiles();

            for (SmbFile file : files) {
                if (!file.isFile()) {
                    continue;
                }
                String name = file.getName();
                if (name.contains("AllEntity_AGL BSPL")) {
                    ThreadUtil.getExecutor().submit(() ->
                            biz.readAndInsert(file, TD365AglBsplData.class, bsplDataMapper, TD365AglBsplData.getMapping(), SysType.d365.getValue(), d365));
                }
                if (name.contains("AllEntity_ACT_CPS")) {
                    ThreadUtil.getExecutor().submit(() ->
                            biz.readAndInsert(file, TD365ActCpsData.class, actCpsDataMapper, TD365ActCpsData.getMapping(), SysType.d365.getValue(), d365));
                }
            }
        } catch (MalformedURLException | SmbException e) {
            XxlJobHelper.handleResult(500, "操作共享文件时出现异常" + ExceptionUtils.getStackTrace(e));
        }
    }


}
