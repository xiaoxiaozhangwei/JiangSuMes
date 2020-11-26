package com.hrms.test;

import com.hrms.bean.Check;
import com.hrms.bean.Gongdan;
import com.hrms.mapper.CheckMapper;
import com.hrms.service.CheckService;
import com.hrms.service.GongdanService;
import com.hrms.util.ExportExcel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
@EnableScheduling
public class CheckTest {
    @Autowired
    CheckService checkService;
    @Autowired
    GongdanService gongdanService;
    @Autowired
    CheckMapper checkMapper;
    @Test
    public void insertcheck1(){
        Check check=new Check(1,"","1","1","1","1","1","1","1","1");
        int res =checkService.insetcheck1(check);
        System.out.println(res);
    }

    @Test
    public void moid(){
        Gongdan gd=gongdanService.getMoById("12344");
        System.out.println(gd);
    }

    @Test
    public void test()
    {
        Integer res =checkMapper.selectCheckRelate("不良","线别3");
        System.out.println(res);
    }


    //目检报表
    @Test
    public void exceltest(){
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(date.getDay());
        List<String> list=checkService.selecthismonthmodel(formatter.format(date));
        for (int i=0;i<list.size();i++){
            ExportExcel.writeSpecifiedCell(5,6+i*4,0,list.get(i));
        }

        for(int j=28;j<=28;j++){
            for (int i = 0; i < list.size(); i++) {
                int r = checkService.selectrightcountbymodeldaily("2020-07-"+j, list.get(i));
                int w = checkService.selectwrongcountbymodeldaily("2020-07-"+j, list.get(i));
                ExportExcel.writeSpecifiedCellint(5, 7 + i * 4, 2 + j, r);
                ExportExcel.writeSpecifiedCellint(5, 8 + i * 4, 2 + j, w);
            }

        }

    }
}
