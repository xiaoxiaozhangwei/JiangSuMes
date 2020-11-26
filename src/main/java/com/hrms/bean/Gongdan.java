package com.hrms.bean;

public class Gongdan {
    private String moId;
    private String model;
    private int moNum;
    private int moRemain;
    private int moRemain0;
    private int moRemain1;

    private int moRemainlabel;

    private String product_name;//品名
    private String pn;//料号
    private String spec;//产品规格
    private  String gkMo;
    private  String operator;
    private  String fw;

    public String getFw() {
        return fw;
    }

    public void setFw(String fw) {
        this.fw = fw;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getGkMo() {
        return gkMo;
    }

    public void setGkMo(String gkMo) {
        this.gkMo = gkMo;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
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

    public int getMoNum() {
        return moNum;
    }

    public void setMoNum(int moNum) {
        this.moNum = moNum;
    }

    public int getMoRemain() {
        return moRemain;
    }

    public void setMoRemain(int moRemain) {
        this.moRemain = moRemain;
    }

    public int getMoRemain0() {
        return moRemain0;
    }

    public void setMoRemain0(int moRemain0) {
        this.moRemain0 = moRemain0;
    }

    public int getMoRemain1() {
        return moRemain1;
    }

    public void setMoRemain1(int moRemain1) {
        this.moRemain1 = moRemain1;
    }

    public int getMoRemainlabel() {
        return moRemainlabel;
    }

    public void setMoRemainlabel(int moRemainlabel) {
        this.moRemainlabel = moRemainlabel;
    }

    public Gongdan() {
    }

    public Gongdan(String moId, String model, int moNum, int moRemain, int moRemain0, int moRemain1, int moRemainlabel, String product_name, String pn, String spec, String gkMo, String operator, String fw) {
        this.moId = moId;
        this.model = model;
        this.moNum = moNum;
        this.moRemain = moRemain;
        this.moRemain0 = moRemain0;
        this.moRemain1 = moRemain1;
        this.moRemainlabel = moRemainlabel;
        this.product_name = product_name;
        this.pn = pn;
        this.spec = spec;
        this.gkMo = gkMo;
        this.operator = operator;
        this.fw = fw;
    }

    @Override
    public String toString() {
        return "Gongdan{" +
                "moId='" + moId + '\'' +
                ", model='" + model + '\'' +
                ", moNum=" + moNum +
                ", moRemain=" + moRemain +
                ", moRemain0=" + moRemain0 +
                ", moRemain1=" + moRemain1 +
                ", moRemainlabel=" + moRemainlabel +
                ", product_name='" + product_name + '\'' +
                ", pn='" + pn + '\'' +
                ", spec='" + spec + '\'' +
                ", gkMo='" + gkMo + '\'' +
                ", operator='" + operator + '\'' +
                ", fw='" + fw + '\'' +
                '}';
    }
}
