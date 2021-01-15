package com.epoch.dentsumcgb.config;

import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface BizMapper<T> extends BaseMapper<T>  {
    int batchInsert(List<T> list) ;
}
