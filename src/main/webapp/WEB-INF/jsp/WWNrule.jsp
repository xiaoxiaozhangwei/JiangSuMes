<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: guyanbo
  Date: 2020/3/20
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WWN编码规则设定</title>
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
                        <div align="center"><h4 style="font-size:40px; color: #ff5a19;">WWN编码规则设定</h4></div>
                        <div id="result" align="right"></div>
                        <div id="form">
                            <table class="table table-hover" style="width: 80%">
                                <thead>
                                <tr>
                                    <!--
                                    <td>
                                        ERP料号：<input type="text" id="erp">
                                    </td>
                                    -->
                                    <td>
                                        规则名称：<input type="text" id="wwnrulename">
                                    </td>
                                    <td>
                                        码长度：<input type="text" id="wwnlength">
                                    </td>
                                    <!--
                                    <td>
                                        码类型：
                                        <select style="text-align: center">
                                            <option>一维码</option>
                                            <option>二维码</option>
                                        </select>
                                    </td>
                                    -->
                                    <td>
                                        <input type="button" value="保存" class="btn btn-default" onclick="saveWWNlabel()">
                                    </td>
                                </tr>
                                </thead>

                            </table>

                            <form class="label">
                                <table class="table table-hover" id="myTable01">
                                    <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>起始位</th>
                                        <th>结束位</th>
                                        <th>规则</th>
                                        <th>信息</th>
                                        <th style="text-align: right">
                                            <input type="button" value="+" class="btn btn-default" onclick="addhang()">
                                            &nbsp;
                                            <input type="button" value="-" class="btn btn-default" onclick="delhang()">
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody></tbody>
                                </table>
                            </form>
                        </div>
                    </div>

                </div>

                <div style="float: left;width: 40%;height: 500px;overflow-x: auto;overflow-y: auto; background-color:  #9cc0ff" id="tab" >
                    <table class="table table-bordered table-hover" id="myTable02" >
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>SN规则</th>
                            <th>SN长度</th>
                            <!--<th>ERP料号</th>-->
                        </tr>
                        </thead>
                        <tbody id="table">
                        <c:forEach items="${wwnrules}" var="wwnrules" varStatus="id">
                            <tr>
                                <td>${id.count}</td>
                                <td>${wwnrules.wwnrulename}</td>
                                <td>${wwnrules.wwnlength}</td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div style="margin-left: 40%;height: 500px;background-color: #a6c9ff ">
                    <table class="table table-bordered table-hover" id="myTable03">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>起始位</th>
                            <th>结束位</th>
                            <th>规则</th>
                            <th>信息</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <script type="text/javascript">
                    var oTable = document.getElementsByTagName("table")[1];
                    var oTable3 = document.getElementsByTagName("table")[3];
                    var tBodies = oTable.tBodies[0];
                    var tBodies2 = oTable3.tBodies[0];
                    var id = 1;



                    //添加行
                    function addhang() {
                        //1.创建行
                        var tr = document.createElement("tr");
                        //2.添加行到表格
                        tBodies.appendChild(tr);
                        //3.添加单元格
                        tr.appendChild(createNode("td", id ));
                        tr.appendChild(createNode("td", "<input type='text' id='start"+id+"'>"));
                        tr.appendChild(createNode("td", "<input type='text' id='over"+id+"'>"));
                        tr.appendChild(createNode("td", "<select id='wwnrule"+id+"'  onchange='wwnrule()' >" +
                            "<option >——请选择——</option>" +
                            "<option value='年'>年</option>" +
                            "<option value='周'>周</option>" +
                            "<option value='固定码'>固定码</option>" +
                            "<option value='流水码'>流水码</option>" +
                            "</select>"));
                        tr.appendChild(createNode("td", "<select id='year"+id+"' hidden>" +
                            "<option>上一年</option>" +
                            "<option>本年</option>" +
                            "</select>" +
                            "<select id='week"+id+"' hidden>" +
                            "<option>上一周</option>" +
                            "<option>本周</option>" +
                            "<option>下一周</option>" +
                            "</select>" +
                            "<input type='text' id='fixedcode"+id+"' hidden>" +
                            "<select  id='pipelinedcode"+id+"' hidden>" +
                            "<option>日结</option>" +
                            "<option>周结</option>" +
                            "<option>月结</option>" +
                            "</select>"));
                        id += 1;
                    }

                    //表格添加列
                    function createNode(element, text) {
                        var td = document.createElement(element);
                        td.innerHTML = text;
                        return td;
                    }

                    //删除行
                    function delhang() {
                        var tb = document.getElementById('myTable01');
                        tb.deleteRow(id-1);
                        id-=1;
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
                            obj.innerHTML = str;
                            window.setTimeout("showTime()", 1000);
                            return t;

                        }
                    }

                    //规则下拉框选择
                    function wwnrule() {
                        //alert("a2");
                        var i=1;
                        for (i==1;i<id;i++){
                            var r=document.getElementById("wwnrule"+i).value;
                            //alert(i+";"+id)
                            switch (r) {
                                case "年":
                                    $("#year"+i).show();
                                    $("#week"+i).hide();
                                    $("#fixedcode"+i).hide();
                                    $("#pipelinedcode"+i).hide();
                                    break;
                                case "周":
                                    $("#year"+i).hide();
                                    $("#week"+i).show();
                                    $("#fixedcode"+i).hide();
                                    $("#pipelinedcode"+i).hide();
                                    break;
                                case "固定码":
                                    $("#year"+i).hide();
                                    $("#week"+i).hide();
                                    $("#fixedcode"+i).show();
                                    $("#pipelinedcode"+i).hide();
                                    break;
                                case "流水码":
                                    $("#year"+i).hide();
                                    $("#week"+i).hide();
                                    $("#fixedcode"+i).hide();
                                    $("#pipelinedcode"+i).show();
                                    break;
                            }
                        }

                    }

                    //保存WWN规则数据
                    function saveWWNlabel() {

                        if (id==1){
                            alert("请添加规则！");
                        }else {
                            var wwnrulename=document.getElementById("wwnrulename").value;
                            var wwnlength=parseInt(document.getElementById("wwnlength").value);
                            //var erp=document.getElementById("erp").value;

                            //var i=1;
                            for (var i=1;i<id;i++){
                                var start=parseInt(document.getElementById("start"+i).value);
                                var over=parseInt(document.getElementById("over"+i).value);
                                var rule=document.getElementById("wwnrule"+i).value;
                                var mesg;
                                switch (rule) {
                                    case "年":
                                        mesg=document.getElementById("year"+i).value;
                                        break;
                                    case "周":
                                        mesg=document.getElementById("week"+i).value;
                                        break;
                                    case "固定码":
                                        mesg=document.getElementById("fixedcode"+i).value;
                                        break;
                                    case "流水码":
                                        mesg=document.getElementById("pipelinedcode"+i).value;
                                        break;
                                }

                                $.ajax({
                                    url:"/label/insertwwnrule",
                                    data:{"wwnrulename":wwnrulename, "wwnlength":wwnlength, "start":start, "over":over, "rule":rule, "mesg":mesg},
                                    type:"POST"
                                })
                            }
                            alert("保存成功！");
                            window.location.href="/label/selectwwnrule";
                        }


                    }

                    //表格2点击事件
                    $( "#myTable02 tr" ).click( function() {//给每行绑定了一个点击事件：
                        var td = $( this ).find( "td" );//this指向了当前点击的行，通过find我们获得了该行所有的td对象。
                        // 题中说到某个td，为了演示所以我们假设是要获得第3个td的数据。
                        var data = td.eq( 1 ).html();
                        //alert(data);
                        //通过eq可以得到具体的某个td对象，从而得到相应的数据
                        rulesdetail(data);
                    });

                    //根据规则名称返回详细信息到表格3
                    function rulesdetail(wwnrulename) {
                        //alert(snrulename+"2");
                        $.ajax({
                            url:"/label/selectwwnrulebyname/"+wwnrulename,
                            type:"GET",
                            success:function (res) {
                                //alert("3");
                                if (res.code==100){
                                    //alert("4");
                                    //清空表格
                                    var tb = document.getElementById('myTable03');
                                    var rowNum=tb.rows.length;
                                    for (i=1;i<rowNum;i++)
                                    {
                                        tb.deleteRow(i);
                                        rowNum=rowNum-1;
                                        i=i-1;
                                    }
                                    //alert("5");
                                    var rules = res.extendInfo.rules;
                                    //alert(rules.length+"行");
                                    for (var i=0;i<rules.length;i++)
                                    {
                                        //alert("第"+(i+1)+"行");
                                        //1.创建行
                                        var tr = document.createElement("tr");
                                        //alert("1");
                                        //2.添加行到表格
                                        tBodies2.appendChild(tr);
                                        //alert("2");

                                        //3.添加单元格
                                        tr.appendChild(createNode("td",i+1));
                                        tr.appendChild(createNode("td",rules[i].wwnstart));
                                        tr.appendChild(createNode("td",rules[i].wwnover));
                                        tr.appendChild(createNode("td", rules[i].wwnrule));
                                        tr.appendChild(createNode("td", rules[i].wwnmesg));

                                    }

                                }
                            }
                        })
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

