<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>原料入库信息</title>
    <style>
        h4 {
            font-family: 'Microsoft YaHei';
        }

    </style>
</head>
<body>
<div>
    <!-- 导航栏-->
    <%@ include file="./commom/head.jsp" %>


    <!-- 中间部分（左侧栏+表格内容） -->
    <div>
        <!-- 左侧栏 -->
        <%@ include file="commom/left-warehouse.jsp" %>

        <!-- 表格内容 -->
        <div class="dept_info col-sm-10">

            <div class="panel panel-success">

                <!-- 路径导航 -->
                <div class="panel-heading">

                    <div align="center">
                        <h4 style="font-size:40px; color: #ff5a19;">原料入库</h4></div>
                    <div id="result" align="right"></div>
                    <div id="form1">
                        <form action="" name="link" class="link" id="form">
                            <table class="table">

                                <tr>
                                    <td>
                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon">订单号码</span>
                                            <input type="text" name="mo" id="mo" class="form-control" value=""
                                                   maxlength="30">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon">采购单码</span>
                                            <input type="text" name="purchaseNumber" id="purchaseNumber"
                                                   class="form-control">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon">ERP料号</span>
                                            <input type="text" name="erp" id="erp" class="form-control">
                                        </div>
                                    </td>

                                </tr>

                                <tr>
                                    <td>
                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon">数量</span>
                                            <input type="text" name="num" id="num" class="form-control" onkeyup=
                                                    "(this.v=function(){
                                                        this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)"
                                                   onblur="this.v();" >
                                            <!--就是在失去焦bai点时也进行验证，du因为可zhi以用输入法输入汉字dao（用鼠标选取汉字）或者用鼠标进行粘贴操作-->
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon">D/C</span>
                                            <input type="text" name="DC" id="DC" class="form-control">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon">外箱号</span>
                                            <input type="text" name="wbox" id="wbox" class="form-control">
                                        </div>
                                    </td>

                                </tr>

                                <tr>
                                    <td>
                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon">品名</span>
                                            <input type="text" name="productName" id="productName" class="form-control">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon">仓别</span>
                                            <input type="text" name="storageArea" id="storageArea" class="form-control">
                                        </div>
                                    </td>

                                    <td>
                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon">储位</span>
                                            <input type="text" name="stock" id="stock" class="form-control">
                                        </div>
                                    </td>
                                </tr>

                                <tr align="center">
                                    <th colspan="3" style="text-align:center;">
                                        <button type="button" style="width:100px;height:45px" class="btn btn-primary"
                                                id="select" onclick="save()">来料入库
                                        </button>
                                        <input type="text" id="id" name="id" style="visibility: hidden;width: 2px"
                                               value="1">
                                        <input type= "hidden" id="operator" name ="operator" value=${name} />
                                        <!-- <button type="button" class="btn btn-success" id="save">来料查询</button> -->
                                    </th>
                                </tr>

                            </table>
                            <div style="width:100%; border:0.5px solid gray;">

                            </div>
                            <br>

                        </form>
                    </div>

                    <ol class="breadcrumb" style="">
                        <li class="active">数据信息/</li>
                        <a href="#">显示全部</a>
                    </ol>
                </div>

                <div style="height: 450px;overflow-x: auto;overflow-y: auto;">
                    <table class="table table-bordered " id="tab">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>订单号码</th>
                            <th>采购单码</th>
                            <th>ERP料号</th>
                            <th>数量</th>
                            <th>D/C</th>
                            <th>外箱号</th>
                            <th>品名</th>
                            <th>仓别</th>
                            <th>储位</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                共<span class="badge" id="count">${length}</span>条数据


                <script type="text/javascript">

                    // 保存输入来料信息
                    function save() {
                        document.getElementById("select").disabled=true;//禁用按钮
                        if ($("#erp").val() == "" || $("#purchaseNumber").val() == "" ||
                            $("#num").val() == "" || $("DC").val() == "" ||
                            $("#storageArea").val() == "" || $("#stock").val() == "" || $("#wbox").val() == "") {
                            alert("页面有未输入项，请检查");
                            document.getElementById("select").disabled=false;//启用按钮
                            return false;
                        } else {
                            // 单个储位不允许放置不同的erp料号
                            var erp = $("#erp").val();
                            var stock = $("#stock").val();
                            $.ajax({
                                url : "/materialstorage/check",
                                type : "GET",
                                data :{"erp":erp,"stock":stock},
                                success :function(result){
                                    if(result.code == 100){
                                        $.ajax({
                                            url: "/materialstorage/save",
                                            type: "GET",
                                            data: $("#form").serialize(),
                                            success: function (result) {
                                                if (result.code == 100) {
                                                    alert("保存成功");
                                                    document.getElementById("select").disabled=false;//启用按钮
                                                    add();
                                                } else {
                                                    alert("保存失败");
                                                }
                                            }
                                        });
                                    }else{
                                        alert("储位不允许放置不同的erp料号!!!");
                                        document.getElementById("select").disabled=false;//启用按钮
                                    }
                                }
                            });
                        }
                    }

                    //将文本框数据添加到表格
                    var oInput = document.getElementById("form").getElementsByTagName("input");
                    var oTable = document.getElementsByTagName("table")[1];
                    var tBodies = oTable.tBodies[0];

                    function add() {
                        var mai = document.getElementById('tab');
                        //1.创建行
                        var tr = document.createElement("tr");
                        //3.添加单元格
                        tr.appendChild(createNode("td", oInput['id'].value));
                        tr.appendChild(createNode("td", oInput['mo'].value));
                        tr.appendChild(createNode("td", oInput['purchaseNumber'].value));
                        tr.appendChild(createNode("td", oInput['erp'].value));
                        tr.appendChild(createNode("td", oInput['num'].value));
                        tr.appendChild(createNode("td", oInput['DC'].value));
                        tr.appendChild(createNode("td", oInput['wbox'].value));
                        tr.appendChild(createNode("td", oInput['productName'].value));
                        tr.appendChild(createNode("td", oInput['storageArea'].value));
                        tr.appendChild(createNode("td", oInput['stock'].value));

                        //2.添加行到表格
                        tBodies.appendChild(tr);
                        document.getElementById("id").value = parseInt(oInput['id'].value) + 1;
                        mai.scrollTop = mai.scrollHeight;//通过设置滚动高度
                        // 保存成功后将数量输入框的值清空,避免重复提交相同数据
                        $("#num").val("");
                        $("#wbox").val("");
                        $("#storageArea").val("");
                        $("#stock").val("");
                    }

                    //表格添加列
                    function createNode(element, text) {
                        var td = document.createElement(element);
                        td.innerHTML = text;
                        return td;
                    }

                    // enter键实现tab键功能
                    $(function() {
                        $("form[name='link'] input:text").keypress(function(e) {
                            if (e.which == 13) {// 判断所按是否回车键
                                var inputs = $("form[name='link']").find(":text"); // 获取表单中的所有输入框
                                var idx = inputs.index(this); // 获取当前焦点输入框所处的位置
                                    inputs[idx + 1].focus(); // 设置焦点
                                    inputs[idx + 1].select(); // 选中文字
                                return false;// 取消默认的提交行为
                            }
                        });
                    });


                </script>
            </div><!-- /.panel panel-success -->
        </div><!-- /.dept_info -->

    </div><!-- /.hrms_dept_body -->

    <!-- 尾部-->
    <%@ include file="./commom/foot.jsp" %>

</div><!-- /.hrms_dept_container -->
</body>
</html>
