$(function () {
    var ck_indent = getParams("ck_indent");
    $(".tablelist tr:not(:first)").remove();
    var tr="";
    var li="";
    $.ajax({
        type:"post",
        url:"deliveryViewOne",
        dataType:"json",
        data:{"ck_indent":ck_indent},
        success:function (data) {
            $("cite").eq(0).html(data.indent);
            $("cite").eq(1).html("<a href=\"../../market/customer/customerView.html?customId="+data.order.custom.customId+"\" title=\"点击查看客户详细信息\" class=\"tablelink\">"+data.order.custom.customname+"</a>");
            $("cite").eq(2).html(data.order.custom.telephone);
            $("cite").eq(3).html(data.order.orderTime);
            $("cite").eq(4).html("￥"+data.order.orderMoney);
            $("cite").eq(5).html(data.order.users.uname);
            if (data.order.status=="0"){
                $("cite").eq(6).html("未审核");
            } else if (data.order.status=="1"){
                $("cite").eq(6).html("审核中");
            } else if (data.order.status=="2"){
                $("cite").eq(6).html("审核通过");
            } else if (data.order.status=="3"){
                $("cite").eq(6).html("审核不通过");
            }
            $("cite").eq(7).html(data.order.opinion);
            $("cite").eq(8).html(data.order.s_users.uname);
            $("cite").eq(9).html(data.order.checTime);
            $("cite").eq(10).html("<a href=\"../storage/storageView.html?id="+data.warehouse.id+"\" title=\"点击查看客户详细信息\" class=\"tablelink\">"+data.warehouse.c_name+"</a>");
            $("cite").eq(11).html(data.c_date);
            $("cite").eq(12).html(data.users.uname);
            if (data.status=="1"){
                $("cite").eq(13).html("未发货");
            } else if (data.order.status=="2"){
                $("cite").eq(13).html("已发货");
            } else if (data.order.status=="3"){
                $("cite").eq(13).html("已回款");
            } else if (data.order.status=="4"){
                $("cite").eq(13).html("取消订单");
            }else{
                $("cite").eq(13).html("已退货");
            }

            $.each(data.order.orderDetails,function (index,detailsList) {
                tr += "<tr>" +
                    "   <td>" + (index + 1) + "</td>" +
                    "   <td>" + detailsList.product.productType.brand.name + "</td>" +
                    "   <td>" + detailsList.product.productType.name + "</td>" +
                    "   <td>" + detailsList.product.name + "</td>" +
                    "   <td>" + detailsList.purchase_num + "</td>" +
                    "   <td>" + detailsList.product.unit + "</td>" +
                    "   <td>￥" + detailsList.product.price + "</td>" +
                    "   <td>￥" + detailsList.prototal + "</td>" +
                    " </tr>";
            })
            $(".tablelist tbody").append(tr);
            $(".tablelist tbody tr:odd").addClass('odd');
        }
    })
})