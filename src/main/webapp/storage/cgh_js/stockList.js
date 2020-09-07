// 登录判断职位
var position = "";
// 登录判断职位
function findPosition(){
    var position;
    $.ajax({
        url:"/MavenERP/market/userPosition",
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

$(function () {
    // 定义全局变量
    var pageCount;// 总页数
    position=findPosition();// 职位
    // 加载时获取数据
    var data = {"pageNum":1,"pageSize":5,"position":position};
    $.ajax({
        url:"../stockList",
        type:"post",
        data:data,
        async:false,
        dataType:"json",
        success:function (data) {
            $("tbody").remove();
            pageCount = successData(data);
            page(pageCount);
        }
    })

    // 2.1点击入库
    $(".tablelist").delegate("#storageOne","click",function () {
        var rk_indent = $(this).attr("name");
        $(".sure").attr("id","sureOne");
        $(".sure").attr("name",rk_indent);

        tipOpen('确定此采购单入库吗？');
    })
    $(".tablelist").delegate("#storageTwo","click",function () {
        var rk_indent = $(this).attr("name");
        $(".sure").attr("id","sureTwo");
        $(".sure").attr("name",rk_indent);

        tipOpen('确定此采购单取消入库吗？');
    })
    // 2.2确定
    $("#tip").delegate("#sureOne","click",function () {
        var rk_indent = $(this).attr("name");
        var sure = $(this).attr("id");
        changeState(rk_indent,sure);
    })
    $("#tip").delegate("#sureTwo","click",function () {
        var rk_indent = $(this).attr("name");
        var sure = $(this).attr("id");
        changeState(rk_indent,sure);
    })

})

// 改变用户状态
function changeState(rk_indent,sure){
    var data = {"rk_indent":rk_indent,"sure":sure};
    $.ajax({
        url:"../changeState",
        data:data,
        type:"post",
        dataType:"text",
        success:function (data) {
            if (data==1){
                alert("修改成功！");
                location.href="stockList.html";
            } else {
                alert("修改失败！");
                location.href="stockList.html";
            }
        }
    })
}

// 点击查询时获取数据
function findOrder(){
    data = inputval();
    $.ajax({
        url: "../stockList",
        data:data,
        type:"post",
        dataType: "json",
        success:function (data) {
            $("tbody").remove();
            pageCount = successData(data);
            page(pageCount);
        }
    })
}

// 获取查询条件
function inputval(){
    var c_name = $("[name=c_name]").val().trim();
    var rk_indent = $("[name=rk_indent]").val().trim();
    var state = $("[name=state]").val().trim();
    data = {"pageNum":1,"pageSize":3,"position":position,
        "c_name":c_name,"rk_indent":rk_indent,"state":state};
    return data;
}

// 分页操作
function page(pageCount) {

    $('.m-style').pagination({
        pageCount: pageCount,
        jump: true,
        coping: true,
        homePage: '首页',
        endPage: '末页',
        prevContent: '上页',
        nextContent: '下页',
        callback: function (api) {
            seekOrder(api.getCurrent());
        }
    });
}

// 点击页面获取相应的值
function seekOrder(pageNum){
    data = inputval();
    data.pageNum = pageNum;
    $.ajax({
        url: "../stockList",
        data:data,
        type:"post",
        dataType: "json",
        async:false,
        success:function (data) {
            $("tbody").remove();
            pageCount = successData(data);
        }
    })
}

// 展示数据并获取总页数
function successData(data){
    var tbody = "<tbody>";
    $.each(data.list,function(index,item){
        tbody += "<tr>";
        tbody += "<td>" + (index+1) + "</td>";
        tbody += "<td>" + item.rk_indent + "</td>";
        tbody += "<td>￥" + item.purchase.money + "</td>";
        tbody += "<td>" + item.warehouse.c_name + "</td>";
        tbody += "<td>" + item.rk_date + "</td>";
        tbody += "<td>" + item.users.uname + "</td>";
        if (item.state=="1"){
            tbody += "<td>未入库</td>";
        }else{
            tbody += "<td>入库</td>";
        }
        tbody += "<td>";
        tbody += "<a href='stockView.html?pid="+item.rk_indent+"' class=\"tablelink\">查看详情</a>&nbsp;";
        if(position=="组员"){
            if (item.state=="1"){
                tbody += "<a href=\"javascript:void(0)\" class=\"tablelink\" id='storageOne' name='"+item.rk_indent+"'>入库</a>";
            }else{
                tbody += "<a href=\"javascript:void(0)\" class=\"tablelink\" id='storageTwo' name='"+item.rk_indent+"'>取消入库</a>";
            }
        }
        tbody += "</td>";
        tbody += "</tr>";
    })
    tbody += "</tbody>";
    $(".tablelist").append(tbody);
    // 返回总页数
    return data.pages;

}