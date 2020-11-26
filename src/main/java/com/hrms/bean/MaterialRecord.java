package com.hrms.bean;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MaterialRecord {
    private Integer id;
    private String erp;
    private String record;
    private Integer amount;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;

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

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MaterialInOut{" +
                "id=" + id +
                ", erp='" + erp + '\'' +
                ", record='" + record + '\'' +
                ", amount=" + amount +
                ", time=" + time +
                '}';
    }
}
