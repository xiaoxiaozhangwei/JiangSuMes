<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EEP信息修改</title>
</head>
<body>
<div class="modal fade erp-update-modal" tabindex="-1" role="dialog" aria-labelledby="emp-update-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">EEP信息审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal update_erp_form" name="update_erp_form">
                         <div class="form-group">
                               <label for="update_ERP" class="col-sm-2 control-label">ERP号</label>
                               <div class="col-sm-8">
                                   <input type="email" name="erp" class="form-control" id="update_ERP" readonly>
                               </div>
                       </div>
                         <div class="form-group">
                             <label for="update_Model" class="col-sm-2 control-label">Model号</label>
                             <div class="col-sm-8">
                                 <input type="email" name="model" class="form-control" id="update_Model">
                                 <span id="helpBlock_update_MoNumber" class="help-block"></span>
                             </div>
                         </div>

                         <div class="form-group">
                             <label for="update_fw" class="col-sm-2 control-label">FW号</label>
                             <div class="col-sm-8">
                                 <input type="email" name="fw" class="form-control" id="update_fw">
                                 <span id="helpBlock_update__MoRemain" class="help-block"></span>
                             </div>
                         </div>
                        <div class="form-group">
                            <label for="update_describe" class="col-sm-2 control-label">描述</label>
                            <div class="col-sm-8">
                                <input type="email" name="descirbe" class="form-control" id="update_describe">
                                <span id="helpBlock_update__MoRemain1" class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="update_confirm" class="col-sm-2 control-label">审核</label>
                            <div class="col-sm-8">
                                <div class="checkbox">
                                    <select class="form-control" name="confirm" id="update_confirm" >
                                        <option value="通过">通过</option>
                                        <option value="不通过">不通过</option>
                                    </select>
                                </div>
                            </div>
                        </div>
         </form>
     </div>
     <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
         <button type="button" class="btn btn-primary erp_update_btn">保存</button>
     </div>
 </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->




<script type="text/javascript">

    <!-- ==========================员工修改操作=================================== -->
    $(".erp_edit_btn").click(function () {

        var erp = $(this).parent().parent().find("td:eq(1)").text();
        $.ajax({
            url:"/Erp/selectErpByName",
            type:"GET",
            data:"erp="+erp,
            success:function (result) {
                if (result.code == 100){
                    var emp = result.extendInfo.Erp;
                    $("#update_ERP").val(emp.erp);
                    $("#update_Model").val(emp.model);
                    $("#update_fw").val(emp.fw);
                    $("#update_describe").val(emp.descirbe);
                }
            }

        });


     //   $(".emp_update_btn").attr("updateEmpId", updateEmpId);

    });


    $(".erp_update_btn").click(function () {
       // var moId = document.getElementById("update_MoName").value;

        //5 点击更新按钮，发送AJAX请求到后台进行保存。
        $.ajax({
            url:"${pageContext.request.contextPath}/Erp/updateErp",
            type:"PUT",
            data:$(".update_erp_form").serialize(),
            success:function (result) {
                if (result.code==100){
                    alert("审核成功！");
                   // $(".emp-update-modal").modal("hide");
                    //跳转到当前页
                    //var curPage = ${curPage};
                    window.location.href="${pageContext.request.contextPath}/Erp/getAllErp";
                }else {
                    alert("审核失败！");
                }
            }
        });

    });
</script>
</body>
</html>
