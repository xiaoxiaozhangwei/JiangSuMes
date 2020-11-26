package com.hrms.service;

import com.hrms.bean.Zuzhuangworklog;
import com.hrms.mapper.ZuzhuangworklogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZuzhuangworklogService {
    @Autowired
    ZuzhuangworklogMapper zuzhuangworklogMapper;


    public  int add(Zuzhuangworklog zuzhuangworklog){
        return  zuzhuangworklogMapper.add(zuzhuangworklog);
    }







}
