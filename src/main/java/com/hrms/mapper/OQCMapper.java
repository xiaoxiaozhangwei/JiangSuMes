package com.hrms.mapper;

import com.hrms.bean.Check;

public interface OQCMapper {
    int insertcheck1(Check check);
    int insertcheck2(Check check);
    Integer selectProByName(String productionSN);
    Integer production( String productionSN);
}
