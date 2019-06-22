$(function(){
    $.ajax({
        url: "http://2p38972u71.qicp.vip/listing/getRentInfo",
        dataType: "json",
        success: function (data) {
            var $listingList = $(".resource ul");
            //遍历获取到的数据，创建房源信息
            $.each(data, function (index, ele) {
                var $item = createListingItem(ele);
                $listingList.append($item);
                var $img = document.getElementsByTagName("img");
                if(ele.url == null){
                    $img[$img.length-1].src="../../img/"+ "not.jpg";
                }else{
                    $img[$img.length-1].src="../../img/"+ele.url;
                }
            });
        },
        error: function (e) {
            console.log(e);
        }
    });
});

$(document).on('click', '.message', function(e){
    var $id = this.getElementsByClassName("id")[0].value;
    var $a = this.getElementsByTagName("a")[0];
    $a.addEventListener("click", function(){
        console.log("box1-click1-阻止默认操作和事件冒泡 被点击了");
        //event.preventDefault();
        event.stopPropagation();
    });
    var $url = $a.href.replace("ID", $id);
    $a.href = $url;
    $a.click();
});



function search() {
    debugger;
    var $location = $("#location").val();
    var $price = $("#price").val();
    var $houseType = $("#houseType").val();
    $.ajax({
        url: "https://2p38972u71.qicp.vip/listing/getRentInfo?location="+$location+"&price="+$price+"&houseType="+$houseType,
        dataType: "json",
        success: function (data) {
            debugger;
            //清空原来的ul列表
            $('ul li').remove();
            var $listingList = $(".resource ul");
            //遍历获取到的数据，创建房源信息
            $.each(data, function (index, ele) {
                var $item = createListingItem(ele);
                $listingList.append($item);
                var $img = document.getElementsByTagName("img");
                if(ele.url == null){
                    $img[$img.length-1].src="../../img/"+ "not.jpg";
                }else{
                    $img[$img.length-1].src="../../img/"+ele.url;
                }
            });
        },
        error: function (e) {
            console.log(e);
        }
    });
}
//6.定义一个方法，创建一条租房信息
function createListingItem(map){
    var $item = $("<li class=\"message\">\n" +
        "                       <a href=\"http://2p38972u71.qicp.vip/listing/getRentDetail?id=ID\" type=\"hidden\" />\n" +
        "                       <input type=\"hidden\" class=\"id\" value="+map.id+" />\n" +
        "                       <div class=\"img\">\n" +
        "                           <img src=\"\"/>\n" +
        "                       </div>\n" +
        "                       <div class=\"house\">\n" +
        "                           <div class=\"title\">"+map.title+"</div>\n" +
        "                           <div class= \"detail\">\n" +
        "                               <span class=\"houseType\">"+map.houseType+"</span>\n" +
        "                               <span class=\"rentalMethod\">"+map.rentalMethod+"</span>\n" +
        "                               <span class=\"price\">"+map.rent+"/月</span>\n" +
        "                            </div>\n" +
        "                            <div class=\"pos\">\n" +
        "                               <span class=\"local\">"+map.location+"</span>\n" +
        "                               <span class=\"communityName\"> "+map.communityName+"</span>\n" +
        "                               <span class=\"time\">"+map.time+"</span>\n" +
        "                            </div>\n" +
        "                       </div>\n" +
        "                   </li>");
    return $item;
}
