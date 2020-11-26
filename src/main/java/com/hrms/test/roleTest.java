package com.hrms.test;

import com.hrms.bean.Permission;
import com.hrms.bean.Role;
import com.hrms.mapper.PermissionMapper;
import com.hrms.mapper.RoleMapper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class roleTest {

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Test
    public void ceshi()
    {
        List<Role> roles = roleMapper.queryRolesByUserName("沈然");
        System.out.println(roles);

    }


    @Test
    public void set()
    {
        List<Permission> permissions=permissionMapper.queryPermissionsByUserName("李四");
        System.out.println(permissions);
    }
    @Test
    public void mima()
    {
        Md5Hash md5Hash= new Md5Hash("123456","潘倩",2);
        System.out.println(md5Hash);
    }

    @Test
    public void testTime() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("Tue Nov 10 2020 18:35:46 GMT+0800 (中国标准时间)");
        String now = new SimpleDateFormat("yyyy年MM月dd日").format(date);
        System.out.println(date);
        System.out.println(now);
    }
}
