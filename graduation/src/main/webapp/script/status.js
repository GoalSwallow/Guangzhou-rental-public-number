function  changeStatus() {
    debugger;
    var $id = document.getElementById("id").value;
    var $select = document.getElementById("ele");
    var $status = $select.value;
    var $text = $select.options[parseInt($status)-1].text;
    var $value = document.getElementById("demo");
    if($text == $value.innerText){
        alert("状态未改变！无法改变");
        return;
    }
    $.ajax({
        url: "https://2p38972u71.qicp.vip/admin/changeStatus?id=" + $id + "&status=" + $status,
        success: function (data) {
            debugger;
            if(data=="ok"){
                alert("状态修改成功！");
                if($status == "1"){
                    $value.innerText="发布";
                }else{
                    $value.innerText="废除";
                }
            }
            else{
                alert("状态修改失败！");
            }

        },
        error: function (e) {
            console.log(e);
        }
    });
}