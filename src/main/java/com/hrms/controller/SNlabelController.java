package com.hrms.controller;

import com.hrms.bean.SNlabel;
import com.hrms.service.SNlabelService;
import com.hrms.util.JsonMsg;
import com.hrms.util.ZplPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/label")
public class SNlabelController {
    @Autowired
    SNlabelService sNlabelService;

    @RequestMapping(value = "/label")
    public String label(){
        return "SNlabel";
    }

    @RequestMapping(value = "/insertlabel" ,method = RequestMethod.POST)
    @ResponseBody
    /*
    public JsonMsg insertlabel(String productsn,String wwn,String snrule,String wwnrule,String model,String moid,String operator){
        SNlabel sNlabel=new SNlabel(moid,productsn,wwn,model,operator,snrule,1,"",1,1,"","",wwnrule,1,1,1,"","");
        int res =sNlabelService.insertlabel(sNlabel);
        if (res>0){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }*/
    public JsonMsg insertlabel(SNlabel sNlabel) {
        //   System.out.println("sn标签："+ sNlabel);
        int res = sNlabelService.insertlabel(sNlabel);
        //  System.out.println(res);
        if (res > 0) {

            return JsonMsg.success();
        } else {
            return JsonMsg.fail();
        }
    }

    @RequestMapping(value = "/snprint")
    public String SNprint(){
        return "SNprint";
    }

    //添加SN规则
    @RequestMapping(value = "/insertsnrule",method = RequestMethod.POST)
    public void insertsnrule(String snrulename, Integer snlength,String erp,Integer start,Integer over,String rule,String mesg){
        //System.out.println("1");
        SNlabel sNlabel =new SNlabel("1","1","1","1","1",snrulename,snlength,erp,start,over, rule,mesg,"",1,1,1,"","");
        sNlabelService.insertsnrule(sNlabel);
        //System.out.println("2");
    }

    //添加WWN规则
    @RequestMapping(value = "/insertwwnrule",method = RequestMethod.POST)
    public void insertwwnrule(String wwnrulename, Integer wwnlength,Integer start,Integer over,String rule,String mesg){
        //System.out.println("1");
        SNlabel sNlabel =new SNlabel("1","1","1","1","1","snrulename",1,"erp",start,over, rule,mesg,wwnrulename,wwnlength,start,over,rule,mesg);
        //System.out.println(sNlabel);
        sNlabelService.insertwwnrule(sNlabel);
        //System.out.println("2");
    }

