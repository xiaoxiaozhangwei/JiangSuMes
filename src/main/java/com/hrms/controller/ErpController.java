package com.hrms.controller;


import com.hrms.bean.Erp;
import com.hrms.service.ErpService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("Erp")
public class ErpController {
    @Autowired
    ErpService erpService;



    @RequestMapping("erpPage")
    public String  erpPage()
    {
        return "ErpPage";
    }

    //@RequiresPermissions("789")
    @RequestMapping(value = "addErp")
    @ResponseBody
    public JsonMsg addErp(Erp erp) throws AddressException {

        int  res= erpService.insertErp(erp);
        if (res==1)
        {    sendErpconfirm(erp.getErp());
            return JsonMsg.success();
        }
        else
        {
               return JsonMsg.fail();
        }
    }



    public void sendErpconfirm(String erp) throws AddressException {

        // 发件人电子邮箱
        String from = "XITC-MES@xitcorp.com";
        // 指定发送邮件的主机为 localhost
        String host = "192.168.200.74";
        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties);
       // List<Email> emails =emailService.selectEmail("芯盛邮箱");
      //  System.out.println(emails);
        InternetAddress to =new InternetAddress("zhujun@xitcorp.com");

        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));
            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, to);
            // Set Subject: 头部头字段
            message.setSubject("ERP信息审核!");
            setMultipart(message,erp);
            Transport.send(message);
        }catch (MessagingException | IOException mex) {
            mex.printStackTrace();
        }


    }


    public void setMultipart(MimeMessage msg,String erp) throws MessagingException, IOException {
        // 一个Multipart对象包含一个或多个BodyPart对象，来组成邮件的正文部分（包括附件）。
        MimeMultipart multiPart = new MimeMultipart();
        // 添加正文
        MimeBodyPart partText = new MimeBodyPart();
        partText.setContent("<font size =\"5\" >有ERP信息需要您审核，请及时审核!  <a href = \"http://localhost:8030/Erp/getAllErp\">ERP的信息为："+erp+"</a></font> <br><font size =\"5\" >此为系统自动发送，请勿回复，如有问题请联系自动化小组<br><br>联系方式：沈&nbsp;&nbsp;然 shenran@xitcorp.com<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;顾艳波 guyanbo@xitcorp.com</font>", "text/html;charset=utf-8");

        multiPart.addBodyPart(partText);
        msg.setContent(multiPart);
    }




    @RequestMapping(value = "selectErp")
    @ResponseBody
    public JsonMsg addErp(String erp)
    {
        List<Erp>  erps= erpService.selectErp(erp);
        if (erps!=null&&erps.size()!=0)
        {
            return JsonMsg.success();
        }
        else
        {
            return JsonMsg.fail();
        }
    }


    @RequestMapping(value = "selectErpByName")
    @ResponseBody
    public JsonMsg selectErpByName(String erp)
    {
        Erp  selecterp= erpService.selectErpByName(erp);
        if (selecterp!=null)
        {
            return JsonMsg.success().addInfo("Erp",selecterp);
        }
        else
        {
            return JsonMsg.fail();
        }
    }

    @RequestMapping(value = "getAllErp")
    public ModelAndView getMoAll(){
        ModelAndView mv = new ModelAndView("ErpPage");
        List<Erp> erps = erpService.selectErp("");
        //将上述查询结果放到Model中，在JSP页面中可以进行展示
        mv.addObject("erps", erps);

        return mv;
    }



    @RequestMapping(value = "/deleteErp/{erp}" ,method = RequestMethod.DELETE)
    @ResponseBody

    public JsonMsg deleteErp(@PathVariable String erp)
    {   int  res= erpService.deleteErp(erp);

        if (res==1)
        {
            return JsonMsg.success();
        }
        else
        {
            return JsonMsg.fail().addInfo("deleteErp","删除异常");
        }
    }

    @RequestMapping(value = "updateErp" ,method =RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updateErp(Erp erp)
    {
        int res= erpService.updateErp(erp);
        System.out.println(res);
        if (res ==1)
        {
            return JsonMsg.success();
        }
        else
        {
            return JsonMsg.fail();
        }
    }


    /**
     * 分页查询：返回指定页数对应的数据
     * @param pageNo
     * @return
     */

    @RequestMapping(value = "/getErpList", method = RequestMethod.GET)
    public ModelAndView getDeptList(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        ModelAndView mv = new ModelAndView("ErpPage");

        //每页显示的记录行数
        int limit = 5;
        //总记录数
        // int totalItems = erpService.selectErpNum();
        // int temp = totalItems / limit;

        //每页的起始行(offset+1)数据，如第一页(offset=0，从第1(offset+1)行数据开始)
        int offset = (pageNo - 1) * limit;
        List<Erp> erps = erpService.selectByLimitAndOffset(offset, limit);
        int totalItems = erpService.selectErpNum();
        int temp = totalItems / limit;
        int totalPages = (totalItems % limit == 0) ? temp : temp + 1;
        int curPage = pageNo;

        mv.addObject("erps", erps)
                .addObject("totalItems", totalItems)
                .addObject("totalPages", totalPages)
                .addObject("curPage", pageNo);
        return mv;
    }

    @RequestMapping(value = "selectErpsByPass" ,method =RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectErpsByPass()
    {
        List<Erp> erps=  erpService.selectErpByPass("");
        return JsonMsg.success().addInfo("erps",erps);


    }


    @RequestMapping(value = "selectErpByPass" ,method =RequestMethod.GET)
    @ResponseBody
    public JsonMsg selectErpByPass(String erp)
    {
        List<Erp> Erp=  erpService.selectErpByPass(erp);
        return JsonMsg.success().addInfo("erp",Erp.get(0));

    }
}
