package com.hrms.bean;

public class Bit {


        private String   sn;
        private String   model;
        private String   BitNumber;
        private String   ComputerNumber;
        private String    DiskName;
        private String    fw;
        private String    ip;
        private String    TestName;
        private String    StartTime;
        private String    FinishTime;
        private String    name;
        private String    round;
        private String    times;
        private String    TestResult;
        private String    ErrorCode;

    public Bit() {
    }

    public Bit(String bitNumber, String computerNumber, String diskName, String ip,String sn,String model, String fw,String testName,
               String startTime, String finishTime, String name, String round, String times, String testResult) {
        this.sn = sn;
        this.model = model;
        BitNumber = bitNumber;
        ComputerNumber = computerNumber;
        DiskName = diskName;
        this.fw = fw;
        this.ip = ip;
        TestName = testName;
        StartTime = startTime;
        FinishTime = finishTime;
        this.name = name;
        this.round = round;
        this.times = times;
        TestResult = testResult;
    }

    public Bit(String bitNumber, String computerNumber, String diskName, String ip,String sn,String model, String fw,String testName,
               String startTime, String finishTime, String name, String round, String times, String testResult ,String errorCode) {
        this.sn = sn;
        this.model = model;
        BitNumber = bitNumber;
        ComputerNumber = computerNumber;
        DiskName = diskName;
        this.fw = fw;
        this.ip = ip;
        TestName = testName;
        StartTime = startTime;
        FinishTime = finishTime;
        this.name = name;
        this.round = round;
        this.times = times;
        TestResult = testResult;
        ErrorCode = errorCode;
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

    public String getBitNumber() {
        return BitNumber;
    }

    public void setBitNumber(String bitNumber) {
        BitNumber = bitNumber;
    }

    public String getComputerNumber() {
        return ComputerNumber;
    }

    public void setComputerNumber(String computerNumber) {
        ComputerNumber = computerNumber;
    }

    public String getDiskName() {
        return DiskName;
    }

    public void setDiskName(String diskName) {
        DiskName = diskName;
    }

    public String getFw() {
        return fw;
    }

    public void setFw(String fw) {
        this.fw = fw;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(String finishTime) {
        FinishTime = finishTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getTestResult() {
        return TestResult;
    }

    public void setTestResult(String testResult) {
        TestResult = testResult;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    @Override
    public String toString() {
        return "Bit{" +
                "sn='" + sn + '\'' +
                ", model='" + model + '\'' +
                ", BitNumber='" + BitNumber + '\'' +
                ", ComputerNumber='" + ComputerNumber + '\'' +
                ", DiskName='" + DiskName + '\'' +
                ", fw='" + fw + '\'' +
                ", ip='" + ip + '\'' +
                ", TestName='" + TestName + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", FinishTime='" + FinishTime + '\'' +
                ", name='" + name + '\'' +
                ", round='" + round + '\'' +
                ", times='" + times + '\'' +
                ", TestResult='" + TestResult + '\'' +
                ", ErrorCode='" + ErrorCode + '\'' +
                '}';
    }
}
