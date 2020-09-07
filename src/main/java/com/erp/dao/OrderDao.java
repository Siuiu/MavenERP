package com.erp.dao;

import com.erp.pojo.Custom;
import com.erp.pojo.Order;
import com.erp.pojo.OrderDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {

    // 查看所有订单
    List<Order> orderList(@Param("position") String position,
                          @Param("operatorId") int operatorId,
                          @Param("orderId") String orderId,
                          @Param("customName") String customName,
                          @Param("startTime") String startTime,
                          @Param("endTime") String endTime,
                          @Param("minMoney") Double minMoney,
                          @Param("maxMoney") Double maxMoney,
                          @Param("status") String status);

    // 判断客户是否存在
    Custom judgeCustomName(@Param("customName") String customName, @Param("serviceId") int serviceId);

    // 添加订单
    int addOrder(Order order);

    // 添加订单详情
    int addOrderDetails(OrderDetails orderDetails);

    // 修改订单
    int updateOrder(Order order);

    // 删除订单详情
    int deleteOrderDetails(String orderId);

    // 删除订单
    Integer deleteOrder(String orderId);

    // 查看订单详情
    Order viewOrder(String orderId);

    // 提交审核
    Integer auditCustom(@Param("orderId") String orderId,@Param("checkId") int checkId);

    // 审核订单
    Integer auditOrder(@Param("orderId") String orderId,@Param("opinion") String opinion,
                       @Param("status") String status,@Param("checTime") String checTime);

}
