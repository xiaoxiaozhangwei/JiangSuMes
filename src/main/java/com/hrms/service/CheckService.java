package com.hrms.service;

import com.hrms.bean.Check;
import com.hrms.mapper.CheckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckService {
    @Autowired
    CheckMapper checkMapper;

    public int insetcheck1(Check check){
        return checkMapper.insertcheck1(check);
    }
    public int insetcheck2(Check check){
        return checkMapper.insertcheck2(check);
    }
    public Integer selectProByName(String productionSN,String moid){
        return checkMapper.selectProByName(productionSN,moid);
    }
    public int updatemo(String moId){
        return checkMapper.updatemo(moId);
    }
    public Integer production(String productionSN){
        return checkMapper.production(productionSN);
    }

    public Integer selectrightcount1(){
        return checkMapper.selectrightcount1();
    }
    public Integer selectrightcount2(){
        return checkMapper.selectrightcount2();
    }
    public Integer selectwrongcount1(){
        return checkMapper.selectwrongcount1();
    }
    public Integer selectwrongcount2(){
        return checkMapper.selectwrongcount2();
    }

    public List<String> selecthismonthmodel(String daytime){
        return checkMapper.selecthismonthmodel(daytime);
    }

    public Integer selectrightcountbymodeldaily(String daytime,String model){
        return checkMapper.selectrightcountbymodeldaily(daytime, model);
    }
    public Integer selectwrongcountbymodeldaily(String daytime,String model){
        return checkMapper.selectwrongcountbymodeldaily(daytime,model);
    }

   public Integer selectCheckRelate( String operation,String line)
   {
       return  checkMapper.selectCheckRelate(operation,line);
   }

}
