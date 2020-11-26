<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>MES看板与ESD</title>

    <script type="text/javascript">
        var newWindow;
        var two;
        function Open(){
            newWindow = window.open('http://192.168.200.76:8050/check/selectcount');
            var time = setTimeout("Close();",120000);
        }
        function Close() {
            newWindow.close();
            var time = setTimeout("Open2();",100);
        }
        function Open2(){
            two = window.open('http://192.168.211.10:8080');
            var time2 = setTimeout("Close2();",120000);
        }
        function Close2() {
            two.close();
            var time2 = setTimeout("Open();",100);
        }
    </script>

</head>
<body onload="Open()">

</body>
</html>