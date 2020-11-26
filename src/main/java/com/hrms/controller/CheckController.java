package com.hrms.controller;

import com.hrms.bean.CDI;
import com.hrms.bean.Check;
import com.hrms.service.*;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/check")
public class CheckController {
    @Autowired
    CheckService checkService;
    @Autowired
    ZuzhuangService zuzhuangService;
    @Autowired
    BaozhuangService baozhuangService;
    @Autowired
    CDIService cdiService;
    @Autowired
    OC4Service oc4Service;
    @Autowired
    OC1Service oc1Service;
    @Autowired
    OC2Service oc2Service;

    @Autowired
    QCService qcService;
    @Autowired
    BitService bitService;

    @RequestMapping(value = "/insert1", method = RequestMethod.POST)
    public void insercheck1(Check check) {
        checkService.insetcheck1(check);
    }

    @RequestMapping(value = "/insert2", method = RequestMethod.POST)
    public void insercheck2(Check check) {
        checkService.insetcheck2(check);
    }

    @RequestMapping(value = "/selectProByName/{productionSN}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg production2(@PathVariable("productionSN") String productionSN, String moid) {
        Integer res = checkService.selectProByName(productionSN, moid);
        if (res == 0) {
            return JsonMsg.success();
        } else {
            return JsonMsg.fail();
        }
    }

    @RequestMapping(value = "/merman", method = RequestMethod.POST)
    public void merman(String moId) {
        checkService.updatemo(moId);
    }

    /**
     * 判断SN号是否经过检查站
     *
     * @param productionSN
     * @return
     */
    @RequestMapping(value = "/production/{productionSN}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg production(@PathVariable("productionSN") String productionSN) {
        Integer res = checkService.production(productionSN);
        if (res != 0) {
            return JsonMsg.success();
        } else {
            return JsonMsg.fail();
        }

    }

