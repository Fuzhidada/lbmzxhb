package com.epoch.dentsumcgb.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Table(name = "t_d365_agl_bspl_data")
@Data
public class TD365AglBsplData extends BaseData {
    @Column(name = "CompanyId")
    private String companyId;

    @Column(name = "BPCEntity")
    private String bpcentity;

    @Column(name = "Date")
    private String date;

    @Column(name = "Datasource")
    private String datasource;

    @Column(name = "Datatype")
    private String datatype;

    @Column(name = "DataAttribute")
    private String dataattribute;

    @Column(name = "Nominalcode")
    private String nominalcode;

    @Column(name = "MainAccountName")
    private String mainaccountname;

    @Column(name = "BPCCode")
    private String bpccode;

    @Column(name = "Functionalarea")
    private String functionalarea;

    @Column(name = "Networkbrand")
    private String networkbrand;

    @Column(name = "BPCClient")
    private String bpcclient;

    @Column(name = "Clientname")
    private String clientname;

    @Column(name = "ServiceSegment")
    private String servicesegment;

    @Column(name = "ServiceSegmentName")
    private String servicesegmentname;

    @Column(name = "Channel")
    private String channel;

    @Column(name = "ChannelName")
    private String channelname;

    @Column(name = "BPCServices")
    private String bpcservices;

    @Column(name = "BPCServicesName")
    private String bpcservicesname;

    @Column(name = "VendorCode")
    private String vendorcode;

    @Column(name = "Vendor")
    private String vendor;

    @Column(name = "Analysiscode")
    private String analysiscode;

    @Column(name = "Analysis")
    private String analysis;

    @Column(name = "Currency")
    private String currency;

    @Column(name = "CostCenter")
    private String costcenter;

    @Column(name = "D365ClientCode")
    private String d365clientcode;

    @Column(name = "D365ClientCodeName")
    private String d365clientcodename;

    @Column(name = "Amount")
    private BigDecimal amount;


    public static Map<String, String> getMapping() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Company Id", "companyId");
        map.put("BPC Entity", "bpcentity");
        map.put("Date", "date");
        map.put("Data source", "datasource");
        map.put("Data type", "datatype");
        map.put("Data Attribute", "dataattribute");
        map.put("Nominal code", "nominalcode");
        map.put("Main Account Name", "mainaccountname");
        map.put("BPC Code", "bpccode");
        map.put("Functional area", "functionalarea");
        map.put("Network brand", "networkbrand");
        map.put("BPC Client", "bpcclient");
        map.put("Client name", "clientname");
        map.put("Service segment", "servicesegment");
        map.put("Service Segment Name", "servicesegmentname");
        map.put("Channel", "channel");
        map.put("Channel Name", "channelname");
        map.put("BPC Services", "bpcservices");
        map.put("BPC Services Name", "bpcservicesname");
        map.put("Vendor Code", "vendorcode");
        map.put("Vendor", "vendor");
        map.put("Analysis code", "analysiscode");
        map.put("Analysis", "analysis");
        map.put("Currency", "currency");

        //暂时没有 后续添加
     /*   map.put("", "costcenter");
        map.put("", "d365clientcode");
        map.put("", "d365clientcodename");*/

        map.put("Amount", "amount");


        return map;
    }
}