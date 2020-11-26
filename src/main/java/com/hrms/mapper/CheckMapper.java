package com.hrms.mapper;

import com.hrms.bean.Check;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckMapper {
    int insertcheck1(Check check);
    int insertcheck2(Check check);
    Integer selectProByName(@Param("productSN") String productionSN,@Param("moid") String moid);

    int updatemo(String moId);
    Integer production( String productionSN);

    Integer selectrightcount1();
    Integer selectrightcount2();
    Integer selectwrongcount1();
    Integer selectwrongcount2();

    List<String> selecthismonthmodel(String daytime);

    Integer selectrightcountbymodeldaily(@Param("daytime") String daytime,@Param("model") String model);
    Integer selectwrongcountbymodeldaily(@Param("daytime") String daytime,@Param("model") String model);

    Integer selectCheckRelate(@Param("operation") String operation,@Param("line") String line);
}
