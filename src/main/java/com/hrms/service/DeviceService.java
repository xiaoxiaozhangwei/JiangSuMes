package com.hrms.service;

import com.hrms.bean.Device;
import com.hrms.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    DeviceMapper deviceMapper;

    public Device getDeviceById(Integer deviceId){
        return deviceMapper.getdeviceById(deviceId);
    }

}
