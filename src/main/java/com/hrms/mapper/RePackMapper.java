package com.hrms.mapper;

import com.hrms.bean.RePack;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RePackMapper {
    int insertpack(RePack rePack);

    Integer production(@Param("productionSN") String productionSN, @Param("model") String model);

    Integer count_Nbox();

    Integer selectProByName(String productionSN);

    List<RePack> evportSN();

    List<RePack> packSN(String NboxId);

    Integer count_Wbox();

    Integer NboxExists(String NboxId);

    Integer updateWbox(@Param("nboxId") String NboxId, @Param("wboxId") String WboxId);

    String selectNboxByPro(String productionSN);

    Integer selectCountPro();
    Integer selectCountByNbox(String NboxId);


    //根据外箱号查询 入库需要
    List<RePack>  selectWbox(String wboxId);

    //扫描查询外箱号
    String selectWboxnumber(String sn);

    int insertpack2(RePack rePack);
    int update_moremain02(String moId);

    //内包装操作信息修改
    int updateModal(String productionSN);
}
