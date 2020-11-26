package com.hrms.test;

import com.hrms.util.ZplPrinter;
import org.junit.Test;

public class m2Label {
    private String content = "";        //打印内容

    @Test
    public void PrintSN( ) {
        String model = "GG9ZM240S3C27";
        String productsn = "204309480G0000007";
        String title = "123456789";
        String fw = "F5CI103";
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-300dpi ZPL (ip:16)");
        p.printmuban_M2_FW(title,fw,model,productsn);
    }


    public void printmuban_M2_FW(String title, String FW, String model, String sn) {
        content += "^XA~TA000~JSN^LT0^MNW^MTT^PON^PMN^LH0,0^JMA^PR4,4~SD23^JUS^LRN^CI0^XZ\n" +
                "^XA\n" +
                "^MMT\n" +
                "^PW809\n" +
                "^LL0325\n" +
                "^LS0\n" +
                "^FT280,55^A0N,20,23^FH\\^FD" + title + "^FS\n" +
                "^BY2,3,40^FT280,99^BCN,,N,N\n" +
                "^FD>:" + model + "^FS\n" +
                "^BY2,3,40^FT280,159^BCN,,N,N\n" +
                "^FD>;" + sn.substring(0, 8) + ">6" + sn.substring(8, 11) + ">5" + sn.substring(11) + "^FS\n" +
                "^FT280,118^A0N,20,24^FH\\^FDModel:" + model + "^FS\n" +
                "^FT280,178^A0N,20,24^FH\\^FDS/N:" + sn + "^FS\n" +
                "^FT280,201^A0N,25,24^FH\\^FDFW:" + FW + "^FS\n" +
                "^PQ1,0,1,Y^XZ";
    }
}
