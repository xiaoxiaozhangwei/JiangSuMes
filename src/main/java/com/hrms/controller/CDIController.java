package com.hrms.controller;


import com.hrms.bean.CDI;
import com.hrms.service.CDIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/CDI")
public class CDIController {
    @Autowired
    CDIService cdiService;

    @RequestMapping(value = "/insertCDI",method = RequestMethod.GET)
    public void insertCDI() throws IOException {
        String filepath = "D:\\Test_CDI";//D盘下的file文件夹的目录
        File file = new File(filepath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        //   List<File> wjList = new ArrayList<File>();//新建一个文件集合
        File fileDesc = new File("D:\\测试数据备份\\CDI备份");
        String encoding = "GBK";
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
                    System.out.println(str1);
                    String str2=str.get(j).substring(str.get(j).indexOf("=") + 1);
                    System.out.println(str2);
                    switch (str1){
                        case "WorkOrderNumber":{
                            System.out.println("3");
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
                System.out.println(cdi);
                if (cdiService.insert(cdi) == 1) {
                    read.close();
                    bufferedReader.close();
                    System.gc();
                    System.out.println("第" + i + "个在删除：  " + fileList[i].delete());
                    str.clear();
                    ErrorCode=null;
                }

            }

        }

    }

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
}
