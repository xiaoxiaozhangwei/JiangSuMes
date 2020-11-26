package com.hrms.test;


import com.hrms.bean.Email;
import com.hrms.service.*;
import com.hrms.util.ExportExcel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class EmailTest {
    @Autowired
    EmailService emailService;
    @Autowired
    CheckService checkService;
    @Autowired
    OC4Service oc4Service;
    @Autowired
    BaozhuangService baozhuangService;
    @Autowired
    OC1Service oc1Service;
    @Autowired
    OC2Service oc2Service;
    @Autowired
    BitService bitService;
    @Autowired
    CDIService cdiService;

    @Test
    public void sendEmail()
    {
        List<Email> emails =emailService.selectEmail("GK邮箱");
        System.out.println(emails.size());

    }


    @Test
    public void sendautoEmail() throws AddressException {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date d=cal.getTime();
        // 发件人电子邮箱
        String from = "XITC-MES@xitcorp.com";
        // 指定发送邮件的主机为 localhost
        String host = "192.168.0.25";
        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties);
        List<Email> emails =emailService.selectEmail("GK邮箱");
        System.out.println(emails);
        InternetAddress[] to = new InternetAddress[emails.size()];
        for (int i=0;i<emails.size();i++)
        {
            to[i]=new InternetAddress(emails.get(i).getAccount());
            System.out.println(to[i]);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));
            // Set To: 头部头字段
            message.addRecipients(Message.RecipientType.TO, to);
            // Set Subject: 头部头字段
            message.setSubject("制造部每日生产系统报表 —— "+formatter.format(d));
            setMultipart(message);
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException | IOException mex) {
            mex.printStackTrace();
        }

        System.out.println("邮件定时发送！");
    }


    @Test
    public void exceltest(){
        System.out.println("定时产生报表！ 时间是："+new Date().toString());
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-4);
        Date date=cal.getTime();
        System.out.println(date.getMonth());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(date));
        System.out.println(date.getDate());
        //OC1报表
        System.out.println("OC1报表");
        List<String> oc1list=oc1Service.selecthismonthmodel(formatter.format(date));
        for (int i=0;i<oc1list.size();i++){
            ExportExcel.writeSpecifiedCell(0,6+i*4,0,oc1list.get(i));
        }
        for (int i = 0; i < oc1list.size(); i++) {
            //       System.out.println(oc1list.get(i)+";"+formatter.format(date)+";"+date.getDate());
            int r = oc1Service.selectrightcountbymodeldaily(formatter.format(date), oc1list.get(i));
            int w = oc1Service.selectwrongcountbymodeldaily(formatter.format(date), oc1list.get(i));
            System.out.println("r="+r+";w="+w);
            ExportExcel.writeSpecifiedCellint(0, 7 + i * 4, 2 + date.getDate(), r);
            ExportExcel.writeSpecifiedCellint(0, 8 + i * 4, 2 + date.getDate(), w);
        }

        //OC2报表
        System.out.println("OC2报表");
        List<String> oc2list=oc2Service.selecthismonthmodel(formatter.format(date));
        for (int i=0;i<oc2list.size();i++){
            ExportExcel.writeSpecifiedCell(1,6+i*4,0,oc2list.get(i));
        }
        for (int i = 0; i < oc2list.size(); i++) {
            int r = oc2Service.selectrightcountbymodeldaily(formatter.format(date), oc2list.get(i));
            int w = oc2Service.selectwrongcountbymodeldaily(formatter.format(date), oc2list.get(i));
            ExportExcel.writeSpecifiedCellint(1, 7 + i * 4, 2 + date.getDate(), r);
            ExportExcel.writeSpecifiedCellint(1, 8 + i * 4, 2 + date.getDate(), w);
        }

        //BIT报表
        System.out.println("BIT报表");

        List<String> bitlist=bitService.selecthismonthmodel(formatter.format(date));
        for (int i=0;i<bitlist.size();i++){
            ExportExcel.writeSpecifiedCell(2,6+i*4,0,bitlist.get(i));
        }

        for (int i = 0; i < bitlist.size(); i++) {
            int r = bitService.selectrightcountbymodeldaily(formatter.format(date), bitlist.get(i));
            int w = bitService.selectwrongcountbymodeldaily(formatter.format(date), bitlist.get(i));
            ExportExcel.writeSpecifiedCellint(2, 7 + i * 4, 2 + date.getDate(), r);

            ExportExcel.writeSpecifiedCellint(2, 8 + i * 4, 2 + date.getDate(), w);
        }

        //OC4报表
        System.out.println("OC4报表");
        List<String> oc4list=oc4Service.selecthismonthmodel(formatter.format(date));
        for (int i=0;i<oc4list.size();i++){
            ExportExcel.writeSpecifiedCell(3,6+i*4,0,oc4list.get(i));
        }
        for (int i = 0; i < oc4list.size(); i++) {
            int r = oc4Service.selectrightcountbymodeldaily(formatter.format(date), oc4list.get(i));
            int w = oc4Service.selectwrongcountbymodeldaily(formatter.format(date), oc4list.get(i));
            ExportExcel.writeSpecifiedCellint(3, 7 + i * 4, 2 + date.getDate(), r);
            ExportExcel.writeSpecifiedCellint(3, 8 + i * 4, 2 + date.getDate(), w);
        }

        //CDI报表
        System.out.println("CDI报表");
        List<String> cdilist=cdiService.selecthismonthmodel(formatter.format(date));
        for (int i=0;i<cdilist.size();i++){
            ExportExcel.writeSpecifiedCell(4,6+i*4,0,cdilist.get(i));
        }
        for (int i = 0; i < cdilist.size(); i++) {
            int r = cdiService.selectrightcountbymodeldaily(formatter.format(date)+"%", cdilist.get(i));
            int w = cdiService.selectwrongcountbymodeldaily(formatter.format(date)+"%", cdilist.get(i));
            ExportExcel.writeSpecifiedCellint(4, 7 + i * 4, 2 + date.getDate(), r);
            ExportExcel.writeSpecifiedCellint(4, 8 + i * 4, 2 + date.getDate(), w);
        }

        //目检报表
        System.out.println("目检报表");
        List<String> list=checkService.selecthismonthmodel(formatter.format(date));
        for (int i=0;i<list.size();i++){
            ExportExcel.writeSpecifiedCell(5,6+i*4,0,list.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            int r = checkService.selectrightcountbymodeldaily(formatter.format(date), list.get(i));
            int w = checkService.selectwrongcountbymodeldaily(formatter.format(date), list.get(i));
            ExportExcel.writeSpecifiedCellint(5, 7 + i * 4, 2 + date.getDate(), r);
            ExportExcel.writeSpecifiedCellint(5, 8 + i * 4, 2 + date.getDate(), w);
        }
        //包装报表
        System.out.println("包装报表");
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


    @Test
    public void sendEmailByXITC() throws AddressException {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date d=cal.getTime();
        // 发件人电子邮箱
        String from = "XITC-MES@xitcorp.com";
        // 指定发送邮件的主机为 localhost
        String host = "192.168.200.74";
        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties);
        List<Email> emails =emailService.selectEmail("芯盛邮箱");
        System.out.println(emails);
        InternetAddress[] to = new InternetAddress[emails.size()];
        for (int i=0;i<emails.size();i++)
        {
            to[i]=new InternetAddress(emails.get(i).getAccount());
        }
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));
            // Set To: 头部头字段
            message.addRecipients(Message.RecipientType.TO, to);
            // Set Subject: 头部头字段
            message.setSubject("制造部每日生产系统报表 —— "+formatter.format(d));
            setMultipart(message);
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException | IOException mex) {
            mex.printStackTrace();
        }

        System.out.println("邮件定时发送！");
    }


    public void setMultipart(MimeMessage msg) throws MessagingException, IOException {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date d=cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
        // 一个Multipart对象包含一个或多个BodyPart对象，来组成邮件的正文部分（包括附件）。
        MimeMultipart multiPart = new MimeMultipart();
        // 添加正文
        MimeBodyPart partText = new MimeBodyPart();
        partText.setContent("<font size =\"5\" >Dear all:<br>&nbsp;&nbsp;&nbsp;&nbsp;请查收"+formatter.format(d)+"制造部生产系统报表<br>此为系统自动发送，请勿回复，如有问题请联系自动化小组<br><br>联系方式：沈&nbsp;&nbsp;然 shenran@xitcorp.com<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;顾艳波 guyanbo@xitcorp.com</font>", "text/html;charset=utf-8");
        // 添加文件 也就 是附件
        MimeBodyPart partFile = new MimeBodyPart();
        File file = new File("D:\\EXCEL\\7月\\生产记录表 For MES.xlsx");
        partFile.setFileName(MimeUtility.encodeText(file.getName()));
        partFile.attachFile(file);
        multiPart.addBodyPart(partText);
        multiPart.addBodyPart(partFile);
        msg.setContent(multiPart);
    }

    @Test
    public void timelyBitError(){
        /*
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date date=cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String daytime= formatter.format(date);
        String[] errorcode ={"0x683A", "0x443A", "0x5D3A" , "0x3A", "0x1A", "0x0E", "0x0D", "0x08", "0x9D3A"};
        List<String> modelCount=bitService.selectModelByday();
        System.out.println("开始时间："+new Date().toString());
        System.out.println("model号数量:"+modelCount.size());
        String bitnumber ;
        for (int i=1;i<6;i++)
        {
            for (int j = 0; j < modelCount.size(); j++) {
                if (i<3)
                {bitnumber= String.valueOf(i);}
                else
                {  bitnumber = String.valueOf(i+1);}
                ExportExcel.writeErrorToExcelString(i, 19 + j * 16, 0, modelCount.get(j));
                int passcount=bitService.selectpasscountbymodelandbitnumber(daytime,modelCount.get(j),bitnumber);
                System.out.println("第"+bitnumber+"温箱: "+"   " +modelCount.get(j)+"良品数量：  "+passcount);
                ExportExcel.writeErrorToExcel(i, 20 + j * 16, date.getDate() + 3, passcount);
                for (int z = 0; z < 9; z++) {
                    int errorCount = bitService.selectNGByBitNumberday(daytime, bitnumber, modelCount.get(j), errorcode[z]);
                    System.out.println("第"+bitnumber+"温箱: "+"   " +modelCount.get(j)+"的"+errorcode[z]+"   不良数量："+errorCount);
                    ExportExcel.writeErrorToExcel(i, 16 * j + 23 + z, date.getDate() + 3, errorCount);
                }
            }

        }

        System.out.println("结束时间："+new Date().toString());

*/
    }

    @Test
    public void testTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date d = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
        System.out.println(formatter.format(d));
        int m = d.getMonth()+1;
        System.out.println(m);
        File file;
        file = new File("D:\\EXCEL\\2020."+m+"\\生产记录表 For MES.xlsx");
        System.out.println(file);



        /*if (m==9){
            //根据路径获取文件
            file = new File("D:\\EXCEL\\2020."+(m+1)+"\\生产记录表 For MES.xlsx");
            System.out.println(file);
        }else {
            //根据路径获取文件
            file = new File("D:\\EXCEL\\2020."+(m+1)+"\\生产记录表 For MES.xlsx");
            System.out.println(file);
        }*/
    }
}
