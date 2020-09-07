var index;
$(function(){
    // 获取页面传值
    var orderId = getParams("orderId");
    // 判断填入的客户姓名
    judgeCustomName();
    // 订单详情的条数
    index = 0;
    // 页面加载时填入，获取当前时间
    $.ajax({
        url:"../customerUser",
        type:"post",
        dataType:"json",
        async:false,
        success:function (data) {
            $(".roinput").eq(1).val(data.date);
        }
    })
    // 获取订单详情
    $.ajax({
        url: "../viewOrder",
        data: {"orderId": orderId},
        type: "post",
        dataType: "json",
        async:false,
        success:function (data) {
            $(".roinput").eq(0).val(data.orderId);
            $(".dfinput").val(data.custom.customname);
            $(".roinput").eq(2).val(data.custom.users2.uname);
            if (data.status=="0"){
                $(".roinput").eq(3).val("未审核");
            } else if (data.status=="1"){
                $(".roinput").eq(3).val("审核中");
            } else if (data.status=="2"){
                $(".roinput").eq(3).val("审核通过");
            } else if (data.status=="3"){
                $(".roinput").eq(3).val("审核不通过");
            }
            $(".roinput").eq(4).val(data.orderMoney);

            $("tbody").empty();
            var tr = "";
            $.each(data.orderDetails,function (indexs,item) {
                index += 1;
                var brandId = item.product.productType.brand.id;
                var productTypeId = item.product.productType.id;
                var productId = item.product.id;
                tr += "<tr>";
                    tr += "<td>"+(indexs + 1)+"</td>";
                    tr += "<td class='brandOne'>";
                        tr += "<select><option value='0'>请选择</option></select>";
                    tr += "</td>";
                    tr += "<td class='typeOne'><select><option value='0'>请选择</option></select></td>";
                    tr += "<td class='productOne'><select><option value='0'>请选择</option></select></td>";
                    tr += "<td><input type=\"text\" class='num' value='"+item.purchase_num+"'/></td>" +
                        "<td>台</td>" +
                        "<td><input type=\"text\" class='price' value='"+item.proprice+"'/></td>" +
                        "<td><input type=\"text\" class='money' value='"+item.prototal+"' readonly='readonly'/></td>" +
                    "</tr>";


                var brands="";
                $.ajax({
                    url:"../../Brand/seBrandAllInfo",
                    type:"get",
                    dataType:"json",

                    success:function(d){
                        brands+="<select onchange='seType(this)'>";
                        brands+="<option value='0'>请选择</option>"
                        $.each(d,function (index,item) {
                            if(item.id==brandId){
                                brands+="<option value='"+item.id+"' selected='selected'>"+item.name+"</option>";
                            }else{
                                brands+="<option value='"+item.id+"'>"+item.name+"</option>";
                            }
                        })
                        brand+="</select>";
                        $(".brandOne").eq(indexs).html(brands);
                    }
                })

                $.ajax({
                    url:"../../productType/bidFindAll",
                    type:"get",
                    data:{"bid":brandId},
                    dataType:"json",

                    success:function(d){
                        var html="<select onchange=\"seProduct(this)\"><option value='0'>请选择</option>";
                        $.each(d,function(index,type) {
                            if (type.id==productTypeId) {
                                html+="<option value='"+type.id+"' selected='selected'>"+type.name+"</option>";
                            }else{
                                html+="<option value='"+type.id+"'>"+type.name+"</option>";
                            }
                        })
                        html+="</select>";
                        $(".typeOne").eq(indexs).html(html);
                    }
                })

                $.ajax({
                    url:"../../product/tidFindAll",
                    type:"get",
                    data:{"tid":productTypeId},
                    dataType:"json",
                    success:function(d){
                        var html="<select class='productId'><option value='0'>请选择</option>";
                        $.each(d,function(index,product) {
                            if (product.id==productId) {
                                html+="<option value='"+product.id+"' selected='selected'>"+product.name+"</option>";
                            }else{
                                html+="<option value='"+product.id+"'>"+product.name+"</option>";
                            }
                        })
                        html+="</select>";
                        $(".productOne").eq(indexs).html(html);
                    }
                })

            })
            $("tbody").append(tr);

        }
    })

    // 获取品牌、类型及型号
    var brand="";
    $.ajax({
        url:"../../Brand/seBrandAllInfo",
        type:"get",
        dataType:"json",
        success:function(d){
            brand+="<select onchange='seType(this)'>";
            brand+="<option value='0'>请选择</option>"
            $.each(d,function (index,item) {
                brand+="<option value='"+item.id+"'>"+item.name+"</option>";
            })
            brand+="</select>";
            //$(".brandOne").html(brand);
        }
    })

    // 点击新增时
    $(".smallbtn").eq(0).click(function () {
        index+=1;
        var detailsList="";
        detailsList += "<tr><td>"+index+"</td>";
        detailsList += "<td>"+brand+"</td>";
        detailsList += "<td><select onchange='seProduct(this)'><option value='0'>请选择</option></select></td>";
        detailsList += "<td><select><option value='0'>请选择</option></select></td>";
        detailsList += "<td><input type=\"text\" class='num'/></td>" +
            "<td>台</td>" +
            "<td><input type=\"text\" class='price'/></td>" +
            "<td><input type=\"text\" value='0' class='money' readonly='readonly'/></td>" +
            "</tr>";
        $("tbody").append(detailsList);
    })

    // 点击删除时
    $(".smallbtn").eq(1).click(function () {
        if(index<=1){
            alert("不可再删除");
        }else{
            $("tr:last").remove();
            index--;
        }
    })

    // 计算金额
    $("tbody").delegate(".price","change",function () {
        $(this).parent().next().children(":first").val("");
        var money;
        var price = $(this).val();
        var num = $(this).parent().prev().prev().children(":first").val();
        if(price>=1 && num>=1){
            money = (price*num);
            $(this).parent().next().children(":first").val(money);
        }

    })
    $("tbody").delegate(".num","change",function () {
        $(this).parent().next().next().next().children(":first").val("");
        var money;
        var num = $(this).val();
        var price = $(this).parent().next().next().children(":first").val();
        if(price>=1 && num>=1){
            money = (price*num);
            $(this).parent().next().next().next().children(":first").val(money);
        }
    })

    // 填入总金额
    $("body").click(function(){
        $(".roinput").eq(4).val("");
        var orderMoney = 0;
        $(".money").each(function () {
            var money = parseInt($(this).val());
            orderMoney += money;
        })
        $(".roinput").eq(4).val(orderMoney);
    })

    // 点击保存时
    $(".smallbtn").eq(2).click(function () {
        var isNo = true;
        $(".formbody input,select").each(function () {
            if ($(this).val() == "" || $(this).val() == "0") {
                alert("必填项填写内容不能为空");
                isNo = false;
                return isNo;
            }
        })
        if($(".dfinput").siblings().last().attr("style")==("color: red;")){
            alert("请正确填写客户姓名");
            isNo = false;
            return isNo;
        }

        if(isNo){
            var data = orderVal();
            $.ajax({
                url:"../updateOrder",
                type:"post",
                data:JSON.stringify(data),
                contentType : 'application/json;charset=utf-8',
                dataType:"json",
                success:function (data) {
                    if(data!=0){
                        alert("修改订单成功")
                        location.href = "orderList.html";
                    }else {
                        alert("修改订单失败")
                        location.href = "orderUpdate.html";
                    }
                }
            })
        }
    })

})

// 获取表中信息
function orderVal(){
    var data={};
    var orderId = $(".roinput").eq(0).val();
    data.orderId = orderId;
    var custom={};
    custom.customname = $(".dfinput").val();
    data.orderTime = $(".roinput").eq(1).val();
    data.custom = custom;
    data.orderMoney = $(".roinput").eq(4).val();
    var orderDetails = new Array();
    $(".money").each(function (index,item) {
        var prototal = $ (".money").eq(index).val();
        var purchase_num = $(".num").eq(index).val();
        var product_id = $(".productId").eq(index).val();
        var proprice = $(".price").eq(index).val();
        orderDetails.push({"order_id":orderId,"product_id":product_id,"purchase_num":purchase_num,"proprice":proprice,"prototal":prototal});
    })
    data.orderDetails = orderDetails;
    return data;
}