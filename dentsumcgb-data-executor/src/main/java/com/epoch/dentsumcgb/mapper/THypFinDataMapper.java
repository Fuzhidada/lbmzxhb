package com.epoch.dentsumcgb.mapper;

import com.epoch.dentsumcgb.config.BizMapper;
import com.epoch.dentsumcgb.entity.THypFinData;


import java.util.List;

public interface THypFinDataMapper extends BizMapper<THypFinData> {
    int batchInsert(List<THypFinData> list) ;

}