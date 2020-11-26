package com.hrms.mapper;


import com.hrms.bean.MaterialRecord;
import com.hrms.bean.MaterialStorage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaterialStorageMapper {
    // 将入库页面的数据添加到入库表
    Integer save(MaterialStorage materialStorage);

    // 入库时向记录表添加数据
    Integer insertRecord(@Param("erp")String erp, @Param("record")String record,@Param("operator")String operator);

    // 查找前台传入的erp料号的数据，按时间排序，先进先出
    List<MaterialStorage> selectMaterialStorageByTime(String erp);

    // 当出库时，在入库表中更改数据
    Integer updateStorage(@Param("num")Integer num,@Param("erp") String erp, @Param("purchaseNumber")String purchaseNumber);

    // 根据erp查询erp记录表
    List<MaterialRecord> selectERPHistory(String erp);

    // 来料出库页面，判断出库数量是否大于该外箱所拥有的数量
    Integer selectNumber(String wbox);

    // 检查展板不允许放置不同的erp料号
    String check(String stock);

    // 扫描枪扫描储位，判断该储位上的erp是否是该erp中最早入库的。先进先出。不是的话要提示哪一个储位上的是最早入库的
    String saostock(String erp);

    // 扫描枪扫描外箱，判断该外箱是否属于该储位
    List<String> saowbox(String saostock);

    //在扫描枪扫描外箱之后，如果该外箱不属于该储位，那么就需要提示该外箱属于哪个储位
    String selectstock(String saowbox);

    // 该外箱属于该储位，在下方表格中自动填充数量
    Integer selectnum(String saowbox);

    // 出库页面根据外箱号查询出来要往出库表中添加的记录,因为页面上没有这些数据
    MaterialStorage selectMessage(String wbox);

    // 选择最早入库储位上最早入库的外箱
    String firstwbox(String stock);
}
