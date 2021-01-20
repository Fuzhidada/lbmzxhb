package com.epoch.dentsumcgb.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sys_recordlist")
@Data
public class TSysRecordlist {
    public TSysRecordlist(){}

    public TSysRecordlist(String sysname, String filename, Integer rownum) {
        this.sysname = sysname;
        this.filename = filename;
        this.rownum = rownum;
        this.datatime = new Date();
    }

    public TSysRecordlist(String sysname, String filename, String msg, Integer rownum) {
        this.sysname = sysname;
        this.filename = filename;
        this.rownum = rownum;
        this.msg = msg;
        this.datatime = new Date();
    }


    @Id
    private Integer id;

    /**
     * D365/PAYROLL/HYPERION/BPC
     */
    private String sysname;

    private String filename;

    /**
     * 数据行数
     */
    private Integer rownum;

    private Date datatime;

    private String msg;


}