<%--
  Created by IntelliJ IDEA.
  User: guyanbo
  Date: 2019/8/13
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>包装流程</title>
    <style>

        #model1{
            width:600px;
            height:60px;
            margin-left:-250px;
            margin-top:-150px;
            position:fixed;
            top:50%;
            left:50%;
            z-index: 200;
        }
        #outer{
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
                        <div align="center"><h4 style="font-size:40px; color: #ff5a19;" >仓储站—内箱打印</h4></div>
                        <div id="form">
                            <form  class="pack" >
                                <table class="table" >

                                    <tr>
                                        <td>请输入要拆箱的外箱号: <input type="text"  id="oldWbox" ></td>
                                        <td>请输入要拆箱的内箱号： <input type="text"  id="oldNbox" ></td>
                                        <td> <input type="button"  onclick="deleteByWbox()" value="请点击确认更改"></td>
                                        <td> <input type="button"  onclick="recordNbox()" value="请点击确认记录"></td>

                                    </tr>


                                    <tr>

                                        <td>
                                            工单号码：<select name="moId" id="getmoid"  onchange="selectchange()" onfocus="checkNull2()">
                                            <option value="">请选择</option>
                                        </select>
                                        </td>

                                        <td>
                                            操作员：<input type="text" name="operator" id="operator" value="${name}" readonly>
                                            <input type="text" id="operation" name="operation" style="visibility: hidden;width: 2px">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>机种名称：<input type="text" name="model" id="model"></td>
                                        <td>
                                            该箱数量：<input type="text" id="Nbox_num" value="50">
                                        </td>
                                        <td>
                                            内箱号码：<input type="text" name="nboxId" id="NboxId" readonly>
                                        </td>
                                        <td>SN长度：<input type="text" id="snlength"></td>
                                    </tr>
                                    <tr>

                                        <td>
                                            产品SN号：<input type="text" name="productionSN" id="productionSN" onkeydown="production()"  onfocus="checkNull1()" >
                                            <input type="text" id="id" name="id" style="width: 2px;visibility: hidden" value="1" />
                                            <input type="text" id="right" name="right" style="width: 2px;visibility: hidden" value="0">
                                            <br>
                                            <span id="operation_meeage_right" style="color: #28a522"></span>
                                            <span id="operation_meeage_wrong" style="color: brown"></span>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>

                    <ol class="breadcrumb" style="height: 50px">
                        <li class="active">内箱包装操作信息</li>
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <input type="button"  value="重新打印标签"  class="btn btn-warning" data-toggle="modal" data-target=".reprint-modal">&nbsp;
                            </li>
                            <li>
                                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">内箱标签模板设计</button>&nbsp;
                            </li>
                        </ul>
                    </ol>
                </div>
                <div style="height: 450px;overflow-x: auto;overflow-y: auto;" id="tab">
                    <table class="table table-bordered table-hover" id="myTable02">
                        <thead>
                        <th>编号</th>
                        <th>产品SN号</th>
                        <th>机种名称</th>
                        <th>异常信息</th>
                        <th>作业时间</th>
                        </thead>
                        <tbody id="table"></tbody>
                    </table>
                </div>

                <%@ include file="Nbaozhuang2.jsp"%>

            </div><!-- /.panel panel-success -->
        </div><!-- /.dept_info -->
    </div><!-- /.hrms_dept_body -->
    <!-- 尾部-->
    <%@ include file="./commom/foot.jsp"%>

</div><!-- /.hrms_dept_container -->




<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">内箱标签模板设计</h4>
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
                            <input type="text" name="QTY" class="form-control" id="add_QTY" value="50 pcs">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_Date" class="col-sm-2 control-label">生产日期/MFG.Date</label>
                        <div class="col-sm-8">
                            <input type="text" name="Date" class="form-control" id="add_Date" value="2020-05-16">
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="print" onclick="printNeimuban()">打印</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


