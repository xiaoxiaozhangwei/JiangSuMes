package com.hrms.mapper;

import com.hrms.bean.Environment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnvironmentMapper {

    Integer insert(Environment environment);

    List<Environment> selectthisweek();

    Integer SelectTodayExists();

    Integer UpdateTodayExists(Environment environment);

    Integer insertpicture(@Param("area") String area,@Param("picture") String picture, @Param("question") String question);

    List<Environment> selectallpict();

    List<Environment> AvgByWeek();

    List<Environment> AvgthisMonth();
}
