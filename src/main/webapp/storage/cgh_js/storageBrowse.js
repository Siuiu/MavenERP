var pageCount=0;
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
})


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
                tr+="<td>";
                tr+="<a href=\"../storage/storageView.html?id="+item.id+"\" class=\"tablelink\">查看详情&nbsp;</a>";
                tr+="</td>";
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
