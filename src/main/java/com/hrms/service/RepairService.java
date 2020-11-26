package com.hrms.service;

import com.hrms.bean.Repair;
import com.hrms.mapper.RepairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService {
    @Autowired
    RepairMapper repairMapper;

    public String selectpcbabyssd(String ssdsn){
        return repairMapper.selectpcbabyssd(ssdsn);
    }

    public String selectfwbyssd(String ssdsn){
        return repairMapper.selectfwbyssd(ssdsn);
    }

    public Integer saverepairmesg(Repair repair){
        return repairMapper.saverepairmesg(repair);
    }

    public List<Repair> showrepairmesg(String ssdsn,String pcbasn){
        return repairMapper.showrepairmesg(ssdsn, pcbasn);
    }

    public Integer updaterepairmesg(Repair repair){
        return repairMapper.updaterepairmesg(repair);
    }

    public List<Repair> selectOC1(String ssdsn,String pcbasn){
        return repairMapper.selectOC1(ssdsn, pcbasn);
    }

    public List<Repair> selectOC2(String ssdsn,String pcbasn){
        return repairMapper.selectOC2(ssdsn, pcbasn);
    }

    public List<Repair> selectOC4(String ssdsn,String pcbasn){
        return repairMapper.selectOC4(ssdsn, pcbasn);
    }

    public List<Repair> selectCDI(String ssdsn,String pcbasn){
        return repairMapper.selectCDI(ssdsn, pcbasn);
    }

    public List<Repair> selectcheck(String ssdsn){
        return repairMapper.selectcheck(ssdsn);
    }

    public Integer outbound(String ssdsn,String pcbasn,String outbound_receiver){
        return repairMapper.outbound(ssdsn, pcbasn, outbound_receiver);
    }
}