</body>
<script type="text/javascript">
    var oInput = document.getElementById("form").getElementsByTagName("input");
    var oTable = document.getElementsByTagName("table")[1];
    var tBodies = oTable.tBodies[0];

    //打印内箱标签
    function print() {

        var nbox = document.getElementById("NboxId").value;
        $.ajax({
            url: "/cangku/printNbox/" + nbox,
            type: "GET"
            // printNeimuban();
        });
    }
    //添加到表格
    function add() {
        var mai=document.getElementById('tab');
        //1.创建行
        var tr = document.createElement("tr");
        //2.添加行到表格
        tBodies.appendChild(tr);

        if (oInput['operation'].value!="无"){
            tr.style.color="brown";
        }else {
            tr.style.color="black";
        }
        //3.添加单元格
        tr.appendChild(createNode("td",oInput['id'].value));
        tr.appendChild(createNode("td", oInput['productionSN'].value));
        tr.appendChild(createNode("td", oInput['model'].value));
        tr.appendChild(createNode("td", oInput['operation'].value));
        tr.appendChild(createNode("td", showTime()));

        document.getElementById("id").value=parseInt( oInput['id'].value) +1;
        mai.scrollTop=mai.scrollHeight;//通过设置滚动高度

    }
    //表格添加列
    function createNode(element,text) {
        var td = document.createElement(element);
        td.innerHTML = text;
        return td;
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
    });

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


    //工单下拉框选择
    function selectchange() {
        updatemoid();
        Nbox();
        document.getElementById("productionSN").focus();
    }
    //填写内箱数量后回车
    function Nboxnum() {
        if (event.keyCode==13){
            Nbox();
            document.getElementById("productionSN").focus();
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



    //根据备注是否进过目检站
    /*
    function chooseCheck() {
        var beizhu=document.getElementById("mesg").value;
        if (beizhu=="良品"||beizhu=="重工")
        {
            // alert("检查目检站");
            $("input:radio[value='是']").attr('checked','true');
            //$("input:radio[value='否']").attr('checked','false');
        }
        else
        {
            //alert("不需要检查目检站");
            //  $("input:radio[value='是']").attr('checked','false');
            $("input:radio[value='否']").attr('checked','true');

        }


    }
*/

    //产品SN号检查
    function production() {
        if (event.keyCode == 13) {
            document.getElementById("productionSN").blur();
            //show();
            var productionSN = $("#productionSN").val();
            var model = $("#model").val();
            var l = parseInt(document.getElementById("snlength").value);
            var mo=document.getElementById("getmoid").value;
            //var w=document.getElementById("week").value;

            if (productionSN == model)//判断输入是否为model
            {
                alert("输入信息为机种名称！");
                $("#operation_meeage_right").text("");
                $("#operation_meeage_wrong").text("输入信息为机种名称");
                $("#operation").val("输入信息为机种名称");
                add();

                $("#productionSN").val("");
                document.getElementById("productionSN").focus();
            } else if (productionSN.length != l) {//检查SN位数是否正确
                alert("SN号位数错误！");
                $("#operation_meeage_right").text("");
                $("#operation_meeage_wrong").text("SN号位数错误");
                $("#operation").val("SN号位数错误");
                add();
                $("#productionSN").val("");
                document.getElementById("productionSN").focus();
            } else {
                //插入数据库
                $("#operation_meeage_wrong").text("");
                $("#operation_meeage_right").text("操作正确！");
                $("#operation").val("无");
                $.ajax({
                    url:"/cangku/insertCangKu",
                    type:"POST",
                    data:$(".pack").serialize(),
                    success:function (result)
                    {
                        if (result.code==100)
                        {
                            document.getElementById("right").value=parseInt( oInput['right'].value) +1;
                            //updatemoremain();
                            // updatemoid();
                            add();
                            if(parseInt( oInput['right'].value)===parseInt( oInput['Nbox_num'].value))
                            {
                                setTimeout(printpause,2000);
                            }else
                            {
                                $("#productionSN").val("");
                                document.getElementById("productionSN").focus();
                            }
                        }
                        else{
                            alert("插入数据库失败")
                        }
                    }
                });

            }
        }
    }
    //打印停顿
    function printpause() {
        alert("该箱已满，可打印标签");
        print();
        printNeimuban();
        alert("打印结束，请填写新的内箱数量");
        document.getElementById("id").value="1";
        $("#productionSN").val("");
        //$("#Nbox_num").val("");
        $("#NboxId").val("");
        $("#right").val("0");
        Nbox();
        document.getElementById("productionSN").focus();
        //清空表格
        var tb = document.getElementById('myTable02');
        var rowNum=tb.rows.length;
        for (var i=1;i<rowNum;i++)
        {
            tb.deleteRow(i);
            rowNum=rowNum-1;
            i=i-1;
        }
    }
    //添加信息正确则工单剩余量-1
    function updatemoremain() {
        var moId=document.getElementById("getmoid").value;
        $.ajax({
            url:"/pack/merman/"+moId,
            type:"PUT",
        });
    }

    //自动生成内箱号
    function Nbox() {
        $.ajax({
            url:"/pack/count",
            type:"GET",
            success:function (result) {
                if (result.code==100){
                    var count=result.extendInfo.c+1;
                    if (count<=9)
                    {
                        $("#NboxId").val("XSN"+showTime2()+"000"+count);
                    }else if (count>=10&&count<=99){
                        $("#NboxId").val("XSN"+showTime2()+"00"+count);
                    }else if (count>=100&&count<=999){
                        $("#NboxId").val("XSN"+showTime2()+"0"+count);
                    }else if (count>=1000){
                        $("#NboxId").val("XSN"+showTime2()+count);
                    }
                }else {
                    alert("内箱号获取失败！")
                }

            }
        });

        $("#add_Lot_No").val($("#getmoid").val());
        $("#add_Model_No").val($("#model").val());
        $("#add_QTY").val($("#Nbox_num").val()+" pcs");
    }


    //光标停在产品SN号时判断前面值是否输入
    function checkNull1() {
        if (document.getElementById("operator").value==""){
            if (confirm("用户名已失效，请重新登录")) {
                document.getElementById("operator").focus();
                window.location="/user/logout";
            }
        }else if (document.getElementById("getmoid").value==""||document.getElementById("getmonum").value==""||document.getElementById("getmoremain").value==""){

            alert("请检查工单信息！");
            document.getElementById("getmoid").focus();

        }else if(document.getElementById("model").value==""){

            alert("请输入机种名称！");
            document.getElementById("model").focus();
        }else if(document.getElementById("Nbox_num").value=="") {
            alert("请输入内箱数量！");
            document.getElementById("Nbox_num").focus();
        } else if (document.getElementById("snlength").value=="") {
            alert("请输入SN长度！");
            document.getElementById("snlength").focus();
        }else {
            document.getElementById("inputproduct").focus();
        }
    }
    function checkNull2() {
        if (document.getElementById("operator").value==""){
            if (confirm("用户名已失效，请重新登录")) {
                document.getElementById("operator").focus();
                window.location="/user/logout";
            }
        }
    }


    function printNeimuban() {
        var Product_Name = $("#Product_Name").val();
        var Lot_No = $("#add_Lot_No").val();
        var P_N = $("#add_P_N").val();
        var SPEC = $("#add_SPEC").val();
        var Model_No = $("#add_Model_No").val();
        var QTY = $("#add_QTY").val();
        var Date = $("#add_Date").val();
        //验证省略...
        $.ajax({
            url:"/cangku/printNeiMuban",
            type:"GET",
            data:{"Product_Name":Product_Name,"Lot_No":Lot_No,"P_N":P_N,"SPEC":SPEC,"Model_No":Model_No,"QTY":QTY,"Date":Date},
        });

    }


    //拆箱和合箱 删除旧入库外箱
    function deleteByWbox() {
        alert("我说");
        var wbox=$("#oldWbox").val();
        alert(wbox);
        $.ajax({
            url:"/storage/deleteBywbox",
            type:"GET",
            data:"wbox="+wbox,
            success:function (result) {
                if (result.code==100)
                {  alert("确认成功！");}
                else
                {
                    alert("确认失败！");

                }
            }
        });
    }

    //记录新旧内箱，和旧外箱
    function recordNbox() {
        var oldWbox = $("#oldWbox").val();
        var oldNbox = $("#oldNbox").val();
        var newNbox = $("#NboxId").val();
        var operator = $("#operator").val();

        var cangkuRecord = {"oldNbox": oldNbox, "newNbox": newNbox, "oldWbox": oldWbox, "operator": operator};

        $.ajax({
            url: "/cangku/insertCangKuRecord",
            type: "POST",
            data: cangkuRecord,
            success: function (result) {
                if (result.code == 100) {
                    alert("记录成功！");
                } else {
                    alert("记录失败！");

                }

            }

        });

    }

</script>

</html>
