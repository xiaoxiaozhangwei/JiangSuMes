package com.hrms.service;

import com.hrms.bean.Check;
import com.hrms.mapper.OQCMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OQCService {
    @Autowired
    OQCMapper oqcMapper;
    public int insetcheck1(Check check){
        return oqcMapper.insertcheck1(check);
    }
    public int insetcheck2(Check check){
        return oqcMapper.insertcheck2(check);
    }
    public Integer selectProByName(String productionSN){
        return oqcMapper.selectProByName(productionSN);
    }
    public Integer production(String productionSN){
        return oqcMapper.production(productionSN);
    }
}
