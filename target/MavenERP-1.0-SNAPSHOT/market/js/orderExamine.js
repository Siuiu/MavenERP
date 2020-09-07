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

    // 点击审核时
    $(".forminfo:eq(1)").delegate(".btn:eq(0)","click",function () {
        var opinion= $(".textinput").val().trim();
        var status = $(".dfselect").val();
        var orderId = $("cite").eq(0).text();
        if (opinion==""||opinion==null||status=="-1"){
            alert("请按要求填写审核内容");
        }else {
            $.ajax({
                url:"../auditTrueOrder",
                data:{"orderId":orderId,"opinion":opinion,"status":status},
                type:"post",
                success:function (data) {
                    if (data==1){
                        alert("审核成功");
                        location.href="orderExamineList.html";
                    }else{
                        alert("审核不成功");
                        location.href="orderExamine.html";
                    }
                }
            })
        }
    })
})