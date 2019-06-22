<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="../../script/jquery.min.js"></script>
    <script src="../../script/rent.js"></script>
    <link rel="stylesheet" href="../../css/rent.css" type="text/css">
</head>
<body>
    <div class="interface">
        <div class="top">广州租房</div>
        <div class="select">
            <label>位置</label>
            <select id="location">
                <option value="0">白云区</option>
                <option value="1">从化区</option>
                <option value="2">海珠区</option>
                <option value="3">花都区</option>
                <option value="4">黄浦区</option>
                <option value="5">荔湾区</option>
                <option value="6">南沙区</option>
                <option value="7">番禺区</option>
                <option value="8">天河区</option>
                <option value="9">越秀区</option>
                <option value="10">增城区</option>
                <option value="11" selected="selected"></option>
            </select>
            <label>租金</label>
            <select id="price" >
                <option value="0">1000以下</option>
                <option value="1">1000——2000</option>
                <option value="2">2000——3000</option>
                <option value="3">3000以上</option>
                <option value="4" selected="selected"></option>
            </select>
            <label>户型</label>
            <select id="houseType" >
                <option value="0">单间</option>
                <option value="1">一厅一室</option>
                <option value="2">一厅两室</option>
                <option value="3">一厅三室</option>
                <option value="4">两厅三室</option>
                <option value="5">其他</option>
                <option value="6" selected="selected"></option>
            </select>
            <button class="search" onclick="search()">查询</button>
        </div>
        <div class="resource">
            <ul class="ul">
                <%--创建房源信息列表--%>
            </ul>
        </div>
    </div>
</body>
</html>