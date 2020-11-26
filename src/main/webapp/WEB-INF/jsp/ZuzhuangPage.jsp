<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: guyanbo
  Date: 2019/10/15
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>组装信息</title>
</head>
<body>
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
                        <h4 style="font-size:40px; color: #ff5a19;" >组装信息查询</h4></div>
                    <div id="result" align="right"></div>
                    <div id="form">
                        <form action="" name="link" class="link">
                            <table class="table">
                                <tr >
                                    <td>
                                        工单号码：<select name="moId" id="getmoid"  onchange="updatemoid()" >
                                        <option value="">请选择</option>
                                    </select>
                                    </td>
                                    <td>操作员：<input type="text" name="operator"  ></td>

                                    <td>机种名称：<input type="text" name="devicename" id="getdevicename"  ></td>
                                    <td>
                                        PCBA供应商：
                                        <select name="gys" id="gys"  onchange="onchanged()">
                                            <option value="">--请选择--</option>
                                            <option value="无锡荣志">无锡荣志</option>
                                            <option value="东莞汇钜">东莞汇钜</option>
                                        </select>
                                    </td>

                                </tr>

                                <tr>
                                    <td>
                                        产品SN：<input type="text" name="productionSN" id="inputproduct"  onkeydown="onKeyUsername();"  />
                                    </td>
                                    <td>
                                        PCBA SN：<input type="text" name="pcbaSN" id="inputpcba"  onkeydown="check()"/>
                                    </td>
                                    <td>
                                        日期：<input type="date" name="getTime">
                                    </td>
                                    <td>操作信息：<input type="text" name="operation"></td>
                                    <td><input type="button" value="查询"  class="select_btn"></td>
                                </tr>
                            </table>
                        </form>
                    </div>

                    <ol class="breadcrumb">
                        <li class="active">组装信息/</li>
                        <a href="/link/selectAll">显示全部</a>
                    </ol>
                </div>

                <div style="height: 450px;overflow-x: auto;overflow-y: auto;">
                    <!-- Table -->
                    <table  class="table table-bordered " id="myTable02" >
                        <thead >
                        <th>编号</th>
                        <th>产品 BARCODE</th>
                        <th>PCBA BARCODE</th>
                        <th>机种名称</th>
                        <th>操作信息</th>
                        <th>操作员</th>
                        <th>作业时间</th>
                        <th>工单号</th>
                        </thead>
                        <tbody >
                        <c:forEach items="${zuzhuang}" var="z">
                            <tr>
                                <td>${z.linkId}</td>
                                <td>${z.productionSN}</td>
                                <td>${z.pcbaSN}</td>
                                <td>${z.devicename}</td>
                                <td>${z.operation}</td>
                                <td>${z.operator}</td>
                                <td>${z.times}</td>
                                <td>${z.moId}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                共<span class="badge" id="count"></span>条数据
                <script type="text/javascript">
                    var oTable = document.getElementsByTagName("table")[1];
                    var tBodies = oTable.tBodies[0];

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
                    //查询
                    $(".select_btn").click(function () {
                        $.ajax({
                            url:"/link/selecting",
                            type:"GET",
                            data:$(".link").serialize(),
                            success:function (result) {
                                if (result.code==100){
                                    //清空表格
                                    var tb = document.getElementById('myTable02');
                                    var rowNum=tb.rows.length;
                                    for (i=1;i<rowNum;i++)
                                    {
                                        tb.deleteRow(i);
                                        rowNum=rowNum-1;
                                        i=i-1;
                                    }
                                    var selectzz = result.extendInfo.zs;
                                    for (var i=0;i<selectzz.length;i++)
                                    {
                                        //alert("第"+(i+1)+"行");
                                        //1.创建行
                                        var tr = document.createElement("tr");
                                        //2.添加行到表格
                                        tBodies.appendChild(tr);
                                        //3.添加单元格
                                        tr.appendChild(createNode("td",i+1));
                                        tr.appendChild(createNode("td",selectzz[i].productionSN));
                                        tr.appendChild(createNode("td",selectzz[i].pcbaSN));
                                        tr.appendChild(createNode("td", selectzz[i].devicename));
                                        tr.appendChild(createNode("td", selectzz[i].operation));
                                        tr.appendChild(createNode("td",selectzz[i].operator));
                                        tr.appendChild(createNode("td", selectzz[i].getTime));
                                        tr.appendChild(createNode("td", selectzz[i].moId));
                                    }

                                    $("#count").text(selectzz.length);
                                }else {
                                    alert("查询信息错误！");
                                }
                            }
                        });
                    })
                    //表格添加列
                    function createNode(element,text) {
                        var td = document.createElement(element);
                        td.innerHTML = text;
                        return td;
                    }
                </script>
            </div><!-- /.panel panel-success -->
        </div><!-- /.dept_info -->

    </div><!-- /.hrms_dept_body -->

    <!-- 尾部-->
    <%@ include file="./commom/foot.jsp"%>

</div><!-- /.hrms_dept_container -->
</body>
</html>
