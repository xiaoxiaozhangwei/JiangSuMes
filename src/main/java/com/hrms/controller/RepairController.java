package com.hrms.controller;


import com.hrms.bean.Repair;
import com.hrms.mapper.RepairMapper;
import com.hrms.service.RepairService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/repair")
public class RepairController {
    @Autowired
    RepairService repairService;

    //跳转到维修界面
    @RequestMapping(value = "/repair", method = RequestMethod.GET)
    public String repair() {
        return "repair";
    }

    //根据SSD_SN回显PABASN及FW
    @RequestMapping(value = "/selectbyssd",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectbyssd(String ssdsn){
        //System.out.println(ssdsn);
        String pcbasn=repairService.selectpcbabyssd(ssdsn);
        //System.out.println(pcbasn);
        String fw=repairService.selectfwbyssd(ssdsn);
        if (1==1){
            //System.out.println("fw="+fw);
            return JsonMsg.success().addInfo("pcbasn", pcbasn)
                    .addInfo("fw", fw);
        }else {
            return JsonMsg.fail();
        }
    }

    @Autowired
    RepairMapper repairMapper;
    @RequestMapping(value = "/saverepairmesg",method = RequestMethod.POST)
    @ResponseBody
    public void saverepairmesg(Repair repair){

        System.out.println(repair);
        repairService.saverepairmesg(repair);
    }

    @RequestMapping(value = "/showrepairmesg",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg showrepairmesg(String ssdsn,String pcbasn){
        List<Repair> repairs=repairService.showrepairmesg(ssdsn, pcbasn);
        if (repairs.size()>0){
            return JsonMsg.success().addInfo("repairs", repairs);
        }else {
            return JsonMsg.fail();
        }
    }

    @RequestMapping(value = "/updaterepairmesg",method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updaterepairmesg(Repair repair){
        System.out.println(repair);
        int res=repairService.updaterepairmesg(repair);
        System.out.println(res);
        if (res>0){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }

    @RequestMapping(value = "/inbound",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg inbound(String ssdsn,String pcbasn,String errorarea){
        String fw=null;
        String errorcode=null;
        String errordate = null;
        switch (errorarea){
            case "OC1":{
                List<Repair> list=repairService.selectOC1(ssdsn, pcbasn);
                if(list.size()>0){
                    errorcode=list.get(0).getErrorcode();
                    fw=list.get(0).getFw();
                    errordate=list.get(0).getError_date();
                }
                break;
            }
            case "OC2":{
                List<Repair> list=repairService.selectOC2(ssdsn, pcbasn);
                if(list.size()>0){
                    errorcode=list.get(0).getErrorcode();
                    fw=list.get(0).getFw();
                    errordate=list.get(0).getError_date();
                }
                break;
            }
            case "OC4":{
                List<Repair> list=repairService.selectOC4(ssdsn, pcbasn);
                if(list.size()>0){
                    errorcode=list.get(0).getErrorcode();
                    fw=list.get(0).getFw();
                    errordate=list.get(0).getError_date();
                }

                break;
            }
            case "CDI":{
                List<Repair> list=repairService.selectCDI(ssdsn, pcbasn);
                if(list.size()>0){
                    errorcode=list.get(0).getErrorcode();
                    fw=repairService.selectfwbyssd(ssdsn);
                    errordate=list.get(0).getError_date();
                }

                break;
            }
            case "目检":{
                List<Repair> list=repairService.selectcheck(ssdsn);
                if(list.size()>0){
                    errorcode=list.get(0).getErrorcode();
                    fw=list.get(0).getFw();
                    errordate=list.get(0).getError_date();
                }
                break;
            }
        }
        System.out.println(fw+";"+errorcode+";"+errordate);
        if (fw==null||errorcode==null||errordate==null){
            errorcode="无不良数据";
        }
        Repair repair=new Repair(ssdsn,pcbasn,errordate,errorarea,errorcode,"","","","",fw,"","","","","","","");
        System.out.println(repair);
        int res=repairService.saverepairmesg(repair);
        System.out.println(res);
        return JsonMsg.success();

    }

    @RequestMapping(value = "/outbound",method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg outbound(String ssdsn,String pcbasn,String outbound_receiver){
        System.out.println(ssdsn);
        int res=repairService.outbound(ssdsn, pcbasn, outbound_receiver);
        System.out.println(res);
        if (res>0){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }
}
