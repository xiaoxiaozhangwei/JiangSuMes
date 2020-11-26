package com.hrms.controller;

import com.hrms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@Controller
@RequestMapping("/DisplayBoard")
public class DisplayBoardController {

    @Autowired
    CheckService checkService;
    @Autowired
    ZuzhuangService zuzhuangService;
    @Autowired
    BaozhuangService baozhuangService;
    @Autowired
    CDIService cdiService;
    @Autowired
    OC4Service oc4Service;
    @Autowired
    OC1Service oc1Service;
    @Autowired
    OC2Service oc2Service;
    @Autowired
    QCService qcService;
    @Autowired
    BitService bitService;


    // 组装线电子看板
    @RequestMapping("/ZuZhuang")
    public ModelAndView ZuZhuangdisplay() {
        ModelAndView mv1 = new ModelAndView("ZuZhuangDisplayBoard");
        DecimalFormat df = new DecimalFormat("0.0"); //设置double类型小数点后位数格式

        // S/N投入
        int LinkLine1 = zuzhuangService.selectLinkbyline("line 1");
        int LinkLine2 = zuzhuangService.selectLinkbyline("line 2");
        int LinkLine3 = zuzhuangService.selectLinkbyline("line 3");
        int LinkLine4 = zuzhuangService.selectLinkbyline("line 4");
        int LinkLine5 = zuzhuangService.selectLinkbyline("line 5");
        int LinkLine6 = zuzhuangService.selectLinkbyline("line 6");
        mv1.addObject("LinkLine1", LinkLine1)
                .addObject("LinkLine2", LinkLine2)
                .addObject("LinkLine3", LinkLine3)
                .addObject("LinkLine4", LinkLine4)
                .addObject("LinkLine5", LinkLine5)
                .addObject("LinkLine6", LinkLine6)
                .addObject("LinkCount", LinkLine1 + LinkLine2 + LinkLine3 + LinkLine4 + LinkLine5 + LinkLine6);

        //目检站
        int checkPassLine1 = checkService.selectCheckRelate("无", "线别1");
        int checkNGLine1 = checkService.selectCheckRelate("不良", "线别1");
        int checkPassLine2 = checkService.selectCheckRelate("无", "线别2");
        int checkNGLine2 = checkService.selectCheckRelate("不良", "线别2");
        int checkPassLine3 = checkService.selectCheckRelate("无", "线别3");
        int checkNGLine3 = checkService.selectCheckRelate("不良", "线别3");
        int checkPassLine4 = checkService.selectCheckRelate("无", "线别4");
        int checkNGLine4 = checkService.selectCheckRelate("不良", "线别4");
        int checkPassLine5 = checkService.selectCheckRelate("无", "线别5");
        int checkNGLine5 = checkService.selectCheckRelate("不良", "线别5");
        int checkPassLine6 = checkService.selectCheckRelate("无", "线别6");
        int checkNGLine6 = checkService.selectCheckRelate("不良", "线别6");

        // 目检站计算良率
        /*float rightpercent1;
        if ((checkPassLine1 + checkNGLine1) == 0) {
            rightpercent1 = 0;
        } else {
            rightpercent1 = ((float) checkPassLine1 / ((float) checkPassLine1 + (float) checkNGLine1)) * 100;
        }

        float rightpercent2;
        if ((checkPassLine2 + checkNGLine2) == 0) {
            rightpercent2 = 0;
        } else {
            rightpercent2 = ((float) checkPassLine2 / ((float) checkPassLine2 + (float) checkNGLine2)) * 100;
        }

        float rightpercent3;
        if ((checkPassLine3 + checkNGLine3) == 0) {
            rightpercent3 = 0;
        } else {
            rightpercent3 = ((float) checkPassLine3 / ((float) checkPassLine3 + (float) checkNGLine3)) * 100;
        }

        float rightpercent4;
        if ((checkPassLine4 + checkNGLine4) == 0) {
            rightpercent4 = 0;
        } else {
            rightpercent4 = ((float) checkPassLine4 / ((float) checkPassLine4 + (float) checkNGLine4)) * 100;
        }

        float rightpercent5;
        if ((checkPassLine5 + checkNGLine5) == 0) {
            rightpercent5 = 0;
        } else {
            rightpercent5 = ((float) checkPassLine5 / ((float) checkPassLine5 + (float) checkNGLine5)) * 100;
        }*/

        // 小计百分比
        float checkpercent;
        if (checkPassLine1 + checkNGLine1 + checkPassLine2 + checkNGLine2 + checkPassLine3 + checkNGLine3 + checkPassLine4 + checkNGLine4 + checkPassLine5 + checkNGLine5
                + checkPassLine6 + checkNGLine6== 0) {
            checkpercent = 0;
        } else {
            checkpercent = ((float) (checkPassLine1 + checkPassLine2 + checkPassLine3 + checkPassLine4 + checkPassLine5 + checkPassLine6) /
                    (float) (checkPassLine1 + checkPassLine2 + checkPassLine3 + checkPassLine4 + checkPassLine5+ checkPassLine6 + checkNGLine1 + checkNGLine2
                    + checkNGLine3 + checkNGLine4 + checkNGLine5 + checkNGLine6)) * 100;
        }

        // 目检数据提交到前台
        mv1
                .addObject("checkPassLine1", checkPassLine1)
                .addObject("checkNGLine1", checkNGLine1)
                .addObject("checkLine1Total", checkPassLine1 + checkNGLine1)
                .addObject("checkrightpercent1", df.format(calculate(checkPassLine1, checkNGLine1)))

                .addObject("checkPassLine2", checkPassLine2)
                .addObject("checkNGLine2", checkNGLine2)
                .addObject("checkLine2Total", checkPassLine2 + checkNGLine2)
                .addObject("checkrightpercent2", df.format(calculate(checkPassLine2, checkNGLine2)))

                .addObject("checkPassLine3", checkPassLine3)
                .addObject("checkNGLine3", checkNGLine3)
                .addObject("checkLine3Total", checkPassLine3 + checkNGLine3)
                .addObject("checkrightpercent3", df.format(calculate(checkPassLine3, checkNGLine3)))

                .addObject("checkPassLine4", checkPassLine4)
                .addObject("checkNGLine4", checkNGLine4)
                .addObject("checkLine4Total", checkPassLine4 + checkNGLine4)
                .addObject("checkrightpercent4", df.format(calculate(checkPassLine4, checkNGLine4)))

                .addObject("checkPassLine5", checkPassLine5)
                .addObject("checkNGLine5", checkNGLine5)
                .addObject("checkLine5Total", checkPassLine5 + checkNGLine5)
                .addObject("checkrightpercent5", df.format(calculate(checkPassLine5, checkNGLine5)))

                .addObject("checkPassLine6", checkPassLine6)
                .addObject("checkNGLine6", checkNGLine6)
                .addObject("checkLine6Total", checkPassLine6 + checkNGLine6)
                .addObject("checkrightpercent6", df.format(calculate(checkPassLine6, checkNGLine6)))

                .addObject("checkPassTotal", checkPassLine1 + checkPassLine2 + checkPassLine3 + checkPassLine4 + checkPassLine5 + checkPassLine6)
                .addObject("checkNGTotal", checkNGLine1 + checkNGLine2 + checkNGLine3 + checkNGLine4 + checkNGLine5 + checkNGLine6)
                .addObject("checkLineTotal", checkPassLine1 + checkPassLine2 + checkPassLine3 + checkPassLine4 + checkPassLine5 + checkPassLine6
                        + checkNGLine1 + checkNGLine2 + checkNGLine3 + checkNGLine4 + checkNGLine5 + checkNGLine6)
                .addObject("checkpercent", df.format(checkpercent));


        //包装站
        int SnCountLine1 = baozhuangService.selectSnCountByLine("Line 1");
        int SnCountLine2 = baozhuangService.selectSnCountByLine("Line 2");
        int SnCountLine3 = baozhuangService.selectSnCountByLine("Line 3");
        int SnCountLine4 = baozhuangService.selectSnCountByLine("Line 4");
        int SnCountLine5 = baozhuangService.selectSnCountByLine("Line 5");
        int SnCountLine6 = baozhuangService.selectSnCountByLine("Line 6");

        int NboxCountLine1 = baozhuangService.selectNboxCountByLine("line 1");
        int NboxCountLine2 = baozhuangService.selectNboxCountByLine("line 2");
        int NboxCountLine3 = baozhuangService.selectNboxCountByLine("line 3");
        int NboxCountLine4 = baozhuangService.selectNboxCountByLine("line 4");
        int NboxCountLine5 = baozhuangService.selectNboxCountByLine("line 5");
        int NboxCountLine6 = baozhuangService.selectNboxCountByLine("line 6");

        int WboxCountLine1 = baozhuangService.selectWboxCountByLine("line 1");
        int WboxCountLine2 = baozhuangService.selectWboxCountByLine("line 2");
        int WboxCountLine3 = baozhuangService.selectWboxCountByLine("line 3");
        int WboxCountLine4 = baozhuangService.selectWboxCountByLine("line 4");
        int WboxCountLine5 = baozhuangService.selectWboxCountByLine("line 5");
        int WboxCountLine6 = baozhuangService.selectWboxCountByLine("line 6");


        mv1.addObject("SnCountLine1", SnCountLine1)
                .addObject("NboxCountLine1", NboxCountLine1)
                .addObject("WboxCountLine1", WboxCountLine1)

                .addObject("SnCountLine2", SnCountLine2)
                .addObject("NboxCountLine2", NboxCountLine2)
                .addObject("WboxCountLine2", WboxCountLine2)

                .addObject("SnCountLine3", SnCountLine3)
                .addObject("NboxCountLine3", NboxCountLine3)
                .addObject("WboxCountLine3", WboxCountLine3)

                .addObject("SnCountLine4", SnCountLine4)
                .addObject("NboxCountLine4", NboxCountLine4)
                .addObject("WboxCountLine4", WboxCountLine4)

                .addObject("SnCountLine5", SnCountLine5)
                .addObject("NboxCountLine5", NboxCountLine5)
                .addObject("WboxCountLine5", WboxCountLine5)

                .addObject("SnCountLine6", SnCountLine6)
                .addObject("NboxCountLine6", NboxCountLine6)
                .addObject("WboxCountLine6", WboxCountLine6)

                .addObject("BaoZhuangSNCount", SnCountLine1 + SnCountLine2 + SnCountLine3 + SnCountLine4 + SnCountLine5 + SnCountLine6)
                .addObject("BaoZhuangNboxCount", NboxCountLine1 + NboxCountLine2 + NboxCountLine3 + NboxCountLine4 + NboxCountLine5 + NboxCountLine6)
                .addObject("BaoZhuangWboxCount", WboxCountLine1 + WboxCountLine2 + WboxCountLine3 + WboxCountLine4 + WboxCountLine5 + WboxCountLine6);


        //CDI
        int cdiPassLine1 = cdiService.selectCDIByDisplayBoard("PASS", "1");
        int cdiNGLine1 = cdiService.selectCDIByDisplayBoard("NG", "1");
        int cdiPassLine2 = cdiService.selectCDIByDisplayBoard("PASS", "2");
        int cdiNGLine2 = cdiService.selectCDIByDisplayBoard("NG", "2");
        int cdiPassLine3 = cdiService.selectCDIByDisplayBoard("PASS", "3");
        int cdiNGLine3 = cdiService.selectCDIByDisplayBoard("NG", "3");
        int cdiPassLine4 = cdiService.selectCDIByDisplayBoard("PASS", "4");
        int cdiNGLine4 = cdiService.selectCDIByDisplayBoard("NG", "4");
        int cdiPassLine5 = cdiService.selectCDIByDisplayBoard("PASS", "5");
        int cdiNGLine5 = cdiService.selectCDIByDisplayBoard("NG", "5");
        int cdiPassLine6 = cdiService.selectCDIByDisplayBoard("PASS", "6");
        int cdiNGLine6 = cdiService.selectCDIByDisplayBoard("NG", "6");

        mv1.addObject("cdiPassLine1", cdiPassLine1)
                .addObject("cdiNGLine1", cdiNGLine1)
                .addObject("CDIxiaoji1", cdiPassLine1 + cdiNGLine1)
                .addObject("CDIpercentline1", accuracy(cdiPassLine1, cdiPassLine1 + cdiNGLine1))

                .addObject("cdiPassLine2", cdiPassLine2)
                .addObject("cdiNGLine2", cdiNGLine2)
                .addObject("CDIxiaoji2", cdiPassLine2 + cdiNGLine2)
                .addObject("CDIpercentline2", accuracy(cdiPassLine2, cdiPassLine2 + cdiNGLine2))

                .addObject("cdiPassLine3", cdiPassLine3)
                .addObject("cdiNGLine3", cdiNGLine3)
                .addObject("CDIxiaoji3", cdiPassLine3 + cdiNGLine3)
                .addObject("CDIpercentline3", accuracy(cdiPassLine3, cdiPassLine3 + cdiNGLine3))

                .addObject("cdiPassLine4", cdiPassLine4)
                .addObject("cdiNGLine4", cdiNGLine4)
                .addObject("CDIxiaoji4", cdiPassLine4 + cdiNGLine4)
                .addObject("CDIpercentline4", accuracy(cdiPassLine4, cdiPassLine4 + cdiNGLine4))

                .addObject("cdiPassLine5", cdiPassLine5)
                .addObject("cdiNGLine5", cdiNGLine5)
                .addObject("CDIxiaoji5", cdiPassLine5 + cdiNGLine5)
                .addObject("CDIpercentline5", accuracy(cdiPassLine5, cdiPassLine5 + cdiNGLine5))

                .addObject("cdiPassLine6", cdiPassLine6)
                .addObject("cdiNGLine6", cdiNGLine6)
                .addObject("CDIxiaoji6", cdiPassLine6 + cdiNGLine6)
                .addObject("CDIpercentline6", accuracy(cdiPassLine6, cdiPassLine6 + cdiNGLine6))

                .addObject("CDItotalPass", cdiPassLine1 + cdiPassLine2 + cdiPassLine3 + cdiPassLine4 + cdiPassLine5 + cdiPassLine6)
                .addObject("CDItotalNG", cdiNGLine1 + cdiNGLine2 + cdiNGLine3 + cdiNGLine4 + cdiNGLine5 + cdiNGLine6)
                .addObject("CDItotal", cdiPassLine1 + cdiNGLine1 + cdiPassLine2 + cdiNGLine2 + cdiPassLine3 + cdiNGLine3 + cdiPassLine4 + cdiNGLine4
                        + cdiPassLine5 + cdiNGLine5 + + cdiPassLine6 + cdiNGLine6)
                .addObject("CDIpercent", accuracy(cdiPassLine1 + cdiPassLine2 + cdiPassLine3 + cdiPassLine4 + cdiPassLine5 + cdiPassLine6,
                        cdiPassLine1 + cdiNGLine1 + cdiPassLine2 + cdiNGLine2 + cdiPassLine3 + cdiNGLine3 + cdiPassLine4 + cdiNGLine4
                                + cdiPassLine5 + cdiNGLine5 + + cdiPassLine6 + cdiNGLine6));


        //OC4
        int oc4PassLine1 = oc4Service.selectOC4ByDisplayBoard("success", 1);
        int oc4NGLine1 = oc4Service.selectOC4ByDisplayBoard("fail", 1);
        int oc4PassLine2 = oc4Service.selectOC4ByDisplayBoard("success", 2);
        int oc4NGLine2 = oc4Service.selectOC4ByDisplayBoard("fail", 2);
        int oc4PassLine3 = oc4Service.selectOC4ByDisplayBoard("success", 3);
        int oc4NGLine3 = oc4Service.selectOC4ByDisplayBoard("fail", 3);
        int oc4PassLine4 = oc4Service.selectOC4ByDisplayBoard("success", 4);
        int oc4NGLine4 = oc4Service.selectOC4ByDisplayBoard("fail", 4);
        int oc4PassLine5 = oc4Service.selectOC4ByDisplayBoard("success", 5);
        int oc4NGLine5 = oc4Service.selectOC4ByDisplayBoard("fail", 5);
        int oc4PassLine6 = oc4Service.selectOC4ByDisplayBoard("success", 6);
        int oc4NGLine6 = oc4Service.selectOC4ByDisplayBoard("fail", 6);

        mv1.addObject("oc4PassLine1", oc4PassLine1)
                .addObject("oc4PassLine2", oc4PassLine2)
                .addObject("oc4PassLine3", oc4PassLine3)
                .addObject("oc4PassLine4", oc4PassLine4)
                .addObject("oc4PassLine5", oc4PassLine5)
                .addObject("oc4PassLine6", oc4PassLine6)

                .addObject("oc4NGLine1", oc4NGLine1)
                .addObject("oc4NGLine2", oc4NGLine2)
                .addObject("oc4NGLine3", oc4NGLine3)
                .addObject("oc4NGLine4", oc4NGLine4)
                .addObject("oc4NGLine5", oc4NGLine5)
                .addObject("oc4NGLine6", oc4NGLine6)


                .addObject("oc4xiaojiline1", oc4PassLine1 + oc4NGLine1)
                .addObject("oc4xiaojiline2", oc4PassLine2 + oc4NGLine2)
                .addObject("oc4xiaojiline3", oc4PassLine3 + oc4NGLine3)
                .addObject("oc4xiaojiline4", oc4PassLine4 + oc4NGLine4)
                .addObject("oc4xiaojiline5", oc4PassLine5 + oc4NGLine5)
                .addObject("oc4xiaojiline6", oc4PassLine6 + oc4NGLine6)


                .addObject("oc4percentageline1", accuracy(oc4PassLine1, oc4PassLine1 + oc4NGLine1))
                .addObject("oc4percentageline2", accuracy(oc4PassLine2, oc4PassLine2 + oc4NGLine2))
                .addObject("oc4percentageline3", accuracy(oc4PassLine3, oc4PassLine3 + oc4NGLine3))
                .addObject("oc4percentageline4", accuracy(oc4PassLine4, oc4PassLine4 + oc4NGLine4))
                .addObject("oc4percentageline5", accuracy(oc4PassLine5, oc4PassLine5 + oc4NGLine5))
                .addObject("oc4percentageline6", accuracy(oc4PassLine6, oc4PassLine6 + oc4NGLine6))


                .addObject("oc4totalpass", oc4PassLine1 + oc4PassLine2 + oc4PassLine3 + oc4PassLine4 + oc4PassLine5 + oc4PassLine6)
                .addObject("oc4totalNG", oc4NGLine1 + oc4NGLine2 + oc4NGLine3 + oc4NGLine4 + oc4NGLine5 + oc4NGLine6)
                .addObject("oc4total", oc4PassLine1 + oc4PassLine2 + oc4PassLine3 + oc4PassLine4 + oc4PassLine5 + oc4PassLine6
                        + oc4NGLine1 + oc4NGLine2 + oc4NGLine3 + oc4NGLine4 + oc4NGLine5 + oc4NGLine6)
                .addObject("oc4percent", accuracy(oc4PassLine1 + oc4PassLine2 + oc4PassLine3 + oc4PassLine4 + oc4PassLine5 + oc4PassLine6,
                        oc4PassLine1 + oc4PassLine2 + oc4PassLine3 + oc4PassLine4 + oc4PassLine5 + oc4PassLine6 +
                                oc4NGLine1 + oc4NGLine2 + oc4NGLine3 + oc4NGLine4 + oc4NGLine5 + oc4NGLine6));
        return mv1;
    }

