package com.hrms.controller;


import com.hrms.bean.*;
import com.hrms.service.*;
import com.hrms.util.ExportExcel;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/Email")

 // @EnableScheduling
public class EmailController {
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
    @Autowired
    QCService qcService;
    @Autowired
    BitWarmCycleService bitWarmCycleService;

    @RequestMapping(value = "/EmailPage", method = RequestMethod.GET)
    public ModelAndView EmailPage() {
        ModelAndView mv = new ModelAndView("emailPage");
        List<Email> emails = emailService.selectEmail("");
        mv.addObject("emails", emails);
        return mv;
    }

    @RequestMapping(value = "/addEmail", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg addEmail(Email email) {
        int res = emailService.insertEmail(email);
        if (res == 1) {
            return JsonMsg.success();
        } else {
            return JsonMsg.fail();
        }
    }


    @RequestMapping(value = "/selectEmail/{groupEmail}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectUser(@PathVariable("groupEmail") String groupEmail) {

        List<Email> emails = null;
        if (groupEmail.equals("0")) {
            emails = emailService.selectEmail("");
        } else {
            emails = emailService.selectEmail(groupEmail);

        }
        if (emails != null) {
            return JsonMsg.success().addInfo("emails", emails);
        } else {
            return JsonMsg.fail();
        }

    }


    @RequestMapping(value = "/deleteEmail", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg deleteEmp(String account) {
        System.out.println(account);
        System.out.println("1");
        int res = emailService.deleteEmail(account);
        System.out.println("2");
        System.out.println(res);
        if (res != 1) {
            return JsonMsg.fail().addInfo("emp_del_error", "邮箱账号删除异常");
        } else {
            return JsonMsg.success();
        }
    }

   // @Scheduled(cron = "0 0 15 * * ?")
   // @RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
  //  @ResponseBody
    public void sendEmail() throws AddressException {
        String from = "mescz@xitcorp.com";
        // 指定发送邮件的主机为 localhost
        String host = "192.168.200.74";
        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties);
        List<Email> emails = emailService.selectEmail("试产组");
        System.out.println(emails);
        InternetAddress[] to = new InternetAddress[emails.size()];
        for (int i = 0; i < emails.size(); i++) {
            InternetAddress toaddr = new InternetAddress(emails.get(i).getAccount());
            to[i] = toaddr;
        }

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));
            // Set To: 头部头字段
            //   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.addRecipients(Message.RecipientType.TO, to);
            // Set Subject: 头部头字段
            message.setSubject("MES邮箱测试!");
            // 设置消息体
            message.setText("MES邮箱测试! 下午3点定时发送  " + "发送时间:  " + new Date().toString());// 发送消息
            //  message.setContent("<div  align=\"center\">你好啊</div>", "text/html;charset=utf-8");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