    //跳转到SN规则界面并显示已有SN规则
    @RequestMapping(value = "/selectsnrule",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView selectsnrule(){
        ModelAndView mv=new ModelAndView("SNlabel");
        List<SNlabel> snrules=sNlabelService.selectsnrule();
        mv.addObject("snrules",snrules);
        return mv;
    }
    //跳转到WWN规则界面并显示已有WWN规则
    @RequestMapping(value = "/selectwwnrule",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView selectwwnrule(){
        ModelAndView mv=new ModelAndView("WWNrule");
        List<SNlabel> wwnrules=sNlabelService.selectwwnrule();
        mv.addObject("wwnrules",wwnrules);
        return mv;
    }

    //根据SN规则名称查询SN规则详细信息
    @RequestMapping(value = "/selectsnrulebyname/{snrulename}",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectsnrulebyname(@PathVariable String snrulename){
        List<SNlabel> rules=sNlabelService.selectsnrulebyname(snrulename);
        if (rules.size()>0){
            return JsonMsg.success().addInfo("rules",rules);
        }else {
            return JsonMsg.fail();
        }
    }

    //根据SN规则名称查询SN规则详细信息
    @RequestMapping(value = "/selectwwnrulebyname/{wwnrulename}",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectwwnrulebyname(@PathVariable String wwnrulename){
        List<SNlabel> rules=sNlabelService.selectwwnrulebyname(wwnrulename);
        if (rules.size()>0){
            return JsonMsg.success().addInfo("rules",rules);
        }else {
            return JsonMsg.fail();
        }
    }

    //查询所有SN规则名称
    @RequestMapping(value = "/snrules",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg snrules(){
        List<SNlabel> snrules=sNlabelService.selectsnrule();
        if (snrules.size()>0){
            return JsonMsg.success().addInfo("snrules",snrules);
        }else{
            return JsonMsg.fail();
        }
    }

    //查询所有WWN规则名称
    @RequestMapping(value = "/wwnrules",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg wwnrules(){
        List<SNlabel> wwnrules=sNlabelService.selectwwnrule();
        if (wwnrules.size()>0){
            return JsonMsg.success().addInfo("wwnrules",wwnrules);
        }else{
            return JsonMsg.fail();
        }
    }

    //根据SN查询日结、月结、周结数量
    @RequestMapping(value = "/selectsncount/{snrulename}",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectsncount(@PathVariable String snrulename){
        int d =sNlabelService.selectsnbytoday(snrulename);
        int w =sNlabelService.selectsnbyweek(snrulename);
        int m =sNlabelService.selectsnbymonth(snrulename);
        //System.out.println(d);
        if (d>=0) {
            //System.out.println("测试开始");
            return JsonMsg.success().addInfo("d",d)
                    .addInfo("w",w)
                    .addInfo("m",m);

        }else {
            return JsonMsg.fail();
        }
    }

    //根据WWN查询日结、月结、周结数量
    @RequestMapping(value = "/selectwwncount/{wwnrulename}",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectwwncount(@PathVariable String wwnrulename){
        int d =sNlabelService.selectwwnbytoday(wwnrulename);
        int w =sNlabelService.selectwwnbyweek(wwnrulename);
        String m =sNlabelService.selectwwnbymonth(wwnrulename);
        //System.out.println(d);
        if (d>=0) {
            //System.out.println("测试开始");
            return JsonMsg.success().addInfo("d",d)
                    .addInfo("w",w)
                    .addInfo("m",Integer.valueOf(m.substring(9)).intValue()+1);

        }else {
            return JsonMsg.fail();
        }
    }

    @RequestMapping(value = "/muban2/{model}/{productsn}/{capacity}")
    @ResponseBody
    public JsonMsg muban2(@PathVariable String model, @PathVariable String productsn, @PathVariable String capacity){
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:20)");
        p.setSN2("2.5\" SATA SSD "+capacity+"GB 6Gb/s 5V 0.6A", model, productsn);
        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        if (result==true){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }


    /*打印sn 无fw*/
    @RequestMapping(value = "/printSN/{model}/{productsn}/{capacity}")
    @ResponseBody
    public JsonMsg printSN(@PathVariable String model, @PathVariable String productsn, @PathVariable String capacity){
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:20)");
        p.setSNmuban("2.5\" SATA SSD "+capacity+"GB 6Gb/s 5V 0.6A", model, productsn);
        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        if (result==true){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }
//M.2单个标签
    @RequestMapping(value = "/M2one/{model}/{productsn}/{productwwn}/{capacity}/{fw}")
    @ResponseBody
    public JsonMsg muban1(@PathVariable String model, @PathVariable String productsn, @PathVariable String capacity, @PathVariable String productwwn, @PathVariable String fw){
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:20)");
      //  p.setWWN( fw,"M.2 SATA SSD 240GB 6Gb/s 3.3V 0.7A",model, productsn,productwwn);
        p.M2Simple(fw,"M.2 SATA SSD "+capacity+"GB 6Gb/s 3.3V 0.7A",model, productsn,productwwn);
        String zpl = p.getZpl();
        // System.out.println(zpl);
        boolean result = p.print(zpl);
        if (result==true){
            //  Integer a=1;
            //  if (a==1){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }

    @RequestMapping(value = "/snprint2")
    public String SNprint2(){
        return "M.2SNprint";
    }

    @RequestMapping(value = "/insertlabel2" ,method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insertlabel2(String productsn,String wwn,String productsn2,String wwn2,String snrule,String wwnrule,String model,String moid,String operator){
        SNlabel sNlabel=new SNlabel(moid,productsn,wwn,model,operator,snrule,1,"",1,1,"","",wwnrule,1,1,1,"","");
        SNlabel sNlabel2=new SNlabel(moid,productsn2,wwn2,model,operator,snrule,1,"",1,1,"","",wwnrule,1,1,1,"","");
        int res =sNlabelService.insertlabel(sNlabel);
        sNlabelService.insertlabel(sNlabel2);
        if (res>0){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }
    //M.2双标签
    @RequestMapping(value = "/M2two/{model}/{productsn}/{productwwn}/{capacity}/{fw}/{productsn2}/{productwwn2}")
    @ResponseBody
    public JsonMsg muban12(@PathVariable String model, @PathVariable String productsn, @PathVariable String capacity, @PathVariable String productwwn, @PathVariable String fw, @PathVariable String productsn2, @PathVariable String productwwn2){
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:20)");
        p.setM2WWN(fw,"M.2 SATA "+capacity+"GB 6Gb/s",model,productsn,productwwn,productsn2,productwwn2);
        String zpl = p.getZpl();
        // System.out.println(zpl);
        boolean result = p.print(zpl);
        if (result==true){
            //  Integer a=1;
            //  if (a==1){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }

    @RequestMapping(value = "/updatemoremain/{moId}",method = RequestMethod.PUT )
    public void update_moremain(@PathVariable String moId){
         sNlabelService.update_moremain(moId);
    }


    @RequestMapping(value = "/PrintSN/{model}/{productsn}/{describe:.+}/{snmuban}/{fw}")
    @ResponseBody
    public JsonMsg PrintSN(@PathVariable String model, @PathVariable String productsn, @PathVariable String describe, @PathVariable String snmuban, @PathVariable String fw) {


        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:16)");
        System.out.println(snmuban);
        if (snmuban.equals("模板1")) {
            p.printmuban1(describe, model, productsn);
        } else if (snmuban.equals("模板2")) {
            p.printmuban2(describe, model, productsn);
        }
        else if (snmuban.equals("模板4"))
        {
            p.printmuban_M2_FW(describe,fw,model,productsn);
        }
        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        if (result == true) {
            return JsonMsg.success();
        } else {
            return JsonMsg.fail();
        }
    }

}
