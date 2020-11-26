<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>ERP新增页面</title>
</head>
<body>
<div class="modal fade erp-add-modal" tabindex="-1" role="dialog" aria-labelledby="dept-add-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增ERP信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal add_erp_form">
                    <div class="form-group">
                        <label for="add_erp" class="col-sm-2 control-label">ERP号</label>
                        <div class="col-sm-8">
                            <input type="text" name="erp" class="form-control" id="add_erp" placeholder="">
                            <span id="helpBlock_add_inputName" class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="1" class="col-sm-2 control-label">model号</label>
                        <div class="col-sm-8">
                            <input type="text" name="model" class="form-control" id="1" placeholder="XXX">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="2" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-8">
                            <input type="text" name="descirbe" class="form-control" id="2" placeholder="XXX">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">FW</label>
                        <div class="col-sm-8">
                            &nbsp; &nbsp; &nbsp;
                            <label><input type="radio" name="selectFW" value="有FW" id="isFW">有FW</label>
                                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                            <label><input type="radio" name="selectFW" value="无FW" id="notFW">无FW</label>
                        </div>
                    </div>
                    <div class="form-group" style="display: none" id="writeFW" >
                        <label for="FW" class="col-sm-2 control-label">输入FW号</label>
                        <div class="col-sm-8">
                            <input type="text" name="fw" class="form-control" id="FW" placeholder="">
                        </div>
                    </div>


                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary erp_save_btn" >保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">

    // 根据单选框触发是否提交事件!
    $('input:radio[name="selectFW"]').change(function () {
        if ($("#isFW").is(":checked")) {
            $("#writeFW").show();
        }
        if ($("#notFW").is(":checked")) {
            $("#writeFW").hide();
            $("#FW").text('');
        }
    })





    <!-- ==========================部门新增操作=================================== -->
    // 为简单操作，省去了输入名称的验证、错误信息提示等操作
    //1 点击部门新增按钮，弹出模态框；
    //2 输入新增部门信息，点击保存按钮，发送AJAX请求到后台进行保存；
    //3 保存成功跳转最后一页
    $(".dept_add_btn").click(function () {
        $('.dept-add-modal').modal({
            backdrop:static,
            keyboard:true
        });

    });


    //=========1 当鼠标从姓名输入框移开的时候，判断姓名输入框内的姓名是否重复 ============
    $("#add_erp").change(function () {
        var erp = $("#add_erp").val();
        $.ajax({
            url:"/Erp/selectErp",
            type:"GET",
            data:"erp="+erp,
            success:function (result) {
                if (result.code == 200){
                    $("#add_erp").parent().parent().removeClass("has-error");
                    $("#add_erp").parent().parent().addClass("has-success");
                    $("#helpBlock_add_inputName").text("ERP号可用！");
                    $(".erp_save_btn").attr("disabled", false);
                }else {
                    $("#add_erp").parent().parent().removeClass("has-success");
                    $("#add_erp").parent().parent().addClass("has-error");
                    $("#helpBlock_add_inputName").text("ERP号重复!");
                    $(".erp_save_btn").attr("disabled", true);
                }
            }
        });
    });







    $(".erp_save_btn").click(function () {
        //验证省略...
        $.ajax({
            url:"/Erp/addErp",
            type:"GET",
            data:$(".add_erp_form").serialize(),
            success:function (result) {
                if(result.code == 100){
                    alert("新增成功");

                    window.location.href="${pageContext.request.contextPath}/Erp/getErpList?pageNo="+totalPages;
                    /*
                    $.ajax({
                        url:"/hrms/dept/getTotalPages",
                        type:"GET",
                        success:function (result) {
                            if (result.code == 100){
                                var totalPage = result.extendInfo.totalPages;
                                window.location.href="/hrms/dept/getDeptList?pageNo="+totalPage;
                            }
                        }
                    });*/
                }else {
                    alert("保存数据失败！");
                }
            }
        });

    });



</script>
</body>
</html>
