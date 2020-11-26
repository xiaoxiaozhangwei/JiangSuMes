package com.hrms.controller;

import com.hrms.bean.Device;
import com.hrms.bean.Gongdan;
import com.hrms.bean.Zuzhuang;
import com.hrms.service.DeviceService;
import com.hrms.service.GongdanService;
import com.hrms.service.ZuzhuangService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/link")
public class ZuzhuangController {
    @Autowired
    ZuzhuangService zuzhuangService;
    @Autowired
    GongdanService gongdanService;
    @Autowired
    DeviceService deviceService;


    /**
     添加数据
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public void add(Zuzhuang zuzhuang){

        zuzhuangService.addlink(zuzhuang);
        //System.out.println(zuzhuang);
        //zuzhuangService.updatedevice(zuzhuang);
    }
    @RequestMapping(value = "/merman",method = RequestMethod.POST)
    @ResponseBody
    public void merman(String moId){

        zuzhuangService.updatemo(moId);
    }


    /**
     * 根据工单号查询工单信息
     * @param
     * @return
     */
    @RequestMapping(value = "/getMoId/{moid}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getMoById(@PathVariable("moid") String moId, HttpServletRequest request) {
        Gongdan gongdan = gongdanService.getMoById(moId);
        Integer moNumZuZhuang = gongdan.getMoNum();
        ServletContext application = request.getServletContext();
        application.setAttribute("moNumZuZhuang",moNumZuZhuang);
        if (gongdan != null) {
            return JsonMsg.success().addInfo("gongdan", gongdan);
        } else {
            return JsonMsg.fail();
        }
    }

    /**
     * 根据设备号查询设备信息
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/getDeviceId/{deviceid}",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getDeviceById(@PathVariable("deviceid") Integer deviceId){
        Device device=deviceService.getDeviceById(deviceId);
        if (device!=null){
            return JsonMsg.success().addInfo("device",device);
        }else {
            return JsonMsg.fail();
        }
    }

    /**
     * 查询输入的产品SN号是否重复
     * @param
     * @return
     */
    @RequestMapping(value = "/checkProExists", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg checkProExists(String productionSN, String mo){
        System.out.println(productionSN+mo);
        Zuzhuang zuzhuang = zuzhuangService.getProByName(productionSN,mo);
        if (zuzhuang != null){
            return JsonMsg.fail();
        }else {
            return JsonMsg.success();
        }
    }
    /**
     * 查询输入的PCBA SN号是否重复
     * @param
     * @return
     */
    @RequestMapping(value = "/checkPcbaExists", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg checkPcbaExists(String pcbaSN,String moid){

        Zuzhuang zuzhuang = zuzhuangService.getPcba(pcbaSN,moid);
        if (zuzhuang != null){
            return JsonMsg.fail();
        }else {
            return JsonMsg.success();
        }
    }

    /**
     * 跳转到组装信息页面
     * @return
     */
    @RequestMapping(value = "/zuzhuangPage", method = RequestMethod.GET)
    public String zuzhuangPage(){
        return "ZuzhuangPage";
    }





    @RequestMapping(value = "/lianxiangbarcode", method = RequestMethod.GET)
    public String lianxiangbarcode(){
        return "lianxiangbarcode";
    }

    /**
     * 复合查询
     */
    @RequestMapping(value = "/selecting",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selecting(Zuzhuang zuzhuang){
        //ModelAndView mv = new ModelAndView("ZuzhuangPage");
        List<Zuzhuang> zs=zuzhuangService.selectlink(zuzhuang);
        if (zs!=null){
            return JsonMsg.success().addInfo("zs",zs);
        }else {
            return JsonMsg.fail();
        }

    }

    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView selectAll(Zuzhuang zuzhuang){
        ModelAndView mv = new ModelAndView("ZuzhuangPage");
        List<Zuzhuang> z=zuzhuangService.selectlink(zuzhuang);
        mv.addObject("zuzhuang",z);
        return mv;
    }


}
