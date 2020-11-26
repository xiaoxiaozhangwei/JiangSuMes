<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: guyanbo
  Date: 2020/1/3
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>5S评比图片</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">

    <style type="text/css">
        table
        {
            border-collapse:collapse;
            width: 100%;
        }

        table, td, th
        {
            font-family: 幼圆;
            background-color: white;
            border:1px solid #a4a4a4;

        }
    </style>


</head>
<body  onload="showTime()">
<div >
    <!-- 导航栏-->
    <%@ include file="./commom/head.jsp"%>


    <!-- 中间部分（左侧栏+表格内容） -->
    <div >
        <!-- 左侧栏 -->
        <%@ include file="commom/left_ziliao.jsp"%>

        <!-- 表格内容 -->
        <div class="dept_info col-sm-10">

            <div class="panel panel-success">

                <!-- 路径导航 -->
                <div class="panel-heading">

                    <div align="center">
                        <h4 style="font-size:40px; color: #ff5a19;" >5S评比图片</h4></div>
                    <div id="result" align="right"></div>
                    <div style="float: left;width: 30%;height: 70%">
                        区域：
                        <select class="form-control" id="area">
                            <option value="0">——请选择——</option>
                            <option value="RDT区">RDT区</option>
                            <option value="OC3区">OC3区</option>
                            <option value="BIT区">BIT区</option>
                            <option value="组装区 ">组装区 </option>
                            <option value="仓库区 ">仓库区 </option>
                            <option value="IQC区">IQC区</option>
                            <option value="抽测区 ">抽测区 </option>
                            <option value="换鞋区 ">换鞋区 </option>
                            <option value="走道  ">走道 </option>
                            <option value="办公室 ">办公室 </option>
                            <option value="RMA区">RMA区</option>
                            <option value="休息区 ">休息区 </option>
                            <option value="茶水间 ">茶水间 </option>
                        </select>
                        <br>
                        图片：
                        <input id="fileBtn" onchange="change()" name="file" type="file" style="width: 100%">
                        <br>
                        <div contenteditable style="width: 100%;height: 50%; border:1px solid">
                            <img style="width: 100%" src="" id="nophoto">
                        </div>
                        <br>
                        问题描述：
                        <textarea class="form-control" style="width: 100%;height: 20%" id="question"></textarea>
                        <br>
                        <input style="width: 20%;float: right"  type="button" class="btn btn-default" value="保存" onclick="addpict()">
                    </div>

                    <div style="margin-left: 35%;width: 65%;height: 70%">
                        <br>
                        <div style="height:23px;overflow-x: auto;overflow-y: auto;" id="tab1">
                            <table style="font-size: 20px">
                                <thead>
                                <tr >
                                    <th style="width: 15%;text-align: center">编号</th>
                                    <th style="width: 15%;text-align: center">时间</th>
                                    <th style="width: 18%">区域</th>
                                    <th style="width: 30%">图片</th>
                                    <th style="width: 22%">问题描述</th>
                                </tr>
                                </thead>
                            </table>
                        </div>

                        <div style="height:95%;overflow-x: auto;overflow-y: auto;" id="tab2">
                            <table >
                                <tbody>
                                <c:forEach items="${pict}" var="p">
                                    <tr >
                                        <td style="width: 15%;text-align: center">${p.picture_id}</td>
                                        <td style="width: 15%;text-align: center">${p.date2}</td>
                                        <td style="width: 18%">${p.area}</td>
                                        <td style="width: 30%"><img  height="150px" src="${p.picture}"></td>
                                        <td style="width: 22%">${p.question}</td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>

                    </div>

                </div>

                <script type="text/javascript">

                    $(document).ready(function () {
                        var m=document.getElementById('tab2');
                        m.scrollTop=m.scrollHeight;//通过设置滚动高度
                        var n=document.getElementById('tab1');
                        n.scrollTop=n.scrollHeight;//通过设置滚动高度
                    });

                    var img;
                    //显示时间
                    function showTime() {
                        var da = '';
                        var now = new Date();
                        var now_m = now.getMonth() + 1;
                        now_m = (now_m < 10) ? '0' + now_m : now_m;
                        var now_d = now.getDate();
                        now_d = (now_d < 10) ? '0' + now_d : now_d;
                        da = now.getFullYear() + '-' + now_m + '-' + now_d;

                        var date1 = new Date(da.substring(0, 4), parseInt(da.substring(5, 7)) - 1, da.substring(8, 10));//当前日期
                        var date2 = new Date(da.substring(0, 4), 0, 1); //1月1号
                        //获取1月1号星期（以周一为第一天，0周一~6周日）
                        var dateWeekNum = date2.getDay() - 1;
                        if (dateWeekNum < 0) {
                            dateWeekNum = 6;
                        }
                        if (dateWeekNum < 4) {
                            //前移日期
                            date2.setDate(date2.getDate() - dateWeekNum);
                        } else {
                            //后移日期
                            date2.setDate(date2.getDate() + 7 - dateWeekNum);
                        }
                        var d = Math.round((date1.valueOf() - date2.valueOf()) / 86400000);
                        if (d < 0) {
                            var date3 = (date1.getFullYear() - 1) + "-12-31";
                            return getYearWeek(date3);
                        } else
                        {
                            //得到年数周数
                            var year = date1.getFullYear();
                            var week = Math.ceil((d + 1) / 7);
                            var str = "时间为: " + year + "年第" + week + "周  " + da + " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
                            var t=da + " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
                            var obj = document.getElementById("result");
                            obj.innerHTML = str;
                            window.setTimeout("showTime()", 1000);
                            return t;

                        }
                    }

                    //获取图片
                    function change(){
                        //获取需要处理的DOM对象
                        //alert("1");
                        var pic = document.getElementById("nophoto"),
                            file = document.getElementById("fileBtn");
                        //获取图片后缀
                        var ext = file.value.substring(file.value.lastIndexOf(".")+1).toLowerCase();
                        //gif在IE浏览器暂时无法显示
                        if(ext != 'png' && ext != 'jpg' && ext != 'jpeg'){
                            alert("图片的格式必须为png或者jpg或者jpeg格式！");
                            return;
                        }
                        //alert("2");


                        //判断ie类型
                        var isIE = navigator.userAgent.match(/MSIE/) != null,
                            isIE6 = navigator.userAgent.match(/MSIE 6.0/) != null;

                        //根据浏览器的类型进行操作方式的选择
                        if(isIE) {
                            alert("IE");
                            //选中表单提交中的file对象,即获得焦点，可以继续保持
                            file.select();
                            //因为ie9安全级别高，需要模拟让其失去焦点
                            //file.blur();
                            //获取文件的本地地址
                            var reallocalpath = document.selection.createRange().text;   //document.selection.createRange(); --->window.getSelection(); [ie11]
                            //alert(reallocalpath);
                            //IE6浏览器设置img的src为本地路径可以直接显示图片
                            if(isIE6){
                                alert("IE6");
                                //加载预览图
                                pic.src = reallocalpath;
                            }else{
                                alert("this");
                                //非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
                                document.getElementById("file1").value=reallocalpath;
                                pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src=\"" + reallocalpath + "\")";
                                //pic.style.cssText="display: block;width:106px ;height: 130px;margin: 0 auto;";
                                //设置img的src为base64编码的透明图片 取消显示浏览器默认图片
                                pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
                                //alert(reallocalpath);
                                pic.src=reallocalpath;
                            }
                        }else {
                            var fileObj = document.getElementById("fileBtn").files[0];
                            size(fileObj);
                            html5Reader(file);
                        }
                    }

                    //判断图片大小是否需要压缩
                    function size(fileObj) {
                        //alert("判断图片大小是否需要压缩");
                        //alert(fileObj.size);
                        if (fileObj.size>=(0.05*1024*1024)) {

                            //调用函数,对图片进行压缩
                            imageDeal(fileObj, dealChange);


                        }else{
                            directTurnIntoBase64(fileObj,function (imgBase64) {

                                imgBase64 = imgBase64;

                                //$('#viewImg').attr('src',imgBase64);
                                img=imgBase64;
                                //alert("不用进行压缩");

                            });
                        }
                    }

                    //不对图片进行压缩
                    function directTurnIntoBase64(fileObj,callback) {

                        var r = new FileReader();

                        //转成base64

                        r.onload = function () {

                            fileObj = r.result;

                            //   console.log(imgBase64);

                            callback(fileObj);

                        }

                        r.readAsDataURL(fileObj);//转成base64格式

                    }

                    //html5预览图片方法
                    function html5Reader(file){
                        var file = file.files[0];
                        var reader = new FileReader();
                        reader.readAsDataURL(file);
                        reader.onload = function(){
                            var pic = document.getElementById("nophoto");
                            pic.src = this.result;
                        }
                        reader.readAsDataURL(file);
                    }

                    //保存到数据库
                    function addpict() {

                        //alert(img);
                        if(document.getElementById("area").value==0){
                            alert("请选择区域！");
                            document.getElementById("area").focus();
                        }else {
                            if (document.getElementById("question").value==""){
                                alert("请阐明问题！");
                                document.getElementById("question").focus();
                            }else{
                                $.ajax({
                                    url:"/environ/insertpicture",
                                    type:"POST",
                                    data:{"area":document.getElementById("area").value,"picture":img,"question":document.getElementById("question").value},
                                    success:function (result) {
                                        if (result.code==100){
                                            alert("保存成功！");
                                            img="";
                                            window.location.href="/environ/selectallpict";
                                        }
                                    }
                                })
                            }
                        }
                    }


                    function dealChange(blob, base64) {

                        img=base64;
                        //alert(img);
                    }

                    //2  从input元素中，获取到file并转成base64，然后调用缩放方法

                    function imageDeal(file, returnBase64) {

                        //获取file，转成base64

                        //var file = ele.files[0]; //取传入的第一个文件

                        if(undefined == file) { //如果未找到文件，结束函数，跳出

                            return;

                        }

                        //alert("fileSize" + file.size);

                       // alert(file.type);



                        var r = new FileReader();

                        r.readAsDataURL(file);

                        r.onload = function(e) {

                            var base64 = e.target.result;

                            var bili = 2;

                            //alert("压缩前：" + base64.length);
                           // alert(base64);

                            suofang(base64, bili, returnBase64);

                        }

                    }

                    function suofang(base64, bili, callback) {

                        //alert("执行缩放程序,bili=" + bili);

                        //处理缩放，转格式

                        var _img = new Image();

                        _img.src = base64;

                        _img.onload = function() {

                            var _canvas = document.createElement("canvas");

                            var w = this.width / bili;
                            var h = this.height / bili;
                            //alert(w+h);


                            _canvas.setAttribute("width", Number(w));
                            //alert(w);

                            _canvas.setAttribute("height", Number(h));
//alert(h);
                            _canvas.getContext("2d").drawImage(this, 0, 0, w, h);

                            var base64 = _canvas.toDataURL("image/jpeg");
//alert(base64);
//alert(3);
                            _canvas.toBlob(function(blob) {

                                //alert(blob.size);



                                if(blob.size > 0.05*1024*1024){

                                    //alert("继续压缩");
                                    suofang(base64, bili, callback);

                                }else{

                                    callback(blob, base64);

                                }

                            }, "image/jpeg");

                        }

                    }
                </script>
            </div><!-- /.panel panel-success -->
        </div><!-- /.dept_info -->
    </div><!-- /.hrms_dept_body -->

    <!-- 尾部-->
    <%@ include file="./commom/foot.jsp"%>

</div><!-- /.hrms_dept_container -->


</body>
</html>
