package com.hrms.controller;

import com.hrms.bean.User;
import com.hrms.service.UserService;
import com.hrms.util.ActiveUser;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;

/**
 * @author GenshenWang.nomico
 * @date 2018/3/9.
 */
@Controller
@RequestMapping(value = "/user")
public class LoginController {
    @Autowired
    UserService userService;

    /**
     * 登录：跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 对登录页面输入的用户名和密码做简单的判断
     * @param
     * @return

    @RequestMapping("login")
    public String login(@Param("name")String name, @Param("password")String password, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user=userService.checkUser(name, password);
        if(name==null||"".equals(name)){  //没有输入姓名
            model.addAttribute("msg1","请输入姓名");
        }else if(user==null||"".trim().equals(user)){ //输入姓名但是姓名错误
            model.addAttribute("msg1","账户不存在，请先注册");
            return "login";
        }else if(user!=null &(password==null||"".equals(password))){ //姓名正确，但没有输入密码
            model.addAttribute("msg2","请输入密码");
        }else if(user!=null &!(user.getPwd().equals(password))) { //姓名正确，输入密码，但是密码错误
            model.addAttribute("msg2","密码错误");

        }else if(user!=null &user.getPwd().equals(password)){ //姓名密码均正确
            session.setAttribute("name",name);
            return "redirect:/user/main";
        }
        return "login";
    }
*/




    @RequestMapping("/login")
    public String login(@Param("name")String name, @Param("password")String password, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext application = request.getServletContext();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, password);
        User user = userService.queryUserByUserName(name);

        try {
            subject.login(usernamePasswordToken);
            ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
            if (activeUser.getUser().getLock() == 1&&!checkLock(application,activeUser.getUser().getName())) {
                model.addAttribute("msg1", "该账号已被锁定");
            } else
            {
                session.setAttribute("name", user.getName());
                cleanFailNum(application, user.getName());
                return "redirect:/user/main";
            }
        } catch (IncorrectCredentialsException e)
        {
            System.out.println("密码不正确");
            if (!checkLock(application, user.getName()) && user.getLock() == 1)
            {

                model.addAttribute("msg1", "该账号已被锁定");
            }
            else {
                model.addAttribute("msg2", "密码错误");
                addFailNum(application, user.getName());

            }

        } catch (UnknownAccountException e)
        {
            model.addAttribute("msg1", "账户不存在，请先注册");

        }
        return "login";

    }


    //校验用户登录失败次数
    public boolean checkLock(ServletContext application,String username) {
        Object o = application.getAttribute(username);
        if(o==null) {
            return true;
        }
        HashMap<String,Object> map  = (HashMap<String, Object>) o;
        int num  = (int) map.get("num");
        //Date date = (Date) map.get("lastDate");
        // long timeDifference = ((new Date().getTime()-date.getTime())/60/1000);
      /*  if(num>=5&&timeDifference<30) {
            userService.lockUser(username);
            return false;
        }*/
        if(num>=5)
        {
            userService.lockUser(username);
            return false;
        }
        return true;
    }

    // 新增用户登录失败次数
    public void addFailNum(ServletContext application, String username) {
        Object o =application.getAttribute(username);
        HashMap<String,Object> map = null;
        int num= 0;
        if(o==null) {
            map = new HashMap<String,Object>();
        }else {
            map  = (HashMap<String, Object>) o;
            num  = (int) map.get("num");
            Date date = (Date) map.get("lastDate");
            long timeDifference = ((new Date().getTime()-date.getTime())/60/1000);
            if(timeDifference>=30) {
                num=0;
            }
        }
        map.put("num", num+1);
        map.put("lastDate", new Date());
        application.setAttribute(username, map);
    }

    // 清理用户登录失败的记录
    public void cleanFailNum(ServletContext application, String username) {
        application.removeAttribute(username);
    }





    /**
     * 跳转到主页面
     * @return
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(){
        return "main";
    }

    /**
     * 退出登录：从主页面跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){ return  "login"; }

    /**
     * 跳转到test页面
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String tset(){
        return "test";
    }




}
