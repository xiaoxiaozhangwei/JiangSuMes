package com.hrms.controller;

import com.hrms.bean.Zuzhuangworklog;
import com.hrms.service.UserService;
import com.hrms.service.ZuzhuangworklogService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/zuzhuangwork")
public class ZuzhuangworklogController {

    @Autowired
    ZuzhuangworklogService zuzhuangworklogService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg deleteEmp(Zuzhuangworklog zuzhuangworklog) {
        System.out.println(zuzhuangworklog.getMo()+"1");
        int res = 0;
        res = zuzhuangworklogService.add(zuzhuangworklog);
        if (res != 1) {
            return JsonMsg.fail().addInfo("emp_add_error", "信息添加异常");
        } else {
            return JsonMsg.success();
        }
    }





    @RequestMapping(value = "/selectName", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectName(String mo,String line,String A01,String A01_2,String A02,String A02_2,String A03,String A03_2,String A04,String A04_2,String A05,String FW, HttpServletRequest request) {
      //  System.out.println("组装作业-----------");
        HttpSession session = request.getSession();
        session.setAttribute("mo",mo);
       // System.out.println(mo);
        //System.out.println(A01 +" "+A05);

        String name1 = userService.selectName(A01);
        String name2 = userService.selectName(A01_2);
        String name3 = userService.selectName(A02);
        String name4 = userService.selectName(A02_2);
        String name5 = userService.selectName(A03);
        String name6 = userService.selectName(A03_2);
        String name7 = userService.selectName(A04);
        String name8 = userService.selectName(A04_2);
        String name9= userService.selectName(A05);


        // System.out.println(name1);
        Zuzhuangworklog zuzhuangworklog= new Zuzhuangworklog(mo,line,name1,name2,name3,name4,name5,name6,name7,name8,name9,FW);
       // Zuzhuangworklog zuzhuangworklog= new Zuzhuangworklog( mo, line, A01, A01_2, A02, A02_2, A03, A03_2, A04, A04_2, A05, FW);
      //  System.out.println(zuzhuangworklog);
        int res = 0;
        res = zuzhuangworklogService.add(zuzhuangworklog);
       // System.out.println("组装作业："+res);
        if (res != 1) {
            return JsonMsg.fail().addInfo("emp_add_error", "信息添加异常");
        } else {
            return JsonMsg.success();
        }
    }


}
