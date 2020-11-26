<%--
  Created by IntelliJ IDEA.
  User: guyanbo
  Date: 2020/6/15
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>维修分析</title>
</head>
<body  onload="showTime()">

    <!-- 导航栏-->
    <%@ include file="./commom/head.jsp"%>


    <!-- 中间部分（左侧栏+表格内容） -->
        <!-- 左侧栏 -->

        <!-- 表格内容 -->


            <div class="panel panel-success">
                <div class="panel-heading" >
                    <div style="font-size: 20px">
                        <div align="center"><h4 style="font-size:40px; color: #ff5a19;" >制造中心维修分析工作站</h4></div>
                    </div>
                    <div id="result" align="right"></div>
                    <table class="table ">
                        <thead>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>操作人员：${name}</td>
                            <td>
                                <input type="button" class="btn btn-primary"  value="不良品接收" data-toggle="modal" data-target=".inbound-modal">
                                <input type="button" class="btn btn-primary" style="float: right" value="维修后返线" data-toggle="modal" data-target=".outbound-modal">
                            </td>
                        </tr>
                        <tr>
                            <th>【产品信息登录作业】</th>
                            <td>SSD SN：<input type="text" id="ssd_sn" onkeydown="selectbyssd()"></td>
                            <td>PCBA SN：<input type="text" id="pcba_sn"></td>
                            <td>FW版本：<input type="text" id="fw"></td>
                            <td><input class="btn btn-info" type="button" value="确定" onclick="showrepairmesg()"></td>
                        </tr>
                        </thead>
                    </table>
                </div>
                <div style="height: 380px;overflow-x: auto;overflow-y: auto;" id="tab">
                <table class="table table-bordered table-hover" id="showrepairmesg">
                    <thead>
                    <tr>
                        <th colspan="8" style="text-align: center">产品异常历史信息显示</th>
                    </tr>
                    <tr>
                        <th>日期</th>
                        <th>站别</th>
                        <th>ErrorCode</th>
                        <th>不良原因分析</th>
                        <th>处置动作</th>
                        <th>更换零件位置</th>
                        <th>维修分析员</th>
                        <th>维修分析日期</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
                <table class="table table-bordered table-hover" >
                    <thead>
                    <tr>
                        <th colspan="4" >【产品分析维修登录信息】</th>
                    </tr>
                    <tr>
                        <th style="width: 300px">请输入该产品不良原因分析</th>
                        <td><input type="text" style="width: 100%;border-width: 0" id="error_result"></td>
                        <th style="width: 300px">请选择不良处置动作</th>
                        <td>
                            <label><input type="radio" name="optionsRadios1" id="optionsRadios1_1" value="重新复测" > 重新复测&nbsp;&nbsp;&nbsp;</label>
                            <label><input type="radio" name="optionsRadios1" id="optionsRadios1_2" value="更换零件" > 更换零件&nbsp;&nbsp;&nbsp;</label>
                            <label><input type="radio" name="optionsRadios1" id="optionsRadios1_3" value="其他" > 其他&nbsp;&nbsp;&nbsp;</label>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 300px">复测开始站别指定</th>
                        <td>
                            <label><input type="radio" name="optionsRadios2" id="optionsRadios2_1" value="OC1" > OC1&nbsp;&nbsp;&nbsp;</label>
                            <label><input type="radio" name="optionsRadios2" id="optionsRadios2_2" value="OC2" > OC2&nbsp;&nbsp;&nbsp;</label>
                            <label><input type="radio" name="optionsRadios2" id="optionsRadios2_3" value="OC4" > OC4&nbsp;&nbsp;&nbsp;</label>
                            <label><input type="radio" name="optionsRadios2" id="optionsRadios2_4" value="CDI" > CDI&nbsp;&nbsp;&nbsp;</label>
                            <label><input type="radio" name="optionsRadios2" id="optionsRadios2_5" value="目检" > 目检&nbsp;&nbsp;&nbsp;</label>
                        </td>
                        <th style="width: 300px">请输入该产品不良零件位置</th>
                        <td><input type="text" style="width: 100%;border-width: 0" id="part"></td>
                    </tr>
                    <tr>
                        <th style="width: 300px">请输入新更换零件料号</th>
                        <td><input type="text" style="width: 100%;border-width: 0" id="element"></td>
                        <th style="width: 300px">新零件D/C</th>
                        <td><input type="text" style="width: 100%;border-width: 0" id="dc"></td>
                    </tr>
                    <tr>
                        <th style="width: 300px">其他说明</th>
                        <td><input type="text" style="width: 100%;border-width: 0" id="others"></td>
                        <th>
                            是否保存：
                            <input type="button" class="btn btn-success" align="center" value="是" onclick="updaterepairmesg()">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="button" class="btn btn-danger" align="center" value="否" >
                        </th>
                    </tr>
                    </thead>
                </table>
            </div><!-- /.panel panel-success -->


    <!-- /.hrms_dept_body -->
    <script type="text/javascript">
        function showTime() {
            var da;
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
                var obj = document.getElementById("result");
                obj.innerHTML = str;
                window.setTimeout("showTime()", 1000);
                //return t;
            }
            //var t=da + " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();

        }

        //根据SSD SN得到PCBA SN和FW
        function selectbyssd() {
            if (event.keyCode==13){
                var ssdsn=document.getElementById("ssd_sn").value;
                //alert(ssdsn);
                $.ajax({
                    url:"/repair/selectbyssd",
                    data:{"ssdsn":ssdsn},
                    type:"GET",
                    success:function (res) {
                       //alert("aa");
                        if (res.code === 100) {
                            //alert(res.extendInfo.pcbasn);
                            var pcbasn = res.extendInfo.pcbasn;
                            //alert(pcbasn);
                            var fw = res.extendInfo.fw;
                            document.getElementById("pcba_sn").value = pcbasn;
                            document.getElementById("fw").value = fw;
                        }
                    }
                })
            }
        }
        //保存数据
        function saverepairmesg() {
            var ssdsn=document.getElementById("ssd_sn").value;
            var pcbasn=document.getElementById("pcba_sn").value;
            alert(ssdsn+";"+pcbasn)
            var fw=document.getElementById("fw").value;
            var errordate="ew";
            var repair={"ssd_sn":ssdsn,"pcba_sn":pcbasn,"error_date":errordate,"error_area":"a","errorcode":"a","result":"s","operation":"d","part":"z","operator":"x","fw":fw};
            alert(repair);
            $.ajax({
                url:"/repair/saverepairmesg",
                type:"POST",
                data:repair

            })
        }
        function updaterepairmesg() {
            var ssdsn=document.getElementById("ssd_sn").value;
            var pcbasn=document.getElementById("pcba_sn").value;
            var result=document.getElementById("error_result").value;
            var operations=document.getElementsByName("optionsRadios1");
            var operation;
            for (var i=0;i<operations.length;i++){
                if (operations[i].checked){
                    operation=operations[i].value;
                }
            }
            var part=document.getElementById("part").value;
            var operator="<%=session.getAttribute("name")%>";
            var retestareas=document.getElementsByName("optionsRadios2");
            var retestarea="";
            for (var i=0;i<retestareas.length;i++){
                if (retestareas[i].checked){
                    retestarea=retestareas[i].value;
                }
            }
            var element=document.getElementById("element").value;
            var dc=document.getElementById("dc").value;
            var others=document.getElementById("others").value;
            var tb = document.getElementById("showrepairmesg");
            var rowNum=tb.rows.length;
            var error_area=tb.rows[rowNum-1].cells[1].innerHTML;
            var repair={"ssd_sn":ssdsn,"pcba_sn":pcbasn,"error_area":error_area,"result":result,"operation":operation,"part":part,"operator":operator,"retest_area":retestarea,"element":element,"dc":dc,"others":others};
            $.ajax({
                url:"/repair/updaterepairmesg",
                type:"PUT",
                data:repair,
                success:function (res) {
                    if (res.code==100){
                        alert("保存成功！");
                        showrepairmesg();
                        document.getElementById("element").value="";
                        document.getElementById("dc").value="";
                        document.getElementById("others").value="";
                        document.getElementById("part").value="";
                        document.getElementById("error_result").value="";
                    }
                }
            })
        }

        //显示数据到表格中
        function showrepairmesg() {
            var ssdsn=document.getElementById("ssd_sn").value;
            var pabasn=document.getElementById("pcba_sn").value;

            $.ajax({
                url:"/repair/showrepairmesg",
                type:"GET",
                data:{"ssdsn":ssdsn,"pcbasn":pabasn},
                success:function (res) {
                    if (res.code==100){
                        //清空表格
                        var tb = document.getElementById("showrepairmesg");
                        var tBodies=tb.tBodies[0];
                        var rowNum=tb.rows.length;
                        for (var i=2;i<rowNum;i++)
                        {
                            tb.deleteRow(i);
                            rowNum=rowNum-1;
                            i=i-1;
                        }
                        var repairs=res.extendInfo.repairs;
                        document.getElementById("fw").value=repairs[repairs.length-1].fw;
                        for (var i=0;i<repairs.length;i++)
                        {
                            //alert("第"+(i+1)+"行");
                            //1.创建行
                            var tr = document.createElement("tr");
                            //2.添加行到表格
                            tBodies.appendChild(tr);
                            //3.添加单元格
                            tr.appendChild(createNode("td",repairs[i].error_date));
                            tr.appendChild(createNode("td",repairs[i].error_area));
                            tr.appendChild(createNode("td",repairs[i].errorcode));
                            tr.appendChild(createNode("td",repairs[i].result));
                            tr.appendChild(createNode("td",repairs[i].operation));
                            tr.appendChild(createNode("td",repairs[i].part));
                            tr.appendChild(createNode("td",repairs[i].operator));
                            tr.appendChild(createNode("td",repairs[i].repair_date));
                        }
                    }else{
                        //清空表格
                        var tb = document.getElementById("showrepairmesg");
                        var rowNum=tb.rows.length;
                        for (var i=2;i<rowNum;i++)
                        {
                            tb.deleteRow(i);
                            rowNum=rowNum-1;
                            i=i-1;
                        }
                        alert("查询数据为空!");
                    }
                }
            })
        }
        //表格添加列
        function createNode(element,text) {
            var td = document.createElement(element);
            td.innerHTML = text;
            return td;
        }
    </script>
    <!-- 尾部-->
    <%@ include file="./commom/foot.jsp"%>

