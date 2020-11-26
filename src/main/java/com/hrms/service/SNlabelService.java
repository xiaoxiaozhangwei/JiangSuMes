package com.hrms.service;

import com.hrms.bean.SNlabel;
import com.hrms.mapper.SNlabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SNlabelService {
    @Autowired
    SNlabelMapper sNlabelMapper;

    public Integer insertlabel(SNlabel sNlabel){
        return sNlabelMapper.insertlabel(sNlabel);
    }

    public Integer insertsnrule(SNlabel sNlabel){
        return sNlabelMapper.insertsnrule(sNlabel);
    }
    public Integer insertwwnrule(SNlabel sNlabel){
        return sNlabelMapper.insertwwnrule(sNlabel);
    }

    public List<SNlabel> selectsnrule(){
        return sNlabelMapper.selectsnrule();
    }
    public List<SNlabel> selectwwnrule(){
        return sNlabelMapper.selectwwnrule();
    }

    public List<SNlabel> selectsnrulebyname(String snrulename){
        return sNlabelMapper.selectsnrulebyname(snrulename);
    }
    public List<SNlabel> selectwwnrulebyname(String wwnrulename){
        return sNlabelMapper.selectwwnrulebyname(wwnrulename);
    }

    public Integer selectsnbytoday(String snrulename){
        return sNlabelMapper.selectsnbytoday(snrulename);
    }
    public Integer selectsnbyweek(String snrulename){
        return sNlabelMapper.selectsnbyweek(snrulename);
    }
    public Integer selectsnbymonth(String snrulename){
        return sNlabelMapper.selectsnbymonth(snrulename);
    }

    public Integer selectwwnbytoday(String wwnrulename){
        return sNlabelMapper.selectwwnbytoday(wwnrulename);
    }
    public Integer selectwwnbyweek(String wwnrulename){
        return sNlabelMapper.selectwwnbyweek(wwnrulename);
    }
    public String selectwwnbymonth(String wwnrulename){
        return sNlabelMapper.selectwwnbymonth(wwnrulename);
    }

    public Integer update_moremain(String moId){
        return sNlabelMapper.update_moremain(moId);
    }
}
