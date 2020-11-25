package com.example.entity;

import com.example.util.ID;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;

@Data
@Table(name = "fuzhi")
public class Fuzhi {
    @Id
    @KeySql(genId = ID.class)
    private String id;

    private String a;

    private String b;

    private String c;

    private String d;

    private String e;

    private String f;

    private String g;

    private String h;

}