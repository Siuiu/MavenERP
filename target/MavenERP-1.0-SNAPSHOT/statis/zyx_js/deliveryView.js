$(function () {

    var pageCount;
    var province;
    var data = getData();
    show(data);
    page(pageCount, data);
    $(".subBut").click(function () {
        data = getData();
        show(data);
        page(pageCount, data);
    })
    function getData() {
        var data = {};
        data.c_name = getParams("c_name");
        data.pageStart = 1;
        data.pageSize = 3;
        return data;
    }
    function show() {
        var tr = "";
        $(".tablelist tr:not(:first)").remove();
        $.ajax({
            type: "post",
            url: "deliveryViewList",
            dataType: "json",
            data: data,
            async: false,
            success: function (data) {

                $.each(data.list, function (index, ck_warehouse) {
                    tr += "<tr>" +
                        "   <td>" + (index + 1) + "</td>" +
                        "   <td>" + ck_warehouse.warehouse.c_name + "</td>" +
                        "   <td>" +  ck_warehouse.indent + "</td>" +
                        "   <td>" + ck_warehouse.count + "</td>" + "  " +
                        "   <td>￥" + ck_warehouse.order.orderMoney + "</td>"+
                        "   <td>" + ck_warehouse.c_date + "</td>"+
                        "   <td>" +  ck_warehouse.users.uname + "</td>" +
                        "   <td>" +
                        "       <a href='../../storage/delivery/deliveryView.html?ck_indent="+ ck_warehouse.indent+"' class='tablelink'>查看详情</a>" +
                        "   </td>\n" +
                        " </tr>";
                })

                pageCount = data.pages;
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