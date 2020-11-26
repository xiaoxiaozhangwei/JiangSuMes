package com.hrms.test;

import com.hrms.bean.PCBAData;
import com.hrms.service.OC4Service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;

public class LogTest {

    @Autowired
    OC4Service oc4Service;

    @Test
    public void insertOC4() throws IOException {
        String filepath = "D:\\Test_OC4";//D盘下的file文件夹的目录
        File fileDesc = new File("D:\\测试数据备份\\OC4备份");
        File file = new File(filepath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        String encoding = "GBK";
        String data[] = new String[25];
        PCBAData pcbaData = null;
        String WorkOrderNumber = null;
        String DeviceNumber = null;
        String OpID = null;
        String SN = null;
        String MN = null;
        String TestDate = null;
        String TestName = null;
        String TestResult = null;
        String ErrorCode = null;
        String FW = null;
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {// 使用FileInputStream读取文本内容，然后通过InputStreamReader和指定的编码将字符转换为字节
                ArrayList<String> str = new ArrayList<>();
                InputStreamReader read = new InputStreamReader(new FileInputStream(fileList[i]), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {//逐行读取// System.out.println(lineTxt.substring(lineTxt.indexOf("=")+1));// 读取“=”后面的字符串
                    lineTxt.trim();
                  if (!"".equals(lineTxt))
                    {
                        str.add(lineTxt);
                        System.out.println(lineTxt);
                    }


                }

                System.out.println(str.size());
                for (int j = 1; j < str.size(); j++)
                {
                     if (j==23)
                      {   //str.get(23).replaceAll("\\s*", "");
                          System.out.println(str.get(23).trim());
                           System.out.println("空行");
                          System.out.println(str.get(23));
                            }


                 /*   String str1="";
                    String str2="";
                    try {
                         str1 = str.get(j).substring(0, str.get(j).indexOf("="));
                         str2 = str.get(j).substring(str.get(j).indexOf("=") + 1);
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }

                    switch (str1) {
                        case "WorkOrderNumber": {
                            WorkOrderNumber = str2;
                            break;
                        }
                        case "DeviceNumber": {
                            DeviceNumber = str2;
                            break;
                        }
                        case "OpID": {
                            OpID = str2;
                            break;
                        }
                        case "SN": {
                            SN = str2.trim();
                            break;
                        }
                        case "MN": {
                            MN = str2;
                            break;
                        }
                        case "TestName": {
                            TestName = str2;
                            break;
                        }
                        case "TestDate": {
                            TestDate = str2;
                            break;
                        }
                        case "TestResult": {
                            TestResult = str2;
                            break;
                        }
                        case "ErrorCode": {
                            ErrorCode = str2;
                            break;
                        }
                        case "FW Version":
                            FW = str2;
                            break;
                    }

                */


                }

                System.out.println(str.size());


            }

        }


    }

}


