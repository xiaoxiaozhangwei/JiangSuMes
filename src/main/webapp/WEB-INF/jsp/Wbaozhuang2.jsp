<%--
  Created by IntelliJ IDEA.
  User: guyanbo
  Date: 2019/9/11
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>包装流程</title>
</head>
<body>
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
                    <div style="font-size: 20px">
                        <div align="center"><h4 style="font-size:40px; color: #ff5a19;" >包装站—外箱包装</h4></div>
                        <div id="form">
                            <form  class="pack" >
                                <table class="table" >
                                    <tr>
                                        <td>
                                            工单号码：
                                            <select name="moId" id="getmoid"  onchange="selectchange()" >
                                                <option value="">请选择</option>
                                            </select>
                                        </td>
                                        <td>工单量：<input type="text" name="momum" id="getmonum" readonly></td>
                                        <td>工单剩余量：<input type="text" name="moremain" id="getmoremain" readonly></td>
                                        <td>机种名称：<input type="text" name="model" id="model"></td>
                                    </tr>

                                    <tr>
                                        <td>
                                            内盒号码1：<input type="text" id="Nbox_num1" >
                                        </td>
                                        <td>
                                            内盒号码2：<input type="text" id="Nbox_num2" >
                                        </td>
                                        <td>
                                            内盒号码3：<input type="text" id="Nbox_num3" >
                                        </td>
                                        <td>
                                            内盒号码4：<input type="text" id="Nbox_num4" >
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            1内盒产品SN：
                                            <textarea id="sn1" style="height: 200px"></textarea>
                                        </td>
                                        <td>
                                            2内盒产品SN：
                                            <textarea id="sn2" style="height: 200px"></textarea>
                                        </td>
                                        <td>
                                            3内盒产品SN：
                                            <textarea id="sn3" style="height: 200px"></textarea>
                                        </td>
                                        <td>
                                            4内盒产品SN：
                                            <textarea id="sn4" style="height: 200px"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            外箱号码：<input type="text" name="wboxId" id="WboxId" value="HM28050058BE">
                                        </td>
                                        <td>
                                            操作员：<input type="text" name="operator" value="${name}" readonly id="operator">
                                            <input type="text" name="num" id="num" style="visibility: hidden;width: 1px">
                                            <input type="text" id="id" name="id" style="width: 2px;visibility: hidden" value="1"/>
                                            <input type="text" id="right_num" style="width: 1px;visibility: hidden" value="0">
                                        </td>
                                        <td>
                                            <input type="button" value="打印" onclick="print2()">
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                    <!-- 路径导航 -->
                    <ol class="breadcrumb">
                        <li><a href="#">包装操作</a></li>
                        <li class="active">信息</li>
                        <div style="float: right"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">外箱标签模板设计</button></div>
                    </ol>
                </div>
                <!-- Table
                <table class="table table-bordered table-hover" id="myTable02">
                    <thead>
                    <th>编号</th>
                    <th>内盒号码</th>
                    <th>内盒盘片数量</th>
                    <th>异常信息</th>
                    <th>操作员</th>
                    <th>作业时间</th>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                -->

            </div><!-- /.panel panel-success -->
        </div><!-- /.dept_info -->
        <script>
            //var oInput = document.getElementById("form").getElementsByTagName("input");
            //var oTable = document.getElementsByTagName("table")[1];
            //var tBodies = oTable.tBodies[0];
            //var rows=oTable.rows;
            //下拉框回显工单号
            $(document).ready(function(){
                //alert("aa");
                $.ajax({
                    url:"/gd/selected/",
                    type:"GET",
                    success:function (result) {
                        //alert("1");
                        var unitObj=document.getElementById("getmoid"); //页面上的html:select元素
                        //alert(result.code)
                        if (result.code == 100){
                            var list = result.extendInfo.moidlist;
                            for(var i=0;i<list.length;i++){                    //遍历后台传回的结果，一项项往select中添加option
                                unitObj.options.add(new Option(list[i].moId));
                            }
                        }
                    }
                });
            })

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
                            $("#getmoremain").val(gd.moRemain0);
                        }else {}
                    }
                });
            }
            //工单下拉框选择
            function selectchange() {
                updatemoid();
                document.getElementById("Nbox_num").focus();
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
                    // obj.innerHTML = str;
                    // window.setTimeout("showTime()", 1000);
                    return t;

                }
            }
            //返回年月日
            function showTime2() {
                var da = '';
                var now = new Date();
                var now_m = now.getMonth() + 1;
                now_m = (now_m < 10) ? '0' + now_m : now_m;
                var now_d = now.getDate();
                now_d = (now_d < 10) ? '0' + now_d : now_d;
                var y=now.getFullYear().toString().substring(2,4);
                da = y+ now_m + now_d;
                return da;
            }

            //自动生成外箱标签
            function Wbox() {
                //alert("1");
                $.ajax({
                    url:"/pack/countW",
                    type:"GET",
                    success:function (result) {
                        if (result.code==100){
                            //alert("R");
                            var count=result.extendInfo.c+1;
                            if (count<=9)
                            {
                                $("#WboxId").val("XSW"+showTime2()+"000"+count);
                            }else if (count>=10&&count<=99){
                                $("#WboxId").val("XSW"+showTime2()+"00"+count);
                            }else if (count>=100&&count<=999){
                                $("#WboxId").val("XSW"+showTime2()+"0"+count);
                            }else if (count>=1000){
                                $("#WboxId").val("XSW"+showTime2()+count);
                            }

                        }else {}

                    }
                });
            }
            //填写内盒数量后回车
            function Nboxnum() {
                if (event.keyCode==13){
                    Wbox();
                    document.getElementById("NboxId").focus();
                }
            }

            //向表格中添加数据
            function addtable(a) {
                //1.创建行
                var tr = document.createElement("tr");
                //2.添加行到表格
                tBodies.appendChild(tr);
                //3.添加单元格
                tr.appendChild(createNode("td",oInput['id'].value));
                tr.appendChild(createNode("td", a));
                tr.appendChild(createNode("td", oInput['num'].value));
                tr.appendChild(createNode("td", oInput['operation'].value));
                tr.appendChild(createNode("td", oInput['operator'].value));
                tr.appendChild(createNode("td", showTime()));

                document.getElementById("id").value=parseInt( oInput['id'].value) +1;
            }
            //表格添加列
            function createNode(element,text) {
                var td = document.createElement(element);
                td.innerHTML = text;
                return td;
            }
            //内盒号码回车操作
            function Nboxenter(){
                if (event.keyCode==13){
                    var a = $("#NboxId").val();
                    alert(a);
                    $.ajax({
                        url:"/pack/selectNboxByPro/"+a,
                        type:"GET",
                        success:function (result) {
                            if (result.code==100){
                                var e;
                                var n=result.extendInfo.n;
                                alert(n);
                                $.ajax({
                                    url:"/pack/NboxExists/"+n,
                                    type:"GET",
                                    success:function (result) {
                                        alert("1");
                                        alert(result.code);
                                        if (result.code == 100) {
                                            alert("2");
                                            var count = result.extendInfo.c;
                                            $("#num").val(count);
                                            $("#operation").val("无");

                                            for (var i=0;i<rows.length;i++){
                                                if (rows[i].cells[1].innerHTML==n){
                                                    alert("4");
                                                    e=false;
                                                    break;
                                                }else {
                                                    e=true;
                                                }
                                            }

                                            if (e==true){
                                                alert("3");
                                                addtable(n);
                                                $("#NboxId").val("");
                                                document.getElementById("right_num").value=parseInt($("#right_num").val())+1;
                                                if(parseInt($("#right_num").val())===parseInt($("#Nbox_num").val())){
                                                    alert("该外箱已满，可打印标签");
                                                    $("#Nbox_num").val("");
                                                    $("#NboxId").val("");
                                                    addsql();

                                                    document.getElementById("Nbox_num").focus();
                                                    $("#WboxId").val("");
                                                    $("#right_num").val("0");
                                                    document.getElementById("id").value="";
                                                }else {
                                                    document.getElementById("NboxId").focus();
                                                }
                                            }else {
                                                $("#NboxId").val("");
                                                $("#NboxId").focus();
                                            }
                                        }
                                    }
                                });
                            }else {
                                $("#NboxId").val("");
                                $("#NboxId").focus();
                            }
                        }
                    });
                }

            }
            var j=0;
            //前端数据传到数据库
            function addsql() {
                //alert("1");
                //alert($("#WboxId").val());
                var mynbox = new Array();

                var i=1;
                //alert(rows.length);
                for (i=1;i<rows.length;i++)
                {
                    //alert("1");
                    if(rows[i].cells[3].innerHTML=="无")
                    {
                        // alert(j);
                        //alert(rows[i].cells[3].innerHTML);
                        mynbox[j]=rows[i].cells[1].innerHTML;
                        j++;
                    }
                }
                var  z=0;
                var a="/pack/printW/";
                for(z=0;z<mynbox.length;z++)
                {
                    a+=mynbox[z]+"/";
                    $.ajax({
                        url:"/pack/updatewbox/"+mynbox[z]+"/"+$("#WboxId").val(),
                        type:"PUT",
                    })
                }
                var Wboxid=document.getElementById("WboxId").value;
                a+=Wboxid;
                //打印外箱
                $.ajax({
                    url:a,
                    type:"GET",
                });

                print();



                //清空表格
                var tb = document.getElementById('myTable02');
                var rowNum=tb.rows.length;
                for (i=1;i<rowNum;i++)
                {
                    tb.deleteRow(i);
                    rowNum=rowNum-1;
                    i=i-1;
                }
            }


            function print2() {

                var operator=document.getElementById("operator").value;


                var moid=document.getElementById("getmoid").value;

                var model=document.getElementById("model").value;
                var monum=document.getElementById("getmonum").value;
                var moremain=document.getElementById("getmoremain").value;
                var Nbox_num1=document.getElementById("Nbox_num1").value;
                var sn1=document.getElementById("sn1").value;
                var Nbox_num2=document.getElementById("Nbox_num2").value;
                var sn2=document.getElementById("sn2").value;
                var Nbox_num3=document.getElementById("Nbox_num3").value;
                var sn3=document.getElementById("sn3").value;
                var Nbox_num4=document.getElementById("Nbox_num4").value;
                var sn4=document.getElementById("sn4").value;
                var WboxId=document.getElementById("WboxId").value;
                $.ajax({
                    url:"/pack/Wprint2",
                    type:"POST",
                    data:{"Nbox_num1":Nbox_num1,"Nbox_num2":Nbox_num2,"Nbox_num3":Nbox_num3,"Nbox_num4":Nbox_num4,"sn1":sn1,"sn2":sn2,"sn3":sn3,"sn4":sn4,"WboxId":WboxId,"moid":moid,"model":model,"monum":monum,"moremain":moremain,"operator":operator},
                    success:function (res) {
                        if(res.code==100){
                            //printWmoban();
                            alert("打印成功！");
                            document.getElementById("getmoremain").value=parseInt(moremain)-200;
                            document.getElementById("Nbox_num1").value="";
                           document.getElementById("sn1").value="";
                          document.getElementById("Nbox_num2").value="";
                           document.getElementById("sn2").value="";
                           document.getElementById("Nbox_num3").value="";
                           document.getElementById("sn3").value="";
                           document.getElementById("Nbox_num4").value="";
                           document.getElementById("sn4").value="";
                           document.getElementById("WboxId").value="";
                        }else {
                            alert("数据缺失！")
                        }
                    }
                })
            }
            function  printWmoban() {

                var Product_Name = $("#Product_Name").val();
                var Lot_No = $("#add_Lot_No").val();
                var P_N = $("#add_P_N").val();
                var SPEC = $("#add_SPEC").val();
                var Model_No = $("#add_Model_No").val();
                var QTY = $("#add_QTY").val();
                var Date = $("#add_Date").val();
                var MEAS=$("#add_MEAS").val();
                var GW=$("#add_GW").val();
                var NW=$("#add_NW").val();
                //验证省略...
                $.ajax({
                    url:"/pack/printWaiMuBan",
                    type:"GET",
                    data:{"Product_Name":Product_Name,"Lot_No":Lot_No,"P_N":P_N,"SPEC":SPEC,"Model_No":Model_No,"QTY":QTY,"Date":Date,
                        "MEAS":MEAS, "GW":GW,"NW":NW},
                });

            }


        </script>
    </div><!-- /.hrms_dept_body -->

    <!-- 尾部-->
    <%@ include file="./commom/foot.jsp"%>

