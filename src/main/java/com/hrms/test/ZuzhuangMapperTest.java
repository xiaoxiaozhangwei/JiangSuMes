package com.hrms.test;


import com.hrms.bean.Zuzhuang;
import com.hrms.bean.Zuzhuangworklog;
import com.hrms.mapper.GongdanMapper;
import com.hrms.mapper.ZuzhuangMapper;
import com.hrms.service.ZuzhuangService;
import com.hrms.service.ZuzhuangworklogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class ZuzhuangMapperTest {
    @Autowired
    ZuzhuangMapper zuzhuangMapper;
    @Autowired
    GongdanMapper gongdanMapper;
    @Autowired
    ZuzhuangService zuzhuangService;
    @Autowired
    ZuzhuangworklogService zuzhuangworklogService;
    /**
     * 单条记录插入
     */
    @Test
    public void insertOneTest(){
        for (int i=1;i<=230;i++){
            Zuzhuang zuzhuang = new Zuzhuang(1,"","","正确",3,"a","2020-06-28 13:46:09",1,"T600-X072006220001","a",100,99,"","","2");
            int res = zuzhuangService.addlink(zuzhuang);
        }

        //System.out.println(res);
    }

    @Test
    public void check()
    {
        Zuzhuangworklog zuzhuangworklog=new Zuzhuangworklog("111","111","111","111","111","111","111","111","111","111","111","111","111");
        int i = zuzhuangworklogService.add(zuzhuangworklog);
    }
   /* @Test
    public  void selectProByName()
    {
        Zuzhuang zuzhuang=zuzhuangMapper.selectProByName("193601234444444" );
        System.out.println(zuzhuang);
    }*/


    @Test
    public void selectPcba(){
        Zuzhuang zuzhuang =zuzhuangService.getPcba("200426000401027","R600-X072008260003");
        System.out.println(zuzhuang);
    }

}
