var position = "";
$(function(){
    // 定义全局变量 总页数
    var pageCount;
    position=findPosition();// 职位

    // 添加按钮是否显示
    if(position!='经理'){
        $(".subBut").eq(1).remove();
    }

    // 加载时获取数据
    var data = {"pageNum":1,"pageSize":3};
    $.ajax({
        url:"/MavenERP/customerList",
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

    // 修改客户信息
    // 1.1点击注销
    $(".tablelist").delegate("#logOut","click",function () {
        // 给当前点击的注销添加属性name为唯一标识
        var customId = $(this).attr("name");
        // 给弹出框为确定的按钮添加属性id为唯一标识
        $(".sure").attr("id","sure1");
        // 给添加的id属性的节点添加属性name等于点击注销时获取的值
        $("#sure1").attr("name",customId);

        // 执行弹框操作
        tipOpen("是否确认注销此条信息？");
    })
    // 1.2确定注销
    $("#tip").delegate("#sure1","click",function () {
        // 获取当前确定按钮的属性name
        var customId = $(this).attr("name");
        // 获取当前按钮的id(此id为了后续共用一个请求做不同操作使用)
        var sure = $(this).attr("id");
        // 调用函数执行操作
        changeStatus(customId,sure);
    })
    // 2.1点击恢复
    $(".tablelist").delegate("#recover","click",function () {
        var customId = $(this).attr("name");
        $(".sure").attr("id","sure2");
        $("#sure2").attr("name",customId);

        tipOpen('是否确认恢复此条信息？')
    })
    // 2.2确定恢复
    $("#tip").delegate("#sure2","click",function () {
        var customId = $(this).attr("name");
        var sure = $(this).attr("id");
        changeStatus(customId,sure);
    })
    // 3.1点击分配
    $(".tablelist").delegate("#allocation","click",function () {
        var customId = $(this).attr("name");
        $(".sure").attr("id","sureThree");
        $(".sure").attr("name",customId);
        // 获取组员信息
        $.ajax({
            url:"../findUserJob",
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
        allottipOpen();
    })
    // 3.2获取组员名称
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
    // 3.3确定分配
    $("#allottip").delegate("#sureThree","click",function () {
        var customId = $(this).attr("name");
        var serviceId = $(".dfselect").eq(2).val();
        $.ajax({
            url:"../allocationCustom",
            data:{"customId":customId,"serviceId":serviceId},
            type:"post",
            dataType:"text",
            success:function (data) {
                if (data==1){
                    location.href="customerList.html";
                } else {
                    alert("修改失败！");
                    location.href="customerList.html";
                }
            }
        })
    })

})

// 点击查询时获取数据
function findCustomer(){
    data = inputval();
    $.ajax({
        url: "/MavenERP/customerList",
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
    var status = $("[name=status]").val();
    var allocation = $("[name=allocation]").val();
    data = {"pageNum":1,"pageSize":3,"company":company,"customer":customer,"province":province,"city":city,"status":status,"allocation":allocation};
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
            customerlist(api.getCurrent());
        }
    });
}

// 点击页面获取相应的值
function customerlist(pageNum){
    data = inputval();
    data.pageNum = pageNum;
    $.ajax({
        url: "/MavenERP/customerList",
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
            tbody += "<td>" + item.status + "</td>";
            tbody += "<td>" + item.createTime + "</td>";
            tbody += "<td>" + item.users1.uname + "</td>";
            if (item.users2!=null){
                tbody += "<td>" + item.distracTime + "</td>";
                tbody += "<td>" + item.users2.uname + "</td>";
            }else {
                tbody += "<td></td>";
                tbody += "<td></td>";
            }
            tbody += "<td>"
                tbody += "<a href='customerView.html?customId="+item.customId+"' class='tablelink'>查看详情</a>&nbsp;";
                if(position=="经理"){
                    if (item.status=="可用"){
                        tbody += "<a href='customerUpdate.html?customId="+item.customId+"' class=\"tablelink\">修改</a>&nbsp;";
                        tbody += "<a href=\"javascript:void(0)\" class=\"tablelink\" id='logOut' name='"+item.customId+"'>注销</a>&nbsp;";
                    }else {
                        tbody += "<a href=\"javascript:void(0)\" class=\"tablelink\" id='recover' name='"+item.customId+"'>恢复</a>&nbsp;";
                    }
                    tbody += "<a href=\"javascript:void(0);\" class=\"tablelink\" id='allocation' name='"+item.customId+"'>分配</a>";
                }
            tbody += "</td>";
        tbody += "</tr>";
    })
    tbody += "</tbody>";
    $(".tablelist").append(tbody);
    // 返回总页数
    return data.pages;

}

// 改变用户状态
function changeStatus(customId,sure){
    var data = {"customId":customId,"sure":sure};
    $.ajax({
        url:"../changesStatusCustom",
        data:data,
        type:"post",
        dataType:"text",
        success:function (data) {
            if (data==1){
                location.href="customerList.html";
            } else {
                alert("修改失败！");
                location.href="customerList.html";
            }
        }
    })
}
