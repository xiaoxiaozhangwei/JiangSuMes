package com.hrms.mapper;

import com.hrms.bean.PCBAData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OC2Mapper {
    int insertOC2(PCBAData pcbaData);
    int selectOC2pass();
    int selectOC2NG();

    List<String> selecthismonthmodel(String daytime);

    Integer selectrightcountbymodeldaily(@Param("daytime") String daytime, @Param("model") String model);
    Integer selectwrongcountbymodeldaily(@Param("daytime") String daytime,@Param("model") String model);
}
