<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="modal fade emp-add-modal" tabindex="-1" role="dialog" aria-labelledby="emp-add-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增工单</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal add_emp_form" >
                    <div class="form-group">
                        <label for="add_inputName" class="col-sm-2 control-label">工单号</label>
                        <div class="col-sm-8">
                            <input type="text" name="moId" class="form-control" id="add_inputName" >
                            <span id="helpBlock_add_inputName" class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_inputNumber" class="col-sm-2 control-label">机种名称</label>
                        <div class="col-sm-8">
                            <input type="text" name="model" class="form-control" id="add_inputmodel" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_inputNumber" class="col-sm-2 control-label">工单数量</label>
                        <div class="col-sm-8">
                            <input type="text" name="moNum" class="form-control" id="add_inputNumber" >
                            <span id="helpBlock_add_inputNumber" class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_input_product_name" class="col-sm-2 control-label">品名</label>
                        <div class="col-sm-8">
                            <input type="text" name="product_name" class="form-control" id="add_input_product_name" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_input_pn" class="col-sm-2 control-label">料号</label>
                        <div class="col-sm-8">
                            <input type="text" name="pn" class="form-control" id="add_input_pn" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_input_spec" class="col-sm-2 control-label">产品规格</label>
                        <div class="col-sm-8">
                            <input type="text" name="spec" class="form-control" id="add_input_spec" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_gkMoNumber" class="col-sm-2 control-label">国科委外工单</label>
                        <div class="col-sm-8">
                            <input type="email" name="gkMo" class="form-control" id="add_gkMoNumber" >
                            <span id="helpBlock_add_gkMoNumber" class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_gkMoNumber" class="col-sm-2 control-label">FW</label>
                        <div class="col-sm-8">
                            <input type="email" name="fw" class="form-control" id="add_fw" >
                            <span id="helpBlock_add_FWr" class="help-block"></span>
                        </div>
                    </div>

                  <!--  <div class="form-group">
                        <label for="add_inputRemain" class="col-sm-2 control-label">工单剩余量 </label>
                        <div class="col-sm-8">
                            <input type="email" name="moRemain" class="form-control" id="add_inputRemain" >
                            <span id="helpBlock_add_inputRemain" class="help-block"></span>
                        </div>
                    </div>-->
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
    $("#add_inputName").change(function () {
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

    //3 保存



    $(".emp_save_btn").click(function () {

        //1 获取到第3步：$(".emp_save_btn").attr("btn_name_exists", "success");中btn_name_exists的值
        // btn_name_exists = error，就是姓名重复
        if($(".emp_save_btn").attr("btn_name_exists") == "error"){
            return false;
        }



        $.ajax({
            url:"/gd/addMo",
            type:"post",
            data: $(".add_emp_form").serialize(),
            success:function (result)
            {
                if (result.code == 100)
                {
                    alert("工单信息添加成功");
                    $('#emp-add-modal').modal("hide");
                    window.location.href="${pageContext.request.contextPath}/gd/getMoAll";
                }
                else
                {
                    alert("工单信息添加失败！");
                }
            }

        });



    });


</script>


