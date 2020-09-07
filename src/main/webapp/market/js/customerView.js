$(function () {
    // 页面加载获取客户详情
    var customId = getParams("customId");
    $.ajax({
        url:"../customerViews",
        type:"post",
        data:{"customId":customId},
        dataType:"json",
        success:function(data){
            $(".forminfo li").remove();
            var li = "";
            li += "<li><label>姓名</label>";
            li += "<cite>"+data.customname+"</cite></li>";
            li += "<li><label>性别</label>";
            li += "<cite>"+data.sex+"</cite></li>";
            li += "<li><label>所属公司名称</label>";
            li += "<cite>"+data.company+"</cite></li>";
            li += "<li><label>联系方式</label>";
            li += "<cite>"+data.telephone+"</cite></li>";
            li += "<li><label>联系地址</label>";
            li += "<cite>"+data.homeAddress+"</cite></li>";
            li += "<li><label>所属区域</label>";
            li += "<cite>"+data.address+"</cite></li>";
            li += "<li><label>描述</label>";
            if (data.distract==null || data.distract==""){
                li += "<cite></cite></li>";
            } else {
                li += "<cite>"+data.distract+"</cite></li>";
            }
            li += "<li><label>状态</label>";
            li += "<cite>"+data.status+"</cite></li>";
            li += "<li><label>创建人</label>";
            li += "<cite>"+data.users1.uname+"</cite></li>";
            li += "<li><label>创建时间</label>";
            li += "<cite>"+data.createTime+"</cite></li>";
            li += "<li><label>&nbsp;</label><input name=\"\" type=\"button\" class=\"btn\" value=\"返回\" onclick=\"window.history.go(-1);\"/></li>";
            $(".forminfo").append(li);
        }
    })
})