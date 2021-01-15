package com.epoch.dentsumcgb.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "t_hyp_hr_data")
public class THypHrData {
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

    /**
     * @return CostCenter
     */
    public String getCostcenter() {
        return costcenter;
    }

    /**
     * @param costcenter
     */
    public void setCostcenter(String costcenter) {
        this.costcenter = costcenter;
    }

    /**
     * @return CostServiceSet
     */
    public String getCostserviceset() {
        return costserviceset;
    }

    /**
     * @param costserviceset
     */
    public void setCostserviceset(String costserviceset) {
        this.costserviceset = costserviceset;
    }

    /**
     * @return LOBBrand
     */
    public String getLobbrand() {
        return lobbrand;
    }

    /**
     * @param lobbrand
     */
    public void setLobbrand(String lobbrand) {
        this.lobbrand = lobbrand;
    }

    /**
     * @return HRAccount
     */
    public String getHraccount() {
        return hraccount;
    }

    /**
     * @param hraccount
     */
    public void setHraccount(String hraccount) {
        this.hraccount = hraccount;
    }

    /**
     * @return Year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return Entity
     */
    public String getEntity() {
        return entity;
    }

    /**
     * @param entity
     */
    public void setEntity(String entity) {
        this.entity = entity;
    }

    /**
     * @return Period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @return Amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}