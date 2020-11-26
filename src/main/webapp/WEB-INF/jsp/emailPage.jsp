<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>邮箱管理页面</title>

</head>
<body>
<div class="hrms_container">
    <%@ include file="./commom/head.jsp" %>
    <div class="hrms_body" style="position:relative; top:-15px;">
        <%@ include file="./commom/left_ziliao.jsp" %>

        <div class="emp_info col-sm-10">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h4 style="font-size:40px; color: #ff5a19;" align="center">邮箱信息管理</h4>
                    <button type="button" class="btn btn-info" class="emp_add_btn" data-toggle="modal"
                            data-target="#add_email">添加邮箱
                    </button>
                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
                    <select name="selectEmail" id="selectEmail">
                        <option value="#">请根据条件查询</option>
                        <option value="0">全部</option>

                        <option value="试产组">试产组</option>
                        <option value="NPI组">NPI组</option>
                    </select>
                    <button type="button" class="btn btn-success" onclick="selectGroupEmail()">查询</button>
                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btn-primary" onclick="sendEmail()">邮件发送</button>
                </div>


                <!-- Table -->
                <table class="table table-bordered table-hover" id="email_table">
                    <thead>
                    <th>编号</th>
                    <th>邮箱账号</th>
                    <th>使用者</th>
                    <th>群发分组</th>
                    <th>操作</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${emails}" var="z" varStatus="obj">
                        <tr>
                            <td>${obj.count}</td>
                            <td>${z.account}</td>
                            <td>${z.name}</td>
                            <td>${z.groupEmail}</td>
                            <td>

                                <a href="${pageContext.request.contextPath}/gd/updateMo/" role="button" class="btn btn-primary emp_edit_btn" data-toggle="modal" data-target=".emp-update-modal">编辑</a>
                                <a  role="button" class="btn btn-danger email_delete_btn">删除</a>

                            </td>
                        </tr>
                    </c:forEach>


                    </tbody>
                </table>

                <div class="panel-body">
                    <div class="table_items">
                        当前第<span class="badge">${curPage}</span>页，共有<span class="badge">${totalPages}</span>页，总记录数<span
                            class="badge" id="count">${totalItems}</span>条。
                    </div>
                    <nav aria-label="Page navigation" class="pull-right">
                        <ul class="pagination">
                            <li><a href="/gd/getEmpList?pageNo=1">首页</a></li>

                            <c:if test="${curPage==1}">
                                <li class="disabled">
                                    <a href="#" aria-label="Previous" class="prePage">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${curPage!=1}">
                                <li>
                                    <a href="#" aria-label="Previous" class="prePage">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <c:forEach begin="1" end="${totalPages<5?totalPages:5}" step="1" var="itemPage">
                                <c:if test="${curPage == itemPage}">
                                    <li class="active"><a href="/emp/getEmpList?pageNo=${itemPage}">${itemPage}</a></li>
                                </c:if>
                                <c:if test="${curPage != itemPage}">
                                    <li><a href="/emp/getEmpList?pageNo=${itemPage}">${itemPage}</a></li>
                                </c:if>
                            </c:forEach>

                            <c:if test="${curPage==totalPages}">
                                <li class="disabled" class="nextPage">
                                    <a href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${curPage!=totalPages}">
                                <li>
                                    <a href="#" aria-label="Next" class="nextPage">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <li><a href="/emp/getEmpList?pageNo=${totalPages}">尾页</a></li>
                        </ul>
                    </nav>
                </div>
            </div><!-- /.panel panel-success -->
        </div><!-- /.emp_info -->
        <%@ include file="employeeAdd.jsp" %>
        <%@ include file="employeeUpdate.jsp" %>


    </div><!-- /.hrms_body -->
    <!-- 尾部 -->
    <%@ include file="./commom/foot.jsp" %>
    <%@ include file="emailAdd.jsp" %>
</div><!-- /.container -->


