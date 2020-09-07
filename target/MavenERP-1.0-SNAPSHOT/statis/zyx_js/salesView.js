$(function () {
    var pageCount;
    var data = getDate();
    show(data);
    page(pageCount,data);
    function getDate() {
        var data = {};
        data.uname = getParams("uname");
        data.pageStart = 1;
        data.pageSize = 3;
        return data;
    }

    function show() {
        var tr = "";
        $(".tablelist tr:not(:first)").remove();
        $.ajax({
            type: "post",
            url: "salesViewList",
            dataType: "json",
            data: data,
            async:false,
            success: function (data) {
                $.each(data.list, function (index, order) {
                    tr += "<tr>" +
                        "   <td>" + (index + 1) + "</td>" +
                        "   <td>" + order.custom.customname + "</td>" +
                        "   <td>" + order.orderId + "</td>" +
                        "   <td>" + order.num + "</td>" +
                        "   <td>￥" + order.orderMoney + "</td>" +
                        "   <td>" + order.orderTime + "</td>" +
                        "   <td>" + order.users.uname + "</td>" +
                        "   <td>" +
                        "       <a href='../../market/order/orderView.html?orderId=" + order.orderId + "' class='tablelink'>查看详情</a>" +
                        "   </td>\n" +
                        " </tr>";
                })
                pageCount=data.pages;
                $(".tablelist tbody").append(tr);
                $(".tablelist tbody tr:odd").addClass('odd');
            }
        })
    }

    function page(pages, data) {

        $('.m-style').pagination({
            pageCount: pages,
            jump: true,
            coping: true,
            homePage: '首页',
            endPage: '末页',
            prevContent: '上页',
            nextContent: '下页',
            callback: function (api) {
                data.pageStart = api.getCurrent();
                show(data);
            }
        });
    }

})