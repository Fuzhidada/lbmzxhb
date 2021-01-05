package com.example.util;

import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;

public class ID implements GenId {

    @Override
    public Object genId(String s, String s1) {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
