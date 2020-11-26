package com.hrms.service;

import com.hrms.bean.Baozhuang;
import com.hrms.mapper.BaozhuangMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaozhuangService {
    @Autowired
    BaozhuangMapper baozhuangMapper;
    public int insertpack(Baozhuang baozhuang){
        return baozhuangMapper.insertpack(baozhuang);
    }

    public Integer production(String productionSN,String model){
        return baozhuangMapper.production(productionSN,model);
    }

    public Integer count_Nbox(){
        return baozhuangMapper.count_Nbox();
    }

    public Integer selectProByName(String productionSN,String mesg,String mo){
        return baozhuangMapper.selectProByName(productionSN,mesg,mo);
    }

    public List<Baozhuang> evportSN() {
        return baozhuangMapper.evportSN();
    }

    public List<Baozhuang> packSN(String NboxId){
        return baozhuangMapper.packSN(NboxId);
    }

    public Integer count_Wbox(){
        return baozhuangMapper.count_Wbox();
    }

    public Integer NboxExists(String NboxId){
        return baozhuangMapper.NboxExists(NboxId);
    }

    public Integer updateWbox(String NboxId,String WboxId,String operator){
        return baozhuangMapper.updateWbox(NboxId, WboxId,operator);
    }

    public List<Baozhuang> selectNboxByPro(String productionSN){
        return baozhuangMapper.selectNboxByPro(productionSN);
    }

    public Integer selectCountPro(){
        return baozhuangMapper.selectCountPro();
    }

    public Integer selectCountByNbox(String NboxId){
        return baozhuangMapper.selectCountByNbox(NboxId);
    }


    //根据外箱号查询所有数据
    public List<Baozhuang>  selectWbox(String wboxId)
    {
        return baozhuangMapper.selectWbox(wboxId);
    }
    //查询外箱号
    public String  selectWboxnumber(String sn)
    {
        return baozhuangMapper.selectWboxnumber(sn);
    }

    public int insertpack2(Baozhuang baozhuang){
        return baozhuangMapper.insertpack2(baozhuang);
    }

    public int update_moremain02(String moId){
        return baozhuangMapper.update_moremain02(moId);
    }

    //内包装操作信息修改
    public int updateModal(String productionSN){
        return baozhuangMapper.updateModal(productionSN);
    }

    //包装信息多条件查询
    public  List<Baozhuang>  selectrelateBaoZhuang(Baozhuang baozhuang) {
        return  baozhuangMapper.selectrelateBaoZhuang(baozhuang);
    }

    //根据外箱号查询内箱号
    public List<String> selectNBoxByWbox(String wboxId){
        return baozhuangMapper.selectNBoxByWbox(wboxId);
    }

    public List<String> selecthismonthmodel(String daytime){
        return baozhuangMapper.selecthismonthmodel(daytime);
    }

    public Integer selectrightcountbymodeldaily(String daytime,String model){
        return baozhuangMapper.selectrightcountbymodeldaily(daytime, model);
    }
    public Integer selectwrongcountbymodeldaily(String daytime,String model){
        return baozhuangMapper.selectwrongcountbymodeldaily(daytime,model);
    }

    //根据线别 查询看板所要数据   sn数量
    public Integer  selectSnCountByLine(String line)
    {
        return baozhuangMapper.selectSnCountByLine(line);
    }
    //根据线别 查询看板所要数据   内箱数量
    public Integer  selectNboxCountByLine(String line)
    {
        return baozhuangMapper.selectNboxCountByLine(line);
    }

    //根据线别 查询看板所要数据   外箱数量
    public  Integer  selectWboxCountByLine(String line)
    {
        return baozhuangMapper.selectWboxCountByLine(line);
    }
}
