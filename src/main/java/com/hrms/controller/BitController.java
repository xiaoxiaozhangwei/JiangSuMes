package com.hrms.controller;

import com.hrms.bean.Bit;
import com.hrms.service.BitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;

@Controller
@RequestMapping(value = "/Bit")
public class BitController {
    @Autowired
    BitService bitService;


    @RequestMapping(value = "/insertBit",method = RequestMethod.GET)
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



}
