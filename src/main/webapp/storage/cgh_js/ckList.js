var pageCount = 0;
// 登录判断职位
var position = "";
$(function () {
    var info=getInfo();
    ckList(info);
    page(info);
    $("#select").click(function () {
        info=getInfo();
        ckList(info);
        page(info);
    })
    position=findPosition();// 职位
})



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
//获取数据
function getInfo() {
    var info={};
    info.c_name=$("[type=text]:eq(0)").val();
    info.indent=$("[type=text]:eq(1)").val();
    info.state=$("select:eq(0)").val();
    info.pageStart=1;
    info.pageSize=5;
    return info;
}

//遍历数据
function ckList(info) {
    $.ajax({
        url:'ckList',
        type:'get',
        data:info,
        dataType:'json',
        async:false,
        success:function (data) {
            var tr="";
            $.each(data.list,function (index,item) {
                tr+="<tr>";
                tr+="<td>"+(index+1)+"</td>";
                tr+="<td>"+item.indent+"</td>";
                tr+="<td>￥"+item.order.orderMoney+"</td>";
                tr+="<td>"+item.warehouse.c_name+"</td>";
                tr+="<td>"+item.c_date+"</td>";
                tr+="<td>"+item.users.uname+"</td>";
                tr+="<td>"+(item.state==1?"未发货":(item.state==2?"已发货":(item.state==3?"已回款":(item.state==4?"取消订单":"已退货"))))+"</td>";
                if (item.state=="1") {
                    tr+="<td class='option'>";
                    tr+="<input type='hidden' value='"+item.indent+"'/>";
                    tr+="<a href='deliveryView.html?ck_indent="+item.indent+"' class='tablelink'>查看详情&nbsp;</a>";
                    if (position=="组员") {
                        tr+="<a href='javascript:void(0)' class='tablelink'>发货&nbsp;</a>";
                        tr+="<a href='javascript:void(0)' class='tablelink'>取消订单</a>";
                    }
                    tr+="</td>";
                }else if (item.state=="2") {
                    tr+="<td class='option'>";
                    tr+="<input type='hidden' value='"+item.indent+"'/>";
                    tr+="<a href='deliveryView.html?ck_indent="+item.indent+"' class='tablelink'>查看详情&nbsp;</a>";
                    if (position=="组员") {
                        tr+="<a href='javascript:void(0)' class='tablelink'>取消订单&nbsp;</a>";
                        tr+="<a href='javascript:void(0)' class='tablelink'>确认回款</a>";
                    }

                    tr+="</td>";
                }else if (item.state=="3") {
                    tr+="<td class='option'>";
                    tr+="<input type='hidden' value='"+item.indent+"'/>";
                    tr+="<a href='deliveryView.html?ck_indent="+item.indent+"' class='tablelink'>查看详情&nbsp;</a>";
                    tr+="</td>";
                }else if (item.state=="4") {
                    tr+="<td class='option'>";
                    tr+="<input type='hidden' value='"+item.indent+"'/>";
                    tr+="<a href='deliveryView.html?ck_indent="+item.indent+"' class='tablelink'>查看详情&nbsp;</a>";
                    if (position=="组员") {
                        tr+="<a href='javascript:void(0)' class='tablelink'>确认已退货</a>";
                    }

                    tr+="</td>";
                }else {
                    tr+="<td>";
                    tr+="<a href='deliveryView.html?ck_indent="+item.indent+"' class='tablelink'>查看详情</a>";
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
        ckList(info);
    }
})

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
            ckList(info);
        }
    })
}
