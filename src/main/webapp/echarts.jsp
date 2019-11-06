
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/frame/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/frame/echarts.js"></script>

</head>
<body>
    <div id="main" style="width: 600px;height: 400px;"></div>
    <script type="text/javascript">
        var chart = echarts.init(document.getElementById("main"));

        var option = {
            title : {
                text : '手机市场占有量'
            },
            tooltip : {},

            series : [{
                name : '销量',
                type : 'pie',
                data : [60, 50 ,46 , 33 ,20 ,15]
            }]
        };

        chart.setOption(option);
    </script>

</body>

</html>
