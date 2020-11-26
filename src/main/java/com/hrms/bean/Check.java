package com.hrms.bean;

public class Check {
    private Integer checkId;
    private String productSN1;
    private String productSN2;
    private String model;
    private String moId;
    private String operation;
    private String operator;
    private String time;
    private String FW;
    private String xianbie;

    public String getXianbie() {
        return xianbie;
    }

    public void setXianbie(String xianbie) {
        this.xianbie = xianbie;
    }

    public String getFW() {
        return FW;
    }

    public void setFW(String FW) {
        this.FW = FW;
    }

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public String getProductSN1() {
        return productSN1;
    }

    public void setProductSN1(String productSN) {
        this.productSN1 = productSN;
    }
    public String getProductSN2() {
        return productSN2;
    }

    public void setProductSN2(String productSN) {
        this.productSN2 = productSN;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMoId() {
        return moId;
    }

    public void setMoId(String moId) {
        this.moId = moId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Check(Integer checkId, String productSN1, String productSN2, String model, String moId, String operation, String operator, String time, String FW, String xianbie) {
        this.checkId = checkId;
        this.productSN1 = productSN1;
        this.productSN2 = productSN2;
        this.model = model;
        this.moId = moId;
        this.operation = operation;
        this.operator = operator;
        this.time = time;
        this.FW = FW;
        this.xianbie = xianbie;
    }

    public Check() {
    }

    @Override
    public String toString() {
        return "Check{" +
                "checkId=" + checkId +
                ", productSN1='" + productSN1 + '\'' +
                ", productSN2='" + productSN2 + '\'' +
                ", model='" + model + '\'' +
                ", moId='" + moId + '\'' +
                ", operation='" + operation + '\'' +
                ", operator='" + operator + '\'' +
                ", time='" + time + '\'' +
                ", FW='" + FW + '\'' +
                ", xianbie='" + xianbie + '\'' +
                '}';
    }
}