<script>
    /*
    $(function () {
        //上一页
        var curPage = ${curPage};
        var totalPages = ${totalPages};
        $(".prePage").click(function () {
            if (curPage > 1) {
                var pageNo = curPage - 1;
                $(this).attr("href", "/emp/getEmpList?pageNo=" + pageNo);
            }
        });
        //下一页
        $(".nextPage").click(function () {
            if (curPage < totalPages) {
                var pageNo = curPage + 1;
                $(this).attr("href", "/emp/getEmpList?pageNo=" + pageNo);
            }
        });
    })
*/
    <!-- ==========================员工删除操作=================================== -->
function sendEmail()
{
    $.ajax({
        url: "${pageContext.request.contextPath}/Email/sendEmail",
        type:  "GET",
        success: function (result) {
            if (result.code == 100) {
                alert("邮件发送成功！");

            }
            else {
                alert("邮件发送失败！");
            }
        }
    });



}

    $(".email_delete_btn").click(function () {
        //var curPage = ${curPage};
        var delEmpId = $(this).parent().parent().find("td:eq(1)").text();
        var delEmpName = $(this).parent().parent().find("td:eq(2)").text();
        if (confirm("确认删除【" + delEmpName + "】的邮箱信息吗？")) {
            $.ajax({
                url: "${pageContext.request.contextPath}/Email/deleteEmail",
                type:  "POST",
                data:{"_method":"delete","account":delEmpId},
                success: function (result) {
                    if (result.code == 100) {
                        alert("删除成功！");
                        window.location.href='${pageContext.request.contextPath}/Email/EmailPage/';
                    }
                    else {
                        alert(result.extendInfo.emp_del_error);
                    }
                }
            });
        }
    });











    $(".email_delete_btn").click(function () {
        //var curPage = ${curPage};
        var delEmpId = $(this).parent().parent().find("td:eq(1)").text();
        var delEmpName = $(this).parent().parent().find("td:eq(2)").text();
        if (confirm("确认删除【" + delEmpName + "】的邮箱信息吗？")) {
            $.ajax({
                url: "${pageContext.request.contextPath}/Email/deleteEmail",
                type:  "POST",
                data:{"_method":"delete","account":delEmpId},
                success: function (result) {
                    if (result.code == 100) {
                        alert("删除成功！");
                        window.location.href='${pageContext.request.contextPath}/Email/EmailPage/';
                    }
                    else {
                        alert(result.extendInfo.emp_del_error);
                    }
                }
            });
        }
    });

    function selectGroupEmail() {
        var options=$("#selectEmail option:selected"); //获取选中的项
        var groupEmail=  options.val()
           $.ajax({
               url:"/Email/selectEmail/"+groupEmail,
               type:"GET",
               success:function (result)
               {
                    if (result.code==100)
                    {
                        alert("查询成功");
                        var tb = document.getElementById('email_table');
                        var tBodies = tb.tBodies[0];
                        var rowNum=tb.rows.length;
                        for (i=1;i<rowNum;i++)
                        {
                            tb.deleteRow(i);
                            rowNum=rowNum-1;
                            i=i-1;
                        }
                        var selectzz =result.extendInfo.emails;
                        for (var i=0;i<selectzz.length;i++)
                        {
                            var tr = document.createElement("tr");
                            //2.添加行到表格
                            tBodies.appendChild(tr);
                            //3.添加单元格
                            tr.appendChild(createNode("td",i+1));
                            tr.appendChild(createNode("td",selectzz[i].account));
                            tr.appendChild(createNode("td",selectzz[i].name));
                            tr.appendChild(createNode("td", selectzz[i].groupEmail));

                        }

                        $("#count").text(selectzz.length);

                    }
                    else
                    {
                        alert("查询失败");
                    }
               }


           });

    }


    function createNode(element,text)
    {
        var td = document.createElement(element);
        td.innerHTML = text;
        return td;
    }
</script>
</body>
</html>
