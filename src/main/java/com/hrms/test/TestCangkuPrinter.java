package com.hrms.test;

import com.hrms.bean.Baozhuang;
import com.hrms.service.BaozhuangService;
import com.hrms.util.ZplPrinter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestCangkuPrinter {

    @Autowired
    private BaozhuangService baozhuangService;

    @Autowired
    private ZplPrinter p;

    @Test
    public void printN() {
        //二维码内的信息
        String Nbox = "XSN2010280005";
        String lineoption = "仓库线";
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
        p.setChar1(b,268,630,75,26);
        String zpl = p.getZpl();
        boolean result = p.print(zpl);//打印
        return result;
    }
}
