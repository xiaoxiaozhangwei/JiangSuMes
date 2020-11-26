<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>工单信息页面</title>

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
                        <li><a href="#">工单信息管理</a></li>
                        <li class="active">工单信息</li>
                    </ol>
                    <a href="${pageContext.request.contextPath}/gd/addMo/" role="button" class="emp_add_btn btn btn-default" data-toggle="modal" data-target=".emp-add-modal">添加工单信息</a>


                </div>
                <!-- Table -->
                <table class="table table-bordered table-hover" id="emp_table">
                    <thead>
                    <tr>
                        <th>工单编号</th>
                        <th>工单号</th>
                        <th>工单数量</th>
                        <th>包装站剩余量</th>
                        <th>ERP料号</th>
                        <th>机种名称</th>
                        <th>FW</th>
                        <th>品名</th>
                        <th>产品规格</th>
                        <th>委外工单</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${gongdans}" var="m" varStatus="obj">
                        <tr>
                            <td>${obj.count}</td>
                            <td>${m.moId}</td>
                            <td>${m.moNum}</td>
                            <td>${m.moRemain0}</td>
                            <td>${m.pn}</td>
                            <td>${m.model}</td>
                            <td>${m.fw}</td>
                            <td>${m.product_name}</td>
                            <td>${m.spec}</td>
                            <td>${m.gkMo}</td>
                            <td>

                                <a role="button"
                                   class="btn btn-primary emp_edit_btn" data-toggle="modal"
                                   data-target=".emp-update-modal">编辑</a>
                                <a role="button" class="btn btn-danger gd_delete_btn">删除</a>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


                <div class="panel-body">
                    <div class="table_items">
                        当前第<span class="badge">${curPage}</span>页，共有<span class="badge">${totalPages}</span>页，总记录数<span class="badge">${totalItems}</span>条。
                    </div>
                    <nav aria-label="Page navigation" class="pull-right">
                        <ul class="pagination">
                            <li><a href="/gd/getMoBypage?pageNo=1">首页</a></li>
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

                            <c:forEach begin="1" end="${totalPages}" step="1" var="itemPage">
                                <c:if test="${curPage == itemPage}">
                                    <li class="active"><a href="/gd/getMoBypage?pageNo=${itemPage}">${itemPage}</a></li>
                                </c:if>
                                <c:if test="${curPage != itemPage}">
                                    <li><a href="/gd/getMoBypage?pageNo=${itemPage}">${itemPage}</a></li>
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
                            <li><a href="/gd/getMoBypage?pageNo=${totalPages}">尾页</a></li>
                        </ul>
                    </nav>
                </div>










            </div><!-- /.panel panel-success -->
        </div><!-- /.emp_info -->
        <%@ include file="gongdanAdd.jsp"%>
        <%@ include file="gongdanUpdate.jsp"%>


    </div><!-- /.hrms_body -->
    <!-- 尾部 -->
    <%@ include file="./commom/foot.jsp"%>
</div><!-- /.container -->



<script type="text/javascript">
    var totalPages = ${totalPages};
    $(function () {
        //上一页
        var curPage = ${curPage};
        // var totalPages = ${totalPages};
        $(".prePage").click(function () {
            if (curPage > 1){
                var pageNo = curPage-1;
                $(this).attr("href", "/gd/getMoBypage?pageNo="+pageNo);
            }
        });
        //下一页
        $(".nextPage").click(function () {
            if (curPage < totalPages){
                var pageNo = curPage+1;
                $(this).attr("href", "/gd/getMoBypage?pageNo="+pageNo);
            }
        });
    })

    <!-- ==========================工单删除操作=================================== -->
    $(".gd_delete_btn").click(function () {
        //alert("测试");
        var mo = $(this).parent().parent().find("td:eq(1)").text();
        //  var delEmpName = $(this).parent().parent().find("td:eq(2)").text();
        if (confirm("确认删除工单号为" + mo+ "的信息吗？")){
            $.ajax({
                url:"${pageContext.request.contextPath}/gd/deletemo/"+mo,
                type:"DELETE",
                success:function (result) {
                    if (result.code == 100){
                        alert("删除成功！");
                        window.location.href="${pageContext.request.contextPath}/gd/getMoAll";
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
