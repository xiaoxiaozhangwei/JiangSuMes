package com.hrms.controller;

import com.hrms.bean.Gongdan;
import com.hrms.bean.RePack;
import com.hrms.service.GongdanService;
import com.hrms.service.RePackService;
import com.hrms.util.JsonMsg;
import com.hrms.util.ZplPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

@Controller
@RequestMapping(value = "/repack")
public class RePackController {
    @Autowired
    RePackService rePackService;
    @Autowired
    GongdanService gongdanService;

    /**
     * 跳转到内盒重工页面
     * @return
     */
    @RequestMapping(value = "/Nboxchonggong", method = RequestMethod.GET)
    public String Nboxchonggong(){
        return "NboxChonggong";
    }

    /**
     * 跳转到外箱重工页面
     * @return
     */
    @RequestMapping(value = "/Wboxchonggong", method = RequestMethod.GET)
    public String Wboxchonggong(){
        return "WboxChonggong";
    }

        /**
         * 添加到数据库
         *
         * @param rePack
         */
        @RequestMapping(value = "/insertpack", method = RequestMethod.POST)
        @ResponseBody
        public JsonMsg insertpack(RePack rePack) {
            Integer res=rePackService.insertpack(rePack);
            if (res==1){
                return JsonMsg.success();
            }else {
                return JsonMsg.fail();
            }
        }
        /**
         * 判断SN号与model号是否匹配
         *
         * @param productionSN
         * @param model
         * @return
         */
        @RequestMapping(value = "/production/{productionSN}/{model}", method = RequestMethod.GET)
        @ResponseBody
        public JsonMsg production(@PathVariable("productionSN") String productionSN, @PathVariable("model") String model) {
            Integer res = rePackService.production(productionSN, model);
            if (res != null) {
                return JsonMsg.success();
            } else {
                return JsonMsg.fail();
            }

        }

        /**
         * 根据工单号查询工单信息
         *
         * @param
         * @return
         */
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

        /**
         * 工单剩余量减1
         *
         * @param moId
         */
        @RequestMapping(value = "/merman/{moId}", method = RequestMethod.PUT)
        public void update_moremain0(@PathVariable("moId") String moId) {
            gongdanService.update_moremain0(moId);
        }

        /**
         * 生成内箱标签
         *
         * @return
         */
        @RequestMapping(value = "/count", method = RequestMethod.GET)
        @ResponseBody
        public JsonMsg count_Nbox() {
            int count = rePackService.count_Nbox();
            if (count >= 0) {
                return JsonMsg.success().addInfo("c", count);
            } else {
                return JsonMsg.fail();
            }
        }

        @RequestMapping(value = "/production2/{productionSN}", method = RequestMethod.GET)
        @ResponseBody
        public JsonMsg production2(@PathVariable("productionSN") String productionSN) {
            Integer res = rePackService.selectProByName(productionSN);
            if (res ==0) {
                return JsonMsg.success();
            } else {
                return JsonMsg.fail();
            }
        }


        /**
         * 打印内标签
         *
         * @param Nbox
         */
        @RequestMapping(value = "/printN/{Nbox}")
        public void printN(@PathVariable String Nbox) {
            //System.out.println(Nbox);
            //二维码内的信息
            String info = "";
            List <RePack> list =rePackService.packSN(Nbox);
            System.out.println(list);
            for (int i = 0; i < list.size(); i++) {
                info += list.get(i).getProductionSN() + "\\0D";
            }

            ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL");
            test1(p, info, Nbox);
            p.resetZpl();
        }

        /**
         * 内标签函数
         *
         * @param p
         * @param a
         * @param b
         * @return
         */
        private static boolean test1(ZplPrinter p, String a, String b) {

            String bar0 = a;

            String bar0Zpl = "^BY450,480^FT250,540^BXN,5,200,0,0,1,~\r\n" +
                    "^FH\\^FD${data}\\0D\\0A^FS\r\n";
            ;
            p.setBarcode(bar0, bar0Zpl);
            String barString = b;
       /* String bar0Zp2 =
                "^BY2.45,3,73^FT360,642^BCN,,Y,N\r\n" +
                        "^FD>:${data}^FS";
        p.setBarcode(barString, bar0Zp2);*/
            //  p.setText(b, 400, 580, 60, 60, 30, 2, 2, 24);
            //p.setText(b, 395, 610, 85, 60, 37, 5, 5, 20);
            p.setChar1(b,268,630,75,26);
            String zpl = p.getZpl();
            System.out.println(zpl);
            boolean result = p.print(zpl);//打印
            return result;
        }

