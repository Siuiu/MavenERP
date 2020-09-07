var position = "";
$(function () {
    // 定义全局变量
    position=findPosition();// 职位
    var pageCount;// 总页数

    // 加载时获取数据
    var data = {"pageNum":1,"pageSize":3,"position":position};
    $.ajax({
        url:"../orderList",
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

    // 添加按钮是否显示
    if(position!='组员'){
        $(".subBut").eq(1).remove();
    }

    // 点击删除时
    $("body").delegate("#deleteOrder","click",function () {
        var orderId = $(this).attr("name");
        $(".sure:eq(0)").attr("id","sure");
        $(".sure:eq(0)").attr("name",orderId);
        deltipOpen();

    })
    $("#deltip").delegate("#sure","click",function () {
        var orderId = $(this).attr("name");
        $.ajax({
            url:"../deleteOrder",
            type:"post",
            data:{"orderId":orderId},
            success:function (data){
                if (data!=0){
                    alert("删除订单成功");
                    location.href = "orderList.html";
                } else {
                    alert("删除订单失败");
                }
            }
        })
    })

    // 1点击审核
    $(".tablelist").delegate("#audit","click",function () {
        var orderId = $(this).attr("name");
        $(".sure:eq(1)").attr("id","sureTwo");
        $(".sure:eq(1)").attr("name",orderId);
        // 获取组员信息
        $.ajax({
            url:"../findJobUser",
            data:{"deptId":1},
            type:"post",
            dataType:"json",
            async:false,
            success:function(data){
                $(".dfselect").eq(1).empty();
                var option = "<option value='0'>请选择</option>";
                $.each(data,function (index,item) {
                    option += "<option value='"+item.job_id+"'>"+item.job_name+"</option>"
                })
                $(".dfselect").eq(1).append(option);
            }
        })
        examinetipOpen();
    })
    // 2获取组员以上名称
    $(".dfselect").eq(1).change(function () {
        var jobId = $(this).val();
        $.ajax({
            url:"../findUserName",
            data:{"jobId":jobId},
            type:"post",
            dataType:"json",
            success:function (data) {
                $(".dfselect").eq(2).empty();
                var option = "<option value='0'>请选择</option>";
                $.each(data,function (index,item) {
                    option += "<option value='"+item.u_id+"'>"+item.uname+"</option>"
                })
                $(".dfselect").eq(2).append(option);
            }
        })
    })
    // 3确定审核
    $("#examinetip").delegate("#sureTwo","click",function () {
        var orderId = $(this).attr("name");
        var checkId = $(".dfselect").eq(2).val();
        $.ajax({
            url:"../auditCustom",
            data:{"orderId":orderId,"checkId":checkId},
            type:"post",
            dataType:"text",
            success:function (data) {
                if (data==1){
                    location.href="orderList.html";
                } else {
                    alert("修改失败！");
                    location.href="orderList.html";
                }
            }
        })
    })

})

// 点击查询时获取数据
function findOrder(){
    data = inputval();
    $.ajax({
        url: "../orderList",
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
    var status = $("[name=status]").val();
    data = {"pageNum":1,"pageSize":3,"position":position,"orderId":orderId,"customName":customName,
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
        url: "../orderList",
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
        tbody += "<td>";
        if (item.status=="0"){
            tbody += "未审核";
        }else if(item.status=="1"){
            tbody += "审核中";
        }else if(item.status=="2"){
            tbody += "审核通过";
        }else if(item.status=="3"){
            tbody += "审核不通过";
        }
        tbody += "</td>";
        if (item.custom.users1==null || item.custom.users1==""){
            tbody += "<td></td>";
        } else{
            tbody += "<td>" + item.custom.users1.uname + "</td>";
        }
        if (item.checTime==null || item.checTime==""){
            tbody += "<td></td>";
        } else{
            tbody += "<td>" + item.checTime + "</td>";
        }
        tbody += "<td>";
        if(position=="组员"){
            if (item.status=="0"){
                tbody += "<a href='orderView.html?orderId="+item.orderId+"' class='tablelink'>查看详情</a>&nbsp;";
                tbody += "<a href='orderUpdate.html?orderId="+item.orderId+"' class='tablelink'>修改</a>&nbsp;";
                tbody += "<a href='javascript:void(0);' class='tablelink' id='deleteOrder' name='"+item.orderId+"'>删除</a>&nbsp;";
                tbody += "<a href='javascript:void(0);' class='tablelink' id='audit' name='"+item.orderId+"'>提交审核</a>";
            }else if(item.status=="1"){
                tbody += "<a href='orderView.html?orderId="+item.orderId+"' class='tablelink'>查看详情</a>";
            }else if(item.status=="2"){
                if (item.dd_state=="0"||item.dd_state==0){
                    tbody += "<a href='orderView.html?orderId="+item.orderId+"' class='tablelink'>查看详情</a>&nbsp;";
                    tbody += "<a href='../../storage/delivery/deliveryView.html?ck_indent="+item.orderId+"' class='tablelink'>出库详情</a>";
                } else{
                    tbody += "<a href='orderView.html?orderId="+item.orderId+"' class='tablelink'>查看详情</a>&nbsp;";
                }
            }else if(item.status=="3"){
                tbody += "<a href='orderView.html?orderId="+item.orderId+"' class='tablelink'>查看详情</a>&nbsp;";
                tbody += "<a href='orderUpdate.html?orderId="+item.orderId+"' class='tablelink'>修改</a>&nbsp;";
                tbody += "<a href='javascript:void(0);' class='tablelink' id='audit' name='"+item.orderId+"'>提交审核</a>";
            }
        }else {
            tbody += "<a href='orderView.html?orderId="+item.orderId+"' class='tablelink'>查看详情</a>";
        }
        tbody += "</td>";
        tbody += "</tr>";
    })
    tbody += "</tbody>";
    $(".tablelist").append(tbody);
    // 返回总页数
    return data.pages;

}