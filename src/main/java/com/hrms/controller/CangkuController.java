package com.hrms.controller;

import com.hrms.bean.Baozhuang;
import com.hrms.bean.CangkuRecord;
import com.hrms.service.CangkuService;
import com.hrms.util.JsonMsg;
import com.hrms.util.ZplPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/cangku")
public class CangkuController {
    @Autowired
    CangkuService cangkuService;

    //查询外箱数据  --------入库使用
    @RequestMapping(value = "/selectWboxSN", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectWboxSN(String wbox) {
        List<Baozhuang> baozhuang=cangkuService.selectWbox(wbox);
        //System.out.println(baozhuang);
        if (baozhuang.size()!=0) {
            return JsonMsg.success().addInfo("wbox",baozhuang);
        } else {
            return JsonMsg.fail();
        }
    }


    @RequestMapping("/NboxPage")
    public String printNboxPage()
    {
        return "cangkuPrintNbox";
    }
    @RequestMapping("/WboxPage")
    public String printWboxPage()
    {
        return "cangkuPrintWbox";
    }
    //插入到仓库数据库
    @RequestMapping(value = "/insertCangKu", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insertCangKu(Baozhuang baozhuang) {
        int i=cangkuService.insertCangKu(baozhuang);
        //  System.out.println(baozhuang);
        if (i!=0) {
            return JsonMsg.success();
        } else {
            return JsonMsg.fail();
        }
    }



    //根据sn查询内箱号  ----仓库外箱 使用
    @RequestMapping(value = "/selectNboxBySn/{sn}")
    @ResponseBody
    public JsonMsg selectNboxBySn(@PathVariable String sn){
        Baozhuang baozhuang=cangkuService.selectNboxBySn(sn);
        //System.out.println(baozhuang);
        if (baozhuang!=null){
            return JsonMsg.success().addInfo("baozhuang",baozhuang);

        }else {
            return JsonMsg.fail();
        }
    }
    //根据内箱号查询数量 仓库外箱打印使用
    @RequestMapping(value = "/selectNboxNumber/{nbox}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectNboxNumber(@PathVariable String nbox) {
        int  count = cangkuService.selectNboxNumber(nbox);
        if (count>0) {
            return JsonMsg.success().addInfo("count", count);
        } else {
            return JsonMsg.fail();
        }
    }


    //打印内箱标签
    @RequestMapping(value = "/printNbox/{Nbox}")
    public void printN(@PathVariable String Nbox) {
        //二维码内的信息
        String info = "";
        List<String> list =cangkuService.selectSnByNbox(Nbox);
        for (int i = 0; i < list.size(); i++) {
            info += list.get(i) + "\\0D";
        }
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:16)");
        NboxMuban(p, info, Nbox);
        p.resetZpl();

    }

    //内标签模板
    private static boolean NboxMuban(ZplPrinter p, String a, String b) {

        String bar0 = a;
        String bar0Zpl = "^BY450,480^FT250,540^BXN,5,200,0,0,1,~\r\n" +
                "^FH\\^FD${data}^FS\r\n";
        p.setBarcode(bar0, bar0Zpl);
        p.setChar1(b,268,630,75,26);
        String zpl = p.getZpl();
        // System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }

    //更改内箱的外箱号
    @RequestMapping(value = "/updateWbox/{nboxId}/{wboxId}",method = RequestMethod.PUT)
    public void updatewbox(@PathVariable String nboxId, @PathVariable String wboxId){
        cangkuService.updateWbox(nboxId,wboxId);
    }

    //打印四个内箱的外箱
    @RequestMapping(value = "/printWbox/{Nbox1}/{Nbox2}/{Nbox3}/{Nbox4}/{Wbox}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg printW(@PathVariable String Nbox1, @PathVariable String Nbox2, @PathVariable String Nbox3, @PathVariable String Nbox4, @PathVariable String Wbox) {
        List<String> list1 = cangkuService.selectSnByNbox(Nbox1);
        List<String> list2 = cangkuService.selectSnByNbox(Nbox2);
        List<String> list3 = cangkuService.selectSnByNbox(Nbox3);
        List<String> list4 = cangkuService.selectSnByNbox(Nbox4);
        String info1="";
        for (int i = 0; i < list1.size(); i++) {
            info1 += list1.get(i) + "\\0D";
        }
        String info2="";
        for (int i = 0; i < list2.size(); i++) {
            info2 += list2.get(i) + "\\0D";
        }
        String info3="";
        for (int i = 0; i < list3.size(); i++) {
            info3 += list3.get(i) + "\\0D";
        }
        String info4="";
        for (int i = 0; i < list4.size(); i++) {
            info4 += list4.get(i) + "\\0D";
        }

        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:17)");
        boolean b=  testW4(p, info1,info3,info2,info4 ,Wbox);
        p.resetZpl();
        if(b==true){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }

    private static boolean testW4(ZplPrinter p,String bar0,String bar1,String bar2,String bar3,String bar4) {

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
        // System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }

    //打印三个内箱的外箱
    @RequestMapping(value = "/printWbox/{Nbox1}/{Nbox2}/{Nbox3}/{Wbox}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg printW3(@PathVariable String Nbox1, @PathVariable String Nbox2, @PathVariable String Nbox3, @PathVariable String Wbox) {
        List<String> list1 = cangkuService.selectSnByNbox(Nbox1);
        List<String> list2 = cangkuService.selectSnByNbox(Nbox2);
        List<String> list3 = cangkuService.selectSnByNbox(Nbox3);
        String info1="";
        for (int i = 0; i < list1.size(); i++) {
            info1 += list1.get(i) + "\\0D";
        }
        String info2="";
        for (int i = 0; i < list2.size(); i++) {
            info2 += list2.get(i) + "\\0D";
        }
        String info3="";
        for (int i = 0; i < list3.size(); i++) {
            info3 += list3.get(i) + "\\0D";
        }

        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:17)");
        boolean b= testW3(p, info1,info3,info2,Wbox);
        p.resetZpl();
        if(b==true){
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
        //System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }

    //打印二个内箱的外箱
    @RequestMapping(value = "/printWbox/{Nbox1}/{Nbox2}/{Wbox}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg printW2(@PathVariable String Nbox1, @PathVariable String Nbox2, @PathVariable String Wbox) {
        List<String> list1 = cangkuService.selectSnByNbox(Nbox1);
        List<String> list2 = cangkuService.selectSnByNbox(Nbox2);
        String info1="";
        for (int i = 0; i < list1.size(); i++) {
            info1 += list1.get(i) + "\\0D";
        }
        String info2="";
        for (int i = 0; i < list2.size(); i++) {
            info2 += list2.get(i) + "\\0D";
        }

        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:17)");
        boolean b= testW2(p, info1,info2,Wbox);
        p.resetZpl();
        if(b==true){
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
        // System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }

    //打印一个内箱的外箱
    @RequestMapping(value = "/printWbox/{Nbox1}/{Wbox}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg printW1(@PathVariable String Nbox1,@PathVariable String Wbox) {
        System.out.println("打印外箱");
        List<String> list1 = cangkuService.selectSnByNbox(Nbox1);
        String info1="";
        for (int i = 0; i < list1.size(); i++) {
            info1 += list1.get(i) + "\\0D";
        }
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:17)");
        boolean b= testW1(p, info1,Wbox);
        System.out.println("打印外箱的 结果"+b);
        p.resetZpl();
        if(b==true){
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
        String zpl = p.getZpl();
        // System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }



    //调用内箱模板标签实现打印
    @RequestMapping(value = "/printNeiMuban", method = RequestMethod.GET)
    @ResponseBody
    public void nei(String Product_Name,String Lot_No,String P_N,String SPEC,String Model_No,String QTY,String Date) {
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:16)");
        NeiMuban(p,Product_Name,Lot_No,P_N,SPEC,Model_No,QTY,Date);
        p.resetZpl();
    }

    //打印内箱模板标签
    private static boolean NeiMuban(ZplPrinter p,String Product_Name,String Lot_No,String P_N,String SPEC,String Model_No,String QTY,String Date) {
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
        // System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }

    //调用外箱模板标签实现打印
    @RequestMapping(value = "/printWaiMuBan", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg printWaiMuBan(String Product_Name,String Lot_No,String P_N,String SPEC,String Model_No,String QTY,String Date,String MEAS,String GW,String NW ) {

        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:17)");
        WaiMuBan(p, Product_Name, Lot_No, P_N, SPEC, Model_No, QTY, Date, MEAS, GW, NW);
        p.resetZpl();
        return JsonMsg.success();
    }

    //打印外箱模板标签
    private static boolean WaiMuBan(ZplPrinter p,String Product_Name,String Lot_No,String P_N,String SPEC,String Model_No,String QTY,String Date,String MEAS,String GW,String NW )
    {
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
        // System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }


    //记录新旧内箱，和旧外箱
    @RequestMapping(value = "/insertCangKuRecord", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insertCangKuRecord(CangkuRecord cangkuRecord) {
        //System.out.println(cangkuRecord);
        int i = cangkuService.insertCangKuRecord(cangkuRecord);
        if (i != 0) {
            return JsonMsg.success();
        } else
        { return JsonMsg.fail();}

    }


    //更改新外箱号 记录
    @RequestMapping(value = "/updateCangKuRecordByWbox", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg updateCangKuRecordByNotChaixiang(String newNbox,String newWbox) {
        //先检查 是不是整箱扫描
        CangkuRecord cangkuRecord = cangkuService.selecteCangKuRecordByNewNbox(newNbox);

        if (cangkuRecord!=null) {
            //直接更改新的外箱号
            int i = cangkuService.updateCangKuRecordByNewWbox(newNbox,newWbox);
            if (i!=0)
            { return JsonMsg.success();}
            else
            {return  JsonMsg.fail();}
        } else
        {
            //根据未拆箱的内箱号 来查出旧外箱号
            String oldWbox = cangkuService.selectOldWboxByOldNbox(newNbox);
            //更改未拆箱的内箱号 的外箱号 进行记录   进行插入
            int i = cangkuService.insertRecordByNotChaixiang(newNbox, oldWbox, newWbox);
            if (i != 0) {
                return JsonMsg.success();
            } else {
                return JsonMsg.fail();
            }
        }
    }
}
