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
    <title>仓库打印外箱</title>
    <style>
        #wrong_mo{
            position:fixed;
            top:0;
            left:0;
            width:100%;
            height:100%;
            z-index:100;
            padding-top:10%;
            text-align:center;
            background-repeat:no-repeat;
            background-position:center center;
            background-color:#000;
            background-color:rgba(0,0,0,0.5);
            filter:alpha(opacity=50);
            display: none;
        }
        #wrong_nbox{
            position:fixed;
            top:0;
            left:0;
            width:100%;
            height:100%;
            z-index:100;
            padding-top:10%;
            text-align:center;
            background-repeat:no-repeat;
            background-position:center center;
            background-color:#000;
            background-color:rgba(0,0,0,0.5);
            filter:alpha(opacity=50);
            display: none;
        }
    </style>
</head>
<body>
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
                <div class="panel-heading" >
                    <div style="font-size: 20px">
                        <div align="center"><h4 style="font-size:40px; color: #ff5a19;" >仓储站—外箱打印</h4></div>
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
                                            内盒产品SN：<input type="text" name="NboxId" id="NboxId" onkeydown="Nboxenter()" maxlength="17" minlength="17">
                                            <!--<input type="text" id="operation" name="operation" style="visibility: hidden;width: 2px">-->
                                        </td>
                                        <td>
                                            内盒数量：<input type="text" id="Nbox_num" onkeydown="Nboxnum()">
                                        </td>
                                        <td>
                                            外箱号码：<input type="text" name="wboxId" id="WboxId" readonly>
                                        </td>
                                        <td>
                                            操作员：<input type="text" name="operator" value="${name}" readonly>
                                            <!--<input type="text" name="num" id="num" style="visibility: hidden;width: 1px">
                                            <input type="text" id="id" name="id" style="width: 2px;visibility: hidden" value="1" />
                                            <input type="text" id="right_num" style="width: 1px;visibility: hidden" value="0">-->
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                    <!-- 路径导航 -->
                    <ol class="breadcrumb" style="height: 50px">
                        <li class="active">外箱包装操作信息</li>
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <input type="button" value="重新打印标签" class=" btn btn-default" data-toggle="modal"
                                       data-target=".reprint-modal">&nbsp;
                            </li>
                            <li>
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" id="waimuban">外箱标签模板设计
                                </button>&nbsp;
                            </li>
                        </ul>
                    </ol>
                </div>
                <!-- Table -->
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


            </div><!-- /.panel panel-success -->
        </div><!-- /.dept_info -->
    </div><!-- /.hrms_dept_body -->
    <div id="wrong_mo">
        <div class="alert alert-danger"  id="model1">工单信息不匹配！</div>
    </div>
    <div id="wrong_nbox">
        <div class="alert alert-warning"  id="model2">该内箱已打包外箱！</div>
    </div>
    <script>
        var oInput = document.getElementById("form").getElementsByTagName("input");
        var oTable = document.getElementsByTagName("table")[1];
        var tBodies = oTable.tBodies[0];
        var rows=oTable.rows;

        var operation;
        var num;
        var id=1;
        var right_num=0;
        var mynbox = new Array();

        //下拉框回显工单号
        $(document).ready(function(){
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
        })
        //回显工单的相关信息
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

                        $("#add_Lot_No").val(moid);
                        $("#add_Model_No").val(gd.model);

                        $("#add_SPEC").val(gd.spec);
                        $("#add_P_N").val(gd.pn);
                        $("#Product_Name").val(gd.product_name);


                        var date = new Date();
                        var seperator1 = "-";
                        var year = date.getFullYear();
                        var month = date.getMonth() + 1;
                        var strDate = date.getDate();
                        if (month >= 1 && month <= 9) {
                            month = "0" + month;
                        }
                        if (strDate >= 0 && strDate <= 9) {
                            strDate = "0" + strDate;
                        }
                        var currentdate = year + seperator1 + month + seperator1 + strDate;
                        $("#add_Date").val(currentdate);
                    }else {}
                }

            });

        }
        //工单下拉框选择 事件触发
        function selectchange() {
            updatemoid();
            document.getElementById("Nbox_num").focus();
            // Wbox();
            $("#waimuban").click();
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
                $("#add_Lot_No").val($("#getmoid").val());
                $("#add_Model_No").val($("#model").val());
                $("#add_QTY").val($("#Nbox_num").val()+" pcs");
                var date = new Date();
                var seperator1 = "-";
                var year = date.getFullYear();
                var month = date.getMonth() + 1;
                var strDate = date.getDate();
                if (month >= 1 && month <= 9) {
                    month = "0" + month;
                }
                if (strDate >= 0 && strDate <= 9) {
                    strDate = "0" + strDate;
                }
                var currentdate = year + seperator1 + month + seperator1 + strDate;
                $("#add_Date").val(currentdate);


            }
        }

        var total_num = 0;
        //向表格中添加数据
        function addtable(a) {
            //1.创建行
            var tr = document.createElement("tr");
            //2.添加行到表格
            tBodies.appendChild(tr);
            //3.添加单元格
            /**
             tr.appendChild(createNode("td",oInput['id'].value));
             tr.appendChild(createNode("td", a));
             tr.appendChild(createNode("td", oInput['num'].value));
             tr.appendChild(createNode("td", oInput['operation'].value));
             tr.appendChild(createNode("td", oInput['operator'].value));
             tr.appendChild(createNode("td", showTime()));

             document.getElementById("id").value=parseInt( oInput['id'].value) +1;
             */
            tr.appendChild(createNode("td",id));
            tr.appendChild(createNode("td", a));
            tr.appendChild(createNode("td", num));
            tr.appendChild(createNode("td", operation));
            tr.appendChild(createNode("td", oInput['operator'].value));
            tr.appendChild(createNode("td", showTime()));

            total_num+=num;
            document.getElementById("add_QTY").value=total_num+" pcs";

            id++;
        }
        //表格添加列
        function createNode(element,text) {
            var td = document.createElement(element);
            td.innerHTML = text;
            return td;
        }


        //内盒号码  回车操作
        function Nboxenter() {
            if (event.which== 13) {
                var sn = $("#NboxId").val();
                $.ajax({
                    url: "/cangku/selectNboxBySn/" + sn,
                    type: "GET",
                    success: function (result) {
                        if (result.code == 100) {
                            var e;
                            var baozhuang= result.extendInfo.baozhuang;
                            var n = baozhuang.nboxId;
                            var mo=  baozhuang.moId;
                            if(mo==document.getElementById("getmoid").value){
                                $.ajax({
                                    url: "/cangku/selectNboxNumber/" + n,
                                    type: "GET",
                                    success: function (result) {
                                        if (result.code == 100)
                                        {
                                            var nboxNumber = result.extendInfo.count;
                                            num = nboxNumber;
                                            operation = "无";
                                            for (var i = 0; i < rows.length; i++) {
                                                if (rows[i].cells[1].innerHTML === n) {
                                                    e = false;
                                                    break;
                                                } else {
                                                    e = true;
                                                }
                                            }
                                            if (e == true) {
                                                addtable(n);
                                                e = false;
                                                right_num++;
                                                if (right_num == parseInt(document.getElementById("Nbox_num").value)) {
                                                    setTimeout(addsql, 10000);
                                                } else {
                                                    document.getElementById("NboxId").focus();
                                                }
                                            }
                                            else
                                            {
                                                $("#NboxId").val("");
                                                $("#NboxId").focus();
                                            }
                                        }
                                        else
                                        {
                                            //该内箱已打印提示
                                            wrong_nbox_show();
                                            $("#NboxId").val("");
                                        }
                                    }
                                });
                            }
                            else
                            {
                                wrong_mo_show();
                                $("#NboxId").val("");
                                $("#NboxId").focus();
                            }

                        }
                        else {
                            document.getElementById("NboxId").value="";
                        }
                    },
                });
            }
        }
        function wrong_mo_show(){
            document.getElementById("wrong_mo").style.display="block";
            setTimeout(wrong_mo_hide,5000);
        }

        function wrong_mo_hide() {
            document.getElementById("wrong_mo").style.display="none";
        }
        function wrong_nbox_show(){
            document.getElementById("wrong_nbox").style.display="block";
            setTimeout(wrong_nbox_hide,3000);
        }

        function wrong_nbox_hide() {
            document.getElementById("wrong_nbox").style.display="none";
        }

        //var j=0;
        //前端数据传到数据库//并打印
        var a="/cangku/printWbox/";
        function addsql() {
            var Wboxid=document.getElementById("WboxId").value;
            alert("该外箱已满，可打印标签");
            for (var i=1;i<rows.length;i++)
            {
                if(rows[i].cells[3].innerHTML=="无")
                {
                    mynbox[i]=rows[i].cells[1].innerHTML;
                    //更改外箱号
                    $.ajax({
                        url: "/cangku/updateWbox/" + mynbox[i] + "/" + Wboxid,
                        type: "PUT",
                    });
                    updateCangKuRecordByWbox(mynbox[i],Wboxid);
                    a+=mynbox[i]+"/";
                }
            }
            a+=Wboxid;
            //打印外箱
            $.ajax({
                url:a,
                type:"GET",
                success:function (result) {
                    if (result.code==100)
                    {   //调用外箱模板
                        printWaiMuBan();
                    }

                }
            });

            //清空表格
            var tb = document.getElementById('myTable02');
            var rowNum = tb.rows.length;
            for (i = 1; i < rowNum; i++) {
                tb.deleteRow(i);
                rowNum = rowNum - 1;
                i = i - 1;
            }
            $("#Nbox_num").val("");
            $("#NboxId").val("");
            document.getElementById("Nbox_num").focus();
            $("#WboxId").val("");
            right_num=0;
            id=1;
            a="/cangku/printWbox/";

        }

        //打印外模板
        function  printWaiMuBan() {
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
                url:"/cangku/printWaiMuBan",
                type:"GET",
                data:{"Product_Name":Product_Name,"Lot_No":Lot_No,"P_N":P_N,"SPEC":SPEC,"Model_No":Model_No,"QTY":QTY,"Date":Date,
                    "MEAS":MEAS, "GW":GW,"NW":NW},
                success:function (res) {
                    if (res.code===100){
                        total_num=0;
                        document.getElementById("add_QTY").value="";
                    }
                }
            });

        }



        //记录更改的外箱
        function  updateCangKuRecordByWbox(newNbox,newWbox)
        {
            $.ajax({
                url:"/cangku/updateCangKuRecordByWbox",
                type:"GET",
                data:{"newNbox":newNbox,"newWbox":newWbox},
                success:function (res) {
                    if (res.code===100){
                        //alert("外箱更改成功");
                    }
                    else {
                        //alert("外箱更改失败");
                    }
                }

            });


        }

    </script>

    <!-- 尾部-->
    <%@ include file="./commom/foot.jsp"%>

