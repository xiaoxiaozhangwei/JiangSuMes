package com.hrms.controller;

import com.hrms.bean.MaterialRecord;
import com.hrms.bean.MaterialShipment;
import com.hrms.bean.MaterialStorage;
import com.hrms.service.MaterialShipmentService;
import com.hrms.service.MaterialStorageService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/materialShipment")
public class MaterialShipmentController {

    @Autowired
    MaterialStorageService materialStorageService;
    @Autowired
    MaterialShipmentService materialShipmentService;


    //跳转到原料仓出货界面
    @RequestMapping(value = "/shipment", method = RequestMethod.GET)
    public String shipmentInterface() {
        return "materialShipment";
    }


    //根据erp 查询入库的工单，model ,数量和仓储位置等信息
    @RequestMapping(value = "/selectByErp", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectByWaiXiang(String erp) {
        List<MaterialStorage> materialStorage = materialStorageService.selectMaterialStorageByTime(erp);
        System.out.println(materialStorage.get(0));
        if (materialStorage != null) {
            return JsonMsg.success().addInfo("materialStorage", materialStorage);
        } else {
            return JsonMsg.fail();
        }
    }

    // 查找前台传入的erp料号的数据，按时间排序，先进先出
    // 该方法被废弃了
    /*@RequestMapping(value = "/selectMaterialStorageByTime", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectMaterialStorageByTime(String erp) {
        List<MaterialStorage> list = materialStorageService.selectMaterialStorageByTime(erp);
        return JsonMsg.success().addInfo("list", list);
    }*/

    // 出库时，要同时更改入库表，出库表，记录表三条表的记录
    // 该方法被废弃了
   /* @RequestMapping(value = "/insertShipment", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg insertShipment(MaterialShipment materialShipment, HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        String operator = (String)servletContext.getAttribute("name");
        materialShipment.setOperator(operator);
        Integer num = materialShipment.getNum();
        System.out.println("传入的choosenum为"+num );
        String record = num.toString(); // 放入记录表数据库后显示+ —号，+代表入库，-代表出库
        record = "-" + record;
        System.out.println("record的值为"+ record);
        String erp = materialShipment.getErp();
        String purchaseNumber = materialShipment.getPurchaseNumber();
        // 出库时，向出库表中添加出库数据
        int result1 = materialShipmentService.insertShipment(materialShipment);
        System.out.println("出库表添加成功");
        // 出库的同时，向入库表中更改数量
        Integer result2 = materialStorageService.updateStorage(num, erp, purchaseNumber);
        System.out.println("入库表修改成功");
        // 出库的同时，向记录表中添加记录
        Integer result3 = materialStorageService.insertRecord(erp, record,operator);
        System.out.println("记录表添加成功");
        if (result1 == 1 && result2 == 1 && result3 == 1) {
            return JsonMsg.success();
        } else {
            return JsonMsg.fail();
        }
    }*/

    // 根据erp查询erp记录表
    @RequestMapping(value = "/selectERPHistory", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectERPHistory(String erp) {
        List<MaterialRecord> erpRecord = materialStorageService.selectERPHistory(erp);
        if (erpRecord != null && erpRecord.size() != 0) {
            return JsonMsg.success().addInfo("erpRecord", erpRecord);
        } else {
            return JsonMsg.fail();
        }
    }

    // 来料出库页面，判断出库数量是否大于该外箱所拥有的数量
    @RequestMapping(value = "/selectNumber",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectNumber(Integer num,String wbox){
        System.out.println(num+wbox);
        Integer result = materialStorageService.selectNumber(wbox);
        System.out.println(result);
        if(result < num){
            return JsonMsg.fail();
        }else{
            return JsonMsg.success();
        }
    }

    // 扫描枪扫描储位，判断该储位上的erp是否是该erp中最早入库的。先进先出。不是的话要提示哪一个储位上的是最早入库的
    @RequestMapping(value = "/stock",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg saostock(String stock,String erp){
        String saostock = materialStorageService.saostock(erp);
        if(stock.equals(saostock) ){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail().addInfo("stock",saostock);
        }
    }

    // 扫描枪扫描外箱，判断该外箱是否属于该储位，如果不属于，弹出该外箱所属储位。如果属于，就在下方表格中自动填充数量
    // 但是自动填充的数量不能够只读，应该让它可以变更，因为有可能是尾数箱
    @RequestMapping(value = "/wbox",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg saowbox(String wbox,String stock){
        List<String> saowbox = materialStorageService.saowbox(stock);
        if (saowbox.contains(wbox)){
            // 该外箱属于该储位，在下方表格中自动填充数量
            Integer num = materialStorageService.selectnum(wbox);
            return JsonMsg.success().addInfo("num",num);
        }else{
            // 在扫描枪扫描外箱之后，如果该外箱不属于该储位，那么就需要提示该外箱属于哪个储位
            String saostock = materialStorageService.selectstock(wbox);
            return JsonMsg.fail().addInfo("stock",saostock);
        }
    }

    // 信息表格插入到数据库中，更新三张表数据，出库表，记录表，入库表
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg add(MaterialShipment materialShipment, HttpServletRequest request){

        String operator = (String) request.getSession().getAttribute("name");

        String wbox = materialShipment.getWbox();

        MaterialStorage materialStorage = materialStorageService.selectMessage(wbox);

        String storageArea = materialStorage.getStorageArea();

        String mo = materialStorage.getMo();

        String purchaseNumber = materialStorage.getPurchaseNumber();

        materialShipment.setOperator(operator);
        Integer num = materialShipment.getNum();

        String record = num.toString(); // 放入记录表数据库后显示+ —号，+代表入库，-代表出库
        record = "-" + record;


         materialShipment.setStorageArea(storageArea);
         materialShipment.setMo(mo);
         materialShipment.setPurchaseNumber(purchaseNumber);
         String erp = materialShipment.getErp();

        // 出库时，向出库表中添加出库数据
        int result1 = materialShipmentService.insertShipment(materialShipment);


        // 出库的同时，向入库表中更改数量
        Integer result2 = materialStorageService.updateStorage(num, erp, purchaseNumber);

        // 出库的同时，向记录表中添加记录
        Integer result3 = materialStorageService.insertRecord(erp, record,operator);

        if (result1 == 1 && result2 == 1 && result3 == 1) {
            return JsonMsg.success();
        } else {
            return JsonMsg.fail();
        }
    }

    // 选择最早入库储位上最早入库的外箱
    @RequestMapping("/firstwbox")
    @ResponseBody
    public JsonMsg firstwbox(String stock,String wbox){

        String firstwbox = materialStorageService.firstwbox(stock);
        Integer num = materialStorageService.selectnum(firstwbox);

        if(firstwbox.equals(wbox)){

            return JsonMsg.success().addInfo("num",num);
        }else{

            return JsonMsg.fail().addInfo("wbox",firstwbox);

        }
    }
}