       // return JsonMsg.success();
    }

    //生产日报表XITC发送邮件
    @Async
    // @Scheduled(cron = "0 30 8 * * ?")
    public void sendEmailByXITC() throws AddressException {
        System.out.println("邮件定时发送xitc！");
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

    //生产日报表GOKE发送邮件
    @Async
   //  @Scheduled(cron = "0 0 9 * * ?")
    public void sendEmailByGoke() throws AddressException {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date d=cal.getTime();
        String from = "XITC-MES@xitcorp.com";
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

    //生产日报表邮件内容
    public void setMultipart(MimeMessage msg) throws MessagingException, IOException {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date d=cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
        // 一个Multipart对象包含一个或多个BodyPart对象，来组成邮件的正文部分（包括附件）。
        MimeMultipart multiPart = new MimeMultipart();
        // 添加正文
        MimeBodyPart partText = new MimeBodyPart();
        partText.setContent("<font size =\"5\" >Dear all:<br>&nbsp;&nbsp;&nbsp;&nbsp;请查收"+formatter.format(d)+"制造部生产系统报表<br>此为系统自动发送，请勿回复，如有问题请联系自动化小组<br><br>联系方式：沈&nbsp;&nbsp;然 shenran@xitcorp.com<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;张&nbsp;&nbsp;炜 zhangwei3@xitcorp.com</font>", "text/html;charset=utf-8");
        // 添加文件 也就 是附件
        MimeBodyPart partFile = new MimeBodyPart();
        File file;
        int m=d.getMonth();
        if (m==8){
            //根据路径获取文件
            file = new File("D:\\EXCEL\\2020.9\\生产记录表 For MES.xlsx");
        }else {
            //根据路径获取文件
            file = new File("D:\\EXCEL\\2020.10\\生产记录表 For MES.xlsx");
        }

        partFile.setFileName(MimeUtility.encodeText(file.getName()));
        partFile.attachFile(file);
        multiPart.addBodyPart(partText);
        multiPart.addBodyPart(partFile);
        msg.setContent(multiPart);
    }

    //生产日报表定时编辑
    @Async
   //  @Scheduled(cron = "0 0 7 * * ?")
    public void exceltest(){
        System.out.println("定时产生报表！ 时间是："+new Date().toString());
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date date=cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //OC1报表
        List<String> oc1list=oc1Service.selecthismonthmodel(formatter.format(date));
        for (int i=0;i<oc1list.size();i++){
            ExportExcel.writeSpecifiedCell(0,6+i*4,0,oc1list.get(i));
        }
        for (int i = 0; i < oc1list.size(); i++) {
            int r = oc1Service.selectrightcountbymodeldaily(formatter.format(date), oc1list.get(i));
            int w = oc1Service.selectwrongcountbymodeldaily(formatter.format(date), oc1list.get(i));
            ExportExcel.writeSpecifiedCellint(0, 7 + i * 4, 2 + date.getDate(), r);
            ExportExcel.writeSpecifiedCellint(0, 8 + i * 4, 2 + date.getDate(), w);
        }

        //OC2报表
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

    //BIT不良报表邮件发送
   //  @Scheduled(cron = "0 30 9 * * ?")
    public void sendBitErrorEmail() throws AddressException {
        System.out.println("bit Error邮件定时发送！");
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
       String[]  emilcount=
               {       "pengbaoxian@xitcorp.com",
                       "wangguangwen@xitcorp.com",
                       "zhangjiandong@xitcorp.com",
                       "liuyuqiang@xitcorp.com",
                       "shenran@xitcorp.com",
                       "guyanbo@xitcorp.com",
                       "zhujun@xitcorp.com",
                       "caobo@xitcorp.com",
                       "shirangde@xitcorp.com",
                       "duhongxia@xitcorp.com",
                       "limei@xitcorp.com",
                       "huangjue@xitcorp.com",
                       "zhangwei3@xitcorp.com",
                       "cuiqingjun@xitcorp.com"};
        InternetAddress[] to = new InternetAddress[emilcount.length];
        for (int i=0;i<emilcount.length;i++)
        {
            to[i]=new InternetAddress(emilcount[i]);
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
            message.setSubject("制造部每日BIT不良生产系统报表 —— "+formatter.format(d));
            setMultipartBITError(message);
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException | IOException mex) {
            mex.printStackTrace();
        }


      //  System.out.println("邮件定时发送！");
    }

    //BIT不良报表邮件内容
    public void setMultipartBITError(MimeMessage msg) throws MessagingException, IOException {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date d=cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
        // 一个Multipart对象包含一个或多个BodyPart对象，来组成邮件的正文部分（包括附件）。
        MimeMultipart multiPart = new MimeMultipart();
        // 添加正文
        MimeBodyPart partText = new MimeBodyPart();
        partText.setContent("<font size =\"5\" >Dear all:<br>&nbsp;&nbsp;&nbsp;&nbsp;请查收"+formatter.format(d)+"制造部BIT不良系统报表<br>此为系统自动发送，请勿回复，如有问题请联系自动化小组<br><br>联系方式：沈&nbsp;&nbsp;然 shenran@xitcorp.com<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;张&nbsp;&nbsp;炜 zhangwei3@xitcorp.com</font>", "text/html;charset=utf-8");
        // 添加文件 也就 是附件
        MimeBodyPart partFile = new MimeBodyPart();
        File file;
        int m=d.getMonth();
        if (m==8){
            //根据路径获取文件
            file = new File("D:\\EXCEL\\2020.9\\BIT Error Code 统计表.xlsx");
        }else {
            //根据路径获取文件
            file = new File("D:\\EXCEL\\2020.10\\BIT Error Code 统计表.xlsx");
        }
        partFile.setFileName(MimeUtility.encodeText(file.getName()));
        partFile.attachFile(file);
        multiPart.addBodyPart(partText);
        multiPart.addBodyPart(partFile);
        msg.setContent(multiPart);
    }

    //BIT不良报表定时产生
    @Async
    // @Scheduled(cron = "0 5 7 * * ?")
    public void timelyBitError(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date date=cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String daytime= formatter.format(date);
        String[] errorcode ={"0x683A", "0x443A", "0x5D3A" , "0x3A", "0x1A", "0x0E", "0x0D", "0x08", "0x9D3A"};
        List<String> modelCount=bitService.selectModelByday();
        System.out.println("开始时间："+new Date().toString());
        String bitnumber ;
        for (int i=1;i<6;i++)
        {
            for (int j = 0; j < modelCount.size(); j++) {
                if (i<3)
                {bitnumber= String.valueOf(i);}
                else
                {  bitnumber = String.valueOf(i+1);}
                ExportExcel.writeErrorToExcelString(i, 19 + j * 16, 0, modelCount.get(j));
                int passcount=bitService.selectpasscountbymodelandbitnumber(modelCount.get(j),bitnumber);
                System.out.println("第"+bitnumber+"温箱: "+"   " +modelCount.get(j)+"良品数量：  "+passcount);
                ExportExcel.writeErrorToExcel(i, 20 + j * 16, date.getDate() + 3, passcount);
                for (int z = 0; z < 9; z++) {
                    int errorCount = bitService.selectNGByBitNumberday(bitnumber, modelCount.get(j), errorcode[z]);
                    System.out.println("第"+bitnumber+"温箱: "+"   " +modelCount.get(j)+"的"+errorcode[z]+"   不良数量："+errorCount);
                    ExportExcel.writeErrorToExcel(i, 16 * j + 23 + z, date.getDate() + 3, errorCount);
                }
            }

        }

        System.out.println("结束时间："+new Date().toString());


    }

   // //内外箱application归零
   // @Scheduled(cron = "0 0 0 * * ?")
   // public void resetcount(HttpServletRequest request){
   //     ServletContext application=request.getServletContext();
   //     application.setAttribute("countn", -1);
   //     application.setAttribute("countw", -1);
//
   // }

    /*
    //BIT读取文件
    //
    public void insertBit() throws IOException {
        String filepath = "D:\\Test_POROS";//D盘下的file文件夹的目录
        File file = new File(filepath);//File类型可以是文件也可
        File fileDesc = new File("D:\\测试数据备份\\BIT备份");
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        String encoding="GBK";
        String[] data=new String[20];
        for (int i = 0; i < fileList.length; i++)
        {
            if (fileList[i].isFile())
            {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(fileList[i]),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int j=0;
                while((lineTxt = bufferedReader.readLine()) != null)
                {   //逐行读取
                    if (j!=11)
                    {
                        data[j]=lineTxt.substring(lineTxt.indexOf("=")+1).trim();
                        j++;
                    }
                    else
                    {
                        String [] arr = lineTxt.split("\\s+");
                        for(String ss : arr)
                        {   data[j]=ss.substring(ss.indexOf("=")+1);
                            j++;
                        }
                    }

                }
                copyFile2Dir(fileList[i],fileDesc);
                Bit bit=null;
                System.out.println(j);
                if (j==15)
                {
                    bit=new Bit(data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10],data[11],data[12],data[13],data[14]);
                    if ( bitService.insertBit(bit)==1)

                    {
                        read.close();
                        bufferedReader.close();
                        System.gc();
                        System.out.println( "BIT第"+i+"个在删除：  "+ fileList[i].delete());
                    }
                }
                else
                {
                    bit=new Bit(data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10],data[11],data[12],data[13],data[14],data[15]);
                    if ( bitService.insertBit(bit)==1)

                    {
                        read.close();
                        bufferedReader.close();
                        System.gc();
                        System.out.println( "BIT第"+i+"个在删除：  "+ fileList[i].delete());
                    }
                }
            }

        }

    }
*/

    //复制文件
    public static void copyFile(File srcFile,File destDir) throws IOException{
        //创建输入流对象 将源文件File对象传入其形参
        FileInputStream fis= new FileInputStream(srcFile);
        //获取目标路径的File对象，关键在于目标路径问题，注意复制访问
        // 只能是文件不能是文件夹 否则拒绝访问，getpath()得到具体带盘符的路径
        // getName()得到的是文件的名称，两者字符串拼接就是具体的目标路径（目标文件//路径）
        File file= new File(destDir.getPath()+File.separator+srcFile.getName());
        //****出错关键点******         //判断目标文件夹File对象（路径）是否存在，不存在则创建
        //创建输出流对象
        FileOutputStream bos= new FileOutputStream(file);       //常规的读写复制
        int len;
        byte [] b= new byte[1024];
        while((len=fis.read(b))!=-1)
        {
            bos.write(b, 0, len);
            bos.flush();
        }
        //关闭资源
        bos.close();
        fis.close();

       System.out.println("复制成功"+bos.toString());
        //return true;
    }

    //读取CDI文件
   //  @Scheduled(cron = "*/20 * * * * ?")
    public void insertCDI() throws IOException {
        String filepath = "D:\\Test_CDI";//D盘下的file文件夹的目录
        File file = new File(filepath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        //   List<File> wjList = new ArrayList<File>();//新建一个文件集合
        File fileDesc = new File("D:\\测试数据备份\\CDI备份");
        String encoding = "UTF-8";
        CDI cdi = null;
        String WorkOrderNumber = null;
        String DeviceNumber=null;
        String OpID=null;
        String SN=null;
        String MN=null;
        String TestDate=null;
        String TestName=null;
        String TestResult=null;
        String ErrorCode=null;
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {// 使用FileInputStream读取文本内容，然后通过InputStreamReader和指定的编码将字符转换为字节
                ArrayList<String> str=new ArrayList<>();
                InputStreamReader read = new InputStreamReader(new FileInputStream(fileList[i]), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {//逐行读取// System.out.println(lineTxt.substring(lineTxt.indexOf("=")+1));// 读取“=”后面的字符串
                    str.add(lineTxt);
                }
                for (int j=1;j<str.size();j++){
                    String str1=str.get(j).substring(0,str.get(j).indexOf("="));
                    String str2=str.get(j).substring(str.get(j).indexOf("=") + 1);

                    switch (str1){
                        case "WorkOrderNumber":{
                            WorkOrderNumber=str2;
                            break;
                        }
                        case "DeviceNumber":{
                            DeviceNumber=str2;
                            break;
                        }
                        case "OpID":{
                            System.out.println("姓名是："+str2);
                            OpID=str2;
                            break;
                        }
                        case "SN":{
                            SN=str2;
                            break;
                        }
                        case "MN":{
                            MN=str2;
                            break;
                        }
                        case "TestName":{
                            TestName=str2;
                            break;
                        }
                        case "TestDate":{
                            TestDate=str2;
                            break;
                        }
                        case "TestResult":{
                            TestResult=str2;
                            break;
                        }
                        case "ErrorCode":{
                            ErrorCode=str2;
                            break;
                        }
                    }
                }

                copyFile(fileList[i], fileDesc);
                cdi = new CDI(WorkOrderNumber,DeviceNumber,OpID,SN,MN,TestName,TestDate,TestResult,ErrorCode);
                if (cdiService.insert(cdi) == 1) {
                    read.close();
                    bufferedReader.close();
                    System.gc();
                   fileList[i].delete();
                    str.clear();
                    ErrorCode=null;
                }

            }

        }

    }

    //读取BIT文件
   // @Scheduled(cron = "*/20 * * * * ?")     -------旧备份  处理Bit文件
   /*public void insertBit() throws IOException {
        String filepath = "D:\\Test_POROS";//D盘下的file文件夹的目录
        File file = new File(filepath);//File类型可以是文件也可
        File fileDesc = new File("D:\\测试数据备份\\BIT备份");
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        String encoding="GBK";
        String[] data=new String[20];
        for (int i = 0; i < fileList.length; i++)
        {
            if (fileList[i].isFile())
            {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(fileList[i]),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int j=0;
                while((lineTxt = bufferedReader.readLine()) != null)
                {   //逐行读取
                    if (j!=11)
                    {
                        data[j]=lineTxt.substring(lineTxt.indexOf("=")+1).trim();
                        j++;
                    }
                    else
                    {
                        String [] arr = lineTxt.split("\\s+");
                        for(String ss : arr)
                        {   data[j]=ss.substring(ss.indexOf("=")+1);
                            j++;
                        }
                    }

                }
                copyFile(fileList[i],fileDesc);
                Bit bit=null;
               // System.out.println(j);
                if (j==15)
                {
                    bit=new Bit(data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10],data[11],data[12],data[13],data[14]);
                    if ( bitService.insertBit(bit)==1)

                    {
                        read.close();
                        bufferedReader.close();
                        System.gc();
                       fileList[i].delete();
                    }
                }
                else
                {
                    bit=new Bit(data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10],data[11],data[12],data[13],data[14],data[15]);
                    if ( bitService.insertBit(bit)==1)

                    {
                        read.close();
                        bufferedReader.close();
                        System.gc();
                      fileList[i].delete();
                    }
                }
            }

        }

    }
*/


   //  @Scheduled(cron = "*/20 * * * * ?")
    public void insertBit() throws IOException {
        String filepath = "D:\\Test_POROS";//D盘下的file文件夹的目录
        File file = new File(filepath);//File类型可以是文件也可
        File fileDesc = new File("D:\\测试数据备份\\BIT备份");
        File fileDesc2 = new File("D:\\测试数据备份\\BIT温循备份");
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        String encoding="GBK";
        String[] data=new String[20];
        Bit bit=null;
        for (int i = 0; i < fileList.length; i++)
        {
            if (fileList[i].isFile())
            {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(fileList[i]),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int j=0;
                while((lineTxt = bufferedReader.readLine()) != null)
                {   //逐行读取
                    if (j!=11)
                    {
                        data[j]=lineTxt.substring(lineTxt.indexOf("=")+1).trim();
                        j++;
                    }
                    else
                    {
                        String [] arr = lineTxt.split("\\s+");
                        for(String ss : arr)
                        {   data[j]=ss.substring(ss.indexOf("=")+1);
                            j++;
                        }
                    }

                }


                if (!data[1].startsWith("wx"))
                {   copyFile(fileList[i],fileDesc);
                    if (j==15)
                    {
                        bit=new Bit(data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10],data[11],data[12],data[13],data[14]);
                        if (bitService.insertBit(bit)==1)
                        {
                            close(read,bufferedReader,fileList[i]);
                        }
                    }
                    else
                    {
                        bit=new Bit(data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10],data[11],data[12],data[13],data[14],data[15]);
                        if (bitService.insertBit(bit)==1)

                        {
                            close(read,bufferedReader,fileList[i]);
                        }
                    }
                }

                else
                {
                    copyFile(fileList[i],fileDesc2);
                    if (j==15)
                    {
                        bit=new Bit(data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10],data[11],data[12],data[13],data[14]);
                        if( bitWarmCycleService.insertBitWarmCycle(bit)==1)
                        {
                            close(read,bufferedReader,fileList[i]);
                        }
                    }
                    else
                    {
                        bit=new Bit(data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10],data[11],data[12],data[13],data[14],data[15]);
                        if (bitWarmCycleService.insertBitWarmCycle(bit)==1)

                        {
                            close(read,bufferedReader,fileList[i]);
                        }
                    }
                }
            }

        }





    }
    public  void close(InputStreamReader reader,BufferedReader bufferedReader,File file) throws IOException {
        reader.close();
        bufferedReader.close();
        System.gc();
        file.delete();
    }







    //读取OC1文件
   //  @Scheduled(cron = "*/20 * * * * ?")
    public void insertOC1() throws IOException {
        String filepath = "D:\\Test_OC1";//D盘下的file文件夹的目录
        File fileDesc = new File("D:\\测试数据备份\\OC1备份");
        File file = new File(filepath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        String encoding = "GBK";
        String data[] = new String[25];
        PCBAData pcbaData = null;
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
                    pcbaData = new PCBAData(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8],data[14]);
                    if (oc1Service.insertOC1(pcbaData) == 1) {
                        read.close();
                        bufferedReader.close();
                        System.gc();
                     fileList[i].delete();
                    }
                } else {
                    pcbaData = new PCBAData(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9],data[14]);
                    if (oc1Service.insertOC1(pcbaData) == 1) {
                        read.close();
                        bufferedReader.close();
                        System.gc();
                     fileList[i].delete();
                    }
                }
            }
        }
    }

    //读取OC2文件
   // @Scheduled(cron = "*/20 * * * * ?")
    public void insertOC2() throws IOException {
        String filepath = "D:\\Test_OC2";//D盘下的file文件夹的目录
        File fileDesc = new File("D:\\测试数据备份\\OC2备份");
        File file = new File(filepath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        String encoding = "GBK";
        String data[] = new String[35];
        PCBAData pcbaData = null;
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
                    pcbaData = new PCBAData(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8],data[14]);
                    if (oc2Service.insertOC2(pcbaData) == 1) {
                        read.close();
                        bufferedReader.close();
                        System.gc();
                       fileList[i].delete();
                    }
                } else {
                    pcbaData = new PCBAData(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9],data[14]);
                    if (oc2Service.insertOC2(pcbaData) == 1) {
                        read.close();
                        bufferedReader.close();
                        System.gc();
                       fileList[i].delete();
                    }
                }
            }
        }
    }

    //读取OC4文件
   // @Scheduled(cron = "*/20 * * * * ?")
    public void insertOC4() throws IOException {
        String filepath = "D:\\Test_OC4";//D盘下的file文件夹的目录
        File fileDesc = new File("D:\\测试数据备份\\OC4备份");
        File file = new File(filepath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        String encoding = "GBK";
        String data[] = new String[25];
        PCBAData pcbaData = null;
        String WorkOrderNumber = null;
        String DeviceNumber=null;
        String OpID=null;
        String SN=null;
        String MN=null;
        String TestDate=null;
        String TestName=null;
        String TestResult=null;
        String ErrorCode=null;
        String FW=null;
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {// 使用FileInputStream读取文本内容，然后通过InputStreamReader和指定的编码将字符转换为字节
                ArrayList<String> str=new ArrayList<>();
                InputStreamReader read = new InputStreamReader(new FileInputStream(fileList[i]), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {//逐行读取// System.out.println(lineTxt.substring(lineTxt.indexOf("=")+1));// 读取“=”后面的字符串
                    str.add(lineTxt);
                }
                for (int j=1;j<str.size();j++){
                    String str1=str.get(j).substring(0,str.get(j).indexOf("="));
                    String str2=str.get(j).substring(str.get(j).indexOf("=") + 1);
                    switch (str1){
                        case "WorkOrderNumber":{
                            WorkOrderNumber=str2;
                            break;
                        }
                        case "DeviceNumber":{
                            DeviceNumber=str2;
                            break;
                        }
                        case "OpID":{
                            OpID=str2;
                            break;
                        }
                        case "SN":{
                            SN=str2.trim();
                            break;
                        }
                        case "MN":{
                            MN=str2;
                            break;
                        }
                        case "TestName":{
                            TestName=str2;
                            break;
                        }
                        case "TestDate":{
                            TestDate=str2;
                            break;
                        }
                        case "TestResult":{
                            TestResult=str2;
                            break;
                        }
                        case "ErrorCode":{
                            ErrorCode=str2;
                            break;
                        }
                        case "FW Version":
                            FW=str2;
                            break;
                    }
                }

                copyFile(fileList[i], fileDesc);
                pcbaData = new PCBAData(WorkOrderNumber,DeviceNumber,OpID,SN,MN,TestName,TestDate,TestResult,ErrorCode,FW);
                if (oc4Service.insertOC4(pcbaData) == 1) {
                    close(read,bufferedReader,fileList[i]);
                    str.clear();
                    ErrorCode=null;
                }

            }

        }




    }

    //读取QC文件
   // @Scheduled(cron = "*/20 * * * * ?")
    public void insertQC() throws IOException {
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
                qc = new QC(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8],data[9]);
                if (qcService.insertQC(qc)==1){
                    read.close();
                    bufferedReader.close();
                    System.gc();
                    fileList[i].delete();
                }

            }

        }

    }
}

