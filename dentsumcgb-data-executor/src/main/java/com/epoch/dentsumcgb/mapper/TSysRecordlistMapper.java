package com.epoch.dentsumcgb.mapper;

import com.epoch.dentsumcgb.config.BizMapper;
import com.epoch.dentsumcgb.entity.TSysRecordlist;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TSysRecordlistMapper extends BizMapper<TSysRecordlist> {
    List<TSysRecordlist> selectByDate(@Param("datetime") Date datetime);
}