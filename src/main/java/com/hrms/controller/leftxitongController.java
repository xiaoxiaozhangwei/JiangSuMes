package com.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/xt")
public class leftxitongController {


    /**
     * 跳转到组装页面
     * @return
     */
    @RequestMapping(value = "/zuzhuang", method = RequestMethod.GET)
    public String zuzhuang(){
        return "Zuzhuang";
    }
    /**
     * 跳转到内包装页面
     * @return
     */
    @RequestMapping(value = "/Nbaozhuang", method = RequestMethod.GET)
    public String nbaozhuang(){
        return "Nbaozhuang";
    }
    /**
     * 跳转到内包装重工页面
     * @return
     */
    @RequestMapping(value = "/NboxChonggong", method = RequestMethod.GET)
    public String NboxChonggong(){
        return "NboxChonggong";
    }
    /**
     * 跳转到外包装页面
     * @return
     */
    @RequestMapping(value = "/Wbaozhuang", method = RequestMethod.GET)
    public String wbaozhuang(){
        return "Wbaozhuang";
    }

    @RequestMapping(value = "/Wbaozhuang2", method = RequestMethod.GET)
    public String wbaozhuang2(){
        return "Wbaozhuang2";
    }

    @RequestMapping(value = "/check")
    public String check(){
        return "CheckPage";
    }


    @RequestMapping(value = "/ZuzhuangWork", method = RequestMethod.GET)
    public String ZuzhuangWork(){
        return "ZuzhuangWork";
    }

    @RequestMapping(value = "/Zuzhuang",method = RequestMethod.GET)
    public ModelAndView Zuzhuang() {
        //System.out.println("1");
        ModelAndView mv =new ModelAndView("Zuzhuang");
        //mv.addObject("mo","12344");
        //System.out.println(mo);
        //modelAndView.setViewName("zuzhuang2");
        return mv;
    }
}
