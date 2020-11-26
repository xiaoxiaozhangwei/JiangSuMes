<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工增加</title>
</head>
<body>
<div class="modal fade emp-add-modal" tabindex="-1" role="dialog" aria-labelledby="emp-add-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增人员</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal add_user_form" >
                    <div class="form-group">
                        <label for="add_inputjobnumber" class="col-sm-2 control-label">人员工号</label>
                        <div class="col-sm-8">
                            <input type="text" name="job_number" class="form-control" id="add_inputjobnumber" >
                            <span id="helpBlock_add_inputjobnumber" class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_inputName" class="col-sm-2 control-label">人员姓名</label>
                        <div class="col-sm-8">
                            <input type="text" name="name" class="form-control" id="add_inputName" >
                            <span id="helpBlock_add_inputName" class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_inputpwd" class="col-sm-2 control-label">人员密码</label>
                        <div class="col-sm-8">
                            <input type="email" name="pwd" class="form-control" id="add_inputpwd" >
                            <span id="helpBlock_add_inputpwd" class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_inputalias" class="col-sm-2 control-label">英文名</label>
                        <div class="col-sm-8">
                            <input type="email" name="name_alias" class="form-control" id="add_inputalias" >
                            <span id="helpBlock_add_inputalias" class="help-block"></span>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary emp_save_btn" >保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script type="text/javascript">

    <!-------------------------------------信息修改操作-------------------------------------->
    //=======0 点击修改按钮，发送AJAX请求查询装配信息列表，弹出模态框，
    // 将查询得到的装配列表信息显示在对应模态框中部门信息处。=============================
    /* $(".emp_add_btn").click(function () {

         $.ajax({
             url:"/dept/getDeptName",
             type:"GET",
             success:function (result) {
                 if (result.code == 100){
                     $.each(result.extendInfo.departmentList, function () {
                         var optionEle = $("<option></option>").append(this.deptName).attr("value", this.deptId);
                         optionEle.appendTo("#add_department");
                     });
                 }
             }
         });

         $('.emp-add-modal').modal({
             backdrop:static,
             keyboard:true
         });
     });
 */
    //=========1 当鼠标从姓名输入框移开的时候，判断姓名输入框内的姓名是否重复 ============
 /*   $("#add_inputName").change(function () {
        var empName = $("#add_inputName").val();
        $.ajax({
            url:"/gd/checkMoExists",
            type:"GET",
            data:"empName="+empName,
            success:function (result) {
                if (result.code == 100){
                    $("#add_inputName").parent().parent().removeClass("has-error");
                    $("#add_inputName").parent().parent().addClass("has-success");
                    $("#helpBlock_add_inputName").text("工单号可用！");
                    $(".emp_save_btn").attr("btn_name_exists", "success");
                }else {
                    $("#add_inputName").parent().parent().removeClass("has-success");
                    $("#add_inputName").parent().parent().addClass("has-error");
                    $("#helpBlock_add_inputName").text(result.extendInfo.name_reg_error);
                    $(".emp_save_btn").attr("btn_name_exists", "error");
                }
            }
        });
    });
*/
    //3 保存



    $(".emp_save_btn").click(function () {
        //1 获取到第3步：$(".emp_save_btn").attr("btn_name_exists", "success");中btn_name_exists的值
        // btn_name_exists = error，就是姓名重复
        if($(".emp_save_btn").attr("btn_name_exists") == "error"){
            return false;
        }



        $.ajax({
            url:"${pageContext.request.contextPath}/user/addUser",
            type:"post",
            data: $(".add_user_form").serialize(),
            success:function (result)
            {
                if (result.code == 100)
                {
                    alert("员工信息添加成功");
                    $('#emp-add-modal').modal("hide");
                    window.location.href="${pageContext.request.contextPath}/user/userAll";
                }
                else
                {
                    alert("用户信息添加失败！");
                }
            }

        });



    });


</script>
</body>
</html>
