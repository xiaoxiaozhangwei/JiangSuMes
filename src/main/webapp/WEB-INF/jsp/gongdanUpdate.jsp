<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>工单信息修改</title>
</head>
<body>
<div class="modal fade emp-update-modal" tabindex="-1" role="dialog" aria-labelledby="emp-update-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">工单信息更改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal update_mo_form" name="update_emp_form">
                    <div class="form-group">
                        <label for="update_MoName" class="col-sm-2 control-label">工单号</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="update_MoName"></p>
                        </div>
                    </div>
                    <!--
                    <div class="form-group">
                        <label for="update_MoName" class="col-sm-2 control-label">工单号</label>
                        <div class="col-sm-8">
                            <input type="email" name="moId" class="form-control" id="update_MoName">
                            <span id="helpBlock_update_MoName" class="help-block"></span>
                        </div>
                    </div>
 -->
                    <div class="form-group">
                        <label for="update_MoNumber" class="col-sm-2 control-label">工单数量</label>
                        <div class="col-sm-8">
                            <input type="email" name="moNum" class="form-control" id="update_MoNumber">
                            <span id="helpBlock_update_MoNumber" class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="update_MoRemain" class="col-sm-2 control-label">工单剩余数量</label>
                        <div class="col-sm-8">
                            <input type="email" name="moRemain" class="form-control" id="update_MoRemain">
                            <span id="helpBlock_update__MoRemain" class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="update_Production" class="col-sm-2 control-label">品名</label>
                        <div class="col-sm-8">
                            <input type="email" name="product_name" class="form-control" id="update_Production">
                            <span id="helpBlock_update__Production" class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="update_ERP" class="col-sm-2 control-label">ERP料号</label>
                        <div class="col-sm-8">
                            <input type="email" name="pn" class="form-control" id="update_ERP">
                            <span id="helpBlock_update__ERP" class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="update_model" class="col-sm-2 control-label">机种名称</label>
                        <div class="col-sm-8">
                            <input type="email" name="model" class="form-control" id="update_model">
                            <span id="helpBlock_update__model" class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="update_SPEC" class="col-sm-2 control-label">产品规格</label>
                        <div class="col-sm-8">
                            <input type="email" name="spec" class="form-control" id="update_SPEC">
                            <span id="helpBlock_update__SPEC" class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="update_gkMo" class="col-sm-2 control-label">委外工单</label>
                        <div class="col-sm-8">
                            <input type="email" name="gkMo" class="form-control" id="update_gkMo">
                            <span id="helpBlock_update__gkMo" class="help-block"></span>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default emp_closeUpdate_btn" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary emp_update_btn" data-dismiss="modal">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script type="text/javascript">

    <!-- ==========================员工修改操作=================================== -->
    $(".emp_edit_btn").click(function () {
        //1 获取点击修改员工的id与name;

        var updateEmpId = $(this).parent().parent().find("td:eq(1)").text();
        //  alert(updateEmpId);
        $.ajax({
            url: "/gd/selectMO/" + updateEmpId,
            type: "GET",
            data: "updateEmpId = " +updateEmpId,
            success: function (result) {
                if (result.code == 100) {
                    var emp = result.extendInfo.gongdan;
                    $("#update_MoName").text(emp.moId);
                    $("#update_MoNumber").val(emp.moNum);
                    $("#update_MoRemain").val(emp.moRemain);
                    $("#update_Production").val(emp.product_name);
                    $("#update_ERP").val(emp.pn);
                    $("#update_model").val(emp.model);
                    $("#update_SPEC").val(emp.spec);
                    $("#update_gkMo").val(emp.gkMo);
                }
            }
        });


        $(".emp_update_btn").attr("updateEmpId", updateEmpId);

    });



    $(".emp_update_btn").click(function () {
        // var moId = document.getElementById("update_MoName").value;
        // alert("测试");

        var updateEmpId = $(this).attr("updateEmpId",updateEmpId);

        // 点击更新按钮，发送AJAX请求到后台进行保存。
        $.ajax({
            url: "/gd/updateMo/" + updateEmpId,
            type: "PUT",
            data: $(".update_mo_form").serialize(),
            success: function (result) {
                if (result.code == 100) {
                    var curPage = ${curPage}
                        alert("工单更改成功！");
                    window.location.href="${pageContext.request.contextPath}/gd/getMoBypage?pageNo="+curPage;
                    $(".emp-update-modal").modal("hide");

                    //跳转到当前页
                    //var curPage = ${curPage};
                    //  window.location.href="/emp/getEmpList?pageNo="+curPage;
                } else {
                    alert(result.extendInfo.emp_update_error);
                }
            }
        });

    });





</script>
</body>
</html>
