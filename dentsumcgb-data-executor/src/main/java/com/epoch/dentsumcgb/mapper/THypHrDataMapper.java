package com.epoch.dentsumcgb.mapper;

import com.epoch.dentsumcgb.config.BizMapper;
import com.epoch.dentsumcgb.entity.THypFinData;
import com.epoch.dentsumcgb.entity.THypHrData;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface THypHrDataMapper extends BizMapper<THypHrData> {
    int batchInsert(List<THypHrData> list) ;
}