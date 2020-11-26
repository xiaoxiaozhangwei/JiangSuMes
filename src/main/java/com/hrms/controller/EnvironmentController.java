package com.hrms.controller;

import com.hrms.bean.Environment;
import com.hrms.service.EnvironmentService;
import com.hrms.util.JsonMsg;
import com.hrms.util.Week;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.List;

@Controller
@RequestMapping(value = "/environ")
public class EnvironmentController {
    @Autowired
    EnvironmentService environmentService;

    /**
     * 跳转到评比图片页面
     * @return
     */
    @RequestMapping(value = "/environpicture", method = RequestMethod.GET)
    public String environ(){
        return "environpicture";
    }

    /**
     * 插入各区域评比分数到数据库
     * @param RDT
     * @param OC3
     * @param BIT
     * @param link
     * @param store
     * @param IQC
     * @param extract
     * @param changeshoe
     * @param walk
     * @param office
     * @param RMA
     * @param rest
     * @param tea
     * @param cause
     * @return
     */
    @RequestMapping(value = "/insert")
    @ResponseBody
    public JsonMsg insert(Double RDT,Double OC3,Double BIT,Double link,Double store,Double IQC,Double extract,Double changeshoe,Double walk,Double office,Double RMA,Double rest,Double tea,String cause){

        Environment environment= new Environment(1,"date", RDT, OC3, BIT, link, store, IQC, extract, changeshoe, walk, office, RMA, rest, tea, cause,1,"1","1","1","1");
        System.out.println(environment);
        int c=environmentService.SelectTodayExists();
        if (c==0){
            int res =environmentService.insert(environment);
            if (res==1){
                return JsonMsg.success().addInfo("s","保存数据成功！");
            }else {
                return JsonMsg.fail();
            }
        }else {
            int u=environmentService.UpdateTodayExists(environment);
            if (u==1){
                return JsonMsg.success().addInfo("s","修改数据成功！");
            }else {
                return JsonMsg.fail();
            }

        }

    }

    /**
     * 查询本周分数
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/selectthisweek",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView selectthisweek() throws Throwable {
        ModelAndView mv = new ModelAndView("environment");
        DecimalFormat df = new DecimalFormat( "0.00"); //设置double类型小数点后位数格式
        List<Environment> list=environmentService.selectthisweek();
        //System.out.println(list.size());


        double RDT = 0;
        double OC3 =0;
        double BIT =0;
        double link =0;
        double store =0;
        double IQC =0;
        double extract =0;
        double changeshoe =0;
        double walk =0;
        double office =0;
        double RMA =0;
        double rest =0;
        double tea =0;


        for (int i = 0; i < list.size(); i++) {
            String w= Week.dayForWeek(list.get(i).getDate());
            RDT+=list.get(i).getRDT();
            OC3 +=list.get(i).getOC3();
            BIT +=list.get(i).getBIT();
            link +=list.get(i).getLink();
            store +=list.get(i).getStore();
            IQC +=list.get(i).getIQC();
            extract +=list.get(i).getExtract();
            changeshoe +=list.get(i).getChangeshoe();
            walk +=list.get(i).getWalk();
            office +=list.get(i).getOffice();
            RMA +=list.get(i).getRMA();
            rest +=list.get(i).getRest();
            tea +=list.get(i).getTea();
            mv.addObject("RDT"+w,list.get(i).getRDT())
                    .addObject("OC3"+w,list.get(i).getOC3())
                    .addObject("BIT"+w,list.get(i).getBIT())
                    .addObject("link"+w,list.get(i).getLink())
                    .addObject("store"+w,list.get(i).getStore())
                    .addObject("IQC"+w,list.get(i).getIQC())
                    .addObject("extract"+w,list.get(i).getExtract())
                    .addObject("changeshoe"+w,list.get(i).getChangeshoe())
                    .addObject("walk"+w,list.get(i).getWalk())
                    .addObject("office"+w,list.get(i).getOffice())
                    .addObject("RMA"+w,list.get(i).getRMA())
                    .addObject("rest"+w,list.get(i).getRest())
                    .addObject("tea"+w,list.get(i).getTea())
                    .addObject("cause"+w,list.get(i).getCause());
        }

        mv.addObject("RDT",df.format(RDT/list.size()))
                .addObject("OC3",df.format(OC3/list.size()))
                .addObject("BIT",df.format(BIT/list.size()))
                .addObject("link",df.format(link/list.size()))
                .addObject("store",df.format(store/list.size()))
                .addObject("IQC",df.format(IQC/list.size()))
                .addObject("extract",df.format(extract/list.size()))
                .addObject("changeshoe",df.format(changeshoe/list.size()))
                .addObject("walk",df.format(walk/list.size()))
                .addObject("office",df.format(office/list.size()))
                .addObject("RMA",df.format(RMA/list.size()))
                .addObject("rest",df.format(rest/list.size()))
                .addObject("tea",df.format(tea/list.size()));

        return mv;
    }

    /**
     * 插入图片
     * @param area
     * @param picture
     * @param question
     * @return
     */
    @RequestMapping(value = "/insertpicture",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insertpicture(String area,String picture,String question){
        int res= environmentService.insertquestion(area, picture, question);
        if (res==1){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }

    @RequestMapping(value = "/selectallpict",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView selectallpict(){
        ModelAndView mv=new ModelAndView("environpicture");
        List<Environment> pictures=environmentService.selectallpict();
        mv.addObject("pict",pictures);
        return mv;
    }

    @RequestMapping(value = "/AvgByWeek",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView AvgByWeek(){
        ModelAndView mv=new ModelAndView("environselect");
        List<Environment> list=environmentService.AvgByWeek();
        List<Environment> list1=environmentService.AvgthisMonth();
        for (int i = 1; i <= list.size(); i++) {
            mv.addObject("RDT"+i,list.get(i-1).getRDT())
                    .addObject("OC3"+i,list.get(i-1).getOC3())
                    .addObject("BIT"+i,list.get(i-1).getBIT())
                    .addObject("link"+i,list.get(i-1).getLink())
                    .addObject("store"+i,list.get(i-1).getStore())
                    .addObject("IQC"+i,list.get(i-1).getIQC())
                    .addObject("extract"+i,list.get(i-1).getExtract())
                    .addObject("changeshoe"+i,list.get(i-1).getChangeshoe())
                    .addObject("walk"+i,list.get(i-1).getWalk())
                    .addObject("office"+i,list.get(i-1).getOffice())
                    .addObject("RMA"+i,list.get(i-1).getRMA())
                    .addObject("rest"+i,list.get(i-1).getRest())
                    .addObject("tea"+i,list.get(i-1).getTea())
                    .addObject("cause"+i,list.get(i-1).getCause());
        }
        mv.addObject("RDT",list1.get(0).getRDT())
                .addObject("OC3",list1.get(0).getOC3())
                .addObject("BIT",list1.get(0).getBIT())
                .addObject("link",list1.get(0).getLink())
                .addObject("store",list1.get(0).getStore())
                .addObject("IQC",list1.get(0).getIQC())
                .addObject("extract",list1.get(0).getExtract())
                .addObject("changeshoe",list1.get(0).getChangeshoe())
                .addObject("walk",list1.get(0).getWalk())
                .addObject("office",list1.get(0).getOffice())
                .addObject("RMA",list1.get(0).getRMA())
                .addObject("rest",list1.get(0).getRest())
                .addObject("tea",list1.get(0).getTea())
                .addObject("cause",list1.get(0).getCause());
        return mv;
    }

}
