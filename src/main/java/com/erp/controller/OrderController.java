package com.erp.controller;

import com.erp.pojo.Custom;
import com.erp.pojo.Order;
import com.erp.pojo.users;
import com.erp.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/market")
public class OrderController {

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    @Resource
    private OrderService orderService;

    private int customId = 0;

    // 查看所有订单
    @RequestMapping("/orderList")
    @ResponseBody
    public PageInfo<Order> orderList(int pageNum, int pageSize, String position, String orderId, String customName,
                                 String startTime, String endTime, Double minMoney, Double maxMoney, String status, HttpServletRequest req){
        PageHelper.startPage(pageNum,pageSize);
        int operatorId = 0;
        if (position.equals("组员")){
            users user = (users)req.getSession().getAttribute("user");
            operatorId = user.getU_id();
        }
        List<Order> list = orderService.orderList(position,operatorId,orderId,customName,startTime,endTime,minMoney,maxMoney,status);
        PageInfo<Order> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    // 判断添加的客户姓名是否存在
    @RequestMapping("/judgeCustomName")
    @ResponseBody
    public Custom judgeCustomName(String customName,HttpServletRequest req){
        users user = (users)req.getSession().getAttribute("user");
        int serviceId = user.getU_id();
        Custom custom = orderService.judgeCustomName(customName,serviceId);
        if (custom!=null){
            customId = custom.getCustomId();
        }
        return custom;
    }

    // 添加订单
    @RequestMapping("/addOrder")
    @ResponseBody
    public Integer addOrder(@RequestBody Order order, HttpServletRequest req){
        users user = (users)req.getSession().getAttribute("user");
        int serviceId = user.getU_id();
        order.getCustom().getUsers2().setU_id(serviceId);
        order.getCustom().setCustomId(customId);
        int row = orderService.addOrder(order);
        return row;
    }

    // 修改订单
    @RequestMapping("/updateOrder")
    @ResponseBody
    public Integer updateOrder(@RequestBody Order order){

        order.getCustom().setCustomId(customId);
        int row = orderService.updateOrder(order);
        return row;
    }

    // 删除订单
    @RequestMapping("/deleteOrder")
    @ResponseBody
    public Integer deleteOrder(String orderId){
        return orderService.deleteOrder(orderId);
    }

    // 查看订单详情
    @RequestMapping("/viewOrder")
    @ResponseBody
    public Order viewOrder(String orderId){
        return orderService.viewOrder(orderId);
    }

    // 提交审核
    @RequestMapping("/auditCustom")
    @ResponseBody
    public Integer auditCustom(String orderId,int checkId){
        return orderService.auditCustom(orderId,checkId);
    }

    // 查看所有审核订单
    @RequestMapping("/orderExamineList")
    @ResponseBody
    public PageInfo<Order> orderExamineList(int pageNum, int pageSize, String position, String orderId, String customName,
                                     String startTime, String endTime, Double minMoney, Double maxMoney, String status, HttpServletRequest req){
        PageHelper.startPage(pageNum,pageSize);
        users user = (users)req.getSession().getAttribute("user");
        int checkId = user.getU_id();
        List<Order> list = orderService.orderList(position,checkId,orderId,customName,startTime,endTime,minMoney,maxMoney,status);
        PageInfo<Order> pageInfoTwo = new PageInfo<>(list);
        return pageInfoTwo;
    }

    // 审核订单
    @RequestMapping("auditTrueOrder")
    @ResponseBody
    public Integer auditOrder(String orderId,String opinion,String status){
        String checTime = df.format(new Date());
        return orderService.auditOrder(orderId,opinion,status,checTime);
    }

}
