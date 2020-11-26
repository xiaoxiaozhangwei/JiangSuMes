package com.hrms.bean;

public class QC {
    private String  mo;
    private String erpNumber;

    private String sn;
    private  String model;
    private  String testName;
    private String testTime;
    private  String testResult;
    private  String nowLoop;
    private  String  totalLoop;

    public QC(String mo, String erpNumber, String sn, String model, String testName, String testTime,  String nowLoop, String totalLoop,String testResult) {
        this.mo = mo;
        this.erpNumber = erpNumber;
        this.sn = sn;
        this.model = model;
        this.testName = testName;
        this.testTime = testTime;
        this.testResult = testResult;
        this.nowLoop = nowLoop;
        this.totalLoop = totalLoop;
    }

    public QC() {
    }

    public String getMo() {
        return mo;
    }

    public void setMo(String mo) {
        this.mo = mo;
    }

    public String getErpNumber() {
        return erpNumber;
    }

    public void setErpNumber(String erpNumber) {
        this.erpNumber = erpNumber;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getNowLoop() {
        return nowLoop;
    }

    public void setNowLoop(String nowLoop) {
        this.nowLoop = nowLoop;
    }

    public String getTotalLoop() {
        return totalLoop;
    }

    public void setTotalLoop(String totalLoop) {
        this.totalLoop = totalLoop;
    }

    @Override
    public String toString() {
        return "QC{" +
                "mo='" + mo + '\'' +
                ", erpNumber='" + erpNumber + '\'' +
                ", sn='" + sn + '\'' +
                ", model='" + model + '\'' +
                ", testName='" + testName + '\'' +
                ", testTime='" + testTime + '\'' +
                ", testResult='" + testResult + '\'' +
                ", nowLoop=" + nowLoop +
                ", totalLoop=" + totalLoop +
                '}';
    }
}
