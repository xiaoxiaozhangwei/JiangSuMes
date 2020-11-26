<%--
  Created by IntelliJ IDEA.
  User: guyanbo
  Date: 2020/7/20
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SN标签打印</title>
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
                <div class="panel-heading">
                    <div style="font-size: 20px">
                        <div align="center"><h4 style="font-size:40px; color: #ff5a19;">SN标签转印</h4></div>
                        <div id="result" align="right"></div>

                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <td>
                                    工单号：<input type="text" id="getmoid" onchange="selectchange()">
                                </td>
                                <td>
                                    工单数量：<input type="text" id="getmonum" readonly>
                                </td>
                                <td>
                                    已完工数：<input type="text" id="getmoremain" readonly>
                                </td>
                                <td>
                                    机种名称：<input type="text" id="model" readonly>
                                </td>
                                <td>
                                    操作员：<input type="text" value="${name}" id="operator" readonly>
                                </td>
                            </tr>
                            <tr>

                                <td>FW：<input type="text" id="fw"></td>
                                <td>SN长度：<input type="text" id="sn_length"></td>
                                <td colspan="2">
                                    产品SN：<input type="text" id="productsn" onkeydown="changesnenter()">
                                    <span id="right" style="color: #1e7e1b"></span>
                                    <span id="wrong" style="color: #c43333"></span>
                                </td>
                            </tr>
                            </thead>

                        </table>
                    </div>

                </div>

                <div style="height: 450px;overflow-x: auto;overflow-y: auto;" id="tab">
                    <table class="table table-bordered table-hover" id="myTable">
                        <thead>
                        <th>编号</th>
                        <th>产品 SN号</th>
                        <th>机种名称</th>
                        <th>工单号</th>
                        <th>操作信息</th>
                        <th>操作员</th>
                        <th>作业时间</th>
                        </thead>
                        <tbody id="table"></tbody>
                    </table>
                </div>
                <script type="text/javascript">

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


                    //回显工单信息
                    function updatemoid() {
                        var moid = $("#getmoid").val();
                        $.ajax({
                            url: "/gd/getMoId/" + moid,
                            type: "GET",
                            success: function (result) {
                                if (result.code == 100) {
                                    var gd = result.extendInfo.gongdan;
                                    $("#model").val(gd.model);
                                    $("#getmonum").val(gd.moNum);
                                    //$("#getmoremain").val(gd.moNum - gd.moRemainlabel);
                                } else {
                                }
                            }
                        });
                    }
                    //查询已完工数并回显
                    function getmoremain() {
                        var moid=$("#getmoid").val();
                        $.ajax({
                            url:"/changesn/getmoremain",
                            type:"GET",
                            data:{"moid":moid},
                            success:function (res) {
                                if(res.code==100){
                                    var c=res.extendInfo.c;
                                    $("#getmoremain").val(c);
                                }
                            }
                        })
                    }

                    //工单下拉框选择
                    function selectchange() {
                        updatemoid();
                        getmoremain();
                    }

                    var operation;

                    function changesnenter() {
                        if (event.keyCode===13){
                            var sn_length=document.getElementById("sn_length").value;
                            var productsn = document.getElementById("productsn").value;
                            if (productsn.length===parseInt(sn_length)){
                                operation="正确";
                                insertlabel();
                                printsn();
                                $("#right").text("正确");
                                $("#wrong").text("");
                                addtable();
                            }else {
                                operation="SN位数错误";
                                insertlabel();
                                $("#right").text("");
                                $("#wrong").text("SN位数错误");
                                addtable();
                                document.getElementById("productsn").value="";
                            }
                        }
                    }

                    //保存SN号
                    function insertlabel() {
                        var productsn = document.getElementById("productsn").value;
                        var model = document.getElementById("model").value;
                        var moId = document.getElementById("getmoid").value;
                        var operator = document.getElementById("operator").value;
                        var monum=document.getElementById("getmonum").value;
                        var chandesn={
                                "productionSN": productsn,
                                "model": model,
                                "moId": moId,
                                "operator": operator,
                            "operation":operation,
                            "monum":monum
                            };
                        $.ajax({
                            url: "/changesn/add_changesn",
                            type: "POST",
                            data: chandesn,
                            success: function (res) {
                                //alert(res)
                                if (res.code == 100) {
                                    //document.getElementById("productsn").blur();
                                }
                            }
                        })
                    }

                    //已完工数+1
                    function changemoremain() {
                        document.getElementById("getmoremain").value = parseInt(document.getElementById("getmoremain").value) + 1;
                    }

                    //打印标签
                    function printsn() {
                        var model = document.getElementById("model").value;
                        var productsn = document.getElementById("productsn").value;
                        var fw=document.getElementById("fw").value;

                        $.ajax({
                            url: "/changesn/printsn",
                            data:{"model":model,"productsn":productsn,"fw":fw},
                            success: function (res) {
                                //alert(res.code);
                                if (res.code == 100) {
                                    changemoremain();
                                    document.getElementById("productsn").value="";
                                }
                            }
                        })


                    }

                    //计时
                    function count() {
                        setInterval(getmoremain, 5000);
                    }

                    //表格添加列
                    function createNode(element, text) {
                        var td = document.createElement(element);
                        td.innerHTML = text;
                        return td;
                    }

                    var id=0;
                    //将文本框数据添加到表格
                    function addtable() {
                        var oTable = document.getElementsByTagName("table")[1];
                        var tBodies = oTable.tBodies[0];
                        var mai=document.getElementById('tab');
                        //1.创建行
                        var tr = document.createElement("tr");

                        //2.添加行到表格
                        tBodies.appendChild(tr);

                        id++;

                        //3.添加单元格
                        tr.appendChild(createNode("td",id));
                        tr.appendChild(createNode("td", document.getElementById("productsn").value));
                        tr.appendChild(createNode("td", document.getElementById("model").value));
                        tr.appendChild(createNode("td", document.getElementById("getmoid").value));
                        tr.appendChild(createNode("td", operation));
                        tr.appendChild(createNode("td", document.getElementById("operator").value));
                        tr.appendChild(createNode("td", showTime()));


                        mai.scrollTop=mai.scrollHeight;//通过设置滚动高度
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
