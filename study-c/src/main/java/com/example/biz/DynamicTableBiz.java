package com.example.biz;

import com.example.mapper.DynamicTableMapper;
import com.example.common.util.CommonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DynamicTableBiz {

    @Resource
    private DynamicTableMapper mapper;

    public CommonResult createTable(String name) {
        return CommonResult.success(mapper.createTable(name));
    }

}