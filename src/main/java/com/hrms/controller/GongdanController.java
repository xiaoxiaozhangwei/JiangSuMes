package com.hrms.controller;


import com.hrms.bean.Gongdan;
import com.hrms.service.GongdanService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/gd")
public class GongdanController {


    @Autowired
    GongdanService gongdanService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(){
        return "gongdanPage";
    }



    @RequestMapping(value = "/addMo", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg addMo(Gongdan gongdan, HttpServletRequest request){
        String name= (String) request.getSession().getAttribute("name");
        gongdan.setOperator(name);
        int res = gongdanService.addMo(gongdan);
        if (res == 1){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }



    @RequestMapping(value = "/checkMoExists", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg checkEmpExists(@RequestParam("empName") String moId){
        String moid = gongdanService.checkMo(moId);
        if (moid != null){
            return JsonMsg.fail().addInfo("name_reg_error", "工单号重复");
        }else {
            return JsonMsg.success();
        }
    }

    @RequestMapping(value = "/getMoAll", method = RequestMethod.GET)
    public ModelAndView getMoAll(){
        ModelAndView mv = new ModelAndView("gongdanPage");
        List<Gongdan> gongdans = gongdanService.selectMoAll();
        //将上述查询结果放到Model中，在JSP页面中可以进行展示
        mv.addObject("getAllMo", gongdans);

        return mv;
    }



    @RequestMapping(value="/deletemo/{mo}",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg  deletemo(@PathVariable("mo") String moid)
    {
        int res = 0;
        res =  gongdanService.deleteMo(moid);
        if (res != 1){
            return JsonMsg.fail().addInfo("emp_del_error", "工单删除异常");
        }else {return JsonMsg.success();}

    }



    @RequestMapping(value ="/updateMo/{moId}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updateMo(Gongdan gongdan, @PathVariable("moId") String moId){
        int res = gongdanService.updateMo(moId, gongdan);
        if (res == 1) {
            return JsonMsg.success();
        }else {
            return JsonMsg.fail().addInfo("emp_update_error", "更改异常");
        }

    }



    @RequestMapping(value = "/selectMO/{updateEmpId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectMO(@PathVariable("updateEmpId") String moId){
        Gongdan gongdan = gongdanService.selectmo(moId);
        if (gongdan != null){
            return JsonMsg.success().addInfo("gongdan", gongdan);
        }else {
            return JsonMsg.fail();
        }

    }

    @RequestMapping(value = "/selected",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selected(){
        List<Gongdan> gd  = gongdanService.selectmoid();
        if (gd!=null){
            return JsonMsg.success().addInfo("moidlist",gd);
        }else{
            return JsonMsg.fail();
        }
    }

    /**
     * 根据工单号查询工单信息
     * @param
     * @return
     */
    @RequestMapping(value = "/getMoId/{moid}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getMoById(@PathVariable("moid") String moId) {
        Gongdan gongdan = gongdanService.getMoById(moId);
        if (gongdan != null) {
            return JsonMsg.success().addInfo("gongdan", gongdan);
        } else {
            return JsonMsg.fail();
        }
    }



    @RequestMapping(value = "/getMoBypage", method = RequestMethod.GET)
    public ModelAndView getMoBypage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo){
        ModelAndView mv = new ModelAndView("gongdanPage");
        int limit = 10;
        // 记录的偏移量(即从第offset行记录开始查询)，
        // 如第1页是从第1行(offset=(21-1)*5=0,offset+1=0+1=1)开始查询；
        // 第2页从第6行(offset=(2-1)*5=5,offset+1=5+1=6)记录开始查询
        int offset = (pageNo-1)*limit;
        //获取指定页数包含的员工信息
        List<Gongdan> gongdans = gongdanService.selectByLimitAndOffset(offset, limit);
        //获取总的记录数
        int totalItems = gongdanService.selectMoNum();
        //获取总的页数
        int temp = totalItems / limit;
        int totalPages = (totalItems % limit == 0) ? temp : temp+1;
        //当前页数
        int curPage = pageNo;

        //将上述查询结果放到Model中，在JSP页面中可以进行展示
        mv.addObject("gongdans", gongdans)
                .addObject("totalItems", totalItems)
                .addObject("totalPages", totalPages)
                .addObject("curPage", curPage);
        return mv;
    }
}
