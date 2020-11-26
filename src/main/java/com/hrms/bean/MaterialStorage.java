package com.hrms.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MaterialStorage {
    private Integer id;
    private String erp;
    private String wbox;
    private Integer num;
    private String productName;
    private String storageArea;
    private String stock;
    private String operator;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;
    private String mo;
    private String purchaseNumber;

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

    public String getWbox() {
        return wbox;
    }

    public void setWbox(String wbox) {
        this.wbox = wbox;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStorageArea() {
        return storageArea;
    }

    public void setStorageArea(String storageArea) {
        this.storageArea = storageArea;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMo() {
        return mo;
    }

    public void setMo(String mo) {
        this.mo = mo;
    }

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    @Override
    public String toString() {
        return "MaterialStorage{" +
                "id=" + id +
                ", erp='" + erp + '\'' +
                ", wbox='" + wbox + '\'' +
                ", num=" + num +
                ", productName='" + productName + '\'' +
                ", storageArea='" + storageArea + '\'' +
                ", stock='" + stock + '\'' +
                ", operator='" + operator + '\'' +
                ", time=" + time +
                ", mo='" + mo + '\'' +
                ", purchaseNumber='" + purchaseNumber + '\'' +
                '}';
    }
}
