
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";


%>
<html>
<head>
    <title>PCBA看板信息页面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <!--界面刷新-->
    <meta http-equiv="refresh" content="120">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="../css/cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="../js/cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="../js/cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body  onload="showTime()">


<!-- 表格内容 -->
<div class="dept_info col-sm-10" style="width: 100%;height: 100%">

    <div class=" panel-success" >
        <!--
        <marquee >
            <a style="color: red;font-size: 40px">热烈欢迎华为技术有限公司各位领导和专家莅临指导</a>
        </marquee>
-->
        <!-- 路径导航 -->
        <div class="panel-heading" >
            <!--            <div style="float: left">
                <img src="${pageContext.request.contextPath}/static/img/log3.png" width="250px">
            </div>
-->
            <div align="center">
                <h4 style="font-size:40px; color: #ff5a19;text-align: center" >智能制造工业物联信息化管理系统</h4></div>
            <div id="result" align="right"></div>
        </div><!-- /.dept_info -->
    </div><!-- /.hrms_dept_body -->

    <br>
    PCBA测试：
    <table class="table table-bordered table-hover">
        <tr>
            <th style="text-align: center;background-color: #eb8c4b;width: 15%">测试站别</th>
            <th style="text-align: center;background-color: #ce9776">OC1</th>
            <!--<th style="text-align: center;background-color: #bd998e">RDT</th>-->
            <th style="text-align: center;background-color: #bd998e">OC2</th>
            <!--<th style="text-align: center;background-color: #bd998e">BIT1</th>-->
            <th style="text-align: center;background-color: #ce9776">BIT</th>
            <!--<th style="text-align: center;background-color: #bd998e">OC3</th>-->
            <th style="text-align: center;background-color: #bd998e">QC</th>
        </tr>
        <tr>
            <th style="text-align: center; background-color: #eb8c4b">良品</th>
            <td style="text-align: center;background-color: #ebad87">${oc1pass}</td>
            <!--<td style="text-align: center;background-color: #cfaa9f"></td>-->
            <td style="text-align: center;background-color: #cfaa9f">${oc2pass}</td>
            <!--<td style="text-align: center;background-color: #cfaa9f"></td>-->
            <td style="text-align: center;background-color: #ebad87">${BitPass}</td>
            <td style="text-align: center;background-color: #cfaa9f">${qcPASS}</td>
            <!--<td style="text-align: center;background-color: #ebad87"></td>-->
        </tr>
        <tr>
            <th style="text-align: center; background-color: #eb8c4b">不良品</th>
            <td style="text-align: center;background-color: #ebad87">${oc1NG}</td>
            <!--<td style="text-align: center;background-color: #cfaa9f"></td>-->
            <td style="text-align: center;background-color: #cfaa9f">${oc2NG}</td>
            <!--<td style="text-align: center;background-color: #cfaa9f"></td>-->
            <td style="text-align: center;background-color: #ebad87">${BitNg}</td>
            <td style="text-align: center;background-color: #cfaa9f">${qcNG}</td>
            <!--<td style="text-align: center;background-color: #ebad87"></td>-->
        </tr>
        <tr>
            <th style="text-align: center; background-color: #eb8c4b">小计</th>
            <td style="text-align: center;background-color: #ebad87">${oc1total}</td>
            <!--<td style="text-align: center;background-color: #cfaa9f"></td>-->
            <td style="text-align: center;background-color: #cfaa9f">${oc2total}</td>
            <!--<td style="text-align: center;background-color: #cfaa9f"></td>-->
            <td style="text-align: center;background-color: #ebad87">${BitTotal}</td>
            <td style="text-align: center;background-color: #cfaa9f">${qctotal}</td>
            <!--<td style="text-align: center;background-color: #ebad87"></td>-->
        </tr>
        <tr>
            <th style="text-align: center; background-color: #eb8c4b">良率</th>
            <td style="text-align: center;background-color: #ebad87">${oc1percent}</td>
            <!--<td style="text-align: center;background-color: #cfaa9f"></td>-->
            <td style="text-align: center;background-color: #cfaa9f">${oc2percent}</td>
            <!--<td style="text-align: center;background-color: #cfaa9f"></td>-->
            <td style="text-align: center;background-color: #ebad87">${BitPercent}</td>
            <td style="text-align: center;background-color: #cfaa9f">${qcpercent}</td>
            <!--<td style="text-align: center;background-color: #ebad87"></td>-->
        </tr>
    </table>


</div><!-- /.hrms_dept_container -->


<script type="text/javascript">
    var oTable = document.getElementsByTagName("table")[0];
    var rows=oTable.rows;

    var oTable2 = document.getElementsByTagName("table")[1];
    var rows2=oTable2.rows;


    function showTime() {
        var da = '';
        var now = new Date();
        var now_m = now.getMonth() + 1;
        now_m = (now_m < 10) ? '0' + now_m : now_m;
        var now_d = now.getDate();
        now_d = (now_d < 10) ? '0' + now_d : now_d;
        da = now.getFullYear() + '-' + now_m + '-' + now_d;

        if (now.getHours()===0&&now.getSeconds()==0&&now.getMinutes()==0){
            $.ajax({
                url:"/pack/resetcount"
            })
        }

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
            return t;
        }            var t=da + " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();

    }
    function selectcount() {

        $.ajax({
            url:"${pageContext.request.contextPath}/check/selectcount",
            type:"GET"
        });

    }

</script>

</body>


</html>
