package com.hrms.util;


import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class ExportExcel<T> {

    public static void writeSpecifiedCell(int sheetid, int rowid,int cellid,String value) {

        //根据路径获取文件
        File file ;
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date date=cal.getTime();
        int m=date.getMonth();
        if (m==8){
            //根据路径获取文件
             file = new File("D:\\EXCEL\\2020.9\\生产记录表 For MES.xlsx");
        }else {
            //根据路径获取文件
             file = new File("D:\\EXCEL\\2020.10\\生产记录表 For MES.xlsx");
        }

        //SimpleDateFormat formatter = new SimpleDateFormat("MM");

        //System.out.println(file);
        //定义输入流对象
        FileInputStream excelFileInputStream;
        try {
            excelFileInputStream = new FileInputStream(file);
            // 拿到文件转化为JavaPoi可操纵类型
            Workbook workbook = WorkbookFactory.create(excelFileInputStream);
            excelFileInputStream.close();
            ////获取excel表格
            Sheet sheet = workbook.getSheetAt(sheetid);

                // 获取行
                Row row = sheet.getRow(rowid);
                // 获取列
                Cell cell = row.getCell(cellid);
                //设置单元的值
                cell.setCellValue(value);

            //写入数据
            FileOutputStream excelFileOutPutStream = new FileOutputStream(file);
            workbook.setForceFormulaRecalculation(true);
            workbook.write(excelFileOutPutStream);
            excelFileOutPutStream.flush();
            excelFileOutPutStream.close();
           // System.out.println("指定单元格设置数据写入完成");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            e.printStackTrace();
        }
    }
    public static void writeSpecifiedCellint(int sheetid, int rowid,int cellid,int value) {
        //根据路径获取文件
        File file ;
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date date=cal.getTime();
        int m=date.getMonth();
        if (m==8){
            //根据路径获取文件
            file = new File("D:\\EXCEL\\2020.9\\生产记录表 For MES.xlsx");
        }else {
            //根据路径获取文件
            file = new File("D:\\EXCEL\\2020.10\\生产记录表 For MES.xlsx");
        }

        //定义输入流对象
        FileInputStream excelFileInputStream;
        try {
            excelFileInputStream = new FileInputStream(file);
            // 拿到文件转化为JavaPoi可操纵类型
            Workbook workbook = WorkbookFactory.create(excelFileInputStream);
            excelFileInputStream.close();
            ////获取excel表格
            Sheet sheet = workbook.getSheetAt(sheetid);

            // 获取行
            Row row = sheet.getRow(rowid);
            // 获取列
            Cell cell = row.getCell(cellid);
            //设置单元的值
            cell.setCellValue(value);

            //写入数据
            FileOutputStream excelFileOutPutStream = new FileOutputStream(file);
            workbook.setForceFormulaRecalculation(true);
            workbook.write(excelFileOutPutStream);
            excelFileOutPutStream.flush();
            excelFileOutPutStream.close();
           // System.out.println("指定单元格设置数据写入完成");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            e.printStackTrace();
        }
    }
    public static void openexcel(){
        //根据路径获取文件
        File file = new File("D:\\EXCEL\\7月\\生产记录表 For MES.xlsx");
        //定义输入流对象
        FileInputStream excelFileInputStream;
        try {
            excelFileInputStream = new FileInputStream(file);
            // 拿到文件转化为JavaPoi可操纵类型
            Workbook workbook = WorkbookFactory.create(excelFileInputStream);
            excelFileInputStream.close();

            //写入数据
            FileOutputStream excelFileOutPutStream = new FileOutputStream(file);
            workbook.setForceFormulaRecalculation(true);
            workbook.write(excelFileOutPutStream);
            excelFileOutPutStream.flush();
            excelFileOutPutStream.close();
            System.out.println("指定单元格设置数据写入完成");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            e.printStackTrace();
        }
    }


    //读取不良数据统计表。
    public static  void writeErrorToExcel(int sheetid, int rowid,int cellid, int value) {

        //根据路径获取文件
        File file ;
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date date=cal.getTime();
        int m=date.getMonth();
        if (m==8){
            //根据路径获取文件
            file = new File("D:\\EXCEL\\2020.9\\BIT Error Code 统计表.xlsx");
        }else {
            //根据路径获取文件
            file = new File("D:\\EXCEL\\2020.10\\BIT Error Code 统计表.xlsx");
        }

        //定义输入流对象
        FileInputStream excelFileInputStream;
        try {
            excelFileInputStream = new FileInputStream(file);
            // 拿到文件转化为JavaPoi可操纵类型
            Workbook workbook = WorkbookFactory.create(excelFileInputStream);
            excelFileInputStream.close();
            ////获取excel表格
            Sheet sheet = workbook.getSheetAt(sheetid);

            // 获取行
            Row row = sheet.getRow(rowid);
            // 获取列
            Cell cell = row.getCell(cellid);
            //设置单元的值
            cell.setCellValue(value);
            //写入数据
            FileOutputStream excelFileOutPutStream = new FileOutputStream(file);
            workbook.setForceFormulaRecalculation(true);
            workbook.write(excelFileOutPutStream);
            excelFileOutPutStream.flush();
            excelFileOutPutStream.close();
            System.out.println("不良数据指定单元格设置数据写入完成");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            e.printStackTrace();
        }
    }




    public static  <T> void writeErrorToExcelString(int sheetid, int rowid,int cellid, T t) {
        //根据路径获取文件
        File file;
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date date=cal.getTime();
        int m=date.getMonth();
        if (m==8){
            //根据路径获取文件
            file = new File("D:\\EXCEL\\2020.9\\BIT Error Code 统计表.xlsx");
        }else {
            //根据路径获取文件
            file = new File("D:\\EXCEL\\2020.10\\BIT Error Code 统计表.xlsx");
        }


        System.out.println(file);
        //定义输入流对象
        FileInputStream excelFileInputStream;
        try {
            excelFileInputStream = new FileInputStream(file);
            // 拿到文件转化为JavaPoi可操纵类型
            Workbook workbook = WorkbookFactory.create(excelFileInputStream);
            excelFileInputStream.close();
            ////获取excel表格
            Sheet sheet = workbook.getSheetAt(sheetid);
            // 获取行
            Row row = sheet.getRow(rowid);
            // 获取列
            Cell cell = row.getCell(cellid);
            //设置单元的值
            cell.setCellValue((String.valueOf(t)));

            //写入数据
            FileOutputStream excelFileOutPutStream = new FileOutputStream(file);
            workbook.setForceFormulaRecalculation(true);
            workbook.write(excelFileOutPutStream);
            excelFileOutPutStream.flush();
            excelFileOutPutStream.close();
            System.out.println("指定单元格设置数据写入完成");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            e.printStackTrace();
        }
    }





}