package com.hrms.bean;

public class Baozhuang {
    private Integer pack_id;
    private String productionSN;
    private String model;
    private String operation;
    private String time;
    private String moId;
    private Integer monum;
    private Integer moremain;
    private String operator;
   // private String NBoxId;
   private String nboxId;
    private String wboxId;
    private String mesg;
    private  String line;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Baozhuang(Integer pack_id, String productionSN, String model, String operation, String time, String moId, Integer monum, Integer moremain, String operator, String nboxId, String wboxId, String mesg, String line) {
        this.pack_id = pack_id;
        this.productionSN = productionSN;
        this.model = model;
        this.operation = operation;
        this.time = time;
        this.moId = moId;
        this.monum = monum;
        this.moremain = moremain;
        this.operator = operator;
        this.nboxId = nboxId;
        this.wboxId = wboxId;
        this.mesg = mesg;
        this.line = line;
    }

    public String getNboxId() {
        return nboxId;
    }

    public void setNboxId(String nboxId) {
        this.nboxId = nboxId;
    }

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

    public Integer getMonum() {
        return monum;
    }

    public void setMonum(Integer monum) {
        this.monum = monum;
    }

    public Integer getMoremain() {
        return moremain;
    }

    public void setMoremain(Integer moremain) {
        this.moremain = moremain;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }


    public String getWboxId() {
        return wboxId;
    }

    public void setWboxId(String wboxId) {
        this.wboxId = wboxId;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public Baozhuang() {
    }

    public Baozhuang(Integer pack_id, String productionSN, String model, String operation, String time, String moId, Integer monum, Integer moremain, String operator, String nboxId, String wboxId, String mesg) {
        this.pack_id = pack_id;
        this.productionSN = productionSN;
        this.model = model;
        this.operation = operation;
        this.time = time;
        this.moId = moId;
        this.monum = monum;
        this.moremain = moremain;
        this.operator = operator;
        this.nboxId = nboxId;
        this.wboxId = wboxId;
        this.mesg = mesg;
    }

    @Override
    public String toString() {
        return "Baozhuang{" +
                "pack_id=" + pack_id +
                ", productionSN='" + productionSN + '\'' +
                ", model='" + model + '\'' +
                ", operation='" + operation + '\'' +
                ", time='" + time + '\'' +
                ", moId='" + moId + '\'' +
                ", monum=" + monum +
                ", moremain=" + moremain +
                ", operator='" + operator + '\'' +
                ", nboxId='" + nboxId + '\'' +
                ", wboxId='" + wboxId + '\'' +
                ", mesg='" + mesg + '\'' +
                ", line='" + line + '\'' +
                '}';
    }
}
