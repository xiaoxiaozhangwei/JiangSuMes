package com.hrms.mapper;

import com.hrms.bean.Zuzhuang;

import java.util.List;

public interface LenovoLinkMapper {
    int insertlenovolink(Zuzhuang zuzhuang);

    List<Zuzhuang> selectProductsnExist(String productionSN);

    List<Zuzhuang> selectLenovosnExist(String lenovoSN);
}
