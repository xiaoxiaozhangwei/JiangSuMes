package com.hrms.service;

import com.hrms.bean.Environment;
import com.hrms.mapper.EnvironmentMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironmentService {
    @Autowired
    EnvironmentMapper environmentMapper;

    public Integer insert(Environment environment){
        return environmentMapper.insert(environment);
    }

    public List<Environment> selectthisweek(){
        return environmentMapper.selectthisweek();
    }

    public Integer SelectTodayExists(){
        return environmentMapper.SelectTodayExists();
    }

    public Integer UpdateTodayExists(Environment environment){
        return environmentMapper.UpdateTodayExists(environment);
    }

    public Integer insertquestion(@Param("area") String area, @Param("picture") String picture, @Param("question") String question){
        return environmentMapper.insertpicture(area, picture, question);
    }

    public List<Environment> selectallpict(){
        return environmentMapper.selectallpict();
    }

    public List<Environment> AvgByWeek(){
        return environmentMapper.AvgByWeek();
    }

    public List<Environment> AvgthisMonth(){
        return environmentMapper.AvgthisMonth();
    }
}
