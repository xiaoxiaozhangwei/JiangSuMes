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

    String name = (String) request.getSession().getAttribute("name");
%>
<html>
<head>
    <title>仓库出货页面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <style type="text/css">
        .table {
            table-layout: fixed;
        }

        table thead th:first-child, thead th:first-child {
            width: 350px;
        }


    </style>
</head>
<body >

    <div>

        <!-- 导航栏-->
        <%@ include file="./commom/head.jsp" %>


        <!-- 中间部分（左侧栏+表格内容） -->
        <div>
            <!-- 左侧栏 -->
            <%@ include file="commom/left-warehouse.jsp" %>

            <!-- 表格内容 -->
            <div class="dept_info col-sm-10">
                <form action="" name="cangku" class="cangku" id="mycangku">
                <div class="panel panel-success">

                    <!-- 路径导航 -->
                    <div class="panel-heading" style="height: 130px" id="divshow">

                        <div align="center">
                            <h4 style="font-size:40px; color: #ff5a19;">原料仓出货——信息记录</h4>
                        </div>

                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <td colspan="4">请输入要出货的料号信息: <input type="text" id="erp" name="erp" value=""> &nbsp;
                                    <button
                                        type="button" id="select" class="btn btn-info" onclick="selectErp()" data-toggle="modal" data-target="#myModal">查询erp库存
                                    </button>

                                    <!--erp出入库记录 -->
                                    <button
                                            type="button" id="select2" class="btn btn-info" onclick="selectErp2()" data-toggle="modal" data-target="#myModal2">查询erp出入库记录
                                    </button>
                                </td>
                                <td colspan="2">请扫描储位: <input type="text" id="stock" name="stock" value="" >
                                <td colspan="2">请扫描外箱号: <input type="text" id="wbox" name="wbox" value="">
                                <%--<input type="radio" id="weishu" name="weishu"  value="是否是尾数箱" onclick="radio()"/>尾数箱--%>
                            </tr>
                            <tr>
                                <input style="display:none;" type="text" id="operator" name="operator" value="${name}">
                            </tr>

                            </thead>
                        </table>
                    </div>

                    <div>
                    <ol class="breadcrumb" style="height: 15px">
                        <li class="active">请填写以下相关信息</li>
                    </ol>
                    </div>

                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th style="width: 523px">信息</th>
                            <th colspan="2">信息填写</th>
                        </tr>
                        </thead>
                    </table>

                    <div style="height: 540px;overflow-x: auto;overflow-y: auto;" id="tab">

                        <table class="table table-bordered " id="myTable02">
                            <tbody>
                            <tr>
                                <th>数量</th>
                                <th colspan="2"><input type="text" name="num" class="form-control" placeholder=""
                                                       id="number" onblur="selectNumber()"></th>
                            </tr>
                            <tr>
                                <th>申请人</th>
                                <th colspan="2"><input type="text" name="applicant" class="form-control" placeholder=""
                                                       id="applicant"></th>
                            </tr>
                            <tr>
                                <th>终端客户</th>
                                <th colspan="2"><input type="text" name="client" class="form-control" placeholder=""
                                                       id="client"></th>
                            </tr>
                            <tr>
                                <th>出货性质</th>
                                <th colspan="2"><input type="text" name="productNature" class="form-control"
                                                       placeholder="" id="productNature"></th>
                            </tr>
                            <tr>
                                <th>收件信息（地址+人力+电话）</th>
                                <th colspan="2"><input type="text" name="information" class="form-control"
                                                       placeholder="" id="information"></th>
                            </tr>
                            <tr>
                                <th>审批单（OA）</th>
                                <th colspan="2"><input type="text" name="approval" class="form-control" placeholder=""
                                                       id="approval"></th>
                            </tr>
                            <tr>
                                <th>备注</th>
                                <th colspan="2"><input type="text" name="comments" class="form-control" placeholder=""
                                                       id="comments"></th>
                            </tr>
                            <tr>
                                <th colspan="3" style="text-align:center;"><a role="button"
                                                                              class="btn btn-primary emp_edit_btn"
                                                                              onclick="add()">信息提交</a></th>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <!-- 根据erp查询的入库表信息模态框 -->
                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">erp信息</h4>
                                </div>
                                <div class="modal-body">
                                    <div id="table">
                                        <table class="table">
                                            <thead>
                                            <th>erp</th>
                                            <th>储位</th>
                                            <th>数量</th>
                                            <th>入库时间</th>
                                            </thead>
                                            <tbody ></tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" id="closeButton"  class="btn btn-default" data-dismiss="modal">关闭</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 根据erp查询的记录表信息模态框 -->
                    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel2">erp信息</h4>
                                </div>
                                <div class="modal-body">
                                    <div id="table2">
                                        <table class="table">
                                            <thead>
                                            <th>erp</th>
                                            <th>出入库数量</th>
                                            <th>库存数量</th>
                                            <th>出入库时间</th>
                                            </thead>
                                            <tbody ></tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" id="closeButton2"  class="btn btn-default" data-dismiss="modal">关闭</button>
                                </div>
                            </div>
                        </div>

                    </div>


                </div><!-- /.panel panel-success -->
                </form>
            </div><!-- /.dept_info -->
        </div><!-- /.hrms_dept_body -->
        <!-- 尾部-->
        <%@ include file="./commom/foot.jsp" %>

    </div><!-- /.hrms_dept_container -->

