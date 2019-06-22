<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>详情</title>
    <script src="../../script/jquery.min.js"></script>
    <script src="../../script/detail.js"></script>
    <link rel="stylesheet" href="../../css/detail.css" type="text/css">
</head>
<body>
<div class="content">
    <input type="hidden" value="${listing.openid}">
    <div class="title">${listing.title}</div>
    <%--<div id="div1">
         <div id="div4">
             <div id="div2">
                 <c:forEach items="${files}" var="file">
                     <img src='../../img/${file.url}'>
                 </c:forEach>
             </div>
             <div id="div3"></div><!--这个容器是用来防止图片滚动时会出现空白的区域-->
         </div>
     </div>--%>
    <div id="div1">
        <c:forEach items="${files}" var="file">
            <img src='../../img/${file.url}'>
        </c:forEach>
    </div>
    <ul class="condition">
        <li>出租方式: ${listing.rentalMethod}<br></li>
        <li>所属区域：${listing.location}</li>
        <li>小区名称：${listing.communityName}</li>
        <li>户型：${listing.houseType}</li>
        <li>楼层：${listing.floor}</li>
        <li>建筑面积：${listing.area}</li>
        <li>配套设施：
            <c:forEach items="${facilities}" var="facility">
                ${facility.facility}
            </c:forEach>
        </li>
        <li>租金：${listing.rent}/月</li>
        <li>描述：${listing.description}</li>
        <li>出租人：${listing.userName}</li>
        <li>手机号：${listing.phone}</li>
    </ul>
    <a class="show" href="#">已出租</a>
</div>
</body>
</html>
