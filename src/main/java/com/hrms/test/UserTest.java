package com.hrms.test;

import com.hrms.bean.User;
import com.hrms.mapper.UserMapper;
import com.hrms.service.BaozhuangService;
import com.hrms.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class UserTest {
    @Autowired
    UserMapper userMapper;
    @Autowired
    BaozhuangService baozhuangService;
    @Autowired
    UserService userService;


    @Test
    public  void selectAll1()
    {

        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date date=cal.getTime();
        int m=date.getMonth();

      System.out.println(m);
    }
/*
    @Test
    public  void delete()
    { int res=userMapper.deleteUser("a");
        System.out.println(res);

    }

    @Test
    public void insert()
    { User user=new User("xit34","err","4564656");
        int res=userMapper.insertUser(user);
        System.out.println(res);
    }


    @Test
    public void update()
    { User user=new User("xittttttt","shen","123456");
        int res=userMapper.updateUser("xittttttt",user);
        System.out.println(res);


    }
    @Test
    public void check()
    { User user=userMapper.selectUser("Xit02333");
        System.out.println(user);


    }
     @Test
    public void printN() {
        //二维码内的信息
        String info = "";
        List<Baozhuang> list = baozhuangService.packSN("XSN2006290048");
        for (int i = 0; i < list.size(); i++) {
            info += list.get(i).getProductionSN() + "\\0D";
        }

        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:20)");
        test1(p, info, "XSN2006290048");
        p.resetZpl();

    }
    //内标签模板
    private static boolean test1(ZplPrinter p, String a, String b) {

        String bar0 = a;

        String bar0Zpl = "^BY450,480^FT250,540^BXN,4,200,0,0,1,~\r\n" +
                "^FH\\^FD${data}^FS\r\n";
        ;
        p.setBarcode(bar0, bar0Zpl);
        String barString = b;
        p.setChar1(b,268,630,75,26);
        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }
    */
@Test
public  void selectAll()
{

    User use=userMapper.queryUserByUserName("shenran");
    System.out.println(use);
}
}
