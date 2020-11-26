package com.hrms.mapper;

import com.hrms.bean.Zuzhuang;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZuzhuangMapper {
    String TABLE_NAME = "tb_link";
    String INSERT_FIELDS = "product_SN,PCBA_SN,operation,device_times,operator,date,device_id,mo_id,device_name,mo_num,mo_remain,gys," +
            "comments,line,surfaceMo,surfacePurchase,surfaceErp,surfaceDC,surfaceTime,bottomMo,bottomPurchase,bottomErp,bottomDC,bottomTime";
    String SELECT_FIELDS = "link_id, " + INSERT_FIELDS;

    /**
     * 新增
     */
    @Insert({"INSERT INTO", TABLE_NAME, "(",INSERT_FIELDS,") " +
            "VALUES(#{productionSN},"+
            "#{pcbaSN},"+
            "#{operation},"+
            "#{times}+1,"+
            "#{operator},"+
            "now(),"+
            "#{deviceId},"+
            "#{moId},"+
            "#{devicename},"+
            "#{monum},"+
            "#{moremain},"+
            "#{gys},"+
            "#{comments},"+
            "#{line},"+
            "#{surfaceMo},"+
            "#{surfacePurchase},"+
            "#{surfaceErp},"+
            "#{surfaceDC},"+
            "#{surfaceTime},"+
            "#{bottomMo},"+
            "#{bottomPurchase},"+
            "#{bottomErp},"+
            "#{bottomDC},"+
            "#{bottomTime})"
    })
    int insertOne(Zuzhuang zuzhuang);

    Integer updatemo(String moId);

    int updatedevice(Zuzhuang zuzhuang);


    Zuzhuang selectProByName(@Param("productionSN") String productionSN,@Param("mo")String mo);
    Zuzhuang selectPcba(@Param("pcbaSN") String pcbaSN,@Param("moid") String moid);

    List <Zuzhuang> selectlink(Zuzhuang zuzhuang);

    List<Zuzhuang> selectAll(Zuzhuang zuzhuang);


    Integer selectcount();

    Integer selectLinkbyline(String line);
}
