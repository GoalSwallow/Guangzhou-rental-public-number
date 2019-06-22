<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>帮助页面</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        html,
        body {
            width: 100%;
            height: 100%;
            font-size: 18px;
            font-family: "微软雅黑";
            opacity: 0.9;
        }
        .introduction{
            width: 60%;
            height: 100%;
            margin: 0 auto;
        }
        .listing{
            width: 100%;
            height: 500px;
        }
        .rent{
            width: 100%;
            height: 340px;
        }
        img{
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
    <div class="introduction">
        <div class="listing">
            点击公众号的出租菜单，将会出现如下页面：
            <img src="../../img/listing.jpg">
        </div>
        <div class="rent">
            点击公众号的租房菜单，将会出现房源信息列表页面：
            <img src="../../img/rent.jpg">
        </div>
    </div>
</body>
</html>
