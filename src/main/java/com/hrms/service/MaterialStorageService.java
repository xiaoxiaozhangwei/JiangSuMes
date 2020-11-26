package com.hrms.service;

import com.hrms.bean.MaterialRecord;
import com.hrms.bean.MaterialStorage;
import com.hrms.mapper.MaterialStorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialStorageService {

    @Autowired
    private MaterialStorageMapper materialStorageMapper;

    // 来料入库记录保存在来料表
    public Integer save(MaterialStorage materialStorage){
        return materialStorageMapper.save(materialStorage);
    }

    // 来料入库记录保存到来料出入库记录表
    // 来料出库记录保存到来料出入库记录表
    public Integer insertRecord(String erp, String record,String operator) {
        return materialStorageMapper.insertRecord(erp,record,operator);
    }

    // 查找前台传入的erp料号的数据，按时间排序
    public List<MaterialStorage> selectMaterialStorageByTime(String erp)
    {
        return  materialStorageMapper.selectMaterialStorageByTime(erp);
    }

    public Integer updateStorage(Integer num,String erp,String purchaseNumber) {
        return materialStorageMapper.updateStorage(num,erp,purchaseNumber);
    }

    // 根据erp查询erp记录表
    public List<MaterialRecord> selectERPHistory(String erp) {
        return materialStorageMapper.selectERPHistory(erp);
    }

    // 来料出库页面，判断出库数量是否大于该外箱所拥有的数量
    public Integer selectNumber(String wbox) {
        return materialStorageMapper.selectNumber(wbox);
    }

    // 检查展板不允许放置不同的erp料号
    public String check(String stock) {
        return materialStorageMapper.check(stock);
    }

    // 扫描枪扫描储位，判断该储位上的erp是否是该erp中最早入库的。先进先出。不是的话要提示哪一个储位上的是最早入库的
    public String  saostock(String erp) {
        return materialStorageMapper.saostock(erp);
    }

    // 扫描枪扫描外箱，判断该外箱是否属于该储位
    public List<String> saowbox(String saostock) {
        return materialStorageMapper.saowbox(saostock);
    }

    // 在扫描枪扫描外箱之后，如果该外箱不属于该储位，那么就需要提示该外箱属于哪个储位
    public String selectstock(String saowbox) {
        return materialStorageMapper.selectstock(saowbox);
    }

    // 该外箱属于该储位，在下方表格中自动填充数量
    public Integer selectnum(String saowbox) {
        return materialStorageMapper.selectnum(saowbox);
    }

    // 出库页面根据外箱号查询出来要往出库表中添加的记录
    public MaterialStorage selectMessage(String wbox) {
        return materialStorageMapper.selectMessage(wbox);
    }

    // 选择最早入库储位上最早入库的外箱
    public String firstwbox(String stock) {
        return materialStorageMapper.firstwbox(stock);
    }
}
