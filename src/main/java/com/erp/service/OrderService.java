package com.erp.service;

import com.erp.dao.OrderDao;
import com.erp.pojo.Custom;
import com.erp.pojo.Order;
import com.erp.pojo.OrderDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService {

    @Resource
    private OrderDao orderDao;

    // 查看所有订单
    public List<Order> orderList(String position, int operatorId, String orderId, String customName, String startTime, String endTime, Double minMoney, Double maxMoney, String status){
        return orderDao.orderList(position,operatorId,orderId,customName,startTime,endTime,minMoney,maxMoney,status);
    }

    // 判断添加的客户姓名是否存在
    public Custom judgeCustomName(String customName,int serviceId) {
        return orderDao.judgeCustomName(customName,serviceId);
    }

    // 添加订单(订单和订单详情)
    public int addOrder(Order order) {
        int rowOne = orderDao.addOrder(order);

        List<OrderDetails> orderDetailsList = order.getOrderDetails();
        int rowTwo = 0;
        for (OrderDetails orderDetails :orderDetailsList ) {
            int row = orderDao.addOrderDetails(orderDetails);
            rowTwo += row;
        }

        if ((rowOne + rowTwo) >=2){
            return 1;
        }else {
            return 0;
        }
    }

    // 修改订单
    public int updateOrder(Order order) {
        // 修改订单信息
        int rowOne = orderDao.updateOrder(order);
        // 删除原有订单详情
        String orderId = order.getOrderId();
        int rowThree = orderDao.deleteOrderDetails(orderId);

        // 添加新的订单详情
        List<OrderDetails> orderDetailsList = order.getOrderDetails();
        int rowTwo = 0;
        for (OrderDetails orderDetails :orderDetailsList ) {
            int row = orderDao.addOrderDetails(orderDetails);
            rowTwo += row;
        }

        if ((rowOne + rowTwo + rowThree) >=3){
            return 1;
        }else {
            return 0;
        }
    }

    // 删除订单
    public Integer deleteOrder(String orderId) {
        return orderDao.deleteOrder(orderId);
    }

    // 查看订单详情
    public Order viewOrder(String orderId) {
        return orderDao.viewOrder(orderId);
    }

    // 提交审核
    public Integer auditCustom(String orderId, int checkId) {
        return orderDao.auditCustom(orderId,checkId);
    }

    // 审核订单
    public Integer auditOrder(String orderId, String opinion, String status, String checTime) {
        return orderDao.auditOrder(orderId,opinion,status,checTime);
    }
}
