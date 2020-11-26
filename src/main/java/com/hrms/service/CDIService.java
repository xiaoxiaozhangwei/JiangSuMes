package com.hrms.service;

import com.hrms.bean.CDI;
import com.hrms.mapper.CDIMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CDIService {
    @Autowired
    CDIMapper cdiMapper;



    public  int insert(CDI cdi)
    {
        return cdiMapper.insertCDI(cdi);
    }

    public  int selectCDIpassByline1( )
    {
        return cdiMapper.selectCDIpassByline1();
    }
    public  int selectCDINGByline1( )
    {
        return cdiMapper.selectCDINGByline1();
    }

    public  int selectCDIpassByline2()
    {
        return cdiMapper.selectCDIpassByline2();
    }
    public  int selectCDINGByline2()
    {
        return cdiMapper.selectCDINGByline2();
    }

    public  int selectCDIpassByline3()
    {
        return cdiMapper.selectCDIpassByline3();
    }
    public  int selectCDINGByline3()
    {
        return cdiMapper.selectCDINGByline3();
    }

    public List<String> selecthismonthmodel(String daytime){
        return cdiMapper.selecthismonthmodel(daytime);
    }

    public Integer selectrightcountbymodeldaily(String daytime,String model){
        return cdiMapper.selectrightcountbymodeldaily(daytime, model);
    }
    public Integer selectwrongcountbymodeldaily(String daytime,String model){
        return cdiMapper.selectwrongcountbymodeldaily(daytime,model);
    }

    // 检查目检经过cdi
    public List<CDI> selectPassCDI(String productionSN,String mo) {
        return cdiMapper.selectPassCDI(productionSN,mo);
    }

    // cdi电子看板
    public int selectCDIByDisplayBoard(String result, String line) {
        return cdiMapper.selectCDIByDisplayBoard(result, line);
    }
}