<!-- /.hrms_dept_container -->
    <!--不良品入库-->
    <div class="modal fade inbound-modal" tabindex="-1" role="dialog" aria-labelledby="reprint-modal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">不良品入库</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal reprint_form">
                        <div class="form-group">
                            <label for="inputssdsn" class="col-sm-2 control-label">SSD SN</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputssdsn">
                                <span id="msg" class="msg"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputpcbasn" class="col-sm-2 control-label">PCBA SN</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputpcbasn">
                                <!--<span id="msg" class="msg"></span>-->
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="errorarea" class="col-sm-2 control-label">站别</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="errorarea">
                                    <option value="OC1">OC1</option>
                                    <option value="OC2">OC2</option>
                                    <option value="OC4">OC4</option>
                                    <option value="CDI">CDI</option>
                                    <option value="目检">目检</option>
                                </select>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <!--<button type="button" class="btn btn-primary inbound_btn" onclick="inbound()">入库</button>-->
                    <input type="button" class="btn btn-primary" value="入库" onclick="inbound()">
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
        <script type="text/javascript">
            function inbound() {
                var ssdsn=document.getElementById("inputssdsn").value;
                var pcbasn=document.getElementById("inputpcbasn").value;
                var errorarea=document.getElementById("errorarea").value;
                $.ajax({
                    url:"/repair/inbound",
                    data:{"ssdsn":ssdsn,"pcbasn":pcbasn,"errorarea":errorarea},
                    success:function (res) {
                        if (res.code===100){
                            alert("入库成功！");
                        }else {
                            alert("未查询到不良数据！");
                        }
                    },
                })
            }
        </script>
    </div><!-- /.modal -->

    <!--维修后出库-->
    <div class="modal fade outbound-modal" tabindex="-1" role="dialog" aria-labelledby="outbound-modal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">维修后出库</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal reprint_form">
                        <div class="form-group">
                            <label for="inputssdsn2" class="col-sm-2 control-label">SSD SN</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputssdsn2">
                                <span id="msg2" class="msg"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputpcbasn2" class="col-sm-2 control-label">PCBA SN</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputpcbasn2">
                                <!--<span id="msg" class="msg"></span>-->
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="outbound_receiver" class="col-sm-2 control-label">接收人</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="outbound_receiver">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <!--<button type="button" class="btn btn-primary inbound_btn" onclick="inbound()">入库</button>-->
                    <input type="button" class="btn btn-primary" value="出库" onclick="outbound()">
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
        <script type="text/javascript">
            function outbound() {
                var ssdsn=document.getElementById("inputssdsn2").value;
                var pcbasn=document.getElementById("inputpcbasn2").value;
                var outbound_receiver=document.getElementById("outbound_receiver").value;
                $.ajax({
                    url:"/repair/outbound",
                    type:"PUT",
                    data:{"ssdsn":ssdsn,"pcbasn":pcbasn,"outbound_receiver":outbound_receiver},
                    success:function (res) {
                        if (res.code===100){
                            alert("出库成功！");
                        }
                    },
                })
            }
        </script>
    </div><!-- /.modal -->

</body>
</html>
