<%--
  Created by IntelliJ IDEA.
  User: guyanbo
  Date: 2019/12/26
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>5S区域考核</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">


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
                        <h4 style="font-size:40px; color: #ff5a19;" >5S区域考核</h4></div>
                    <div id="result" align="right"></div>
                    <div id="form">
                        <form action="" name="link" class="link">
                            <table class="table table-bordered table-hover" style="background-color: white" >
                                <tr >
                                    <th >
                                        部门
                                    </th>
                                    <th style="width: 13%">
                                        区域
                                    </th>
                                    <th style="width: 12%">
                                        周一
                                    </th>
                                    <th style="width: 12%">
                                        周二
                                    </th>
                                    <th style="width: 12%">
                                        周三
                                    </th>
                                    <th style="width: 12%">
                                        周四
                                    </th>
                                    <th style="width: 12%">
                                        周五
                                    </th>
                                    <th style="width: 13%">
                                        平均分
                                    </th>
                                </tr>

                                <tr>
                                    <td rowspan="4" style="vertical-align: middle">
                                        生产部
                                    </td>
                                    <td>
                                        RDT区
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="RDT1" value="${RDT1}" >
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="RDT2" value="${RDT2}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="RDT3" value="${RDT3}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="RDT4" value="${RDT4}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="RDT5" value="${RDT5}">
                                    </td>
                                    <td>${RDT}</td>
                                </tr>
                                <tr>
                                    <td>
                                        OC3区
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="OC31" value="${OC31}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="OC32" value="${OC32}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="OC33" value="${OC33}" >
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="OC34" value="${OC34}" >
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="OC35" value="${OC35}" >
                                    </td>
                                    <td>${OC3}</td>
                                </tr>

                                <tr>
                                    <td>
                                        BIT区
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="BIT1" value="${BIT1}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="BIT2" value="${BIT2}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="BIT3" value="${BIT3}" >
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="BIT4" value="${BIT4}" >
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="BIT5" value="${BIT5}" >
                                    </td>
                                    <td>${BIT}</td>
                                </tr>

                                <tr>
                                    <td>
                                        组装区
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="link1" value="${link1}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="link2" value="${link2}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="link3" value="${link3}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="link4" value="${link4}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="link5" value="${link5}">
                                    </td>
                                    <td>${link}</td>
                                </tr>
                                <tr>
                                    <td rowspan="5" style="vertical-align: middle">
                                        品质/IE
                                    </td>
                                    <td>
                                        仓库区
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="store1" value="${store1}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="store2" value="${store2}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="store3" value="${store3}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="store4" value="${store4}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="store5" value="${store5}">
                                    </td>
                                    <td>${store}</td>
                                </tr>
                                <tr>
                                    <td>
                                        IQC区
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="IQC1" value="${IQC1}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="IQC2" value="${IQC2}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="IQC3" value="${IQC3}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="IQC4" value="${IQC4}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="IQC5" value="${IQC5}">
                                    </td>
                                    <td>${IQC}</td>
                                </tr>

                                <tr>
                                    <td>
                                        抽测区
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="extract1" value="${extract1}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="extract2" value="${extract2}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="extract3" value="${extract3}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="extract4" value="${extract4}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="extract5" value="${extract5}">
                                    </td>
                                    <td>${extract}</td>
                                </tr>
                                <tr>
                                    <td>
                                        换鞋区
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="changeshoe1" value="${changeshoe1}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="changeshoe2" value="${changeshoe2}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="changeshoe3" value="${changeshoe3}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="changeshoe4" value="${changeshoe4}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="changeshoe5" value="${changeshoe5}">
                                    </td>
                                    <td>${changeshoe}</td>
                                </tr>
                                <tr>
                                    <td>
                                        走道
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="walk1" value="${walk1}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="walk2" value="${walk2}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="walk3" value="${walk3}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="walk4" value="${walk4}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="walk5" value="${walk5}">
                                    </td>
                                    <td>${walk}</td>
                                </tr>
                                <tr>
                                    <td rowspan="4" style="vertical-align: middle">
                                        工程部
                                    </td>
                                    <td>
                                        办公室
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="office1" value="${office1}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="office2" value="${office2}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="office3" value="${office3}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="office4" value="${office4}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="office5" value="${office5}">
                                    </td>
                                    <td>${office}</td>
                                </tr>
                                <tr>
                                    <td>
                                        RMA区
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="RMA1" value="${RMA1}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="RMA2" value="${RMA2}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="RMA3" value="${RMA3}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="RMA4" value="${RMA4}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="RMA5" value="${RMA5}">
                                    </td>
                                    <td>${RMA}</td>
                                </tr>
                                <tr>
                                    <td>
                                        休息区
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="rest1" value="${rest1}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="rest2" value="${rest2}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="rest3" value="${rest3}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="rest4" value="${rest4}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="rest5" value="${rest5}">
                                    </td>
                                    <td>${rest}</td>
                                </tr>
                                <tr>
                                    <td>
                                        茶水间
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="tea1" value="${tea1}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="tea2" value="${tea2}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="tea3" value="${tea3}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="tea4" value="${tea4}">
                                    </td>
                                    <td>
                                        <input type="text"  style="border: none;width: 100%" id="tea5" value="${tea5}">
                                    </td>
                                    <td>${tea}</td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="text-align: right;vertical-align: middle">
                                        <input type="text" id="name" value="${name}" hidden>
                                        最低分原因：
                                    </td>
                                    <td>
                                        <textarea  rows="2"   style="OVERFLOW:hidden;border: none ;width: 100%"  id="cause1" >${cause1}</textarea>
                                    </td>
                                    <td>
                                        <textarea  rows="2"   style="OVERFLOW:hidden;border: none ;width: 100%"  id="cause2" >${cause2}</textarea>
                                    </td>
                                    <td>
                                        <textarea  rows="2"   style="OVERFLOW:hidden;border: none ;width: 100%"  id="cause3" >${cause3}</textarea>
                                    </td>
                                    <td>
                                        <textarea  rows="2"   style="OVERFLOW:hidden;border: none ;width: 100%"  id="cause4" >${cause4}</textarea>
                                    </td>
                                    <td>
                                        <textarea  rows="2"   style="OVERFLOW:hidden;border: none ;width: 100%"  id="cause5" >${cause5}</textarea>
                                    </td>
                                    <td  style="text-align: center;vertical-align: middle">
                                        <input style="width: 80%" type="button" class="btn btn-default" id="insert1" value="保存" onclick="insert()">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>

                </div>

                <script type="text/javascript">
                    var oTable = document.getElementsByTagName("table")[0];
                    var rows=oTable.rows;

                    var operator=$("#name").val();


                    var tb=[];
                    for (var j=0;j<14;j++){
                        tb[j]=[];
                    }
                    for (var i=1;i<=5;i++){
                        tb[0][i-1]=$("#RDT"+i).val();
                        tb[1][i-1]=$("#OC3"+i).val();
                        tb[2][i-1]=$("#BIT"+i).val();
                        tb[3][i-1]=$("#link"+i).val();
                        tb[4][i-1]=$("#store"+i).val();
                        tb[5][i-1]=$("#IQC"+i).val();
                        tb[6][i-1]=$("#extract"+i).val();
                        tb[7][i-1]=$("#changeshoe"+i).val();
                        tb[8][i-1]=$("#walk"+i).val();
                        tb[9][i-1]=$("#office"+i).val();
                        tb[10][i-1]=$("#RMA"+i).val();
                        tb[11][i-1]=$("#rest"+i).val();
                        tb[12][i-1]=$("#tea"+i).val();
                        tb[13][i-1]=$("#cause"+i).val();
                    }

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
                    //获取当前周几
                    function week() {
                        var now=new Date();//创建Date对象
                        var day=now.getDay();//获取星期
                        return day;
                    }
                    //获取当前时刻
                    function hour() {
                        var now=new Date();//创建Date对象
                        var h=now.getHours();//获取小时
                        return h;
                    }

                    //播放声音
                    function playSound() {
                        var borswer = window.navigator.userAgent.toLowerCase();
                        if ( !!window.ActiveXObject || "ActiveXObject" in window )
                        {
                            //IE内核浏览器
                            var OSPlayer = new ActiveXObject("WMPLayer.OCX");
                            OSPlayer.url = "http://www.xmf119.cn/static/admin/sounds/notify.wav";
                            OSPlayer.controls.play();
                        } else
                        {
                            //非IE内核浏览器
                            var strAudio = "<audio id='audioPlay' src='http://www.xmf119.cn/static/admin/sounds/notify.wav' hidden='true'>";
                            if ( $( "body" ).find( "audio" ).length <= 0 )
                                $( "body" ).append( strAudio );
                            var audio = document.getElementById( "audioPlay" );
                            //浏览器支持 audion
                            audio.play();
                        }
                    }

                    function insert() {
                        //alert(operator);
                        var w=week();
                        //alert(w);
                        tb[0][w-1]=$("#RDT"+w).val();
                        tb[1][w-1]=$("#OC3"+w).val();
                        tb[2][w-1]=$("#BIT"+w).val();
                        tb[3][w-1]=$("#link"+w).val();
                        tb[4][w-1]=$("#store"+w).val();
                        tb[5][w-1]=$("#IQC"+w).val();
                        tb[6][w-1]=$("#extract"+w).val();
                        tb[7][w-1]=$("#changeshoe"+w).val();
                        tb[8][w-1]=$("#walk"+w).val();
                        tb[9][w-1]=$("#office"+w).val();
                        tb[10][w-1]=$("#RMA"+w).val();
                        tb[11][w-1]=$("#rest"+w).val();
                        tb[12][w-1]=$("#tea"+w).val();
                        tb[13][w-1]=$("#cause"+w).val();

                        var h=hour();

                        if (!((w==1&&operator=="王光文")||(w==2&&operator=="朱军")||(w==3&&operator=="曹波")||(w==4&&operator=="刘玉强")||(w==5&&operator=="张建东"))){
                            alert("您没有权限操作！")
                        }else  if (h<15||h>16){
                            alert("当前时间不可操作！");
                            //document.getElementById("insert").blur();
                        }else
                        if (tb[0][w-1]==''){
                            alert("请输入RDT分数！");
                            document.getElementById("RDT"+w).focus();
                        }else if (tb[1][w-1]=='') {
                            alert("请输入OC3分数！");
                            document.getElementById("OC3" + w).focus();
                        }else if (tb[2][w-1]=='') {
                            alert("请输入BIT分数！");
                            document.getElementById("BIT" + w).focus();
                        }else if (tb[3][w-1]=='') {
                            alert("请输入组装区分数！");
                            document.getElementById("link" + w).focus();
                        }else if (tb[4][w-1]=='') {
                            alert("请输入仓库区分数！");
                            document.getElementById("store" + w).focus();
                        }else if (tb[5][w-1]=='') {
                            alert("请输入IQC分数！");
                            document.getElementById("IQC" + w).focus();
                        }else if (tb[6][w-1]=='') {
                            alert("请输入抽测区分数！");
                            document.getElementById("extract" + w).focus();
                        }else if (tb[7][w-1]=='') {
                            alert("请输入换鞋区分数！");
                            document.getElementById("changeshoe" + w).focus();
                        }else if (tb[8][w-1]=='') {
                            alert("请输入走道分数！");
                            document.getElementById("walk" + w).focus();
                        }else if (tb[9][w-1]=='') {
                            alert("请输入办公区分数！");
                            document.getElementById("office" + w).focus();
                        }else if (tb[10][w-1]=='') {
                            alert("请输入RMA分数！");
                            document.getElementById("RMA" + w).focus();
                        }else if (tb[11][w-1]=='') {
                            alert("请输入休息区分数！");
                            document.getElementById("rest" + w).focus();
                        }else if (tb[12][w-1]=='') {
                            alert("请输入茶水间分数！");
                            document.getElementById("tea" + w).focus();
                        }else if (tb[13][w-1]=='') {
                            alert("请输入最低分原因！");
                            document.getElementById("cause" + w).focus();
                        }else {

                            $.ajax({
                                url:"/environ/insert",
                                //type:"POST",
                                data:{"RDT": tb[0][w-1],"OC3":tb[1][w-1],"BIT":tb[2][w-1],"link":tb[3][w-1],"store":tb[4][w-1],"IQC":tb[5][w-1],"extract":tb[6][w-1],"changeshoe":tb[7][w-1],"walk":tb[8][w-1],"office":tb[9][w-1],"RMA":tb[10][w-1],"rest":tb[11][w-1],"tea":tb[12][w-1],"cause":tb[13][w-1]},
                                success:function (res) {
                                    if (res.code==100){
                                        var s=res.extendInfo.s;
                                        alert(s);
                                        window.location.href="/environ/selectthisweek";
                                    }
                                }
                            });
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
