$(function(){
    $.ajax({
        url: "https://2p38972u71.qicp.vip/listing/getRentInfo",
        dataType: "json",
        success: function (data) {
            var $listingList = $(".resource ul");
            //遍历获取到的数据，创建房源信息
            $.each(data, function (index, ele) {
                var $item = createListingItem(ele);
                $listingList.append($item);
                var $img = document.getElementsByTagName("img");
                $img[$img.length-1].src=ele.url;
                console.log(ele.url);
            });
        },
        error: function (e) {
            console.log(e);
        }
    });
});

$(document).on('click', '.message', function(e){
    var $location = $("#location").val();
    debugger;
});
//6.定义一个方法，创建一条租房信息
function createListingItem(map){
    var $item = $("<li class=\"message\">\n" +
        "                       <input type=\"hidden\" value="+map.id+"/>\n" +
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
        "                            <div class=\"position\">\n" +
        "                               <span>"+map.communityName+"</span>\n" +
        "                               <span class=\"time\">"+map.time+"</span>\n" +
        "                            </div>\n" +
        "                       </div>\n" +
        "                   </li>");
    return $item;
}
