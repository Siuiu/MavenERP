$(function(){
    // 页面加载时填入，不可编辑
    $.ajax({
        url:"../customerUser",
        type:"post",
        dataType:"json",
        success:function (data) {
            $(".roinput").eq(2).val(data.uName);
            $(".roinput").eq(1).val(data.date);
        }
    })

    // 订单编号填入
    $(".roinput").eq(0).val(getOrderId());

    // 判断填入的客户姓名
    judgeCustomName();

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
            $("#brandOne").html(brand);
        }
    })

    var index = 1;
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
                url:"../addOrder",
                type:"post",
                data:JSON.stringify(data),
                contentType : 'application/json;charset=utf-8',
                dataType:"json",
                success:function (data) {
                    if(data!=0){
                        alert("添加订单成功")
                        location.href = "orderList.html";
                    }else {
                        alert("添加订单失败")
                        location.href = "orderAdd.html";
                    }
                }
            })
        }

    })

})

// 获取订单编号
function getOrderId(){
    var num=parseInt(Math.random()*10000)+"";
    if(parseInt(num.length)<5){
        var index=4-parseInt(num.length);
        for(var i=0;i<index;i++){
            num="0"+num;
        }
    }
    var date=new Date()
    var month=date.getMonth()+1;
    month=month+"";
    if(month.length==1)month="0"+month;
    num="DJ"+date.getFullYear()+month+date.getDate()+num;
    return num;
}

// 获取表中信息
function orderVal(){
    var data={};
    var orderId = $(".roinput").eq(0).val();
    data.orderId = orderId;
    var custom={};
    custom.customname = $(".dfinput").val();
    data.orderTime = $(".roinput").eq(1).val();
    var users2 = {};
    users2.uname = $(".roinput").eq(2).val();
    custom.users2 = users2;
    data.custom = custom;
    data.status="0";
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


