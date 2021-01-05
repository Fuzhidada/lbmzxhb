package com.example.util;

import org.apache.ibatis.annotations.DeleteProvider;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.provider.base.BaseDeleteProvider;


public interface BizMapper<T> extends Mapper<T> {

    @Override
    @DeleteProvider(type = BaseDeleteProvider.class, method = "dynamicSQL")
    int delete(T var1);

}
