package com.hrms.service;

import com.hrms.bean.MaterialShipment;
import com.hrms.bean.MaterialStorage;
import com.hrms.mapper.MaterialShipmentMapper;
import com.hrms.mapper.MaterialStorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MaterialShipmentService {

    @Autowired
    MaterialStorageMapper materialStorageMapper;

    @Autowired
    MaterialShipmentMapper materialShipmentMapper;

    // 出库时，向出库表中添加出库数据
    public  int insertShipment(MaterialShipment materialShipment) {
        return materialShipmentMapper.insertMaterialShipment(materialShipment);
    }

    // 查找前台传入的erp料号的数据，按时间排序，先进先出
    public List<MaterialStorage> selectMaterialStorageByTime(String erp)
    {
       return  materialStorageMapper.selectMaterialStorageByTime(erp);
    }


}
