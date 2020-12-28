package com.example.mapper;

import org.apache.ibatis.annotations.Param;

public interface DynamicTableMapper {

    int createTable(@Param("tableName") String tableName);
}