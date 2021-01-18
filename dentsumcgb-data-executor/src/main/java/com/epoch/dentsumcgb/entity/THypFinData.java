package com.epoch.dentsumcgb.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Table(name = "t_hyp_fin_data")
@Data
public class THypFinData extends BaseData {
    @Column(name = "CostCenter")
    private String costcenter;

    @Column(name = "CostServiceSet")
    private String costserviceset;

    @Column(name = "Client")
    private String client;

    @Column(name = "Product")
    private String product;

    @Column(name = "MasterServiceSet")
    private String masterserviceset;

    @Column(name = "LOB")
    private String lob;

    @Column(name = "Account")
    private String account;

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
        map.put("LOBBrand", "lob");
        map.put("Account", "account");
        map.put("Year", "year");
        map.put("Entity", "entity");
        map.put("Period", "period");
        map.put("Amount", "amount");

        return map;
    }

}