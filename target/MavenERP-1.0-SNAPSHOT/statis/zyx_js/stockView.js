$(function () {

    var pageCount;
    var province;
    var data = getData();
    show(data);
    page(pageCount, data);
    $(".subBut").click(function () {
        data = getData();province
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
            url: "stockViewList",
            dataType: "json",
            data: data,
            async: false,
            success: function (data) {

                $.each(data.list, function (index, rk_warehouse) {
                    tr += "<tr>" +
                        "   <td>" + (index + 1) + "</td>" +
                        "   <td>" + rk_warehouse.warehouse.c_name + "</td>" +
                        "   <td>" + rk_warehouse.rk_indent+"</td>" +
                        "   <td>" + rk_warehouse.count + "</td>" + "  " +
                        "   <td>￥" + rk_warehouse.purchase.money + "</td>" +
                        "   <td>" + rk_warehouse.rk_date + "</td>" +
                        "   <td>" + rk_warehouse.users.uname + "</td>" +
                        "   <td>" +
                        "       <a href='../../storage/stock/stockView.html?pid="+ rk_warehouse.rk_indent+"' class='tablelink'>查看详情</a>" +
                        "   </td>" +
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