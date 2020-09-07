$(function () {
    var cfirm = getCfirm();
    var pageCount;
    var province;
    show(cfirm);
    findProvince();
    page(pageCount, cfirm);
    $(".subBut").click(function () {
        cfirm = getCfirm();
        show(cfirm);
         page(pageCount, cfirm);
    })

    function getCfirm() {
        var cfirm = {};
        cfirm.province = $("select:eq(0)").val();
        cfirm.city = $("select:eq(1)").val();
        cfirm.cfirmname = $("[type=text]:eq(0)").val();
        cfirm.startTime = $("[type=text]:eq(1)").val();
        cfirm.endTime = $("[type=text]:eq(2)").val();
        cfirm.pageStart = 1;
        cfirm.pageSize = 3;
        return cfirm;
    }

    function show(cfirm) {
        var tr = "";
        $(".tablelist tr:not(:first)").remove();
        $.ajax({
            type: "post",
            url: "purchaseStatisList",
            dataType: "json",
            data: cfirm,
            async: false,
            success: function (data) {
                $.each(data.list, function (index, purchase) {
                    tr += "<tr>" +
                        "   <td>" + (index + 1) + "</td>" +
                        "   <td>" + purchase.cfirm.firm_name + "</td>" +
                        "   <td>" + purchase.cfirm.firm_founder + "</td>" +
                        "   <td>" + purchase.cfirm.firm_city+"</td>" +
                        "   <td>" + purchase.total_count + "</td>" +
                        "   <td>￥"+purchase.money+"</td>" +
                        "   <td>" +
                        "       <a href='purchaseView.html?cfirm_id="+ purchase.cfirm.id+"' class='tablelink'>查看详情</a>" +
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