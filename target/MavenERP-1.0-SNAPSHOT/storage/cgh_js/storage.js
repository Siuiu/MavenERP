var pageCount = 0;
$(function () {
    var info=getInfo();
    storageList(info);
    page(info);

    $("#select").click(function () {
        info=getInfo();
        storageList(info);
        page(info);

    })
    provincelist();
    $("#province").change(function () {
        var id=$(this).val();
        citylist(id);
    });

    // 修改仓库信息
    // 1.1点击注销
    $(".tablelist").delegate("#logOut","click",function () {
        // 给当前点击的注销添加属性name为唯一标识
        var wId = $(this).attr("name");
        // 给弹出框为确定的按钮添加属性id为唯一标识
        $(".sure").attr("id","sure1");
        // 给添加的id属性的节点添加属性name等于点击注销时获取的值
        $(".sure").attr("name",wId);

        // 执行弹框操作
        tipOpen('是否确认注销此条信息？');
    })
    // 1.2确定注销
    $("#tip").delegate("#sure1","click",function () {
        // 获取当前确定按钮的属性name
        var wId = $(this).attr("name");
        // 获取当前按钮的id(此id为了后续共用一个请求做不同操作使用)
        var sure = $(this).attr("id");
        // 调用函数执行操作
        changeStatus(wId,sure);
    })
    // 2.1点击恢复
    $(".tablelist").delegate("#recover","click",function () {
        var wId = $(this).attr("name");
        $(".sure").attr("id","sure2");
        $(".sure").attr("name",wId);

        tipOpen('是否确认恢复此条信息？');
    })
    // 2.2确定恢复
    $("#tip").delegate("#sure2","click",function () {
        var wId = $(this).attr("name");
        var sure = $(this).attr("id");
        changeStatus(wId,sure);
    })
})

// 改变仓库状态
function changeStatus(wId,sure){
    var data = {"id":wId,"sure":sure};
    $.ajax({
        url:"../changesStatusWarehouse",
        data:data,
        type:"post",
        dataType:"text",
        success:function (data) {
            if (data==1){
                location.href="storageList.html";
            } else {
                alert("修改失败！");
                location.href="storageList.html";
            }
        }
    })
}

//获取数据
function getInfo() {
    var info={};
    info.ck_name=$("[type=text]").val();
    info.p_name=$("select:eq(0)").val();
    info.c_name=$("select:eq(1)").val();
    info.pageStart=1;
    info.pageSize=5;
    return info;
}


//遍历数据
function storageList(info) {
                $.ajax({
                    url:'storagelist',
                    type:'get',
                    data:info,
                    dataType:'json',
                    async:false,
                    success:function (data) {
                        var tr = "";
                        $.each(data.list,function (index,item) {
                            tr+="<tr>";
                            tr+="<td>"+(index+1)+"</td>";
                            tr+="<td>"+item.c_name+"</td>";
                            tr+="<td>"+item.users.uname+"</td>";
                            tr+="<td>"+item.phone+"</td>";
                            tr+="<td>"+item.province.p_name+item.city.c_name+"</td>";
                            tr+="<td>"+(item.state ==1 ? "可用" : "不可用")+"</td>";
                            tr+="<td>"+item.creation_time+"</td>";
                            tr+="<td>"+item.user.uname+"</td>";
                            if (item.state == "1") {
                                tr+="<td>";
                                tr+="<a href=\"storageView.html?id="+item.id+"\" class=\"tablelink\">查看详情&nbsp;</a>";
                                tr+="<a href=\"storageUpdate.html?id="+item.id+"\" class=\"tablelink\">修改&nbsp;</a>";
                                tr+="<a href=\"javascript:void(0)\" class=\"tablelink\" id='logOut' name='"+item.id+"'>注销</a>";
                                tr+="</td>";
                            }else {
                                tr+="<td>";
                                tr+="<a href=\"storageView.html?id="+item.id+"\" class=\"tablelink\">查看详情&nbsp;</a>";
                                tr+="<a href=\"javascript:void(0)\" class=\"tablelink\" id='recover' name='"+item.id+"'>恢复</a>";
                                tr+="</td>";
                            }
                            tr+="</tr>";
                        })
                        pageCount=data.pages;
                        $("tbody").html(tr);
        }
    })
}


$('.m-style').pagination({
    pageCount:pageCount,
    jump:true,
    coping:true,
    homePage:'首页',
    endPage:'末页',
    prevContent:'上页',
    nextContent:'下页',
    callback:function (api) {
        info.pageStart=api.getCurrent();
        storageList(info);
    }
})


//省
function provincelist() {
    $.ajax({
        url:"findProvince",
        type:"get",
        dataType:"json",
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


function page(info) {
    $('.m-style').pagination({
        pageCount:pageCount,
        jump:true,
        coping:true,
        homePage:'首页',
        endPage:'末页',
        prevContent:'上页',
        nextContent:'下页',
        callback:function (api) {
            info.pageStart=api.getCurrent();
            storageList(info);
        }
    })
}
