package com.hrms.bean;

public class RePack {
    private Integer pack_id;
    private String productionSN;
    private String model;
    private String operation;
    private String time;
    private String moId;
    private String operator;
    private String nboxId;
    private String wboxId;

    public Integer getPack_id() {
        return pack_id;
    }

    public void setPack_id(Integer pack_id) {
        this.pack_id = pack_id;
    }

    public String getProductionSN() {
        return productionSN;
    }

    public void setProductionSN(String productionSN) {
        this.productionSN = productionSN;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoId() {
        return moId;
    }

    public void setMoId(String moId) {
        this.moId = moId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getNboxId() {
        return nboxId;
    }

    public void setNboxId(String nboxId) {
        this.nboxId = nboxId;
    }

    public String getWboxId() {
        return wboxId;
    }

    public void setWboxId(String wboxId) {
        this.wboxId = wboxId;
    }

    public RePack() {
    }

    public RePack(Integer pack_id, String productionSN, String model, String operation, String time, String moId, String operator, String nboxId, String wboxId) {
        this.pack_id = pack_id;
        this.productionSN = productionSN;
        this.model = model;
        this.operation = operation;
        this.time = time;
        this.moId = moId;
        this.operator = operator;
        this.nboxId = nboxId;
        this.wboxId = wboxId;
    }

    @Override
    public String toString() {
        return "RePack{" +
                "pack_id=" + pack_id +
                ", productionSN='" + productionSN + '\'' +
                ", model='" + model + '\'' +
                ", operation='" + operation + '\'' +
                ", time='" + time + '\'' +
                ", moId='" + moId + '\'' +
                ", operator='" + operator + '\'' +
                ", nboxId='" + nboxId + '\'' +
                ", wboxId='" + wboxId + '\'' +
                '}';
    }
}
