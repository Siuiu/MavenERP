$(function () {
    var pageCount;
    var province;
    var custom = getCustom();
    show(custom);
    findProvince();
    page(pageCount, custom);
    $(".subBut").click(function () {
        custom = getCustom();province
        show(custom);
        page(pageCount, custom);
    })

    function getCustom() {
        var custom = {};
        custom.province = $("select:eq(0)").val();
        custom.city = $("select:eq(1)").val();
        custom.customName = $("[type=text]:eq(0)").val();
        custom.startTime = $("[type=text]:eq(1)").val();
        custom.endTime = $("[type=text]:eq(2)").val();
        custom.pageStart = 1;
        custom.pageSize = 3;
        return custom;
    }

    function show(custom) {
        var tr = "";
        $(".tablelist tr:not(:first)").remove();
        $.ajax({
            type: "post",
            url: "salesStatisList",
            dataType: "json",
            data: custom,
            async: false,
            success: function (data) {

                $.each(data.list, function (index, order) {
                    tr += "<tr>" +
                        "   <td>" + (index + 1) + "</td>" +
                        "   <td>" + order.custom.customname + "</td>" +
                        "   <td>" + order.custom.address + "</td>" +
                        "   <td>" + order.num + "</td>" +
                        "   <td>￥" + order.orderMoney + "</td>" +
                        "   <td>" +
                        "       <a href='salesView.html?uname=" + order.custom.customname + "' class='tablelink'>查看详情</a>" +
                        "   </td>\n" +
                        " </tr>";
                })

                pageCount = data.pages;
                $(".tablelist tbody").append(tr);
                $(".tablelist tbody tr:odd").addClass('odd');
            }
        })
    }

    function page(pages, custom) {

        $('.m-style').pagination({
            pageCount: pages,
            jump: true,
            coping: true,
            homePage: '首页',
            endPage: '末页',
            prevContent: '上页',
            nextContent: '下页',
            callback: function (api) {
                custom.pageStart = api.getCurrent();

                show(custom);
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