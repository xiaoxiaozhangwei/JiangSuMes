package com.hrms.test;

import com.hrms.bean.Storage;
import com.hrms.mapper.StorageMapper;
import com.hrms.service.StorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class StorageTest {
    @Autowired
    private StorageMapper storageMapper;
    @Autowired
    private StorageService storageService;




    @Test
    public void intsert()
    {
      // Storage storage = new Storage("123456789", "P600", "GSXESEEEewrere", "XSN123", "XSW001", "沈然","1");
      // int i= storageMapper.insertStorage(storage);
      // System.out.println(i);
        String gongMo=storageService.selectMoByStorageMo("1111");
        System.out.println(gongMo);
    }

    @Test
    public void selectsnByMo(){
        String mo_id = "P600-X072010160005";
        String d = "2020-10-22";
        List<Storage> storages=storageService.selectsnByMo(mo_id, d);
        System.out.println(storages);
    }
}