    // PCBA电子看板
    @RequestMapping("/PCBA")
    public ModelAndView PCBAdisplay(){
        ModelAndView mv2 = new ModelAndView("PCBADisplayBoard");
        //oc1
        int oc1pass=oc1Service.selectOC1pass();
        int oc1NG=oc1Service.selectOC1NG();
        int oc1total=oc1pass+oc1NG;
        mv2.addObject("oc1pass",oc1pass)
                .addObject("oc1NG",oc1NG)
                .addObject("oc1total",oc1total)
                .addObject("oc1percent",accuracy(oc1pass,oc1total));

        //OC2
        int oc2pass=oc2Service.selectOC2pass();
        int oc2NG=oc2Service.selectOC2NG();
        int oc2total=oc2pass+oc2NG;
        mv2.addObject("oc2pass",oc2pass)
                .addObject("oc2NG",oc2NG)
                .addObject("oc2total",oc2total)
                .addObject("oc2percent",accuracy(oc2pass,oc2total));
        //BIT
        int  BitPass=bitService.selectBitpass();
        int BitNg=bitService.selectBitNG();
        int BitTotal=BitPass+BitNg;
        mv2.addObject("BitPass",BitPass)
                .addObject("BitNg",BitNg)
                .addObject("BitTotal",BitTotal)
                .addObject("BitPercent",accuracy(BitPass,BitTotal));
        //QC
        int qcPASS=qcService.selectQCpass();
        int qcNG = qcService.selectQCNG();
        int qctotal=qcPASS+qcNG;

        mv2.addObject("qcPASS",qcPASS)
                .addObject("qcNG",qcNG)
                .addObject("qctotal",qctotal)
                .addObject("qcpercent",accuracy(qcPASS,qctotal));
        return mv2;
    }

