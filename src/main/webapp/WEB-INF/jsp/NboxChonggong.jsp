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
        <%@ include file="commom/leftchonggong.jsp"%>

        <!-- 表格内容 -->


        <div class="dept_info col-sm-10">

            <div class="panel panel-success">
                <div class="panel-heading" >
                    <div style="font-size: 20px">
                        <div align="center"><h4 style="font-size:40px; color: #ff5a19;" >包装站—内盒重工</h4></div>
                        <div id="form">
                            <form  class="pack" >
                                <table class="table" >
                                    <tr>

                                        <td>
                                            工单号码：
                                            <select name="moId" id="getmoid" onchange="selectchange()">
                                                <option value="">请选择</option>
                                            </select>
                                        </td>
                                        <td>工单量：<input type="text" name="monum" id="getmonum" readonly></td>
                                        <td>工单剩余量：<input type="text" name="moremain" id="getmoremain" readonly></td>
                                        <td>
                                            操作员：<input type="text" name="operator" value="${name}" readonly>
                                            <input type="text" id="operation" name="operation" style="visibility: hidden;width: 2px">
                                        </td>
                                        <td>SN长度：<input type="text" id="snlength"></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            产品SN号：<input type="text" name="productionSN" id="productionSN" onkeydown="production()" maxlength="17" onfocus="checkNull1()">
                                            <input type="text" id="id" name="id" style="width: 2px;visibility: hidden" value="1" />
                                            <input type="text" id="right" name="right" style="width: 2px;visibility: hidden" value="0">
                                            <br>
                                            <span id="operation_meeage_right" style="color: #28a522"></span>
                                            <span id="operation_meeage_wrong" style="color: brown"></span>
                                        </td>
                                        <td>机种名称：<input type="text" name="model" id="model"></td>
                                        <td>
                                            该箱数量：<input type="text" id="Nbox_num" onkeydown="Nboxnum()">
                                        </td>
                                        <td>
                                            内箱号码：<input type="text" name="nboxId" id="NboxId" >
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
                                <input type="button" value="操作信息修改" class="btn btn-warning" data-toggle="modal" data-target=".updateModal">&nbsp;
                            </li>
                            <li>
                                <input type="button" value="重新打印标签" class="reprint_btn btn btn-default" data-toggle="modal" data-target=".reprint-modal">&nbsp;
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
                <script type="text/javascript">
                    var oInput = document.getElementById("form").getElementsByTagName("input");
                    var oTable = document.getElementsByTagName("table")[1];
                    var tBodies = oTable.tBodies[0];

                    //打印标签
                    function print() {

                        var my = document.getElementById("NboxId").value;
                        alert(my)
                        $.ajax({
                            url:"/repack/printN/"+my,
                            type:"GET"
                        });

                    }
                    //添加到表格
                    function add() {
                        var mai=document.getElementById('tab');
                        //1.创建行
                        var tr = document.createElement("tr");
                        //2.添加行到表格
                        tBodies.appendChild(tr);
                        //3.添加单元格
                        tr.appendChild(createNode("td",oInput['id'].value));
                        tr.appendChild(createNode("td", oInput['productionSN'].value));
                        tr.appendChild(createNode("td", oInput['model'].value));
                        tr.appendChild(createNode("td", oInput['operation'].value));
                        tr.appendChild(createNode("td", showTime()));

                        document.getElementById("id").value=parseInt( oInput['id'].value) +1;
                        mai.scrollTop=mai.scrollHeight;//通过设置滚动高度

                        return 1;
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
                    //添加到数据库
                    function insertpack() {
                        $.ajax({
                            url:"/repack/insertpack",
                            type:"POST",
                            data:$(".pack").serialize(),
                            success:function (result) {

                            }
                        });
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
                    }) ;
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
                    //填写内箱数量后回车
                    function Nboxnum() {
                        if (event.keyCode==13){
                            Nbox();
                            document.getElementById("productionSN").focus();
                        }
                    }
                    //产品SN号
                    function production() {
                        if(event.keyCode==13){
                            //show();
                            var productionSN=$("#productionSN").val();
                            var model =$("#model").val();
                            var l = parseInt(document.getElementById("snlength").value);

                            if(productionSN.length!=l)
                            {
                                alert("SN号位数错误！");
                                $("#operation_meeage_right").text("");
                                playSound();
                                $("#operation_meeage_wrong").text("SN号位数错误");
                                $("#operation").val("SN号位数错误");
                                add();
                                insertpack();
                                $("#productionSN").val("");
                                document.getElementById("productionSN").focus();


                            }else {//判断输入是否为model
                                if (productionSN != model) {

                                    //判断产品SN号是否重复
                                    $.ajax({
                                        url: "/repack/production2/" + productionSN,
                                        type: "GET",
                                        success: function (result) {
                                            if (result.code == 100) {

                                                $("#operation_meeage_wrong").text("");
                                                $("#operation_meeage_right").text("操作正确！");
                                                $("#operation").val("无");
                                                //add();
                                                //insertpack();
                                                if (add() == 1) {
                                                    $.ajax({
                                                        url: "/repack/insertpack",
                                                        type: "POST",
                                                        data: $(".pack").serialize(),
                                                        success: function (result) {
                                                            if (result.code == 100) {
                                                                document.getElementById("right").value = parseInt(oInput['right'].value) + 1;
                                                                updatemoremain();
                                                                updatemoid();

                                                                if (parseInt(oInput['right'].value) === parseInt(oInput['Nbox_num'].value)) {
                                                                    setTimeout(printpause,1000);
                                                                } else {
                                                                    $("#productionSN").val("");
                                                                    document.getElementById("productionSn").focus();
                                                                }
                                                            } else {
                                                            }
                                                        }
                                                    });
                                                }
                                            } else {
                                                $("#operation_meeage_right").text("");
                                                playSound();
                                                alert("产品SN号重复");
                                                $("#operation_meeage_wrong").text("产品SN号重复");
                                                $("#operation").val("产品SN号重复");
                                                add();
                                                insertpack();
                                                $("#productionSN").val("");
                                                document.getElementById("productionSn").focus();
                                            }

                                        }
                                    });

                                } else {
                                    alert("输入信息为机种名称！");
                                    $("#operation_meeage_right").text("");
                                    playSound();
                                    $("#operation_meeage_wrong").text("输入信息为机种名称");
                                    $("#operation").val("输入信息为机种名称");
                                    add();
                                    insertpack();
                                    $("#productionSN").val("");
                                    document.getElementById("productionSN").focus();
                                }
                            }



                        }

                    }
                    //打印停顿
                    function printpause() {
                        alert("该箱已满，可打印标签");
                        print();
                        //printNeimuban();
                        alert("打印结束，请填写新的内箱数量");
                        document.getElementById("id").value="1";
                        $("#productionSN").val("");
                        $("#Nbox_num").val("");
                        $("#NboxId").val("");
                        $("#right").val("0");
                        document.getElementById("Nbox_num").focus();
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
                            url:"/repack/merman/"+moId,
                            type:"PUT",
                        });
                    }
                    //自动生成内箱标签
                    function Nbox() {
                        //alert("1");
                        $.ajax({
                            url:"/repack/count",
                            type:"GET",
                            success:function (result) {
                                //alert("2");
                                if (result.code==100){
                                    //alert("R");
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

                                }else {}

                            }
                        });
                    }

                    function playSound() {
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

                    //光标停在产品SN号时判断前面值是否输入
                    function checkNull1() {
                        if (document.getElementById("getmoid").value==""||document.getElementById("getmonum").value==""||document.getElementById("getmoremain").value==""){
                            playSound();
                            alert("请检查工单信息！");
                            document.getElementById("getmoid").focus();
                        }else if(document.getElementById("model").value==""){
                            playSound();
                            alert("请输入机种名称！");
                            document.getElementById("model").focus();
                        }else if (document.getElementById("operator").value==""){
                            if (confirm("用户名已失效，请重新登录")) {
                                document.getElementById("operator").focus();
                                window.location="/user/logout";
                            }

                        }else if(document.getElementById("Nbox_num").value=="") {
                            alert("请输入内箱数量！");
                            playSound();
                            document.getElementById("Nbox_num").focus();
                        } else {
                            document.getElementById("inputproduct").focus();
                        }
                    }

                    function show(){
                        document.getElementById("outer").style.display="block";
                        setTimeout(hide,2000);
                    }

                    function hide() {
                        document.getElementById("outer").style.display="none";
                    }

                </script>
            </div><!-- /.panel panel-success -->
        </div><!-- /.dept_info -->
    </div><!-- /.hrms_dept_body -->
    <!-- 尾部-->
    <%@ include file="./commom/foot.jsp"%>

</div><!-- /.hrms_dept_container -->


<div id="outer">
    <div class="alert alert-success"  id="model1">操作成功！</div>
</div>


<!--内箱标签模板设计-->
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
                            <input type="text" name="Date" class="form-control" id="add_Date" value="2019-12-11">
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

<!--操作信息修改-->
<div class="modal fade updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="update">内盒包装操作信息修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal add_dept_form">
                    <div class="form-group">
                        <label for="Product_Name" class="col-sm-2 control-label">操作编号</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="operation_id">
                            <span id="operation_id_msg" class="msg"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_Lot_No" class="col-sm-2 control-label">产品SN号</label>
                        <div class="col-sm-8">
                            <input type="text"  class="form-control" id="update_sn">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_P_N" class="col-sm-2 control-label">原操作信息</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="operation_mesg">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_P_N" class="col-sm-2 control-label">修改为</label>
                        <div class="col-sm-8">
                            <select class="form-control" >
                                <option value="错误">错误</option>
                            </select>
                        </div>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="save" >保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script type="text/javascript">
    rows=oTable.rows;
    $("#operation_id").change(function () {
        const i = parseInt(document.getElementById("id").value);
        const d = parseInt(document.getElementById("operation_id").value);
        if (d<i){
            $(".msg").parent().parent().removeClass("has-error");
            $(".msg").parent().parent().addClass("has-success");
            document.getElementById("operation_id_msg").innerText="编号正确！";
            //返回SN号
            document.getElementById("update_sn").value=rows[d].cells[1].innerHTML;
            //返回操作信息
            document.getElementById("operation_mesg").value=rows[d].cells[3].innerHTML;
        }else {
            $(".msg").parent().parent().removeClass("has-success");
            $(".msg").parent().parent().addClass("has-error");
            document.getElementById("operation_id_msg").innerText="编号错误！";
        }
    })
    $("#save").click(function () {
        const productionsn = document.getElementById("update_sn").value;
        const d = parseInt(document.getElementById("operation_id").value);
        $.ajax({
            url:"/repack/updateModal",
            type:"PUT",
            data:{"productionSN":productionsn},
            success:function (result) {
                if (result.code==100){
                    alert("修改成功");
                    rows[d].cells[3].innerHTML="错误";
                    document.getElementById("right").value=parseInt(document.getElementById("right").value)-1;
                }
            }

        })
    })
</script>
</body>

<script type="text/javascript">


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
            url:"/repack/NeiMuban",
            type:"GET",
            data:{"Product_Name":Product_Name,"Lot_No":Lot_No,"P_N":P_N,"SPEC":SPEC,"Model_No":Model_No,"QTY":QTY,"Date":Date},
        });

    }


</script>


</html>
