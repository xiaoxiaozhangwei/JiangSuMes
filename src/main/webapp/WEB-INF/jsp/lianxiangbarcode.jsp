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


%>
<html>
<head>
    <title>LENOVO BarCode Link页面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">


</head>
<body onload="showTime()">
<div>
    <!-- 导航栏-->
    <%@ include file="./commom/head.jsp" %>


    <!-- 中间部分（左侧栏+表格内容） -->
    <div>
        <!-- 左侧栏 -->
        <%@ include file="commom/left-xitong.jsp" %>

        <!-- 表格内容 -->
        <div class="dept_info col-sm-10">

            <div class="panel panel-success">

                <!-- 路径导航 -->
                <div class="panel-heading">

                    <div align="center">
                        <h4 style="font-size:40px; color: #ff5a19;">LX — Barcode号码绑定</h4></div>
                    <div id="result" align="right"></div>
                    <div id="form">
                        <form action="" name="link" class="link">
                            <table class="table">
                                <tr>
                                    <td>
                                        工单号码：
                                        <select name="moId" id="getmoid" onchange="updatemoid()">
                                            <option value="">请选择</option>
                                        </select>
                                    </td>
                                    <td>机种名称：<input type="text" name="devicename" id="getdevicename"></td>
                                    <td>工单量：<input type="text" name="monum" id="getmonum" readonly></td>
                                    <!--<td>工单剩余量：<input type="text" name="moremain" id="getmoremain" readonly/></td>-->
                                    <td>当前操作员：<input type="text" name="operator" readonly value="${name}"></td>
                                </tr>

                                <tr>
                                    <td>ERP料号：<input type="text" name="erp" id="materialnumber" onkeydown="PN()"></td>
                                    <td>联想PN：<input type="text" name="pn" id="LXPN" readonly/></td>
                                    <td>
                                        状态：
                                        <select name="comments" id="comments">
                                            <option value="正常">正常</option>
                                            <option value="重工">重工</option>
                                        </select>
                                    </td>
                                    <td>
                                        芯盛SN位数：<input id="productSN_length">
                                    </td>

                                    <td>
                                        日份：
                                        <select name="moDay" id="showDay" onchange="updateDay()">
                                        </select>
                                    </td>

                                </tr>

                                <tr>
                                    <td>
                                        芯盛SN：<input type="text" name="productionSN" id="inputproduct" onkeydown="onKeyUsername();" onfocus="checkNull1()"/>
                                        <input type="text" id="operation" name="operation"
                                               style="visibility: hidden;width: 2px">
                                        <span id="helpBlock_add_inputProduct" class="help-block"></span>
                                    </td>
                                    <td>
                                        联想SN：<input type="text" name="lenovoSN" id="inputpcba" onfocus="checkNull()" onkeydown="check()"/>
                                        <input type="text" id="id" name="id" style="visibility: hidden;width: 2px" value="1">
                                        <span id="helpBlock_add_inputPCBA" class="help-block"></span>
                                    </td>
                                    <td>目标产能：<input type="text" name="mubiao" id="mubiao" value="0" readonly/></td>
                                    <td>
                                        <input type="button" name="begin" id="begin" value="开始" onclick="beginwork()" style="background-color: #025ba9;color: white;width: 80px" class="btn"/>
                                        &nbsp;&nbsp;
                                        <input type="button" name="finish" id="finish" value="暂停" onclick="pausework()" style="background-color: #bb7800;color: white;width: 80px" class="btn"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        实际产出：<input type="text" name="mubiao" id="real" value="0" readonly/>
                                    </td>
                                    <td>
                                        达成率：<input type="text" name="mubiao" id="realpercent" readonly/>
                                    </td>
                                    <td>
                                        错误次数：<input type="text" name="mistake" id="mistake" value="0" readonly/>
                                    </td>
                                    <td>
                                        错误率：<input type="text" name="mistakepercent" id="mistakepercent" readonly/>
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
                    <table class="table table-bordered " id="myTable02">
                        <thead>
                        <th>编号</th>
                        <th>产品 BARCODE</th>
                        <th>PCBA BARCODE</th>
                        <th>操作信息</th>
                        <th>操作员</th>
                        <th>作业时间</th>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
                <script type="text/javascript"></script>
                <script type="text/javascript">

                    $(document).ready(function () {
                        // 下拉框显示昨天今天明天
                        //昨天的时间
                        var day1 = new Date();
                        day1.setTime(day1.getTime()-24*60*60*1000);
                        var s1 =  day1.getDate();
                        //今天的时间
                        var day2 = new Date();
                        day2.setTime(day2.getTime());
                        var s2 =  day2.getDate();
                        //明天的时间
                        var day3 = new Date();
                        day3.setTime(day3.getTime()+24*60*60*1000);
                        var s3 = day3.getDate();

                        $("#showDay").append("<option value='"+s3+"'>"+"明天: "+s3+"号"+"</option>");
                        $("#showDay").append("<option value='"+s2+"'>"+"今天: "+s2+"号"+"</option>");
                        $("#showDay").append("<option value='"+s1+"'>"+"昨天: "+s1+"号"+"</option>");
                        //下拉框回显工单号
                        $.ajax({
                            url: "${pageContext.request.contextPath}/gd/selected/",
                            type: "GET",
                            success: function (result) {
                                var unitObj = document.getElementById("getmoid"); //页面上的html:select元素
                                if (result.code == 100) {
                                    var list = result.extendInfo.moidlist;
                                    for (var i = 0; i < list.length; i++) {                    //遍历后台传回的结果，一项项往select中添加option
                                        unitObj.options.add(new Option(list[i].moId));
                                    }
                                }
                            }

                        });
                    });




                    var myVar;
                    var oInput = document.getElementById("form").getElementsByTagName("input");
                    var oTable = document.getElementsByTagName("table")[1];
                    var tBodies = oTable.tBodies[0];

                    //回显工单信息
                    function updatemoid() {
                        var moid = $("#getmoid").val();
                        $.ajax({
                            url: "/link/getMoId/" + moid,
                            type: "GET",
                            success: function (result) {
                                if (result.code == 100) {
                                    var gd = result.extendInfo.gongdan;
                                    $("#getmonum").val(gd.moNum);
                                    //$("#getmoremain").val(gd.moRemain);
                                    $("#getdevicename").val(gd.model);
                                } else {
                                }
                            }

                        });

                    }

                    //添加到数据库
                    function addlink() {
                        //添加数据
                        $.ajax({
                            url: "/lenovolink/insertlenovolink",
                            type: "POST",
                            data: $(".link").serialize(),
                        });
                    }

                    //添加信息正确则工单剩余量-1
                    function updatemoremain() {
                        $.ajax({
                            url: "/link/merman",
                            type: "POST",
                            data: $(".link").serialize(),
                        });
                    }

                    //将文本框数据添加到表格
                    function add() {
                        var mai = document.getElementById('tab');
                        //1.创建行
                        var tr = document.createElement("tr");

                        //3.添加单元格
                        tr.appendChild(createNode("td", oInput['id'].value));
                        tr.appendChild(createNode("td", oInput['productionSN'].value));
                        tr.appendChild(createNode("td", oInput['lenovoSN'].value));
                        tr.appendChild(createNode("td", oInput['operation'].value));
                        tr.appendChild(createNode("td", oInput['operator'].value));
                        tr.appendChild(createNode("td", showTime()));

                        //2.添加行到表格
                        tBodies.appendChild(tr);
                        //document.getElementById("getmoremain").value=oInput['moremain'].value-1;
                        //document.getElementById("getimes").value = parseInt(oInput['times'].value) + 1;
                        document.getElementById("id").value = parseInt(oInput['id'].value) + 1;

                        mai.scrollTop = mai.scrollHeight;//通过设置滚动高度
                    }

                    //表格添加列
                    function createNode(element, text) {
                        var td = document.createElement(element);
                        td.innerHTML = text;
                        return td;
                    }

                    //产品SN号判断
                    function onKeyUsername() {
                        if (event.keyCode == 13) {
                            var ProSN = $("#inputproduct").val();

                            if(document.getElementById("comments").value==='正常'){

                                //判断产品SN号是否重复
                                $.ajax({
                                    url: "/lenovolink/selectProductsnExist",
                                    type: "GET",
                                    data: {"productionSN":ProSN},
                                    success: function (result) {
                                        if (result.code == 100) {
                                            checkSN();
                                        } else {
                                            $("#inputproduct").parent().parent().removeClass("has-success");
                                            $("#inputproduct").parent().parent().addClass("has-error");
                                            $("#helpBlock_add_inputProduct").text("产品SN号重复");
                                            document.getElementById("operation").value = "错误：产品SN号重复";
                                            addlink();
                                            add();
                                            mistake();
                                            jisuanmistake();

                                            document.getElementById("inputproduct").value = "";
                                            document.getElementById("inputproduct").focus();
                                        }
                                    }
                                });
                            }else {
                                checkSN();
                            }


                        }
                    }


                    //enter键跳入
                    function jump(next, event) {
                        if (event.keyCode == 13) {
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
                        } else {
                            //得到年数周数
                            var year = date1.getFullYear();
                            var week = Math.ceil((d + 1) / 7);
                            var str = "时间为: " + year + "年第" + week + "周  " + da + " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
                            var t = da + " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
                            var obj = document.getElementById("result");
                            obj.innerHTML = str;
                            window.setTimeout("showTime()", 1000);
                            return t;

                        }
                    }

                    //联想PN字符显示
                    function PN() {
                        if (event.keyCode == 13) {
                            var gkname = document.getElementById("getdevicename").value;
                            var materialnumber = document.getElementById("materialnumber").value;

                            if (gkname == "SSDC600TS30240C-257-BGC" && materialnumber == "10000026") {

                                // $("#LXPN").val("SSS0L68805");
                                document.getElementById("LXPN").value = "SSS0L68805";
                            } else if (gkname == "SSDC600TS30240C-257-BGC" && materialnumber == "10000083") {
                                $("#LXPN").val("SSS0L68811");
                            } else if (gkname == "SSDE600MS30240C-257-BGS" && (materialnumber == "10000077" || materialnumber == "10000082")) {
                                $("#LXPN").val("SSS0T08251");
                            } else if (gkname == "SSDE600MS30480C-257-BGS" && materialnumber == "10000078") {
                                $("#LXPN").val("SSS0T08250");
                            }else if(gkname == "SSDC600TS30240C-257-BGC" && materialnumber == "10000105") {
                                $("#LXPN").val("SSS0L68811");
                            }else if(gkname == "SSDE600MS30240C-257-BGS" && materialnumber == "10000107") {
                                $("#LXPN").val("SSD0P10957");
                            }else if(gkname == "GGAZT120S3CN6" && materialnumber == "10040063") {
                                $("#LXPN").val("SSS0L68814");
                            }else if(gkname == "GG76T480S3CN6M0" && materialnumber == "30090123") {
                                $("#LXPN").val("SSSCFA1975");
                            }
                            else {
                                alert("国科型号或ERP料号输入错误，请重新输入！");
                                document.getElementById("getdevicename").focus();

                            }
                        }

                    }

                    var isClick = false;

                    //产品SN号字符串检查
                    function checkSN() {

                        var input = document.getElementById("inputproduct").value;

                        if (isClick == false) {
                            alert("请点击开始按钮进行工作");
                            document.getElementById("inputproduct").value = "";
                            //document.getElementById("operation").value="";
                            document.getElementById("inputproduct").focus();
                            return;
                        }

                        //字符串条件判断
                        if (input.length != document.getElementById("productSN_length").value) {
                            $("#inputproduct").parent().parent().addClass("has-error");
                            //输入框下面显示红色提示信息
                            $("#helpBlock_add_inputProduct").text("SN位数错误，请重新输入！");
                            //$("#helpBlock_add_inputPCBA").hide();
                            document.getElementById("operation").value = "错误：产品SN号位数错误";
                            addlink();
                            add();
                            document.getElementById("inputproduct").value = "";
                            document.getElementById("inputproduct").focus();
                        } else {
                            $("#inputproduct").parent().parent().removeClass("has-error");
                            $("#inputproduct").parent().parent().addClass("has-success");
                            $("#helpBlock_add_inputProduct").text("操作正确！");
                            //$("#helpBlock_add_inputPCBA").hide();
                            document.getElementById("inputpcba").focus();
                        }


                    }

                    //PCBA SN号检查
                    function check() {
                        if (event.keyCode == 13) {
                            var PcbaSN = $("#inputpcba").val();


                            var options=$("#showDay option:selected"); //获取选中的项
                            var option= options.val();
                            $.ajax
                            ({
                                url: "/lenovolink/selectLenovosnExist",
                                type: "GET",
                                data: "pcbaSN=" + PcbaSN,
                                success: function (result) {
                                    if (result.code == 100) {
                                        var reg;
                                        reg = /^8S/;
                                        var data = new Date();
                                        var year = data.getFullYear();
                                        var month = data.getMonth() + 1;
                                        //  var date = data.getDate().toString();
                                        var m;

                                        var d;
                                        switch (month) {
                                            case  1:m='1';break;
                                            case  2:m='2';break;
                                            case  3:m='3';break;
                                            case  4:m='4';break;
                                            case  5:m='5';break;
                                            case  6:m='6';break;
                                            case  7:m='7';break;
                                            case  8:m='8';break;
                                            case  9:m='9';break;
                                            case  10:m='A';break;
                                            case  11:m='B';break;
                                            default:m='C';break;
                                        }
                                        switch (option) {
                                            case  "1":
                                                d = "1";
                                                break;
                                            case  "2":
                                                d = "2";
                                                break;
                                            case  "3":
                                                d = "3";
                                                break;
                                            case  "4":
                                                d = "4";
                                                break;
                                            case  "5":
                                                d = "5";
                                                break;
                                            case  "6":
                                                d = "6";
                                                break;
                                            case  "7":
                                                d = "7";
                                                break;
                                            case  "8":
                                                d = "8";
                                                break;
                                            case  "9":
                                                d = "9";
                                                break;
                                            case  "10":
                                                d = "A";
                                                break;
                                            case  "11":
                                                d = "B";
                                                break;
                                            case  "12":
                                                d = "C";
                                                break;
                                            case  "13":
                                                d = "D";
                                                break;
                                            case  "14":
                                                d = "E";
                                                break;
                                            case  "15":
                                                d = "F";
                                                break;
                                            case  "16":
                                                d = "G";
                                                break;
                                            case  "17":
                                                d = "H";
                                                break;
                                            case  "18":
                                                d = "J";
                                                break;
                                            case  "19":
                                                d = "K";
                                                break;
                                            case  "20":
                                                d = "L";
                                                break;
                                            case  "21":
                                                d = "M";
                                                break;
                                            case  "22":
                                                d = "N";
                                                break;
                                            case  "23":
                                                d = "P";
                                                break;
                                            case  "24":
                                                d = "R";
                                                break;
                                            case  "25":
                                                d = "S";
                                                break;
                                            case  "26":
                                                d = "T";
                                                break;
                                            case  "27":
                                                d = "V";
                                                break;
                                            case  "28":
                                                d = "W";
                                                break;
                                            case  "29":
                                                d = "X";
                                                break;
                                            case  "30":
                                                d = "Y";
                                                break;
                                            case  "31":
                                                d = "Z";
                                                break;
                                        }
                                        if (!reg.test($("#inputpcba").val())) {
                                            //输入框变红//
                                            // $("#helpBlock_add_inputPCBA").parent().parent().removeClass("has-success");
                                            $("#helpBlock_add_inputPCBA").parent().parent().addClass("has-error");
                                            //输入框下面显示红色提示信息
                                            $("#helpBlock_add_inputPCBA").text("错误：联想8S码首码错误");
                                            document.getElementById("operation").value = "错误：联想8S码首码错误";
                                            addlink();
                                            add();
                                            playSound();
                                            alert("错误：联想8S码首码错误");
                                            mistake();
                                            jisuanmistake();
                                            document.getElementById("inputproduct").value = "";
                                            document.getElementById("inputpcba").value = "";
                                            document.getElementById("inputproduct").focus();
                                        } else if ($("#inputpcba").val().substring(2, 12) != $("#LXPN").val()) {
                                            // $("#helpBlock_add_inputPCBA").parent().parent().removeClass("has-success");
                                            $("#helpBlock_add_inputPCBA").parent().parent().addClass("has-error");
                                            //输入框下面显示红色提示信息
                                            $("#helpBlock_add_inputPCBA").text("错误：联想PN号错误");
                                            document.getElementById("operation").value = "错误：联想PN号错误";
                                            addlink();
                                            add();
                                            playSound();
                                            alert("错误：联想PN号首码错误");
                                            mistake();
                                            jisuanmistake();
                                            document.getElementById("inputproduct").value = "";
                                            document.getElementById("inputpcba").value = "";
                                            document.getElementById("inputproduct").focus();
                                        } else if ($("#inputpcba").val().charAt(16) != year.toString().charAt(3)) {
                                            $("#inputproduct").parent().parent().addClass("has-error");
                                            //输入框下面显示红色提示信息
                                            $("#helpBlock_add_inputPCBA").text("联想8S码年份错误,请重新输入！");
                                            document.getElementById("operation").value = "错误：联想8S码年份错误";
                                            //添加数据
                                            addlink();
                                            add();
                                            playSound();
                                            alert("错误：联想8S码年份错误");
                                            mistake();
                                            jisuanmistake();
                                            document.getElementById("inputproduct").value = "";
                                            document.getElementById("inputpcba").value = "";
                                            document.getElementById("inputproduct").focus();
                                        } else if ($("#inputpcba").val().charAt(17) != m) {
                                            $("#inputproduct").parent().parent().addClass("has-error");
                                            //输入框下面显示红色提示信息
                                            $("#helpBlock_add_inputPCBA").text("联想8S码月份错误,请重新输入！");
                                            document.getElementById("operation").value = "错误：联想8S码月份错误";
                                            //添加数据
                                            addlink();
                                            add();
                                            playSound();
                                            alert("错误：联想8S码月份错误");
                                            mistake();
                                            jisuanmistake();
                                            document.getElementById("inputproduct").value = "";
                                            document.getElementById("inputpcba").value = "";
                                            document.getElementById("inputproduct").focus();
                                        } else if ($("#inputpcba").val().charAt(18) != d) {

                                            $("#inputproduct").parent().parent().addClass("has-error");
                                            //输入框下面显示红色提示信息
                                            $("#helpBlock_add_inputPCBA").text("联想8S码日份错误,请重新输入！");
                                            document.getElementById("operation").value = "错误：联想8S码日份错误";
                                            //添加数据
                                            addlink();
                                            add();
                                            playSound();
                                            alert("错误：联想8S码日份错误");
                                            mistake();
                                            jisuanmistake();
                                            document.getElementById("inputproduct").value = "";
                                            document.getElementById("inputpcba").value = "";
                                            document.getElementById("inputproduct").focus();

                                        } else {
                                            //  $("#inputpcba").parent().parent().removeClass("has-error");
                                            $("#inputpcba").parent().parent().addClass("has-success");
                                            $("#helpBlock_add_inputPCBA").text("操作正确！");
                                            document.getElementById("operation").value = "正确";
                                            //document.getElementById("getmoremain").value = oInput['moremain'].value - 1;
                                            addlink();
                                            add();
                                            //updatemoremain();
                                            document.getElementById("inputproduct").value = "";
                                            document.getElementById("inputpcba").value = "";
                                            real();
                                            jisuanreal();
                                            document.getElementById("inputproduct").focus();
                                        }
                                    } else {
                                        $("#inputpcba").parent().parent().removeClass("has-success");
                                        $("#inputpcba").parent().parent().addClass("has-error");
                                        $("#helpBlock_add_inputPCBA").text("PCBA SN号重复");
                                        document.getElementById("operation").value = "错误：联想号重复";
                                        addlink();
                                        add();
                                        playSound();
                                        alert("错误：联想号重复");
                                        document.getElementById("inputpcba").value = "";
                                        mistake();
                                        jisuanmistake();
                                        document.getElementById("inputpcba").focus();
                                    }


                                }
                            });
                        }
                    }


                    ////光标停在产品SN号时判断前面值是否输入
                    function checkNull1() {
                        if (document.getElementById("getmoid").value == "" || document.getElementById("getmonum").value == "" || document.getElementById("getmoremain").value == "") {
                            playSound();
                            alert("请检查工单信息！");
                            document.getElementById("getmoid").focus();

                        } else if (document.getElementById("getdevicename").value == "") {
                            playSound();
                            alert("请输入机种名称！");
                            document.getElementById("getdevicename").focus();

                        } else {
                            document.getElementById("inputproduct").focus();
                        }
                    }

                    //光标停在PCBA SN号时判断是否输入产品SN号
                    function checkNull() {
                        if (document.getElementById("inputproduct").value == "") {
                            playSound();
                            alert("请输入产品SN号！");
                            document.getElementById("inputproduct").focus();
                        }
                    }


                    //播放声音
                    function playSound() {
                        var borswer = window.navigator.userAgent.toLowerCase();
                        if (!!window.ActiveXObject || "ActiveXObject" in window) {
                            //IE内核浏览器
                            var OSPlayer = new ActiveXObject("WMPLayer.OCX");
                            OSPlayer.url = "http://www.xmf119.cn/static/admin/sounds/notify.wav";
                            OSPlayer.controls.play();
                        } else {
                            //非IE内核浏览器
                            var strAudio = "<audio id='audioPlay' src='http://www.xmf119.cn/static/admin/sounds/notify.wav' hidden='true'>";
                            if ($("body").find("audio").length <= 0)
                                $("body").append(strAudio);
                            var audio = document.getElementById("audioPlay");
                            //浏览器支持 audion
                            audio.play();
                        }
                    }

                    y = 0;

                    function real() {
                        y = y + 1;
                        document.getElementById("real").value = y;
                    }

                    z = 0;

                    function mistake() {
                        z = z + 1;
                        document.getElementById("mistake").value = z;
                    }

                    function jisuanreal() {
                        document.getElementById("realpercent").value = Number(parseFloat(document.getElementById("real").value) / parseFloat(document.getElementById("mubiao").value) * 100).toFixed(1) + "%";
                    }

                    function jisuanmistake() {
                        document.getElementById("mistakepercent").value = Number(parseFloat(document.getElementById("mistake").value) / parseFloat(document.getElementById("mubiao").value) * 100).toFixed(1) + "%";
                    }

                    function count() {
                        myVar = setInterval(colc, 17000);
                    }

                    function beginwork() {
                        if (confirm("请确认开始计时")) {
                            isClick = true;//改变标识符的状态值
                            count();
                        }
                    }

                    function pausework() {

                        if (confirm("请确认暂停工作")) {
                            isClick = false;
                            cancel();
                        }

                    }

                    var line = "${line_number}";
                    x = 0;

                    function colc() {
                        x = x + 1;
                        document.getElementById("mubiao").value = x;
                        document.getElementById("realpercent").value = Number(parseFloat(document.getElementById("real").value) / parseFloat(document.getElementById("mubiao").value) * 100).toFixed(1) + "%";
                        document.getElementById("mistakepercent").value = Number(parseFloat(document.getElementById("mistake").value) / parseFloat(document.getElementById("mubiao").value) * 100).toFixed(1) + "%";
                    }

                    function cancel() {
                        clearInterval(myVar);
                    }

                    function test() {
                        if (event.keyCode == 13) {
                            alert("123");
                        }
                    }

                    function one() {
                        alert("test");
                        window.location.href = "${pageContext.request.contextPath}/link/set";
                    }

                </script>
            </div><!-- /.panel panel-success -->
        </div><!-- /.dept_info -->
    </div><!-- /.hrms_dept_body -->

    <!-- 尾部-->
    <%@ include file="./commom/foot.jsp" %>

</div><!-- /.hrms_dept_container -->


</body>


</html>
