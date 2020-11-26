package com.hrms.bean;

public class Zuzhuangworklog {
    private  int id;
    private  String mo;
    private String  line_number;
    private  String A01name1;
    private  String A01name2;
    private  String A02name1;
    private  String A02name2;
    private  String A03name1;
    private  String A03name2;
    private  String A04name1;
    private  String A04name2;
    private  String A05name1;
    private  String A05name2;
    private  String FW;

    public Zuzhuangworklog(int id, String mo, String line_number, String a01name1, String a01name2, String a02name1, String a02name2, String a03name1, String a03name2, String a04name1, String a04name2, String a05name1, String a05name2, String FW) {
        this.id = id;
        this.mo = mo;
        this.line_number = line_number;
        A01name1 = a01name1;
        A01name2 = a01name2;
        A02name1 = a02name1;
        A02name2 = a02name2;
        A03name1 = a03name1;
        A03name2 = a03name2;
        A04name1 = a04name1;
        A04name2 = a04name2;
        A05name1 = a05name1;
        A05name2 = a05name2;
        this.FW = FW;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMo() {
        return mo;
    }

    public void setMo(String mo) {
        this.mo = mo;
    }

    public String getLine_number() {
        return line_number;
    }

    public void setLine_number(String line_number) {
        this.line_number = line_number;
    }

    public String getA01name1() {
        return A01name1;
    }

    public void setA01name1(String a01name1) {
        A01name1 = a01name1;
    }

    public String getA01name2() {
        return A01name2;
    }

    public void setA01name2(String a01name2) {
        A01name2 = a01name2;
    }

    public String getA02name1() {
        return A02name1;
    }

    public void setA02name1(String a02name1) {
        A02name1 = a02name1;
    }

    public String getA02name2() {
        return A02name2;
    }

    public void setA02name2(String a02name2) {
        A02name2 = a02name2;
    }

    public String getA03name1() {
        return A03name1;
    }

    public void setA03name1(String a03name1) {
        A03name1 = a03name1;
    }

    public String getA03name2() {
        return A03name2;
    }

    public void setA03name2(String a03name2) {
        A03name2 = a03name2;
    }

    public String getA04name1() {
        return A04name1;
    }

    public void setA04name1(String a04name1) {
        A04name1 = a04name1;
    }

    public String getA04name2() {
        return A04name2;
    }

    public void setA04name2(String a04name2) {
        A04name2 = a04name2;
    }

    public String getA05name1() {
        return A05name1;
    }

    public void setA05name1(String a05name1) {
        A05name1 = a05name1;
    }

    public String getA05name2() {
        return A05name2;
    }

    public void setA05name2(String a05name2) {
        A05name2 = a05name2;
    }

    public String getFW() {
        return FW;
    }

    public void setFW(String FW) {
        this.FW = FW;
    }

    public Zuzhuangworklog() {
    }

    @Override
    public String toString() {
        return "Zuzhuangworklog{" +
                "id=" + id +
                ", mo='" + mo + '\'' +
                ", line_number='" + line_number + '\'' +
                ", A01name1='" + A01name1 + '\'' +
                ", A01name2='" + A01name2 + '\'' +
                ", A02name1='" + A02name1 + '\'' +
                ", A02name2='" + A02name2 + '\'' +
                ", A03name1='" + A03name1 + '\'' +
                ", A03name2='" + A03name2 + '\'' +
                ", A04name1='" + A04name1 + '\'' +
                ", A04name2='" + A04name2 + '\'' +
                ", A05name1='" + A05name1 + '\'' +
                ", A05name2='" + A05name2 + '\'' +
                ", FW='" + FW + '\'' +
                '}';
    }

    public Zuzhuangworklog(String mo, String line_number, String a01name1, String a01name2, String a02name1, String a02name2, String a03name1, String a03name2, String a04name1, String a04name2, String a05name1, String a05name2, String FW) {
        this.mo = mo;
        this.line_number = line_number;
        A01name1 = a01name1;
        A01name2 = a01name2;
        A02name1 = a02name1;
        A02name2 = a02name2;
        A03name1 = a03name1;
        A03name2 = a03name2;
        A04name1 = a04name1;
        A04name2 = a04name2;
        A05name1 = a05name1;
        A05name2 = a05name2;
        this.FW = FW;
    }


    public Zuzhuangworklog(String mo, String line_number, String a01name1, String a01name2, String a02name1, String a02name2, String a03name1, String a03name2, String a04name1, String a04name2, String a05name1, String FW) {
        this.mo = mo;
        this.line_number = line_number;
        A01name1 = a01name1;
        A01name2 = a01name2;
        A02name1 = a02name1;
        A02name2 = a02name2;
        A03name1 = a03name1;
        A03name2 = a03name2;
        A04name1 = a04name1;
        A04name2 = a04name2;
        A05name1 = a05name1;
        this.FW = FW;
    }
}
