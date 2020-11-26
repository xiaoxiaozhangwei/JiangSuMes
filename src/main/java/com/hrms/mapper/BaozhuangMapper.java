package com.hrms.mapper;

import com.hrms.bean.Baozhuang;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaozhuangMapper {
    int insertpack(Baozhuang baozhuang);

    Integer production(@Param("productionSN") String productionSN, @Param("model") String model);

    Integer count_Nbox();

    Integer selectProByName(@Param("productionSN") String productionSN,@Param("mesg") String mesg,@Param("moId") String moId);

    List<Baozhuang> evportSN();

    List<Baozhuang> packSN(String NboxId);

    Integer count_Wbox();

    Integer NboxExists(String NboxId);

    Integer updateWbox(@Param("nboxId") String NboxId,@Param("wboxId") String WboxId,@Param("operator") String operator);

    List<Baozhuang> selectNboxByPro(String productionSN);

    Integer selectCountPro();
    Integer selectCountByNbox(String NboxId);


    //根据外箱号查询 入库需要
    List<Baozhuang>  selectWbox(String wboxId);

    //扫描查询外箱号
    String selectWboxnumber(String sn);

    int insertpack2(Baozhuang baozhuang);
    int update_moremain02(String moId);

    //内包装操作信息修改
    int updateModal(String productionSN);

    //包装信息多条件查询
    List<Baozhuang>  selectrelateBaoZhuang(Baozhuang baozhuang);

    //根据外箱号查询内箱号
    List<String> selectNBoxByWbox(String wboxId);

    List<String> selecthismonthmodel(String daytime);

    Integer selectrightcountbymodeldaily(@Param("daytime") String daytime, @Param("model") String model);
    Integer selectwrongcountbymodeldaily(@Param("daytime") String daytime,@Param("model") String model);

    //根据线别 查询看板所要数据   sn数量
    Integer  selectSnCountByLine(String line);
    //根据线别 查询看板所要数据   内箱数量
    Integer  selectNboxCountByLine(String line);
    //根据线别 查询看板所要数据   外箱数量
    Integer  selectWboxCountByLine(String line);
}
