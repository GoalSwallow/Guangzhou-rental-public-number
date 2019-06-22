<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="../../script/jquery.min.js"></script>
		<title>Insert title here</title>
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

			ul li {
				list-style: none;
				width: 60%;
				height: 40px;
				border-bottom: 1px solid rgba(255, 0, 0, 0.1);
				box-sizing: border-box;
				user-select: none;
				margin-left: 20%;
			}

			label {
				margin-left: 8%;
				margin-right: 5%;
			}

			.ordinary {
				border: 0px;
				width: 60%;
				height: 100%;
			}

			li .radio {
				margin-left: 8%;
			}

			select {
				width: 60%;
				height: 100%;
				border: 0px;
			}

			.textarea {
				width: 60%;
				height: 6.25rem;
				vertical-align: middle;

			}

			textarea {
				width: 60%;
				height: 99%;
				border: 0px;
				background-color: aliceblue;
				vertical-align: middle;
			}

			button {
				width: 100%;
				height: 100%;
				background-color: lightpink;
				border: 0;
			}

			.send {
				width: 60%;
				height: 40px;
				margin-left: 20%;
				margin-top: 4%;
				margin-bottom: 4%;
			}

			#div {
				width: 60%;
				height: 6.25rem;
				margin-left: 20%;
				border: 1px solid rgba(255, 0, 0, 0.1);
				font-size: 14px;
				color: cadetblue;
				vertical-align: middle;
			}
			#content{
				width: 100%;
				height: 80%;
				position: relative;
			}
			.item{
				height: 5rem;
				width: 5rem;
				position: relative;
				display: inline-block;
				vertical-align: middle;
				margin-right: 0.625rem;
			}
			.img{
				height: 4.375rem;
				width: 4.375rem;
				position: relative;
				vertical-align: middle;
				margin-top: 5px;
			}
			.cancel{
				width: 14px;
				height: 14px;
				float: right;
				position: absolute;
			}
		</style>
	</head>
	<body>
		<script>
			function upload(fileDom){
				if($("#num").val() >= 7){
					alert("最多可上传7张图片，上传图片数额已满");
					return;
				}
				var divDom=document.getElementById('content');
				//判断是否支持FileReader
				var reader = null;
				if (window.FileReader) {
					reader = new FileReader();
				} else {
					alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
				}
				var file = fileDom.files[0];
				var imageType = /^image\//;
				//是否是图片
				if (!imageType.test(file.type)) {
					alert("所选文件不是图片类型，请重新选择！");
					return;
				}
				//读取完成
				reader.onload = function(e) {
					//展示图片
					var div = document.createElement('div');  
					div.setAttribute("class", "item");
					div.setAttribute("name", "item");
					var img = document.createElement('img'); 
					img.setAttribute("class", "img");
					img.src = e.target.result;
					//增加删除图片图标
					var cancel = document.createElement('img');
					cancel.setAttribute("src", "../../img/timg.jpg");
					cancel.setAttribute("class", "cancel");
					
					//添加到容器中
					div.appendChild(img);
					div.appendChild(cancel);
					divDom.appendChild(div);
					
					//将图片信息储存
					var fileDiv = document.getElementById("imgList");
					var input = document.createElement('input');
					input.setAttribute("type", "hidden");
					input.setAttribute("name", "file");
					input.setAttribute("class", "imgClass");
					input.setAttribute("value", e.target.result);
					fileDiv.appendChild(input);
					
					//清空文件内容，允许重复上传
					fileDom.value="";
					$('#num').val(parseInt($('#num').val())+1);
				};
				reader.readAsDataURL(file);
			}
			//监听删除图标的点击
			 $('body').on('click','.cancel',function(){
				debugger;
				$item = $(this).parents(".item");
				$img = $item.find(".img");
				$src = $img[0].src;
				$item.remove();
				
				var imgList = document.getElementsByClassName("imgClass");
				for(var i=0; i<imgList.length; i++){
					if($img[0].src == imgList[i].value){
						//删除
						$input = imgList[i]
						$input.remove();
						//一次循环只删除一次
						break;
					}
				}
			});
		</script>
		<h5 style="color: #ff5134; text-align: center;margin: 10px;">温馨提示：有照片的出租信息更容易被青睐哦！</h5>
		<input id="num" hidden type="text" value="0" />
		<div id="div">
			<input id="picture" type="file" onchange="upload(this)" multiple accept="image/png,image/gif,image/jpeg,image/gif" />
			<div id="content">
				<!-- <div class="item" name="item">
					<img src="" />
				</div> -->
			</div>
		</div> 
		<form id="form" action="${pageContext.request.contextPath}/listing/save" enctype="multipart/form-data" method="post">
			<div id="imgList">
				<!-- 将转码后的文件聚合在此处传到后台处理 -->
			</div>
			<ul>
				<li>
					<!-- 出租方式 -->
					<label>出租方式</label>
					<input name="rentalMethod" type="radio" value="0" checked/>整租
					<span class="radio"></span>
					<input name="rentalMethod" type="radio" value="1" />合租
				</li>
				<li>
					<!-- 小区名称 -->
					<label>小区名称</label>
					<input class="ordinary" name="communityName" required placeholder="请输入小区名称" />
				</li>
				<li>
					<!-- 户型 -->
					<label>户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</label>
					<select name="houseType" required>
						<option value="0" selected="selected">单间</option>
						<option value="1">一厅一室</option>
						<option value="2">一厅两室</option>
						<option value="3">一厅三室</option>
						<option value="4">两厅三室</option>
						<option value="5">其他</option>
					</select>
				</li>
				<li>
					<label>楼&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;层</label>
					<input class="ordinary" name="floor" type="text" required placeholder="请输入楼层" />
				</li>
				<li>
					<label>建筑面积</label>
					<input class="ordinary" name="area" type="text" required placeholder="请输入楼层" />
				</li>
				<li>
					<label>租&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金</label>
					<input class="ordinary" name="rent" type="text" required placeholder="请输入租金" />
				</li>
				<li>
					<label>配套设施</label>
					<input name="facility" type="checkbox" value="0" />床
					<input name="facility" type="checkbox" value="1" />宽带
					<input name="facility" type="checkbox" value="2" />电视
					<input name="facility" type="checkbox" value="3" />洗衣机
					<input name="facility" type="checkbox" value="4" />冰箱
					<input name="facility" type="checkbox" value="5" />暖气
					<input name="facility" type="checkbox" value="6" />空调
					<input name="facility" type="checkbox" value="7" />热水器
				</li>
				<li>
					<label>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题</label>
					<input class="ordinary" name="title" type="text" required placeholder="请输入标题" />
				</li>
				<li class="textarea">
					<label class="desLabel">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述</label>
					<textarea name="description" required placeholder="请输入描述"></textarea>
				</li>
				<li>
					<label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
					<input class="ordinary" name="userName" type="text" required placeholder="请输入姓名" />
				</li>
				<li>
					<label>手&nbsp;&nbsp;机&nbsp;&nbsp;号</label>
					<input class="ordinary" name="phone" type="text" required placeholder="请输入手机号" />
				</li>
			</ul>
			<div class="send">
				<button type="submit" onclick="send()">提交</button>
			</div>
		</form>
	</body>
</html>
