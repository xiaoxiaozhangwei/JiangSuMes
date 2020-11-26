package com.hrms.service;

import com.hrms.bean.Baozhuang;
import com.hrms.bean.CangkuRecord;
import com.hrms.mapper.CangkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CangkuService {
    @Autowired
    CangkuMapper cangkuMapper;


    //根据外箱号查询所有数据
    public List<Baozhuang> selectWbox(String wboxId)
    {
        return cangkuMapper.selectWbox(wboxId);
    }


    // 根据外箱号批量导入包装数据到仓库的数据库中
    public int insertCangkuByWbox(String wbox)
    {
        return  cangkuMapper.insertCangkuByWbox(wbox);
    }


    //插入仓库数据库
    public  int insertCangKu(Baozhuang baozhuang)
    {
        return  cangkuMapper.insertCangKu(baozhuang);
    }


    //根据sn查询内箱号  ----仓库外箱打印使用
    public Baozhuang selectNboxBySn(String sn){
        return cangkuMapper.selectNboxBySn(sn);
    }

    //根据内箱号查询数量 仓库外箱打印使用
    public Integer selectNboxNumber(String nbox)
    {
        return  cangkuMapper.selectNboxNumber(nbox);
    }

    //根据内箱号查询sn 仓库内箱打印使用
    public  List<String> selectSnByNbox(String nbox)
    {
        return cangkuMapper.selectSnByNbox(nbox);
    }

    //更改内箱的外箱号
    public Integer updateWbox( String NboxId,  String WboxId)
    {
        return  cangkuMapper.updateWbox(NboxId,WboxId);
    }


    //记录新旧内箱，和旧外箱
    public  int insertCangKuRecord(CangkuRecord cangkuRecord)
    {
        return cangkuMapper.insertCangKuRecord(cangkuRecord);
    }
    //先检查是不是经过拆箱记录修改信息了
    public    CangkuRecord  selecteCangKuRecordByNewNbox(String newNbox)
    {
        return cangkuMapper.selecteCangKuRecordByNewNbox(newNbox);
    }
    //直接更改新的外箱号
    public   int updateCangKuRecordByNewWbox(String newNbox, String newWbox)
    {
        return  cangkuMapper.updateCangKuRecordByNewWbox(newNbox,newWbox);
    }
    //根据未拆箱的内箱号 来查出旧外箱号
    public  String  selectOldWboxByOldNbox(String oldNbox)
    {
        return  cangkuMapper.selectOldWboxByOldNbox(oldNbox);
    }

    //更改未拆箱的内箱号 的外箱号 进行记录

    public   int insertRecordByNotChaixiang(String oldNbox, String oldWbox, String newWbox)
    {
        return  cangkuMapper.insertRecordByNotChaixiang(oldNbox,oldWbox,newWbox);
    }
}
