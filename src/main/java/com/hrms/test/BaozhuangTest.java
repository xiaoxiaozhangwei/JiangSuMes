package com.hrms.test;

import com.hrms.bean.Baozhuang;
import com.hrms.mapper.BaozhuangMapper;
import com.hrms.service.BaozhuangService;
import com.hrms.service.GongdanService;
import com.hrms.util.ExportExcel;
import com.hrms.util.ZplPrinter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class BaozhuangTest {
    @Autowired
    BaozhuangMapper baozhuangMapper;
    @Autowired
    BaozhuangService baozhuangService;
    @Autowired
    GongdanService gongdanService;



    @Test
    public void insertpack() {
        Integer a = baozhuangMapper.selectWboxCountByLine("Line 1");
        System.out.println(a);
    }


     @Test
     public void check()
     {
         Integer a = baozhuangMapper.production("194502256G0011165","a");
         System.out.println(a);
     }

     @Test
     public void count_Nbox()
     {
         int count=baozhuangMapper.count_Nbox();
         System.out.println(count);
     }

     @Test
     public void packSN(){
         String info="";
         List <Baozhuang> list =baozhuangMapper.packSN("XSN2004230061");
         System.out.println(list.size());
         for (int i = 0; i < list.size(); i++) {
          System.out.println(i);
             info+=i+":"+list.get(i).getProductionSN()+"\n";
         }
         System.out.println(info);
     }

     @Test
    public void mo(){
        String moid="12344";
         int res =gongdanService.update_moremain0(moid);
        System.out.println(res);
     }

     @Test
    public void insert(){
         Baozhuang baozhuang = new Baozhuang(1, "194202256G0004526", "GGAZT120S3C27", "1", "1", "P600-X072005140001", 5000, 1, "1", "XSN2005210042","","良品");
         int i=1;
         for (i=1;i<=50;i++){
             baozhuangService.insertpack(baozhuang);
         }
     }
     @Test
    public void a(){
        int res=baozhuangMapper.updateWbox("1","2","");
     }

     @Test
     public void selectProByName(){
         Integer res = baozhuangService.selectProByName("194102256G4015726","外观不良","P600-X072004300002");
         System.out.println(res);
     }

    //包装报表
    @Test
    public void exceltest(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,0);
        Date date=cal.getTime();

        //Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        System.out.println(formatter.format(date));
        List<String> list=baozhuangService.selecthismonthmodel(formatter.format(date));
        System.out.println(list);
        for (int i=0;i<list.size();i++){
            ExportExcel.writeSpecifiedCell(6,6+i*4,0,list.get(i));
        }
       for(int j=23;j<=27;j++){
           for (int i = 0; i < list.size(); i++) {
               int r = baozhuangService.selectrightcountbymodeldaily("XSW2008"+j+"%", list.get(i));
               int w = baozhuangService.selectwrongcountbymodeldaily("XSW2008"+j+"%", list.get(i));

               System.out.println("r="+r+",w="+w);
               ExportExcel.writeSpecifiedCellint(6, 7 + i * 4, 2 + j, r);
               ExportExcel.writeSpecifiedCellint(6, 8 + i * 4, 2 + j, w);
           }
       }


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
    @Test
    public void testNeimuban(){
      //  ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL");
        //testNeiMuban(p,"2.5\" SSD   256GB","P600-X071912020001","10000076","SSD@","SSDE600MS30240C-257-BGC","50 pcs","2020-05-16");
       // p.resetZpl();
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:20)");
        p.setzhuanyin("F3C1k1k","GGAZT120S3C27","203619456626295496");
        // testNeiMuban(p,"2.5\" SSD", "R600" ,"100265","2.5\" SSD","GGAe333","50 pacs","2080");
        p.resetZpl();
    }

    @Test
    public void printWbox(){
        String bar0Zpl1,bar0Zpl2,bar0Zpl3,bar0Zpl4;
        String bar1="202905032G0000158\n" +"\\0D"+
                "202905032G0000109\n" +"\\0D"+
                "202905032G0000156\n" +"\\0D"+
                "202905032G0000110\n" +"\\0D"+
                "202905032G0000157\n" +"\\0D"+
                "202905032G0000160\n" +"\\0D"+
                "202905032G0000106\n" +"\\0D"+
                "202905032G0000107\n" +"\\0D"+
                "202905032G0000108\n" +"\\0D"+
                "202905032G0000159\n" +"\\0D"+
                "202905032G0000163\n" +"\\0D"+
                "202905032G0000115\n" +"\\0D"+
                "202905032G0000165\n" +"\\0D"+
                "202905032G0000114\n" +"\\0D"+
                "202905032G0000112\n" +"\\0D"+
                "202905032G0000162\n" +"\\0D"+
                "202905032G0000164\n" +"\\0D"+
                "202905032G0000111\n" +"\\0D"+
                "202905032G0000161\n" +"\\0D"+
                "202905032G0000113\n" +"\\0D"+
                "202905032G0000168\n" +"\\0D"+
                "202905032G0000120\n" +"\\0D"+
                "202905032G0000119\n" +"\\0D"+
                "202905032G0000167\n" +"\\0D"+
                "202905032G0000116\n" +"\\0D"+
                "202905032G0000170\n" +"\\0D"+
                "202905032G0000169\n" +"\\0D"+
                "202905032G0000117\n" +"\\0D"+
                "202905032G0000166\n" +"\\0D"+
                "202905032G0000118\n" +"\\0D"+
                "202905032G0000122\n" +"\\0D"+
                "202905032G0000173\n" +"\\0D"+
                "202905032G0000124\n" +"\\0D"+
                "202905032G0000175\n" +"\\0D"+
                "202905032G0000125\n" +"\\0D"+
                "202905032G0000171\n" +"\\0D"+
                "202905032G0000121\n" +"\\0D"+
                "202905032G0000172\n" +"\\0D"+
                "202905032G0000123\n" +"\\0D"+
                "202905032G0000174\n" +"\\0D"+
                "202905032G0000127\n" +"\\0D"+
                "202905032G0000178\n" +"\\0D"+
                "202905032G0000129\n" +"\\0D"+
                "202905032G0000180\n" +"\\0D"+
                "202905032G0000126\n" +"\\0D"+
                "202905032G0000176\n" +"\\0D"+
                "202905032G0000130\n" +"\\0D"+
                "202905032G0000177\n" +"\\0D"+
                "202905032G0000179\n" +"\\0D"+
                "202905032G0000128\n" +"\\0D"+
                "202905032G0000132\n" +"\\0D"+
                "202905032G0000182\n" +"\\0D"+
                "202905032G0000134\n" +"\\0D"+
                "202905032G0000135\n" +"\\0D"+
                "202905032G0000181\n" +"\\0D"+
                "202905032G0000131\n" +"\\0D"+
                "202905032G0000185\n" +"\\0D"+
                "202905032G0000183\n" +"\\0D"+
                "202905032G0000184\n" +"\\0D"+
                "202905032G0000133\n" +"\\0D"+
                "202905032G0000188\n" +"\\0D"+
                "202905032G0000189\n" +"\\0D"+
                "202905032G0000139\n" +"\\0D"+
                "202905032G0000136\n" +"\\0D"+
                "202905032G0000140\n" +"\\0D"+
                "202905032G0000186\n" +"\\0D"+
                "202905032G0000190\n" +"\\0D"+
                "202905032G0000187\n" +"\\0D"+
                "202905032G0000137\n" +"\\0D"+
                "202905032G0000138\n" +"\\0D"+
                "202905032G0000143\n" +"\\0D"+
                "202905032G0000194\n" +"\\0D"+
                "202905032G0000144\n" +"\\0D"+
                "202905032G0000145\n" +"\\0D"+
                "202905032G0000191\n" +"\\0D"+
                "202905032G0000141\n" +"\\0D"+
                "202905032G0000195\n" +"\\0D"+
                "202905032G0000192\n" +"\\0D"+
                "202905032G0000142\n" +"\\0D"+
                "202905032G0000193";
        String bar2=bar1;
        String bar3=bar1;
        String bar4=bar1;
        String bar0="XSW2007150000";
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：24)");
        bar0Zpl1 = "^BY100,100^FT135,550^BXN,4,200,0,0,1,~" +
                        "^FH\\^FD${data}^FS\r\n";


        bar0Zpl2 = "^BY384,384^FT673,700^BXN,4,200,0,0,1,~" +
                "^FH\\^FD${data}^FS\r\n";

        bar0Zpl3 = "^BY384,384^FT135,1200^BXN,4,200,0,0,1,~" +
                "^FH\\^FD${data}^FS\r\n";

        bar0Zpl4 = "^BY384,384^FT673,1080^BXN,4,200,0,0,1,~" +
                "^FH\\^FD${data}^FS\r\n";

        p.setBarcode(bar1, bar0Zpl2);
        p.setBarcode(bar2, bar0Zpl3);
        p.setBarcode(bar3, bar0Zpl4);
        p.setBarcode(bar4, bar0Zpl1);
        p.setChar1(bar0,400,1120,80,28);
        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
    }

    @Test
    public void print()
    { String bar0Zpl1,bar0Zpl2,bar0Zpl3,bar0Zpl4;
        String bar1="202905032G0000158\n" +"\\0D"+
                "202905032G0000109\n" +"\\0D"+
                "202905032G0000156\n" +"\\0D"+
                "202905032G0000110\n" +"\\0D"+
                "202905032G0000157\n" +"\\0D"+
                "202905032G0000160\n" +"\\0D"+
                "202905032G0000106\n" +"\\0D"+
                "202905032G0000107\n" +"\\0D"+
                "202905032G0000108\n" +"\\0D"+
                "202905032G0000159\n" +"\\0D"+
                "202905032G0000163\n" +"\\0D"+
                "202905032G0000115\n" +"\\0D"+
                "202905032G0000165\n" +"\\0D"+
                "202905032G0000114\n" +"\\0D"+
                "202905032G0000112\n" +"\\0D"+
                "202905032G0000162\n" +"\\0D"+
                "202905032G0000164\n" +"\\0D"+
                "202905032G0000111\n" +"\\0D"+
                "202905032G0000161\n" +"\\0D"+
                "202905032G0000113\n" +"\\0D"+
                "202905032G0000168\n" +"\\0D"+
                "202905032G0000120\n" +"\\0D"+
                "202905032G0000119\n" +"\\0D"+
                "202905032G0000167\n" +"\\0D"+
                "202905032G0000116\n" +"\\0D"+
                "202905032G0000170\n" +"\\0D"+
                "202905032G0000169\n" +"\\0D"+
                "202905032G0000117\n" +"\\0D"+
                "202905032G0000166\n" +"\\0D"+
                "202905032G0000118\n" +"\\0D"+
                "202905032G0000122\n" +"\\0D"+
                "202905032G0000173\n" +"\\0D"+
                "202905032G0000124\n" +"\\0D"+
                "202905032G0000175\n" +"\\0D"+
                "202905032G0000125\n" +"\\0D"+
                "202905032G0000171\n" +"\\0D"+
                "202905032G0000121\n" +"\\0D"+
                "202905032G0000172\n" +"\\0D"+
                "202905032G0000123\n" +"\\0D"+
                "202905032G0000174\n" +"\\0D"+
                "202905032G0000127\n" +"\\0D"+
                "202905032G0000178\n" +"\\0D"+
                "202905032G0000129\n" +"\\0D"+
                "202905032G0000180\n" +"\\0D"+
                "202905032G0000126\n" +"\\0D"+
                "202905032G0000176\n" +"\\0D"+
                "202905032G0000130\n" +"\\0D"+
                "202905032G0000177\n" +"\\0D"+
                "202905032G0000179\n" +"\\0D"+
                "202905032G0000128\n" +"\\0D"+
                "202905032G0000132\n" +"\\0D"+
                "202905032G0000182\n" +"\\0D"+
                "202905032G0000134\n" +"\\0D"+
                "202905032G0000135\n" +"\\0D"+
                "202905032G0000181\n" +"\\0D"+
                "202905032G0000131\n" +"\\0D"+
                "202905032G0000185\n" +"\\0D"+
                "202905032G0000183\n" +"\\0D"+
                "202905032G0000184\n" +"\\0D"+
                "202905032G0000133\n" +"\\0D"+
                "202905032G0000188\n" +"\\0D"+
                "202905032G0000189\n" +"\\0D"+
                "202905032G0000139\n" +"\\0D"+
                "202905032G0000136\n" +"\\0D"+
                "202905032G0000140\n" +"\\0D"+
                "202905032G0000186\n" +"\\0D"+
                "202905032G0000190\n" +"\\0D"+
                "202905032G0000187\n" +"\\0D"+
                "202905032G0000137\n" +"\\0D"+
                "202905032G0000138\n" +"\\0D"+
                "202905032G0000143\n" +"\\0D"+
                "202905032G0000194\n" +"\\0D"+
                "202905032G0000144\n" +"\\0D"+
                "202905032G0000145\n" +"\\0D"+
                "202905032G0000191\n" +"\\0D"+
                "202905032G0000141\n" +"\\0D"+
                "202905032G0000195\n" +"\\0D"+
                "202905032G0000192\n" +"\\0D"+
                "202905032G0000142\n" +"\\0D"+
                "202905032G0000193";
        String bar2=bar1;
        String bar3=bar1;
        String bar4=bar1;
        String bar0="XSW2007150000";
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：24)");
        bar0Zpl1 = "^BY100,100^FT135,550^BXN,4,200,0,0,1,~" +
                "^FH\\^FD${data}^FS\r\n";

        bar0Zpl2 = "^BY384,384^FT673,550^BXN,4,200,0,0,1,~" +
                "^FH\\^FD${data}^FS\r\n";

        bar0Zpl3 = "^BY384,384^FT135,1080^BXN,4,200,0,0,1,~" +
                "^FH\\^FD${data}^FS\r\n";

        bar0Zpl4 = "^BY384,384^FT673,1080^BXN,4,200,0,0,1,~" +
                "^FH\\^FD${data}^FS\r\n";

        p.setBarcode(bar1, bar0Zpl2);
        p.setBarcode(bar2, bar0Zpl3);
        p.setBarcode(bar3, bar0Zpl4);
        p.setBarcode(bar4, bar0Zpl1);
        p.setChar1(bar0,400,1135,80,28);
        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印

    }
}