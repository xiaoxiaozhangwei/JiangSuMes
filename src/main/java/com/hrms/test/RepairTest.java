package com.hrms.test;

import com.hrms.bean.Repair;
import com.hrms.mapper.RepairMapper;
import com.hrms.service.RepairService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class RepairTest {
    @Autowired
    RepairService repairService;
    @Autowired
    RepairMapper repairMapper;

    @Test
    public void selectfwbyssd(){
        String res=repairService.selectfwbyssd("201712120G0008929");
        System.out.println(res);
    }

    @Test
    public void saverepairmesg(){
        Repair repair=new Repair("1", "1", "2020-06-23 7:00:00", "a", "s", "d", "z", "x", "c","q","","","","","","","");
        System.out.println(repair);
        System.out.println(repairService.saverepairmesg(repair));
    }

    @Test
    public void showrepairmesg(){
        List<Repair> repairs=repairMapper.showrepairmesg("201712120G0008929", "194802128G4003250");
        System.out.println(repairs);
    }

    @Test
    public void updaterepairmesg(){
        Repair repair=new Repair("201712120G0008929", "194802128G4003250", "2020-06-23 7:00:00", "a", "s", "d", "z", "x", "c","q","","","","","","","");
        System.out.println(repair);
        System.out.println(repairService.updaterepairmesg(repair));
    }

    @Test
    public void selectOC1(){
        List<Repair> list=repairMapper.selectOC1("12","23");
        System.out.println(list);
    }

    @Test
    public void outbound(){
        System.out.println(repairMapper.outbound("", "111111", "a"));
    }
}