        /**
         * 生成外箱标签
         *
         * @return
         */
        @RequestMapping(value = "/countW", method = RequestMethod.GET)
        @ResponseBody
        public JsonMsg count_Wbox() {
            int count = rePackService.count_Wbox();
            if (count >= 0) {
                return JsonMsg.success().addInfo("c", count);
            } else {
                return JsonMsg.fail();
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
            int count = rePackService.NboxExists(NboxId);
            if (count > 0) {
                return JsonMsg.success().addInfo("c", count);
            } else {
                return JsonMsg.fail();
            }
        }

        @RequestMapping(value = "/printW/{Nbox1}/{Nbox2}/{Nbox3}/{Nbox4}/{Wbox}", method = RequestMethod.GET)
        public void printW(@PathVariable String Nbox1, @PathVariable String Nbox2, @PathVariable String Nbox3, @PathVariable String Nbox4, @PathVariable String Wbox) {
            List<RePack> list1 = rePackService.packSN(Nbox1);
            List<RePack> list2 = rePackService.packSN(Nbox2);
            List<RePack> list3 = rePackService.packSN(Nbox3);
            List<RePack> list4 = rePackService.packSN(Nbox4);
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

            ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：24)");
            testW(p, info1,info3,info2,info4 ,Wbox);
            p.resetZpl();
        }
        private static boolean testW(ZplPrinter p,String bar0,String bar1,String bar2,String bar3,String bar4) {

            String bar0Zpl1 =
                    "^BY384,384^FT165,443^BXN,4,200,0,0,1,~" +
                            "^FH\\^FD${data}\\0D\\0A^FS\r\n";


            p.setBarcode(bar0, bar0Zpl1);


            String bar0Zpl2 = "^BY384,384^FT673,443^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}\\0D\\0A^FS\r\n";
            p.setBarcode(bar1, bar0Zpl2);


            String bar0Zpl3 = "^BY384,384^FT165,904^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}\\0D\\0A^FS\r\n";
            p.setBarcode(bar2, bar0Zpl3);


            String bar0Zpl4 = "^BY384,384^FT673,904^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}\\0D\\0A^FS\r\n";
            p.setBarcode(bar3, bar0Zpl4);


            //p.setText(bar4,400, 1000, 60, 60, 30, 2, 2, 24);
            p.setChar1(bar4,400,1000,80,28);
            String zpl = p.getZpl();
            System.out.println(zpl);
            boolean result = p.print(zpl);//打印
            return result;
        }

