package com.hrms.service;

import com.hrms.bean.PCBAData;
import com.hrms.mapper.OC2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OC2Service {
    @Autowired
    OC2Mapper oc2Mapper;
    public    int insertOC2(PCBAData pcbaData)
    {
        return oc2Mapper.insertOC2(pcbaData);
    }
    public int selectOC2pass()
    {
        return  oc2Mapper.selectOC2pass();
    }

    public int selectOC2NG()
    {
        return  oc2Mapper.selectOC2NG();
    }

    public List<String> selecthismonthmodel(String daytime){
        return oc2Mapper.selecthismonthmodel(daytime);
    }

    public Integer selectrightcountbymodeldaily(String daytime,String model){
        return oc2Mapper.selectrightcountbymodeldaily(daytime, model);
    }
    public Integer selectwrongcountbymodeldaily(String daytime,String model){
        return oc2Mapper.selectwrongcountbymodeldaily(daytime,model);
    }
}
