
$(function () {
    var myChart = echarts.init($("#main")[0]);
    var myChart1 = echarts.init($("#main_two")[0]);
   // var myChart1 = echarts.init($("#main_two")[0]);
    var data;
    getData();
    var option = shanXing(data) ;   //扇形
    //var option1 = zheXian() ;   //折线

    myChart.setOption(option);

    myChart.on('click', function () {
        var color = "";
        var tab="";
        $(".tablelist tr:not(:first)").remove();
        var text = $("#main div:eq(1) ").contents()[3].data.substring(0,2);
        if (text == "销量"){
            color="#3CB2EF";
            tab = "order";
            $(".tablelist th:eq(1)").html("订单号");
            $(".tablelist th:eq(2)").html("客户姓名");
        }else if(text == "采购"){
            color="#67E0E3";
            tab = "purchase";
            $(".tablelist th:eq(1)").html("采购单号");
            $(".tablelist th:eq(2)").html("厂商名称");
        }else if(text == "出库"){
            color="#FFDB5C";
            tab = "ck";
            $(".tablelist th:eq(1)").html("订单号");
            $(".tablelist th:eq(2)").html("仓库名称");
        }else {
            color="#FF9F7F";
            tab = "rk";
            $(".tablelist th:eq(1)").html("采购单号");
            $(".tablelist th:eq(2)").html("仓库名称");
        }
       var money =  getDataOne(tab);
        myChart1.clear();
        myChart1 = echarts.init($("#main_two")[0]);
        var option1 = zheXian(color,money) ;
        myChart1.setOption(option1,true);
    });

    function shanXing(data) {
        var option = {
            //legend: {},
            tooltip: {},
            color:['#3CB2EF','#67E0E3','#FFDB5C','#FF9F7F'],
            dataset: {
                source: [
                    ['数量', ''],
                    ['销量', data.orderNum],
                    ['采购', data.purchaseNum],
                    ['出库', data.ckNum],
                    ['入库', data.rkNum]
                ]
            },
            series: [{
                type: 'pie',
                radius: 120,
                center: ['50%', '50%']
                // No encode specified, by default, it is '2012'.
            }]
        };
        return option;
    }
    function zheXian(color,data) {
        var option1 = {
            tooltip: {},
            title: {
                text: '近五笔交易金额'
            },
            color:[color],
            xAxis: {
                type: 'category',
                data: ['第1笔', '第2笔', '第3笔', '第4笔', '第5笔']
            },
            yAxis: {
                type: 'value',
                /*axisLabel:{
                        formatter:'{value}(元)'
                }*/
            },
            series: [{
                data: data,
                name:'金额(元)',
                type: 'line'
            }]
        };
        return option1;
    }
    function getData() {
        $.ajax({
            type:"post",
            url:"findData",
            dataType: "json",
            async:false,
            success:function (d) {
                data = d;
            }
        })
    }
    function getDataOne(tab) {
        var action={};
        action.tab = tab;
        var money=new Array();
        var tr="";
        $(".tablelist tr:not(:first)").remove();
        $.ajax({
            type:"post",
            url:"findDataOne",
            dataType: "json",
            data:action,
            async:false,
            success:function (d) {
                $.each(d,function (index,a) {
                    money[index] = a.money;
                    tr += "<tr>" +
                        "   <td>" + (index + 1) + "</td>" +
                        "   <td>" + a.id + "</td>" +
                        "   <td>" + a.name + "</td>" +
                        "   <td>" + a.address + "</td>" +
                        "   <td>￥" + a.money + "</td>" +
                        " </tr>";
                })
                $(".tablelist tbody").append(tr);
                $(".tablelist tbody tr:odd").addClass('odd');
            }
        })
        return money;
    }


})