    @RequestMapping(value = "/selectcount")
    @ResponseBody
    // 索引没问题，已加
    public ModelAndView selectcount() throws IOException {
        ModelAndView mv = new ModelAndView("kanban");

        // int real=zuzhuangService.selectcount();
        DecimalFormat df = new DecimalFormat("0.0"); //设置double类型小数点后位数格式
        int LinkLine1 = zuzhuangService.selectLinkbyline("line 1");
        int LinkLine2 = zuzhuangService.selectLinkbyline("line 2");
        int LinkLine3 = zuzhuangService.selectLinkbyline("line 3");

        //SN投入目标与实际达成率
/*
        Calendar cal=Calendar.getInstance();
        int h=cal.get(Calendar.HOUR_OF_DAY);
        int mi=cal.get(Calendar.MINUTE);
        int s=cal.get(Calendar.SECOND);
        int mubiao;
        float realpercent;
        if (h<9){
            mubiao =0;
            realpercent =0;
        }
        else if (h==9){
            mubiao=(mi*60+s)/11;
            realpercent= (((float) real/(float) mubiao)*100);
        }
        else if (h==10){
            mubiao=(60*60+mi*60+s)/11;
            realpercent= ((float) real/(float) mubiao)*100;
        }
        else if(h==11){
            mubiao=((h-10)*60*60+mi*60+s)/11;
            realpercent= ((float) real/(float) mubiao)*100;
        }
        else {
            mubiao=((h-11)*60*60+mi*60+s)/10;
            realpercent= ((float) real/(float) mubiao)*100;
        }
*/

//目检站
        int rightnum1 = checkService.selectrightcount1();
        int rightnum2 = checkService.selectrightcount2();
        int wrongnum1 = checkService.selectwrongcount1();
        int wrongnum2 = checkService.selectwrongcount2();

        int rightnum3 = checkService.selectCheckRelate("无", "线别3");
        int wrongnum3 = checkService.selectCheckRelate("不良", "线别3");


        float rightpercent1;
        if ((rightnum1 + wrongnum1) == 0) {
            rightpercent1 = 0;
        } else {
            rightpercent1 = ((float) rightnum1 / ((float) rightnum1 + (float) wrongnum1)) * 100;
        }

        float rightpercent2;
        if ((rightnum2 + wrongnum2) == 0) {
            rightpercent2 = 0;
        } else {
            rightpercent2 = ((float) rightnum2 / ((float) rightnum2 + (float) wrongnum2)) * 100;
        }

        float rightpercent3;
        if ((rightnum3 + wrongnum3) == 0) {
            rightpercent3 = 0;
        } else {
            rightpercent3 = ((float) rightnum3 / ((float) rightnum3 + (float) wrongnum3)) * 100;
        }


        float checkpercent;
        if (rightnum1 + rightnum2 + wrongnum1 + wrongnum2 + rightnum3 + wrongnum3 == 0) {
            checkpercent = 0;
        } else {
            checkpercent = ((float) (rightnum1 + rightnum2 + rightnum3) / (float) (rightnum1 + rightnum2 + wrongnum1 + wrongnum2 + rightnum3 + wrongnum3)) * 100;
        }

//包装站
        int SnCountLine1 = baozhuangService.selectSnCountByLine("Line 1");
        int SnCountLine2 = baozhuangService.selectSnCountByLine("Line 2");
        int SnCountLine3 = baozhuangService.selectSnCountByLine("Line 3");

        int NboxCountLine1 = baozhuangService.selectNboxCountByLine("line 1");
        int NboxCountLine2 = baozhuangService.selectNboxCountByLine("line 2");
        int NboxCountLine3 = baozhuangService.selectNboxCountByLine("line 3");

        int WboxCountLine1 = baozhuangService.selectWboxCountByLine("line 1");
        int WboxCountLine2 = baozhuangService.selectWboxCountByLine("line 2");
        int WboxCountLine3 = baozhuangService.selectWboxCountByLine("line 3");


        mv.addObject("LinkLine1", LinkLine1)
                .addObject("LinkLine2", LinkLine2)
                .addObject("LinkLine3", LinkLine3)
                .addObject("LinkCount", LinkLine1 + LinkLine2 + LinkLine3)
                //.addObject("mubiao",mubiao)
                //.addObject("realpercent",df.format(realpercent))
                .addObject("rightnum1", rightnum1)
                .addObject("wrongnum1", wrongnum1)
                .addObject("num1", rightnum1 + wrongnum1)
                .addObject("rightpercent1", df.format(rightpercent1))

                .addObject("rightnum2", rightnum2)
                .addObject("wrongnum2", wrongnum2)
                .addObject("num2", rightnum2 + wrongnum2)
                .addObject("rightpercent2", df.format(rightpercent2))


                .addObject("rightnum3", rightnum3)
                .addObject("wrongnum3", wrongnum3)
                .addObject("num3", rightnum3 + wrongnum3)
                .addObject("rightpercent3", df.format(rightpercent3))

                .addObject("checkrightnum", rightnum1 + rightnum2 + rightnum3)
                .addObject("checkwrongnum", wrongnum1 + wrongnum2 + wrongnum3)
                .addObject("checknum", rightnum1 + rightnum2 + wrongnum1 + wrongnum2 + rightnum3 + wrongnum3)
                .addObject("checkpercent", df.format(checkpercent))
                .addObject("SnCountLine1", SnCountLine1)
                .addObject("NboxCountLine1", NboxCountLine1)
                .addObject("WboxCountLine1", WboxCountLine1)

                .addObject("SnCountLine2", SnCountLine2)
                .addObject("NboxCountLine2", NboxCountLine2)
                .addObject("WboxCountLine2", WboxCountLine2)

                .addObject("SnCountLine3", SnCountLine3)
                .addObject("NboxCountLine3", NboxCountLine3)
                .addObject("WboxCountLine3", WboxCountLine3)
                .addObject("BaoZhuangSNCount", SnCountLine1 + SnCountLine2 + SnCountLine3)
                .addObject("BaoZhuangNboxCount", NboxCountLine1 + NboxCountLine2 + NboxCountLine3)
                .addObject("BaoZhuangWboxCount", WboxCountLine1 + WboxCountLine2 + WboxCountLine3);

//CDI
        int cdipassline1 = cdiService.selectCDIpassByline1();
        int cdiNGline1 = cdiService.selectCDINGByline1();
        int cdixiaoji1 = cdipassline1 + cdiNGline1;
        int cdipassline2 = cdiService.selectCDIpassByline2();
        int cdiNGline2 = cdiService.selectCDINGByline2();
        int cdixiaoji2 = cdipassline2 + cdiNGline2;
        int cdipassline3 = cdiService.selectCDIpassByline3();
        int cdiNGline3 = cdiService.selectCDINGByline3();
        int cdixiaoji3 = cdipassline3 + cdiNGline3;
        mv.addObject("line1good", cdipassline1)
                .addObject("line1bad", cdiNGline1)
                .addObject("CDIcountline1", cdixiaoji1)
                .addObject("CDIpercentline1", accuracy(cdipassline1, cdixiaoji1))

                .addObject("line2good", cdipassline2)
                .addObject("line2bad", cdiNGline2)
                .addObject("CDIcountline2", cdixiaoji2)
                .addObject("CDIpercentline2", accuracy(cdipassline2, cdixiaoji2))

                .addObject("line3good", cdipassline3)
                .addObject("line3bad", cdiNGline3)
                .addObject("CDIcountline3", cdixiaoji3)
                .addObject("CDIpercentline3", accuracy(cdipassline3, cdixiaoji3))

                .addObject("CDItotalPass", cdipassline1 + cdipassline2 + cdipassline3)
                .addObject("CDItotalNG", cdiNGline1 + cdiNGline2 + cdiNGline3)
                .addObject("CDItotal", cdixiaoji1 + cdixiaoji2 + cdixiaoji3)
                .addObject("CDIpercent", accuracy(cdipassline1 + cdipassline2 + cdipassline3, cdixiaoji1 + cdixiaoji2 + cdixiaoji3));


//oc4
        int oc4passline1 = oc4Service.selectOC4passByline1();
        int oc4NGline1 = oc4Service.selectOC4NGByline1();
        int oc4countline1 = oc4passline1 + oc4NGline1;
        int oc4passline2 = oc4Service.selectOC4passByline2();
        int oc4NGline2 = oc4Service.selectOC4NGByline2();
        int oc4countline2 = oc4passline2 + oc4NGline2;
        int oc4passline3 = oc4Service.selectOC4passByline3();
        int oc4NGline3 = oc4Service.selectOC4NGByline3();
        int oc4countline3 = oc4passline3 + oc4NGline3;
        int oc4totalpass = oc4passline1 + oc4passline2 + oc4passline3;
        int oc4totalNG = oc4NGline1 + oc4NGline2 + oc4NGline3;
        int oc4total = oc4totalpass + oc4totalNG;
        mv.addObject("oc4passline1", oc4passline1)
                .addObject("oc4passline2", oc4passline2)
                .addObject("oc4passline3", oc4passline3)

                .addObject("oc4NGline1", oc4NGline1)
                .addObject("oc4NGline2", oc4NGline2)
                .addObject("oc4NGline3", oc4NGline3)

                .addObject("oc4countline1", oc4countline1)
                .addObject("oc4countline2", oc4countline2)
                .addObject("oc4countline3", oc4countline3)

                .addObject("oc4percentageline1", accuracy(oc4passline1, oc4countline1))
                .addObject("oc4percentageline2", accuracy(oc4passline2, oc4countline2))
                .addObject("oc4percentageline3", accuracy(oc4passline3, oc4countline3))

                .addObject("oc4totalpass", oc4totalpass)
                .addObject("oc4totalNG", oc4totalNG)
                .addObject("oc4total", oc4total)
                .addObject("oc4percent", accuracy(oc4totalpass, oc4total));

//oc1
        int oc1pass = oc1Service.selectOC1pass();
        int oc1NG = oc1Service.selectOC1NG();
        int oc1total = oc1pass + oc1NG;
        mv.addObject("oc1pass", oc1pass)
                .addObject("oc1NG", oc1NG)
                .addObject("oc1total", oc1total)
                .addObject("oc1percent", accuracy(oc1pass, oc1total));

//OC2
        int oc2pass = oc2Service.selectOC2pass();
        int oc2NG = oc2Service.selectOC2NG();
        int oc2total = oc2pass + oc2NG;
        mv.addObject("oc2pass", oc2pass)
                .addObject("oc2NG", oc2NG)
                .addObject("oc2total", oc2total)
                .addObject("oc2percent", accuracy(oc2pass, oc2total));
        /*
        Map<String, Integer> map_OC4=OC4();
        mv.addObject("OC4line1good",map_OC4.get("line1good"))
        .addObject("OC4line1bad",map_OC4.get("line1bad"))
        .addObject("OC4line2good",map_OC4.get("line2good"))
        .addObject("OC4line2bad",map_OC4.get("line2bad"));

         */
        /*

        mv.addObject("OC4good",map_OC4.get("zhengque"));
        mv.addObject("OC4bad",map_OC4.get("wrong"));

*/

        /*
        Map<String, Integer> map_OC1=OC1();
        mv.addObject("OC1good",map_OC1.get("zhengque"));
        mv.addObject("OC1bad",map_OC1.get("wrong"));

        Map<String, Integer> map_OC2=OC2();
        mv.addObject("OC2good",map_OC2.get("zhengque"));
        mv.addObject("OC2bad",map_OC2.get("wrong"));

         */
//BIT
        int BitPass = bitService.selectBitpass();
        int BitNg = bitService.selectBitNG();
        int BitTotal = BitPass + BitNg;
        mv.addObject("BitPass", BitPass)
                .addObject("BitNg", BitNg)
                .addObject("BitTotal", BitTotal)
                .addObject("BitPercent", accuracy(BitPass, BitTotal));


        //QC
        int qcPASS = qcService.selectQCpass();
        int qcNG = qcService.selectQCNG();
        int qctotal = qcPASS + qcNG;

        mv.addObject("qcPASS", qcPASS)
                .addObject("qcNG", qcNG)
                .addObject("qctotal", qctotal)
                .addObject("qcpercent", accuracy(qcPASS, qctotal));
        return mv;
    }


