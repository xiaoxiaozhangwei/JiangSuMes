<%--
  Created by IntelliJ IDEA.
  User: guyanbo
  Date: 2019/10/29
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>检查站</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">

    <style>

        #model1 {
            width: 600px;
            height: 60px;
            margin-left: -250px;
            margin-top: -150px;
            position: fixed;
            top: 50%;
            left: 50%;
            z-index: 200;
        }

        #outer {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 100;
            padding-top: 10%;
            text-align: center;
            background-repeat: no-repeat;
            background-position: center center;
            background-color: #000;
            background-color: rgba(0, 0, 0, 0.5);
            filter: alpha(opacity=50);
            display: none;
        }
    </style>
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
                        <h4 style="font-size:40px; color: #ff5a19;">外观检查站</h4></div>
                    <div id="result" align="right"></div>
                    <div id="form">
                        <form action="" class="check">
                            <table class="table">
                                <tr>
                                    <td>
                                        工单号码：
                                        <select name="moId" id="getmoid" onchange="change()">
                                            <option value="0">请选择</option>
                                        </select>
                                        <div>
                                            该工单是否需要经过CDI测试?
                                            <input type="radio" name="cdi" value="yes">是</input>
                                            <input type="radio" name="cdi" value="no">否</input>
                                        </div>
                                    </td>
                                    <td>机种名称：<input type="text" name="model" id="getmodel"></td>
                                    <td>检验员：<input type="text" name="operator" readonly value="${name}"></td>
                                    <td>线别：
                                        <select name="xianbie" id="xianbie">
                                            <option value="0">--请选择--</option>
                                            <option value="线别1">线别1</option>
                                            <option value="线别2">线别2</option>
                                            <option value="线别3">线别3</option>
                                            <option value="线别4">线别4</option>
                                            <option value="线别5">线别5</option>
                                            <option value="线别6">线别6</option>
                                            <option value="NPI线">NPI线</option>
                                        </select>
                                    </td>
                                </tr>

                                <tr>
                                    <td>工单数量：<input type="text" name="monum" id="getmonum" readonly></td>
                                    <td>已完工数：<input type="text" id="getmofinish" readonly/></td>
                                    <td>未完工数：<input type="text" name="moremain" id="getmoremain" readonly/></td>
                                    <td>SN长度：<input type="text" id="snlength"></td>
                                </tr>
                                <tr>
                                    <td>OC4 FW:<input type="text" name="FW" id="FW"></td>
                                    <td>目标产值：<input type="text" id="mubiao" value="0"></td>
                                    <td>达成率：<input type="text" id="realpercent"></td>
                                    <td>周：<input type="text" id="week"></td>

                                </tr>
                            </table>
                            <div style="width:100%; border:0.5px solid gray;"></div>
                            <br>

                            <div style="float: left;height: 100px ">
                                <input type="button" style="background-color: darkgreen;color: white;width: 80px"
                                       value="良品" class="btn" id="rightclick">
                                <br><br>
                                <input type="button" style="background-color: brown;color: white;width: 80px"
                                       value="不良品" class="btn " id="wrongclick">
                            </div>

                            <div style="margin-left:15%;width: 800px " id="right">
                                请输入待检验产品SN:
                                <input type="text" style="color: black" name="productSN1" id="inputproduct1"
                                       onfocus="checkNull1()" onkeydown="insertcheck1()">
                                <span id="checkCDISpan" style="display:none; color:red">该SN未进行CDI测试，请进行CDI测试</span>
                            </div>

                            <div style="margin-left:15%;width:800px" id="wrong">
                                <a style="color: #c43333">请输入待检验产品SN:</a>
                                <input type="text" style="color: black" name="productSN2" id="inputproduct2"
                                       onfocus="checkNull1()">
                                &nbsp;&nbsp;&nbsp;
                                <a style="color: #c43333">不良原因：</a>
                                <select name="operation" style="color: black" id="operation" onchange="insertcheck2()">
                                    <option value="0">——请选择原因——</option>
                                    <option value="螺丝孔不良">螺丝孔不良</option>
                                    <option value="SATA接口不良">SATA接口不良</option>
                                    <option value="组装不良">组装不良</option>
                                    <option value="上面盖不良">上面盖不良</option>
                                    <option value="下面盖不良">下面盖不良</option>
                                    <option value="S/N标签不良">S/N标签不良</option>
                                    <option value="防拆标签">防拆标签</option>
                                    <option value="其他">其他</option>
                                </select>
                            </div>
                            <div style="margin-left:75%">
                                <input type="button" value="开始" id="begin" onclick="beginwork()"
                                       style="background-color: #025ba9;color: white;width: 80px" class="btn">
                                &nbsp;
                                <input type="button" value="暂停" id="finish" onclick="pausework()"
                                       style="background-color: #bb7800;color: white;width: 80px" class="btn">
                            </div>
                        </form>
                        <br>
                        <table class="table table-bordered table-hover" style="background-color: #ffffff" id="message">
                            <thead style="font-size: 22px;">
                            <tr>
                                <th></th>
                                <th>检验数量</th>
                                <th>良品数</th>
                                <th>不良品数</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>数量</td>
                                <td>0</td>
                                <td>0</td>
                                <td style="color: red">0</td>
                            </tr>
                            <tr>
                                <td>比例</td>
                                <td></td>
                                <td></td>
                                <td style="color: red"></td>
                            </tr>
                            </tbody>
                            <tr style="display: none">
                                <td colspan="4" style="color: dimgray;font-size: 25px;text-align: -moz-left">不良明细记录</td>
                            </tr>
                            <thead style="font-size: 22px;">
                            <tr style="display: none">
                                <th>编号</th>
                                <th>不良现象</th>
                                <th>数量</th>
                                <th>比例</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr style="display: none">
                                <td>A</td>
                                <td>螺丝孔不良</td>
                                <td>0</td>
                                <td></td>
                            </tr>
                            <tr style="display: none">
                                <td>B</td>
                                <td>SATA接口不良</td>
                                <td>0</td>
                                <td></td>
                            </tr>
                            <tr style="display: none">
                                <td>C</td>
                                <td>组装不良</td>
                                <td>0</td>
                                <td></td>
                            </tr>
                            <tr style="display: none">
                                <td>D</td>
                                <td>上面盖不良</td>
                                <td>0</td>
                                <td></td>
                            </tr>
                            <tr style="display: none">
                                <td>E</td>
                                <td>下面盖不良</td>
                                <td>0</td>
                                <td></td>
                            </tr>
                            <tr style="display: none">
                                <td>F</td>
                                <td>S/N标签不良</td>
                                <td>0</td>
                                <td></td>
                            </tr>
                            <tr style="display: none">
                                <td>G</td>
                                <td>防拆标签</td>
                                <td>0</td>
                                <td></td>
                            </tr>
                            <tr style="display: none">
                                <td>H</td>
                                <td>其他</td>
                                <td>0</td>
                                <td></td>
                            </tr>
                            </tbody>
                        </table>
                        <div style="height: 300px;overflow-x: auto;overflow-y: auto;" id="tab">
                            <table class="table table-bordered table-hover" style="background-color: #ffffff">
                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>产品SN号</th>
                                    <th>机种名称</th>
                                    <th>盘片异常</th>
                                    <th>操作信息</th>
                                    <th>检验员</th>
                                    <th>工单号</th>
                                    <th>操作时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>

                            </table>
                            <div id="outer">
                                <div class="alert alert-success" id="model1">操作成功！</div>
                            </div>
                        </div>
                        <script type="text/javascript">
                            //countSecond();
                            var oInput = document.getElementById("form").getElementsByTagName("input");
                            var oTable = document.getElementsByTagName("table")[1];
                            var oTable2 = document.getElementsByTagName("table")[2];
                            var rows = oTable.rows;
                            var tBodies = oTable2.tBodies[0];
                            var isClick = false;
                            var myVar;
                            var id = 1;
                            var operation;

                            $(document).ready(function () {
                                $("#right").hide();
                                $("#wrong").hide();
                                //下拉框回显工单号
                                $.ajax({
                                    url: "${pageContext.request.contextPath}/gd/selected",
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

                                percent();

                            });

                            //正确信息添加到表格
                            function right_add_table(operation) {
                                var mai = document.getElementById('tab');
                                //1.创建行
                                var tr = document.createElement("tr");
                                //2.添加行到表格
                                tBodies.appendChild(tr);
                                //3.添加单元格
                                tr.appendChild(createNode("td", id));
                                tr.appendChild(createNode("td", oInput['productSN1'].value));
                                tr.appendChild(createNode("td", oInput['model'].value));
                                tr.appendChild(createNode("td", "无"));
                                tr.appendChild(createNode("td", operation));
                                tr.appendChild(createNode("td", oInput['operator'].value));
                                tr.appendChild(createNode("td", $("#getmoid").val()));
                                tr.appendChild(createNode("td", showTime()));

                                id++;
                                mai.scrollTop = mai.scrollHeight;//通过设置滚动高度
                            }

                            //表格添加列
                            function createNode(element, text) {
                                var td = document.createElement(element);
                                td.innerHTML = text;
                                return td;
                            }

                            //隐藏和显示层
                            $("#rightclick").click(function () {
                                $("#wrong").hide();
                                $("#right").show();
                                expandall1();
                            });
                            $("#wrongclick").click(function () {
                                $("#right").hide();
                                $("#wrong").show();
                                expandall();
                            });

                            //显示百分比
                            function percent() {
                                if (rows[2].cells[1].innerHTML != "0") {
                                    rows[3].cells[2].innerHTML = Number(parseFloat(rows[2].cells[2].innerHTML) / parseFloat(rows[2].cells[1].innerHTML) * 100).toFixed(1) + "%";
                                    rows[3].cells[3].innerHTML = Number(parseFloat(rows[2].cells[3].innerHTML) / parseFloat(rows[2].cells[1].innerHTML) * 100).toFixed(1) + "%";
                                } else {
                                    rows[3].cells[2].innerHTML = "0%";
                                    rows[3].cells[3].innerHTML = "0%";
                                }
                                if (rows[2].cells[3].innerHTML != "0") {
                                    rows[5].cells[3].innerHTML = Number(parseFloat(rows[5].cells[2].innerHTML) / parseFloat(rows[2].cells[3].innerHTML) * 100).toFixed(1) + "%";
                                    rows[6].cells[3].innerHTML = Number(parseFloat(rows[6].cells[2].innerHTML) / parseFloat(rows[2].cells[3].innerHTML) * 100).toFixed(1) + "%";
                                    rows[7].cells[3].innerHTML = Number(parseFloat(rows[7].cells[2].innerHTML) / parseFloat(rows[2].cells[3].innerHTML) * 100).toFixed(1) + "%";
                                    rows[8].cells[3].innerHTML = Number(parseFloat(rows[8].cells[2].innerHTML) / parseFloat(rows[2].cells[3].innerHTML) * 100).toFixed(1) + "%";
                                    rows[9].cells[3].innerHTML = Number(parseFloat(rows[9].cells[2].innerHTML) / parseFloat(rows[2].cells[3].innerHTML) * 100).toFixed(1) + "%";
                                    rows[10].cells[3].innerHTML = Number(parseFloat(rows[10].cells[2].innerHTML) / parseFloat(rows[2].cells[3].innerHTML) * 100).toFixed(1) + "%";
                                    rows[11].cells[3].innerHTML = Number(parseFloat(rows[11].cells[2].innerHTML) / parseFloat(rows[2].cells[3].innerHTML) * 100).toFixed(1) + "%";
                                    rows[12].cells[3].innerHTML = Number(parseFloat(rows[12].cells[2].innerHTML) / parseFloat(rows[2].cells[3].innerHTML) * 100).toFixed(1) + "%";
                                } else {
                                    rows[5].cells[3].innerHTML = "0%";
                                    rows[6].cells[3].innerHTML = "0%";
                                    rows[7].cells[3].innerHTML = "0%";
                                    rows[8].cells[3].innerHTML = "0%";
                                    rows[9].cells[3].innerHTML = "0%";
                                    rows[10].cells[3].innerHTML = "0%";
                                    rows[11].cells[3].innerHTML = "0%";
                                    rows[12].cells[3].innerHTML = "0%";
                                }
                                if (parseFloat(document.getElementById("mubiao").value) == 0) {
                                    document.getElementById("realpercent").value = "0%";
                                } else {
                                    document.getElementById("realpercent").value = Number(parseFloat(rows[2].cells[1].innerHTML) / parseFloat(document.getElementById("mubiao").value) * 100).toFixed(1) + "%";
                                }
                                window.setTimeout("percent()", 500);

                            }

                            //正确信息操作
                            function insertcheck1() {
                                if (event.keyCode == 13) {
                                    document.getElementById("inputproduct1").blur();
                                    var productionSN = $("#inputproduct1").val();
                                    var model = $("#getmodel").val();
                                    var l = parseInt(document.getElementById("snlength").value);
                                    var w = document.getElementById("week").value;
                                    var moid = $("#getmoid").val();

                                    //取消link阶段的检查
                                    /*//判断产品SN号是否经过组装站
                                       $.ajax({
                                           url: "/check/production/"+ productionSN ,
                                           type: "GET",
                                           success: function (result) {
                                               if (result.code == 100) {
                                                   //判断产品SN号是否重复
                                                   $.ajax({
                                                       url:"/check/selectProByName/"+productionSN,
                                                       type:"GET",
                                                       success:function (result) {
                                                           if (result.code==100){
                                                               if (productionSN==model){
                                                                   alert("输入信息为机种名称！");
                                                                   operation="输入信息为机种名称";
                                                                   right_add_table(operation);
                                                                   $("#inputproduct1").val("");
                                                                   document.getElementById("inputproduct1").focus();
                                                               }else if(productionSN.length!=17){
                                                                   alert("产品SN号位数错误！");
                                                                   operation="产品SN号位数错误";
                                                                   right_add_table(operation);
                                                                   $("#inputproduct1").val("");
                                                                   document.getElementById("inputproduct1").focus();
                                                               }else {
                                                                   //正确信息添加数据库
                                                                   $.ajax({
                                                                       url: "/check/insert1",
                                                                       type: "POST",
                                                                       data: $(".check").serialize(),
                                                                   });
                                                                   //更改表格中的值
                                                                   rows[2].cells[1].innerHTML = parseInt(rows[2].cells[1].innerHTML) + 1;
                                                                   rows[2].cells[2].innerHTML = parseInt(rows[2].cells[2].innerHTML) + 1;
                                                                   //percent();
                                                                   updatemoremain();
                                                                   operation="正确";
                                                                   right_add_table(operation);
                                                                   document.getElementById("inputproduct1").value = "";
                                                                   document.getElementById("inputproduct1").focus();
                                                               }
                                                           }else {
                                                               alert("该产品SN号已添加！");
                                                               operation="该产品SN号已添加";
                                                               right_add_table(operation);
                                                               document.getElementById("inputproduct1").value = "";
                                                               document.getElementById("inputproduct1").focus();
                                                           }
                                                       }
                                                   });
                                               } else {
                                                   alert("产品未经过组装站！");
                                                   operation="产品未经过组装站";
                                                   right_add_table(operation);
                                                   document.getElementById("inputproduct1").value = "";
                                                   document.getElementById("inputproduct1").focus();
                                               }
                                           }
                                       });
                                       */


                                    // 判断产品是否经过cdi，11月2日新增功能
                                    var cdi = $('input[name="cdi"]:checked').val();
                                    var cdicheck = false;
                                    // alert(cdi);
                                    if (cdi == "yes") {
                                        cdicheck = true;
                                    }
                                    //判断产品SN号是否重复 正常流程
                                    $.ajax({
                                        url: "/check/selectProByName/" + productionSN,
                                        type: "GET",
                                        data: {"moid": moid},
                                        success: function (result) {
                                            if (result.code == 100) {
                                                if (productionSN == model) {
                                                    alert("输入信息为机种名称！");
                                                    operation = "输入信息为机种名称";
                                                    right_add_table(operation);
                                                    $("#inputproduct1").val("");
                                                    document.getElementById("inputproduct1").focus();
                                                } else if (productionSN.length != l) {
                                                    alert("产品SN号位数错误！");
                                                    operation = "产品SN号位数错误";
                                                    right_add_table(operation);
                                                    $("#inputproduct1").val("");
                                                    document.getElementById("inputproduct1").focus();
                                                } else if (productionSN.substring(2, 4) != w) {
                                                    alert("产品SN号周期错误！");
                                                    operation = "产品SN号周期错误";
                                                    right_add_table(operation);
                                                    $("#inputproduct1").val("");
                                                    document.getElementById("inputproduct1").focus();
                                                } else {
                                                    if (cdicheck == true) {
                                                        checkCDI();
                                                    } else {
                                                        addCheck();
                                                    }
                                                }
                                            } else {
                                                alert("该产品SN号已添加！");
                                                operation = "该产品SN号已添加";
                                                right_add_table(operation);
                                                document.getElementById("inputproduct1").value = "";
                                                document.getElementById("inputproduct1").focus();
                                                document.getElementById("checkCDISpan").style.display = "none";
                                            }
                                        }
                                    });

                                }


                            }


                            function checkCDI() {
                                var productionSN = $("#inputproduct1").val();
                                var mo = document.getElementById("getmoid").value;
                                $.ajax({
                                    url: "/check/passCDI/" + productionSN,
                                    type: "GET",
                                    data: "mo=" + mo,
                                    success: function (result) {
                                        if (result.code == 200) {
                                            alert("未经过CDI");
                                            document.getElementById("checkCDISpan").style.display = "block";
                                            operation = "SN未经过CDI测试";
                                            right_add_table(operation);
                                            $("#inputproduct1").val("");
                                            document.getElementById("inputproduct1").focus();
                                        } else {

                                            document.getElementById("checkCDISpan").style.display = "none";
                                            addCheck();
                                        }
                                    }
                                });

                            }


                            function addCheck() {

                                //正确信息添加数据库
                                $.ajax({
                                    url: "/check/insert1",
                                    type: "POST",
                                    data: $(".check").serialize(),
                                });
                                //更改表格中的值
                                rows[2].cells[1].innerHTML = parseInt(rows[2].cells[1].innerHTML) + 1;
                                rows[2].cells[2].innerHTML = parseInt(rows[2].cells[2].innerHTML) + 1;
                                //percent();
                                updatemoremain();
                                operation = "正确";
                                right_add_table(operation);
                                document.getElementById("inputproduct1").value = "";
                                document.getElementById("inputproduct1").focus();


                            }

                            //错误信息操作
                            function insertcheck2() {
                                var productionSN = $("#inputproduct2").val();
                                //    $.ajax({
                                //        url: "/check/production" + "/" + productionSN ,
                                //        type: "GET",
                                //        success: function (result) {
                                //        if (result.code == 100) {
                                //判断产品SN号是否重复
                                $.ajax({
                                    url: "/check/selectProByName/" + productionSN,
                                    type: "GET",
                                    success: function (result) {
                                        if (result.code == 100) {
                                            //添加到数据库
                                            $.ajax({
                                                url: "/check/insert2",
                                                type: "POST",
                                                data: $(".check").serialize(),
                                            });
                                            //更改表格数据
                                            rows[2].cells[1].innerHTML = parseInt(rows[2].cells[1].innerHTML) + 1;
                                            rows[2].cells[3].innerHTML = parseInt(rows[2].cells[3].innerHTML) + 1;
                                            for (var i = 5; i <= 12; i++) {
                                                if (rows[i].cells[1].innerHTML == document.getElementById("operation").value) {
                                                    rows[i].cells[2].innerHTML = parseInt(rows[i].cells[2].innerHTML) + 1;
                                                }
                                            }
                                            //percent();
                                            document.getElementById("inputproduct2").value = "";
                                            document.getElementById("operation").value = "0";
                                            document.getElementById("inputproduct2").focus();
                                        } else {
                                            alert("该产品SN号已添加！");
                                            document.getElementById("inputproduct2").value = "";
                                            document.getElementById("operation").value = "0";
                                            document.getElementById("inputproduct2").focus();
                                        }
                                    }
                                });
                                //          }
                                //           else {
                                //               alert("产品未经过组装站！");
                                //               document.getElementById("inputproduct2").value = "";
                                //               document.getElementById("operation").value="0";
                                //               document.getElementById("inputproduct2").focus();
                                //           }
                                //      }
                                //   });
                            }

                            //回显工单信息
                            function updatemoid() {
                                var moid = $("#getmoid").val();
                                $.ajax({
                                    url: "/gd/getMoId/" + moid,
                                    type: "GET",
                                    success: function (result) {
                                        if (result.code == 100) {
                                            var gd = result.extendInfo.gongdan;
                                            $("#getmonum").val(gd.moNum);
                                            $("#getmofinish").val(gd.moNum - gd.moRemain1);
                                            $("#getmoremain").val(gd.moRemain1);
                                            $("#getmodel").val(gd.model);
                                        } else {
                                        }
                                    }

                                });

                                if (document.getElementById("getmoremain").value === "0") {
                                    $("#getmofinish").val("");
                                    $("#getmoid").val("0");
                                    $("#getmonum").val("");
                                    $("#getmoremain").val("");
                                    $("#getmodel").val("");
                                    //$("#FW").val("");
                                    alert("该工单已结束,重新输入工单号");
                                    document.getElementById("getmoid").focus();

                                    //window.location.href = "/xt/check";
                                }
                                window.setTimeout("updatemoid()", 500);
                            }

                            //工单下拉框操作
                            function change() {
                                updatemoid();
                                document.getElementById("FW").focus();
                                /*
                                var first = $("#getmoid option:selected").val().substring(0, 1);
                                 if (first != "P") {
                                     alert("该工单非P开头，请确认是否需要经过CDI测试！");
                                 }else{
                                     $('input:radio:first').attr('checked', 'true');
                                 }
                                 */
                            }

                            //添加信息正确则工单剩余量-1
                            function updatemoremain() {
                                $.ajax({
                                    url: "/check/merman",
                                    type: "POST",
                                    data: $("#getmoid"),
                                });
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

                            //产品SN号字符串检查
                            function checkSN() {
                                var input = document.getElementById("inputproduct").value;
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
                                } else {
                                    //得到年数周数
                                    var year = date1.getFullYear();
                                    var week = Math.ceil((d + 1) / 7);
                                }
                                if (document.getElementById("getmoremain").value == "0") {
                                    playSound();
                                    alert("该工单已结束,重新输入工单号");
                                    window.location.href = "/xt/zuzhuang";

                                }

                                /**
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
                            document.getElementById("inputproduct").value="";
                            //document.getElementById("operation").value="";
                            document.getElementById("inputproduct").focus();
                            //$("#helpBlock_add_inputProduct").hide();

                        }
                                 else if(input.substring(2, 4)!==week.toString())
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
                            document.getElementById("inputproduct").value="";
                            document.getElementById("inputproduct").focus();
                        }

                                 else if(input.charAt(4)!='0')
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
                            document.getElementById("inputproduct").value="";
                            document.getElementById("inputproduct").focus();
                        }
                                 */
                                else {
                                    $("#inputproduct").parent().parent().removeClass("has-error");
                                    $("#inputproduct").parent().parent().addClass("has-success");
                                    $("#helpBlock_add_inputProduct").text("操作正确！");
                                    //$("#helpBlock_add_inputPCBA").hide();
                                    document.getElementById("inputpcba").focus();
                                }
                            }


                            //光标停在产品SN号时判断前面值是否输入
                            function checkNull1() {
                                if (document.getElementById("getmoid").value == "0" || document.getElementById("getmonum").value == "" || document.getElementById("getmoremain").value == "") {
                                    alert("请检查工单信息！");
                                    document.getElementById("getmoid").focus();
                                } else if (document.getElementById("FW").value == "") {
                                    alert("请输入FW信息！");
                                    document.getElementById("FW").focus();
                                } else if (isClick == false) {
                                    alert("请点击开始键！");
                                    document.getElementById("begin").focus();
                                } else if ($("#xianbie").val() == "0") {
                                    alert("请选择线别");
                                    document.getElementById("xianbie").focus();
                                } else {
                                    document.getElementById("inputproduct").focus();
                                }
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

                            function colc() {
                                document.getElementById("mubiao").value = parseInt($("#mubiao").val()) + 1;
                                //document.getElementById("realpercent").value= Number(parseFloat(rows[2].cells[1].innerHTML)/parseFloat(document.getElementById("mubiao").value)*100).toFixed(1)+"%";


                            }

                            function cancel() {
                                clearInterval(myVar);
                            }

                            //表格伸缩
                            function expandall() {
                                var t = document.getElementById('message');
                                t.rows[1].style.display = 'block'
                                for (var i = 4; i <= 12; i++) {

                                    t.rows[i].style.display = 'block'
                                }

                            }

                            function expandall1() {
                                var t = document.getElementById('message');
                                t.rows[1].style.display = 'none'
                                for (var i = 4; i <= 12; i++) {

                                    t.rows[i].style.display = 'none'
                                }
                            }

                            function show() {
                                document.getElementById("outer").style.display = "block";
                                setTimeout(hide, 2000);
                            }

                            function hide() {
                                document.getElementById("outer").style.display = "none";
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
