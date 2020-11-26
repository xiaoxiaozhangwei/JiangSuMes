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
    <title>组装作业信息页面</title>
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
        <%@ include file="commom/left-xitong.jsp"%>

        <!-- 表格内容 -->
        <div class="dept_info col-sm-10">

            <div class="panel panel-success">

                <!-- 路径导航 -->
                <div class="panel-heading">

                    <div align="center">
                        <h4 style="font-size:40px; color: #ff5a19;" >组装作业信息登录</h4>
                    </div>
                   <!-- <div id="result" align="right"></div>
                    <div id="form">
                        <form action="" name="link" class="link">
                            <table class="table">
                                <tr >
                                    <td align="center">
                                        <a role="button" class="btn btn-primary" data-toggle="modal" data-target=".emp-add-modal">上班</a>
                                    </td>
                                    <td  align="center"><a role="button" class="btn btn-primary" data-toggle="modal" data-target=".emp-add-modal">下班</a></td>
                                    <td align="center"><a role="button" class="btn btn-primary emp_edit_btn" onclick=" window.location.reload();">更换工单</a></td>
                                </tr>

                            </table>
                        </form>
                    </div>

                  -->
                </div>




                <ol class="breadcrumb">
                    <li class="active">操作信息</li>
                </ol>
                <form id="my">
                <div style="height: 450px;overflow-x: auto;overflow-y: auto;" id="tab">
                    <!-- Table -->

                    <table  class="table table-bordered " id="myTable02" >
                        <thead>
                        <th>站别信息</th>
                        <th colspan="2">操作员信息</th>
                        </thead>
                        <tbody >
                        <tr>
                            <th>线别</th>
                            <th colspan="2">请选择线别:
                                <select name="line_number"  id="line">
                                <option  value="线别一" selected="selected">&nbsp;线别1&nbsp;</option>
                                <option value ="线别二" >&nbsp;线别2&nbsp;</option>
                                <option value="线别三" >&nbsp;线别3&nbsp;</option>
                                <option value="线别四">&nbsp;线别4&nbsp;</option>
                                    <option value="NPI线">NPI线</option>
                            </select>
                            </th>
                        </tr>
                        <tr >
                            <th>作业工单信息</th>
                            <th colspan="2">
                                <!--<input type="text" name="mo" class="form-control"  placeholder="请输入工单信息: " id="mo">-->
                                <select name="mo" id="getmoid"  >
                                    <option value="">请选择</option>
                                </select>
                            </th>
                        </tr>
                        <tr >
                            <th>A01 贴合的作业人员</th>
                            <th ><input type="text" name="A01name1" class="form-control"  placeholder="请输入贴合的操作人员信息: " id="A01"></th>
                            <th ><input type="text" name="A01name2" class="form-control"  placeholder="请输入贴合的操作人员信息: " id="A01_2"></th>

                        </tr>
                        <tr >
                            <th>A02 锁螺丝的作业人员</th>
                            <th ><input type="text" name="A02name1" class="form-control"  placeholder="请输入锁螺丝的操作人员信息: "  id="A02"></th>
                            <th ><input type="text" name="A02name2" class="form-control"  placeholder="请输入锁螺丝的操作人员信息: "  id="A02_2"></th>
                        </tr>
                        <tr >
                            <th>A03 组装的作业人员</th>
                            <th ><input type="text" name="A03name1" class="form-control"  placeholder="请输入组装的操作人员信息: "  id="A03"></th>
                            <th ><input type="text" name="A03name2" class="form-control"  placeholder="请输入组装的操作人员信息: "  id="A03_2"></th>
                        </tr>
                        <tr >
                            <th >A04 锁面盖的作业人员</th>
                            <th ><input type="text" name="A04name1" class="form-control"  placeholder="请输入锁面盖的操作人员信息: "  id="A04"></th>
                            <th ><input type="text" name="A04name2" class="form-control"  placeholder="请输入锁面盖的操作人员信息: "  id="A04_2"></th>
                        </tr>
                        <tr >
                            <th>A05 CDI&OC4的作业人员</th>
                            <th ><input type="text" name="A05name1" class="form-control"  placeholder="请输入CDI&OC4的操作人员信息: "  id="A05"></th>
                            <th><input type="text" name="FW" class="form-control"  placeholder="请输入FW版本信息: "  id="FW"></th>
                        </tr>
                        <tr  >
                          <!--<th  style="text-align:center;" colspan="2" ><a  role="button" class="btn btn-primary emp_edit_btn" onclick="window.location.href='/xt/zuzhuang'" >开始组装操作</a></th>-->

                            <th colspan="3" style="text-align:center;" colspan="2" ><a  role="button" class="btn btn-primary emp_edit_btn" onclick="add()" >开始组装操作</a></th>
                        </tr>
                        </tbody>
                    </table>
                </div>

                </form>


                <script type="text/javascript">
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



                    function add()
                    {
                        if (document.getElementById("getmoid").value==""){
                            alert("请输入工单的信息！");
                            document.getElementById("getmoid").focus();
                        }else if(document.getElementById("A01").value==""){
                            alert("请输入A01 贴合的信息！");
                            document.getElementById("A01").focus();
                        }else if(document.getElementById("A02").value==""){
                            alert("请输入A02 锁螺丝的信息！");
                            document.getElementById("A02").focus();
                        }else if (document.getElementById("A03").value==""){
                            alert("请输入A03 组装的信息！");
                            document.getElementById("A03").focus();
                        }else if (document.getElementById("A04").value==""){
                            alert("请输入A04 锁面盖的信息！");
                            document.getElementById("A04").focus();
                        }else if (document.getElementById("A05").value==""){
                            alert("请输入A05 CDI&OC4的信息！");
                            document.getElementById("A05").focus();
                        }else if (document.getElementById("FW").value==""){
                            alert("请输入FW的信息！");
                            document.getElementById("FW").focus();
                        }
                        else
                        {
                            var mo  =document.getElementById("getmoid").value;
                            var line= document.getElementById("line").value;
                            var A01=document.getElementById("A01").value;
                            var A02=document.getElementById("A02").value;
                            var A03=document.getElementById("A03").value;
                            var A04=document.getElementById("A04").value;
                            var A05=document.getElementById("A05").value;
                            var A01_2=document.getElementById("A01_2").value;
                            var A02_2=document.getElementById("A02_2").value;
                            var A03_2=document.getElementById("A03_2").value;
                            var A04_2=document.getElementById("A04_2").value;
                            var FW = document.getElementById("FW").value;

                        // alert("组装作业信息登录")
                           // alert(mo)
                            $.ajax({
                                url:"/zuzhuangwork/selectName",
                                data:{"mo":mo,"line":line,"A01":A01,"A01_2":A01_2,"A02":A02,"A02_2":A02_2,"A03":A03,"A03_2":A03_2,"A04":A04,"A04_2":A04_2,"A05":A05,"FW":FW},
                                type:"GET",
                                success:function (result)
                                {
                                    if (result.code == 100){
                                        alert("信息添加成功");
                                        //var mo=document.getElementById("getmoid").value;
                                        window.location.href="${pageContext.request.contextPath}/xt/zuzhuang";
                                   //    $.ajax({
                                   //        url:"${pageContext.request.contextPath}/xt/Zuzhuang/"+mo,
                                   //        type:"GET",
                                   //    }
//
                                    }else {
                                        alert(result.extendInfo.emp_add_error);
                                    }
                                }


                            });
                        }




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
