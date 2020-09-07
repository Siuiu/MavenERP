$(function () {
    // 获取页面传来的订单编号
    var orderId = getParams("orderId");
    $.ajax({
        url:"../viewOrder",
        data:{"orderId":orderId},
        type:"post",
        dataType:"json",
        success:function(data){

            $("cite").eq(0).html(data.orderId);
            $("cite").eq(1).html("<a href='../../market/customer/customerView.html?customId="+data.custom.customId+"' title=\"点击查看客户详细信息\" class=\"tablelink\">"+data.custom.customname+"</a>");
            $("cite").eq(2).html(data.custom.telephone);
            $("cite").eq(3).html(data.orderTime);
            $("cite").eq(4).html("￥"+data.orderMoney);
            $("cite").eq(5).html(data.custom.users2.uname);
            if (data.status=="0"){
                $("cite").eq(6).html("未审核");
            } else if (data.status=="1"){
                $("cite").eq(6).html("审核中");
            } else if (data.status=="2"){
                $("cite").eq(6).html("审核通过");
            } else if (data.status=="3"){
                $("cite").eq(6).html("审核不通过");
            }
            if (data.opinion!=null){
                $("cite").eq(7).html(data.opinion);
            } else{
                $("cite").eq(7).html("");
            }
            if (data.custom.users1!=null) {
                $("cite").eq(8).html(data.custom.users1.uname);
                $("cite").eq(9).html(data.checTime);
            }else{
                $("cite").eq(8).html("");
                $("cite").eq(9).html("");
            }

            $("tbody").empty();
            var tr = "";
            $.each(data.orderDetails,function (index,item) {
                tr += "<tr>";
                tr += "<td>"+(index+1)+"</td>";
                tr += "<td>"+item.product.productType.brand.name+"</td>";
                tr += "<td>"+item.product.productType.name+"</td>";
                tr += "<td>"+item.product.name+"</td>";
                tr += "<td>"+item.purchase_num+"</td>";
                tr += "<td>台</td>";
                tr += "<td>"+item.proprice+"</td>";
                tr += "<td>"+item.prototal+"</td>";
                tr += "</tr>";
            })
            $("tbody").append(tr);
        }
    })
})