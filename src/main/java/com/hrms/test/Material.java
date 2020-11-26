package com.hrms.test;

import com.hrms.bean.MaterialStorage;
import com.hrms.mapper.MaterialShipmentMapper;
import com.hrms.mapper.MaterialStorageMapper;
import com.hrms.service.MaterialStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class Material {
    @Autowired
    private MaterialStorageService materialStorageService;
    @Autowired
    private MaterialStorageMapper materialStorageMapper;

    @Autowired
    private MaterialShipmentMapper materialShipmentMapper;


    @Test
    public void intsert()
    {
      // Storage storage = new Storage("123456789", "P600", "GSXESEEEewrere", "XSN123", "XSW001", "沈然","1");
      // int i= storageMapper.insertStorage(storage);
      // System.out.println(i);
     List<MaterialStorage> materialStorage =materialStorageService.selectMaterialStorageByTime("100");
        System.out.println(materialStorage);
    }

    /*@Test
    public void intsert1()
    {
        // Storage storage = new Storage("123456789", "P600", "GSXESEEEewrere", "XSN123", "XSW001", "沈然","1");
        // int i= storageMapper.insertStorage(storage);
        // System.out.println(i);
        MaterialShipment materialShipment=new MaterialShipment("100",50,"test2");
        int i =materialStorageService.updateStorage(100,"E1","C5");
        System.out.println(i);
    }*/

}
