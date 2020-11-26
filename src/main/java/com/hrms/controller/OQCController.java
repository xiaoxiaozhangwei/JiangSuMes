package com.hrms.controller;

import com.hrms.bean.Check;
import com.hrms.service.OQCService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/OQC")
public class OQCController {
    @Autowired
    OQCService oqcService;

    @RequestMapping(value = "/check")
    public String check(){
        return "OQC";
    }

    @RequestMapping(value = "/insert1",method = RequestMethod.POST)
    public void insercheck1(Check check){
        oqcService.insetcheck1(check);
    }

    @RequestMapping(value = "/insert2",method = RequestMethod.POST)
    public void insercheck2(Check check){
        oqcService.insetcheck2(check);
    }

    @RequestMapping(value = "/selectProByName/{productionSN}",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg production2(@PathVariable("productionSN") String productionSN){
        Integer res=oqcService.selectProByName(productionSN);
        if (res==null){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }

    @RequestMapping(value = "/OQC_ChangeSN")
    public String OQC_ChangeSN(){
        return "OQC_ChangeSN";
    }

}
