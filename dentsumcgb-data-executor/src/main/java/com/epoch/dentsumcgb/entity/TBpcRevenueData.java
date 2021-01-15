package com.epoch.dentsumcgb.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "t_bpc_revenue_data")
public class TBpcRevenueData {
    @Column(name = "ENTITY")
    private String entity;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "ACCOUNT")
    private String account;

    @Column(name = "SEGMENT")
    private String segment;

    @Column(name = "TIME")
    private String time;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CATEGORY")
    private String category;

    /**
     * @return ENTITY
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
     * @return BRAND
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return ACCOUNT
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return SEGMENT
     */
    public String getSegment() {
        return segment;
    }

    /**
     * @param segment
     */
    public void setSegment(String segment) {
        this.segment = segment;
    }

    /**
     * @return TIME
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return AMOUNT
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

    /**
     * @return CATEGORY
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }
}