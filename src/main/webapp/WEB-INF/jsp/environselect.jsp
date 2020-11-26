<%--
  Created by IntelliJ IDEA.
  User: guyanbo
  Date: 2020/1/16
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>5S成绩查询</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">


</head>
<body  onload="showTime()">
<div >
    <!-- 导航栏-->
    <%@ include file="./commom/head.jsp"%>


    <!-- 中间部分（左侧栏+表格内容） -->
    <div >
        <!-- 左侧栏 -->
        <%@ include file="commom/left_ziliao.jsp"%>

        <!-- 表格内容 -->
        <div class="dept_info col-sm-10">

            <div class="panel panel-success">

                <!-- 路径导航 -->
                <div class="panel-heading">

                    <div align="center">
                        <h4 style="font-size:40px; color: #ff5a19;" >5S成绩查询</h4>
                    </div>
                    <div id="result" align="right">
                    </div>

                    <ol class="breadcrumb">
                        <li><a href="/environ/AvgByWeek">本月成绩</a></li>
                        <!--<li class="active">部门信息</li>-->
                    </ol>
                    <table class="table table-bordered table-hover" style="background-color: white" >
                        <tr >
                            <th >
                                部门
                            </th>
                            <th style="width: 13%">区域</th>
                            <th style="width: 13%">第1周</th>
                            <th style="width: 13%">第2周</th>
                            <th style="width: 13%">第3周</th>
                            <th style="width: 13%">第4周</th>
                            <th style="width: 13%">第5周</th>
                            <th style="width: 13%">月度平均分</th>
                        </tr>
                        <tr>
                            <td rowspan="4" style="vertical-align: middle">
                                生产部
                            </td>
                            <td>
                                RDT区
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="RDT1" value="${RDT1}" >
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="RDT2" value="${RDT2}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="RDT3" value="${RDT3}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="RDT4" value="${RDT4}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="RDT5" value="${RDT5}">
                            </td>
                            <td>${RDT}</td>
                        </tr>
                        <tr>
                            <td>
                                OC3区
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="OC31" value="${OC31}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="OC32" value="${OC32}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="OC33" value="${OC33}" >
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="OC34" value="${OC34}" >
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="OC35" value="${OC35}" >
                            </td>
                            <td>${OC3}</td>
                        </tr>

                        <tr>
                            <td>
                                BIT区
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="BIT1" value="${BIT1}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="BIT2" value="${BIT2}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="BIT3" value="${BIT3}" >
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="BIT4" value="${BIT4}" >
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="BIT5" value="${BIT5}" >
                            </td>
                            <td>${BIT}</td>
                        </tr>

                        <tr>
                            <td>
                                组装区
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="link1" value="${link1}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="link2" value="${link2}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="link3" value="${link3}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="link4" value="${link4}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="link5" value="${link5}">
                            </td>
                            <td>${link}</td>
                        </tr>
                        <tr>
                            <td rowspan="5" style="vertical-align: middle">
                                品质/IE
                            </td>
                            <td>
                                仓库区
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="store1" value="${store1}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="store2" value="${store2}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="store3" value="${store3}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="store4" value="${store4}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="store5" value="${store5}">
                            </td>
                            <td>${store}</td>
                        </tr>
                        <tr>
                            <td>
                                IQC区
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="IQC1" value="${IQC1}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="IQC2" value="${IQC2}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="IQC3" value="${IQC3}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="IQC4" value="${IQC4}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="IQC5" value="${IQC5}">
                            </td>
                            <td>${IQC}</td>
                        </tr>

                        <tr>
                            <td>
                                抽测区
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="extract1" value="${extract1}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="extract2" value="${extract2}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="extract3" value="${extract3}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="extract4" value="${extract4}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="extract5" value="${extract5}">
                            </td>
                            <td>${extract}</td>
                        </tr>
                        <tr>
                            <td>
                                换鞋区
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="changeshoe1" value="${changeshoe1}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="changeshoe2" value="${changeshoe2}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="changeshoe3" value="${changeshoe3}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="changeshoe4" value="${changeshoe4}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="changeshoe5" value="${changeshoe5}">
                            </td>
                            <td>${changeshoe}</td>
                        </tr>
                        <tr>
                            <td>
                                走道
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="walk1" value="${walk1}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="walk2" value="${walk2}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="walk3" value="${walk3}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="walk4" value="${walk4}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="walk5" value="${walk5}">
                            </td>
                            <td>${walk}</td>
                        </tr>
                        <tr>
                            <td rowspan="4" style="vertical-align: middle">
                                工程部
                            </td>
                            <td>
                                办公室
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="office1" value="${office1}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="office2" value="${office2}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="office3" value="${office3}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="office4" value="${office4}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="office5" value="${office5}">
                            </td>
                            <td>${office}</td>
                        </tr>
                        <tr>
                            <td>
                                RMA区
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="RMA1" value="${RMA1}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="RMA2" value="${RMA2}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="RMA3" value="${RMA3}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="RMA4" value="${RMA4}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="RMA5" value="${RMA5}">
                            </td>
                            <td>${RMA}</td>
                        </tr>
                        <tr>
                            <td>
                                休息区
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="rest1" value="${rest1}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="rest2" value="${rest2}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="rest3" value="${rest3}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="rest4" value="${rest4}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="rest5" value="${rest5}">
                            </td>
                            <td>${rest}</td>
                        </tr>
                        <tr>
                            <td>
                                茶水间
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="tea1" value="${tea1}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="tea2" value="${tea2}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="tea3" value="${tea3}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="tea4" value="${tea4}">
                            </td>
                            <td>
                                <input type="text"  style="border: none;width: 100%" id="tea5" value="${tea5}">
                            </td>
                            <td>${tea}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
