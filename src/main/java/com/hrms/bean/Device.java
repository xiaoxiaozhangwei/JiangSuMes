package com.hrms.bean;

public class Device {
    private Integer deviceId;
    private Integer times;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Device() {
    }

    public Device(Integer deviceId, Integer times) {
        this.deviceId = deviceId;
        this.times = times;
    }
}
