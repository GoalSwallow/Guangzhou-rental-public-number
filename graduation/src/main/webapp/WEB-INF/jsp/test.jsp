<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../../script/jquery.min.js"></script>
    <style type="text/css">
         div,img{
             margin:0;
             padding:0;
         }
         img{
             float:left;
             height:100px;
             width:150px;
         }
         #div1{
             width:450px;
             height:100px;
             overflow: hidden;
             border:solid blue 2px;
         }
         #div2,#div3{
             float:left;
         }
         #div4{
             width:500%;/*这个属性很重要 让容器有足够的宽度实现滚动*/
             float:left;
         }
     </style>
</head>
<body>
<div id="div1">
         <div id="div4">
             <div id="div2">
                 <img src="../../img/nature.jpg" alt="图片1"/>
                 <img src="../../img/nature.jpg" alt="图片2"/>
                 <img src="../../img/nature.jpg" alt="图片3"/>
                 <img src="../../img/nature.jpg" alt="图片4"/>
             </div>
             <div id="div3"></div><!--这个容器是用来防止图片滚动时会出现空白的区域-->
         </div>
     </div>
</body>
<script type="text/javascript">
     window.onload=function(){
           var v1=document.getElementById('div1');
           var v2=document.getElementById('div2');
           var v3=document.getElementById('div3');

           v3.innerHTML= v2.innerHTML;//将v2容器里面的图片插入到v3容器里面  使其空白区域被遮住。
           function fun(){
             if(v1.scrollLeft>=600){
                 v1.scrollLeft=0;
             }else{
                 v1.scrollLeft++;
             }
         }

         var fun1=setInterval(fun,10);

         v1.onmouseover = function() {//鼠标经过时  清除定时器  停止图片的滚动
                 clearInterval(fun1)
             };
         v1.onmouseout = function() {//鼠标离开后  继续滚动图片
                 fun1 = setInterval(fun, 10)
             };
     }

 </script>
</html>
