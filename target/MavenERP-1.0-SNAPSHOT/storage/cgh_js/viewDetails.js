$(function () {
    viewDetails();
})
//查看详情
function viewDetails() {
    var id = getParams("id");
    $.ajax({
        url: 'storageview',
        type: 'get',
        data:{"id":id},
        dataType:'json',
        success:function (data) {
            var li = "";
            li += "<li>";
            li += "<label>仓库名称</label>";
            li += "<cite>"+data.c_name+"</cite>";
            li += "</li>";
            li += "<li>";
            li += "<label>仓库地址</label>";
            li += "<cite>"+data.c_address+"</cite>";
            li += "</li>";
            li += "<li>";
            li += "<label>所属区域</label>";
            li += "<cite>"+data.province.p_name+data.city.c_name+"</cite>";
            li += "</li>";
            li += "<li>";
            li += "<label>负责人</label>";
            li += "<cite>"+data.users.uname+"</cite>";
            li += "</li>";
            li += "<li>";
            li += "<label>联系方式</label>";
            li += "<cite>"+data.phone+"</cite>";
            li += "</li>";
            li += "<li>";
            li += "<label>描述</label>";
            li += "<cite>"+data.details+"</cite>";
            li += "</li>";
            li += "<li>";
            li += "<label>状态</label>";
            li += "<cite>"+(data.state==1? "可用":"不可用")+"</cite>";
            li += "</li>";
            li += "<li>";
            li += "<label>创建人</label>";
            li += "<cite>"+data.user.uname+"</cite>";
            li += "</li>";
            li += "<li>";
            li += "<label>创建时间</label>";
            li += "<cite>"+data.creation_time+"</cite>";
            li += "</li>";
            li +="<li>";
            li += "<label>&nbsp;</label>";
            li += "<input name=\"\" type=\"button\" class=\"btn\" value=\"返回\" onclick=\"window.history.go(-1);\"/>";
            li += "</li>";
            $(".forminfo").append(li);
        }
    })
}
