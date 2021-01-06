package com.example.d.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TaskTest {
    @XxlJob(value = "taskTest")
    public void dd() throws Exception {
        XxlJobHelper.handleResult(500,"xxxxxx");
        System.out.println("任务--->");
    }

    
}
