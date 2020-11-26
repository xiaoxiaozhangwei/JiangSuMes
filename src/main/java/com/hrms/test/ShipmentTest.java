package com.hrms.test;


import com.hrms.bean.MaterialShipment;
import com.hrms.bean.Shipment;
import com.hrms.mapper.ShipmentMapper;
import com.hrms.service.MaterialShipmentService;
import com.hrms.service.ShipmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class ShipmentTest {
    @Autowired
    ShipmentMapper shipmentMapper;
    @Autowired
    ShipmentService  shipmentService;
    @Autowired
    MaterialShipmentService materialShipmentService;
    @Autowired

    @Test
    public void insertMS(){
        MaterialShipment materialShipment = new MaterialShipment();
        int result = materialShipmentService.insertShipment(materialShipment);
        System.out.println(result);
    }

    @Test
    public void  insert()
    {
        Shipment shipment=new Shipment("XSW2222",
                240,
                "2.5 SSD",
                "李四",
                "zhasgsn",
                "1232354",
                "南京达到",
                "1000126",
                "2334",
                "oa1111",
                "97524456",
                "无");
        int i=shipmentService.insertShipment(shipment);
        System.out.println(i);
    }
    @Test
    public void  insert2()
    {

        List<String> list= Arrays.asList("XSW2010140016","XSW2010140031");
                List<String> l=  shipmentMapper.selectShipmentSN(list);
        System.out.println(l);
    }


}
