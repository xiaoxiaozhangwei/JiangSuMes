package com.hrms.bean;

public class SNlabel {
    private String moId;
    private String productSN;
    private String WWN;
    private String model;
    private String operator;
    private  String fw;

    private String snrulename;
    private Integer snlength;
    private String erp;
    private Integer start;
    private Integer over;
    private String rule;
    private String mesg;

    private String wwnrulename;
    private Integer wwnlength;
    private Integer wwnstart;
    private Integer wwnover;
    private String wwnrule;
    private String wwnmesg;


    public String getFw() {
        return fw;
    }

    public void setFw(String fw) {
        this.fw = fw;
    }

    public String getMoId() {
        return moId;
    }

    public void setMoId(String moId) {
        this.moId = moId;
    }

    public String getProductSN() {
        return productSN;
    }

    public void setProductSN(String productSN) {
        this.productSN = productSN;
    }

    public String getWWN() {
        return WWN;
    }

    public void setWWN(String WWN) {
        this.WWN = WWN;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSnrulename() {
        return snrulename;
    }

    public void setSnrulename(String snrulename) {
        this.snrulename = snrulename;
    }

    public Integer getSnlength() {
        return snlength;
    }

    public void setSnlength(Integer snlength) {
        this.snlength = snlength;
    }

    public String getErp() {
        return erp;
    }

    public void setErp(String erp) {
        this.erp = erp;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getOver() {
        return over;
    }

    public void setOver(Integer over) {
        this.over = over;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public String getWwnrulename() {
        return wwnrulename;
    }

    public void setWwnrulename(String wwnrulename) {
        this.wwnrulename = wwnrulename;
    }

    public Integer getWwnlength() {
        return wwnlength;
    }

    public void setWwnlength(Integer wwnlength) {
        this.wwnlength = wwnlength;
    }

    public Integer getWwnstart() {
        return wwnstart;
    }

    public void setWwnstart(Integer wwnstart) {
        this.wwnstart = wwnstart;
    }

    public Integer getWwnover() {
        return wwnover;
    }

    public void setWwnover(Integer wwnover) {
        this.wwnover = wwnover;
    }

    public String getWwnrule() {
        return wwnrule;
    }

    public void setWwnrule(String wwnrule) {
        this.wwnrule = wwnrule;
    }

    public String getWwnmesg() {
        return wwnmesg;
    }

    public void setWwnmesg(String wwnmesg) {
        this.wwnmesg = wwnmesg;
    }

    public SNlabel() {
    }

    public SNlabel(String moId, String productSN, String WWN, String model, String operator, String snrulename, Integer snlength, String erp, Integer start, Integer over, String rule, String mesg, String wwnrulename, Integer wwnlength, Integer wwnstart, Integer wwnover, String wwnrule, String wwnmesg) {
        this.moId = moId;
        this.productSN = productSN;
        this.WWN = WWN;
        this.model = model;
        this.operator = operator;
        this.snrulename = snrulename;
        this.snlength = snlength;
        this.erp = erp;
        this.start = start;
        this.over = over;
        this.rule = rule;
        this.mesg = mesg;
        this.wwnrulename = wwnrulename;
        this.wwnlength = wwnlength;
        this.wwnstart = wwnstart;
        this.wwnover = wwnover;
        this.wwnrule = wwnrule;
        this.wwnmesg = wwnmesg;
    }

    @Override
    public String toString() {
        return "SNlabel{" +
                "moId='" + moId + '\'' +
                ", productSN='" + productSN + '\'' +
                ", WWN='" + WWN + '\'' +
                ", model='" + model + '\'' +
                ", operator='" + operator + '\'' +
                ", snrulename='" + snrulename + '\'' +
                ", snlength=" + snlength +
                ", erp='" + erp + '\'' +
                ", start=" + start +
                ", over=" + over +
                ", rule='" + rule + '\'' +
                ", mesg='" + mesg + '\'' +
                ", wwnrulename='" + wwnrulename + '\'' +
                ", wwnlength=" + wwnlength +
                ", wwnstart=" + wwnstart +
                ", wwnover=" + wwnover +
                ", wwnrule='" + wwnrule + '\'' +
                ", wwnmesg='" + wwnmesg + '\'' +
                '}';
    }
}
