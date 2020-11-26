package com.hrms.controller;

import com.hrms.bean.Baozhuang;
import com.hrms.service.ScanSNPrintService;
import com.hrms.util.JsonMsg;
import com.hrms.util.ZplPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/changesn")
public class ScanSNPrintController {
    @Autowired
    ScanSNPrintService scanSNPrintService;

    @RequestMapping(value = "/scansnprint")
    public String scansnprint(){
        return "ScanSNPrint";
    }

    @RequestMapping(value = "/getmoremain",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getmoremain(String moid){
        int count=scanSNPrintService.getmoremain(moid);
        return JsonMsg.success().addInfo("c", count);
    }

    @RequestMapping(value = "/add_changesn",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg add_chandesn(Baozhuang baozhuang){
        int res=scanSNPrintService.add_chandesn(baozhuang);
        if(res>0){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }

    @RequestMapping(value = "/printsn")
    @ResponseBody
    public JsonMsg print_sn(String model,String productsn,String fw){
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：19)");

        p.setzhuanyin(fw,model,productsn);

        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return JsonMsg.success();
    }

    @RequestMapping(value = "/printsn_M2")
    @ResponseBody
    public JsonMsg print_sn_M2(String model,String productsn,String fw,String capacity,String productwwn){
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：19)");

        p.M2Simple(fw,"M.2 SATA SSD "+capacity+"GB 6Gb/s 3.3V 0.7A",model, productsn,productwwn);

        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return JsonMsg.success();
    }


}
