package com.hrms.controller;

import com.hrms.bean.Baozhuang;
import com.hrms.bean.Gongdan;
import com.hrms.service.BaozhuangService;
import com.hrms.service.GongdanService;
import com.hrms.util.JsonMsg;
import com.hrms.util.ZplPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

@Controller
@EnableScheduling
@RequestMapping(value = "/pack")
public class BaozhuangController {
    @Autowired
    BaozhuangService baozhuangService;
    @Autowired
    GongdanService gongdanService;


    ZplPrinter p;

    @RequestMapping(value = "/BaozhuangPage")
    public String BaozhuangPage( ) {
        return "baozhuangpage";
    }

    //添加到数据库
    @RequestMapping(value = "/insertpack", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insertpack(Baozhuang baozhuang) {
        Integer res=baozhuangService.insertpack(baozhuang);
        if (res==1){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }


    //判断SN号与model号是否匹配,主要判断是否经过目检站。
    @RequestMapping(value = "/production/{productionSN}/{model}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg production(@PathVariable("productionSN") String productionSN, @PathVariable("model") String model) {
        Integer res = baozhuangService.production(productionSN, model);
        if (res != 0) {
            return JsonMsg.success();
        } else {
            return JsonMsg.fail();
        }

    }


    //根据工单号查询工单信息
    @RequestMapping(value = "/getMoId/{moid}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getMoById(@PathVariable("moid") String moId) {
        Gongdan gongdan = gongdanService.getMoById0(moId);
        if (gongdan != null) {
            return JsonMsg.success().addInfo("gongdan", gongdan);
        } else {
            return JsonMsg.fail();
        }
    }


    //工单剩余量减1
    @RequestMapping(value = "/merman/{moId}", method = RequestMethod.PUT)
    public void update_moremain0(@PathVariable("moId") String moId) {
        gongdanService.update_moremain0(moId);
    }


    //生成内箱号码
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg count_Nbox(HttpServletRequest request) {
        ServletContext application=request.getServletContext();
        Integer countn =(Integer)application.getAttribute("countn");
        if (countn==null){
            int count = baozhuangService.count_Nbox();
            application.setAttribute("countn",count);
            return JsonMsg.success().addInfo("c", count);
        }else {
            countn = new Integer(countn.intValue() + 1);
            application.setAttribute("countn",countn);
            return JsonMsg.success().addInfo("c", countn);
        }
    }


    //根据备注信息 来查询重复
    @RequestMapping(value = "/production2/{productionSN}/{mesg}/{mo}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg production2(@PathVariable("productionSN") String productionSN, @PathVariable String mesg, @PathVariable String mo) {
        Integer res = baozhuangService.selectProByName(productionSN,mesg,mo);
        if (res ==0) {
            return JsonMsg.success();
        } else {
            return JsonMsg.fail();
        }
    }


    //打印内箱标签
    @RequestMapping(value = "/printN/{Nbox}")
    public void printN(@PathVariable String Nbox,@RequestParam String lineoption) {
        //二维码内的信息
        String info = "";
        List<Baozhuang> list = baozhuangService.packSN(Nbox);
        for (int i = 0; i < list.size(); i++) {
            info += list.get(i).getProductionSN() + "\\0D";
        }if("Line 3".equals(lineoption)){
            p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:20)");
        }else if ("仓库线".equals(lineoption)){
            p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (副本 1)");
        } else{
            p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:25)");
        }
        test1(p, info, Nbox);
        p.resetZpl();
    }

    /*//打印内箱 第二条线 标签
    @RequestMapping(value = "/printN2/{Nbox}")
    public void printN2(@PathVariable String Nbox) {
        //二维码内的信息
        String info = "";
        List<Baozhuang> list = baozhuangService.packSN(Nbox);
        for (int i = 0; i < list.size(); i++) {
            info += list.get(i).getProductionSN() + "\\0D";
        }

        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:20)");
        test1(p, info, Nbox);
        p.resetZpl();

    }*/

