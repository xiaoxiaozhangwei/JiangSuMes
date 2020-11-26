package com.hrms.test;

import com.hrms.bean.SNlabel;
import com.hrms.mapper.SNlabelMapper;
import com.hrms.service.SNlabelService;
import com.hrms.util.ZplPrinter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class SNlabelTest {
    @Autowired
    SNlabelService sNlabelService;
    @Autowired
    SNlabelMapper sNlabelMapper;





    @Test
    public void insertwwnrule(){
        System.out.println("1");
        SNlabel sNlabel =new SNlabel("1","1","1","1","1","snrulename",12,"erp",1,12, "rule","mesg","a",6,1,2,"a","2");
        sNlabelService.insertwwnrule(sNlabel);
        System.out.println("2");
    }

    @Test
    public void selectwwnrule(){
        System.out.println("1");
        List<SNlabel> list= sNlabelMapper.selectwwnrule();
        System.out.println("2");
        int i=0;
        for (i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

    }

    @Test
    public void selectsnrulebyname(){
        List<SNlabel> rules=sNlabelService.selectsnrulebyname("er");
        for (int i=0;i<rules.size();i++){
            System.out.println(rules.get(i));
        }
    }

    @Test
    public void selectbytoday(){
        //int count=sNlabelMapper.selectbytoday("a");
        System.out.println("count");
    }

    @Test
    public void insertlabel(){
        SNlabel sNlabel=new SNlabel("P600-X072007010001","","","GG5ZT032S3IN4SS","operator","SN",1,"",1,1,"","","WWN",1,1,1,"","");
        for (int i=1;i<=360;i++){
              sNlabelService.insertlabel(sNlabel);
        }
    }

    @Test
    public void printsn(){
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (IP：20)");

        p.setzhuanyin("F3C1K101","GG2ZT256S3C27","123456789AS123456");

        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
    }


    @Test
    public void printSN(){
        //System.out.println("1");
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:20)");

        //System.out.println("2");
      testNeiMuban(p);
    }

    private static boolean testNeiMuban(ZplPrinter p)
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
        p.setChar1("2.5 SSD 128Gb", 500, 90, 15, 15);
        // p.setChar1(Lot_No,  415, 160, 15, 1);
        p.setNeiMo("P600-X0823342322222",415,140);
        p.setChar1("10012642",  500, 240, 15, 15);
        //p.setChar1(SPEC,  490, 330, 15, 15);
        p.setChar1("2.5 SSD",  425, 330, 15, 15);
        p.setNeimodel("GG2ZT156GC44");
        //p.setChar1(Model_No,  492, 410, 15, 15);
        p.setChar1("50 psc",  520, 500, 15, 15);
        p.setChar1("2020-08-98",  565, 580,15, 15);
        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }
}
