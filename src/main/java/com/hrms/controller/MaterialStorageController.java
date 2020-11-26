package com.hrms.controller;

import com.hrms.bean.MaterialStorage;
import com.hrms.service.MaterialStorageService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/materialstorage")
public class MaterialStorageController {

    @Autowired
    private MaterialStorageService materialStorageService;

    @RequestMapping("/storage")
    public String lailiao(){
        return "materialstorage";
    }

    // 将页面内容存入数据库
    @RequestMapping("/save")
    @ResponseBody
    public JsonMsg save(MaterialStorage materialStorage, HttpServletRequest request)  {
        String operator = (String) request.getSession().getAttribute("name");
        materialStorage.setOperator(operator);
        Integer saveResult = materialStorageService.save(materialStorage);
        if( 1 == saveResult ){
            String erp = materialStorage.getErp();
            String record = materialStorage.getNum().toString(); // 放入记录表数据库后显示+ —号，+代表入库，-代表出库
            record = "+" + record;
            Integer insertResult = materialStorageService.insertRecord(erp, record,operator);
            if(1 == insertResult ){
                return JsonMsg.success();
            }else {
                return JsonMsg.fail();
            }
        }else{
            return JsonMsg.fail();
        }
    }

    // 检查储位不允许放置不同的erp料号
    @RequestMapping("/check")
    @ResponseBody
    public JsonMsg check(String erp, String stock){
        String result = materialStorageService.check(stock);
        if (erp.equals(result ) || null ==result){
            return JsonMsg.success();
        }else{
            return JsonMsg.fail();
        }
    }
}
