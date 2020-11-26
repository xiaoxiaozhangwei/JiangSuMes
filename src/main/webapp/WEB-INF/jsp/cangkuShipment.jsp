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
        .table { table-layout:fixed; }
        table thead th:first-child,thead th:first-child{ width:350px; }

    </style>
</head>
<body  onload="showTime()">
<form action="" name="cangku" class="cangku" id="mycangku">
    <div >
        <!-- 导航栏-->
        <%@ include file="./commom/head.jsp"%>


        <!-- 中间部分（左侧栏+表格内容） -->
        <div >
            <!-- 左侧栏 -->
            <%@ include file="commom/left-warehouse.jsp"%>

            <!-- 表格内容 -->
            <div class="dept_info col-sm-10">

                <div class="panel panel-success">

                    <!-- 路径导航 -->
                    <div class="panel-heading" style="height: 130px" id="divshow">

                        <div align="center">
                            <h4 style="font-size:40px; color: #ff5a19;" >仓库出货——信息记录</h4>
                        </div>
                        <table class="table table-bordered" >
                            <thead>
                            <tr>
                                <td colspan="2">请扫出货的外箱信息: <input type="text" id="sn" name="sn"> &nbsp;<button type="button"  id="selectWbox" class="btn btn-info">查询外箱</button> </td>
                                <td colspan="2">外箱号码: <input type="text" id="wbox" name="wbox"> &nbsp;&nbsp;&nbsp;&nbsp;<button type="button"  id="selectRelate" class="btn btn-success">相关查询</button> </td>
                                <td hidden>外箱号码: <input type="text" id="hidden" name="hidden"> &nbsp;&nbsp; </td>
                            </tr>



                            </thead>
                            <tbody style="display:none"  id="show">
                            <tr>
                                <td>仓库区域:<input type="text" id="cangku" readonly></td>
                                <td>展板区域:<input type="text" id="zhanban" readonly></td>
                                <td>工单信息:<input type="text" id="mo" readonly></td>
                                <td>数量信息:<input type="text" id="num" readonly></td>
                                <td>FW信息:  <input type="text" id="fw" readonly></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <ol class="breadcrumb" style="height: 15px">
                        <li class="active">请填写以下相关信息</li>
                    </ol>

                    <table class="table table-bordered" >
                        <thead>
                        <tr>
                            <th style="width: 518px">信息</th>
                            <th  colspan="2"  >信息填写</th>
                        </tr>
                        </thead>
                    </table>

                    <div style="height: 540px;overflow-x: auto;overflow-y: auto;" id="tab" >

                        <table  class="table table-bordered " id="myTable02" >
                            <tbody >
                            <tr>
                                <th>ERP料号</th>
                                <th colspan="2">
                                    <input type="text" name="erpItemNo" class="form-control"  placeholder="" id="erpItemNo"></th>
                                </th>
                            </tr>
                            <tr >
                                <th  >产品规格</th>
                                <th colspan="2">
                                    <input type="text" name="productSpecifications" class="form-control"  placeholder="" id="productSpecifications"></th>

                                </th>
                            </tr>
                            <tr >
                                <th>申请人</th>
                                <th colspan="2"><input type="text" name="applicant" class="form-control"  placeholder="" id="applicant"></th>


                            </tr>
                            <tr >
                                <th>终端客户</th>
                                <th colspan="2"><input type="text" name="customer" class="form-control"  placeholder=""  id="customer"></th>

                            </tr>
                            <tr >
                                <th>出货性质</th>
                                <th colspan="2"><input type="text" name="shipmentNature" class="form-control"  placeholder=""  id="shipmentNature"></th>

                            </tr>
                            <tr >
                                <th >收件信息（地址+人力+电话）</th>
                                <th colspan="2"><input type="text" name="receivingInformation" class="form-control"  placeholder=""  id="receivingInformation"></th>

                            </tr>
                            <tr >
                                <th>ERP单号</th>
                                <th colspan="2"><input type="text" name="erpOddNumbers" class="form-control"  placeholder=""  id="erpOddNumbers"></th>

                            </tr>
                            <tr >
                                <th>审批单（OA）</th>
                                <th colspan="2"><input type="text" name="oa" class="form-control"  placeholder=""  id="oa"></th>

                            </tr>
                            <tr >
                                <th>物流单号</th>
                                <th colspan="2"><input type="text" name="logisticsNumber" class="form-control"  placeholder=""  id="logisticsNumber"></th>

                            </tr>
                            <tr >
                                <th>数量</th>
                                <th colspan="2"><input type="text" name="num" class="form-control"  placeholder=""  id="number"></th>

                            </tr>
                            <tr >
                                <th>备注</th>
                                <th colspan="2"><input type="text" name="comments" class="form-control"  placeholder=""  id="comments"></th>

                            </tr>
                            <tr >
                                <th>执行结果</th>
                                <th colspan="2"><input type="text" name="A05name1" class="form-control"  placeholder=""  id="75"></th>

                            </tr>
                            <tr>
                                <th colspan="3" style="text-align:center;">
                                    <a  role="button" class="btn btn-primary emp_edit_btn" onclick="checkSN()" >信息提交</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">数据导出</button>

                                </th>

                            </tr>
                            </tbody>
                        </table>
                    </div>


                </div><!-- /.panel panel-success -->

            </div><!-- /.dept_info -->

        </div><!-- /.hrms_dept_body -->

        <!-- 尾部-->
        <%@ include file="./commom/foot.jsp"%>

    </div><!-- /.hrms_dept_container -->

