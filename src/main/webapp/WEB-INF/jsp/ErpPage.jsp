<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ERP信息页面</title>

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
                    <div align="center">
                        <h4 style="font-size:40px; color: #ff5a19;" >ERP信息管理</h4></div>
                    <ol class="breadcrumb">
                        <li><a href="#">ERP信息管理</a></li>
                        <li class="active">ERP信息</li>
                    </ol>
                    <shiro:hasAnyRoles name="ERP信息录入,ERP信息审核">
                    <a href="${pageContext.request.contextPath}/gd/addMo/" role="button" class="btn  btn-primary "  data-toggle="modal" data-target=".erp-add-modal">添加ERP信息</a>
                    </shiro:hasAnyRoles>

                </div>
                <!-- Table -->
                <table class="table table-bordered table-hover" id="emp_table">
                    <thead>
                    <th>编号</th>
                    <th>ERP号</th>
                    <th>model号</th>
                    <th>FW号</th>
                    <th>描述</th>
                    <th>审核情况</th>
                    <shiro:hasRole name="ERP信息审核">
                    <th>操作</th>
                    </shiro:hasRole>
                    </thead>
                    <tbody>
                    <c:forEach  items="${erps}" var="erp" varStatus="obj">
                        <tr>
                            <td>${obj.count}</td>
                            <td>${erp.erp}</td>
                            <td>${erp.model}</td>
                            <td>${erp.fw}</td>
                            <td>${erp.descirbe}</td>
                            <td>${erp.confirm}</td>
                            <shiro:hasRole name="ERP信息审核">
                            <td>

                                <a href="${pageContext.request.contextPath}/gd/updateMo/" role="button" class="btn btn-primary erp_edit_btn" data-toggle="modal" data-target=".erp-update-modal">审核</a>
                                <a  role="button" class="btn btn-danger erp_delete_btn">删除</a>

                            </td>
                            </shiro:hasRole>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>









            </div><!-- /.panel panel-success -->
            <div class="panel-body">
                <div class="table_items">
                    当前第<span class="badge">${curPage}</span>页，共有<span class="badge">${totalPages}</span>页，总记录数<span class="badge">${totalItems}</span>条。
                </div>
                <nav aria-label="Page navigation" class="pull-right">
                    <ul class="pagination">
                        <li><a href="/Erp/getErpList?pageNo=1">首页</a></li>
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
                                <li class="active"><a href="/Erp/getErpList?pageNo=${itemPage}">${itemPage}</a></li>
                            </c:if>
                            <c:if test="${curPage != itemPage}">
                                <li><a href="/Erp/getErpList?pageNo=${itemPage}">${itemPage}</a></li>
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
                        <li><a href="/Erp/getErpList?pageNo=${totalPages}">尾页</a></li>
                    </ul>
                </nav>
            </div>

        </div><!-- /.emp_info -->

        <%@ include file="ErpAdd.jsp"%>
        <%@ include file="ErpUpdate.jsp"%>




    </div><!-- /.hrms_body -->
    <!-- 尾部 -->
    <%@ include file="./commom/foot.jsp"%>
</div><!-- /.container -->



<script type="text/javascript">
    var totalPages = ${totalPages};
    $(function () {
        //上一页
        var curPage = ${curPage};

        $(".prePage").click(function () {
            if (curPage > 1){
                var pageNo = curPage-1;
                $(this).attr("href", "/Erp/getErpList?pageNo="+pageNo);
            }
        });
        //下一页
        $(".nextPage").click(function () {
            if (curPage < totalPages){
                var pageNo = curPage+1;
                $(this).attr("href", "/Erp/getErpList?pageNo="+pageNo);
            }
        });
    })


    <!-- ==========================工单删除操作=================================== -->

    $(".erp_delete_btn").click(function () {
        var erp = $(this).parent().parent().find("td:eq(1)").text();
        if (confirm("确认删除ERP为" + erp+ "的信息吗？")){
            $.ajax({
                url:"${pageContext.request.contextPath}/Erp/deleteErp/"+erp,
                type:"DELETE",
                success:function (result) {
                    if (result.code == 100){
                        alert("删除成功！");
                        window.location.href="${pageContext.request.contextPath}/Erp/getErpList?pageNo="+totalPages;
                    }else {
                        alert(result.extendInfo.deleteErp);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(XMLHttpRequest.status);
                    // 状态
                    console.log(XMLHttpRequest.readyState);
                    // 错误信息
                    console.log(textStatus);
                    alert("没有权限")
                }

            });
        }
    });






</script>
</body>
</html>
