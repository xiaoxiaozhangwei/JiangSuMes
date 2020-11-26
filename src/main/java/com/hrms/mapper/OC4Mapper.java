package com.hrms.mapper;


import com.hrms.bean.PCBAData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OC4Mapper {
    int insertOC4(PCBAData pcbaData);
    int selectOC4passByline1();
    int selectOC4passByline2();
    int selectOC4passByline3();
    int selectOC4NGByline1();
    int selectOC4NGByline2();
    int selectOC4NGByline3();

    List<String> selecthismonthmodel(String daytime);

    Integer selectrightcountbymodeldaily(@Param("daytime") String daytime, @Param("model") String model);
    Integer selectwrongcountbymodeldaily(@Param("daytime") String daytime,@Param("model") String model);

    // oc4 电子看板
    int selectOC4ByDisplayBoard(@Param("testResult")String testResult,@Param("deviceNumber") Integer line);
}
