$(function () {

    var pageCount;
    var province;
    var data = getData();
    show(data);
    findProvince();
    page(pageCount, data);
    $(".subBut").click(function () {
        data = getData();province
        show(data);
        page(pageCount, data);
    })
    function getData() {
        var data = {};
        data.province = $("select:eq(0)").val();
        data.city = $("select:eq(1)").val();
        data.wname = $("[type=text]:eq(0)").val();
        data.startTime = $("[type=text]:eq(1)").val();
        data.endTime = $("[type=text]:eq(2)").val();
        data.pageStart = 1;
        data.pageSize = 3;
        return data;
    }
    function show() {
        var tr = "";
        $(".tablelist tr:not(:first)").remove();
        $.ajax({
            type: "post",
            url: "deliveryStatisList",
            dataType: "json",
            data: data,
            async: false,
            success: function (data) {

                $.each(data.list, function (index, ck_warehouse) {
                    tr += "<tr>" +
                        "   <td>" + (index + 1) + "</td>" +
                        "   <td>" + ck_warehouse.warehouse.c_name + "</td>" +
                        "   <td>" +  ck_warehouse.users.uname + "</td>" +
                        "   <td>" + ck_warehouse.warehouse.c_address + "</td>" +
                        "   <td>" + ck_warehouse.count + "</td>" + "  " +
                        "   <td>￥" + ck_warehouse.order.orderMoney + "</td>" +
                        "   <td>" +
                        "       <a href='deliveryView.html?c_name="+ ck_warehouse.warehouse.c_name+"' class='tablelink'>查看详情</a>" +
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
    function findProvince() {
        $("select:eq(0) option:not(:first) ").remove();
        $("select:eq(1) option:not(:first) ").remove();
        var op="";
        $.ajax({
            type: "post",
            url: "../../findProvince",
            dataType: "json",
            async:false,
            success:function (data) {
                province=data;
                $.each(data,function (index,province) {
                    op +="<option value='"+province.p_name+"' name='"+province.id+"'>"+province.p_name+"</option>";

                })
                $("select:eq(0)").append(op);
            }
        })
    }
    $(".tools").delegate("select:eq(0)","change",function () {
        $("select:eq(1) option:not(:first) ").remove();
        var provinceId = $("select:eq(0) option:selected").attr("name");
        var op1="";

        $.each(province,function (index,province1) {

            $.each(province1.setcity,function (index1,city) {
                if (city.p_id == provinceId){
                    op1+="<option value='"+city.c_name+"'>"+city.c_name+"</option>";
                }
            })

        })
        $("select:eq(1)").append(op1);

    })
})