<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>包装站数据导出</title>
    <style>

        #model1{
            width:600px;
            height:60px;
            margin-left:-250px;
            margin-top:-150px;
            position:fixed;
            top:50%;
            left:50%;
            z-index: 200;
        }
        #outer{
            position:fixed;
            top:0;
            left:0;
            width:100%;
            height:100%;
            z-index:100;
            padding-top:10%;
            text-align:center;
            background-repeat:no-repeat;
            background-position:center center;
            background-color:#000;
            background-color:rgba(0,0,0,0.5);
            filter:alpha(opacity=50);
            display: none;
        }

    </style>
</head>
<body>
<div >
    <!-- 导航栏-->
    <%@ include file="./commom/head.jsp"%>


    <!-- 中间部分（左侧栏+表格内容） -->
    <div >
        <!-- 左侧栏 -->
        <%@ include file="commom/left-warehouse.jsp"%>

        <!-- 表格内容 -->
        <div class="dept_info col-sm-10">

            <div class="panel panel-success">

                <!-- 路径导航 -->
                <div class="panel-heading">

                    <div align="center">
                        <h4 style="font-size:40px; color: #ff5a19;" >仓库接收</h4></div>
                    <div id="result" align="right"></div>
                    <div id="form">
                        <form action="" name="link" class="link">
                            <table class="table">
                                <tr>
                                    <td>
                                        <div class="input-group">
                                            <span class="input-group-addon">请扫外箱信息</span>
                                            <input type="text" name="wbox" id="wboxnumber" class="form-control"
                                                   onchange="settime()" maxlength="17"
                                                   minlength="17">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group" >
                                            <span class="input-group-addon">外箱号</span>
                                            <input type="text" name="momum" id="wbox" class="form-control"  onchange="selectByWbox()">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group" >
                                            <span class="input-group-addon">数量</span>
                                            <input type="text" name="num" id="num" class="form-control" readonly>
                                        </div>
                                    </td>
                                    <td>
                                        <select id="storageType"  name="storageType">
                                            <option value="请选择阶段">请选择阶段</option>
                                            <option value="量产">量产</option>
                                            <option value="PVT">PVT</option>
                                            <option value="EVT">EVT</option>
                                            <option value="DVT">DVT</option>
                                        </select>
                                    </td>
                                    <td style="text-align: right;float:right;margin-right: 50px">
                                        <button type="button" class="btn btn-success" id="save">入库</button>
                                    </td>
                                    <td style="text-align: left;float:right;margin-right: 50px">
                                        <button type="button" class="btn btn-primary" id="select">查询</button>
                                    </td>

                                </tr>
                                <tr>
                                    <td>
                                        <div class="input-group" >
                                            <span class="input-group-addon">ERP料号</span>
                                            <input type="text" name="erp" id="erp" class="form-control">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group" >
                                            <span class="input-group-addon">入库单</span>
                                            <input type="text" name="stock" id="storageMo" class="form-control">
                                        </div>
                                    </td>
                                        <td>
                                            <div class="input-group" >
                                                <span class="input-group-addon">仓别</span>
                                                <input type="text" name="stock" id="stock" class="form-control">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="input-group" >
                                                <span class="input-group-addon">储位</span>
                                                <input type="text" name="store_area" id="store_area" class="form-control">
                                            </div>
                                        </td>
                                </tr>


                            </table>
                            <div style="width:100%; border:0.5px solid gray;"></div>
                            <br>
                            <table class="table">
                                <tr>
                                    <td>
                                        <div class="input-group">
                                            <!--
                                            <span class="input-group-addon">请输入导出的工单号</span>
                                            <input type="text" name="momum" id="mo" class="form-control">-->
                                            <span class="input-group-addon">请输入要导出的入库单号</span>
                                            <input type="text" name="" id="mo" class="form-control">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group" style="float: right">
                                            <span class="input-group-addon">日期</span>
                                            <input type="date" id="pack_date" >
                                            <input type="checkbox" id="check" checked>
                                        </div>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-primary" id="selectAll">根据入库单查询</button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-info xitc">导出芯盛入库单</button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-warning goke">导出国科入库单</button>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <div class="input-group">
                                            <!--
                                            <span class="input-group-addon">请输入导出的工单号</span>
                                            <input type="text" name="momum" id="mo" class="form-control">-->
                                            <span class="input-group-addon">请输入要导出的工单号</span>
                                            <input type="text" name="" id="mo_id" class="form-control">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group" style="float: right">
                                            <span class="input-group-addon">日期</span>
                                            <input type="date" id="pack_date2" >
                                            <input type="checkbox" id="check2" checked>
                                        </div>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-primary" id="selectMo">根据工单查询</button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-info xitc2">导出芯盛工单</button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-warning goke2">导出国科工单</button>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>

                    <ol class="breadcrumb" style="">
                        <li class="active">数据信息/</li>
                        <a href="#">显示全部</a>
                    </ol>
                </div>
                <div style="height: 450px;overflow-x: auto;overflow-y: auto;">
                    <!-- Table -->
                    <table  class="table table-bordered " id="myTable02" >
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>产品SN</th>
                            <th>机种名称</th>
                            <th>工单号</th>
                            <th>内箱号</th>
                            <th>外箱号</th>
                            <th>操作员</th>
                            <th>作业时间</th>
                            <th>仓别</th>
                            <th>储位</th>
                        </tr>
                        </thead>
                        <tbody >
                        <c:forEach items="${wbox}" var="z" varStatus="w">
                            <tr>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                共<span class="badge" id="count">${length}</span>条数据




                <div id="outer">
                    <div class="alert alert-success"  id="model1">系统正在读取中,请稍后！</div>
                </div>


                <script type="text/javascript">
                    var oTable = document.getElementsByTagName("table")[2];
                    var tBodies = oTable.tBodies[0];
                    var inputText=document.getElementById("wboxnumber");

                    inputText.addEventListener("keyup",function(e){
                        show();
                        if(e.keyCode==13){
                            document.getElementById("wbox").value="";
                            var sn=document.getElementById("wboxnumber").value;
                            $.ajax({
                                url:"/cangku/selectWboxNumber",
                                type:"GET",
                                data: "sn="+sn,
                                success:function (result) {
                                    if (result.code==100)
                                    {   //alert("已查询到外箱号!");
                                        var wbox = result.extendInfo.wbox;
                                        if(document.getElementById("wbox").value!==wbox){
                                            document.getElementById("wbox").value=wbox;
                                            selectByWbox();
                                        }

                                    }
                                    else
                                    {
                                        document.getElementById("wboxnumber").value="";
                                    }
                                }

                            });

                        }

                    })

                    var num;
                    var model;
                    var moid;

                    //查询wbox的具体数量 自动触发
                    function selectByWbox(){
                        var wbox=document.getElementById("wbox").value;
                        $.ajax({
                            url: "/cangku/selectWboxSN",
                            type: "GET",
                            data: "wbox=" + wbox,
                            success: function (result) {
                                if (result.code==100){
                                    var selectzz = result.extendInfo.wbox;
                                    num=selectzz.length;
                                    document.getElementById("num").value=num;
                                    model=selectzz[0].model;
                                    moid=selectzz[0].moId;
                                }
                            }
                        })
                    }


                    //根据Wbox查询具体信息  按钮点击触发,
                    $("#select").click(function () {
                        var wbox=document.getElementById("wbox").value;
                        //alert(wbox);
                        $.ajax({
                            url:"/cangku/selectWboxSN",  // 从仓库表查询具体数据
                            type: "GET",
                            data: "wbox="+wbox,
                            success:function (result)
                            {
                                if (result.code==100){
                                    //清空表格
                                    var tb = document.getElementById('myTable02');
                                    var rowNum=tb.rows.length;
                                    for (i=1;i<rowNum;i++)
                                    {
                                        tb.deleteRow(i);
                                        rowNum=rowNum-1;
                                        i=i-1;
                                    }
                                    var selectzz = result.extendInfo.wbox;
                                    for (var i=0;i<selectzz.length;i++)
                                    {
                                        //alert("第"+(i+1)+"行");
                                        //1.创建行
                                        var tr = document.createElement("tr");
                                        //2.添加行到表格
                                        tBodies.appendChild(tr);
                                        //3.添加单元格
                                        tr.appendChild(createNode("td",i+1));
                                        tr.appendChild(createNode("td",selectzz[i].productionSN));
                                        tr.appendChild(createNode("td",selectzz[i].model));
                                        tr.appendChild(createNode("td", selectzz[i].moId));
                                        tr.appendChild(createNode("td", selectzz[i].nboxId));
                                        tr.appendChild(createNode("td",selectzz[i].wboxId));
                                        tr.appendChild(createNode("td", selectzz[i].operator));
                                        tr.appendChild(createNode("td", selectzz[i].time));
                                    }
                                    $("#count").text(selectzz.length);
                                }else {
                                    alert("查询信息错误！");
                                    var tb = document.getElementById('myTable02');
                                    var rowNum=tb.rows.length;
                                    for (i=1;i<rowNum;i++)
                                    {
                                        tb.deleteRow(i);
                                        rowNum=rowNum-1;
                                        i=i-1;
                                    }
                                    document.getElementById("wbox").value='';
                                }
                            }
                        });

                    });


                    //根据工单查询入库信息 (旧版本)   --保留   --11月3日新增根据工单号查询功能，将其反注释,并新增一个controller方法
                    $("#selectMo").click(function () {
                        var mo_id=$("#mo_id").val();
                        var date=null;
                        if (document.getElementById("check2").checked==false){
                            date="";
                        }else {
                            date=$("#pack_date2").val();
                        }
                        //清空表格
                        var tb = document.getElementById('myTable02');
                        var rowNum=tb.rows.length;
                        for ( var i=1;i<rowNum;i++)
                        {
                            tb.deleteRow(i);
                            rowNum=rowNum-1;
                            i=i-1;
                        }
                        $.ajax({
                            url:"/storage/selectsnByMo",   // 根据工单和时间从仓库表和入库表查询外箱数据，联结条件为外箱号
                            type:"GET",
                            data:{"mo_id":mo_id,"d":date},
                            success:function (result) {
                                if (result.code==100)
                                {
                                    var  storages= result.extendInfo.storages;
                                    for (var i = 0; i < storages.length; i++) {
                                        //1.创建行
                                        var tr = document.createElement("tr");
                                        //2.添加行到表格
                                        tBodies.appendChild(tr);
                                        //3.添加单元格
                                        tr.appendChild(createNode("td", i+1));
                                        tr.appendChild(createNode("td", storages[i].sn));
                                        tr.appendChild(createNode("td", storages[i].model));
                                        tr.appendChild(createNode("td", storages[i].mo));
                                        tr.appendChild(createNode("td", storages[i].nbox));
                                        tr.appendChild(createNode("td", storages[i].wbox));
                                        tr.appendChild(createNode("td", storages[i].operator));
                                        tr.appendChild(createNode("td", storages[i].time));
                                        tr.appendChild(createNode("td", storages[i].stock));
                                        tr.appendChild(createNode("td", storages[i].store_area));
                                    }
                                    $("#count").text(storages.length);
                                }
                                else {
                                    alert("未查询到相关信息,请重新输入！");
                                    $("#mo_id").val("");

                                }

                            }
                        })

                    });



                    //根据入库单查询入库信息
                    $("#selectAll").click(function () {
                        var mo=$("#mo").val();
                        var date=null;
                        if (document.getElementById("check").checked==false){
                            date="";
                        }else {
                            date=$("#pack_date").val();
                        }
                        //清空表格
                        var tb = document.getElementById('myTable02');
                        var rowNum=tb.rows.length;
                        for ( var i=1;i<rowNum;i++)
                        {
                            tb.deleteRow(i);
                            rowNum=rowNum-1;
                            i=i-1;
                        }
                        $.ajax({
                            url:"/storage/selectsnByStorageMo",
                            type:"GET",
                            data:{"mo":mo,"d":date},
                            success:function (result) {
                                if (result.code==100)
                                {
                                    var  storages= result.extendInfo.storages;
                                    for (var i = 0; i < storages.length; i++) {

                                        //1.创建行
                                        var tr = document.createElement("tr");
                                        //2.添加行到表格
                                        tBodies.appendChild(tr);
                                        //3.添加单元格
                                        tr.appendChild(createNode("td", i+1));
                                        tr.appendChild(createNode("td", storages[i].sn));
                                        tr.appendChild(createNode("td", storages[i].model));
                                        tr.appendChild(createNode("td", storages[i].mo));
                                        tr.appendChild(createNode("td", storages[i].nbox));
                                        tr.appendChild(createNode("td", storages[i].wbox));
                                        tr.appendChild(createNode("td", storages[i].operator));
                                        tr.appendChild(createNode("td", storages[i].time));
                                        tr.appendChild(createNode("td", storages[i].stock));
                                        tr.appendChild(createNode("td", storages[i].store_area));
                                    }

                                    $("#count").text(storages.length);
                                }
                                else {
                                    alert("未查询到相关信息,请重新输入！");
                                    $("#mo").val("");

                                }

                            }
                        })

                    });

                    function createNode(element,text) {
                        var td = document.createElement(element);
                        td.innerHTML = text;
                        return td;
                    }

                    //window.location.href="${pageContext.request.contextPath}/pack/selectWboxSN/"+wbox;

                    //保存入库信息 先查询是否已入库，后添加至数据库
                    $("#save").click(function () {
                        var selectType = $("#storageType option:selected").val();
                        alert(selectType);
                        if ("请选择阶段" !== selectType) {
                        document.getElementById("save").disabled = true;//禁用按钮
                        var wbox = document.getElementById("wbox").value;
                        var operator = "<%=session.getAttribute("name")%>";
                        var stock = document.getElementById("stock").value;  // 仓别
                        var store_area = document.getElementById("store_area").value; // 储位
                        var storageMo = document.getElementById("storageMo").value;
                        var erp = document.getElementById("erp").value;
                        var num = document.getElementById("num").value;
                        var storage = {
                            "wbox": wbox,
                            "operator": operator,
                            "num": num,
                            "store_area": store_area,
                            "mo": moid,
                            "model": model,
                            "stock": stock,
                            "storageMo": storageMo
                            ,
                            "erp": erp
                        };

                        $.ajax({
                            url: "/storage/selectWaiExistByStorage",
                            type: "GET",
                            data: "wbox=" + wbox,
                            success: function (result) {
                                if (result.code == 100) {
                                    $.ajax({
                                        url: "/storage/saveStorage",
                                        type: "GET",
                                        data: storage,
                                        success: function (result) {
                                            if (result.code == 100) {
                                                alert("添加成功");
                                                document.getElementById("save").disabled = false;//启用按钮
                                            } else {
                                                alert("添加失败");
                                                document.getElementById("save").disabled = false;//启用按钮
                                            }
                                        }
                                    });

                                } else {
                                    alert("该外箱数据已入库,请重新输入！");
                                    $("#wbox").val("");
                                    $("#wboxnumber").val("");
                                    $("#num").val("");
                                    $("#store_area").val("");
                                    $("#GK").val("");
                                    $("#madeby").val("");
                                    document.getElementById("save").disabled = false;//启用按钮
                                }
                            }
                        });
                    }
                        else{
                            alert("请选择阶段!!!");
                        }
                    });


                    /*

                                       function selectWbox()
                                       {    show();
                                           if (event.keyCode==13) {
                                               document.getElementById("wbox").value="";
                                               var sn=document.getElementById("wboxnumber").value;
                                               $.ajax({
                                                   url:"/pack/selectWboxNumber",
                                                   type:"GET",
                                                   data: "sn="+sn,
                                                   success:function (result) {
                                                      if (result.code==100)
                                                      {   //alert("已查询到外箱号!");
                                                          var wbox = result.extendInfo.wbox;
                                                          document.getElementById("wbox").value=wbox;

                                                      }
                                                      else
                                                      {
                                                          document.getElementById("wboxnumber").value="";
                                                      }
                                                   }

                                               });

                                           }



                                       }
                                       */

                    // 根据入库单来导 芯盛
                    $(".xitc").click(function () {
                        var mo=$("#mo").val();
                        var type = "storageMo";
                        var date=null;
                        if (document.getElementById("check").checked==false){
                            date="";
                        }else {
                            date=$("#pack_date").val();
                        }
                        $.ajax({
                            url:"/storage/selectsnByStorageMo",
                            type:"GET",
                            data:{"mo":mo,"d":date},
                            success:function(result) {
                                if(result.code==100)
                                {
                                    if (date==""){
                                        window.location.href="/storage/exportExcel/"+mo+"/"+type;
                                    }else {
                                        window.location.href="/storage/exportExcel/"+mo+"/"+date+"/"+type;
                                    }
                                }
                                else
                                {
                                    alert("该工单未查询到，请重新输入");
                                }
                            }
                        });
                    });
                    // 根据入库单来导 国科
                    $(".goke").click(function () {
                        var mo=$("#mo").val();
                        var type = "storageMo";
                        var date=null;
                        if (document.getElementById("check").checked==false){
                            date="";
                        }else {
                            date=$("#pack_date").val();
                        }
                        $.ajax({
                            url:"/storage/selectsnByStorageMo",
                            type:"GET",
                            data:{"mo":mo,"d":date},
                            success:function(result) {
                                if(result.code==100)
                                {
                                    if (date==""){
                                        window.location.href="/storage/gkexportExcel/"+mo+"/"+type;
                                    }else {
                                        window.location.href="/storage/gkexportExcel/"+mo+"/"+date+"/"+type;
                                    }
                                }
                                else
                                {
                                    alert("该工单未查询到，请重新输入");
                                }
                            }
                        });
                    });



                    //导出XIT报表,按照工单号来导出  --被反注释了，之前该功能被按入库单导出覆盖掉了
                    $(".xitc2").click(function () {
                        var mo_id=$("#mo_id").val();
                        var type = "mo";
                        var date=null;
                        if (document.getElementById("check2").checked==false){
                            date="";
                        }else {
                            date=$("#pack_date2").val();
                        }

                        $.ajax({
                            url:"/storage/selectAllByMo",
                            type:"GET",
                            data:{"mo_id":mo_id,"d":date},
                            success:function(result) {
                                if(result.code==100)
                                {
                                    if (date==""){
                                        window.location.href="/storage/exportExcel/"+mo_id+"/"+type;
                                    }else {
                                        window.location.href="/storage/exportExcel/"+mo_id+"/"+date+"/"+type;
                                    }
                                }
                                else
                                {
                                    alert("该工单未查询到，请重新输入");
                                }
                            }
                        });
                    });
                    //导出GK报表，按照工单号来导出
                    $(".goke2").click(function () {
                        var mo_id=$("#mo_id").val();
                        var type = "mo";

                        var date=null;
                        if (document.getElementById("check2").checked==false){
                            date="";
                        }else {
                            date=$("#pack_date2").val();
                        }
                        $.ajax({
                            url:"/storage/selectAllByMo",
                            type:"GET",
                            data:{"mo_id":mo_id,"d":date},
                            success:function(result) {
                                if(result.code==100)
                                {
                                    if (date==""){
                                        window.location.href="/storage/gkexportExcel/"+mo_id+"/"+type;
                                    }else {
                                        window.location.href="/storage/gkexportExcel/"+mo_id+"/"+date+"/"+type;
                                    }
                                }
                                else
                                {
                                    alert("该工单未查询到，请重新输入");
                                }
                            }
                        });
                    });


                    function settime()
                    {
                        setTimeout(tanchu,10100);
                    }
                    function show(){
                        document.getElementById("outer").style.display="block";
                        setTimeout(hide,10000);

                    }

                    function hide() {
                        document.getElementById("outer").style.display="none";

                    }

                    function tanchu() {

                        if(document.getElementById("wbox").value !="")
                        {
                            alert("读取成功!");
                            return;
                        } else
                        {
                            alert("读取失败,请重新扫描！");
                            window.location.reload();
                            return 0;
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
