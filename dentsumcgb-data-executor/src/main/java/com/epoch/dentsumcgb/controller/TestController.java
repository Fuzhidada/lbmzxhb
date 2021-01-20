package com.epoch.dentsumcgb.controller;

import com.epoch.dentsumcgb.job.D365Task;
import com.epoch.dentsumcgb.job.HYPTask;
import com.epoch.dentsumcgb.config.smb.SmbUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController

public class TestController {
    @Resource
    SmbUtil smbUtil;

    @Resource
    HYPTask hypTask;
    @Resource
    D365Task d365Task;

    @GetMapping("/get")
    public void test() {
        System.out.println(smbUtil.start());

    }

    @GetMapping("/get11")
    public void test2() {
        hypTask.dealData();
    }
    @GetMapping("/get22")
    public void test3() {
        d365Task.dealData();
    }
}
