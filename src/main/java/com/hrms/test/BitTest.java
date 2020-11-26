package com.hrms.test;

import com.hrms.mapper.BaozhuangMapper;
import com.hrms.mapper.BitMapper;
import com.hrms.service.BitService;
import com.hrms.util.ExportExcel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class BitTest {

    @Autowired
    BitService bitService;

    @Autowired
    BitMapper bitMapper;

    @Test
    public void one() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date date = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // System.out.println(date.getDate() );
        //String daytime= formatter.format(date);
        String daytime = formatter.format(date);
        //System.out.println(daytime);

        //
        //int  errorCount=bitMapper.selectpasscountbymodelandbitnumber(daytime,"GG7ZT256S3C27","4");
        // System.out.println(errorCount);
        // List<String> modelCount=bitService.selectModelByday();
        // System.out.println(modelCount.size());

    }

    @Test
    public void test() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date date = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String daytime = formatter.format(date);
        String[] errorcode = {"0x683A", "0x443A", "0x5D3A", "0x3A", "0x1A", "0x0E", "0x0D", "0x08", "0x9D3A"};
        List<String> modelCount = bitService.selectModelByday();
        // System.out.println(s.get(0)+"  "+s.get(1));
        System.out.println("开始时间：" + new Date().toString());
        String bitnumber;
        for (int i = 1; i < 6; i++) {

            for (int j = 0; j < modelCount.size(); j++) {
                if (i < 3) {
                    bitnumber = String.valueOf(i);
                } else {
                    bitnumber = String.valueOf(i + 1);
                }

                ExportExcel.writeErrorToExcelString(i, 19 + j * 16, 0, modelCount.get(j));
                //   int passcount=bitService.selectpasscountbymodelandbitnumber(daytime,modelCount.get(j),bitnumber);
                // System.out.println("第"+bitnumber+"温箱: "+"   " +modelCount.get(j)+"良品数量：  "+passcount);
                // ExportExcel.writeErrorToExcel(i, 20 + j * 16, date.getDate() + 3, passcount);

                for (int z = 0; z < 9; z++) {

                    //  int errorCount = bitService.selectNGByBitNumberday(daytime, bitnumber, modelCount.get(j), errorcode[z]);
                    //  System.out.println("第"+bitnumber+"温箱: "+"   " +modelCount.get(j)+"的"+errorcode[z]+"   不良数量："+errorCount);
                    //ExportExcel.writeErrorToExcel(i, 16 * j + 23 + z, date.getDate() + 3, errorCount);


                }


            }

        }

        System.out.println("结束时间：" + new Date().toString());

    }

    @Test
    public void exceltest() {
        /*
          //OC4报表
        System.out.println("OC4报表");
        List<String> oc4list=oc4Service.selecthismonthmodel();
        for (int i=0;i<oc4list.size();i++){
            ExportExcel.writeSpecifiedCell(3,6+i*4,0,oc4list.get(i));
        }
        for (int i = 0; i < oc4list.size(); i++) {
            int r = oc4Service.selectrightcountbymodeldaily(formatter.format(date), oc4list.get(i));
            int w = oc4Service.selectwrongcountbymodeldaily(formatter.format(date), oc4list.get(i));
            ExportExcel.writeSpecifiedCellint(3, 7 + i * 4, 2 + date.getDate(), r);
            ExportExcel.writeSpecifiedCellint(3, 8 + i * 4, 2 + date.getDate(), w);
        }



         */
    }


}
