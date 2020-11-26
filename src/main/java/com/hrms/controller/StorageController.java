package com.hrms.controller;

import com.hrms.bean.Baozhuang;
import com.hrms.bean.Gongdan;
import com.hrms.bean.Storage;
import com.hrms.service.BaozhuangService;
import com.hrms.service.GongdanService;
import com.hrms.service.StorageService;
import com.hrms.util.JsonMsg;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/storage")
public class StorageController {
    @Autowired
    BaozhuangService baozhuangService;
    @Autowired
    StorageService storageService;
    @Autowired
    GongdanService gongdanService;


    //添加入库信息,同时往成品仓记录表中添加记录
    @RequestMapping(value = "/saveStorage", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg saveStorage(Storage storage) {
        String erp = storage.getErp();
        Integer num = storage.getNum();
        String snRecord = "+" + num;
        String stock = storage.getStore_area();
        System.out.println("展板的值是" + stock);
        String operator = storage.getOperator();
        Integer i = storageService.insertStorage(storage);
        if (i == 1) {
            String wboxRecord = "+1";
            // 在入库时往成品仓记录表中添加记录
            Integer warehouseRecodeResult = storageService.insertRecord(erp, snRecord, stock, operator, wboxRecord);
            if (warehouseRecodeResult == 1) {
                return JsonMsg.success();
            } else {
                return JsonMsg.fail();
            }
        } else {
            return JsonMsg.fail();
        }
    }

    //添加至数据库
    @RequestMapping(value = "/saveStorage2", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg saveStorage2(String wbox) throws Exception {
        //System.out.println("12");
        List<Baozhuang> count = baozhuangService.selectWbox(wbox);
        System.out.println(count);
        //System.out.println("1");
        Storage storage = null;
        if (count != null && !count.isEmpty()) {
            storageService.insertStorage(count, storage);
            return JsonMsg.success();
        } else {
            return JsonMsg.fail();
        }


    }


    //导出XIT报表 按工单查 全部
    @RequestMapping(value = "/exportExcel/{mo_id}/{type}", method = RequestMethod.GET)
    public void export1(HttpServletRequest request, HttpServletResponse response, @PathVariable String mo_id, @PathVariable String type) {
        String d = "";
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + mo_id + ".xls");
        OutputStream ouputStream = null;
        HSSFWorkbook wb = exportXITData(mo_id, d, type);
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
        } catch (Exception e) {
            throw new RuntimeException("系统异常");
        } finally {
            try {
                ouputStream.flush();
                ouputStream.close();
            } catch (Exception e) {
                throw new RuntimeException("系统异常");
            }
        }
    }

