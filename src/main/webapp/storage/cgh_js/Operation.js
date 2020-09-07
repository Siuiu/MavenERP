$(function () {
    $("tbody").delegate(".tablelink","click",function () {
        var op=$(this).text().trim();
        var indent=$(this).parent(".option").children("input").val();
        var state = $(this).parent().prev().text().trim();
        cancelOrder(indent,op,state);
    })
})


//取消订单
function cancelOrder(indent,op,state) {
    if (op=="确认回款"){
        var status = 3;
        $.ajax({
            url:'cancelOrder',
            type:'get',
            data:{"indent":indent,"status":status},
            success:function (data) {
                if (data=="success") {
                    alert(op+"成功！");
                    location.reload();
                }
            }
        })
    }
    if (op=="取消订单") {

        if (state == "未发货"){
            var status = 1;   // 状态(1未发货,2已发货,3已回款,4取消订单,5已退货)
        }else {
            var status = 6;   // 确认已退货
        }
            $.ajax({
                url:'cancelOrder',
                type:'get',
                data:{"indent":indent,"status":status},
                success:function (data) {
                    if (data=="success") {
                        alert(op+"成功！");
                        location.reload();
                    }
                }
            })


    }
    if (op=="发货"){
        $.ajax({
            url:'ship',
            type:'get',
            data:{"indent":indent},
            success:function (data) {
                if (data=="success") {
                    alert(op+"成功！");
                    location.reload();
                }else {
                    alert("库存数量不够");
                }
            }
        })
    }
    if (op=="确认已退货"){
        $.ajax({
            url:'cancelShopping',
            type:'get',
            data:{"indent":indent},
            success:function (data) {
                if (data=="success") {
                    alert(op + "成功！");
                    location.reload();
                }
            }
        })
    }
}