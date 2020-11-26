<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Head Page</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="../css/cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">


    <!-- 可选的 Bootstrap 主题文件（一般不用引入）-->
    <link rel="stylesheet" href="../css/cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="../js/cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件-->
    <script src="../js/cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <!--[if lt IE 9]-->
    <script src="../js/oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="../js/oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <!--[endif]-->

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="../css/cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="../js/cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="../js/cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- 鼠标悬停电子看板下拉框显示组装线电子看板和PCBA电子看板 -->
    <style>

    </style>
</head>
<body>

<div class="hrms_brand_nav" >
    <nav class="navbar navbar-inverse navbar-static-top">
        <div class="container-fluid">
            <div class="navbar-header">

                <a class="navbar-brand" id="company_logo" href="#" >江苏芯盛智能科技有限公司</a>
            </div>

            <div class="collapse navbar-collapse" id="hrms-navbar-collapse-1" style="margin-left: 400px">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/hrms/main">首页</a> </li>
                    <li ><a href="/hrms/xitong">系统流程</a></li>
                   <shiro:hasRole name="管理员">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">测试工具 <span class="caret"></span></a>
                        <ul class="dropdown-menu nav nav-pills nav-stacked" style="background-color: #8d8d8d;color: #cccccc">
                            <li id="OC1"><a href="#">OC1</a></li>
                            <li id="OC2"><a href="#">OC2</a></li>
                            <li id="OC4"><a href="#">OC4</a></li>
                            <li id="CDI"><a href="#" >CDI</a></li>
                            <li id="BIT"><a href="#" >BIT</a></li>
                            <li id="QC"><a href="#" >QC</a></li>
                        </ul>
                    </li>
                   </shiro:hasRole>
                    <!--<li><a href="/repack/Nboxchonggong">重工流程</a></li>-->
                    <li><a href="#">待开发</a></li>
                    <li><a href="/hrms/ziliao">资料维护</a></li>
                    <shiro:hasRole name="管理员">
                    <li><a href="/hrms/rebarcode">barcode重工</a></li>

                      <!--  <li >
                        <select id="dzkbxlk" value="电子看板" onchange="self.location.href=options[selectedIndex].value">
                            <option id="dzkb" value="/user/main">
                                电子看板
                            </option>
                            <option id="zz" value="/DisplayBoard/ZuZhuang">
                                组装线电子看板
                            </option>
                            <option id="pcba" value="/DisplayBoard/PCBA">
                                PCBA电子看板
                            </option>
                        </select>
                        </li>
                        -->

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">电子看板 <span class="caret"></span></a>
                            <ul class="dropdown-menu nav nav-pills nav-stacked" style="background-color: #8d8d8d;color: #cccccc">
                                <li id="zzxdzkb"><a href="/DisplayBoard/ZuZhuang">组装线电子看板</a></li>
                                <li id="pcbadzkb"><a href="/DisplayBoard/PCBA">PCBA电子看板</a></li>
                                <li id="hcdzkb"><a href="/DisplayBoard/HeCheng">合成电子看板</a></li>
                            </ul>
                        </li>

                    <li><a href="/repair/repair">维修分析</a></li>
                        <li><a href="/warehouse/management">仓储管理</a></li>
                    </shiro:hasRole>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">账号管理 <span class="caret"></span></a>
                        <ul class="dropdown-menu nav nav-pills nav-stacked">
                            <li class="active"><a href="#"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 修改信息</a></li>
                            <li><a href="/hrms/manage" ><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 权限管理</a></li>
                            <li><a href="/user/logout" ><span class="glyphicon glyphicon-off" aria-hidden="true"></span> 账号退出</a></li>
                        </ul>
                    </li>
                </ul><!-- /.nav navbar-nav navbar-right -->
                <form class="navbar-form navbar-right" role="search" style="color: white">
                    <span class="glyphicon glyphicon-user" style="vertical-align: middle">当前用户:</span>
                    <span id="head_operator" style="vertical-align: middle">${name}</span>
                </form>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div><!-- /.hrms_brand_nav -->

<script type="text/javascript">
    //主页面
    $("#company_logo").click(function () {
        $(this).attr("href", "/user/main");
    });
    $(document).ready(function () {
        if (document.getElementById("head_operator").innerHTML===''){
            document.getElementById("head_operator").innerHTML="未登录";
        }
    })

    $("#OC1").click(function () {
        alert("开始OC1测试");
        setInterval(ReadAndDeleteOC1,20000);
    })
    function ReadAndDeleteOC1() {
        $.ajax({
            url:"${pageContext.request.contextPath}/OC1/insertOC1",
            type:"GET"
        });

    }
    $("#OC2").click(function () {
        alert("开始OC2测试");
        setInterval(ReadAndDeleteOC2,20000);
    })
    function ReadAndDeleteOC2() {
        $.ajax({
            url:"${pageContext.request.contextPath}/OC2/insertOC2",
            type:"GET"
        });

    }

    $("#CDI").click(function () {
        alert("开始CDI测试")
       setInterval(ReadAndDeleteCDI,20000);
      //  ReadAndDeleteCDI();
    })
    function ReadAndDeleteCDI() {
        $.ajax({
            url:"${pageContext.request.contextPath}/CDI/insertCDI",
            type:"GET"
        });

    }


    $("#OC4").click(function () {
        alert("开始OC4测试")
        setInterval(ReadAndDeleteOC4,20000);

    })
    function ReadAndDeleteOC4() {
        $.ajax({
            url:"${pageContext.request.contextPath}/OC4/insertOC4",
            type:"GET"
        });

    }


    $("#QC").click(function () {
        alert("开始QC测试");
        setInterval(ReadAndDeleteQC,20000);

    })
    function ReadAndDeleteQC() {
        $.ajax({
            url:"${pageContext.request.contextPath}/QC/insertQC",
            type:"GET"
        });

    }


    $("#BIT").click(function () {
        alert("开始BIT测试");
        setInterval(ReadAndDeleteBIT,20000);

    })
    function ReadAndDeleteBIT() {
        $.ajax({
            url:"${pageContext.request.contextPath}/Bit/insertBit",
            type:"GET"
        });

    }

</script>

</body>
</html>
