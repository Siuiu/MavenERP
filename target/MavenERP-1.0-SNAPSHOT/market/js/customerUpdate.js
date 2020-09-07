$(function () {
    // 加载时获取数据
    var customId = getParams("customId");
    $.ajax({
        url:"../customerViews",
        type:"post",
        data:{"customId":customId},
        dataType:"json",
        success:function(data){
            $(".dfinput").eq(0).val(data.customname);
            $("[name=sex][value="+data.sex+"]").attr("checked",true);
            $(".dfinput").eq(1).val(data.company);
            $(".dfinput").eq(2).val(data.telephone);
            $(".dfinput").eq(3).val(data.homeAddress);
            $(".textinput").val(data.distract);
            $(".roinput").eq(0).val(data.users1.uname);
            $(".roinput").eq(1).val(data.createTime);
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
    var customname,sex,telephone,company,address,status,homeAddress,distract;

    // 表单验证
    formVal();

    // 点击确定
    $(".btn").eq(0).click(function(){
        var isNo = true;
        $(".forminfo input,select").each(function () {
            if($(this).val()==""){
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
            distract = $(".textinput").val();
            var data = {"customId":customId,"customname":customname,"sex":sex,"company":company,
                "telephone":telephone,"homeAddress":homeAddress,"address":address,
                "distract":distract,"status":status};
            $.ajax({
                url:"../customUpdate",
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