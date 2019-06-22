<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>详情管理后台</title>
    <script src="../../script/jquery.min.js"></script>
    <script src="../../script/status.js"></script>
    <link rel="stylesheet" href="../../css/status.css" type="text/css">
</head>
<body>
<div class="content">
    <input id="id" type="hidden" value="${listing.id}">
    <input id="openid" type="hidden" value="${listing.openid}">
    <div class="title">${listing.title}</div>
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
        <li>租金：${listing.rent}</li>
        <li>描述：${listing.description}</li>
        <li>出租人：${listing.userName}</li>
        <li>手机号：${listing.phone}</li>
        <li>
            <span class="status">状态：<i id="demo">${listing.status}</i></span>
            <div class="select">
                <select id="ele">
                    <option value="1" selected="selected">发布</option>
                    <option value="2">废除</option>
                </select>
            </div>
            <span class="second">更改状态</span>
        </li>
        <button class="sure" onclick="changeStatus()">确定更改</button>
    </ul>
</div>
</body>
</html>
