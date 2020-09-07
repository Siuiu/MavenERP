$(function () {
    // 页面加载时填入，不可编辑
    $.ajax({
        url:"../customerUser",
        type:"post",
        dataType:"json",
        success:function (data) {
            $(".roinput").eq(0).val(data.uName);
            $(".roinput").eq(1).val(data.date);
        }
    })

    // 省份的填入
    findProvince();
    // 区域的填入
    $("[name=province]").change(function(){
        var id = $(this).val();
        findCity(id);
    })

    // 定义全局变量
    var customname,sex,telephone,company,address,status,createTime,homeAddress,distract;

    // 表单验证
    formVal();

    // 姓名不可重复
    $(".dfinput").eq(0).blur(function(){
        customname = $(this).val().trim();
        var row = 1;
        if (customname!=""){
            $.ajax({
                url:"../repeatCustomName",
                type:"post",
                async:false,
                data:{"customname":customname},
                success:function (data) {
                    if (data==1){
                        row = 0;
                    }
                }
            })
        }

        if (row == 0){
            $(this).next().css("color","red");
            $(this).next().html("客户名重复");
        }else {
            $(this).next().css("color","#7f7f7f");
            $(this).next().html("必填，不能超过30个字符");
        }
    })

    // 点击确定
    $(".btn").eq(0).click(function(){
        var isNo = true;
        $(".forminfo input,select").each(function () {
            if($(this).val().trim()==""){
                alert("必填项填写内容不能为空");
                $(this).siblings().last().css("color","red");
                isNo = false;
                return isNo;
            }
            if($(this).siblings().last().attr("style")==("color: red;")){
                isNo = false;
                return isNo;
            }
        })

        // 判断校验是否成功
        if(isNo){

            // 获取填写的参数
            customname = $(".dfinput").eq(0).val().trim();
            sex = $("[name=sex]:checked").val();
            company = $(".dfinput").eq(1).val().trim();
            telephone = $(".dfinput").eq(2).val().trim();
            var province = $(".dfselect").eq(0).find("option:selected").text();
            var city = $(".dfselect").eq(1).val();
            homeAddress = $(".dfinput").eq(3).val().trim();
            address = province + city;
            status = $(".dfselect").eq(2).val();
            createTime = $(".roinput").eq(1).val();
            distract = $(".textinput").val();
            var data = {"customname":customname,"sex":sex,"company":company,
                "telephone":telephone,"homeAddress":homeAddress,"address":address,
                "distract":distract,"status":status,"createtime":createTime};
            $.ajax({
                url:"../customAdd",
                data:data,
                type: "post",
                dataType: "text",
                success:function(data){
                    if (data==1){
                        location.href="customerList.html";
                    } else{
                        location.href="#";
                    }
                }
            })
        }
    })
})