    public Map<String, Integer> CDI() throws IOException {
        Map<String, Integer> map = new HashMap<String, Integer>();
        int line1good = 0, line1bad = 0;
        int line2good = 0, line2bad = 0;
        DateFormat dateFormat = DateFormat.getDateInstance();
        Date date = new Date();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        String nowtime = dft.format(date);
        //   System.out.println(nowtime);
        String filepath = "D:\\Test_CDI";//D盘下的file文件夹的目录
        File file = new File(filepath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        List<File> wjList = new ArrayList<File>();//新建一个文件集合
        int zhengque = 0, wrong = 0, z = 0;
        String encoding = "GBK";
        String data[] = new String[20];
        System.out.println(fileList.length);
        for (int i = 0; i < fileList.length; i++) {

            if (fileList[i].isFile()) {//判断是否为文件
                // 使用FileInputStream读取文本内容，然后通过InputStreamReader和指定的编码将字符转换为字节
                z++;
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(fileList[i]), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int j = 0;
                while ((lineTxt = bufferedReader.readLine()) != null) {//逐行读取
                    // System.out.println(lineTxt.substring(lineTxt.indexOf("=")+1));//读取“=”后面的字符串
                    data[j] = lineTxt.substring(lineTxt.indexOf("=") + 1);
                    //  System.out.println(data[j]);
                    j++;
                }
                //System.out.println("2020-02-28 17:30:25.indexOf(nowtime));

                if (data[2].equals("1")) {
                    if (data[7].contains(nowtime)) {
                        if (data[8].contains("PASS")) {
                            line1good = line1good + 1;
                        } else {
                            line1bad = line1bad + 1;
                        }

                    }
                } else if (data[2].equals("2")) {
                    if (data[7].contains(nowtime)) {
                        if (data[8].contains("PASS")) {
                            line2good = line2good + 1;
                        } else {
                            line2bad = line2bad + 1;
                        }

                    }


                }


                wjList.add(fileList[i]);

            }

        }


        //    System.out.println("正确的有："+zhengque+"\n"+"错误的有: "+wrong);


        map.put("line1good", line1good);
        map.put("line1bad", line1bad);

        map.put("line2good", line2good);
        map.put("line2bad", line2bad);

        return map;
    }

    public Map<String, Integer> OC1() throws IOException {
        Map<String, Integer> map = new HashMap<String, Integer>();

        DateFormat dateFormat = DateFormat.getDateInstance();
        Date date = new Date();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        String nowtime = dft.format(date);
        //   System.out.println(nowtime);
        String filepath = "D:\\Test_OC1";//D盘下的file文件夹的目录
        File file = new File(filepath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        List<File> wjList = new ArrayList<File>();//新建一个文件集合
        int zhengque = 0, wrong = 0, z = 0;
        String encoding = "GBK";
        String data[] = new String[25];
        System.out.println(fileList.length);
        for (int i = 0; i < fileList.length; i++) {

            if (fileList[i].isFile()) {//判断是否为文件
                // 使用FileInputStream读取文本内容，然后通过InputStreamReader和指定的编码将字符转换为字节
                z++;
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(fileList[i]), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int j = 0;
                while ((lineTxt = bufferedReader.readLine()) != null) {//逐行读取
                    // System.out.println(lineTxt.substring(lineTxt.indexOf("=")+1));//读取“=”后面的字符串
                    data[j] = lineTxt.substring(lineTxt.indexOf("=") + 1);
                    System.out.println(data[j]);
                    j++;
                }
                //System.out.println("2020-02-28 17:30:25.indexOf(nowtime));

                if (data[7].contains(nowtime)) {
                    if (data[8].contains("success")) {
                        zhengque = zhengque + 1;
                    } else {
                        wrong = wrong + 1;
                    }

                }
                wjList.add(fileList[i]);

            }

        }


        System.out.println("正确的有：" + zhengque + "\n" + "错误的有: " + wrong);


        map.put("zhengque", zhengque);
        map.put("wrong", wrong);

        return map;
    }

    public Map<String, Integer> OC2() throws IOException {
        Map<String, Integer> map = new HashMap<String, Integer>();

        DateFormat dateFormat = DateFormat.getDateInstance();
        Date date = new Date();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        String nowtime = dft.format(date);
        //   System.out.println(nowtime);
        String filepath = "D:\\Test_OC2";//D盘下的file文件夹的目录
        File file = new File(filepath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        List<File> wjList = new ArrayList<File>();//新建一个文件集合
        int zhengque = 0, wrong = 0, z = 0;
        String encoding = "GBK";
        String data[] = new String[25];
        System.out.println(fileList.length);
        for (int i = 0; i < fileList.length; i++) {

            if (fileList[i].isFile()) {//判断是否为文件
                // 使用FileInputStream读取文本内容，然后通过InputStreamReader和指定的编码将字符转换为字节
                z++;
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(fileList[i]), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int j = 0;
                while ((lineTxt = bufferedReader.readLine()) != null) {//逐行读取
                    // System.out.println(lineTxt.substring(lineTxt.indexOf("=")+1));//读取“=”后面的字符串
                    data[j] = lineTxt.substring(lineTxt.indexOf("=") + 1);
                    System.out.println(data[j]);
                    j++;
                }
                //System.out.println("2020-02-28 17:30:25.indexOf(nowtime));

                if (data[7].contains(nowtime)) {
                    if (data[8].contains("success")) {
                        zhengque = zhengque + 1;
                    } else {
                        wrong = wrong + 1;
                    }

                }
                wjList.add(fileList[i]);

            }

        }


        System.out.println("正确的有：" + zhengque + "\n" + "错误的有: " + wrong);


        map.put("zhengque", zhengque);
        map.put("wrong", wrong);

        return map;
    }

    public Map<String, Integer> OC3() throws IOException {
        Map<String, Integer> map = new HashMap<String, Integer>();

        DateFormat dateFormat = DateFormat.getDateInstance();
        Date date = new Date();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        String nowtime = dft.format(date);
        //   System.out.println(nowtime);
        String filepath = "D:\\Test_OC1";//D盘下的file文件夹的目录
        File file = new File(filepath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        List<File> wjList = new ArrayList<File>();//新建一个文件集合
        int zhengque = 0, wrong = 0, z = 0;
        String encoding = "GBK";
        String data[] = new String[25];
        System.out.println(fileList.length);
        for (int i = 0; i < fileList.length; i++) {

            if (fileList[i].isFile()) {//判断是否为文件
                // 使用FileInputStream读取文本内容，然后通过InputStreamReader和指定的编码将字符转换为字节
                z++;
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(fileList[i]), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int j = 0;
                while ((lineTxt = bufferedReader.readLine()) != null) {//逐行读取
                    // System.out.println(lineTxt.substring(lineTxt.indexOf("=")+1));//读取“=”后面的字符串
                    data[j] = lineTxt.substring(lineTxt.indexOf("=") + 1);
                    System.out.println(data[j]);
                    j++;
                }
                //System.out.println("2020-02-28 17:30:25.indexOf(nowtime));

                if (data[7].contains(nowtime)) {
                    if (data[8].contains("success")) {
                        zhengque = zhengque + 1;
                    } else {
                        wrong = wrong + 1;
                    }

                }
                wjList.add(fileList[i]);

            }

        }


        System.out.println("正确的有：" + zhengque + "\n" + "错误的有: " + wrong);


        map.put("zhengque", zhengque);
        map.put("wrong", wrong);

        return map;
    }

    public Map<String, Integer> OC4() throws IOException {
        Map<String, Integer> map = new HashMap<String, Integer>();
        int line1good = 0, line1bad = 0;
        int line2good = 0, line2bad = 0;
        DateFormat dateFormat = DateFormat.getDateInstance();
        Date date = new Date();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        String nowtime = dft.format(date);
        String filepath = "D:\\Test_OC4";//D盘下的file文件夹的目录
        File file = new File(filepath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        List<File> wjList = new ArrayList<File>();//新建一个文件集合
        int zhengque = 0, wrong = 0, z = 0;
        String encoding = "GBK";
        String data[] = new String[30];
        System.out.println(fileList.length);
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {//判断是否为文件
                // 使用FileInputStream读取文本内容，然后通过InputStreamReader和指定的编码将字符转换为字节
                z++;
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(fileList[i]), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int j = 0;
                while ((lineTxt = bufferedReader.readLine()) != null) {//逐行读取
                    // System.out.println(lineTxt.substring(lineTxt.indexOf("=")+1));//读取“=”后面的字符串
                    data[j] = lineTxt.substring(lineTxt.indexOf("=") + 1);
                    // System.out.println(data[j]);
                    j++;
                }
                //System.out.println("2020-02-28 17:30:25.indexOf(nowtime));
                if (data[2].equals("1")) {
                    if (data[7].contains(nowtime)) {
                        if (data[8].contains("success")) {
                            line1good = line1good + 1;
                        } else {
                            line1bad = line1bad + 1;
                        }

                    }
                } else if (data[2].equals("2")) {
                    if (data[7].contains(nowtime)) {
                        if (data[8].contains("success")) {
                            line2good = line2good + 1;
                        } else {
                            line2bad = line2bad + 1;
                        }

                    }


                }


                //  wjList.add(fileList[i]);

            }

        }
        //  System.out.println("OC4正确的有："+zhengque+"\n"+"错误的有: "+wrong);

/*
        map.put("zhengque", zhengque);
        map.put("wrong", wrong);
*/
/*
map.put("line1good",line1good);
map.put("line2ba")*/
        //  System.out.println("正确的有："+zhengque+"\n"+"错误的有: "+wrong);
        //  System.out.println("1号： "+line1good+"     "+line1bad);
        // System.out.println("2号： "+line2good+"     "+line2bad);


        map.put("line1good", line1good);
        map.put("line1bad", line1bad);

        map.put("line2good", line2good);
        map.put("line2bad", line2bad);
        return map;
    }

    public Map<String, Integer> readQC() throws IOException {
        Map<String, Integer> map = new HashMap<String, Integer>();
        String filepath = "D:\\Test_QC";//D盘下的file文件夹的目录
        File file = new File(filepath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//该目录下的所有文件放置在一个File类型的数组中
        int zhengque = 0, wrong = 0, z = 0;
        String encoding = "GBK";
        String data[] = new String[10];
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(fileList[i]), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int j = 0;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    data[j] = lineTxt.substring(lineTxt.indexOf(":") + 1).trim();
                    j++;
                }
                if (data[7].equals("PASS")) {
                    zhengque++;
                } else {
                    wrong++;
                }
            }

        }

        map.put("QCzhengque", zhengque);
        map.put("QCwrong", wrong);
        map.put("QCtotal", fileList.length);

        return map;


    }

    public static String accuracy(int num, int total) {
        DecimalFormat df = new DecimalFormat("0%");
        //可以设置精确几位小数
        df.setMaximumFractionDigits(1);
        // 模式 例如四舍五入
        df.setRoundingMode(RoundingMode.HALF_UP);
        if (total == 0) {
            return "0.0%";
        } else {
            String accuracy_num = df.format(num * 1.0 / total * 1.0);
            return accuracy_num;
        }

    }

    @RequestMapping(value = "/ChangeSNCheck", method = RequestMethod.GET)
    public String ChangeSNCheck() {
        return "ChangeSNCheck";
    }

    // 目检时检测是否经过CDI
    @RequestMapping(value = "/passCDI/{productionSN}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg checkCDI(@PathVariable String productionSN, String mo) {
        List<CDI> list = cdiService.selectPassCDI(productionSN, mo);
        System.out.println(list.size());
        if (list.size() > 0) {
            return JsonMsg.success();
        } else {
            return JsonMsg.fail();
        }
    }
}
