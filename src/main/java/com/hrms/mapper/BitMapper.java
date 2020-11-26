package com.hrms.mapper;

import com.hrms.bean.Bit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BitMapper {
    int insertBit(Bit bit);
    int selectBitpass();
    int selectBitNG();

    List<String> selecthismonthmodel(String daytime);

    Integer selectrightcountbymodeldaily(@Param("daytime") String daytime, @Param("model") String model);
    Integer selectwrongcountbymodeldaily(@Param("daytime") String daytime,@Param("model") String model);
    Integer  selectAllNGBymonth(Bit bit);


    Integer selectNGByBitNumberday(@Param("BitNumber") String BitNumber, @Param("model") String model,@Param("ErrorCode") String ErrorCode);

    List<String> selectModelByday();

    Integer selectpasscountbymodelandbitnumber(@Param("model") String model,@Param("BitNumber") String BitNumber);

}
