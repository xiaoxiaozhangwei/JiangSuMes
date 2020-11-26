package com.hrms.bean;

public class CDI {
    private String  mo;
    private String  deviceNumber;
    private  String operator;
    private String sn;
    private  String model;
    private  String testName;
    private  String  testTime;
    private  String testResult;
    private  String errorCode;

    public CDI(String mo, String deviceNumber, String operator, String sn, String model, String testName, String testTime, String testResult, String errorCode) {
        this.mo = mo;
        this.deviceNumber = deviceNumber;
        this.operator = operator;
        this.sn = sn;
        this.model = model;
        this.testName = testName;
        this.testTime = testTime;
        this.testResult = testResult;
        this.errorCode = errorCode;
    }
    public CDI(String mo, String deviceNumber, String operator, String sn, String model, String testName, String testTime, String testResult) {
        this.mo = mo;
        this.deviceNumber = deviceNumber;
        this.operator = operator;
        this.sn = sn;
        this.model = model;
        this.testName = testName;
        this.testTime = testTime;
        this.testResult = testResult;

    }

    public CDI() {
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

    @Override
    public String toString() {
        return "CDI{" +
                "mo='" + mo + '\'' +
                ", deviceNumber='" + deviceNumber + '\'' +
                ", operator='" + operator + '\'' +
                ", sn='" + sn + '\'' +
                ", model='" + model + '\'' +
                ", testName='" + testName + '\'' +
                ", testTime='" + testTime + '\'' +
                ", testResult='" + testResult + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
