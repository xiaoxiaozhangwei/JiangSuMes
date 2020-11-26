package com.hrms.mapper;

import com.hrms.bean.SNlabel;

import java.util.List;

public interface SNlabelMapper {

    Integer insertlabel(SNlabel sNlabel);

    Integer insertsnrule(SNlabel sNlabel);
    Integer insertwwnrule(SNlabel sNlabel);

    List <SNlabel> selectsnrule();
    List <SNlabel> selectwwnrule();

    List<SNlabel> selectsnrulebyname(String snrulename);
    List<SNlabel> selectwwnrulebyname(String wwnrulename);


    Integer selectsnbytoday(String snrulename);
    Integer selectsnbyweek(String snrulename);
    Integer selectsnbymonth(String snrulename);

    Integer selectwwnbytoday(String wwnrulename);
    Integer selectwwnbyweek(String wwnrulename);
    String selectwwnbymonth(String wwnrulename);

    Integer update_moremain(String moId);
}
