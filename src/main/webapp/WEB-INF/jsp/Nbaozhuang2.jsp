<%--
  Created by IntelliJ IDEA.
  User: guyanbo
  Date: 2019/12/10
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>内包装重新打印</title>
</head>
<body>
<div class="modal fade reprint-modal" tabindex="-1" role="dialog" aria-labelledby="reprint-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">重新打印标签</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal reprint_form" >
                    <div class="form-group">
                        <label for="inputNbox" class="col-sm-2 control-label">内箱号</label>
                        <div class="col-sm-8">
                            <input type="text"  class="form-control" id="inputNbox" >
                            <span id="msg" class="msg"></span>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary reprint_btn" onclick="reprint()" >打印</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
    $(".reprint_btn").click(function () {


        $('.reprint-modal').modal({
            backdrop:static,
            keyboard:true
        });
    });
    $("#inputNbox").change(function () {
        var nbox = $("#inputNbox").val();
        $.ajax({
            url:"/pack/selectCountByNbox/"+nbox,
            type:"GET",
            success:function (result) {
                if (result.code == 100){
                    $("#msg").parent().parent().removeClass("has-error");
                    $("#msg").parent().parent().addClass("has-success");
                    $("#msg").text("内箱号正确！");
                    $(".reprint_btn").attr("btn_name_exists", "success");
                }else {
                    $("#msg").parent().parent().removeClass("has-success");
                    $("#msg").parent().parent().addClass("has-error");
                    $("#msg").text("内箱号错误！");
                    $(".reprint_btn").attr("btn_name_exists", "error");
                }
            }
        });
    });
    function reprint() {
        var nbox = $("#inputNbox").val();
        $.ajax({
            url:"/pack/printN/"+nbox,
            type:"GET"
        });
    }
</script>
</body>
</html>
