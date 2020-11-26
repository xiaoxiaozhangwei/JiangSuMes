package com.hrms.service;

import com.hrms.bean.Shipment;
import com.hrms.bean.Storage;
import com.hrms.mapper.ShipmentMapper;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShipmentService {
    @Autowired
    ShipmentMapper shipmentMapper;
    @Autowired
    StorageService storageService;


    public  int insertShipment(Shipment shipment)
    {
         //添加到出库数据库
        int i=  shipmentMapper.insertShipment(shipment);
        //出库之后，入库数量更改
        int j=storageService.updateStorageByNum(shipment.getWbox(),shipment.getNum());
        return  i;
    }


    //出货SN信息比对  根据所有出货SN 查询是否重复
    public List<String> selectByAllSN(String wbox)
    {
        return  shipmentMapper.selectByAllSN(wbox);
    }




    //导出要出货的SN号
    public  List<String> selectShipmentSN(List<String> wboxs)
    {

        return   shipmentMapper.selectShipmentSN(wboxs);
    }



    public HSSFWorkbook exportData(List<String> wboxs) {
        //获取SN信息
        List<String> shipmentSN = shipmentMapper.selectShipmentSN(wboxs);
        // 创建工作空间
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建表
        HSSFSheet sheet = wb.createSheet("出货SN号");
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        // 创建行
        HSSFRow row = sheet.createRow((int) 0);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中

        // 背景色
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());

        // 设置边框
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);

        // 生成一个字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        font.setBold(true);
        font.setFontName("宋体");

        // 把字体 应用到当前样式
        style.setFont(font);

        // 添加表头数据
        String excelHeader = "SN";
        HSSFCell cell = row.createCell(0);
        cell.setCellValue(excelHeader);
        cell.setCellStyle(style);


        // 添加单元格数据
        for (int i = 0; i <shipmentSN.size(); i++) {

            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(shipmentSN.get(i));
        }
        System.out.println(wb);
        return wb;
    }

    //查找入库时间最早的，先进先出
    public Storage selectFirstByStorageTime(String model)
    {
        return shipmentMapper.selectFirstByStorageTime(model);
    }

}
