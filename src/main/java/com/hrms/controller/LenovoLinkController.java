package com.hrms.controller;

import com.hrms.bean.Zuzhuang;
import com.hrms.service.LenovoLinkService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/lenovolink")
public class LenovoLinkController {
    @Autowired
    LenovoLinkService lenovoLinkService;

    @RequestMapping(value = "/insertlenovolink",method = RequestMethod.POST)
    public void insertlenovolink(Zuzhuang zuzhuang){

        System.out.println(zuzhuang);
        lenovoLinkService.insertlenovolink(zuzhuang);

    }

    @RequestMapping(value = "/selectProductsnExist",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectProductsnExist(String productionSN){


        List<Zuzhuang> list=lenovoLinkService.selectProductsnExist(productionSN);

        if(list.size()==0){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }

    @RequestMapping(value = "/selectLenovosnExist",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectLenovosnExist(String lenovoSN){
        List<Zuzhuang> list=lenovoLinkService.selectLenovosnExist(lenovoSN);
        if(list.size()==0){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }
}

