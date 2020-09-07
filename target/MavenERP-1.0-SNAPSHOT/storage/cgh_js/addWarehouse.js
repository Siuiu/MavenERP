var judge;
$(function () {
    provincelist();
    $("#province").change(function () {
        var id=$(this).val();
        citylist(id);
    });
    findPerson();
    $("#sname").blur(function () {
        formVerify();
    })

    $("#sure").click(function () {
        judge = formVerify();
        if (judge) {
            addWarehouse();
        }else {
            alert("仓库名称重复，请修改后再提交！");
        }
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
function addWarehouse() {

    var sname=$("#sname").val();
    var saddress=$("#saddress").val();
    var province_id=$("#province").val();
    var city_id=$("#city").val();
    var usre_id=$("#usre").val();
    var sphone=$("#sphone").val();
    var sdetails=$("#sdetails").val();
    var state=$("#state").val();
    $.ajax({
        url:'addWarehouse',
        type:'get',
        data:{"sname":sname,"saddress":saddress,"province_id":province_id,"city_id":city_id,"usre_id":usre_id,"sphone":sphone,"sdetails":sdetails,"state":state},
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


//表单验证
function formVerify() {
        var ju;
        var c_name=$("#sname").val();
        $.ajax({
            url:'findCname',
            type:'get',
            dataType:'text',
            async:false,
            data:{"c_name":c_name},
            success:function (data) {
                if (data=="error") {
                    $(".forminfo li:eq(0) i").html("仓库名称已被使用！").css("color","red");
                    ju=false;
                }else {
                    $(".forminfo li:eq(0) i").html("仓库名称可以使用！").css("color","green");
                    ju=true;
                }
            }
        })
    return ju;
}

//省
function provincelist() {
    $.ajax({
        url:"findProvince",
        type:"get",
        dataType:"json",
        async:false,
        success:function (d) {
            var op="<option value=''>请选择省份</option>";
            $.each(d,function (index,item) {
                    op+="<option value='"+item.id+"'>"+item.p_name+"</option>";

            });
            $("#province").append(op);

        }

    });
}


//市
function citylist(id) {
    $.ajax({
        url:"showcity",
        type:"get",
        data:{"p_id":id},
        dataType: "json",
        success:function (d) {
            $("#city option").remove();
            var op="<option value=''>请选择城市</option>";
            $.each(d,function (index,item) {
                    op+=" <option value='"+item.id+"'>"+item.c_name+"</option>";

            });
            $("#city").append(op);

        }
    });
}

//财务部下的经理
function findPerson() {
    $.ajax({
        url:'findPerson',
        type:'get',
        dataType:'json',
        success:function (d) {
            var op="<option value=''>请选择</option>";
            $.each(d,function (index,item) {
                    op+="<option value='"+item.u_id+"'>"+item.uname+"</option>";
            })
            $("#usre").append(op);
        }
    })
}