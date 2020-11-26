package com.hrms.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class WarehouseRecord {
    private Integer id;
    private String erp;
    private String snRecord;
    private Integer snAmount;
    private String wboxRecord;
    private Integer wboxAmount;
    private String stock;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;
    private String operator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getErp() {
        return erp;
    }

    public void setErp(String erp) {
        this.erp = erp;
    }

    public String getSnRecord() {
        return snRecord;
    }

    public void setSnRecord(String snRecord) {
        this.snRecord = snRecord;
    }

    public Integer getSnAmount() {
        return snAmount;
    }

    public void setSnAmount(Integer snAmount) {
        this.snAmount = snAmount;
    }

    public String getWboxRecord() {
        return wboxRecord;
    }

    public void setWboxRecord(String wboxRecord) {
        this.wboxRecord = wboxRecord;
    }

    public Integer getWboxAmount() {
        return wboxAmount;
    }

    public void setWboxAmount(Integer wboxAmount) {
        this.wboxAmount = wboxAmount;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "WarehouseRecord{" +
                "id=" + id +
                ", erp='" + erp + '\'' +
                ", snRecord='" + snRecord + '\'' +
                ", snAmount=" + snAmount +
                ", wboxRecord='" + wboxRecord + '\'' +
                ", wboxAmount=" + wboxAmount +
                ", stock='" + stock + '\'' +
                ", time=" + time +
                ", operator='" + operator + '\'' +
                '}';
    }
}
