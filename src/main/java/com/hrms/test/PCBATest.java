package com.hrms.test;


import com.hrms.bean.PCBAData;
import com.hrms.bean.QC;
import com.hrms.mapper.QCMapper;
import com.hrms.service.*;
import com.hrms.util.ExportExcel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class PCBATest {
    @Autowired
    OC2Service oc2Service;
    @Autowired
    QCMapper qcMapper;
    @Autowired
    QCService qcService;
    @Autowired
    OC4Service oc4Service;
    @Autowired
    OC1Service oc1Service;
    @Autowired
    BitService bitService;
    @Autowired
    CDIService cdiService;

    @Test
    public void insertOC2() throws IOException {
        System.out.println("starrt");
        String filepath = "D:\\Test_OC2";//D盘下的file文件夹的目录
        System.out.println("Test_OC2");
        File fileDesc = new File("D:\\备份OC2");
        System.out.println("备份OC2");
        File file = new File(filepath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        String encoding = "GBK";
        String data[] = new String[25];
        PCBAData pcbaData = null;
        System.out.println(fileList.length);
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                // 使用FileInputStream读取文本内容，然后通过InputStreamReader和指定的编码将字符转换为字节
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(fileList[i]), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int j = 0;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    data[j] = lineTxt.substring(lineTxt.indexOf("=") + 1).trim();
                    j++;
                }
                copyFile(fileList[i], fileDesc);
                if (j == 9) {
                    pcbaData = new PCBAData(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[14]);
                    if (oc2Service.insertOC2(pcbaData) == 1) {
                        read.close();
                        bufferedReader.close();
                        System.gc();
                        System.out.println("OC2数据第" + i + "个在删除：  " + fileList[i].delete());
                    }
                } else {
                    pcbaData = new PCBAData(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[14]);
                    if (oc2Service.insertOC2(pcbaData) == 1) {
                        read.close();
                        bufferedReader.close();
                        System.gc();
                        System.out.println("OC2数据第" + i + "个在删除：  " + fileList[i].delete());
                    }
                }
            }
        }
    }

    public static void copyFile(File srcFile, File destDir) throws IOException {
        //创建输入流对象 将源文件File对象传入其形参
        FileInputStream fis = new FileInputStream(srcFile);
        //获取目标路径的File对象，关键在于目标路径问题，注意复制访问
        // 只能是文件不能是文件夹 否则拒绝访问，getpath()得到具体带盘符的路径
        // getName()得到的是文件的名称，两者字符串拼接就是具体的目标路径（目标文件//路径）
        File file = new File(destDir.getPath() + File.separator + srcFile.getName());
        //****出错关键点******         //判断目标文件夹File对象（路径）是否存在，不存在则创建
        //创建输出流对象
        FileOutputStream bos = new FileOutputStream(file);       //常规的读写复制
        int len;
        byte[] b = new byte[1024];
        while ((len = fis.read(b)) != -1) {
            bos.write(b, 0, len);
            bos.flush();
        }
        //关闭资源
        bos.close();
        fis.close();

        System.out.println("复制成功" + bos.toString());
        //return true;
    }

    @Test
    public void insertqc() throws IOException {
        String filepath = "D:\\Test_QC";//D盘下的file文件夹的目录
        File file = new File(filepath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        //   List<File> wjList = new ArrayList<File>();//新建一个文件集合
        File fileDesc = new File("D:\\测试数据备份\\QC备份");
        String encoding = "GBK";
        String data[] = new String[20];
        QC qc = null;
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {// 使用FileInputStream读取文本内容，然后通过InputStreamReader和指定的编码将字符转换为字节
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(fileList[i]), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int j = 0;
                while ((lineTxt = bufferedReader.readLine()) != null) {//逐行读取
                    // System.out.println(lineTxt.substring(lineTxt.indexOf("=")+1));//读取“=”后面的字符串
                    data[j] = lineTxt.substring(lineTxt.indexOf(":") + 1).trim();
                    j++;
                }

                copyFile(fileList[i], fileDesc);
                qc = new QC(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9]);
                if (qcService.insertQC(qc) == 1) {
                    read.close();
                    bufferedReader.close();
                    System.gc();
                    System.out.println("QC第" + i + "个在删除：  " + fileList[i].delete());
                }


            }

        }

    }


    //OC1报表
    @Test
    public void oc1exceltest() {
        System.out.println("定时产生报表！ 时间是：" + new Date().toString());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date date = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //OC1报表
        List<String> oc1list = oc1Service.selecthismonthmodel(formatter.format(date));
        System.out.println(oc1list.size());
        for (int i = 0; i < oc1list.size(); i++) {
            System.out.println(oc1list.get(i));
            ExportExcel.writeSpecifiedCell(0, 6 + i * 4, 0, oc1list.get(i));
        }
        for (int i = 0; i < oc1list.size(); i++) {
            int r = oc1Service.selectrightcountbymodeldaily(formatter.format(date), oc1list.get(i));
            System.out.println(oc1list.get(i) + "良品数是: " + r);
            int w = oc1Service.selectwrongcountbymodeldaily(formatter.format(date), oc1list.get(i));
            System.out.println(oc1list.get(i) + "不良品数是: " + w);
            ExportExcel.writeSpecifiedCellint(0, 7 + i * 4, 2 + date.getDate(), r);
            ExportExcel.writeSpecifiedCellint(0, 8 + i * 4, 2 + date.getDate(), w);
        }

    }

    //OC2报表
    /*@Test
    public void oc2exceltest(){
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(date));
        List<String> list=oc2Service.selecthismonthmodel(formatter.format(date));
        System.out.println(list);
        for (int i=0;i<list.size();i++){
            ExportExcel.writeSpecifiedCell(1,6+i*4,0,list.get(i));
        }

        for (int i=5;i<=6;i++){
            System.out.println(i);
            for (int j = 0; j < list.size(); j++) {
                int r = oc2Service.selectrightcountbymodeldaily("2020-08-"+i, list.get(j));
                int w = oc2Service.selectwrongcountbymodeldaily("2020-08-"+i, list.get(j));
                System.out.println("r="+r+",w="+w);
                ExportExcel.writeSpecifiedCellint(1, 7 + j * 4, 2 + i, r);
                ExportExcel.writeSpecifiedCellint(1, 8 + j * 4, 2 + i, w);
            }
        }

    }*/
    @Test    //
    public void oc2() {
        System.out.println("定时产生报表！ 时间是：" + new Date().toString());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -3);
        Date date = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //OC2报表
        List<String> oc2list = oc2Service.selecthismonthmodel(formatter.format(date));
        System.out.println(oc2list.size());
        for (int i = 0; i < oc2list.size(); i++) {
            System.out.println(oc2list.get(i));
            ExportExcel.writeSpecifiedCell(1, 6 + i * 4, 0, oc2list.get(i));
        }
        for (int i = 0; i < oc2list.size(); i++) {
            int r = oc2Service.selectrightcountbymodeldaily(formatter.format(date), oc2list.get(i));
            System.out.println(oc2list.get(i) + "良品数是: " + r);
            int w = oc2Service.selectwrongcountbymodeldaily(formatter.format(date), oc2list.get(i));
            System.out.println(oc2list.get(i) + "不良品数是: " + w);
            ExportExcel.writeSpecifiedCellint(1, 7 + i * 4, 2 + date.getDate(), r);
            ExportExcel.writeSpecifiedCellint(1, 8 + i * 4, 2 + date.getDate(), w);
        }


    }

    //BIT报表
    @Test
    public void bitexceltest() {
        System.out.println("定时产生报表！ 时间是：" + new Date().toString());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date date = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<String> list = bitService.selecthismonthmodel(formatter.format(date));
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            ExportExcel.writeSpecifiedCell(2, 6 + i * 4, 0, list.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            int r = bitService.selectrightcountbymodeldaily(formatter.format(date), list.get(i));
            System.out.println(list.get(i) + "良品数是: " + r);
            int w = bitService.selectwrongcountbymodeldaily(formatter.format(date), list.get(i));
            System.out.println(list.get(i) + "不良品数是: " + w);
            ExportExcel.writeSpecifiedCellint(2, 7 + i * 4, 2 + date.getDate(), r);
            ExportExcel.writeSpecifiedCellint(2, 8 + i * 4, 2 + date.getDate(), w);
        }


    }


    //OC4报表
    @Test
    public void exceltest() {
        System.out.println("定时产生报表！ 时间是：" + new Date().toString());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -3);
        Date date = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(date));
        //OC4报表
        List<String> oc4list = oc4Service.selecthismonthmodel(formatter.format(date));
        for (int i = 0; i < oc4list.size(); i++) {
            System.out.println(oc4list.get(i));
            ExportExcel.writeSpecifiedCell(3, 6 + i * 4, 0, oc4list.get(i));
        }
        for (int i = 0; i < oc4list.size(); i++) {
            int r = oc4Service.selectrightcountbymodeldaily(formatter.format(date), oc4list.get(i));
            System.out.println(oc4list.get(i) + "良品数是: " + r);
            int w = oc4Service.selectwrongcountbymodeldaily(formatter.format(date), oc4list.get(i));
            System.out.println(oc4list.get(i) + "不良品数是: " + w);
            ExportExcel.writeSpecifiedCellint(3, 7 + i * 4, 2 + date.getDate(), r);
            ExportExcel.writeSpecifiedCellint(3, 8 + i * 4, 2 + date.getDate(), w);
        }


    }

    //CDI报表
    @Test
    public void cdiexceltest() {
        System.out.println("定时产生报表！ 时间是：" + new Date().toString());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -2);
        Date date = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(date));
        List<String> cdilist = cdiService.selecthismonthmodel(formatter.format(date));
        System.out.println(cdilist.size());
        for (int i = 0; i < cdilist.size(); i++) {
            System.out.println(cdilist.get(i));
            ExportExcel.writeSpecifiedCell(4, 6 + i * 4, 0, cdilist.get(i));
        }
        for (int i = 0; i < cdilist.size(); i++) {
            int r = cdiService.selectrightcountbymodeldaily(formatter.format(date) + "%", cdilist.get(i));
            System.out.println(cdilist.get(i) + "良品数是: " + r);
            int w = cdiService.selectwrongcountbymodeldaily(formatter.format(date) + "%", cdilist.get(i));
            System.out.println(cdilist.get(i) + "不良品数是: " + w);
            ExportExcel.writeSpecifiedCellint(4, 7 + i * 4, 2 + date.getDate(), r);
            ExportExcel.writeSpecifiedCellint(4, 8 + i * 4, 2 + date.getDate(), w);
        }


    }

    @Autowired
    private CheckService checkService;

    @Test
    public void checkexceltest() {
        System.out.println("定时产生报表！ 时间是：" + new Date().toString());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -2);
        Date date = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(date));
        //目检报表
        List<String> list = checkService.selecthismonthmodel(formatter.format(date));
        for (int i = 0; i < list.size(); i++) {
            ExportExcel.writeSpecifiedCell(5, 6 + i * 4, 0, list.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            int r = checkService.selectrightcountbymodeldaily(formatter.format(date), list.get(i));
            int w = checkService.selectwrongcountbymodeldaily(formatter.format(date), list.get(i));
            ExportExcel.writeSpecifiedCellint(5, 7 + i * 4, 2 + date.getDate(), r);
            ExportExcel.writeSpecifiedCellint(5, 8 + i * 4, 2 + date.getDate(), w);
        }
    }


    @Autowired
    private BaozhuangService baozhuangService;
    @Test
    public void baozhuangExcel(){
    //包装报表
        System.out.println("定时产生报表！ 时间是：" + new Date().toString());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -3);
        Date date = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(date));

        List<String> packlist=baozhuangService.selecthismonthmodel(formatter.format(date));
        for (int i=0;i<packlist.size();i++){
        ExportExcel.writeSpecifiedCell(6,6+i*4,0,packlist.get(i));
    }
    SimpleDateFormat packformatter = new SimpleDateFormat("yyMMdd");
        for (int i = 0; i < packlist.size(); i++) {
        int r = baozhuangService.selectrightcountbymodeldaily("XSW"+packformatter.format(date)+"%", packlist.get(i));
        int w = baozhuangService.selectwrongcountbymodeldaily("XSW"+packformatter.format(date)+"%", packlist.get(i));

        System.out.println("r="+r+",w="+w);
        ExportExcel.writeSpecifiedCellint(6, 7 + i * 4, 2 + date.getDate(), r);
        ExportExcel.writeSpecifiedCellint(6, 8 + i * 4, 2 + date.getDate(), w);
    }
    }
}

