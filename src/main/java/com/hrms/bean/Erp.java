package com.hrms.bean;

public class Erp {
    private  String  erp;
    private  String  model;
    private  String  descirbe;
    private  String  fw;
    private  String  confirm;


    public Erp() {
    }

    public Erp(String erp, String model, String descirbe, String fw, String confirm) {
        this.erp = erp;
        this.model = model;
        this.descirbe = descirbe;
        this.fw = fw;
        this.confirm = confirm;
    }

    @Override
    public String toString() {
        return "Erp{" +
                "erp='" + erp + '\'' +
                ", model='" + model + '\'' +
                ", descirbe='" + descirbe + '\'' +
                ", fw='" + fw + '\'' +
                ", confirm='" + confirm + '\'' +
                '}';
    }

    public String getErp() {
        return erp;
    }

    public void setErp(String erp) {
        this.erp = erp;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescirbe() {
        return descirbe;
    }

    public void setDescirbe(String descirbe) {
        this.descirbe = descirbe;
    }

    public String getFw() {
        return fw;
    }

    public void setFw(String fw) {
        this.fw = fw;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
