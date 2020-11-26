package com.hrms.bean;

public class Repair {
    private String ssd_sn;
    private String pcba_sn;
    private String error_date;
    private String error_area;
    private String errorcode;
    private String result;
    private String operation;
    private String part;
    private String operator;
    private String fw;
    private String repair_date;
    private String retest_area;
    private String element;
    private String dc;
    private String others;

    private String outbound_time;
    private String outbound_receiver;

    public String getOutbound_time() {
        return outbound_time;
    }

    public void setOutbound_time(String outbound_time) {
        this.outbound_time = outbound_time;
    }

    public String getOutbound_receiver() {
        return outbound_receiver;
    }

    public void setOutbound_receiver(String outbound_receiver) {
        this.outbound_receiver = outbound_receiver;
    }

    public String getRetest_area() {
        return retest_area;
    }

    public void setRetest_area(String retest_area) {
        this.retest_area = retest_area;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getRepair_date() {
        return repair_date;
    }

    public void setRepair_date(String repair_date) {
        this.repair_date = repair_date;
    }

    public String getFw() {
        return fw;
    }

    public void setFw(String fw) {
        this.fw = fw;
    }

    public String getSsd_sn() {
        return ssd_sn;
    }

    public void setSsd_sn(String ssd_sn) {
        this.ssd_sn = ssd_sn;
    }

    public String getPcba_sn() {
        return pcba_sn;
    }

    public void setPcba_sn(String pcba_sn) {
        this.pcba_sn = pcba_sn;
    }

    public String getError_date() {
        return error_date;
    }

    public void setError_date(String error_date) {
        this.error_date = error_date;
    }

    public String getError_area() {
        return error_area;
    }

    public void setError_area(String error_area) {
        this.error_area = error_area;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Repair() {
    }

    public Repair(String ssd_sn, String pcba_sn, String error_date, String error_area, String errorcode, String result, String operation, String part, String operator, String fw, String repair_date, String retest_area, String element, String dc, String others, String outbound_time, String outbound_receiver) {
        this.ssd_sn = ssd_sn;
        this.pcba_sn = pcba_sn;
        this.error_date = error_date;
        this.error_area = error_area;
        this.errorcode = errorcode;
        this.result = result;
        this.operation = operation;
        this.part = part;
        this.operator = operator;
        this.fw = fw;
        this.repair_date = repair_date;
        this.retest_area = retest_area;
        this.element = element;
        this.dc = dc;
        this.others = others;
        this.outbound_time = outbound_time;
        this.outbound_receiver = outbound_receiver;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "ssd_sn='" + ssd_sn + '\'' +
                ", pcba_sn='" + pcba_sn + '\'' +
                ", error_date='" + error_date + '\'' +
                ", error_area='" + error_area + '\'' +
                ", errorcode='" + errorcode + '\'' +
                ", result='" + result + '\'' +
                ", operation='" + operation + '\'' +
                ", part='" + part + '\'' +
                ", operator='" + operator + '\'' +
                ", fw='" + fw + '\'' +
                ", repair_date='" + repair_date + '\'' +
                ", retest_area='" + retest_area + '\'' +
                ", element='" + element + '\'' +
                ", dc='" + dc + '\'' +
                ", others='" + others + '\'' +
                ", outbound_time='" + outbound_time + '\'' +
                ", outbound_receiver='" + outbound_receiver + '\'' +
                '}';
    }
}

