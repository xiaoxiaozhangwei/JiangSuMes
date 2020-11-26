package com.hrms.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MaterialShipment {
    private Integer id;
    private  String erp;
    private String wbox;
    private  Integer num;
    private String storageArea;
    private String stock;
    private  String operator;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;
    private  String mo;
    private  String  purchaseNumber;
    private String applicant;
    private String client;
    private String productNature;
    private String information;
    private String approval;
    private String comments;

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

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getProductNature() {
        return productNature;
    }

    public void setProductNature(String productNature) {
        this.productNature = productNature;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "MaterialShipment{" +
                "id=" + id +
                ", erp='" + erp + '\'' +
                ", wbox='" + wbox + '\'' +
                ", num=" + num +
                ", storageArea='" + storageArea + '\'' +
                ", stock='" + stock + '\'' +
                ", operator='" + operator + '\'' +
                ", time=" + time +
                ", mo='" + mo + '\'' +
                ", purchaseNumber='" + purchaseNumber + '\'' +
                ", applicant='" + applicant + '\'' +
                ", client='" + client + '\'' +
                ", productNature='" + productNature + '\'' +
                ", information='" + information + '\'' +
                ", approval='" + approval + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }

    public MaterialShipment(Integer id, String erp, String wbox, Integer num, String storageArea, String stock, String operator, Date time, String mo, String purchaseNumber, String applicant, String client, String productNature, String information, String approval, String comments) {
        this.id = id;
        this.erp = erp;
        this.wbox = wbox;
        this.num = num;
        this.storageArea = storageArea;
        this.stock = stock;
        this.operator = operator;
        this.time = time;
        this.mo = mo;
        this.purchaseNumber = purchaseNumber;
        this.applicant = applicant;
        this.client = client;
        this.productNature = productNature;
        this.information = information;
        this.approval = approval;
        this.comments = comments;
    }

    public MaterialShipment() {
    }
}
