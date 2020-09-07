var position="";
$(function () {
    // 定义全局变量
    position=findPosition();// 职位
    var pageCount;// 总页数

    // 加载时获取数据
    var data = {"pageNum":1,"pageSize":3,"position":position};
    $.ajax({
        url:"../customBrowse",
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

    // 省份的填入
    findProvince();
    // 区域的填入
    $("[name=province]").change(function(){
        var id = $(this).val();
        findCity(id);
    })

})

// 点击查询时获取数据
function findCustomer(){
    data = inputval();
    $.ajax({
        url: "../customBrowse",
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
    var company = $("[name=companyName]").val();
    var customer = $("[name=customerName]").val();
    var province = $("[name=province]").find("option:selected").attr("name");// 获取的是name值而不是value的值
    var city = $("[name=city]").val();
    data = {"pageNum":1,"pageSize":3,"position":position,"company":company,"customer":customer,"province":province,"city":city};
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
            customBrowse(api.getCurrent());
        }
    });
}

// 点击页面获取相应的值
function customBrowse(pageNum){
    data = inputval();
    data.pageNum = pageNum;
    $.ajax({
        url: "../customBrowse",
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
        tbody += "<td>" + item.customname + "</td>";
        tbody += "<td>" + item.sex + "</td>";
        tbody += "<td>" + item.telephone + "</td>";
        tbody += "<td>" + item.company + "</td>";
        tbody += "<td>" + item.address + "</td>";
        tbody += "<td>" + item.distracTime + "</td>";
        tbody += "<td>" + item.users2.uname + "</td>";
        tbody += "<td>"
        tbody += "<a href='../customer/customerView.html?customId="+item.customId+"' class='tablelink'>查看详情</a>&nbsp;";
        tbody += "</td>";
        tbody += "</tr>";
    })
    tbody += "</tbody>";
    $(".tablelist").append(tbody);
    // 返回总页数
    return data.pages;

}