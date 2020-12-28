package com.example.rest;

import com.example.biz.DynamicTableBiz;
import com.example.biz.FuzhiBiz;
import com.example.config.Moom;
import com.example.util.CommonResult;
import com.example.vo.DefaultQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class DynamicTableController {
    @Resource
    private DynamicTableBiz biz;

    @GetMapping("/create")
    public CommonResult testB(String name) {
        return biz.createTable(name);
    }


}