</form>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">要导出的外箱号码:</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal add_dept_form">
                    <div class="form-group">
                        <label for="wboxs" class="col-sm-2 control-label">外箱号</label>
                        <div class="col-sm-8">
                            <textarea rows="15" cols="11"  name="" class="form-control"  id="wboxs" readonly></textarea>
                        </div>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary"  onclick="exportSN()">SN数据导出</button>

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>


<script type="text/javascript">
    var model;
    $(document).ready(function(){

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

    //扫描内箱号回车功能
    $('#sn').bind('keyup', function(event) {
        if (event.keyCode == "13") {
            //回车执行查询
            //alert("test");
            document.getElementById("sn").readOnly=true;
            document.getElementById("sn").blur();
            document.getElementById("hidden").focus();
            setTimeout(selectWbox,14000);
        }
    });


    //根据sn号查询外箱号  条码枪触发
    function selectWbox()
    {
        var sn=document.getElementById("sn").value;
        $.ajax({
            url:"/cangku/selectWboxNumber",
            type:"GET",
            data: "sn="+sn,
            success:function (result) {
                if (result.code==100)
                {   //alert("已查询到外箱号!");
                    var wbox = result.extendInfo.wbox;
                    /*
                    if(document.getElementById("wbox").value!==wbox){
                        document.getElementById("wbox").value=wbox;
                      //  selectByWbox();
                    }*/
                    document.getElementById("wbox").value=wbox;

                }
                else
                {  alert("没有查询到外箱号，请重新扫描！")
                    //document.getElementById("sn").value="";
                    // location.reload();
                }
            }

        });


    }

    //根据sn号查询外箱号 收到点击查询
    $("#selectWbox").click(function () {
        var sn=document.getElementById("sn").value;
        $.ajax({
            url:"/pack/selectWboxNumber",
            type:"GET",
            data: "sn="+sn,
            success:function (result) {
                if (result.code==100)
                {   alert("已查询到外箱号!");
                    var wbox = result.extendInfo.wbox;
                    /*
                    if(document.getElementById("wbox").value!==wbox){
                        document.getElementById("wbox").value=wbox;
                      //  selectByWbox();
                    }*/
                    document.getElementById("wbox").value=wbox;

                }
                else
                {   alert("没有查询到外箱号，请重新扫描！")
                    //document.getElementById("sn").value="";
                    location.reload();
                }
            }

        });


    });


    //根据外箱号查询相关信息
    $("#selectRelate").click(function () {
        var ui =document.getElementById("show");
        var divshow=document.getElementById("divshow");
        var wbox=$("#wbox").val();
        $.ajax({
            url:"${pageContext.request.contextPath}/cangkuShipment/selectByWbox",
            type:"GET",
            data:"wbox="+wbox,
            success:function (result) {
                if (result.code==100)
                {
                    alert("查询成功！");
                    var storage= result.extendInfo.storage;
                    $("#cangku").val(storage.stock);
                    $("#zhanban").val(storage.store_area);
                    $("#mo").val(storage.mo);
                    $("#num").val(storage.num);
                    $("#fw").val(storage.gongdan.fw);
                    model=storage.model;
                    ui.style.display="block";
                    divshow.style.height="180px";
                    selectFirstByModel();
                }
                else
                {
                    divshow.style.height="130px";
                    alert("未查询到！");
                    ui.style.display="none";
                }

            }


        });


    });

    //导出SN的excel
    function exportSN(){
        var wboxs=document.getElementById("wboxs").value.trim();
        var array = wboxs.split("\n");
        window.location.href="${pageContext.request.contextPath}/cangkuShipment/exportShipmentSN/"+array;

    }

    //先进行sn数据比对 查询是否有重复
    function checkSN()
    { var wbox=$("#wbox").val();
        $.ajax({
            url:"${pageContext.request.contextPath}/cangkuShipment/selectByAllSn",
            type:"GET",
            data: "wbox="+wbox,
            success:function (result)
            {
                if (result.code == 100){
                    addShipment();
                }
                else {
                    var list= result.extendInfo.list;
                    var str = "存在如下重复项:\n";
                    for (var x in list) {
                        str+=list[x];
                        str+="\n";
                    }
                    alert(str);
                }
            }
        });
    }

    //出货数据添加到入库数据库
    function  addShipment() {
        var wbox=$("#wbox").val();
        $.ajax({
            url:"${pageContext.request.contextPath}/cangkuShipment/addShipment",
            type:"POST",
            data: $("#mycangku").serialize(),
            success:function (result)
            {
                if (result.code == 100){
                    alert("信息添加成功");
                    var wboxs=wbox+"\n"+$("#wboxs").val();
                    $("#wboxs").val(wboxs);
                    //document.getElementById("mycangku").reset();
                    $("#divshow input").val("");
                    $("#number").val("");

                }
                else {
                    alert("信息添加失败");
                }
            }
        });
    }

    //查找入库时间最早的，先进先出
    function  selectFirstByModel() {
        $.ajax({
            url:"${pageContext.request.contextPath}/cangkuShipment/selectFirstByStorageTime",
            type:"GET",
            data:"model="+model,
            success:function (result)
            {
                if (result.code == 200){
                    var storage= result.extendInfo.storage;
                    alert(storage.wbox+" 的入库时间最早，请尽快出货！");
                }
                else {
                }
            }


        });
    }

</script>

</html>
