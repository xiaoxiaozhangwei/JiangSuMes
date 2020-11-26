package com.hrms.mapper;

import com.hrms.bean.Shipment;
import com.hrms.bean.Storage;

import java.util.List;

public interface ShipmentMapper {
    //添加到出货数据库
    int insertShipment(Shipment shipment);

    //出货SN信息比对  根据所有出货SN 查询是否重复
    List<String> selectByAllSN(String wbox);

    //导出要出货的SN号
    List<String> selectShipmentSN(List<String> wboxs);


    //查找入库时间最早的，先进先出
    Storage selectFirstByStorageTime(String model);
}
