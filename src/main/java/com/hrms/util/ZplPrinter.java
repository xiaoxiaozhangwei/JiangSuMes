package com.hrms.util;

import javax.print.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ZplPrinter {

    private String printerURI = null;            //打印机完整路径
    private PrintService printService = null;    //打印机服务
    private byte[] dotFont;
    private String darkness = "~SD23";    //Set Darkness设置色带颜色的深度 0-30
    private String width = "^PW1200";    //Print Width打印宽度0-1500
    private String length = "^LL800";    //Label Length标签长度0-x(暂无作用)
    private String begin = "^XA" + darkness + width;    //标签格式以^XA开始
    private String end = "^XZ";            //标签格式以^XZ结束
    private String content = "";        //打印内容
    private String message = "";        //打印的结果信息

    /**
     * 构造方法
     *
     * @param printerURI 打印机路径
     */
    public ZplPrinter(String printerURI) {
        this.printerURI = printerURI;
        //加载字体
        File file = new File("C:\\Users\\Administrator\\Desktop\\ts24.lib");
        if (file.exists()) {
            FileInputStream fis;
            try {
                fis = new FileInputStream(file);
                dotFont = new byte[fis.available()];
                fis.read(dotFont);
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("C://ts24.lib文件不存在");
        }
        //初始化打印机
        //AppContext.getAppContext().put(PrintServiceLookup.class.getDeclaredClasses()[0], null);//刷新打印机列表
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        if (services != null && services.length > 0) {
            for (PrintService service : services) {
                if (printerURI.equals(service.getName())) {
                    printService = service;
                    break;
                }
            }
        }
        if (printService == null) {
            //System.out.println("没有找到打印机：["+printerURI+"]");
            //循环出所有的打印机
            if (services != null && services.length > 0) {
                //System.out.println("可用的打印机列表：");
                for (PrintService service : services) {
                    //System.out.println("["+service.getName()+"]");
                }
            }
        } else {
            //	System.out.println("找到打印机：["+printerURI+"]");
            //System.out.println("打印机名称：["+printService.getAttribute(PrinterName.class).getValue()+"]");
        }
    }

    /**
     * 设置条形码
     *
     * @param barcode 条码字符
     * @param zpl     条码样式模板
     */

    public void setBarcode(String barcode, String zpl) {
        content += zpl.replace("${data}", barcode);
    }


    /**
     * 中文字符、英文字符(包含数字)混合
     *
     * @param str 中文、英文
     * @param x   x坐标
     * @param y   y坐标
     * @param eh  英文字体高度height
     * @param ew  英文字体宽度width
     * @param es  英文字体间距spacing
     * @param mx  中文x轴字体图形放大倍率。范围1-10，默认1
     * @param my  中文y轴字体图形放大倍率。范围1-10，默认1
     * @param ms  中文字体间距。24是个比较合适的值。
     */
    public void setText(String str, int x, int y, int eh, int ew, int es, int mx, int my, int ms) {
        byte[] ch = str2bytes(str);
        for (int off = 0; off < ch.length; ) {
            if (((int) ch[off] & 0x00ff) >= 0xA0) {//中文字符
                try {
                    int qcode = ch[off] & 0xff;
                    int wcode = ch[off + 1] & 0xff;
                    content += String.format("^FO%d,%d^A@N,100,100,E:ARI000.FNT^XG0000%01X%01X,%d,%d^FS\n", x, y, qcode, wcode, mx, my);
                    begin += String.format("~DG0000%02X%02X,00072,003,\n", qcode, wcode);
                    qcode = (qcode + 128 - 32) & 0x00ff;
                    wcode = (wcode + 128 - 32) & 0x00ff;
                    int offset = ((int) qcode - 16) * 94 * 72 + ((int) wcode - 1) * 72;
                    for (int j = 0; j < 72; j += 3) {
                        qcode = (int) dotFont[j + offset] & 0x00ff;
                        wcode = (int) dotFont[j + offset + 1] & 0x00ff;
                        int qcode1 = (int) dotFont[j + offset + 2] & 0x00ff;
                        begin += String.format("%02X%02X%02X\n", qcode, wcode, qcode1);
                    }
                    x = x + ms * mx;
                    off = off + 2;
                } catch (Exception e) {
                    e.printStackTrace();
                    //替换成X号
                    setChar("X", x, y, eh, ew);
                    x = x + es;//注意间距更改为英文字符间距
                    off = off + 2;
                }
            } else if (((int) ch[off] & 0x00FF) < 0xA0) {//英文字符
                setChar(String.format("%c", ch[off]), x, y, eh, ew);
                x = x + es;
                off++;
            }
        }
    }


    public void setText2(String str, int x, int y, int eh, int ew, int es, int mx, int my, int ms) {
        byte[] ch = str2bytes(str);
        for (int off = 0; off < ch.length; ) {
            if (((int) ch[off] & 0x00ff) >= 0xA0) {//中文字符
                try {
                    int qcode = ch[off] & 0xff;
                    int wcode = ch[off + 1] & 0xff;
                    content += String.format("^FO%d,%d^A@N,100,100,E:ARI001.FNT^XG0000%01X%01X,%d,%d^FS\n", x, y, qcode, wcode, mx, my);
                    begin += String.format("~DG0000%02X%02X,00072,003,\n", qcode, wcode);
                    qcode = (qcode + 128 - 32) & 0x00ff;
                    wcode = (wcode + 128 - 32) & 0x00ff;
                    int offset = ((int) qcode - 16) * 94 * 72 + ((int) wcode - 1) * 72;
                    for (int j = 0; j < 72; j += 3) {
                        qcode = (int) dotFont[j + offset] & 0x00ff;
                        wcode = (int) dotFont[j + offset + 1] & 0x00ff;
                        int qcode1 = (int) dotFont[j + offset + 2] & 0x00ff;
                        begin += String.format("%02X%02X%02X\n", qcode, wcode, qcode1);
                    }
                    x = x + ms * mx;
                    off = off + 2;
                } catch (Exception e) {
                    e.printStackTrace();
                    //替换成X号
                    setChar("X", x, y, eh, ew);
                    x = x + es;//注意间距更改为英文字符间距
                    off = off + 2;
                }
            } else if (((int) ch[off] & 0x00FF) < 0xA0) {//英文字符
                setChar(String.format("%c", ch[off]), x, y, eh, ew);
                x = x + es;
                off++;
            }
        }
    }


    public void setText3(String str, int x, int y, int eh, int ew, int es, int mx, int my, int ms) {
        byte[] ch = str2bytes(str);
        for (int off = 0; off < ch.length; ) {
            if (((int) ch[off] & 0x00ff) >= 0xA0) {//中文字符
                try {
                    int qcode = ch[off] & 0xff;
                    int wcode = ch[off + 1] & 0xff;
                    content += String.format("^FO%d,%d^A@N,100,100,E:ARI000.FNT^XG0000%01X%01X,%d,%d^FS\n", x, y, qcode, wcode, mx, my);
                    begin += String.format("~DG0000%02X%02X,00072,003,\n", qcode, wcode);
                    qcode = (qcode + 128 - 32) & 0x00ff;
                    wcode = (wcode + 128 - 32) & 0x00ff;
                    int offset = ((int) qcode - 16) * 94 * 72 + ((int) wcode - 1) * 72;
                    for (int j = 0; j < 72; j += 3) {
                        qcode = (int) dotFont[j + offset] & 0x00ff;
                        wcode = (int) dotFont[j + offset + 1] & 0x00ff;
                        int qcode1 = (int) dotFont[j + offset + 2] & 0x00ff;
                        begin += String.format("%02X%02X%02X\n", qcode, wcode, qcode1);
                    }
                    x = x + ms * mx;
                    off = off + 2;
                } catch (Exception e) {
                    e.printStackTrace();
                    //替换成X号
                    setChar("X", x, y, eh, ew);
                    x = x + es;//注意间距更改为英文字符间距
                    off = off + 2;
                }
            } else if (((int) ch[off] & 0x00FF) < 0xA0) {//英文字符
                setChar(String.format("%c", ch[off]), x, y, eh, ew);
                x = x + es;
                off++;
            }
        }
    }


    /**
     * 英文字符串(包含数字)
     *
     * @param str 英文字符串
     * @param x   x坐标
     * @param y   y坐标
     * @param h   高度
     * @param w   宽度
     */


    public void setChar(String str, int x, int y, int h, int w) {
        content += "^FO" + x + "," + y + "^A0," + h + "," + w + "^FD" + str + "^FS";

    }

    public void setChar1(String str, int x, int y, int h, int w) {
        content += "^FO" + x + "," + y + "^A@N," + h + "," + w + "," + "E:ARI000.FNT" + "^FD" + str + "^FS";

    }

    public void setChar2(String str, int x, int y, int h, int w) {
        content += "^FO" + x + "," + y + "^A@N," + h + "," + w + "," + "E:ARI001.FNT" + "^FD" + str + "^FS";

    }

    public void setChar4(String str, int x, int y, int h, int w) {
        content += "^FO" + x + "," + y + "^AB," + h + "," + w + "," + "E:ARI000.FNT" + "^FD" + str + "^FS";

    }

    public void setXian() {
        //	content += "^FO"+x+","+y+"^GB343,3,3^FS";

        content +=
                "^FO480,105^GB420,3,5^FS" +
                        "^FO400,180^GB500,3,5^FS" +
                        "^FO250,259^GB650,3,5^FS" +
                        "^FO380,353^GB525,3,5^FS" +
                        "^FO455,430^GB440,3,5^FS" +
                        "^FO260,520^GB630,3,5^FS" +
                        "^FO490,600^GB415,3,5^FS";
    }

    public void setXian2() {
        //	content += "^FO"+x+","+y+"^GB343,3,3^FS";

        content += "^FO590,145^GB600,3,5^FS" +
                "^FO475,245^GB700,3,5^FS" +
                "^FO320,345^GB835,3,5^FS" +
                "^FO460,445^GB680,3,5^FS" +
                "^FO550,545^GB610,3,5^FS" +
                "^FO320,645^GB820,3,5^FS" +
                "^FO580,745^GB610,3,5^FS" +
                "^FO380,845^GB790,3,5^FS" +
                "^FO330,945^GB820,3,5^FS" +
                "^FO330,1045^GB820,3,5^FS"
        ;
    }


    /**
     * 英文字符(包含数字)顺时针旋转90度
     *
     * @param str 英文字符串
     * @param x   x坐标
     *            * @param y y坐标
     * @param h   高度
     * @param w   宽度
     */
    public void setCharR(String str, int x, int y, int h, int w) {
        content += "^FO" + x + "," + y + "^A0R," + h + "," + w + "^FD" + str + "^FS" + "";
    }

    /**
     * 获取完整的ZPL
     *
     * @return
     */
    public String getZpl() {
        return begin + content + end;
    }

    /**
     * 重置ZPL指令，当需要打印多张纸的时候需要调用。
     */

    public void resetZpl() {
        begin = "^XA" + darkness + width;
        end = "^XZ";
        content = "";
    }

    /**
     * 打印
     *
     * @param zpl 完整的ZPL
     */
    public boolean print(String zpl) {
        if (printService == null) {
            message = "打印出错：没有找到打印机[" + printerURI + "]";
            //	System.out.println("打印出错：没有找到打印机["+printerURI+"]");
            return false;
        }
        DocPrintJob job = printService.createPrintJob();
        byte[] by = zpl.getBytes();
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(by, flavor, null);
        try {
            job.print(doc, null);
            message = "已打印";
            //	System.out.println("已打印");
            return true;
        } catch (PrintException e) {
            message = "打印出错:" + e.getMessage();
            //	System.out.println("打印出错:"+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public String getMessage() {
        return message;
    }

    /**
     * 字符串转byte[]
     *
     * @param s
     * @return
     */
    private byte[] str2bytes(String s) {
        if (null == s || "".equals(s)) {
            return null;
        }
        byte[] abytes = null;
        try {
            abytes = s.getBytes("gb2312");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return abytes;
    }


    /**
     * @param unit    单位名称
     * @param orderId 工单号
     * @param type    类别
     */
    public static boolean printPicking(String unit, String orderId, String type) {
        ZplPrinter p = new ZplPrinter("ZDesigner ZT410-600dpi ZPL");
        p.setText(unit, 253, 26, 40, 40, 20, 1, 1, 24);
        p.setChar(orderId, 253, 76, 20, 20);
        p.setText(type, 253, 120, 53, 53, 20, 1, 1, 24);
        String zpl = p.getZpl();
        //System.out.println(zpl);
        boolean flag = p.print(zpl);
        return flag;
    }


    //打印SN标签模板
    public void setSN2(String title, String model, String sn) {
        content += "^FT855,404^A0I,45,45^FH\\" + "^FD" + title + "^FS" +
                "^BY2,3,65^FT855,325^BCI,,N,N\r\n" +
                "^FD>:" + model + "^FS\r\n" +
                "^FT855,286^A0I,33,33^FH\\^FDModel:^FS\r\n" +
                "^FT735,285^A0I,33,33^FH\\^FD" + model + "^FS\r\n" +
                "^BY2,3,65^FO410,210^BCN,,N,N,N,N\r\n" +
                "^FD" + sn + "^FS\r\n" +
                "^FT855,168^A0I,33,33^FH\\^FDS/N:^FS\r\n" +
                "^FT795,168^A0I,33,33^FH\\^FD" + sn + "^FS";


    }

    //打印WWN标签模板1

    public void setWWN(String FW, String title, String model, String sn, String wwn) {
        content += "^FT294,52^A@N,30,8,E:ARI001.FNT\\^FDFW:^FS\r\n" +
                "^FT334,52^A@N,30,8,E:ARI001.FNT\\^FD" + FW + "^FS" +
                "^FT294,80^A@N,30,8,E:ARI001.FNT\\" + "^FD" + title + "^FS" +
                "^BY2,3,20^FT294,105^BCN,,N,N\r\n" +
                "^FD>:" + model + "^FS\r\n" +
                "^FT294,123^A@N,30,8,E:ARI001.FNT\\^FDModel:^FS\r\n" +
                "^FT364,124^A@N,30,8,E:ARI001.FNT\\^FD" + model + "^FS\r\n" +
                "^BY1,3,20^FO294,126^BCN,,N,N,N,N\r\n" +
                "^FD" + sn + "^FS\r\n" +
                "^FT294,165^A@N,30,8,E:ARI001.FNT\\^FDS/N:^FS\r\n" +
                "^FT334,165^A@N,30,8,E:ARI001.FNT\\^FD" + sn + "^FS" +
                "^BY1,3,20^FO294,170^BCN,,N,N,N,N\r\n" +
                "^FD" + wwn + "^FS\r\n" +
                "^FT294,210^A@N,30,8,E:ARI001.FNT\\^FDWWN:^FS\r\n" +
                "^FT359,210^A@N,30,8,E:ARI001.FNT\\^FD" + wwn + "^FS";


    }

    //打印SN标签   SN标签模板
    public void setSNmuban(String title, String model, String sn) {
        content +=
                "^FT80,200^A0N,46,40" + "^FD" + title + "^FS" +
                        "^BY3,3,68^FT80,276^BCN,,N,N\r\n" +
                        "^FD>:" + model + "^FS\r\n" +
                        "^FT80,310^A0N,33,33^FH\\^FDModel:^FS\r\n" +
                        "^FT180,310^A0N,33,33^FH\\^FD" + model + "^FS\r\n" +
                        "^BY3,3,68^FO80,325^BCN,,N,N,N,N\r\n" +
                        "^FD" + sn + "^FS\r\n" +
                        "^FT80,425^A0N,33,33^FH\\^FDS/N:^FS\r\n" +
                        "^FT150,425^A0N,33,33^FH\\^FD" + sn + "^FS";

    }


    // M.2版本 （单个标签)
    public void M2Simple(String FW, String title, String model, String sn, String wwn) {
        content += "^FT280,48^A0N,25,25\\^FDFW:^FS\r\n" +
                "^FT320,48^A0N,25,25\\^FD" + FW + "^FS" +
                "^FT280,71^A0N,25,25\\" + "^FD" + title + "^FS" +
                "^FT280,95^A0N,25,25\\^FDModel:^FS\r\n" +
                "^FT350,96^A0N,25,25\\^FD" + model + "^FS\r\n" +
                "^BY1,3,30^FO280,100^BCN,,N,N,N,N\r\n" +
                "^FD" + sn + "^FS\r\n" +
                "^FT280,152^A0N,25,25\\^FDS/N:^FS\r\n" +
                "^FT320,152^A0N,25,25\\^FD" + sn + "^FS" +
                "^BY1,3,30^FO280,157^BCN,,N,N,N,N\r\n" +
                "^FD" + wwn + "^FS\r\n" +
                "^FT280,208^A0N,23,25\\^FDWWN:^FS\r\n" +
                "^FT345,208^A0N,23,25\\^FD" + wwn + "^FS";
    }


    //M.2双标签模板
    public void setM2WWN(String FW, String title, String model, String sn, String wwn, String sn1, String wwn1) {
        content += "^FT200,207^A0N,28,21\\^FDFW:^FS\r\n" +
                "^FT250,207^A0N,28,21\\^FD" + FW + "^FS" +
                "^FT185,46^A0N,31,21\\" + "^FD" + title + "^FS" +
                "^FT17,73^A0N,24,21\\^FDModel:^FS\r\n" +
                "^FT87,74^A0N,24,21\\^FD" + model + "^FS\r\n" +
                "^BY1,6,29^FO17,76^BCN,,N,N,N,N\r\n" +
                "^FD" + sn + "^FS\r\n" +
                "^FT17,126^A0N,26,21\\^FDS/N:^FS\r\n" +
                "^FT65,126^A0N,26,21\\^FD" + sn + "^FS" +
                "^BY1,6,29^FO17,128^BCN,,N,N,N,N\r\n" +
                "^FD" + wwn + "^FS\r\n" +
                "^FT17,175^A0N,23,21\\^FDWWN:^FS\r\n" +
                "^FT85,175^A0N,23,21\\^FD" + wwn + "^FS" +

                "^FT620,207^A0N,28,21\\^FDFW:^FS\r\n" +
                "^FT670,207^A0N,28,21\\^FD" + FW + "^FS" +
                "^FT620,46^A0N,31,21\\" + "^FD" + title + "^FS" +
                "^FT455,73^A0N,24,21\\^FDModel:^FS\r\n" +
                "^FT525,74^A0N,24,21\\^FD" + model + "^FS\r\n" +
                "^BY1,6,29^FO455,76^BCN,,N,N,N,N\r\n" +
                "^FD" + sn1 + "^FS\r\n" +
                "^FT455,126^A0N,26,21\\^FDS/N:^FS\r\n" +
                "^FT505,126^A0N,26,21\\^FD" + sn1 + "^FS" +
                "^BY1,6,29^FO455,128^BCN,,N,N,N,N\r\n" +
                "^FD" + wwn1 + "^FS\r\n" +
                "^FT455,175^A0N,23,21\\^FDWWN:^FS\r\n" +
                "^FT525,175^A0N,23,21\\^FD" + wwn1 + "^FS";
    }
    //设置内箱模板-model号
	/*
	public void  setNeimodel(String str, int x, int y)
	{
		content +="^FO" + x + "," + y +"^A0N,45,35,E:ARI001.FNT\\"+"^FD"+str+"^FS";
	}*/

    public void setNeimodel(String str) {
        if (str.length() > 13) {
            content += "^FO448,390^A0N,50,32,E:ARI001.FNT\\" + "^FD" + str + "^FS";
        } else {
            content += "^FO450,400^A@N,15,15," + "E:ARI000.FNT" + "^FD" + str + "^FS";
        }
    }

    //设置内箱模板-工单号
    public void setNeiMo(String str, int x, int y) {
        content += "^FO" + x + "," + y + "^A0N,50,45\\" + "^FD" + str + "^FS";
    }

    //设置外箱模板-model号
    public void setWboxmodel(String str, int x, int y) {
        if (str.length() > 13) {
            content += "^FO" + x + "," + y + "^A0N,50,50,E:ARI001.FNT\\" + "^FD" + str + "^FS";
        } else {
            content += "^FO" + x + "," + y + "^A0N,50,50,E:ARI001.FNT\\" + "^FD" + str + "^FS";
        }
    }

    public void setNeiSPEC(String str) {
        if (str.length() > 13) {
            content += "^FO405,310^A0N,50,40,E:ARI001.FNT\\" + "^FD" + str + "^FS";
        } else {
            content += "^FO405,325^A@N,15,15," + "E:ARI000.FNT" + "^FD" + str + "^FS";
        }

    }

    public void setzhuanyin(String fw, String model, String sn) {
        content = "^BY2,3,45^FO55,43^BCN,,N,N\r\n" +
                "^FD>:" + model + "^FS\r\n" +
                "^FT55,122^A0N,33,36^FH\\^FDModel:^FS\r\n" +
                "^FT168,122^A0N,37,36^FH\\^FD" + model + "^FS\r\n" +

                "^BY2,3,45^FO55,128^BCN,,N,N,N,N\r\n" +
                "^FD" + sn + "^FS\r\n" +

                "^FT55,206^A0N,36,36^FH\\^FDS/N:^FS" +
                "^FT125,206^A0N,36,36^FH\\^FD" + sn + "^FS" +
                "^FT486,206^A0N,33,35^FH\\^FDFW:^FS\r\n" +
                "^FT550,206^A0N,33,35^FH\\^FD" + fw + "^FS";
    }


    /*
     * SN标签模板
     *
     *
     * */
    //模板一  2.5寸常规
    public void printmuban1(String title, String model, String sn) {
        content += "^FT71,208^A0N,38,47^FH\\^FD" + title + "^FS\n" +
                "^BY3,3,75^FT71,300^BCN,,N,N\n" + "^FD" + model + "^FS\n" +
                "^FT71,335^A0N,33,33^FH\\^FDModel: " + model + "^FS\n" +
                "^BY3,3,75^FT71,419^BCN,,N,N\n" + "^FD" + sn + "^FS\n" +
                "^FT71,455^A0N,33,38^FH\\^FDS/N: " + sn + "^FS";
    }

    //模板二
    public void printmuban2(String title, String model, String sn) {
        content += "^XA~TA000~JSN^LT0^MNW^MTT^PON^PMN^LH0,0^JMA^PR4,4~SD23^JUS^LRN^CI0^XZ" +
                "^XA\n" +
                "^MMT\n" +
                "^PW831\n" +
                "^LL0486\n" +
                "^LS0" +
                "^FO150,61^AAN,17,18\\^FD" + title + "^FS\n" +
                "^BY2,3,80^FT150,187^BCN,,N,N\n" +
                "^FD>:" + model + "^FS\n" +
                "^FO150,229^ABN,15,16^FH\\^FDModel:" + model + "^FS\n" +
                "^BY2,3,80^FT150,332^BCN,,N,N\n" +
                "^FD>:" + sn + "^FS\n" +
                "^FO150,374^ABN,15,16^FH\\^FDS/N:" + sn + "^FS" + "\n" +
                "^PQ1,0,1,Y^XZ";

    }

    //模板四 M.2带FW
    public void printmuban_M2_FW(String title, String FW, String model, String sn) {
        //String title1=title.substring(0, title.indexOf("F"));
        //String title2=title.substring(title.indexOf("F"));
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
                "^FT280,201^A0N,20,24^FH\\^FDFW:" + FW + "^FS\n" +
                "^PQ1,0,1,Y^XZ";
    }


    //test
    public void printmuban_M2_FW2(String title, String FW, String model, String sn) {
        //String title1=title.substring(0, title.indexOf("F"));
        //String title2=title.substring(title.indexOf("F"));
        content += "^XA~TA000~JSN^LT0^MNW^MTT^PON^PMN^LH0,0^JMA^PR4,4~SD23^JUS^LRN^CI0^XZ\n" +
                "^XA\n" +
                "^MMT\n" +
                "^PW809\n" +
                "^LL0325\n" +
                "^LS0\n" +
                "^FT260,50^A0N,20,23^FH\\^FD" + title + "^FS\n" +
                "^BY2,3,40^FT260,94^BCN,,N,N\n" +
                "^FD>:" + model + "^FS\n" +
                "^BY2,3,40^FT260,154^BCN,,N,N\n" +
                "^FD>;" + sn.substring(0, 8) + ">6" + sn.substring(8, 11) + ">5" + sn.substring(11) + "^FS\n" +
                "^FT260,113^A0N,20,24^FH\\^FDModel:" + model + "^FS\n" +
                "^FT260,173^A0N,20,24^FH\\^FDS/N:" + sn + "^FS\n" +
                "^FT260,196^A0N,20,24^FH\\^FDFW:" + FW + "^FS\n" +
                "^PQ1,0,1,Y^XZ";
    }
}
