package com.hrms.mapper;

import com.hrms.bean.Repair;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RepairMapper {
    String selectpcbabyssd(String ssdsn);
    String selectfwbyssd(String ssdsn);

    Integer saverepairmesg(Repair repair);

    List<Repair> showrepairmesg(@Param("ssd_sn")String ssdsn,@Param("pcba_sn")String pcbasn);

    Integer updaterepairmesg(Repair repair);

    List<Repair> selectOC1(@Param("ssd_sn")String ssd_sn,@Param("pcba_sn")String pcba_sn);
    List<Repair> selectOC2(@Param("ssd_sn")String ssd_sn,@Param("pcba_sn")String pcba_sn);
    List<Repair> selectOC4(@Param("ssd_sn")String ssd_sn,@Param("pcba_sn")String pcba_sn);
    List<Repair> selectCDI(@Param("ssd_sn")String ssd_sn,@Param("pcba_sn")String pcba_sn);
    List<Repair> selectcheck(@Param("ssd_sn")String ssd_sn);

    Integer outbound(@Param("ssd_sn")String ssd_sn,@Param("pcba_sn")String pcba_sn,@Param("outbound_receiver")String outbound_receiver);
}
