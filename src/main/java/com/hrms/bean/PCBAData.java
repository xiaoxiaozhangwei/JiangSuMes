package com.hrms.bean;

public class PCBAData {
    private String  mo;
    private String  deviceNumber;
    private  String operator;
    private String sn;
    private  String model;
    private  String testName;
    private String testTime;
    private  String testResult;
    private  String errorCode;
    private String fw;

    public PCBAData(String mo, String deviceNumber, String operator, String sn, String model, String testName, String testTime, String testResult, String errorCode,String fw) {
        this.mo = mo;
        this.deviceNumber = deviceNumber;
        this.operator = operator;
        this.sn = sn;
        this.model = model;
        this.testName = testName;
        this.testTime = testTime;
        this.testResult = testResult;
        this.errorCode = errorCode;
        this.fw=fw;
    }
    public PCBAData(String mo, String deviceNumber, String operator, String sn, String model, String testName, String testTime, String testResult,String fw) {
        this.mo = mo;
        this.deviceNumber = deviceNumber;
        this.operator = operator;
        this.sn = sn;
        this.model = model;
        this.testName = testName;
        this.testTime = testTime;
        this.testResult = testResult;
        this.fw=fw;
    }

    public PCBAData() {
    }

    public String getMo() {
        return mo;
    }

    public void setMo(String mo) {
        this.mo = mo;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getFw() {
        return fw;
    }

    public void setFw(String fw) {
        this.fw = fw;
    }

    @Override
    public String toString() {
        return "PCBAData{" +
                "mo='" + mo + '\'' +
                ", deviceNumber='" + deviceNumber + '\'' +
                ", operator='" + operator + '\'' +
                ", sn='" + sn + '\'' +
                ", model='" + model + '\'' +
                ", testName='" + testName + '\'' +
                ", testTime='" + testTime + '\'' +
                ", testResult='" + testResult + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", fw='" + fw + '\'' +
                '}';
    }
}