        @RequestMapping(value = "/printW/{Nbox1}/{Nbox2}/{Nbox3}/{Wbox}", method = RequestMethod.GET)
        public void printW3(@PathVariable String Nbox1, @PathVariable String Nbox2, @PathVariable String Nbox3, @PathVariable String Wbox) {
            List<RePack> list1 = rePackService.packSN(Nbox1);
            List<RePack> list2 = rePackService.packSN(Nbox2);
            List<RePack> list3 = rePackService.packSN(Nbox3);
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


            ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：24)");
            testW3(p, info1,info3,info2,Wbox);
            p.resetZpl();
        }
        private static boolean testW3(ZplPrinter p,String bar0,String bar1,String bar2,String bar4) {
            //1.打印单个条码

            String bar0Zpl1 =
                    "^BY384,384^FT165,443^BXN,4,200,0,0,1,~" +
                            "^FH\\^FD${data}\\0D\\0A^FS\r\n";
            p.setBarcode(bar0, bar0Zpl1);


            String bar0Zpl2 = "^BY384,384^FT673,443^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}\\0D\\0A^FS\r\n";
            p.setBarcode(bar1, bar0Zpl2);


            String bar0Zpl3 = "^BY384,384^FT165,904^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}\\0D\\0A^FS\r\n";
            p.setBarcode(bar2, bar0Zpl3);


            //p.setText(bar4,400, 1000, 60, 60, 30, 2, 2, 24);
            p.setChar2(bar4,400,1000,80,28);
            String zpl = p.getZpl();
            System.out.println(zpl);
            boolean result = p.print(zpl);//打印
            return result;
        }

        @RequestMapping(value = "/printW/{Nbox1}/{Nbox2}/{Wbox}", method = RequestMethod.GET)
        public void printW2(@PathVariable String Nbox1, @PathVariable String Nbox2, @PathVariable String Wbox) {
            List<RePack> list1 = rePackService.packSN(Nbox1);
            List<RePack> list2 = rePackService.packSN(Nbox2);
            String info1="";
            for (int i = 0; i < list1.size(); i++) {
                info1 += list1.get(i).getProductionSN() + "\\0D";
            }
            String info2="";
            for (int i = 0; i < list2.size(); i++) {
                info2 += list2.get(i).getProductionSN() + "\\0D";
            }



            ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：24)");
            testW2(p, info1,info2,Wbox);
            p.resetZpl();
        }
        private static boolean testW2(ZplPrinter p,String bar0,String bar1,String bar4) {
            //1.打印单个条码

            String bar0Zpl1 =
                    "^BY384,384^FT165,443^BXN,4,200,0,0,1,~" +
                            "^FH\\^FD${data}\\0D\\0A^FS\r\n";
            p.setBarcode(bar0, bar0Zpl1);

            String bar0Zpl2 = "^BY384,384^FT165,904^BXN,4,200,0,0,1,~" +
                    "^FH\\^FD${data}\\0D\\0A^FS\r\n";
            p.setBarcode(bar1, bar0Zpl2);

            //  p.setText(bar4,400, 1000, 60, 60, 30, 2, 2, 24);
            p.setChar2(bar4,400,1000,80,28);
            String zpl = p.getZpl();
            System.out.println(zpl);
            boolean result = p.print(zpl);//打印
            return result;
        }

        @RequestMapping(value = "/printW/{Nbox1}/{Wbox}", method = RequestMethod.GET)
        public void printW1(@PathVariable String Nbox1,@PathVariable String Wbox) {
            List<RePack> list1 = rePackService.packSN(Nbox1);
            String info1="";
            for (int i = 0; i < list1.size(); i++) {
                info1 += list1.get(i).getProductionSN() + "\\0D";
            }

            ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：24)");
            testW1(p, info1,Wbox);
            p.resetZpl();
        }
        private static boolean testW1(ZplPrinter p,String bar0,String bar4) {
            //1.打印单个条码

            String bar0Zpl1 =
                    "^BY384,384^FT165,443^BXN,4,200,0,0,1,~" +
                            "^FH\\^FD${data}\\0D\\0A^FS\r\n";
            p.setBarcode(bar0, bar0Zpl1);

            //  p.setText(bar4,400, 1000, 60, 60, 30, 2, 2, 24);
            p.setChar2(bar4,400,1000,80,28);
            String zpl = p.getZpl();
            System.out.println(zpl);
            boolean result = p.print(zpl);//打印
            return result;
        }

        @RequestMapping(value = "/updatewbox/{NboxId}/{WboxId}",method = RequestMethod.PUT)
        public void updatewbox(@PathVariable String NboxId, @PathVariable String WboxId){
            rePackService.updateWbox(NboxId,WboxId);
        }

        @RequestMapping(value = "/selectNboxByPro/{productSN}")
        @ResponseBody
        public JsonMsg selectNboxByPro(@PathVariable String productSN){
            String nboxid=rePackService.selectNboxByPro(productSN);
            if (nboxid!=null){
                return JsonMsg.success().addInfo("n",nboxid);
            }else {
                return JsonMsg.fail();
            }
        }

        @RequestMapping(value = "/selectCountByNbox/{NboxId}")
        @ResponseBody
        public JsonMsg selectCountByNbox(@PathVariable String NboxId){
            Integer c=rePackService.selectCountByNbox(NboxId);
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


        //查询外箱号
        @RequestMapping(value = "/selectWboxNumber")
        @ResponseBody
        public JsonMsg  selectWboxNumber(String sn)
        {
            String  wbox=rePackService.selectWboxnumber(sn);
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
            List<RePack> baozhuang=rePackService.selectWbox(wbox);
            System.out.println(baozhuang);
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
            p.setChar1(SPEC,  630, 420, 15, 15);
            p.setChar1(Model_No,  630, 520, 15, 15);
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
        public void printWaiMuBan(String Product_Name,String Lot_No,String P_N,String SPEC,String Model_No,String QTY,String Date,String MEAS,String GW,String NW ) {

            ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：24)");
            testWaiMuBan(p, Product_Name, Lot_No, P_N, SPEC, Model_No, QTY, Date, MEAS, GW, NW);
            p.resetZpl();
        }


        @RequestMapping(value = "/NeiMuban", method = RequestMethod.GET)
        @ResponseBody
        public void nei(String Product_Name,String Lot_No,String P_N,String SPEC,String Model_No,String QTY,String Date) {
            System.out.println(Product_Name+Lot_No);
            ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL");
            testNeiMuban(p,Product_Name,Lot_No,P_N,SPEC,Model_No,QTY,Date);
            p.resetZpl();
        }




        private static boolean testNeiMuban(ZplPrinter p,String Product_Name,String Lot_No,String P_N,String SPEC,String Model_No,String QTY,String Date)
        {
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
            p.setText("品名", 64,55, 15, 15, 25, 2, 2, 22);
            p.setText("生产单号", 64,128, 15, 15, 25, 2, 2, 22);
            p.setText("料号", 64,210, 15, 15, 25, 2, 2, 22);
            p.setText("产品规格", 64,300, 15, 15, 25, 2, 2, 22);
            p.setText("产品型号", 64,384, 15, 15, 25, 2, 2, 22);
            p.setText("数量",     64,470, 15, 15, 25, 2, 2, 22);
            p.setText("生产日期", 64,560, 15, 15, 25, 2, 2, 22);


            p.setChar1("/Product Name", 150, 85, 15,15);
            p.setChar1("/Lot No.", 240, 158, 60, 10);
            p.setChar1("/P/N", 160, 240, 60, 10);
            p.setChar1("/SPEC", 245, 332, 60, 10);
            p.setChar1("/Model No", 245, 415, 60, 10);
            p.setChar1("/QTY", 160, 500, 60, 10);
            p.setChar1("/MFG.Date", 243, 590, 60, 10);


            p.setXian();
            /**
             * 英文字符串(包含数字)
             * @param str 英文字符串
             * @param x	x坐标
             * @param y y坐标
             * @param h 高度
             * @param w 宽度
             */
            p.setChar1(Product_Name, 500, 90, 15, 15);
            p.setChar1(Lot_No,  415, 160, 15, 1);
            p.setChar1(P_N,  500, 240, 15, 15);
            p.setChar1(SPEC,  490, 330, 15, 15);
            p.setChar1(Model_No,  492, 410, 15, 15);
            p.setChar1(QTY,  520, 500, 15, 15);
            p.setChar1(Date,  565, 580,15, 15);
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
        public JsonMsg Wprint2(String Nbox_num1, String Nbox_num2, String Nbox_num3, String Nbox_num4, String sn1, String sn2, String sn3, String sn4, String WboxId, String moid, String model, Integer monum, Integer moremain, String operator) throws IOException {
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

                    RePack rePack=new RePack(1,sn1,model,"无","1",moid,operator,Nbox_num1,WboxId);
                    info1+=sn1+"\\0D";
                    a++;
                    //System.out.println(sn1);
                    rePackService.insertpack2(rePack);
                    moremain=moremain-1;
                }
            }
            String info2="";
            int b=0;
            while ( (sn2 = br2.readLine()) != null ) {
                if(!sn2.trim().equals("")){
                    //line2=line2;//每行可以做加工
                    //strbuf.append(line+"\r\n");
                    RePack rePack=new RePack(1,sn2,model,"无","1",moid,operator,Nbox_num1,WboxId);
                    rePackService.insertpack2(rePack);
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
                    RePack rePack=new RePack(1,sn3,model,"无","1",moid,operator,Nbox_num1,WboxId);
                    rePackService.insertpack2(rePack);
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
                    RePack rePack=new RePack(1,sn4,model,"无","1",moid,operator,Nbox_num1,WboxId);
                    rePackService.insertpack2(rePack);
                    info4+=sn4+"\\0D";
                    moremain=moremain-1;
                    d++;
                }
            }

            //System.out.println(a);
            rePackService.update_moremain02(moid);
            ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：241)");
            testW(p,info1,info2,info3,info4,WboxId);
            p.resetZpl();

            if (moremain>=0){
                return JsonMsg.success();
            }else {
                return JsonMsg.fail();
            }
        }


        //内包装操作信息修改
        @RequestMapping(value = "/updateModal",method = RequestMethod.PUT)
        @ResponseBody
        public JsonMsg updateModal(String productionSN){
            int res=rePackService.updateModal(productionSN);
            if (res==1){
                return JsonMsg.success();
            }else {
                return JsonMsg.fail();
            }
        }


}
