package com.hrms.service;

import com.hrms.bean.PCBAData;
import com.hrms.mapper.OC1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OC1Service {

    @Autowired
    OC1Mapper oc1Mapper;
    public    int insertOC1(PCBAData pcbaData)
    {
        return oc1Mapper.insertOC1(pcbaData);
    }
   public int selectOC1pass()
   {
       return  oc1Mapper.selectOC1pass();
   }

   public int selectOC1NG()
   {
       return  oc1Mapper.selectOC1NG();
   }

    public List<String> selecthismonthmodel(String daytime){
        return oc1Mapper.selecthismonthmodel(daytime);
    }

    public Integer selectrightcountbymodeldaily(String daytime,String model){
        return oc1Mapper.selectrightcountbymodeldaily(daytime, model);
    }
    public Integer selectwrongcountbymodeldaily(String daytime,String model){
        return oc1Mapper.selectwrongcountbymodeldaily(daytime,model);
    }
}
