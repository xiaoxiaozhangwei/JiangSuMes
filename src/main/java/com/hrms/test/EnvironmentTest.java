package com.hrms.test;

import com.hrms.bean.Environment;
import com.hrms.mapper.EnvironmentMapper;
import com.hrms.service.EnvironmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class EnvironmentTest {
    @Autowired
    EnvironmentService environmentService;
    @Autowired
    EnvironmentMapper environmentMapper;



    @Test
    public void selectthisweek(){
        List<Environment> list=environmentMapper.selectthisweek();
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void insertpicture(){
        environmentMapper.insertpicture("as","vufggvgvggggygbghgtfvhghbjh","gghgdfcgi");
    }

    @Test
    public void selectallpict(){
        List<Environment> list=environmentMapper.selectallpict();
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
            //.out.println(list);

    }
}