    // 合成电子看板
    @RequestMapping("/HeCheng")
    public ModelAndView hc(){
        ModelAndView mv3 = new ModelAndView("HeChengDisplayBoard");
        DecimalFormat df = new DecimalFormat("0.0"); //设置double类型小数点后位数格式

        // S/N投入
        int LinkLine1 = zuzhuangService.selectLinkbyline("line 1");
        int LinkLine2 = zuzhuangService.selectLinkbyline("line 2");
        int LinkLine3 = zuzhuangService.selectLinkbyline("line 3");
        int LinkLine4 = zuzhuangService.selectLinkbyline("line 4");
        int LinkLine5 = zuzhuangService.selectLinkbyline("line 5");
        int LinkLine6 = zuzhuangService.selectLinkbyline("line 6");
        mv3.addObject("LinkLine1", LinkLine1)
                .addObject("LinkLine2", LinkLine2)
                .addObject("LinkLine3", LinkLine3)
                .addObject("LinkLine4", LinkLine4)
                .addObject("LinkLine5", LinkLine5)
                .addObject("LinkLine6", LinkLine6)
                .addObject("LinkCount", LinkLine1 + LinkLine2 + LinkLine3 + LinkLine4 + LinkLine5 + LinkLine6);

        //目检站
        int checkPassLine1 = checkService.selectCheckRelate("无", "线别1");
        int checkNGLine1 = checkService.selectCheckRelate("不良", "线别1");
        int checkPassLine2 = checkService.selectCheckRelate("无", "线别2");
        int checkNGLine2 = checkService.selectCheckRelate("不良", "线别2");
        int checkPassLine3 = checkService.selectCheckRelate("无", "线别3");
        int checkNGLine3 = checkService.selectCheckRelate("不良", "线别3");
        int checkPassLine4 = checkService.selectCheckRelate("无", "线别4");
        int checkNGLine4 = checkService.selectCheckRelate("不良", "线别4");
        int checkPassLine5 = checkService.selectCheckRelate("无", "线别5");
        int checkNGLine5 = checkService.selectCheckRelate("不良", "线别5");
        int checkPassLine6 = checkService.selectCheckRelate("无", "线别6");
        int checkNGLine6 = checkService.selectCheckRelate("不良", "线别6");

        // 目检站计算良率
        /*float rightpercent1;
        if ((checkPassLine1 + checkNGLine1) == 0) {
            rightpercent1 = 0;
        } else {
            rightpercent1 = ((float) checkPassLine1 / ((float) checkPassLine1 + (float) checkNGLine1)) * 100;
        }

        float rightpercent2;
        if ((checkPassLine2 + checkNGLine2) == 0) {
            rightpercent2 = 0;
        } else {
            rightpercent2 = ((float) checkPassLine2 / ((float) checkPassLine2 + (float) checkNGLine2)) * 100;
        }

        float rightpercent3;
        if ((checkPassLine3 + checkNGLine3) == 0) {
            rightpercent3 = 0;
        } else {
            rightpercent3 = ((float) checkPassLine3 / ((float) checkPassLine3 + (float) checkNGLine3)) * 100;
        }

        float rightpercent4;
        if ((checkPassLine4 + checkNGLine4) == 0) {
            rightpercent4 = 0;
        } else {
            rightpercent4 = ((float) checkPassLine4 / ((float) checkPassLine4 + (float) checkNGLine4)) * 100;
        }

        float rightpercent5;
        if ((checkPassLine5 + checkNGLine5) == 0) {
            rightpercent5 = 0;
        } else {
            rightpercent5 = ((float) checkPassLine5 / ((float) checkPassLine5 + (float) checkNGLine5)) * 100;
        }*/

        // 小计百分比
        float checkpercent;
        if (checkPassLine1 + checkNGLine1 + checkPassLine2 + checkNGLine2 + checkPassLine3 + checkNGLine3 + checkPassLine4 + checkNGLine4 + checkPassLine5 + checkNGLine5
                + checkPassLine6 + checkNGLine6== 0) {
            checkpercent = 0;
        } else {
            checkpercent = ((float) (checkPassLine1 + checkPassLine2 + checkPassLine3 + checkPassLine4 + checkPassLine5 + checkPassLine6) /
                    (float) (checkPassLine1 + checkPassLine2 + checkPassLine3 + checkPassLine4 + checkPassLine5+ checkPassLine6 + checkNGLine1 + checkNGLine2
                            + checkNGLine3 + checkNGLine4 + checkNGLine5 + checkNGLine6)) * 100;
        }

        // 目检数据提交到前台
        mv3
                .addObject("checkPassLine1", checkPassLine1)
                .addObject("checkNGLine1", checkNGLine1)
                .addObject("checkLine1Total", checkPassLine1 + checkNGLine1)
                .addObject("checkrightpercent1", df.format(calculate(checkPassLine1, checkNGLine1)))

                .addObject("checkPassLine2", checkPassLine2)
                .addObject("checkNGLine2", checkNGLine2)
                .addObject("checkLine2Total", checkPassLine2 + checkNGLine2)
                .addObject("checkrightpercent2", df.format(calculate(checkPassLine2, checkNGLine2)))

                .addObject("checkPassLine3", checkPassLine3)
                .addObject("checkNGLine3", checkNGLine3)
                .addObject("checkLine3Total", checkPassLine3 + checkNGLine3)
                .addObject("checkrightpercent3", df.format(calculate(checkPassLine3, checkNGLine3)))

                .addObject("checkPassLine4", checkPassLine4)
                .addObject("checkNGLine4", checkNGLine4)
                .addObject("checkLine4Total", checkPassLine4 + checkNGLine4)
                .addObject("checkrightpercent4", df.format(calculate(checkPassLine4, checkNGLine4)))

                .addObject("checkPassLine5", checkPassLine5)
                .addObject("checkNGLine5", checkNGLine5)
                .addObject("checkLine5Total", checkPassLine5 + checkNGLine5)
                .addObject("checkrightpercent5", df.format(calculate(checkPassLine5, checkNGLine5)))

                .addObject("checkPassLine6", checkPassLine6)
                .addObject("checkNGLine6", checkNGLine6)
                .addObject("checkLine6Total", checkPassLine6 + checkNGLine6)
                .addObject("checkrightpercent6", df.format(calculate(checkPassLine6, checkNGLine6)))

                .addObject("checkPassTotal", checkPassLine1 + checkPassLine2 + checkPassLine3 + checkPassLine4 + checkPassLine5 + checkPassLine6)
                .addObject("checkNGTotal", checkNGLine1 + checkNGLine2 + checkNGLine3 + checkNGLine4 + checkNGLine5 + checkNGLine6)
                .addObject("checkLineTotal", checkPassLine1 + checkPassLine2 + checkPassLine3 + checkPassLine4 + checkPassLine5 + checkPassLine6
                        + checkNGLine1 + checkNGLine2 + checkNGLine3 + checkNGLine4 + checkNGLine5 + checkNGLine6)
                .addObject("checkpercent", df.format(checkpercent));


        //包装站
        int SnCountLine1 = baozhuangService.selectSnCountByLine("Line 1");
        int SnCountLine2 = baozhuangService.selectSnCountByLine("Line 2");
        int SnCountLine3 = baozhuangService.selectSnCountByLine("Line 3");
        int SnCountLine4 = baozhuangService.selectSnCountByLine("Line 4");
        int SnCountLine5 = baozhuangService.selectSnCountByLine("Line 5");
        int SnCountLine6 = baozhuangService.selectSnCountByLine("Line 6");

        int NboxCountLine1 = baozhuangService.selectNboxCountByLine("line 1");
        int NboxCountLine2 = baozhuangService.selectNboxCountByLine("line 2");
        int NboxCountLine3 = baozhuangService.selectNboxCountByLine("line 3");
        int NboxCountLine4 = baozhuangService.selectNboxCountByLine("line 4");
        int NboxCountLine5 = baozhuangService.selectNboxCountByLine("line 5");
        int NboxCountLine6 = baozhuangService.selectNboxCountByLine("line 6");

        int WboxCountLine1 = baozhuangService.selectWboxCountByLine("line 1");
        int WboxCountLine2 = baozhuangService.selectWboxCountByLine("line 2");
        int WboxCountLine3 = baozhuangService.selectWboxCountByLine("line 3");
        int WboxCountLine4 = baozhuangService.selectWboxCountByLine("line 4");
        int WboxCountLine5 = baozhuangService.selectWboxCountByLine("line 5");
        int WboxCountLine6 = baozhuangService.selectWboxCountByLine("line 6");


        mv3.addObject("SnCountLine1", SnCountLine1)
                .addObject("NboxCountLine1", NboxCountLine1)
                .addObject("WboxCountLine1", WboxCountLine1)

                .addObject("SnCountLine2", SnCountLine2)
                .addObject("NboxCountLine2", NboxCountLine2)
                .addObject("WboxCountLine2", WboxCountLine2)

                .addObject("SnCountLine3", SnCountLine3)
                .addObject("NboxCountLine3", NboxCountLine3)
                .addObject("WboxCountLine3", WboxCountLine3)

                .addObject("SnCountLine4", SnCountLine4)
                .addObject("NboxCountLine4", NboxCountLine4)
                .addObject("WboxCountLine4", WboxCountLine4)

                .addObject("SnCountLine5", SnCountLine5)
                .addObject("NboxCountLine5", NboxCountLine5)
                .addObject("WboxCountLine5", WboxCountLine5)

                .addObject("SnCountLine6", SnCountLine6)
                .addObject("NboxCountLine6", NboxCountLine6)
                .addObject("WboxCountLine6", WboxCountLine6)

                .addObject("BaoZhuangSNCount", SnCountLine1 + SnCountLine2 + SnCountLine3 + SnCountLine4 + SnCountLine5 + SnCountLine6)
                .addObject("BaoZhuangNboxCount", NboxCountLine1 + NboxCountLine2 + NboxCountLine3 + NboxCountLine4 + NboxCountLine5 + NboxCountLine6)
                .addObject("BaoZhuangWboxCount", WboxCountLine1 + WboxCountLine2 + WboxCountLine3 + WboxCountLine4 + WboxCountLine5 + WboxCountLine6);


        //CDI
        int cdiPassLine1 = cdiService.selectCDIByDisplayBoard("PASS", "1");
        int cdiNGLine1 = cdiService.selectCDIByDisplayBoard("NG", "1");
        int cdiPassLine2 = cdiService.selectCDIByDisplayBoard("PASS", "2");
        int cdiNGLine2 = cdiService.selectCDIByDisplayBoard("NG", "2");
        int cdiPassLine3 = cdiService.selectCDIByDisplayBoard("PASS", "3");
        int cdiNGLine3 = cdiService.selectCDIByDisplayBoard("NG", "3");
        int cdiPassLine4 = cdiService.selectCDIByDisplayBoard("PASS", "4");
        int cdiNGLine4 = cdiService.selectCDIByDisplayBoard("NG", "4");
        int cdiPassLine5 = cdiService.selectCDIByDisplayBoard("PASS", "5");
        int cdiNGLine5 = cdiService.selectCDIByDisplayBoard("NG", "5");
        int cdiPassLine6 = cdiService.selectCDIByDisplayBoard("PASS", "6");
        int cdiNGLine6 = cdiService.selectCDIByDisplayBoard("NG", "6");

        mv3.addObject("cdiPassLine1", cdiPassLine1)
                .addObject("cdiNGLine1", cdiNGLine1)
                .addObject("CDIxiaoji1", cdiPassLine1 + cdiNGLine1)
                .addObject("CDIpercentline1", accuracy(cdiPassLine1, cdiPassLine1 + cdiNGLine1))

                .addObject("cdiPassLine2", cdiPassLine2)
                .addObject("cdiNGLine2", cdiNGLine2)
                .addObject("CDIxiaoji2", cdiPassLine2 + cdiNGLine2)
                .addObject("CDIpercentline2", accuracy(cdiPassLine2, cdiPassLine2 + cdiNGLine2))

                .addObject("cdiPassLine3", cdiPassLine3)
                .addObject("cdiNGLine3", cdiNGLine3)
                .addObject("CDIxiaoji3", cdiPassLine3 + cdiNGLine3)
                .addObject("CDIpercentline3", accuracy(cdiPassLine3, cdiPassLine3 + cdiNGLine3))

                .addObject("cdiPassLine4", cdiPassLine4)
                .addObject("cdiNGLine4", cdiNGLine4)
                .addObject("CDIxiaoji4", cdiPassLine4 + cdiNGLine4)
                .addObject("CDIpercentline4", accuracy(cdiPassLine4, cdiPassLine4 + cdiNGLine4))

                .addObject("cdiPassLine5", cdiPassLine5)
                .addObject("cdiNGLine5", cdiNGLine5)
                .addObject("CDIxiaoji5", cdiPassLine5 + cdiNGLine5)
                .addObject("CDIpercentline5", accuracy(cdiPassLine5, cdiPassLine5 + cdiNGLine5))

                .addObject("cdiPassLine6", cdiPassLine6)
                .addObject("cdiNGLine6", cdiNGLine6)
                .addObject("CDIxiaoji6", cdiPassLine6 + cdiNGLine6)
                .addObject("CDIpercentline6", accuracy(cdiPassLine6, cdiPassLine6 + cdiNGLine6))

                .addObject("CDItotalPass", cdiPassLine1 + cdiPassLine2 + cdiPassLine3 + cdiPassLine4 + cdiPassLine5 + cdiPassLine6)
                .addObject("CDItotalNG", cdiNGLine1 + cdiNGLine2 + cdiNGLine3 + cdiNGLine4 + cdiNGLine5 + cdiNGLine6)
                .addObject("CDItotal", cdiPassLine1 + cdiNGLine1 + cdiPassLine2 + cdiNGLine2 + cdiPassLine3 + cdiNGLine3 + cdiPassLine4 + cdiNGLine4
                        + cdiPassLine5 + cdiNGLine5 + + cdiPassLine6 + cdiNGLine6)
                .addObject("CDIpercent", accuracy(cdiPassLine1 + cdiPassLine2 + cdiPassLine3 + cdiPassLine4 + cdiPassLine5 + cdiPassLine6,
                        cdiPassLine1 + cdiNGLine1 + cdiPassLine2 + cdiNGLine2 + cdiPassLine3 + cdiNGLine3 + cdiPassLine4 + cdiNGLine4
                                + cdiPassLine5 + cdiNGLine5 + + cdiPassLine6 + cdiNGLine6));


        //OC4
        int oc4PassLine1 = oc4Service.selectOC4ByDisplayBoard("success", 1);
        int oc4NGLine1 = oc4Service.selectOC4ByDisplayBoard("fail", 1);
        int oc4PassLine2 = oc4Service.selectOC4ByDisplayBoard("success", 2);
        int oc4NGLine2 = oc4Service.selectOC4ByDisplayBoard("fail", 2);
        int oc4PassLine3 = oc4Service.selectOC4ByDisplayBoard("success", 3);
        int oc4NGLine3 = oc4Service.selectOC4ByDisplayBoard("fail", 3);
        int oc4PassLine4 = oc4Service.selectOC4ByDisplayBoard("success", 4);
        int oc4NGLine4 = oc4Service.selectOC4ByDisplayBoard("fail", 4);
        int oc4PassLine5 = oc4Service.selectOC4ByDisplayBoard("success", 5);
        int oc4NGLine5 = oc4Service.selectOC4ByDisplayBoard("fail", 5);
        int oc4PassLine6 = oc4Service.selectOC4ByDisplayBoard("success", 6);
        int oc4NGLine6 = oc4Service.selectOC4ByDisplayBoard("fail", 6);

        mv3.addObject("oc4PassLine1", oc4PassLine1)
                .addObject("oc4PassLine2", oc4PassLine2)
                .addObject("oc4PassLine3", oc4PassLine3)
                .addObject("oc4PassLine4", oc4PassLine4)
                .addObject("oc4PassLine5", oc4PassLine5)
                .addObject("oc4PassLine6", oc4PassLine6)

                .addObject("oc4NGLine1", oc4NGLine1)
                .addObject("oc4NGLine2", oc4NGLine2)
                .addObject("oc4NGLine3", oc4NGLine3)
                .addObject("oc4NGLine4", oc4NGLine4)
                .addObject("oc4NGLine5", oc4NGLine5)
                .addObject("oc4NGLine6", oc4NGLine6)


                .addObject("oc4xiaojiline1", oc4PassLine1 + oc4NGLine1)
                .addObject("oc4xiaojiline2", oc4PassLine2 + oc4NGLine2)
                .addObject("oc4xiaojiline3", oc4PassLine3 + oc4NGLine3)
                .addObject("oc4xiaojiline4", oc4PassLine4 + oc4NGLine4)
                .addObject("oc4xiaojiline5", oc4PassLine5 + oc4NGLine5)
                .addObject("oc4xiaojiline6", oc4PassLine6 + oc4NGLine6)


                .addObject("oc4percentageline1", accuracy(oc4PassLine1, oc4PassLine1 + oc4NGLine1))
                .addObject("oc4percentageline2", accuracy(oc4PassLine2, oc4PassLine2 + oc4NGLine2))
                .addObject("oc4percentageline3", accuracy(oc4PassLine3, oc4PassLine3 + oc4NGLine3))
                .addObject("oc4percentageline4", accuracy(oc4PassLine4, oc4PassLine4 + oc4NGLine4))
                .addObject("oc4percentageline5", accuracy(oc4PassLine5, oc4PassLine5 + oc4NGLine5))
                .addObject("oc4percentageline6", accuracy(oc4PassLine6, oc4PassLine6 + oc4NGLine6))


                .addObject("oc4totalpass", oc4PassLine1 + oc4PassLine2 + oc4PassLine3 + oc4PassLine4 + oc4PassLine5 + oc4PassLine6)
                .addObject("oc4totalNG", oc4NGLine1 + oc4NGLine2 + oc4NGLine3 + oc4NGLine4 + oc4NGLine5 + oc4NGLine6)
                .addObject("oc4total", oc4PassLine1 + oc4PassLine2 + oc4PassLine3 + oc4PassLine4 + oc4PassLine5 + oc4PassLine6
                        + oc4NGLine1 + oc4NGLine2 + oc4NGLine3 + oc4NGLine4 + oc4NGLine5 + oc4NGLine6)
                .addObject("oc4percent", accuracy(oc4PassLine1 + oc4PassLine2 + oc4PassLine3 + oc4PassLine4 + oc4PassLine5 + oc4PassLine6,
                        oc4PassLine1 + oc4PassLine2 + oc4PassLine3 + oc4PassLine4 + oc4PassLine5 + oc4PassLine6 +
                                oc4NGLine1 + oc4NGLine2 + oc4NGLine3 + oc4NGLine4 + oc4NGLine5 + oc4NGLine6));
        int oc1pass=oc1Service.selectOC1pass();
        int oc1NG=oc1Service.selectOC1NG();
        int oc1total=oc1pass+oc1NG;
        mv3.addObject("oc1pass",oc1pass)
                .addObject("oc1NG",oc1NG)
                .addObject("oc1total",oc1total)
                .addObject("oc1percent",accuracy(oc1pass,oc1total));

        //OC2
        int oc2pass=oc2Service.selectOC2pass();
        int oc2NG=oc2Service.selectOC2NG();
        int oc2total=oc2pass+oc2NG;
        mv3.addObject("oc2pass",oc2pass)
                .addObject("oc2NG",oc2NG)
                .addObject("oc2total",oc2total)
                .addObject("oc2percent",accuracy(oc2pass,oc2total));
        //BIT
        int  BitPass=bitService.selectBitpass();
        int BitNg=bitService.selectBitNG();
        int BitTotal=BitPass+BitNg;
        mv3.addObject("BitPass",BitPass)
                .addObject("BitNg",BitNg)
                .addObject("BitTotal",BitTotal)
                .addObject("BitPercent",accuracy(BitPass,BitTotal));
        //QC
        int qcPASS=qcService.selectQCpass();
        int qcNG = qcService.selectQCNG();
        int qctotal=qcPASS+qcNG;

        mv3.addObject("qcPASS",qcPASS)
                .addObject("qcNG",qcNG)
                .addObject("qctotal",qctotal)
                .addObject("qcpercent",accuracy(qcPASS,qctotal));
        return mv3;
    }

    // 计算百分比
    public float calculate(int checkPassLine, int checkNGLine) {
        float rightpercent;
        if ((checkPassLine + checkNGLine) == 0) {
            rightpercent = 0;
        } else {
            rightpercent = ((float) checkPassLine / ((float) checkPassLine + (float) checkNGLine)) * 100;
        }
        return rightpercent;
    }

    // 计算良率
    public static String accuracy(int num, int total) {
        DecimalFormat df = new DecimalFormat("0%");
        //可以设置精确几位小数
        df.setMaximumFractionDigits(1);
        // 模式 例如四舍五入
        df.setRoundingMode(RoundingMode.HALF_UP);
        if (total == 0) {
            return "0.0%";
        } else {
            String accuracy_num = df.format(num * 1.0 / total * 1.0);
            return accuracy_num;
        }

    }

}
