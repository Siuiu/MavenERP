$(function () {
    var pid = getParams("pid");
    $(".tablelist tr:not(:first)").remove();
    $(".forminfo li").remove();
    var tr="";
    var li="";
    $.ajax({
        type:"post",
        url:"../../statis/findPurchaseViewOne",
        dataType:"json",
        data:{"pid":pid},
        success:function (data) {
            if(data.checkOpinion==null)data.checkOpinion=""
            if(data.checkname==null)data.checkname=""
            if(data.checktime==null)data.checktime=""
            li ="    <li>" +
                "      <label>采购单编号</label>" +
                "      <cite>"+data.id+"</cite>" +
                "    </li>" +
                "    <li>" +
                "      <label>订购时间</label>" +
                "      <cite>"+data.time+"</cite>" +
                "    </li>" +
                "    <li>" +
                "      <label>总金额</label>" +
                "       <cite>￥"+data.money+"</cite>" +
                "    </li>" +
                "    <li>" +
                "      <label>操作人</label>" +
                "      <cite>"+data.name+"</cite>" +
                "    </li>" +
                "    <li>" +
                "      <label>审核状态</label>" +
                "      <cite>"+data.status+"</cite>" +
                "    </li>" +
                "    <li>" +
                "      <label>审核意见</label>\n" +
                "      <cite>"+data.checkOpinion+"</cite>\n" +
                "    </li>" +
                "    <li>" +
                "      <label>审核人</label>" +
                "      <cite>"+data.checkname+"</cite>" +
                "    </li>" +
                "    <li>" +
                "      <label>审核时间</label>" +
                "      <cite>"+data.checktime+"</cite>" +
                "    </li>"

            $.each(data.detailsList,function (index,detailsList) {
                tr += "<tr>" +
                    "   <td>" + (index + 1) + "</td>" +
                    "   <td>" + detailsList.product.productType.brand.name + "</td>" +
                    "   <td>" + detailsList.product.productType.name + "</td>" +
                    "   <td>" + detailsList.product.name + "</td>" +
                    "   <td>" + detailsList.product.cfirm.firm_name + "</td>" +
                    "   <td>" + detailsList.count + "</td>" +
                    "   <td>" + detailsList.product.unit + "</td>" +
                    "   <td>￥" + detailsList.price + "</td>" +
                    "   <td>￥" + detailsList.money + "</td>" +
                    " </tr>";
            })
            $(".forminfo ").append(li);
            $(".tablelist tbody").append(tr);
            $(".tablelist tbody tr:odd").addClass('odd');
        }
    })
})