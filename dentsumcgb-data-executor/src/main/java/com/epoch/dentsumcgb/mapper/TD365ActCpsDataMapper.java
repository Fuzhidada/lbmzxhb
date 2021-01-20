package com.epoch.dentsumcgb.mapper;

import com.epoch.dentsumcgb.config.BizMapper;
import com.epoch.dentsumcgb.entity.TD365ActCpsData;

import java.util.List;

public interface TD365ActCpsDataMapper extends BizMapper<TD365ActCpsData> {
    int batchInsert(List<TD365ActCpsData> list) ;
}