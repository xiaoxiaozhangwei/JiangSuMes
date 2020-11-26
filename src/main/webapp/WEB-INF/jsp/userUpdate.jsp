<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工信息修改</title>
</head>
<body>
<div class="modal fade emp-update-modal" tabindex="-1" role="dialog" aria-labelledby="emp-update-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">员工信息更改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal update_user_form" name="update_user_form">
                    <div class="form-group">
                        <label for="update_jobnumber" class="col-sm-2 control-label">员工工号</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="update_jobnumber"  ></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="update_name" class="col-sm-2 control-label">员工姓名</label>
                        <div class="col-sm-8">
                            <input type="email" name="name" class="form-control" id="update_name">
                            <span id="helpBlock_update_name" class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="update_pwd" class="col-sm-2 control-label">员工密码</label>
                        <div class="col-sm-8">
                            <input type="email" name="pwd" class="form-control" id="update_pwd">
                            <span id="helpBlock_update_pwd" class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="update_alias" class="col-sm-2 control-label">员工英文</label>
                        <div class="col-sm-8">
                            <input type="email" name="name_alias" class="form-control" id="update_alias">
                            <span id="helpBlock_update_alias" class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary emp_update_btn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->




<script type="text/javascript">

    <!-- ==========================员工修改操作=================================== -->
    $(".emp_edit_btn").click(function () {
        //1 获取点击修改员工的id与name;

        var job_number = $(this).parent().parent().find("td:eq(1)").text();
        $.ajax({
            url:"/user/selectUser/"+job_number,
            type:"GET",
            success:function (result) {
                if (result.code == 100){
                    var emp = result.extendInfo.user;
                    $("#update_jobnumber").text(emp.job_number);
                    $("#update_name").val(emp.name);
                    $("#update_pwd").val(emp.pwd);
                    $("#update_alias").val(emp.name_alias);
                }
            }

        });


        $(".emp_update_btn").attr("updateEmpId", job_number);

    });


    $(".emp_update_btn").click(function () {
        // var moId = document.getElementById("update_MoName").value;
        var updateEmpId = $(this).attr("updateEmpId");
        //5 点击更新按钮，发送AJAX请求到后台进行保存。
        $.ajax({
            url:"/user/updateUser/"+updateEmpId,
            type:"PUT",
            data:$(".update_user_form").serialize(),
            success:function (result) {
                if (result.code==100){
                    alert("员工更改成功！");
                    $(".emp-update-modal").modal("hide");
                    //跳转到当前页
                    //var curPage = ${curPage};
                   window.location.href="/user/userAll/";
                }else {
                    alert(result.extendInfo.emp_update_error);
                }
            }
        });

    });
</script>
</body>
</html>
