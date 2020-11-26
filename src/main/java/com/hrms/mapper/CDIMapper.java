package com.hrms.mapper;

import com.hrms.bean.CDI;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public  interface CDIMapper {
    int insertCDI(CDI cdi);
    int selectCDIpassByline1();
    int selectCDINGByline1();
    int selectCDIpassByline2();
    int selectCDINGByline2();
    int selectCDIpassByline3();
    int selectCDINGByline3();

    List<String> selecthismonthmodel(String daytime);
    Integer selectrightcountbymodeldaily(@Param("daytime") String daytime, @Param("model") String model);
    Integer selectwrongcountbymodeldaily(@Param("daytime") String daytime,@Param("model") String model);

    // 检查目检经过cdi
    List<CDI> selectPassCDI(@Param("productionSN") String productionSN,@Param("mo") String mo);

    // cdi电子看板
    int selectCDIByDisplayBoard(@Param("testResult")String result, @Param("deviceNumber") String line);
}
