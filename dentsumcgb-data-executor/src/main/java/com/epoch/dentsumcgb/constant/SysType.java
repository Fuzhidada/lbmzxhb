package com.epoch.dentsumcgb.constant;

public enum SysType {
//    {D365,PAYROLL,HYPERION,BPC}

    d365("D365"),
    payroll("PAYROLL"),
    hyp("HYPERION"),
    bpc("BPC");

    String sysName;

    SysType(String sysName) {
        this.sysName = sysName;
    }
    public String getValue()
    {
        return sysName;
    }

}
