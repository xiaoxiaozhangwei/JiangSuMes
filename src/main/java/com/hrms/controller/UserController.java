package com.hrms.controller;


import com.hrms.bean.User;
import com.hrms.service.UserService;
import com.hrms.util.JsonMsg;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {


    @Autowired
    UserService userService;


    @RequestMapping(value = "/userAll", method = RequestMethod.GET)
    public ModelAndView userAll() {
        ModelAndView mv = new ModelAndView("userPage");
        List<User> users = userService.selectAllUser();
        //将上述查询结果放到Model中，在JSP页面中可以进行展示
        mv.addObject("users", users);
        return mv;
    }


    @RequestMapping(value = "/deleteEmp/{user}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg deleteEmp(@PathVariable("user") String name) {
        int res = 0;
        res = userService.deleteUser(name);
        if (res != 1) {
            return JsonMsg.fail().addInfo("emp_del_error", "员工删除异常");
        } else {
            return JsonMsg.success();
        }
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg addUser(User user) {
        Md5Hash password= new Md5Hash(user.getPwd(),user.getName(),2);
        user.setPwd(password.toString());
        int res = userService.insertUser(user);
        if (res == 1) {
            return JsonMsg.success();
        } else {
            return JsonMsg.fail();
        }
    }


    @RequestMapping(value = "/updateUser/{updateEmpId}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updateMo(User user, @PathVariable("updateEmpId") String job_number) {
        Md5Hash password= new Md5Hash(user.getPwd(),user.getName(),2);
        user.setPwd(password.toString());
        int res = userService.updateUser(job_number, user);
        if (res == 1) {
            return JsonMsg.success();
        } else {
            return JsonMsg.fail().addInfo("emp_update_error", "更改异常");
        }

    }


    @RequestMapping(value = "/selectUser/{updateEmpId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectUser(@PathVariable("updateEmpId") String job_number) {
        User user = userService.selectUser(job_number);
        if (user != null) {
            return JsonMsg.success().addInfo("user", user);
        } else {
            return JsonMsg.fail();
        }

    }





}