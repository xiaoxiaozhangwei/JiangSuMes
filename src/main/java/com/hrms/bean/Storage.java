package com.hrms.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class Storage {

    private  Integer id;
    private String sn;
    private  String mo;
    private String model;
    private  String nbox;
    private String wbox;
    private  String operator;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;
    private String stock;
    private String store_area;
    private Integer num;
    private String erp;
    private  String storageMo;
    private Gongdan gongdan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMo() {
        return mo;
    }

    public void setMo(String mo) {
        this.mo = mo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNbox() {
        return nbox;
    }

    public void setNbox(String nbox) {
        this.nbox = nbox;
    }

    public String getWbox() {
        return wbox;
    }

    public void setWbox(String wbox) {
        this.wbox = wbox;
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStore_area() {
        return store_area;
    }

    public void setStore_area(String store_area) {
        this.store_area = store_area;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getErp() {
        return erp;
    }

    public void setErp(String erp) {
        this.erp = erp;
    }

    public String getStorageMo() {
        return storageMo;
    }

    public void setStorageMo(String storageMo) {
        this.storageMo = storageMo;
    }

    public Gongdan getGongdan() {
        return gongdan;
    }

    public void setGongdan(Gongdan gongdan) {
        this.gongdan = gongdan;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", sn='" + sn + '\'' +
                ", mo='" + mo + '\'' +
                ", model='" + model + '\'' +
                ", nbox='" + nbox + '\'' +
                ", wbox='" + wbox + '\'' +
                ", operator='" + operator + '\'' +
                ", time=" + time +
                ", stock='" + stock + '\'' +
                ", store_area='" + store_area + '\'' +
                ", num=" + num +
                ", erp='" + erp + '\'' +
                ", storageMo='" + storageMo + '\'' +
                ", gongdan=" + gongdan +
                '}';
    }
}
