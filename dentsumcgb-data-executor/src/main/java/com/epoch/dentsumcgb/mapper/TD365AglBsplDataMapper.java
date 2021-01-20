package com.epoch.dentsumcgb.mapper;

import com.epoch.dentsumcgb.config.BizMapper;
import com.epoch.dentsumcgb.entity.TD365AglBsplData;

import java.util.List;

public interface TD365AglBsplDataMapper extends BizMapper<TD365AglBsplData> {
    int batchInsert(List<TD365AglBsplData> list) ;
}