$(function () {
    warehouseList();
    orderNumber();
    $("#sure").click(function () {
        addDelivery();
    })

    $.ajax({
        type:"get",
        url:"../../purchase/getUser",
        dataType:"json",
        success:function(d){
            $("#uName").val(d.uname);
        }
    });

    var time=new Date();
    var dateTime=time.getFullYear()+"-"+(time.getMonth()+1+"-")+time.getDate()+" "+time.getHours()+":"+time.getMinutes()+":"+time.getSeconds();
    $("#dateTime").val(dateTime);
})



//添加
function addDelivery() {
    var indent=$("#indent").val();
    var id=$("#warehouse option:selected").val();
    $.ajax({
        url:'addDelivery',
        type:'get',
        data:{"indent":indent,"id":id},
        dataType:'json',
        success:function (row) {
            if (row>0) {
                alert("添加成功！");
                location.reload();
            }else {
                alert("添加失败！");
            }
        }
    })
}


//仓库
function warehouseList() {
    $.ajax({
        url:'findWarehouse',
        type:'get',
        dataType:'json',
        success:function (d) {
            var op="<option value=''>请选择</option>";
            $.each(d,function (index,item) {
                op+="<option value='"+item.id+"'>"+item.c_name+"</option>";
            })
            $("#warehouse").append(op);
        }
    })
}

//订单号
function orderNumber() {
    $.ajax({
        url:'findDdh',
        type:'get',
        dataType:'json',
        success:function (d) {
            var li="";
            $.each(d,function (index,item) {
                li+="<li>"+item.orderId+"</li>"
            })
            $("#ddh ul").append(li);
        }
    })
}