</div><!-- /.hrms_dept_container -->


<!--外箱标签模板设计-->
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
                            <input type="text" name="Lot_No" class="form-control" id="add_Lot_No" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_P_N" class="col-sm-2 control-label">料号/P/N</label>
                        <div class="col-sm-8">
                            <input type="text" name="P_N" class="form-control" id="add_P_N"  >
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
                            <input type="text" name="Model_No" class="form-control" id="add_Model_No" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_QTY" class="col-sm-2 control-label">数量/QTY</label>
                        <div class="col-sm-8">
                            <input type="text" name="QTY" class="form-control" id="add_QTY" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_Date" class="col-sm-2 control-label">生产日期/MFG.Date</label>
                        <div class="col-sm-8">
                            <input type="text" name="Date" class="form-control" id="add_Date" >
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
                <button type="button" class="btn btn-primary" id="print"  onclick="printWaiMuBan()">打印</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->


</div>
<!--重新打印标签-->
<div class="modal fade reprint-modal" tabindex="-1" role="dialog" aria-labelledby="reprint-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">重新打印标签</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal reprint_form">
                    <div class="form-group">
                        <label for="inputWbox" class="col-sm-2 control-label">外箱号</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="inputWbox">
                            <span id="msg" class="msg"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputNbox" class="col-sm-2 control-label">内箱号</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="inputNbox"></p>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary reprint_btn" >打印</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
    <script type="text/javascript">
        var nbox;
        $("#inputWbox").change(function () {
            //alert(document.getElementById("inputWbox").value);
            $.ajax({
                url: "/pack/selectNBoxByWbox/" + document.getElementById("inputWbox").value,
                type: "GET",
                success: function (res) {
                    if (res.code == 100) {
                        $("#msg").parent().parent().removeClass("has-error");
                        $("#msg").parent().parent().addClass("has-success");
                        $("#msg").text("外箱号正确！");
                        //$(".reprint_btn").attr("btn_name_exists", "success");
                        nbox = res.extendInfo.nbox;
                        for (var i=1;i<=nbox.length;i++){
                            $("#inputNbox").append(nbox[i-1]+"<br>");

                        }
                    }else {
                        $("#msg").parent().parent().removeClass("has-success");
                        $("#msg").parent().parent().addClass("has-error");
                        $("#msg").text("外箱号错误！");
                        //$(".reprint_btn").attr("btn_name_exists", "error");
                    }
                }
            })
        })

        $(".reprint_btn").click(function () {
            var u = "/pack/printW/";
            for (let i= 0; i < nbox.length; i++) {
                u += nbox[i] + "/";
            }
            $.ajax({
                url:u+document.getElementById("inputWbox").value,
                type:"GET",
                success:function (res) {
                    if(res.code==100){
                        alert("打印成功");
                        u="/pack/printW/";
                        //document.getElementById("inputNbox").value="";
                        document.getElementById("inputWbox").value="";
                        $("#inputNbox").html("");
                        $(".reprint-modal").model("hide");
                    }
                }
            })
        })
    </script>
</div><!-- /.modal -->
</body>
</html>
