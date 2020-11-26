package com.hrms.mapper;


import com.hrms.bean.MaterialShipment;

public interface MaterialShipmentMapper {

     // 出库时，向出库表中添加出库数据
     int insertMaterialShipment(MaterialShipment materialShipment);


}
