<%--
  Created by IntelliJ IDEA.
  User: guyanbo
  Date: 2019/8/7
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    String name= (String) request.getSession().getAttribute("name");
%>
<html>
<head>
    <title>BarCode Link页面</title>
    <meta charset="utf-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">

    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <!--引入 Bootstrap-->
    <link href="../css/cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">


</head>
<body  onload="showTime()">
<div >
    <!-- 导航栏-->
    <%@ include file="commom/head.jsp"%>


    <!-- 中间部分（左侧栏+表格内容） -->
    <div >
        <!-- 左侧栏 -->
        <%@ include file="commom/left-xitong.jsp"%>

        <!-- 表格内容 -->
        <div class="dept_info col-sm-10">

            <div class="panel panel-success">

                <!-- 路径导航 -->
                <div class="panel-heading">

                    <div align="center">
                        <h4 style="font-size:40px; color: #ff5a19;" >组装站-Barcode号码绑定</h4></div>
                    <div id="result" align="right"></div>
                    <div id="form">
                        <form action="" name="link" class="link">
                            <table class="table">
                                <tr >
                                    <td>
                                        工单号码：
                                        <!--     <input  type="text" value="${mo}"  name="moId" id="getmoid" readonly/>-->
                                        <select  id="getmoid"  onchange="updatemoid()" name="moId">
                                            <option value="">请选择工单号</option>
                                        </select>
                                    </td>
                                    <td>工单量：<input type="text" name="monum" id="getmonum"   readonly></td>
                                    <td>工单剩余量：<input type="text" name="moremain"  id="getmoremain"  readonly/></td>
                                    <td>机种名称：<input type="text" name="devicename" id="getdevicename"  ></td>
                                    <td>当前操作员：<input type="text" name="operator" readonly value="${name}"></td>
                                    <!--  <td>备注:<input type="text" id="comments" name="comments"/></td>-->

                                </tr>

                                <tr>
                                    <td>
                                        请选择线别：
                                        <select name="line" id="line" >
                                            <option value="0">--请选择线别--</option>
                                            <option value="line 1">Line 1</option>
                                            <option value="line 2">Line 2</option>
                                            <option value="line 3">Line 3</option>
                                            <option value="line 4">Line 4</option>
                                            <option value="line 5">Line 5</option>
                                            <option value="line 6">Line 6</option>
                                            <option value="NPI线">NPI线</option>
                                        </select>
                                    </td>
                                    <!--
                                <td>
                                    设备编号：<input type="text" name="deviceId" id="getdeviceid"  onkeydown="update()" >
                                    <br><span style="color: #28a522" id="deviceIdright"></span>
                                    <span style="color: brown" id="deviceIdwrong"></span>
                                </td>
                                <td>已使用次数：<input type="text" name="times" id="getimes"  readonly></td>
                                -->
                                    <td>请选择产品SN长度：<select  id="snlength">
                                        <option value="17">17 </option>
                                        <option value="16">16 </option>
                                        <option value="15">15 </option>
                                    </select></td>

                                    <td>
                                        <!--
                                       PCBA供应商：
                                       <select name="gys" id="gys"  >
                                           <option value="0">--请选择--</option>
                                           <option value="无锡荣志——9">无锡荣志——9</option>
                                           <option value="无锡荣志——A">无锡荣志——A</option>
                                           <option value="东莞汇钜">东莞汇钜</option>
                                       </select>
                                       <span id="gys2" style="color: #ff5a19"></span>-->
                                        输入外壳厂商: <input  name="gys" id="gys" >
                                    </td>

                                    <td>
                                        <!--
                                        请选择周期：
                                        <select name="week" id="week"  onchange="onchanged()">
                                            <option value="0">--请选择--</option>
                                        </select>-->
                                        请输入周期：
                                        <input type="text" id="week" name="week"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        面盖：  订单号码：<input type="text" name="surfaceMo"   />

                                    </td>
                                    <td>
                                        采购单码：<input type="text" name="surfacePurchase"   />

                                    </td>
                                    <td>
                                        ERP料号：<input type="text" name="surfaceErp"   />

                                    </td>
                                    <td>
                                        输入D/C：<input type="text" name="surfaceDC"   />

                                    </td>
                                    <td>
                                        生产日期：<input type="text" name="surfaceTime"   />

                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        底盖：  订单号码：<input type="text" name="bottomMo"   />

                                    </td>
                                    <td>
                                        采购单码：<input type="text" name="bottomPurchase"  />

                                    </td>
                                    <td>
                                        ERP料号：<input type="text" name="bottomErp"   />

                                    </td>
                                    <td>
                                        输入D/C：<input type="text" name="bottomDC"   />

                                    </td>
                                    <td>
                                        生产日期：<input type="text" name="bottomTime"   />

                                    </td>
                                </tr>


                                <tr>
                                    <td>
                                        PCBA SN：<input type="text" name="pcbaSN" id="inputpcba" onfocus="checkALL()" onkeydown="check()"/>
                                        <input type="text" id="id" name="id" style="visibility: hidden;width: 2px" value="1">
                                        <span id="helpBlock_add_inputPCBA" class="help-block"></span>
                                    </td>
                                    <td>
                                        产品SN：<input type="text" name="productionSN" id="inputproduct"  onkeydown="onKeyUsername();" onfocus="checkPCBA()"/>
                                        <input type="text" id="operation" name="operation" style="visibility: hidden;width: 2px">
                                        <span id="helpBlock_add_inputProduct" class="help-block"></span>
                                    </td>
                                    <td>目标产能：<input type="text" name="mubiao" id="mubiao" value="0"    readonly/></td>
                                    <td>
                                        <input type="button" name="begin" id="begin"  value="开始" onclick="beginwork()" style="background-color: #025ba9;color: white;width: 80px" class="btn"/>
                                        &nbsp;&nbsp;
                                        <input type="button" name="finish" id="finish"  value="暂停"   onclick="pausework()" style="background-color: #bb7800;color: white;width: 80px" class="btn"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        实际产出：<input type="text" name="mubiao" id="real" value="0"  readonly/>
                                    </td>
                                    <td>
                                        达成率：<input type="text" name="mubiao" id="realpercent"  readonly/>
                                    </td>

                                    <td>
                                        错误次数：<input type="text" name="mistake" id="mistake" value="0"    readonly/>
                                    </td>
                                    <td>
                                        错误率：<input type="text" name="mistakepercent" id="mistakepercent"   readonly/>
                                    </td>

                                </tr>

                            </table>
                        </form>
                    </div>

                    <ol class="breadcrumb">
                        <li class="active">操作信息</li>
                    </ol>
                </div>

                <div style="height: 450px;overflow-x: auto;overflow-y: auto;" id="tab">
                    <!-- Table -->
                    <table  class="table table-bordered " id="myTable02" >
                        <thead >
                        <th>编号</th>
                        <th>PCBA BARCODE</th>
                        <th>产品 BARCODE</th>
                        <th>Model号</th>
                        <th>操作信息</th>
                        <th>操作员</th>
                        <th>作业时间</th>
                        <!-- <th>设备号码</th>-->
                        </thead>
                        <tbody ></tbody>
                    </table>
                </div>
                <script type="text/javascript">
                    $(document).ready(function(){
                        // selectweek();
                        //updatemoid();
                        //下拉框回显工单号
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
                    }) ;

                    var myVar;
                    var oInput = document.getElementById("form").getElementsByTagName("input");
                    var oTable = document.getElementsByTagName("table")[1];
                    var tBodies = oTable.tBodies[0];
                    //周期
                    function selectweek() {
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
                            var zhouqi = Math.ceil((d + 1) / 7);
                            var lastweek=zhouqi-1;
                            var lastlastweek=lastweek-1;
                            var lastlastlastweek=lastlastweek-1;
                            var last=lastlastlastweek-1;
                            var final2 = last - 1;
                            var final = last - 2;
                        }
                        // var week=document.getElementById("week"); //页面上的html:select元素
                        //遍历后台传回的结果，一项项往select中添加option
                        //week.options.add(new Option("第"+zhouqi+"周"));
                        // week.options.add(new Option("第"+lastweek+"周"));
                        $("#week").append("<option value='"+zhouqi+"'>"+"当前周：第"+zhouqi+"周"+"</option>");
                        $("#week").append("<option value='"+lastweek+"'>"+"上一周：第"+lastweek+"周"+"</option>");
                        $("#week").append("<option value='"+lastlastweek+"'>"+"上上周：第"+lastlastweek+"周"+"</option>");
                        $("#week").append("<option value='"+lastlastlastweek+"'>"+"上上上周：第"+lastlastlastweek+"周"+"</option>");
                        $("#week").append("<option value='"+last+"'>"+"上上上上周：第"+last+"周"+"</option>");
                        $("#week").append("<option value='"+final2+"'>"+"最后一周前一周：第"+final2+"周"+"</option>");
                        $("#week").append("<option value='"+final+"'>"+"最后一周：第"+final+"周"+"</option>");


                    }
                    //回显工单信息
                    function updatemoid(){
                        var moid = $("#getmoid").val();
                        $.ajax({
                            url:"/link/getMoId/"+moid,
                            type:"GET",
                            success:function (result) {
                                if (result.code == 100){
                                    var gd = result.extendInfo.gongdan;
                                    $("#getmonum").val(gd.moNum);
                                    $("#getmoremain").val(gd.moRemain);
                                    $("#getdevicename").val(gd.model);
                                }else {
                                }
                            }

                        });

                    }
                    //显示设备信息
                    function update() {
                        if(event.keyCode==13) {
                            var deviceid=$("#getdeviceid").val();
                            $.ajax({
                                url: "/link/getDeviceId/"+deviceid,
                                type:"GET",
                                success: function (result) {
                                    if (result.code == 100){
                                        $("#deviceIdwrong").text("");
                                        $("#deviceIdright").text("设备号正确！");
                                        var d=result.extendInfo.device;
                                        $("#getimes").val(d.times);
                                    }else {
                                        $("#deviceIdright").text("");
                                        $("#deviceIdwrong").text("设备号错误！");
                                    }

                                }
                            });
                        }
                    }

                    //添加到数据库
                    function addlink() {
                        //添加数据
                        $.ajax({
                            url: "/link/add",
                            type: "POST",
                            data: $(".link").serialize(),
                        });
                    }

                    //添加信息正确则工单剩余量-1
                    function updatemoremain() {
                        $.ajax({
                            url:"/link/merman",
                            type:"POST",
                            data: $(".link").serialize(),
                        });
                    }
                    //将文本框数据添加到表格
                    function add() {
                        var mai=document.getElementById('tab');
                        //1.创建行
                        var tr = document.createElement("tr");


                        //3.添加单元格
                        tr.appendChild(createNode("td",oInput['id'].value));
                        tr.appendChild(createNode("td",oInput['pcbaSN'].value));
                        tr.appendChild(createNode("td", oInput['productionSN'].value));
                        tr.appendChild(createNode("td", oInput['devicename'].value));
                        tr.appendChild(createNode("td", oInput['operation'].value));
                        // tr.appendChild(createNode("td", oInput['times'].value));
                        tr.appendChild(createNode("td", oInput['operator'].value));
                        tr.appendChild(createNode("td", showTime()));
                        // tr.appendChild(createNode("td", oInput['deviceId'].value));

                        //2.添加行到表格
                        tBodies.appendChild(tr);
                        //document.getElementById("getmoremain").value=oInput['moremain'].value-1;
                        //document.getElementById("getimes").value= parseInt( oInput['times'].value) +1;
                        document.getElementById("id").value=parseInt( oInput['id'].value) +1;

                        mai.scrollTop=mai.scrollHeight;//通过设置滚动高度
                    }
                    //表格添加列
                    function createNode(element,text) {
                        var td = document.createElement(element);
                        td.innerHTML = text;
                        return td;
                    }
                    //产品SN号判断
                    function onKeyUsername(){
                        if(event.keyCode==13){
                            //判断产品SN号是否重复,添加工单号条件
                            var ProSN = $("#inputproduct").val();
                            var mo = $("#getmoid option:selected").val();
                            $.ajax({
                                url:"/link/checkProExists",
                                type:"GET",
                                data:{"productionSN":ProSN,"mo":mo},
                                success:function (result) {
                                    if (result.code == 100){
                                        checkSN();
                                    }
                                    else {
                                        $("#inputproduct").parent().parent().removeClass("has-success");
                                        $("#inputproduct").parent().parent().addClass("has-error");
                                        $("#helpBlock_add_inputProduct").text("产品SN号重复");
                                        document.getElementById("operation").value="错误：产品SN号重复";
                                        addlink();
                                        add();

                                        mistake();
                                        jisuanmistake();

                                        document.getElementById("inputproduct").value="";
                                        document.getElementById("inputproduct").focus();
                                    }
                                }
                            });
                        }
                    }
                    //enter键跳入
                    function jump(next, event){
                        if(event.keyCode==13)
                        {
                            document.getElementById(next).focus();
                        }
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
                        pausetime();
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
                    //下拉框提示字符
                    function onchanged(){
                        var id = document.getElementById("gys").value;
                        var showMsg = document.getElementById("showMeg");
                        if(id=="无锡荣志——9".toString()){
                            $("#gys2").text("首码为9");
                        }else if (id=="无锡荣志——A".toString()){
                            $("#gys2").text("首码为A");
                        } else if(id=="东莞汇钜".toString()) {
                            $("#gys2").text("首码为H");
                        }else {
                            $("#gys2").text("");
                        }
                    }
                    var isClick = false;


                    //产品SN号字符串检查
                    function checkSN() {

                        var input = document.getElementById("inputproduct").value;

                        var options=$("#snlength option:selected"); //获取选中的项
                        var snlength= parseInt( options.val())




                        //计算时间
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

                        }
                        if(isClick==false)
                        {
                            alert("请点击开始按钮进行工作");
                            document.getElementById("inputproduct").value="";
                            //document.getElementById("operation").value="";
                            document.getElementById("inputproduct").focus();
                            return ;
                        }


                        if (document.getElementById("getmoremain").value=="0") {
                            playSound();
                            alert("该工单已结束,重新输入工单号");
                            window.location.href="/xt/zuzhuang";

                        }
                        var myselect=document.getElementById("week");
                        // var index = myselect.selectedIndex;
                        var week =myselect.value;

                        //字符串条件判断
                        if(input.substring(0, 2)!= year.toString().substring(2,4))

                        {
                            //$("#helpBlock_add_inputPCBA").hide();
                            //输入框变红
                            $("#inputproduct").parent().parent().addClass("has-error");
                            //输入框下面显示红色提示信息
                            $("#helpBlock_add_inputProduct").text("年份错误,请重新输入！");
                            document.getElementById("operation").value="错误：产品SN号年份错误";
                            //添加数据
                            addlink();
                            add();
                            playSound();
                            alert("错误：产品SN号年份错误");
                            mistake();
                            jisuanmistake();
                            document.getElementById("inputproduct").value="";
                            //document.getElementById("operation").value="";
                            document.getElementById("inputproduct").focus();
                            //$("#helpBlock_add_inputProduct").hide();
                        }


                        else if(input.substring(2, 4)!==week.toString())
                        //else if(input.substring(2, 4)!=="33".toString())
                        {
                            //$("#helpBlock_add_inputPCBA").hide();
                            //输入框变红
                            $("#inputproduct").parent().parent().addClass("has-error");
                            //输入框下面显示红色提示信息
                            $("#helpBlock_add_inputProduct").text("周期错误，请重新输入！");
                            document.getElementById("operation").value="错误：产品SN号周期错误";
                            //添加数据
                            addlink();
                            add();
                            playSound();
                            alert("错误：产品SN号周期错误");
                            mistake();
                            jisuanmistake();
                            document.getElementById("inputproduct").value="";
                            document.getElementById("inputproduct").focus();
                        }
                        /*
                                                else if(input.charAt(10)!='0')
                                                {
                                                    //输入框变红
                                                    $("#inputproduct").parent().parent().addClass("has-error");
                                                    //输入框下面显示红色提示信息
                                                    $("#helpBlock_add_inputProduct").text("生产厂家错误，请重新输入！");
                                                    //$("#helpBlock_add_inputPCBA").hide();
                                                    document.getElementById("operation").value="错误：产品SN号生产厂家错误";
                                                    addlink();
                                                    add();
                                                    playSound();
                                                    alert("错误：产品SN号生产厂家错误");



                                                    mistake();
                                                    jisuanmistake();
                                                    document.getElementById("inputproduct").value="";
                                                    document.getElementById("inputproduct").focus();
                                                }*/
                        else if(input.length!=snlength)
                        {

                            $("#inputproduct").parent().parent().addClass("has-error");
                            //输入框下面显示红色提示信息
                            $("#helpBlock_add_inputProduct").text("SN位数错误，请重新输入！");
                            //$("#helpBlock_add_inputPCBA").hide();
                            document.getElementById("operation").value="错误：产品SN号位数错误";
                            addlink();
                            add();
                            document.getElementById("inputproduct").value="";

                            mistake();
                            jisuanmistake();
                            document.getElementById("inputproduct").focus();
                        }
                        else {

                            $("#inputproduct").parent().parent().removeClass("has-error");
                            $("#inputproduct").parent().parent().addClass("has-success");
                            $("#helpBlock_add_inputProduct").text("操作正确！");
                            //$("#helpBlock_add_inputPCBA").hide();

                            document.getElementById("operation").value = "正确";
                            document.getElementById("getmoremain").value = oInput['moremain'].value - 1;
                            addlink();
                            add();
                            updatemoremain();
                            document.getElementById("inputproduct").value = "";
                            document.getElementById("inputpcba").value = "";
                            real();
                            jisuanreal();
                            document.getElementById("inputpcba").focus();
                        }
                    }


                    //PCBA SN号检查
                    function check() {
                        if  (event.keyCode==13) {
                            var PcbaSN = $("#inputpcba").val();
                            var moid=$("#getmoid").val;
                            $.ajax({
                                url: "/link/checkPcbaExists",
                                type: "GET",
                                data: {"pcbaSN":PcbaSN,"moid":moid},
                                success: function (result)
                                {
                                    if (result.code == 100)
                                    {
                                        //  var reg=/^20/;
                                        /*
                                        var id = document.getElementById("gys").value;
                                        if(id=="无锡荣志——9".toString()){
                                            reg=/^9/;
                                        }else if(id=="无锡荣志——A".toString()){
                                            reg=/^A/;
                                        }
                                        else {
                                            reg = /^H/;
                                        }
                                        */
                                        /*
                                       if (!reg.test($("#inputpcba").val())||PcbaSN.length!==17) {
                                           //输入框变红
                                           $("#helpBlock_add_inputPCBA").parent().parent().removeClass("has-success");
                                           $("#helpBlock_add_inputPCBA").parent().parent().addClass("has-error");
                                           //输入框下面显示红色提示信息
                                        //   $("#helpBlock_add_inputPCBA").text("错误：PCBA SN号首码错误");
                                         //  document.getElementById("operation").value = "错误：PCBA SN号首码错误";
                                           $("#helpBlock_add_inputPCBA").text("错误：PCBA SN号错误");
                                             document.getElementById("operation").value = "错误：PCBA SN号错误";
                                           addlink();
                                           add();
                                           playSound();
                                           alert("错误：PCBA SN号错误");

                                           mistake();
                                           jisuanmistake();

                                           document.getElementById("inputproduct").value = "";
                                           document.getElementById("inputpcba").value = "";

                                           document.getElementById("inputproduct").focus();
                                       }

*/

                                        $("#inputpcba").parent().parent().removeClass("has-error");
                                        $("#inputpcba").parent().parent().addClass("has-success");
                                        $("#helpBlock_add_inputPCBA").text("操作正确！");
                                        //document.getElementById("operation").value = "正确";
                                        //  document.getElementById("getmoremain").value = oInput['moremain'].value - 1;
                                        // addlink();
                                        //add();
                                        //updatemoremain();
                                        //document.getElementById("inputproduct").value = "";
                                        //document.getElementById("inputpcba").value = "";
                                        //real();
                                        //jisuanreal();
                                        document.getElementById("inputproduct").focus();

                                    }
                                    else
                                    {
                                        $("#inputpcba").parent().parent().removeClass("has-success");
                                        $("#inputpcba").parent().parent().addClass("has-error");
                                        $("#helpBlock_add_inputPCBA").text("PCBA SN号重复");
                                        document.getElementById("operation").value="错误：PCBA SN号重复";
                                        addlink();
                                        add();
                                        playSound();
                                        alert("错误：PCBA SN号重复");
                                        document.getElementById("inputproduct").value = "";
                                        mistake();
                                        jisuanmistake();
                                        document.getElementById("inputproduct").focus();
                                    }


                                }
                            });
                        }
                    }












                    ////光标停在产品SN号时判断前面值是否输入
                    function checkALL() {
                        if (document.getElementById("getmoid").value==""||document.getElementById("getmonum").value==""||document.getElementById("getmoremain").value==""){
                            playSound();
                            alert("请检查工单信息！");
                            document.getElementById("getmoid").focus();
                        }
                        /*else if(document.getElementById("getdeviceid").value==""||document.getElementById("getimes").value==""){
                            playSound();
                            alert("请检查设备信息！");
                            document.getElementById("getdeviceid").focus();
                        }*/
                        else if(document.getElementById("getdevicename").value==""){
                            playSound();
                            alert("请输入机种名称！");
                            document.getElementById("getdevicename").focus();
                        }
                        else if (document.getElementById("line").value==0)
                        {
                            playSound();
                            alert("请选择线别！");
                            document.getElementById("line").focus();
                        }else if (document.getElementById("gys").value==0){
                            playSound();
                            alert("请输入外壳厂商！");
                            document.getElementById("gys").focus();
                        }else if (document.getElementById("operator").value==""){
                            if (confirm("用户名已失效，请重新登录")) {
                                document.getElementById("operator").focus();
                                window.location="/user/logout";
                            }

                        }else {
                            document.getElementById("inputpcba").focus();
                        }
                    }

                    //光标停在sn号时判断是否输入产品PCBA号
                    function checkPCBA() {
                        if (document.getElementById("inputpcba").value==""){
                            playSound();
                            alert("请输入产品PCBA号！");
                            document.getElementById("inputpcba").focus();
                        }
                    }


                    //播放声音
                    function playSound()
                    {
                        var borswer = window.navigator.userAgent.toLowerCase();
                        if ( !!window.ActiveXObject || "ActiveXObject" in window )
                        {
                            //IE内核浏览器
                            var OSPlayer = new ActiveXObject("WMPLayer.OCX");
                            OSPlayer.url = "http://www.xmf119.cn/static/admin/sounds/notify.wav";
                            OSPlayer.controls.play();
                        } else
                        {
                            //非IE内核浏览器
                            var strAudio = "<audio id='audioPlay' src='http://www.xmf119.cn/static/admin/sounds/notify.wav' hidden='true'>";
                            if ( $( "body" ).find( "audio" ).length <= 0 )
                                $( "body" ).append( strAudio );
                            var audio = document.getElementById( "audioPlay" );
                            //浏览器支持 audion
                            audio.play();
                        }
                    }

                    y=0;
                    function real()
                    {
                        y=y+1;
                        document.getElementById("real").value=y;
                    }

                    z=0;
                    function  mistake()
                    {
                        z=z+1;
                        document.getElementById("mistake").value=z;
                    }

                    function  jisuanreal() {
                        document.getElementById("realpercent").value= Number(parseFloat(document.getElementById("real").value)/parseFloat(document.getElementById("mubiao").value)*100).toFixed(1)+"%";
                    }
                    function  jisuanmistake() {
                        document.getElementById("mistakepercent").value= Number(parseFloat(document.getElementById("mistake").value)/parseFloat(document.getElementById("mubiao").value)*100).toFixed(1)+"%";
                    }

                    function count() {
                        myVar=setInterval(colc, 17000);

                    }

                    function beginwork() {

                        if (confirm("请确认开始计时")) {
                            isClick = true;//改变标识符的状态值
                            count();
                        }
                    }
                    function pausework() {

                        if (confirm("请确认暂停工作")) {
                            isClick=false;
                            cancel();
                        }

                    }

                    x=0;
                    function  colc(){
                        x = x+1;
                        document.getElementById("mubiao").value= x;
                        document.getElementById("realpercent").value= Number(parseFloat(document.getElementById("real").value)/parseFloat(document.getElementById("mubiao").value)*100).toFixed(1)+"%";
                        document.getElementById("mistakepercent").value= Number(parseFloat(document.getElementById("mistake").value)/parseFloat(document.getElementById("mubiao").value)*100).toFixed(1)+"%";

                    }





                    function cancel() {
                        clearInterval(myVar);

                    }


                    function pausetime() {
                        var date=new Date();
                        var h= date.getHours();  // 获取小时数(0-23)
                        var m= date.getMinutes();  // 获取分钟数(0-59)
                        var s= date.getSeconds();  // 获取秒数(0-59)
                        if(h=="11".toString()&&m=="51".toString()&&s=="1".toString())
                        {
                            isClick=false;
                            cancel();

                        }else if(h=="18".toString()&&m=="1".toString()&&s=="1".toString())
                        {
                            isClick=false;
                            cancel();

                        }

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
