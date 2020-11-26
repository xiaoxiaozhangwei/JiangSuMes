package com.hrms.service;

import com.hrms.bean.PCBAData;
import com.hrms.mapper.OC4Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OC4Service {

    @Autowired
    OC4Mapper oc4Mapper;
    public    int insertOC4(PCBAData pcbaData)
    {
        return oc4Mapper.insertOC4(pcbaData);
    }
   public int selectOC4passByline1()
   {
       return  oc4Mapper.selectOC4passByline1();
   }
    public int selectOC4NGByline1()
    {
        return  oc4Mapper.selectOC4NGByline1();
    }
  public  int selectOC4passByline2()
    {
        return oc4Mapper.selectOC4passByline2();
    }
  public  int selectOC4NGByline2()
  {
      return  oc4Mapper.selectOC4NGByline2();
  }
    public  int selectOC4passByline3()
    {
        return oc4Mapper.selectOC4passByline3();
    }
    public  int selectOC4NGByline3()
    {
        return  oc4Mapper.selectOC4NGByline3();
    }

    public List<String> selecthismonthmodel(String daytime){
        return oc4Mapper.selecthismonthmodel(daytime);
    }

    public Integer selectrightcountbymodeldaily(String daytime,String model){
        return oc4Mapper.selectrightcountbymodeldaily(daytime, model);
    }
    public Integer selectwrongcountbymodeldaily(String daytime,String model){
        return oc4Mapper.selectwrongcountbymodeldaily(daytime,model);
    }

    // oc4 电子看板
    public int selectOC4ByDisplayBoard(String testResult, Integer line) {
        return oc4Mapper.selectOC4ByDisplayBoard(testResult,line);
    }

}
