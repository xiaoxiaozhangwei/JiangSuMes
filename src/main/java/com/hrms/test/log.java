package com.hrms.test;

import com.hrms.bean.Bit;
import com.hrms.bean.Email;
import com.hrms.bean.User;
import com.hrms.mapper.UserMapper;
import com.hrms.service.BitService;
import com.hrms.service.EmailService;
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
import java.io.*;
import java.util.List;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class log {
    @Autowired
    UserMapper userMapper;
    @Autowired
    BitService bitService;
    @Autowired
    EmailService emailService;

    /**
     * 单条记录插入
     */
    @Test
    public void  check(){
        User user=userMapper.checkUser("a");
        System.out.println(user);
    }

@Test
    public void insertBit() throws IOException
    {
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

    public static void copyFile2Dir(File srcFile,File destDir) throws IOException{
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

        System.out.println("复制成功" +bos);
        // return true;
    }

    @Test
    public void sendautoEmail() throws AddressException {
        // String[] to = {"shenran@xitcorp.com","guyanbo@xitcorp.com"};
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
        List<Email> emails =emailService.selectEmail("试产组");
        System.out.println(emails);
        InternetAddress[] to = new InternetAddress[emails.size()];
        for (int i=0;i<emails.size();i++)
        {
            to[i]=new InternetAddress(emails.get(i).getAccount());

        }

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));
            // Set To: 头部头字段
            message.addRecipients(Message.RecipientType.TO, to);
            // Set Subject: 头部头字段
            message.setSubject("MES邮箱测试!");
            setMultipart(message);
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException | IOException mex) {
            mex.printStackTrace();
        }

        System.out.println("邮件定时发送！");
    }

    public void setMultipart(MimeMessage msg) throws MessagingException, IOException {
        // 一个Multipart对象包含一个或多个BodyPart对象，来组成邮件的正文部分（包括附件）。
        MimeMultipart multiPart = new MimeMultipart();

        // 添加正文
        MimeBodyPart partText = new MimeBodyPart();
        partText.setContent("邮件测试：每日定时发送报表！", "text/html;charset=utf-8");

        // 添加文件 也就 是附件
        MimeBodyPart partFile = new MimeBodyPart();
        File file = new File("D:\\EXCEL\\6月\\生产记录表 For MES.xlsx");

        partFile.setFileName(MimeUtility.encodeText(file.getName()));
        partFile.attachFile(file);

        multiPart.addBodyPart(partText);
        multiPart.addBodyPart(partFile);
        msg.setContent(multiPart);
    }



}
