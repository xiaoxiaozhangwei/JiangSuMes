package com.hrms.bean;

public class CangkuRecord {
    private  String  oldNbox;
    private  String  newNbox;
    private  String  operator;
    private  String  oldWbox;
    private  String  newWbox;

    public String getOldNbox() {
        return oldNbox;
    }

    public void setOldNbox(String oldNbox) {
        this.oldNbox = oldNbox;
    }

    public String getNewNbox() {
        return newNbox;
    }

    public void setNewNbox(String newNbox) {
        this.newNbox = newNbox;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOldWbox() {
        return oldWbox;
    }

    public void setOldWbox(String oldWbox) {
        this.oldWbox = oldWbox;
    }

    public String getNewWbox() {
        return newWbox;
    }

    public void setNewWbox(String newWbox) {
        this.newWbox = newWbox;
    }

    public CangkuRecord() {
    }

    public CangkuRecord(String oldNbox, String newNbox, String operator, String oldWbox, String newWbox) {
        this.oldNbox = oldNbox;
        this.newNbox = newNbox;
        this.operator = operator;
        this.oldWbox = oldWbox;
        this.newWbox = newWbox;
    }

    @Override
    public String toString() {
        return "CangkuRecord{" +
                "oldNbox='" + oldNbox + '\'' +
                ", newNbox='" + newNbox + '\'' +
                ", operator='" + operator + '\'' +
                ", oldWbox='" + oldWbox + '\'' +
                ", newWbox='" + newWbox + '\'' +
                '}';
    }
}
