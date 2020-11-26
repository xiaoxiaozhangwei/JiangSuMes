
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";


%>
<html>
<head>
    <title>组装看板信息页面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <!--界面刷新-->
    <meta http-equiv="refresh" content="120">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="../css/cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="../js/cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="../js/cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        .title{text-align: center}
    </style>

</head>

<body  onload="showTime()">


<!-- 表格内容 -->
<div class="dept_info col-sm-10" style="width: 100%;height: 100%">

    <div class=" panel-success" >
        <!--
        <marquee >
            <a style="color: red;font-size: 40px">热烈欢迎华为技术有限公司各位领导和专家莅临指导</a>
        </marquee>
-->
        <!-- 路径导航 -->
        <div class="panel-heading" >
            <!--            <div style="float: left">
                <img src="${pageContext.request.contextPath}/static/img/log3.png" width="250px">
            </div>
-->
            <div align="center">
                <h4 style="font-size:40px; color: #ff5a19;text-align: center" >智能制造工业物联信息化管理系统</h4></div>
            <div id="result" align="right"></div>
        </div><!-- /.dept_info -->
    </div><!-- /.hrms_dept_body -->

    <br>
    <h3 class="title">组装线</h3>
    <table class="table table-bordered table-hover" >
        <!--  <table class="table" align="center">-->

        <tr>

            <th style="text-align: center;background-color: #6295eb;vertical-align: middle;width: 5%" rowspan="2" >线别</th>
            <th style="text-align: center;background-color: #7db2ff" colspan="1" >S/N 投入</th>
            <th style="text-align: center;background-color: #98caff" colspan="4" >OC4</th>
            <th style="text-align: center;background-color: #7db2ff" colspan="4" >CDI</th>
            <th style="text-align: center;background-color: #98caff" colspan="4" >目检</th>
            <th style="text-align: center;background-color: #7db2ff" colspan="3" >包装</th>
        </tr>
        <tr>
            <!--<th style="text-align: center;background-color: #7db2ff">目标</th>-->
            <th style="text-align: center;background-color: #7db2ff">实际</th>
            <!--<th style="text-align: center;background-color: #7db2ff">达成率</th>-->
            <th style="text-align: center;background-color: #98caff">良品数</th>
            <th style="text-align: center;background-color: #98caff">不良数</th>
            <th style="text-align: center;background-color: #98caff">小计</th>
            <th style="text-align: center;background-color: #98caff">良率</th>
            <th style="text-align: center;background-color: #7db2ff">良品数</th>
            <th style="text-align: center;background-color: #7db2ff">不良数</th>
            <th style="text-align: center;background-color: #7db2ff">小计</th>
            <th style="text-align: center;background-color: #7db2ff">良率</th>
            <th style="text-align: center;background-color: #98caff">良品数</th>
            <th style="text-align: center;background-color: #98caff">不良数</th>
            <th style="text-align: center;background-color: #98caff">小计</th>
            <th style="text-align: center;background-color: #98caff">良率</th>
            <th style="text-align: center;background-color: #7db2ff">盘片数量</th>
            <th style="text-align: center;background-color: #7db2ff">内箱数</th>
            <th style="text-align: center;background-color: #7db2ff">外箱数</th>
        </tr>
        <tr>
            <th style="text-align: center;background-color: #6295eb" >Line1</th>
            <!--<td style="text-align: center;background-color: #7db2ff" >${mubiao}      </td>-->
            <td style="text-align: center;background-color: #7db2ff" >${LinkLine1}</td>
            <!--<td style="text-align: center;background-color: #7db2ff" >${realpercent}%</td>-->
            <td style="text-align: center;background-color: #98caff" >${oc4PassLine1}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4NGLine1}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4xiaojiline1}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4percentageline1}</td>
            <td style="text-align: center;background-color: #7db2ff" >${cdiPassLine1}</td>
            <td style="text-align: center;background-color: #7db2ff" >${cdiNGLine1}</td>
            <td style="text-align: center;background-color: #7db2ff" >${CDIxiaoji1}</td>
            <td style="text-align: center;background-color: #7db2ff" >${CDIpercentline1}</td>
            <td style="text-align: center;background-color: #98caff" >${checkPassLine1}     </td>
            <td style="text-align: center;background-color: #98caff" >${checkNGLine1}     </td>
            <td style="text-align: center;background-color: #98caff" >${checkLine1Total}</td>
            <td style="text-align: center;background-color: #98caff" >${checkrightpercent1}%</td>
            <td style="text-align: center;background-color: #7db2ff" >${SnCountLine1}  </td>
            <td style="text-align: center;background-color: #7db2ff" >${NboxCountLine1}</td>
            <td style="text-align: center;background-color: #7db2ff" >${WboxCountLine1}</td>
        </tr>
        <tr>
            <th style="text-align: center;background-color: #6295eb" >Line2</th>
            <!--<td style="text-align: center;background-color: #7db2ff"></td>-->
            <td style="text-align: center;background-color: #7db2ff">${LinkLine2} </td>
            <!--<td style="text-align: center;background-color: #7db2ff"></td>-->
            <td style="text-align: center;background-color: #98caff" >${oc4PassLine2}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4NGLine2}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4xiaojiline2}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4percentageline2}</td>
            <td style="text-align: center;background-color: #7db2ff" >${cdiPassLine2}</td>
            <td style="text-align: center;background-color: #7db2ff" >${cdiNGLine2}</td>
            <td style="text-align: center;background-color: #7db2ff" >${CDIxiaoji2}</td>
            <td style="text-align: center;background-color: #7db2ff" >${CDIpercentline2}</td>
            <td style="text-align: center;background-color: #98caff">${checkPassLine2}    </td>
            <td style="text-align: center;background-color: #98caff">${checkNGLine2}    </td>
            <td style="text-align: center;background-color: #98caff">${checkLine2Total}</td>
            <td style="text-align: center;background-color: #98caff">${checkrightpercent2}%</td>
            <td style="text-align: center;background-color: #7db2ff">${SnCountLine2}  </td>
            <td style="text-align: center;background-color: #7db2ff">${NboxCountLine2}</td>
            <td style="text-align: center;background-color: #7db2ff">${WboxCountLine2}</td>
        </tr>
        <tr>
            <th style="text-align: center;background-color: #6295eb" >Line3</th>
            <td style="text-align: center;background-color: #7db2ff">${LinkLine3} </td>
            <td style="text-align: center;background-color: #98caff" >${oc4PassLine3}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4NGLine3}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4xiaojiline3}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4percentageline3}</td>
            <td style="text-align: center;background-color: #7db2ff" >${cdiPassLine3}</td>
            <td style="text-align: center;background-color: #7db2ff" >${cdiNGLine3}</td>
            <td style="text-align: center;background-color: #7db2ff" >${CDIxiaoji3}</td>
            <td style="text-align: center;background-color: #7db2ff" >${CDIpercentline3}</td>
            <td style="text-align: center;background-color: #98caff" >${checkPassLine3}    </td>
            <td style="text-align: center;background-color: #98caff" >${checkNGLine3}    </td>
            <td style="text-align: center;background-color: #98caff" >${checkLine3Total}</td>
            <td style="text-align: center;background-color: #98caff" >${checkrightpercent3}%</td>
            <td style="text-align: center;background-color: #7db2ff" >${SnCountLine3}  </td>
            <td style="text-align: center;background-color: #7db2ff" >${NboxCountLine3}</td>
            <td style="text-align: center;background-color: #7db2ff" >${WboxCountLine3}</td>
        </tr>
        <tr>
            <th style="text-align: center;background-color: #6295eb" >Line4</th>
            <td style="text-align: center;background-color: #7db2ff">${LinkLine4} </td>
            <td style="text-align: center;background-color: #98caff" >${oc4PassLine4}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4NGLine4}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4xiaojiline4}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4percentageline4}</td>
            <td style="text-align: center;background-color: #7db2ff" >${cdiPassLine4}</td>
            <td style="text-align: center;background-color: #7db2ff" >${cdiNGLine4}</td>
            <td style="text-align: center;background-color: #7db2ff" >${CDIxiaoji4}</td>
            <td style="text-align: center;background-color: #7db2ff" >${CDIpercentline4}</td>
            <td style="text-align: center;background-color: #98caff" >${checkPassLine4}    </td>
            <td style="text-align: center;background-color: #98caff" >${checkNGLine4}    </td>
            <td style="text-align: center;background-color: #98caff" >${checkLine4Total}</td>
            <td style="text-align: center;background-color: #98caff" >${checkrightpercent4}%</td>
            <td style="text-align: center;background-color: #7db2ff" >${SnCountLine4}  </td>
            <td style="text-align: center;background-color: #7db2ff" >${NboxCountLine4}</td>
            <td style="text-align: center;background-color: #7db2ff" >${WboxCountLine4}</td>
        </tr>
        <tr>
            <th style="text-align: center;background-color: #6295eb" >Line5</th>
            <td style="text-align: center;background-color: #7db2ff">${LinkLine5} </td>
            <td style="text-align: center;background-color: #98caff" >${oc4PassLine5}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4NGLine5}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4xiaojiline5}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4percentageline5}</td>
            <td style="text-align: center;background-color: #7db2ff" >${cdiPassLine5}</td>
            <td style="text-align: center;background-color: #7db2ff" >${cdiNGLine5}</td>
            <td style="text-align: center;background-color: #7db2ff" >${CDIxiaoji5}</td>
            <td style="text-align: center;background-color: #7db2ff" >${CDIpercentline5}</td>
            <td style="text-align: center;background-color: #98caff" >${checkPassLine5}    </td>
            <td style="text-align: center;background-color: #98caff" >${checkNGLine5}    </td>
            <td style="text-align: center;background-color: #98caff" >${checkLine5Total}</td>
            <td style="text-align: center;background-color: #98caff" >${checkrightpercent5}%</td>
            <td style="text-align: center;background-color: #7db2ff" >${SnCountLine5}  </td>
            <td style="text-align: center;background-color: #7db2ff" >${NboxCountLine5}</td>
            <td style="text-align: center;background-color: #7db2ff" >${WboxCountLine5}</td>
        </tr>
        <tr>
            <th style="text-align: center;background-color: #6295eb" >Line6</th>
            <td style="text-align: center;background-color: #7db2ff">${LinkLine6} </td>
            <td style="text-align: center;background-color: #98caff" >${oc4PassLine6}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4NGLine6}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4xiaojiline6}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4percentageline6}</td>
            <td style="text-align: center;background-color: #7db2ff" >${cdiPassLine6}</td>
            <td style="text-align: center;background-color: #7db2ff" >${cdiNGLine6}</td>
            <td style="text-align: center;background-color: #7db2ff" >${CDIxiaoji6}</td>
            <td style="text-align: center;background-color: #7db2ff" >${CDIpercentline6}</td>
            <td style="text-align: center;background-color: #98caff" >${checkPassLine6}    </td>
            <td style="text-align: center;background-color: #98caff" >${checkNGLine6}    </td>
            <td style="text-align: center;background-color: #98caff" >${checkLine6Total}</td>
            <td style="text-align: center;background-color: #98caff" >${checkrightpercent6}%</td>
            <td style="text-align: center;background-color: #7db2ff" >${SnCountLine6}  </td>
            <td style="text-align: center;background-color: #7db2ff" >${NboxCountLine6}</td>
            <td style="text-align: center;background-color: #7db2ff" >${WboxCountLine6}</td>
        </tr>
        <tr>
            <th style="text-align: center;background-color: #6295eb">小计</th>
            <!--<td style="text-align: center;background-color: #7db2ff">${mubiao}      </td>-->
            <td style="text-align: center;background-color: #7db2ff">${LinkCount}        </td>
            <!--<td style="text-align: center;background-color: #7db2ff">${realpercent}%</td>-->
            <td style="text-align: center;background-color: #98caff" >${oc4totalpass}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4totalNG}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4total}</td>
            <td style="text-align: center;background-color: #98caff" >${oc4percent}</td>
            <td style="text-align: center;background-color: #7db2ff" >${CDItotalPass}</td>
            <td style="text-align: center;background-color: #7db2ff" >${CDItotalNG}</td>
            <td style="text-align: center;background-color: #7db2ff" >${CDItotal}</td>
            <td style="text-align: center;background-color: #7db2ff" >${CDIpercent}</td>
            <td style="text-align: center;background-color: #98caff">${checkPassTotal}</td>
            <td style="text-align: center;background-color: #98caff">${checkNGTotal}</td>
            <td style="text-align: center;background-color: #98caff">${checkLineTotal}</td>
            <td style="text-align: center;background-color: #98caff">${checkpercent}%</td>
            <td style="text-align: center;background-color: #7db2ff">${BaoZhuangSNCount} </td>
            <td style="text-align: center;background-color: #7db2ff">${BaoZhuangNboxCount}</td>
            <td style="text-align: center;background-color: #7db2ff">${BaoZhuangWboxCount}</td>
        </tr>
        <!--
        <tr>
            <th style="text-align: center;background-color: #6295eb">NPI</th>
            <td style="text-align: center;background-color: #7db2ff">${NPIlink}</td>
            <td style="text-align: center;background-color: #98caff" >${NPIoc4totalpass}</td>
            <td style="text-align: center;background-color: #98caff" >${NPIoc4totalNG}</td>
            <td style="text-align: center;background-color: #98caff" >${NPIoc4total}</td>
            <td style="text-align: center;background-color: #98caff" >${NPIoc4percent}</td>
            <td style="text-align: center;background-color: #7db2ff" >${NPICDItotalPass}</td>
            <td style="text-align: center;background-color: #7db2ff" >${NPICDItotalNG}</td>
            <td style="text-align: center;background-color: #7db2ff" >${NPICDItotal}</td>
            <td style="text-align: center;background-color: #7db2ff" >${NPICDIpercent}</td>
            <td style="text-align: center;background-color: #98caff" >${NPIcheckrightnum}</td>
            <td style="text-align: center;background-color: #98caff" >${NPIcheckwrongnum}</td>
            <td style="text-align: center;background-color: #98caff" >${NPIchecknum}</td>
            <td style="text-align: center;background-color: #98caff" >${NPIcheckpercent}</td>
            <td style="text-align: center;background-color: #7db2ff" >${NPIcountpro} </td>
            <td style="text-align: center;background-color: #7db2ff" >${NPIcountNbox}</td>
            <td style="text-align: center;background-color: #7db2ff" >${NPIcountWbox}</td>
        </tr>
-->
    </table>


</div><!-- /.hrms_dept_container -->


<script type="text/javascript">
    var oTable = document.getElementsByTagName("table")[0];
    var rows=oTable.rows;

    var oTable2 = document.getElementsByTagName("table")[1];
    var rows2=oTable2.rows;


    function showTime() {
        var da = '';
        var now = new Date();
        var now_m = now.getMonth() + 1;
        now_m = (now_m < 10) ? '0' + now_m : now_m;
        var now_d = now.getDate();
        now_d = (now_d < 10) ? '0' + now_d : now_d;
        da = now.getFullYear() + '-' + now_m + '-' + now_d;

        if (now.getHours()===0&&now.getSeconds()==0&&now.getMinutes()==0){
            $.ajax({
                url:"/pack/resetcount"
            })
        }

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
            var obj = document.getElementById("result");
            obj.innerHTML = str;
            window.setTimeout("showTime()", 1000);
            return t;
        }            var t=da + " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();

    }
    function selectcount() {

        $.ajax({
            url:"${pageContext.request.contextPath}/check/selectcount",
            type:"GET"
        });

    }

</script>

</body>


</html>