</div><!-- /.hrms_dept_container -->

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">外箱标签模板设计</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal add_dept_form">
                    <div class="form-group">
                        <label for="Product_Name" class="col-sm-2 control-label">品名/Product Name</label>
                        <div class="col-sm-8">
                            <input type="text" name="Product_Name" class="form-control" id="Product_Name" value="2.5&quot; SSD   256GB" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_Lot_No" class="col-sm-2 control-label">生产单号/Lot No.</label>
                        <div class="col-sm-8">
                            <input type="text" name="Lot_No" class="form-control" id="add_Lot_No" value="P600-X071912020001">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_P_N" class="col-sm-2 control-label">料号/P/N</label>
                        <div class="col-sm-8">
                            <input type="text" name="P_N" class="form-control" id="add_P_N"  value="10000076">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_SPEC" class="col-sm-2 control-label">产品规格/SPEC</label>
                        <div class="col-sm-8">
                            <input type="text" name="SPEC" class="form-control" id="add_SPEC"  value="2.5&quot;  SSD">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_Model_No" class="col-sm-2 control-label">产品型号/Model No.</label>
                        <div class="col-sm-8">
                            <input type="text" name="Model_No" class="form-control" id="add_Model_No" value="GG2ZT256S3C27">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_QTY" class="col-sm-2 control-label">数量/QTY</label>
                        <div class="col-sm-8">
                            <input type="text" name="QTY" class="form-control" id="add_QTY" value="200 pcs">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_Date" class="col-sm-2 control-label">生产日期/MFG.Date</label>
                        <div class="col-sm-8">
                            <input type="text" name="Date" class="form-control" id="add_Date" value="2019-12-11">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_Date" class="col-sm-2 control-label">材积/MEAS</label>
                        <div class="col-sm-8">
                            <input type="text" name="MEAS" class="form-control" id="add_MEAS" value="625*370*235mm">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_Date" class="col-sm-2 control-label">毛重/G.W</label>
                        <div class="col-sm-8">
                            <input type="text" name="G.W" class="form-control" id="add_GW" value="13KG">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_Date" class="col-sm-2 control-label">净重/N.W</label>
                        <div class="col-sm-8">
                            <input type="text" name="N.W" class="form-control" id="add_NW" value="10KG">
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="print"  onclick="printWmoban()">打印</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>

</html>
