$(function () {
    // 定义全局变量
    var pageCount;// 总页数

    // 加载时获取数据
    findOrder();

})

// 点击查询时获取数据
function findOrder(){
    data = inputval();
    $.ajax({
        url: "../orderExamineList",
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
    var orderId = $("[name=orderId]").val();
    var customName = $("[name=customName]").val();
    var startTime = $("[name=startTime]").val();
    var endTime = $("[name=endTime]").val();
    var minMoney = $("[name=minMoney]").val();
    var maxMoney = $("[name=maxMoney]").val();
    var status = "1";
    data = {"pageNum":1,"pageSize":3,"position":"非组员","orderId":orderId,"customName":customName,
        "startTime":startTime,"endTime":endTime,"minMoney":minMoney,"maxMoney":maxMoney,"status":status};
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
        url: "../orderExamineList",
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
        tbody += "<td>" + item.orderId + "</td>";
        tbody += "<td>" + item.custom.customname + "</td>";
        tbody += "<td>" + item.custom.telephone + "</td>";
        tbody += "<td>" + item.orderTime + "</td>";
        tbody += "<td>￥" + item.orderMoney + "</td>";
        tbody += "<td>" + item.custom.users2.uname + "</td>";
        tbody += "<td>审核中</td>";
        tbody += "<td><a href='orderExamine.html?orderId="+item.orderId+"' class=\"tablelink\">审核</a></td>";
        tbody += "</tr>";
    })
    tbody += "</tbody>";
    $(".tablelist").append(tbody);
    // 返回总页数
    return data.pages;

}