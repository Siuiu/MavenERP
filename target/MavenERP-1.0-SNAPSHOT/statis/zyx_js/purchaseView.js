$(function () {
    var cfirm = getCfirm();
    var pageCount;
    show(cfirm);
    page(pageCount, cfirm);
    $(".subBut").click(function () {
        cfirm = getCfirm();
        show(cfirm);
        page(pageCount, cfirm);
    })

    function getCfirm() {
        var cfirm = {};
        cfirm.firm_id = getParams("cfirm_id");
        cfirm.pageStart = 1;
        cfirm.pageSize = 3;
        return cfirm;
    }

    function show(cfirm) {
        var tr = "";
        $(".tablelist tr:not(:first)").remove();
        $.ajax({
            type: "post",
            url: "purchaseViewList",
            dataType: "json",
            data: cfirm,
            async: false,
            success: function (data) {
                $.each(data.list, function (index, purchase) {
                    tr += "<tr>" +
                        "   <td>" + (index + 1) + "</td>" +
                        "   <td>" + purchase.cfirm.firm_name + "</td>" +
                        "   <td>" + purchase.id + "</td>" +
                        "   <td>" + purchase.total_count + "</td>" +
                        "   <td>￥"+purchase.money+"</td>" +
                        "   <td>" + purchase.time+"</td>" +
                        "   <td>" + purchase.name+"</td>" +
                        "   <td>" +
                        "       <a href='../../purchase/purchase/purchaseView.html?pid="+purchase.id+"' class='tablelink'>查看详情</a>" +
                        "   </td>\n" +
                        " </tr>";
                })

                pageCount = data.pages;
                $(".tablelist tbody").append(tr);
                $(".tablelist tbody tr:odd").addClass('odd');
            }
        })
    }

    function page(pages, cfirm) {

        $('.m-style').pagination({
            pageCount: pages,
            jump: true,
            coping: true,
            homePage: '首页',
            endPage: '末页',
            prevContent: '上页',
            nextContent: '下页',
            callback: function (api) {
                cfirm.pageStart = api.getCurrent();

                show(cfirm);
            }
        });
    }

})