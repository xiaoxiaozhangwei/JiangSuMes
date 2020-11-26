package com.hrms.bean;

public class Environment {
    private Integer environ_id;
    private String date;
    private Double RDT;
    private Double OC3;
    private Double BIT;
    private Double link;
    private Double store;
    private Double IQC;
    private Double extract;
    private Double changeshoe;
    private Double walk;
    private Double office;
    private Double RMA;
    private Double rest;
    private Double tea;
    private String cause;
    private Integer picture_id;
    private String date2;
    private String area;
    private String picture;
    private String question;

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public Integer getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(Integer picture_id) {
        this.picture_id = picture_id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getEnviron_id() {
        return environ_id;
    }

    public void setEnviron_id(Integer environ_id) {
        this.environ_id = environ_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getRDT() {
        return RDT;
    }

    public void setRDT(Double RDT) {
        this.RDT = RDT;
    }

    public Double getOC3() {
        return OC3;
    }

    public void setOC3(Double OC3) {
        this.OC3 = OC3;
    }

    public Double getBIT() {
        return BIT;
    }

    public void setBIT(Double BIT) {
        this.BIT = BIT;
    }

    public Double getLink() {
        return link;
    }

    public void setLink(Double link) {
        this.link = link;
    }

    public Double getStore() {
        return store;
    }

    public void setStore(Double store) {
        this.store = store;
    }

    public Double getIQC() {
        return IQC;
    }

    public void setIQC(Double IQC) {
        this.IQC = IQC;
    }

    public Double getExtract() {
        return extract;
    }

    public void setExtract(Double extract) {
        this.extract = extract;
    }

    public Double getChangeshoe() {
        return changeshoe;
    }

    public void setChangeshoe(Double changeshoe) {
        this.changeshoe = changeshoe;
    }

    public Double getWalk() {
        return walk;
    }

    public void setWalk(Double walk) {
        this.walk = walk;
    }

    public Double getOffice() {
        return office;
    }

    public void setOffice(Double office) {
        this.office = office;
    }

    public Double getRMA() {
        return RMA;
    }

    public void setRMA(Double RMA) {
        this.RMA = RMA;
    }

    public Double getRest() {
        return rest;
    }

    public void setRest(Double rest) {
        this.rest = rest;
    }

    public Double getTea() {
        return tea;
    }

    public void setTea(Double tea) {
        this.tea = tea;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Environment() {
    }

    public Environment(Integer environ_id, String date, Double RDT, Double OC3, Double BIT, Double link, Double store, Double IQC, Double extract, Double changeshoe, Double walk, Double office, Double RMA, Double rest, Double tea, String cause, Integer picture_id, String date2, String area, String picture, String question) {
        this.environ_id = environ_id;
        this.date = date;
        this.RDT = RDT;
        this.OC3 = OC3;
        this.BIT = BIT;
        this.link = link;
        this.store = store;
        this.IQC = IQC;
        this.extract = extract;
        this.changeshoe = changeshoe;
        this.walk = walk;
        this.office = office;
        this.RMA = RMA;
        this.rest = rest;
        this.tea = tea;
        this.cause = cause;
        this.picture_id = picture_id;
        this.date2 = date2;
        this.area = area;
        this.picture = picture;
        this.question = question;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "environ_id=" + environ_id +
                ", date='" + date + '\'' +
                ", RDT=" + RDT +
                ", OC3=" + OC3 +
                ", BIT=" + BIT +
                ", link=" + link +
                ", store=" + store +
                ", IQC=" + IQC +
                ", extract=" + extract +
                ", changeshoe=" + changeshoe +
                ", walk=" + walk +
                ", office=" + office +
                ", RMA=" + RMA +
                ", rest=" + rest +
                ", tea=" + tea +
                ", cause='" + cause + '\'' +
                ", picture_id=" + picture_id +
                ", date2='" + date2 + '\'' +
                ", area='" + area + '\'' +
                ", picture='" + picture + '\'' +
                ", question='" + question + '\'' +
                '}';
    }
}
