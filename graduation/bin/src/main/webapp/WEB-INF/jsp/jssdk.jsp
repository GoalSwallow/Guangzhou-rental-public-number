<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../script/jquery.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		var appId = $("#appId").val();
		var nonceStr = $("#nonceStr").val();
		var timestamp = $("#timestamp").val();
		var signature = $("#signature").val();
		wx.config({
			debug : false,
			appId : appId,
			timestamp : timestamp,
			nonceStr : nonceStr,
			signature : signature,
			jsApiList : [ 'chooseImage', 'scanQRCode', 'getLocation' ]
		});

		//选择照片
		$("#sao").on("click", function() {
			wx.chooseImage({
				count : 9, // 默认9
				sizeType : [ 'original', 'compressed' ], // 可以指定是原图还是压缩图，默认二者都有
				sourceType : [ 'album', 'camera' ], // 可以指定来源是相册还是相机，默认二者都有
				success : function(res) {
					var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
					alert(localIds);
				},
				error : function(text) {
					alert("delete");
					console.log(text);
				}
			});

		});
		
		$("#share").on("click", function() {
			wx.scanQRCode({
				needResult : 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
				scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有
				success : function(res) {
					var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
					alert(res);
				}
			});
		});
		
		$("#location").on("click", function() {
			wx.getLocation({
				type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
				success: function (res) {
					debugger;
					var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
					var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
					var speed = res.speed; // 速度，以米/每秒计
					var accuracy = res.accuracy; // 位置精度
					alert(res.latitude);
				}
			});
		});
	});

	wx.ready(function() {
		// 1 判断当前版本是否支持指定 JS 接口，支持批量判断
		alert("1230k");
	});

	wx.error(function(res) {
		console.log("error");
		alert("///");
	});
</script>
</head>
<body>
	<input type="button" id="sao" value="选择照片" />
	<input type="button" id="share" value="扫描二维码" />
	<input type="button" id="location" value="我的位置" />
	<input id="appId" type="hidden" value="${sign.appId }" />
	<input id="url" type="hidden" value="${sign.url}" />
	<input id="tk" type="hidden" value="${sign.jsapi_ticket }" />
	<input id="nonceStr" type="hidden" value="${sign.nonceStr }" />
	<input id="timestamp" type="hidden" value="${sign.timestamp }" />
	<input id="signature" type="hidden" value="${sign.signature }" />
</body>
</html>