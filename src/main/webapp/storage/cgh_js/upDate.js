var tamp="";
var temps="";
var person="";
var jstate="";
var judge;
var cname="";
var original;
$(function () {

    setValue();
    provincelist();
    var id=$("#province option:selected").val();
    citylist(id);
    $("#province").change(function () {
        var id=$(this).val();
        citylist(id);
    });
    findPerson();


    $("#sname").blur(function () {
        original=$("#sname").val();
          formVerify(original);
    })
    $("#sure").click(function () {
        original=$("#sname").val();
        judge = formVerify(original);
            if (judge) {
                upDate();
            }else {
                alert("仓库名称重复，请修改后再提交！");
            }
    })
    judgeState();


})

//修改
function upDate() {
    var id=getParams("id");
    var sname=$("#sname").val();
    var saddress=$("#saddress").val();
    var province_id=$("#province").val();
    var city_id=$("#city").val();
    var usre_id=$("#usre").val();
    var sphone=$("#sphone").val();
    var sdetails=$("#sdetails").val();
    var state=$("#state").val();
    $.ajax({
        url:'updateWarehouse',
        type:'get',
        data:{"id":id,"sname":sname,"saddress":saddress,"province_id":province_id,"city_id":city_id,"usre_id":usre_id,"sphone":sphone,"sdetails":sdetails,"state":state},
        dataType:'json',
        success:function (row) {
            if (row>0) {
                alert("修改成功！");
                location.reload();
            }else {
                alert("修改失败！");
            }
        }
    })
}
//赋值
function setValue() {
    var id=getParams("id");
    $.ajax({
        url: 'storageview',
        type: 'get',
        async:false,
        data:{"id":id},
        dataType:'json',
        success:function (data) {

            $("#sname").val(data.c_name);

            $("#saddress").val(data.c_address);

            $("#sphone").val(data.phone);

            $("#sdetails").val(data.details);

            $("#user").val(data.user.uname);

            $("#time").val(data.creation_time);
            tamp=data.province.id;
            temps=data.city.id;
            person=data.users.u_id;
            jstate=data.state;
            cname=data.c_name;
        }

    })

}


//表单验证
function formVerify(original) {
    var ju;
    if(original == cname){
        ju=true;
    }else {
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

    }
    return ju;
}


//状态
function judgeState() {
    var states=$("#state option").val();
    if (states==jstate) {
        $("#state option[value="+jstate+"]").attr("selected","selected");
    }
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
                if(tamp==item.id){
                    op+="<option value='"+item.id+"' selected='selected'>"+item.p_name+"</option>";
                }else{
                    op+="<option value='"+item.id+"'>"+item.p_name+"</option>";
                }

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
                if (temps==item.id) {
                    op+=" <option value='"+item.id+"' selected='selected'>"+item.c_name+"</option>";
                }else {
                    op+=" <option value='"+item.id+"'>"+item.c_name+"</option>";
                }

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
                if (person==item.u_id) {
                    op+="<option value='"+item.u_id+"' selected='selected'>"+item.uname+"</option>";
                }else {
                    op+="<option value='"+item.u_id+"'>"+item.uname+"</option>";
                }

            })
            $("#usre").append(op);
        }
    })
}
