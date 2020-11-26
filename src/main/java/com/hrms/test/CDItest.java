package com.hrms.test;

import com.hrms.service.CDIService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CDItest {

    @Autowired
    private CDIService cdiService;


    @Test
    public void select() {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date date=cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //CDI报表
        List<String> cdilist = cdiService.selecthismonthmodel(formatter.format(date));
        for (int i = 0; i < cdilist.size(); i++) {
           // ExportExcel.writeSpecifiedCell(4, 6 + i * 4, 0, cdilist.get(i));
        }
        for (int i = 0; i < cdilist.size(); i++) {
            int r = cdiService.selectrightcountbymodeldaily(formatter.format(date) + "%", cdilist.get(i));
            int w = cdiService.selectwrongcountbymodeldaily(formatter.format(date) + "%", cdilist.get(i));

            System.out.println();

           // ExportExcel.writeSpecifiedCellint(4, 7 + i * 4, 2 + date.getDate(), r);
           // ExportExcel.writeSpecifiedCellint(4, 8 + i * 4, 2 + date.getDate(), w);
        }
    }
}
