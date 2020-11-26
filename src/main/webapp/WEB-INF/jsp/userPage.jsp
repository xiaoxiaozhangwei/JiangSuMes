<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户信息页面</title>

</head>





<body >
<div class="hrms_container">
    <!-- 导航条 -->
    <%@ include file="./commom/head.jsp"%>

    <!-- 中间部分（包括左边栏和员工/部门表单显示部分） -->
    <div class="hrms_body" style="position:relative; top:-15px;">

        <!-- 左侧栏 -->
        <%@ include file="./commom/left_ziliao.jsp"%>

        <!-- 中间员工表格信息展示内容 -->
        <div class="emp_info col-sm-10">

            <div class="panel panel-success">
                <!-- 路径导航 -->
                <div class="panel-heading">
                    <ol class="breadcrumb">
                        <li><a href="#">用户信息管理</a></li>
                        <li class="active">用户信息</li>
                    </ol>
                   <!-- <a href="${pageContext.request.contextPath}/user/addUser/" role="button" class="emp_add_btn btn btn-default" data-toggle="modal" data-target=".emp-add-modal">添加用户</a>-->
                    <a href=${pageContext.request.contextPath}/user/addUser/" role="button" class="btn  btn-primary " data-toggle="modal" data-target=".emp-add-modal">添加用户</a>

                </div>
                <!-- Table -->
                <table class="table table-bordered table-hover" id="emp_table">
                    <thead>
                    <th>用户编号</th>
                    <th>用户工号</th>
                    <th>用户姓名</th>
                    <th>用户密码</th>
                    <th>英文名</th>
                    <th>操作</th>
                    <th>是否3个月内修改密码</th>
                    </thead>
                    <tbody>
                    <c:forEach  items="${users}" var="user" varStatus="obj">
                        <tr>
                            <td>${obj.count}</td>
                            <td>${user.job_number}</td>
                            <td>${user.name}</td>
                            <td>${user.pwd}</td>
                            <td>${user.name_alias}</td>
                            <td>

                                <a href="${pageContext.request.contextPath}/user/updateUser/" role="button" class="btn btn-primary emp_edit_btn" data-toggle="modal" data-target=".emp-update-modal">编辑</a>
                                <a role="button" class="btn btn-danger user_delete_btn" >删除</a>



                            </td>
                            <td><input name="mima" type="checkbox" value="mima" /></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <!--
                <div class="panel-body">
                    <div class="table_items">
                        当前第<span class="badge">${curPage}</span>页，共有<span class="badge">${totalPages}</span>页，总记录数<span class="badge">${totalItems}</span>条。
                    </div>
                    <nav aria-label="Page navigation" class="pull-right">
                        <ul class="pagination">
                            <li><a href="/emp/getEmpList?pageNo=1">首页</a></li>
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
                -->
            </div><!-- /.panel panel-success -->

        </div><!-- /.emp_info -->
        <%@ include file="userAdd.jsp"%>
        <%@ include file="userUpdate.jsp"%>


    </div><!-- /.hrms_body -->
    <!-- 尾部 -->
    <%@ include file="./commom/foot.jsp"%>
</div><!-- /.container -->


<script type="text/javascript" language="JavaScript">
    <!-- ==========================员工删除操作=================================== -->
    $(".user_delete_btn").click(function () {
        var user = $(this).parent().parent().find("td:eq(2)").text();
         if (confirm("确认删除【" + user+ "】的信息吗？")){
             $.ajax({
                 url:"${pageContext.request.contextPath}/user/deleteEmp/"+user,
                 type:"DELETE",
                 success:function (result) {
                     if (result.code == 100){
                         alert("删除成功！");
                         window.location.href="${pageContext.request.contextPath}/user/userAll";
                     }else {
                         alert(result.extendInfo.emp_del_error);
                     }
                 }
             });
         }



    });





</script>

</body>









</html>
