<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: guyanbo
  Date: 2020/2/18
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>标签打印</title>
</head>
<body onload="showTime()">
<div >
    <!-- 导航栏-->
    <%@ include file="./commom/head.jsp"%>

    <!-- 中间部分（左侧栏+表格内容） -->
    <div >
        <!-- 左侧栏 -->
        <%@ include file="commom/left-xitong.jsp"%>

        <!-- 表格内容 -->


        <div class="dept_info col-sm-10">

            <div class="panel panel-success">
                <div class="panel-heading" >
                    <div style="font-size: 20px" id="form">
                        <div align="center"><h4 style="font-size:40px; color: #ff5a19;" >SN标签打印</h4></div>
                        <div id="result" align="right"></div>
                        <form name="snprint" class="snprint" >
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <td>
                                        请选择料号: <select id="getErp" onchange="selectErp()"   name="erp" >
                                        <option>——请选择ERP——</option>
                                    </select>

                                    </td>
                                    <td>
                                        描述: <input type="text" id="describe" readonly/>
                                    </td>
                                    <td>
                                        FW: <input type="text" id="fw"  name="fw" readonly/>
                                    </td>
                                    <td>
                                        机种名称: <input type="text" id="Erpmodel" readonly />
                                    </td>
                                    <input type="text" id="id" name="id" style="visibility: hidden;width: 2px" value="1">
                                </tr>

                                <tr>
                                    <td>
                                        工单号：
                                        <select id="getmoid" onchange="selectchange()" name="moId">
                                            <option>——请选择——</option>
                                        </select>
                                    </td>
                                    <td>
                                        工单数量：<input type="text" id="getmonum" readonly>
                                    </td>
                                    <td>
                                        已完工数：<input type="text" id="getmoremain" readonly>
                                    </td>
                                    <td>
                                        机种名称：<input type="text" id="model" name="model" readonly>
                                    </td>
                                    <td>
                                        操作员：<input type="text" value="${name}" id="operator"  name="operator" readonly>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        标签模板：
                                        <select id="label" onchange="showandhide()">
                                            <option>——请选择——</option>
                                            <option value="模板1">SN标签模板-1</option>
                                            <option value="模板2">SN标签模板-2</option>
                                            <option value="模板3">SN标签模板-3</option>
                                            <option value="模板4">SN标签模板-4</option>
                                            <option value="模板5">SN标签模板-5</option>

                                        </select>
                                    </td>
                                    <!--
                                    <td>
                                        容量：<input type="text" id="capacity" style="text-align: right">GB
                                    </td>
                                    <td id="one">FW：<input type="text" id="fw" /></td>-->
                                    <td>
                                        SN规则名称：
                                        <!-- <select id="snrule" onchange="productsn()" name="snrulename">-->
                                        <select id="snrule" onchange="snRule()"  name="snrulename">
                                            <option>——请选择——</option>
                                        </select>
                                    </td>

                                </tr>
                                <tr>

                                    <td>
                                        是否是南山项目：<input type="radio" checked="checked" name="iswwn" id="yclick" value="是"  >是&nbsp;</input>
                                        <input type="radio" name="iswwn" id="nclick" checked value="否">否&nbsp;</input>
                                    </td>
                                    <td id="wwn1">
                                        WWN规则：
                                        <select id="wwnrule" onchange="productwwns()" name="wwnrulename">
                                            <option></option>
                                        </select>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        产品SN：<input type="text" id="productsn"  name="productSN">
                                    </td>
                                    <td >
                                        <div id="wwn3">
                                            WWN：<input type="text" id="wwn" name="wwn">
                                        </div>
                                    </td>
                                    <td>
                                    <td style="text-align: center">
                                        <input class="btn btn-default" type="button" value="开始打印" style="color: #1e7e1b" id="begin">
                                    </td>
                                    <td style="text-align: center">
                                        <input class="btn btn-default" type="button" value="停止打印" style="color: #c43333" id="end">
                                    </td>
                                    <!--
                                    <td style="text-align: center">
                                        <input class="btn btn-default" type="button" value="测试打印" style="color: #52c482" onclick="setLock()" >
                                        <input class="btn btn-default" type="button" value="单元格" style="color: #425bc4" onclick="addTable()" >
                                    </td>-->
                                </tr>
                                </thead>
                            </table>
                        </form>
                    </div>

                </div>

                <div style="height: 450px;overflow-x: auto;overflow-y: auto;" id="tab">
                    <table class="table table-bordered table-hover" id="myTable">
                        <thead>
                        <th>编号</th>
                        <th>产品 SN号</th>
                        <th>WWN</th>
                        <th>机种名称</th>
                        <th>工单号</th>
                        <th>ERP料号</th>
                        <th>操作员</th>
                        <th>作业时间</th>
                        </thead>
                        <tbody id="table"></tbody>
                    </table>
                </div>
                <script type="text/javascript">
                    var sn_d;//日结
                    var sn_w;//周结
                    var sn_m;//月结
                    var wwn_d;//日结
                    var wwn_w;//周结
                    var wwn_m;//月结
                    var productwwn;
                    var sn_l;//SN流水码长度
                    var wwn_l;//WWN流水码长度
                    var sn=new Array();
                    var wwn=new Array();

                    var isClick = false;
                    var myVar;
                    var oTable = document.getElementsByTagName("table")[1];

                    var tBodies = oTable.tBodies[0];
                    var oInput = document.getElementById("form").getElementsByTagName("input");
                    $(function(){

                        if ($("input[type='radio']:checked").val()=="否")
                        {
                            $("#wwn1").hide();
                            //$("#wwn2").hide();
                            $("#wwn3").hide();

                            productwwn="无";
                            wwn.length=0;
                            document.getElementById("wwnrule").value="";
                            document.getElementById("wwn").value="";
                        }

                    })

                    $(document).ready(function(){
                        //回显SN规则
                        $.ajax({
                            url:"/label/snrules",
                            type:"GET",
                            success:function (res) {
                                var snrule=document.getElementById("snrule");
                                if (res.code==100){
                                    var snrules=res.extendInfo.snrules;
                                    for (var i=0;i<snrules.length;i++){
                                        snrule.options.add(new Option(snrules[i].snrulename));
                                    }
                                }
                            }
                        });
                        //回显WWN规则
                        $.ajax({
                            url:"/label/wwnrules",
                            type:"GET",
                            success:function (res) {
                                var wwnrule=document.getElementById("wwnrule");
                                if (res.code==100){
                                    var wwnrules=res.extendInfo.wwnrules;
                                    for (var i=0;i<wwnrules.length;i++){
                                        wwnrule.options.add(new Option(wwnrules[i].wwnrulename));
                                    }
                                }
                            }
                        });
                        //回显工单号
                        $.ajax({
                            url:"${pageContext.request.contextPath}/gd/selected/",
                            type:"GET",
                            success:function (result) {
                                var unitObj=document.getElementById("getmoid"); //页面上的html:select元素
                                if (result.code == 100){
                                    var list = result.extendInfo.moidlist;
                                    for(var i=0;i<list.length;i++){                    //遍历后台传回的结果，一项项往select中添加option
                                        unitObj.options.add(new Option(list[i].moId));
                                    }
                                }
                            }

                        });



                        $.ajax({
                            url:"${pageContext.request.contextPath}/Erp/selectErpsByPass/",
                            type:"GET",
                            success:function (result) {
                                var unitObj=document.getElementById("getErp"); //页面上的html:select元素
                                if (result.code == 100){
                                    var list = result.extendInfo.erps;
                                    for(var i=0;i<list.length;i++){                    //遍历后台传回的结果，一项项往select中添加option
                                        unitObj.options.add(new Option(list[i].erp));
                                    }
                                }
                            }

                        });


                    }) ;

                    $("#yclick").click(function () {
                        $("#wwn1").show();
                        //$("#wwn2").show();
                        $("#wwn3").show();
                        productwwn="有";
                    })
                    $("#nclick").click(function () {
                        $("#wwn1").hide();
                        //$("#wwn2").hide();
                        $("#wwn3").hide();

                        productwwn="无";
                        wwn.length=0;
                        document.getElementById("wwnrule").value="";
                        document.getElementById("wwn").value="";
                    })



                    function selectErp() {
                        var erp = $("#getErp").val();
                        $.ajax({
                            url:"${pageContext.request.contextPath}/Erp/selectErpByPass/",
                            type:"get",
                            data:"erp="+erp,
                            success:function (result)
                            {
                                if (result.code == 100){
                                    var erp = result.extendInfo.erp;
                                    $("#Erpmodel").val(erp.model);
                                    $("#fw").val(erp.fw);
                                    $("#describe").val(erp.descirbe);

                                }
                                else
                                {
                                    alert("查询失败！")
                                }
                            }

                        });

                    }

                    function showandhide() {
                        /*  var options=$("#label option:selected"); //获取选中的项
                          var option= options.val()
                        //  alert(option)
                          if (option=="1") {
                              var sum1=document.getElementById("one");//隐藏输入框1
                              sum1.style.display="none";
                          }
                          else if(option=="2") {
                              var sum1=document.getElementById("one");//显示输入框1
                              sum1.style.display="block";
                          }

  */
                    }
                    //显示时间
                    function showTime() {
                        var da = '';
                        var now = new Date();
                        var now_m = now.getMonth() + 1;
                        now_m = (now_m < 10) ? '0' + now_m : now_m;
                        var now_d = now.getDate();
                        now_d = (now_d < 10) ? '0' + now_d : now_d;
                        da = now.getFullYear() + '-' + now_m + '-' + now_d;

                        var date1 = new Date(da.substring(0, 4), parseInt(da.substring(5, 7)) - 1, da.substring(8, 10));//当前日期
                        var date2 = new Date(da.substring(0, 4), 0, 1); //1月1号
                        //获取1月1号星期（以周一为第一天，0周一~6周日）
                        var dateWeekNum = date2.getDay() - 1;
                        if (dateWeekNum < 0) {
                            dateWeekNum = 6;
                        }
                        if (dateWeekNum < 4) {
                            //前移日期
                            date2.setDate(date2.getDate() - dateWeekNum);
                        } else {
                            //后移日期
                            date2.setDate(date2.getDate() + 7 - dateWeekNum);
                        }
                        var d = Math.round((date1.valueOf() - date2.valueOf()) / 86400000);
                        if (d < 0) {
                            var date3 = (date1.getFullYear() - 1) + "-12-31";
                            return getYearWeek(date3);
                        } else
                        {
                            //得到年数周数
                            var year = date1.getFullYear();
                            var week = Math.ceil((d + 1) / 7);
                            var str = "时间为: " + year + "年第" + week + "周  " + da + " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
                            var t=da + " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
                            var obj = document.getElementById("result");
                            obj.innerHTML = str;
                            window.setTimeout("showTime()", 1000);
                            return t;

                        }
                    }


                    //回显工单信息
                    function updatemoid(){
                        var moid = $("#getmoid").val();
                        $.ajax({
                            url:"/gd/getMoId/"+moid,
                            type:"GET",
                            success:function (result) {
                                if (result.code == 100){
                                    var gd = result.extendInfo.gongdan;
                                    $("#model").val(gd.model);
                                    $("#getmonum").val(gd.moNum);
                                    $("#getmoremain").val(gd.moNum-gd.moRemainlabel);
                                }else {}
                            }

                        });

                    }
                    //工单下拉框选择
                    function selectchange() {
                        updatemoid();
                        //var snrulename=document.getElementById("snrule").value;
                        //rulesdetail(snrulename);
                        //document.getElementById("productsn").focus();
                    }

                    //SN规则下拉框选择
                    function snRule(){

                        sn.length=0;//清空sn数组
                        var snrulename=document.getElementById("snrule").value;

                        //查询该SN规则，日结、周结、月结数量
                        $.ajax({
                            url:"/label/selectsncount/"+snrulename,
                            type:"GET",
                            dataType: 'text',
                            success:function (res)
                            {
                                var obj = eval("("+res+")");//先用一个js方法eval将json转化为一个object，才能调用它的属性
                                //alert(obj.code);
                                //alert(res);
                                if (obj.code){
                                    //alert("成功");
                                    sn_d=(obj.extendInfo.d).toString();
                                    sn_w=(obj.extendInfo.w).toString();
                                    sn_m=(obj.extendInfo.m).toString();
                                    //alert(d+w+m);
                                }

                                rulesdetail(snrulename);
                            }
                        });

                        //document.getElementById("label").focus();

                    }


                    //WWN规则下拉框选择
                    function productwwns() {
                        wwn.length=0;//清空wwn数组
                        var wwnrulename=document.getElementById("wwnrule").value;
                        //查询该WWN规则，日结、周结、月结数量
                        $.ajax({
                            url:"/label/selectwwncount/"+wwnrulename,
                            type:"GET",
                            dataType: 'text',
                            success:function (res)
                            {
                                var obj = eval("("+res+")");//先用一个js方法eval将json转化为一个object，才能调用它的属性
                                if (obj.code){
                                    wwn_d=(obj.extendInfo.d).toString();
                                    wwn_w=(obj.extendInfo.w).toString();
                                    wwn_m=(obj.extendInfo.m).toString();
                                }
                                wwnrulesdetail(wwnrulename);
                            },
                        });

                        //document.getElementById("label").focus();

                    }

                    //查询SN规则并生成SN
                    function rulesdetail(snrulename) {

                        $.ajax({
                            url:"/label/selectsnrulebyname/"+snrulename,
                            type:"GET",
                            success:function (res) {

                                //  alert(res);
                                if (res.code==100){
                                    var rules = res.extendInfo.rules;
                                    //alert(rules.length+"行");
                                    for (var i=0;i<rules.length;i++){
                                        //alert(rules[i].rule);
                                        switch (rules[i].rule) {
                                            case "年":{
                                                switch (rules[i].mesg) {
                                                    case "上一年":
                                                        sn[i]=parseInt(year())-1;
                                                        break;
                                                    case "本年":
                                                        //
                                                        sn[i]=year();
                                                        //alert(year());
                                                        break;
                                                }
                                                break;
                                            }
                                            case "周":{
                                                switch (rules[i].mesg) {
                                                    case "上一周":
                                                        sn[i]=(parseInt(week())-1).toString();
                                                        while (sn[i].length<2){
                                                            sn[i]='0'+sn[i];
                                                        }
                                                        //alert(sn[i]);
                                                        break;
                                                    case "本周":
                                                        sn[i]=week();
                                                        break;
                                                    case "下一周":
                                                        sn[i]=(parseInt(week())+1).toString();
                                                        while (sn[i].length<2){
                                                            sn[i]='0'+sn[i];
                                                        }
                                                        break;
                                                }
                                                break;
                                            }
                                            case "固定码":{
                                                sn[i]=rules[i].mesg;
                                                break;
                                            }
                                            case "流水码":{
                                                sn_l=rules[i].over-rules[i].start+1;
                                                //alert(rules[i].mesg+"/"+l);
                                                switch (rules[i].mesg) {
                                                    case "日结":{
                                                        sn[i]=sn_d;
                                                        break;
                                                    }
                                                    case "周结":{
                                                        sn[i]=sn_w;
                                                        break;
                                                    }
                                                    case "月结":{
                                                        sn[i]=sn_m;
                                                        break;
                                                    }
                                                }
                                                //alert("1")
                                                while (sn[i].length<sn_l){
                                                    sn[i]='0'+sn[i];
                                                    //alert("c="+sn[i]);
                                                }
                                                //alert("2")
                                                //sn[i]=c;
                                                //alert("sn[i]="+sn[i]);
                                            }
                                        }
                                    }
                                    var productsn="";
                                    for (var j=0;j<sn.length;j++){
                                        //alert(sn[j]);
                                        productsn+=sn[j].toString();
                                    }
                                    //alert(productsn);
                                    document.getElementById("productsn").value=productsn;
                                }
                            }
                        })
                    }

                    //查询WWN规则并生成WWN
                    function wwnrulesdetail(wwnrulename) {
                        alert(wwnrulename+"2");
                        $.ajax({
                            url:"/label/selectwwnrulebyname/"+wwnrulename,
                            type:"GET",
                            success:function (res) {
                                //alert(res.code);
                                if (res.code==100){
                                    var rules = res.extendInfo.rules;
                                    //alert(rules.length+"行");
                                    for (var i=0;i<rules.length;i++){
                                        //alert(rules[i].wwnrule);
                                        switch (rules[i].wwnrule) {
                                            case "年":{
                                                switch (rules[i].wwnmesg) {
                                                    case "上一年":
                                                        wwn[i]=parseInt(year())-1;
                                                        break;
                                                    case "本年":
                                                        //
                                                        wwn[i]=year();
                                                        //alert(year());
                                                        break;
                                                }
                                                break;
                                            }
                                            case "周":{
                                                switch (rules[i].wwnmesg) {
                                                    case "上一周":
                                                        wwn[i]=(parseInt(week())-1).toString();
                                                        while (wwn[i].length<2){
                                                            wwn[i]='0'+wwn[i];
                                                        }
                                                        //alert(sn[i]);
                                                        break;
                                                    case "本周":
                                                        wwn[i]=week();
                                                        break;
                                                    case "下一周":
                                                        wwn[i]=(parseInt(week())+1).toString();
                                                        while (wwn[i].length<2){
                                                            wwn[i]='0'+wwn[i];
                                                        }
                                                        break;
                                                }
                                                break;
                                            }
                                            case "固定码":{
                                                wwn[i]=rules[i].wwnmesg;
                                                break;
                                            }
                                            case "流水码":{
                                                wwn_l=rules[i].wwnover-rules[i].wwnstart+1;
                                                //alert(rules[i].wwnmesg+"/"+l);
                                                switch (rules[i].wwnmesg) {
                                                    case "日结":{
                                                        wwn[i]=wwn_d;
                                                        break;
                                                    }
                                                    case "周结":{
                                                        wwn[i]=wwn_w;
                                                        break;
                                                    }
                                                    case "月结":{
                                                        wwn[i]=wwn_m;
                                                        break;
                                                    }
                                                }
                                                //alert("1")
                                                while (wwn[i].length<wwn_l){
                                                    wwn[i]='0'+wwn[i];
                                                    //alert("c="+wwn[i]);
                                                }
                                                //alert("2")
                                                //sn[i]=c;
                                                //alert("sn[i]="+sn[i]);
                                            }
                                        }
                                    }
                                    var productwwn="";
                                    for (var j=0;j<wwn.length;j++){
                                        //alert(wwn[j]);
                                        productwwn+=wwn[j].toString();
                                    }
                                    //alert(productwwn);
                                    document.getElementById("wwn").value=productwwn;
                                }
                            }
                        })
                    }
                    //返回年
                    function year() {
                        var now = new Date();
                        var now_y=now.getFullYear();
                        return now_y.toString().substring(2, 4);
                    }
                    //返回周
                    function week(){
                        //var da = '';
                        var now = new Date();
                        var now_m = now.getMonth() + 1;
                        now_m = (now_m < 10) ? '0' + now_m : now_m;
                        var now_d = now.getDate();
                        now_d = (now_d < 10) ? '0' + now_d : now_d;
                        var da = now.getFullYear() + '-' + now_m + '-' + now_d;
                        var date1 = new Date(da.substring(0, 4), parseInt(da.substring(5, 7)) - 1, da.substring(8, 10));//当前日期
                        var date2 = new Date(da.substring(0, 4), 0, 1); //1月1号
                        var d = Math.round((date1.valueOf() - date2.valueOf()) / 86400000);
                        var week = Math.ceil((d + 1) / 7);
                        var w='';
                        if (week<10){
                            w='0'+week.toString();
                        }else {
                            w=week.toString();
                        }
                        return w;
                    }


                    //保存SN号
                    function insertlabel() {
                        /* var wwnrule;
                         if(productwwn!="无"){
                             productwwn=document.getElementById("wwn").value;
                             wwnrule=document.getElementById("wwnrule").value;
                         }else {
                             wwnrule="";
                         }
                         var productsn=document.getElementById("productsn").value;
                         var snrulename=document.getElementById("snrule").value;
                         var model=document.getElementById("model").value;
                         var moid=document.getElementById("getmoid").value;
                         var operator=document.getElementById("operator").value;
                         var  erp= document.getElementById("getErp").value;
                         alert("erp是: "+ erp)
                        var muban=document.getElementById("label").value;*/
                        $.ajax({
                            url:"/label/insertlabel",
                            type:"POST",
                            //  data:{"productsn":productsn,"wwn":productwwn,"snrule":snrulename,wwnrule:wwnrule,"model":model,"moid":moid,"operator":operator,"erp":erp},
                            // contentType:'application/json',
                            data: $(".snprint").serialize(),
                            // dataType:"json",
                            success:function (res) {
                                if (res.code==100){
                                    printsn();

                                }
                            }
                        });

                    }
                    //打印sn标签
                    function printsn() {
                        var model=document.getElementById("model").value;
                        var productsn=document.getElementById("productsn").value;
                        var fw=document.getElementById("fw").value;
                        var options=$("#label option:selected"); //获取选中的项
                        var snmuban= options.val();
                        var describe=document.getElementById("describe").value;

                        $.ajax({
                            url:"/label/PrintSN/"+model+"/"+productsn+"/"+describe+"/"+snmuban+"/"+fw,
                            success:function (res) {
                                //alert(res.code);
                                if (res.code==100){
                                    addTable();
                                    changesn();
                                    if(productwwn!="无")
                                    {
                                        changewwn();
                                    }
                                    changemoremain();


                                }
                            }
                        })

                    }
                    //打印完成SN+1
                    function changesn() {
                        //alert(sn[sn.length-1]);
                        sn[sn.length-1]=(parseInt(sn[sn.length-1])+1).toString();
                        while (sn[sn.length-1].length<sn_l){
                            sn[sn.length-1]='0'+sn[sn.length-1];
                            //alert("c="+sn[i]);
                        }
                        var productsn="";
                        for (var j=0;j<sn.length;j++){
                            //alert(sn[j]);
                            productsn+=sn[j].toString();
                        }
                        //alert(productsn);
                        document.getElementById("productsn").value=productsn;
                    }
                    //打印完成WWN+1
                    function changewwn() {
                        //alert(sn[sn.length-1]);
                        wwn[wwn.length-1]=(parseInt(wwn[wwn.length-1])+1).toString();
                        while (wwn[wwn.length-1].length<wwn_l){
                            wwn[wwn.length-1]='0'+wwn[wwn.length-1];
                            //alert("c="+sn[i]);
                        }
                        var productwwn="";
                        for (var j=0;j<wwn.length;j++){
                            //alert(sn[j]);
                            productwwn+=wwn[j].toString();
                        }
                        //alert(productsn);
                        document.getElementById("wwn").value=productwwn;
                    }
                    //已完工数+1
                    function changemoremain() {
                        document.getElementById("getmoremain").value=parseInt(document.getElementById("getmoremain").value)+1;
                        var moid=document.getElementById("getmoid").value;
                        $.ajax({
                            url:"/label/updatemoremain/"+moid,
                            type:"PUT",
                        })
                    }


                    //将文本框数据添加到表格
                    function addTable() {
                        var mai=document.getElementById("tab");
                        //1.创建行
                        var tr = document.createElement("tr");
                        //3.添加单元格
                        tr.appendChild(createNode("td",oInput['id'].value));
                        tr.appendChild(createNode("td", oInput['productSN'].value));
                        tr.appendChild(createNode("td",oInput['wwn'].value));
                        tr.appendChild(createNode("td", oInput['model'].value));
                        tr.appendChild(createNode("td", $("#getmoid").val()));
                        tr.appendChild(createNode("td", $("#getErp").val()));
                        tr.appendChild(createNode("td", oInput['operator'].value));
                        tr.appendChild(createNode("td", showTime()));

                        //2.添加行到表格
                        tBodies.appendChild(tr);
                        document.getElementById("id").value=parseInt( oInput['id'].value) +1;


                        mai.scrollTop=mai.scrollHeight;//通过设置滚动高度
                    }
                    //表格添加列
                    function createNode(element,text) {
                        var td = document.createElement(element);
                        td.innerHTML = text;
                        return td;
                    }



                    //定时器开始结束打印
                    $("#begin").click(function () {
                        if (confirm("请确认开始打印")){
                            isClick = true;//改变标识符的状态值
                            count();

                        }
                    });
                    $("#end").click(function () {
                        if (confirm("请确认结束打印")) {
                            isClick = false;
                            cancel();
                        }
                    });
                    //计时
                    function count() {
                        myVar = setInterval(insertlabel, 5000);
                    }
                    function cancel() {
                        clearInterval(myVar);
                    }

                    function  setLock() {
                        $("#getErp").attr("disabled","disabled");
                        //   var color="background-color:yellow";
                        $("#getErp").css("background-color","red");
                    }
                </script>
            </div><!-- /.panel panel-success -->
        </div><!-- /.dept_info -->
    </div><!-- /.hrms_dept_body -->
    <!-- 尾部-->
    <%@ include file="./commom/foot.jsp"%>

</div><!-- /.hrms_dept_container -->
</body>
</html>
