package com.epoch.dentsumcgb.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Table(name = "t_hyp_hr_data")
@Data
public class THypHrData extends BaseData{
    @Column(name = "CostCenter")
    private String costcenter;

    @Column(name = "CostServiceSet")
    private String costserviceset;

    @Column(name = "LOBBrand")
    private String lobbrand;

    @Column(name = "HRAccount")
    private String hraccount;

    @Column(name = "Year")
    private String year;

    @Column(name = "Entity")
    private String entity;

    @Column(name = "Period")
    private String period;

    @Column(name = "Amount")
    private BigDecimal amount;

    public static Map<String, String> getMapping() {
        HashMap<String, String> map = new HashMap<>();
        map.put("CostCenter", "costcenter");
        map.put("CostServiceSet", "costserviceset");
        map.put("Client", "client");
        map.put("Product", "product");
        map.put("MasterServiceSet", "masterserviceset");
        map.put("LOBBrand", "lobbrand");
        map.put("Account", "account");
        map.put("Year", "year");
        map.put("Entity", "entity");
        map.put("Period", "period");
        map.put("Amount", "amount");

        return map;
    }

}