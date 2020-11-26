<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <!-- Bootstrap Core CSS
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="http://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">-->
    <link rel="stylesheet" href="../css/cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>-->
    <script src="../js/oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="../js/oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
     <!--[endif]
    <script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="../js/cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="../js/cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="../js/cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <style>
        .one{
            font-size: 80px;
            color:dodgerblue;
        }
    </style>
</head>
<body >
<img src="${pageContext.request.contextPath}/static/img/log2.jpg" height="100">
<h1 style="text-align: center" class="one" >江苏芯盛智能科技有限公司MES系统</h1>
<div class="container" >

        <div class="col-md-4 col-md-offset-4" style="margin:100px 380px" >
            <div class="login-panel panel panel-default" >
                <div class="panel-heading">
                    <h3 class="panel-title" style="text-align: center;" >登录</h3>
                </div>
                <div class="panel-body">
                    <form  action="${pageContext.request.contextPath }/user/login"  method="post">
                        <fieldset>
                            <div class="form-group">

                                用户名：<span style="color: brown">${msg1}</span><br>
                                <input class="form-control"  name="name" autofocus>
                            </div>
                            <div class="form-group">

                                密码：<span style="color: brown">${msg2}</span><br>
                                <input class="form-control"  name="password" type="password" >
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
<%--                            <a href="javascript:void(0)" class="btn btn-lg btn-success btn-block" id='login_btn'>登录</a>--%>
                            <input type="submit" value="登录" class="btn btn-lg btn-success btn-block">
                            <!--<button  class="btn btn-lg btn-success btn-block" id="login_btn" >登录</button>-->
                        </fieldset>
                    </form>

                </div>
            </div>
        </div>

</div>
<!--
<a href="/user/test">test</a>

<a href ="/user/readfile">readfile</a>
<input type="file" value="上传文件" />


<textarea name="" id="textArea" cols="30" rows="10"></textarea>
<input id="files" type="file" value="上传文件">
-->
<script type="text/javascript">

    //$(function () {
        $("#login_btn").click(function () {
            $.ajax({
                url:"/hrms/dologin",
                type:"POST",
                data:$("#login_form").serialize(),
                success:function (result) {
                    if(result.code == 100){
                        window.location.href= "/hrms/main";
                    }else {
                        alert(result.extendInfo.login_error);
                    }
                }

            });
        });

    $(function () {
        $("#files").change(function () {
            fileUpload_onselect();
        })
        function fileUpload_onselect(){
            var selectedFile = document.getElementById("files").files[0];
            var reader = new FileReader();//这是核心！！读取操作都是由它完成的
            reader.readAsText(selectedFile,'gb2312');
            reader.onload = function(oFREvent){//读取完毕从中取值
                var pointsTxt = oFREvent.target.result;
                $("#textArea").val(pointsTxt)
            }
        }
    })
</script>

</body>
</html>
