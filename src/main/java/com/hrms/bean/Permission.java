package com.hrms.bean;

public class Permission {


    private String pername;

    private String percode;



    public String getPername() {
        return pername;
    }

    public void setPername(String pername) {
        this.pername = pername == null ? null : pername.trim();
    }

    public String getPercode() {
        return percode;
    }

    public void setPercode(String percode) {
        this.percode = percode == null ? null : percode.trim();
    }

    @Override
    public String toString() {
        return "Permission{" +
                ", pername='" + pername + '\'' +
                ", percode='" + percode + '\'' +
                '}';
    }
}