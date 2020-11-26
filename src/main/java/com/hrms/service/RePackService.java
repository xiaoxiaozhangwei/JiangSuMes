package com.hrms.service;

import com.hrms.bean.RePack;
import com.hrms.mapper.RePackMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RePackService {
    @Autowired
    RePackMapper rePackMapper;

    public int insertpack(RePack rePack){
        return rePackMapper.insertpack(rePack);
    }

    public Integer production(String productionSN,String model){
        return rePackMapper.production(productionSN,model);
    }

    public Integer count_Nbox(){
        return rePackMapper.count_Nbox();
    }

    public Integer selectProByName(String productionSN){
        return rePackMapper.selectProByName(productionSN);
    }

    public List<RePack> evportSN() {
        return rePackMapper.evportSN();
    }

    public List<RePack> packSN(String NboxId){
        return rePackMapper.packSN(NboxId);
    }

    public Integer count_Wbox(){
        return rePackMapper.count_Wbox();
    }

    public Integer NboxExists(String NboxId){
        return rePackMapper.NboxExists(NboxId);
    }

    public Integer updateWbox(@Param("NboxId") String NboxId, @Param("WboxId") String WboxId){
        return rePackMapper.updateWbox(NboxId, WboxId);
    }

    public String selectNboxByPro(String productionSN){
        return rePackMapper.selectNboxByPro(productionSN);
    }

    public Integer selectCountPro(){
        return rePackMapper.selectCountPro();
    }

    public Integer selectCountByNbox(String NboxId){
        return rePackMapper.selectCountByNbox(NboxId);
    }


    //根据外箱号查询所有数据
    public List<RePack> selectWbox(String wboxId)
    {
        return rePackMapper.selectWbox(wboxId);
    }
    //查询外箱号
    public String  selectWboxnumber(String sn)
    {
        return rePackMapper.selectWboxnumber(sn);
    }

    public int insertpack2(RePack rePack){
        return rePackMapper.insertpack2(rePack);
    }
    public int update_moremain02(String moId){
        return rePackMapper.update_moremain02(moId);
    }

    //内包装操作信息修改
    public int updateModal(String productionSN){
        return rePackMapper.updateModal(productionSN);
    }
}
