<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>新增邮箱</title>
</head>
<body>
<div class="modal fade" id="add_email" tabindex="-1" role="dialog" aria-labelledby="dept-add-modal"  aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">邮箱新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal add_dept_form">
                    <div class="form-group">
                        <label for="add_account" class="col-sm-2 control-label">邮箱账号</label>
                        <div class="col-sm-8">
                            <input type="text" name="account" class="form-control" id="add_account" placeholder="请输入邮箱账号" oninput="if(/@$/.test(value) && this.tvl<value.length) value+='xitcorp.com'; this.tvl=value.length;">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_user" class="col-sm-2 control-label">邮箱使用人</label>
                        <div class="col-sm-8">
                            <input type="text"  name="name" class="form-control" id="add_user" placeholder="请输入使用者">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_group" class="col-sm-2 control-label">群发分组</label>
                        <div class="col-sm-8">
                            <div class="checkbox">

                                <select class="form-control" name="groupEmail" id="add_group">
                                    <option value="试产组">试产组</option>
                                    <option value="NPI组">NPI组</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary dept_save_btn"  data-dismiss="modal">保存</button>

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">
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



    $(".dept_save_btn").click(function ()
    {   if ($("#add_account").val()!=""&&$("#add_user").val()!=""&&$("#add_group").val()!="") {
        $.ajax({
            url: "/Email/addEmail",
            type: "POST",
            data: $(".add_dept_form").serialize(),
            success: function (result) {
                if (result.code == 100) {
                    alert("新增成功");
                     window.location.href='${pageContext.request.contextPath}/Email/EmailPage/';
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
                } else {
                    alert("添加错误！");
                }
            }
        });
       }
       else
    {
           alert("请正确填写相关信息！")
    }
    });



</script>
</body>
</html>
