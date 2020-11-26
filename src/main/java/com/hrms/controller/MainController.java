package com.hrms.controller;


import com.hrms.util.JsonMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/hrms")
public class MainController {

    /**
     * 跳转到主页面
     * @return
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(){
        return "main";
    }
    /**
     * 跳转到员工添加页面
     * @return
     */
    @RequestMapping(value = "/employeeAdd", method = RequestMethod.GET)
    public String employeeAdd(){
        return "employeeAdd";
    }

    /**
     * 跳转到系统页面
     * @return
     */
    @RequestMapping(value = "/xitong", method = RequestMethod.GET)
    public String xitong(){
        return "xitong";
    }
    /**
     * 跳转到资料页面
     * @return
     */
    @RequestMapping(value = "/ziliao", method = RequestMethod.GET)
    public String ziliao(){
        return "ziliao";
    }

    /**
     跳转到权限管理页面
     * @return
     */
    @RequestMapping(value = "/manage")
    @ResponseBody
    public JsonMsg rightManage(){
       return JsonMsg.success();

    }

    @RequestMapping(value = "/kanban", method = RequestMethod.GET)
    public String kanban(){
        return "kanban";
    }

    @RequestMapping(value = "/rebarcode", method = RequestMethod.GET)
    public String rebarcode(){
        return "rebarcode";
    }

    @RequestMapping(value = "/ZuZhuangDisplayBoard",method = RequestMethod.GET)
    public String ZuZhuangDisplayBoard(){
        return "ZuZhuangDisplayBoard";
    }

}
