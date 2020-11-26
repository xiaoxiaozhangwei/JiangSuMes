package com.hrms.mapper;


import com.hrms.bean.PCBAData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OC1Mapper {
    int insertOC1(PCBAData pcbaData);
    int selectOC1pass();
    int selectOC1NG();

    List<String> selecthismonthmodel(String daytime);

    Integer selectrightcountbymodeldaily(@Param("daytime") String daytime, @Param("model") String model);
    Integer selectwrongcountbymodeldaily(@Param("daytime") String daytime,@Param("model") String model);
}
