package com.example.d.job;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class TaskTest {
    @XxlJob(value = "taskTest")
    public void dd() throws Exception {
        System.out.println("任务--->");
    }
}
