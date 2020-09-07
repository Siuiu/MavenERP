// 查找省份
function findProvince(){
    $.ajax({
        url:"/MavenERP/findProvince",
        type:"post",
        dataType:"json",
        async:false,
        success:function (data) {
            $("[name=province]>option").remove();
            var option = "<option value=\"\">请选择省份</option>";
            $.each(data,function(index,item){
                option += "<option name='"+item.p_name+"' value='"+item.id+"'>"+item.p_name+"</option>";
            })
            $("[name=province]").append(option);
        }
    })
}

// 查找区域
function findCity(id) {
    $.ajax({
        url:"/MavenERP/findCity",
        data:{"id":id},
        type:"post",
        dataType:"json",
        success:function(data){
            $("[name=city]>option").remove();
            var option = "<option value=\"\">请选择城市</option>";
            $.each(data, function (index, item) {
                option += "<option value='"+item.c_name+"'>" + item.c_name + "</option>";
            })
            $("[name=city]").append(option);
        }

    })

}

// 登录判断职位
function findPosition(){
    var position;
    $.ajax({
        url:"../userPosition",
        type:"post",
        async:false,
        dataType:"text",
        success:function (data) {
            if (data==0){
                position="组员";
            } else if (data==2) {
                position="董事长";
            }else {
                position="经理";
            }
        }
    })
    return position;
}

// 判断填入的客户姓名
function judgeCustomName(){
    $(".dfinput").blur(function () {
        var customname = $(this).val().trim();
        $.ajax({
            url:"../judgeCustomName",
            data:{"customName":customname},
            type: "post",
            async:false,
            success:function (data) {
                if (data.customname!=null){
                    $(".dfinput").siblings().last().html("该客户名存在,可用");
                    $(".dfinput").siblings().last().css("color","green");
                } else{
                    if ($(".dfinput").val().trim()!=""){
                        $(".dfinput").siblings().last().html("此组员下不存在该客户名或者该客户被禁用");
                    }else{
                        $(".dfinput").siblings().last().html("不能为空");
                    }
                    $(".dfinput").siblings().last().css("color","red");
                }
            }
        })
    })

}

// 查询品牌下的类型
function seType(op) {
    var mythis=$(op);
    var bid=mythis.val();
    if(bid==0){
        mythis.parent().next().html("<select onchange=\"seProduct(this)\"><option value='0'>请选择</option></select>");
        mythis.parent().next().next().html("<select><option value='0'>请选择</option></select>");
    }
    $.ajax({
        url:"../../productType/bidFindAll",
        type:"get",
        data:{"bid":bid},
        dataType:"json",
        success:function(d){
            var html="<select onchange=\"seProduct(this)\"><option value='0'>请选择</option>";
            $.each(d,function(index,type) {
                html+="<option value='"+type.id+"'>"+type.name+"</option>";
            })
            html+="</select>";
            mythis.parent().next().html(html);
        }
    })
}

//查询类型下的商品
function seProduct(op) {
    var mythis=$(op);
    var tid=mythis.val();
    if(tid==0){
        mythis.parent().next().html("<select><option value='0'>请选择</option></select>")
    }
    $.ajax({
        url:"../../product/tidFindAll",
        type:"get",
        data:{"tid":tid},
        dataType:"json",
        success:function(d){
            var html="<select class='productId'><option value='0'>请选择</option>";
            $.each(d,function(index,product) {
                html+="<option value='"+product.id+"'>"+product.name+"</option>";
            })
            html+="</select>";
            mythis.parent().next().html(html);
        }
    })
}

// 表单验证
function formVal(){
    var customname,company,telephone,homeAddress;
    // 1.校验姓名不能超过30个字符
            // 可用each遍历验证1-4？
    $(".dfinput").eq(0).blur(function(){
        customname = $(this).val().trim();
        if (customname.length>30){
            $(this).next().css("color","red");
        }else {
            $(this).next().css("color","#7f7f7f");
        }
    })

    // 2.校验所属公司不能超过30个字符
    $(".dfinput").eq(1).blur(function(){
        company = $(this).val().trim();
        if (company.length>30){
            $(this).next().css("color","red");
        }else {
            $(this).next().css("color","#7f7f7f");
        }
    })
    // 3.校验联系方式
    $(".dfinput").eq(2).blur(function(){
        telephone = $(this).val().trim();
        isNo = isMobileNo(telephone);
        if (!isNo){
            $(this).next().css("color","red");
        } else {
            $(this).next().css("color","#7f7f7f");
        }
    })
    // 4.校验联系地址
    $(".dfinput").eq(3).blur(function(){
        homeAddress = $(this).val().trim();
        if (homeAddress.length>130){
            $(this).next().css("color","red");
        }else {
            $(this).next().css("color","#7f7f7f");
        }
    })
    // 5.校验所属区域
    $(".dfselect").each(function(){
        $(this).blur(function(){
            var selectname = $(this).val();
            if(selectname == "" || selectname == null){
                $(this).siblings().last().css("color","red");
            }else {
                $(this).siblings().last().css("color","#7f7f7f");
            }
        })
    })

}
// 验证手机号
function isMobileNo(mobile) {
    var pattern = /^1[34578]\d{9}$/;
    return pattern.test(mobile);
}

// 页面传参
function getParams(key) {
    var keyList = [];
    var valueList = [];
    var hrefStr = decodeURIComponent(window.location.href);
    var strArray = hrefStr.split("?");
    var paramStr = strArray[1];
    if((paramStr != null) && (paramStr != null)) {
        var paramArray = paramStr.split("&");
        for(var i = 0; i <
        paramArray.length; i++) {
            var param = paramArray[i];
            valueList.push(param.substr(param.indexOf("=") + 1));
            keyList.push(param.substr(0, param.indexOf("=")));
        }
        for(var j = 0; j < keyList.length; j++) {
            if(key == keyList[j]) {
                return valueList[j];
            }
        }
    }
}