    //导出XIT报表 按工单查 根据时间
    @RequestMapping(value = "/exportExcel/{mo}/{d}/{type}", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response, @PathVariable String mo_id, @PathVariable String d, @PathVariable String type) {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + mo_id + ".xls");
        OutputStream ouputStream = null;
        HSSFWorkbook wb = exportXITData(mo_id, d, type);
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);

        } catch (Exception e) {
            throw new RuntimeException("系统异常");
        } finally {
            try {
                ouputStream.flush();
                ouputStream.close();
            } catch (Exception e) {
                throw new RuntimeException("系统异常");
            }
        }
    }

    private HSSFWorkbook exportXITData(String mo, String d, String type) {
        //获取SN信息
        List<Storage> storages;
        System.out.println("写入EXCLE时的工单号为" + mo);
        System.out.println("写入EXCLE时的type值为" + type);
        if ("1".equals(type)) {
            storages = storageService.selectsnByStorageMo(mo, d);
            System.out.println("根据入库单查询时的storages的数量是" + storages.size());
        } else {
            storages = storageService.selectsnByMo(mo, d);
            System.out.println("根据工单查询时的storages的数量是" + storages.size());
        }

        // 创建工作空间
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建表
        HSSFSheet sheet = wb.createSheet("工单SN号");
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        // 创建行
        HSSFRow row = sheet.createRow((int) 0);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        System.out.println("生成一个样式时的excel");
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
        String[] excelHeader = {"完工入库单项次", "SN", "BIN01"};
        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
        }
        System.out.println("表头数据已生成");

        // 添加单元格数据
        for (int i = 0; i < storages.size(); i++) {

            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue("1");
            row.createCell(1).setCellValue(storages.get(i).getSn());
            row.createCell(2).setCellValue("BIN01");

        }
        System.out.println("单元格数据已生成");
        System.out.println(wb);
        return wb;

    }


    //导出国科入库单 仅有入库工单 查全部
    @RequestMapping(value = "/gkexportExcel/{mo}/{type}", method = RequestMethod.GET)
    public void gkexport1(HttpServletRequest request, HttpServletResponse response, @PathVariable String mo, @PathVariable String type) {
        String d = "";
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + mo + ".xls");
        OutputStream ouputStream = null;
        HSSFWorkbook wb = exportGKData(mo, d, type);
        // System.out.println(mo);
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);

        } catch (Exception e) {
            throw new RuntimeException("系统异常");
        } finally {
            try {
                ouputStream.flush();
                ouputStream.close();
            } catch (Exception e) {
                throw new RuntimeException("系统异常");
            }
        }
    }

    //导出国科入库单 根据入库单 和时间
    @RequestMapping(value = "/gkexportExcel/{mo}/{d}/{type}", method = RequestMethod.GET)
    public void gkexport(HttpServletRequest request, HttpServletResponse response, @PathVariable String mo, @PathVariable String d, @PathVariable String type) {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + mo + ".xls");
        OutputStream ouputStream = null;
        HSSFWorkbook wb = exportGKData(mo, d, type);
        // System.out.println(mo);
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);

        } catch (Exception e) {
            throw new RuntimeException("系统异常");
        } finally {
            try {
                ouputStream.flush();
                ouputStream.close();
            } catch (Exception e) {
                throw new RuntimeException("系统异常");
            }
        }
    }

    private HSSFWorkbook exportGKData(String mo, String d, String type) {
        List<Storage> storages;
        String pn;
        String gk_mo;
        Gongdan gongdan;
        //获取SN信息
        // List<Storage> storages = storageService.selectsnByMo(mo,d);
        if ("1".equals(type)) {
            storages = storageService.selectsnByStorageMo(mo, d);
            //根据入库单导出工单
            String gongdanMo = storageService.selectMoByStorageMo(mo);
            //获取PN
            gongdan = gongdanService.getMoById(gongdanMo);

        } else {
            storages = storageService.selectsnByMo(mo, d);
            //获取PN
            gongdan = gongdanService.getMoById(mo);
        }
        pn = gongdan.getPn();
        gk_mo = gongdan.getGkMo();
        //获取当前日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date now_date = cal.getTime();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyMMdd");
        // 创建工作空间
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建表
        HSSFSheet sheet = wb.createSheet("0826");
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        // 创建行
        HSSFRow row = sheet.createRow((int) 0);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中

        // 背景色
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_ORANGE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.LIGHT_ORANGE.getIndex());

        // 设置边框
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);

        // 生成一个字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        font.setBold(true);
        font.setFontName("宋体");

        // 把字体 应用到当前样式
        style.setFont(font);

        // 添加表头数据
        String[] excelHeader = {"Date", "Cust. PO No.", "PN", "Cust. Prod. Name", "Stock", "Lot No.", "Qty", "BIN", "留空", "SN号"};
        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
        }

        // 添加单元格数据
        for (int i = 0; i < storages.size(); i++) {

            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(formatter1.format(now_date));
            row.createCell(1).setCellValue(gk_mo);
            row.createCell(2).setCellValue(pn);
            row.createCell(3).setCellValue(storages.get(i).getModel());
            row.createCell(4).setCellValue(storages.get(i).getStock());
            row.createCell(5).setCellValue("XN" + formatter2.format(now_date) + "01");
            row.createCell(6).setCellValue("1");
            row.createCell(7).setCellValue("BIN01");
            row.createCell(8).setCellValue("");
            row.createCell(9).setCellValue(storages.get(i).getSn());

        }
        return wb;
    }


    //根据外箱号去入库数据库查询是否重复
    @RequestMapping(value = "/selectWaiExistByStorage", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectWaiExistByStorage(String wbox) {
        Storage storages = storageService.selectWaiExistByStorage(wbox);
        if (storages != null) {
            return JsonMsg.fail();
        } else {
            return JsonMsg.success();
        }
    }


    //查询工单是否存在
    @RequestMapping(value = "/selectAllByMo", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectAllByMo(String mo_id, String d) {
        System.out.println(mo_id);
        System.out.println(d);
        List<Storage> storages = storageService.selectMoExist(mo_id, d);
        System.out.println(storages);
        if (storages != null && !storages.isEmpty()) {
            return JsonMsg.success().addInfo("storages", storages);
        } else {
            return JsonMsg.fail();
        }

    }


    //根据工单导出SN
    @RequestMapping(value = "/selectsnByMo", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectsnByMo(String mo_id, String d) {
        List<Storage> storages = storageService.selectsnByMo(mo_id, d);
        return JsonMsg.success().addInfo("storages", storages);
    }

    //根据入库单查询导出SN号
    @RequestMapping(value = "/selectsnByStorageMo", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectsnByStorageMo(String mo, String d) {
        List<Storage> storages = storageService.selectsnByStorageMo(mo, d);
        if (storages != null && !storages.isEmpty()) {
            return JsonMsg.success().addInfo("storages", storages);
        } else {
            return JsonMsg.fail();
        }
    }

    //拆箱和合箱 删除旧入库外箱
    //向成品仓记录表中添加记录
    @RequestMapping(value = "/deleteBywbox", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg deleteBywbox(String wbox) {
        System.out.println(wbox);
        Storage storage = storageService.selectByWbox(wbox);
        System.out.println(storage);
        String erp = storage.getErp();
        Integer num = storage.getNum();
        String snRecord = "-" + num;
        String stock = storage.getStore_area();
        String operator = storage.getOperator();
        String wboxRecord = "-1";
        System.out.println(erp+"  "+ snRecord + " " + stock + "  "+ operator + "  " + wboxRecord);
        if (null != storage) {  // 该外箱在入库表中有数据，则让其能够拆箱并进行记录。
            Integer resultDelete = storageService.deleteByWbox(wbox);
            System.out.println(resultDelete + "删除");
            Integer resultRecord = storageService.insertRecord(erp, snRecord, stock, operator, wboxRecord);
            System.out.println(resultRecord + "记录表");
            if (1 == resultDelete && 1 == resultRecord) {
                return JsonMsg.success();
            } else {
                return JsonMsg.success();
            }
        } else {
            return JsonMsg.fail();
        }
    }
}



