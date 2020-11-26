package com.hrms.controller;

import com.hrms.bean.Shipment;
import com.hrms.bean.Storage;
import com.hrms.mapper.ShipmentMapper;
import com.hrms.service.GongdanService;
import com.hrms.service.ShipmentService;
import com.hrms.service.StorageService;
import com.hrms.util.JsonMsg;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cangkuShipment")
public class CangkuShipmentController {

    @Autowired
    StorageService storageService;
    @Autowired
    GongdanService gongdanService;
    @Autowired
    ShipmentService shipmentService;
    @Autowired
    ShipmentMapper shipmentMapper;



    @RequestMapping(value = "/shipment")
    public String  shipmentInterface() {
        return "cangkuShipment";
    }


    //通过外箱号 查询入库的相关信息 包括fw 数量 工单等
    @RequestMapping(value = "/selectByWbox",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg  selectByWaiXiang(String wbox)
    {
        Storage storage = storageService.selectWaiExist(wbox);
        if (storage!=null)
        {
            return  JsonMsg.success().addInfo("storage",storage);
        }
        else
        {
            return  JsonMsg.fail();
        }
    }



    //添加出货信息到数据库
    //同时添加到出入库记录表
    @RequestMapping(value = "/addShipment",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg  addShipment(Shipment shipment,HttpServletRequest request)
    {
        String operator = (String) request.getSession().getAttribute("name");
        shipment.setOperator(operator);
        String wbox = shipment.getWbox();
        String erp = shipment.getErpItemNo();
        Integer num = shipment.getNum();
        String snRecord = "-" + num;
        String stock = storageService.getStock(wbox);
        String wboxRecord = "-1";
        int i=0;
        Integer warehouseRecodeResult = null;
        try {
            i=  shipmentService.insertShipment(shipment);
            warehouseRecodeResult = storageService.insertRecord(erp,snRecord,stock,operator,wboxRecord);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        if (0 != i && 1 == warehouseRecodeResult)
        {
            return  JsonMsg.success();
        }
        else
        {
            return  JsonMsg.fail();
        }
    }


    //根据所有出货sn号，检查是否出现重复
    @RequestMapping(value = "/selectByAllSn",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg  selectByAllSn(String wbox)
    {
        List<String>  list=null;
        list= shipmentService.selectByAllSN(wbox);
        if (list.size()==0)
        {
            return  JsonMsg.success();
        }
        else
        {
            return  JsonMsg.fail().addInfo("list",list);
        }
    }



    //导出出货sn报表
    @RequestMapping(value = "/exportShipmentSN/{wbox}", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response, @PathVariable String[] wbox) throws UnsupportedEncodingException {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < wbox.length; i++) {
            list.add(wbox[i]);
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="+ java.net.URLEncoder.encode("出货SN", "UTF-8")+".xls");
        OutputStream ouputStream = null;
        HSSFWorkbook wb=shipmentService.exportData(list);
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



    //查找入库时间最早的，先进先出
    @RequestMapping(value = "/selectFirstByStorageTime",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg  selectFirstByStorageTime(String model)
    {
        Storage storage=null;
        storage= shipmentService.selectFirstByStorageTime(model);
        if (storage==null)
        {
            return  JsonMsg.success();
        }
        else
        {
            return  JsonMsg.fail().addInfo("storage",storage);
        }
    }



}
