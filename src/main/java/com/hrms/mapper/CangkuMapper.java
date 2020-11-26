package com.hrms.mapper;


import com.hrms.bean.Baozhuang;
import com.hrms.bean.CangkuRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CangkuMapper {

    // 根据外箱号批量导入包装数据到仓库的数据库中
    int insertCangkuByWbox(String wbox);

    //根据外箱号查询 入库需要
    List<Baozhuang> selectWbox(String wboxId);

    //插入仓库数据库
    int insertCangKu(Baozhuang baozhuang);

    //根据sn查询内箱号  ----仓库外箱打印使用
    Baozhuang selectNboxBySn(String sn);

    //根据内箱号查询数量 仓库外箱打印使用
    int selectNboxNumber(String nbox);

    // 根据内箱号查询sn 仓库内箱打印使用
    List<String> selectSnByNbox(String nbox);

    //更改内箱的外箱号
    Integer updateWbox(@Param("nboxId") String NboxId, @Param("wboxId") String WboxId);

    //记录新旧内箱，和旧外箱
    int insertCangKuRecord(CangkuRecord cangkuRecord);
    //先检查是不是经过拆箱记录修改信息了
    CangkuRecord  selecteCangKuRecordByNewNbox(String newNbox);
    //直接更改新的外箱号
    int updateCangKuRecordByNewWbox(@Param("newNbox") String newNbox, @Param("newWbox") String newWbox);
    //根据未拆箱的内箱号 来查出旧外箱号
    String selectOldWboxByOldNbox(String oldNbox);
    //更改未拆箱的内箱号 的外箱号 进行记录
    int insertRecordByNotChaixiang(@Param("oldNbox") String oldNbox, @Param("oldWbox") String oldWbox, @Param("newWbox") String newWbox);
}