</body>


<script type="text/javascript">

    var divshow = document.getElementById("divshow");
    var oTable = document.getElementsByTagName("table")[3];
    var tBodies = oTable.tBodies[0];
    var table=document.getElementById('table');

    var divshow = document.getElementById("divshow");
    var oTable2 = document.getElementsByTagName("table")[4];
    var tBodies2 = oTable2.tBodies[0];
    var table2=document.getElementById('table2');

    //点击"查询erp库存信息"按钮
    function selectErp() {
        var erp = document.getElementById("erp").value;
        $.ajax({
            url: "/materialShipment/selectByErp",
            type: "GET",
            data: "erp=" + erp,
            success: function (result) {
                if (result.code == 100) {
                    var list = result.extendInfo.materialStorage;
                    for (var i = 0; i < list.length; i++) {
                        var tr = document.createElement("tr");
                        tr.appendChild(createNode("td",list[i].erp));
                        tr.appendChild(createNode("td",list[i].stock));
                        tr.appendChild(createNode("td",list[i].num));
                        tr.appendChild(createNode("td",list[i].time));
                        tBodies.appendChild(tr);
                        table.scrollTop=table.scrollHeight;//通过设置滚动高度
                    }
                } else {
                    divshow.style.height = "130px";
                    alert("没有查询到外erp，请重新输入！")
                    ui.style.display = "none";
                }
            }
        });
    }

    //表格添加列
    function createNode(element,text) {
        var td = document.createElement(element);
        td.innerHTML = text;
        return td;
    }

    // 清空erp库存信息表格
    $("#closeButton").click(function () {
        $("#table tr:not(:first)").empty("");
    })

    //点击"查询erp历史记录"按钮
    function selectErp2() {
        var erp = document.getElementById("erp").value;
        $.ajax({
            url: "/materialShipment/selectERPHistory",
            type: "GET",
            data: "erp=" + erp,
            success: function (result) {
                if (result.code == 100) {
                    var list = result.extendInfo.erpRecord;
                    for (var i = 0; i < list.length; i++) {
                        var tr = document.createElement("tr");
                        tr.appendChild(createNode("td",list[i].erp));
                        tr.appendChild(createNode("td",list[i].record));
                        tr.appendChild(createNode("td",list[i].amount));
                        tr.appendChild(createNode("td",list[i].time));
                        tBodies2.appendChild(tr);
                        table2.scrollTop=table.scrollHeight;//通过设置滚动高度
                    }
                } else {
                    divshow.style.height = "130px";
                    alert("没有查询到外erp，请重新输入！")
                    ui.style.display = "none";
                }
            }
        });
    }

    //表格添加列
    function createNode(element,text) {
        var td = document.createElement(element);
        td.innerHTML = text;
        return td;
    }

    // 清空erp库存信息表格
    $("#closeButton2").click(function () {
        $("#table2 tr:not(:first)").empty("");
    })

    /*function add() {
        var number = $("#number").val();
        if(number == ""){
            alert("请输入待出库数量");
            return false;
        }else{
        //将出货数据添加到数据库
        var erp = document.getElementById("erp").value;
        var shipmentnum = document.getElementById("number").value;
        $.ajax({
            url: "/materialShipment/selectMaterialStorageByTime",
            type: "GET",
            data: "erp=" + erp,
            success: function (result) {
                if (result.code == 100) {
                    var list = result.extendInfo.list
                    var i = 0
                    var shengyu = shipmentnum;  // shengyu的值为输入框的出库数
                    while (i < list.length) {
                        var choosenum = list[i].num
                        shengyu = shengyu - choosenum;
                        if (shengyu <= 0) {
                            alert("已找到  对第" + (i + 1) + "个采购单号为：" + list[i].purchaseNumber +"  展板所在位置为:  "+list[i].stock + "  " + " 只需要出货： " + (choosenum + shengyu) + " 剩余： " + (-shengyu));
                            var shipment = {
                                "erp": erp,
                                "num": choosenum + shengyu,
                                "purchaseNumber": list[i].purchaseNumber,
                                "operator": operator
                            };
                            $.ajax({
                                url: "/materialShipment/insertShipment",
                                type: "GET",
                                data: shipment,
                                success: function (result) {
                                    if (result.code == 100) {
                                        alert("数据最终更改成功")
                                    }
                                }
                            });
                            break;
                        } else {
                            alert("对第 " + (i + 1) + " 个采购单号为：" + list[i].purchaseNumber +"  展板所在位置为:  "+list[i].stock + "  "+ " 需要出货： " + choosenum + " 剩余： " + 0);
                            var shipment = {"erp": erp, "num": choosenum, "purchaseNumber": list[i].purchaseNumber};
                            $.ajax({
                                url: "/materialShipment/insertShipment",
                                type: "GET",
                                data: shipment,
                                success: function (result) {
                                    if (result.code == 100) {
                                        alert("数据更改成功")
                                    }
                                }
                            });
                            i++;
                            continue;
                        }
                    }
                } else {
                    alert("信息查询失败");
                    divshow.style.height = "130px";
                    ui.style.display = "none";
                }
            }
        });
    }
    }*/

    // 数量框失焦后，看输入数量是否比库存数量多，多则无法继续操作
    function selectNumber(){
        var number = $("#number").val();
        var wbox = $("#wbox").val();
        $.ajax({
            url:"/materialShipment/selectNumber",
            type:"GET",
            data:{"num":number,"wbox":wbox},
            success:function (result) {
                if(result.code == 200){
                    alert("输入出库数量大于该外箱实际数量，请重新输入");
                    $("#number").val("");
                    return false;
                }
            }
        })
    }

    // erp料号扫描枪功能
    var inputText=document.getElementById("erp");
    inputText.addEventListener("keyup",function(e){
        if(e.keyCode==13){
            $("#select").trigger("click");
        }})

    // 储位扫描枪功能
    var stock=document.getElementById("stock");
    stock.addEventListener("keyup",function(e){
        if(e.keyCode==13){
           /*var stock = $("#stock").val();
           var erp = $("#erp").val();
            $.ajax({
                url : "/materialShipment/stock",
                type : "GET",
                data : {"stock":stock,"erp":erp},
                success : function(result){
                    if(result.code == 200){
                        alert("该储位不是最早入库的，请选择 "+ result.extendInfo.stock +" 号储位");
                        $("#stock").val("");
                        return false;
                    }
                }
            });*/
           selectstock();
        }})

    /*// 储位框失焦
    $(document).ready(function(){
        $("#stock").blur(function(){
            selectstock();
        });
    });*/

    // 储位查询
    function selectstock(){
        var stock = $("#stock").val();
        var erp = $("#erp").val();
        $.ajax({
            url : "/materialShipment/stock",
            type : "GET",
            data : {"stock":stock,"erp":erp},
            success : function(result){
                if(result.code == 200){
                    alert("该储位不是最早入库的，请选择 "+ result.extendInfo.stock +" 号储位");
                    $("#stock").val("");
                    $("#stock").focus();
                    return false;
                }else{
                    $("#wbox").focus();
                }
            }
        });
    }

    // 外箱号扫描枪功能
    var wbox=document.getElementById("wbox");
    wbox.addEventListener("keyup",function(e){
        if(e.keyCode==13){
            var wbox = $("#wbox").val();
            var stock = $("#stock").val();
                $.ajax({
                    url : "/materialShipment/wbox",
                    type : "GET",
                    data : {"wbox":wbox,"stock":stock},
                    success : function(result){
                        if(result.code == 200){
                            alert("该外箱不属于该储位，请将该外箱搬至 "+ result.extendInfo.stock + " 号储位.")
                            $("#wbox").val("");
                            return false;
                        }else{
                            $.ajax({
                                url : "/materialShipment/firstwbox",
                                type : "GET",
                                data : {"stock":stock,"wbox":wbox},
                                success:function (result) {
                                    if(result.code == 100){
                                        $("#number").val(result.extendInfo.num);
                                    }else{
                                        alert("扫描的外箱不是最先入库的，请选择外箱号为 " + result.extendInfo.wbox + " 的外箱.")
                                        $("#wbox").val("");
                                    }
                                }
                            });

                        }
                    }
                });
            }
        })

    // 尾数单选框点击选中，点击取消选中
    // 该方法暂时废弃不用
    /*function radio() {
        var old = null; //用来保存原来的对象
        $("#weishu").each(function(){//循环绑定事件
            if(this.checked){
                old = this; //如果当前对象选中，保存该对象
            }
            this.onclick = function(){
                if(this == old){//如果点击的对象原来是选中的，取消选中
                    this.checked = false;
                    old = null;
                } else{
                    old = this;
                }
            }
        });
    }*/

    // 点击信息提交,将table信息上传到数据库
    function add(){
       // var radio = $('#weishu').is(':checked');
        $.ajax({
            url : "/materialShipment/add",
            type : "POST",
            data : $("#mycangku").serialize(),
            success : function (result) {
                if(result.code == 100){
                    alert("添加成功");
                    $("#wbox").val("");
                    $("#number").val("");
                }else{
                    alert("添加失败");
                }
            }
        })
    }

</script>
</html>
