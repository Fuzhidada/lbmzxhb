package com.epoch.dentsumcgb.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class D365Task {
    @XxlJob(value = "D365Task")
    public void dd() throws Exception {
        XxlJobHelper.handleResult(200,"xxxxxx");
        System.out.println("任务--->");
    }

    
}
