package com.hrms.service;

import com.hrms.bean.Baozhuang;
import com.hrms.mapper.ScanSNPrintMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScanSNPrintService {
    @Autowired
    ScanSNPrintMapper scanSNPrintMapper;

    public Integer getmoremain(String mo_id){
        return scanSNPrintMapper.getmoremain(mo_id);
    }

    public Integer add_chandesn(Baozhuang baozhuang){
        return scanSNPrintMapper.add_chandesn(baozhuang);
    }
}