    //内标签模板
    private static boolean test1(ZplPrinter p, String a, String b) {

        String bar0 = a;
        String bar0Zpl;
        if (bar0.length()>1200)
        {
            bar0Zpl = "^BY450,480^FT250,540^BXN,4,200,0,0,1,~\r\n" +
                    "^FH\\^FD${data}^FS\r\n";
        }
        else
        {
            bar0Zpl = "^BY450,480^FT250,540^BXN,5,200,0,0,1,~\r\n" +
                    "^FH\\^FD${data}^FS\r\n";
        }
        p.setBarcode(bar0, bar0Zpl);
       /*
       String barString = b;
       String bar0Zp2 =
                "^BY2.45,3,73^FT360,642^BCN,,Y,N\r\n" +
                        "^FD>:${data}^FS";
        p.setBarcode(barString, bar0Zp2);*/
        //  p.setText(b, 400, 580, 60, 60, 30, 2, 2, 24);
        //p.setText(b, 395, 610, 85, 60, 37, 5, 5, 20);
        System.out.println("4548498+5498+459+59+5"+b);
        p.setChar1(b,268,630,75,26);
        String zpl = p.getZpl();
        boolean result = p.print(zpl);//打印
        return result;
    }

    // 内箱模板
    @RequestMapping(value = "/NeiMuban", method = RequestMethod.GET)
    @ResponseBody
    public void nei(String Product_Name,String Lot_No,String P_N,String SPEC,String Model_No,String QTY,String Date,String lineoption) {
        System.out.println(lineoption);
        if("Line 3".equals(lineoption)){
            p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:20)");
        }else if ("仓库线".equals(lineoption)){
            p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (副本 1)");
        }
        else{
            p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:25)");
        }
        testNeiMuban(p,Product_Name,Lot_No,P_N,SPEC,Model_No,QTY,Date);
        p.resetZpl();
    }

   /* @RequestMapping(value = "/NeiMuban2", method = RequestMethod.GET)
    @ResponseBody
    public void nei2(String Product_Name,String Lot_No,String P_N,String SPEC,String Model_No,String QTY,String Date) {

        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:20)");
        testNeiMuban(p,Product_Name,Lot_No,P_N,SPEC,Model_No,QTY,Date);
        p.resetZpl();
    }
*/

    /**
     * 生成外箱标签
     *
     * @return
     */
    @RequestMapping(value = "/countW", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg count_Wbox(HttpServletRequest request) {
        ServletContext application=request.getServletContext();
        Integer countw =(Integer)application.getAttribute("countw");
        if (countw==null){
            int count = baozhuangService.count_Wbox();
            application.setAttribute("countw",count);
            return JsonMsg.success().addInfo("c", count);
        }else {
            countw = new Integer(countw.intValue() + 1);
            application.setAttribute("countw",countw);
            return JsonMsg.success().addInfo("c", countw);
        }
    }

    /**
     * 判断内箱号是否正确
     *
     * @param NboxId
     * @return
     */
    @RequestMapping(value = "/NboxExists/{NboxId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg NboxExists(@PathVariable String NboxId) {
        int count = baozhuangService.NboxExists(NboxId);
        if (count > 0) {
            return JsonMsg.success().addInfo("c", count);
        } else {
            return JsonMsg.fail();
        }
    }

    @RequestMapping(value = "/printW/{Nbox1}/{Nbox2}/{Nbox3}/{Nbox4}/{Wbox}", method = RequestMethod.GET)
    public JsonMsg printW(@PathVariable String Nbox1, @PathVariable String Nbox2, @PathVariable String Nbox3, @PathVariable String Nbox4, @PathVariable String Wbox,
                          String selectLine) {
        List<Baozhuang> list1 = baozhuangService.packSN(Nbox1);
        List<Baozhuang> list2 = baozhuangService.packSN(Nbox2);
        List<Baozhuang> list3 = baozhuangService.packSN(Nbox3);
        List<Baozhuang> list4 = baozhuangService.packSN(Nbox4);
        String info1="";
        for (int i = 0; i < list1.size(); i++) {
            info1 += list1.get(i).getProductionSN() + "\\0D";
        }
        String info2="";
        for (int i = 0; i < list2.size(); i++) {
            info2 += list2.get(i).getProductionSN() + "\\0D";
        }
        String info3="";
        for (int i = 0; i < list3.size(); i++) {
            info3 += list3.get(i).getProductionSN() + "\\0D";
        }
        String info4="";
        for (int i = 0; i < list4.size(); i++) {
            info4 += list4.get(i).getProductionSN() + "\\0D";
        }

        // ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：24)");
        if (("一号线").equals(selectLine)) {
            p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：24)");
        }
        else
        {
            p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：19)");
        }
        testW(p, info1,info3,info2,info4 ,Wbox);
        p.resetZpl();
        if(1==1){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }

    private static boolean testW(ZplPrinter p,String bar0,String bar1,String bar2,String bar3,String bar4) {

        String bar0Zpl1,bar0Zpl2,bar0Zpl3,bar0Zpl4;
        if (bar0.length()<=1100)
        {
            bar0Zpl1 =
                    "^BY384,384^FT165,443^BXN,4,200,0,0,1,~" +
                            "^FH\\^FD${data}^FS\r\n";



            bar0Zpl2 = "^BY384,384^FT673,443^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}^FS\r\n";


            bar0Zpl3 = "^BY384,384^FT165,904^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}^FS\r\n";



            bar0Zpl4 = "^BY384,384^FT673,904^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}^FS\r\n";
            p.setChar1(bar4,400,1100,80,28);

        }


        else
        {
            bar0Zpl1 = "^BY100,100^FT135,550^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}^FS\r\n";

            bar0Zpl2 = "^BY384,384^FT673,550^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}^FS\r\n";

            bar0Zpl3 = "^BY384,384^FT135,1080^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}^FS\r\n";

            bar0Zpl4 = "^BY384,384^FT673,1080^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}^FS\r\n";
            p.setChar1(bar4,400,1135,80,28);
        }
        p.setBarcode(bar1, bar0Zpl2);
        p.setBarcode(bar2, bar0Zpl3);
        p.setBarcode(bar3, bar0Zpl4);
        p.setBarcode(bar0, bar0Zpl1);

        //p.setText(bar4,400, 1000, 60, 60, 30, 2, 2, 24);
        //p.setChar1(bar4,400,1000,80,28);
        // p.setChar1(bar4,400,1135,80,28);
        //  p.setChar1(bar4,400,1100,80,28);
        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }

    @RequestMapping(value = "/printW/{Nbox1}/{Nbox2}/{Nbox3}/{Wbox}", method = RequestMethod.GET)
    public JsonMsg printW3(@PathVariable String Nbox1, @PathVariable String Nbox2, @PathVariable String Nbox3, @PathVariable String Wbox,
                           String selectLine) {
        List<Baozhuang> list1 = baozhuangService.packSN(Nbox1);
        List<Baozhuang> list2 = baozhuangService.packSN(Nbox2);
        List<Baozhuang> list3 = baozhuangService.packSN(Nbox3);
        String info1="";
        for (int i = 0; i < list1.size(); i++) {
            info1 += list1.get(i).getProductionSN() + "\\0D";
        }
        String info2="";
        for (int i = 0; i < list2.size(); i++) {
            info2 += list2.get(i).getProductionSN() + "\\0D";
        }
        String info3="";
        for (int i = 0; i < list3.size(); i++) {
            info3 += list3.get(i).getProductionSN() + "\\0D";
        }

        if (("一号线").equals(selectLine)) {
            p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：24)");
        }
        else
        {
            p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：19)");
        }
        testW3(p, info1,info3,info2,Wbox);
        p.resetZpl();
        if(1==1){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }
    private static boolean testW3(ZplPrinter p,String bar0,String bar1,String bar2,String bar4) {

        String bar0Zpl1,bar0Zpl2,bar0Zpl3;
        if (bar0.length()<=1100)
        {
            bar0Zpl1 =
                    "^BY384,384^FT165,443^BXN,4,200,0,0,1,~" +
                            "^FH\\^FD${data}\\0D\\0A^FS\r\n";

            bar0Zpl2 = "^BY384,384^FT673,443^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}\\0D\\0A^FS\r\n";

            bar0Zpl3 = "^BY384,384^FT165,904^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}\\0D\\0A^FS\r\n";
            p.setChar1(bar4,400,1100,80,28);
        }
        else
        {

            bar0Zpl1 = "^BY100,100^FT135,550^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}^FS\r\n";

            bar0Zpl2 = "^BY384,384^FT673,550^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}^FS\r\n";

            bar0Zpl3 = "^BY384,384^FT135,1080^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}^FS\r\n";
            p.setChar1(bar4,400,1135,80,28);
        }


        p.setBarcode(bar0, bar0Zpl1);


        p.setBarcode(bar1, bar0Zpl2);



        p.setBarcode(bar2, bar0Zpl3);


        //p.setText(bar4,400, 1000, 60, 60, 30, 2, 2, 24);
        // p.setChar1(bar4,400,1135,80,28);
        // p.setChar1(bar4,400,1100,80,28);
        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }

    @RequestMapping(value = "/printW/{Nbox1}/{Nbox2}/{Wbox}", method = RequestMethod.GET)
    public JsonMsg printW2(@PathVariable String Nbox1, @PathVariable String Nbox2, @PathVariable String Wbox,
                           String selectLine) {
        List<Baozhuang> list1 = baozhuangService.packSN(Nbox1);
        List<Baozhuang> list2 = baozhuangService.packSN(Nbox2);
        String info1="";
        for (int i = 0; i < list1.size(); i++) {
            info1 += list1.get(i).getProductionSN() + "\\0D";
        }
        String info2="";
        for (int i = 0; i < list2.size(); i++) {
            info2 += list2.get(i).getProductionSN() + "\\0D";
        }

        if (("一号线").equals(selectLine)) {
            p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：24)");
        }
        else
        {
            p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：19)");
        }
        testW2(p, info1,info2,Wbox);
        p.resetZpl();
        if(1==1){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }
    private static boolean testW2(ZplPrinter p,String bar0,String bar1,String bar4) {
        //1.打印单个条码
        String bar0Zpl1,bar0Zpl2;
        if (bar0.length()<=1100)
        {
            bar0Zpl1 =
                    "^BY384,384^FT165,443^BXN,4,200,0,0,1,~" +
                            "^FH\\^FD${data}\\0D\\0A^FS\r\n";
            bar0Zpl2 = "^BY384,384^FT165,904^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}\\0D\\0A^FS\r\n";
            p.setChar1(bar4,400,1100,80,28);
        }
        else
        {
            bar0Zpl1 = "^BY100,100^FT135,550^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}^FS\r\n";

            bar0Zpl2 = "^BY384,384^FT673,550^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}^FS\r\n";
            p.setChar1(bar4,400,1135,80,28);
        }

        p.setBarcode(bar0, bar0Zpl1);


        p.setBarcode(bar1, bar0Zpl2);

        //  p.setText(bar4,400, 1000, 60, 60, 30, 2, 2, 24);
        //  p.setChar1(bar4,400,1135,80,28);
        // p.setChar1(bar4,400,1100,80,28);
        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }

    @RequestMapping(value = "/printW/{Nbox1}/{Wbox}", method = RequestMethod.GET)
    public JsonMsg printW1(@PathVariable String Nbox1,@PathVariable String Wbox,
                           String selectLine) {
        List<Baozhuang> list1 = baozhuangService.packSN(Nbox1);
        String info1="";
        for (int i = 0; i < list1.size(); i++) {
            info1 += list1.get(i).getProductionSN() + "\\0D";
        }

        if (("一号线").equals(selectLine)) {
            p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：24)");
        }
        else
        {
            p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：19)");
        }
        testW1(p, info1,Wbox);
        p.resetZpl();
        if(1==1){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }
    private static boolean testW1(ZplPrinter p,String bar0,String bar4) {
        //1.打印单个条码
        String bar0Zpl1;
        if (bar0.length()<=1100)
        {
            bar0Zpl1 =
                    "^BY384,384^FT165,443^BXN,4,200,0,0,1,~" +
                            "^FH\\^FD${data}\\0D\\0A^FS\r\n";
            p.setChar1(bar4,400,1100,80,28);
        }
        else
        {
            bar0Zpl1 = "^BY100,100^FT135,550^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}^FS\r\n";
            p.setChar1(bar4,400,1135,80,28);
        }
        p.setBarcode(bar0, bar0Zpl1);

        //  p.setText(bar4,400, 1000, 60, 60, 30, 2, 2, 24);
        //  p.setChar1(bar4,400,1135,80,28);
        // p.setChar1(bar4,400,1100,80,28);
        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }

    @RequestMapping(value = "/updatewbox/{NboxId}/{WboxId}",method = RequestMethod.PUT)
    public void updatewbox(@PathVariable String NboxId, @PathVariable String WboxId,String operator){
        baozhuangService.updateWbox(NboxId,WboxId,operator);
    }

    @RequestMapping(value = "/selectNboxByPro/{productSN}")
    @ResponseBody
    public JsonMsg selectNboxByPro(@PathVariable String productSN){
        List<Baozhuang> list=baozhuangService.selectNboxByPro(productSN);
        if (list.size()!=0){
            return JsonMsg.success().addInfo("n",list.get(0).getNboxId())
                    .addInfo("mo", list.get(0).getMoId());
        }else {
            return JsonMsg.fail();
        }
    }

    @RequestMapping(value = "/selectCountByNbox/{NboxId}")
    @ResponseBody
    public JsonMsg selectCountByNbox(@PathVariable String NboxId){
        Integer c=baozhuangService.selectCountByNbox(NboxId);
        if (c!=0){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }



    //跳转至入库界面
    @RequestMapping(value = "/BaozhuangExport")
    public String baozhuangExport( ) {

        return "cangkuStorage";
    }


    //通过sn号，查询外箱号
    @RequestMapping(value = "/selectWboxNumber")
    @ResponseBody
    public JsonMsg  selectWboxNumber(String sn)
    {
        String  wbox=baozhuangService.selectWboxnumber(sn);
        if (wbox !=null)
        {
            return JsonMsg.success().addInfo("wbox",wbox);
        }
        else {
            return JsonMsg.fail();
        }
    }


    //查询外箱数据
    @RequestMapping(value = "/selectWboxSN", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectWboxSN(String wbox) {
        List<Baozhuang> baozhuang=baozhuangService.selectWbox(wbox);
        //System.out.println(baozhuang);
        if (baozhuang.size()!=0) {
            return JsonMsg.success().addInfo("wbox",baozhuang);
        } else {
            return JsonMsg.fail();
        }
    }




    //打印外箱模板标签
    private static boolean testWaiMuBan(ZplPrinter p,String Product_Name,String Lot_No,String P_N,String SPEC,String Model_No,String QTY,String Date,String MEAS,String GW,String NW )
    {

      /*
        p.setText2("品名", 90,100, 15, 15, 25, 2, 2, 22);
        p.setText2("生产单号", 90,200, 15, 15, 25, 2, 2, 22);
        p.setText2("料号", 90,300, 15, 15, 25, 2, 2, 22);
        p.setText2("产品规格",90,400, 15, 15, 25, 2, 2, 22);
        p.setText2("产品型号", 90,500, 15, 15, 25, 2, 2, 22);
        p.setText2("数量",     90,600, 15, 15, 25, 2, 2, 22);
        p.setText2("生产日期", 90,700, 15, 15, 25, 2, 2, 22);
        p.setText2("材积", 90,800, 15, 15, 25, 2, 2, 22);
        p.setText2("毛重", 90,900, 15, 15, 25, 2, 2, 22);
        p.setText2("净重", 90,1000, 15, 15, 25, 2, 2, 22);*/

        p.setText3("品名", 90,100, 15, 15, 25, 2, 2, 22);
        p.setText3("生产单号", 90,200, 15, 15, 25, 2, 2, 22);
        p.setText3("料号", 90,300, 15, 15, 25, 2, 2, 22);
        p.setText3("产品规格",90,400, 15, 15, 25, 2, 2, 22);
        p.setText3("产品型号", 90,500, 15, 15, 25, 2, 2, 22);
        p.setText3("数量",     90,600, 15, 15, 25, 2, 2, 22);
        p.setText3("生产日期", 90,700, 15, 15, 25, 2, 2, 22);
        p.setText3("材积", 90,800, 15, 15, 25, 2, 2, 22);
        p.setText3("毛重", 90,900, 15, 15, 25, 2, 2, 22);
        p.setText3("净重", 90,1000, 15, 15, 25, 2, 2, 22);


        p.setChar1("/Product Name", 180, 135, 15,15);
        p.setChar1("/Lot No.", 270, 230, 60, 10);
        p.setChar1("/P/N", 180, 330, 60, 10);
        p.setChar1("/SPEC", 270, 430, 60, 10);
        p.setChar1("/Model No", 270, 530, 60, 10);
        p.setChar1("/QTY", 180, 630, 60, 10);
        p.setChar1("/MFG.Date", 270, 730, 60, 10);
        p.setChar1("/MEAS", 180, 830, 60, 10);
        p.setChar1("/G.W", 180, 930, 60, 10);
        p.setChar1("/N.W", 180, 1030, 60, 10);

        p.setXian2();

        p.setChar1(Product_Name, 650, 119, 15, 15);
        p.setChar1(Lot_No,  550, 220, 15, 1);
        p.setChar1(P_N,  550, 320, 15, 15);
        p.setChar1(SPEC,  510, 420, 15, 15);
        if (Model_No.length()>13)
        {
            p.setWboxmodel(Model_No,560,500);
        }else
        {
            p.setChar1(Model_No,  630, 520, 15, 15);
        }

        p.setChar1(QTY,  570, 620, 15, 15);
        p.setChar1(Date,  700, 720,15, 15);
        p.setChar1(MEAS,  570, 820,15, 15);
        p.setChar1(GW,  570, 920,15, 15);
        p.setChar1(NW,  570, 1020,15, 15);
        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }

    //调用外箱模板标签实现打印
    @RequestMapping(value = "/printWaiMuBan", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg printWaiMuBan(String Product_Name,String Lot_No,String P_N,String SPEC,String Model_No,String QTY,String Date,String MEAS,String GW,String NW,
                                 String selectLine) {
        if (("一号线").equals(selectLine)) {
            p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：24)");
        }
        else
        {
            p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：19)");
        }
        testWaiMuBan(p, Product_Name, Lot_No, P_N, SPEC, Model_No, QTY, Date, MEAS, GW, NW);
        p.resetZpl();
        return JsonMsg.success();
    }




    private static boolean testNeiMuban(ZplPrinter p,String Product_Name,String Lot_No,String P_N,String SPEC,String Model_No,String QTY,String Date) {
        /**
         * 中文字符、英文字符(包含数字)混合
         * @param str 中文、英文
         * @param x x坐标
         * @param y y坐标
         * @param eh 英文字体高度height
         * @param ew 英文字体宽度width
         * @param es 英文字体间距spacing
         * @param mx 中文x轴字体图形放大倍率。范围1-10，默认1
         * @param my 中文y轴字体图形放大倍率。范围1-10，默认1
         * @param ms 中文字体间距。24是个比较合适的值。
         */
        p.setText("品名", 24,55, 15, 15, 25, 2, 2, 22);
        p.setText("生产单号", 24,128, 15, 15, 25, 2, 2, 22);
        p.setText("料号", 24,210, 15, 15, 25, 2, 2, 22);
        p.setText("产品规格", 24,300, 15, 15, 25, 2, 2, 22);
        p.setText("产品型号", 24,384, 15, 15, 25, 2, 2, 22);
        p.setText("数量",     24,470, 15, 15, 25, 2, 2, 22);
        p.setText("生产日期", 24,560, 15, 15, 25, 2, 2, 22);


        p.setChar1("/Product Name", 110, 85, 15,15);
        p.setChar1("/Lot No.", 200, 158, 60, 10);
        p.setChar1("/P/N", 120, 240, 60, 10);
        p.setChar1("/SPEC", 205, 332, 60, 10);
        p.setChar1("/Model No", 205, 415, 60, 10);
        p.setChar1("/QTY", 120, 500, 60, 10);
        p.setChar1("/MFG.Date", 203, 590, 60, 10);


        p.setXian();
        /**
         * 英文字符串(包含数字)
         * @param str 英文字符串
         * @param x	x坐标
         * @param y y坐标
         * @param h 高度
         * @param w 宽度
         */
        p.setChar1(Product_Name, 470, 90, 15, 15);
        // p.setChar1(Lot_No,  415, 160, 15, 1);
        p.setNeiMo(Lot_No,395,140);
        p.setChar1(P_N,  480, 240, 15, 15);
        //p.setChar1(SPEC,  490, 330, 15, 15);

        //p.setChar1(SPEC,  405, 330, 15, 15);
        p.setNeiSPEC(SPEC);
        // p.setNeimodel(Model_No, 450, 390);
        p.setNeimodel(Model_No);

        //p.setChar1(Model_No,  492, 410, 15, 15);
        p.setChar1(QTY,  510, 500, 15, 15);
        p.setChar1(Date,  550, 580,15, 15);
        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }

    /**
     * 外箱打印2
     */
    @RequestMapping(value = "/Wprint2",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg Wprint2(String Nbox_num1,String Nbox_num2,String Nbox_num3,String Nbox_num4,String sn1,String sn2,String sn3,String sn4,String WboxId,String moid,String model,Integer monum,Integer moremain,String operator) throws IOException {
        BufferedReader br1= new BufferedReader(new InputStreamReader(new ByteArrayInputStream(sn1.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));
        BufferedReader br2= new BufferedReader(new InputStreamReader(new ByteArrayInputStream(sn2.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));
        BufferedReader br3= new BufferedReader(new InputStreamReader(new ByteArrayInputStream(sn3.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));
        BufferedReader br4= new BufferedReader(new InputStreamReader(new ByteArrayInputStream(sn4.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));


        String info1="";
        int a=0;
        while ((sn1 = br1.readLine()) != null ) {
            if(!sn1.trim().equals("")){
                //line1=line1;//每行可以做加
                //strbuf.append(line+"\r\n");
                //System.out.println(sn1);

                Baozhuang baozhuang=new Baozhuang(1,sn1,model,"无","1",moid,monum,moremain,operator,Nbox_num1,WboxId,"");
                info1+=sn1+"\\0D";
                a++;
                //System.out.println(sn1);
                baozhuangService.insertpack2(baozhuang);
                moremain=moremain-1;
            }
        }
        String info2="";
        int b=0;
        while ( (sn2 = br2.readLine()) != null ) {
            if(!sn2.trim().equals("")){
                //line2=line2;//每行可以做加工
                //strbuf.append(line+"\r\n");
                Baozhuang baozhuang=new Baozhuang(1,sn2,model,"无","1",moid,monum,moremain,operator,Nbox_num2,WboxId,"");
                baozhuangService.insertpack2(baozhuang);
                info2+=sn2+"\\0D";
                moremain=moremain-1;
                b++;
            }
        }
        String info3="";
        int c=0;
        while ( (sn3 = br3.readLine()) != null ) {
            if(!sn3.trim().equals("")){
                //line3=line3;//每行可以做加工
                //strbuf.append(line+"\r\n");
                Baozhuang baozhuang=new Baozhuang(1,sn3,model,"无","1",moid,monum,moremain,operator,Nbox_num3,WboxId,"");
                baozhuangService.insertpack2(baozhuang);
                info3+=sn3+"\\0D";
                moremain=moremain-1;
                c++;
            }
        }
        String info4="";
        int d=0;
        while ( (sn4 = br4.readLine()) != null ) {
            if(!sn4.trim().equals("")){
                //line4=line4;//每行可以做加工
                //strbuf.append(line+"\r\n");
                Baozhuang baozhuang=new Baozhuang(1,sn4,model,"无","1",moid,monum,moremain,operator,Nbox_num4,WboxId,"");
                baozhuangService.insertpack2(baozhuang);
                info4+=sn4+"\\0D";
                moremain=moremain-1;
                d++;
            }
        }

        //System.out.println(a);
        baozhuangService.update_moremain02(moid);
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：241)");
        testW(p,info1,info2,info3,info4,WboxId);
        p.resetZpl();

        if (a==50&&b==50&&c==50&&d==50){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }

    //内包装操作信息修改
    @RequestMapping(value = "/updateModal",method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updateModal(String productionSN){
        int res=baozhuangService.updateModal(productionSN);
        if (res==1){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }

    //包装信息多条件查询
    @RequestMapping(value = "/selectrelate",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectrelate(Baozhuang baozhuang){

        List<Baozhuang> bz=baozhuangService.selectrelateBaoZhuang(baozhuang);
        if (bz!=null){
            return JsonMsg.success().addInfo("bz",bz);
        }else {
            return JsonMsg.fail();
        }

    }

    //内外箱application归零
    @RequestMapping(value = "/resetcount")
    public void resetcount(HttpServletRequest request){
        ServletContext application=request.getServletContext();
        application.setAttribute("countn", -1);
        application.setAttribute("countw", -1);

    }

    //根据外箱号查询内箱号
    @RequestMapping(value = "/selectNBoxByWbox/{wboxId}")
    @ResponseBody
    public JsonMsg selectNBoxByWbox( @PathVariable String wboxId){
        List<String> nbox=baozhuangService.selectNBoxByWbox(wboxId);
        if (nbox.size()>0){
            return JsonMsg.success().addInfo("nbox", nbox);
        }else {
            return JsonMsg.fail();
        }
    }
    // 根据外箱号批量导入包装数据到仓库的数据库中    仓库使用
    @RequestMapping(value = "/insertCangkuByWbox/{wbox}",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg insertCangkuByWbox(@PathVariable String wbox){
        int i=0;
        try {

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        if (i!=0){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }

}

