package com.erp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据统计
 *
 * @author ZYX
 */

public class Order {
    private String orderId;  //订单编号
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderTime;   //创建时间
    private int num;     //总数量
    private Custom custom;   // 客户
    private users users;     //操作人
    private users s_users;   //审核人
    private  Double orderMoney; // 总金额
    private List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();// 订单详情
    private String status; // 审核状态
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date checTime; // 审核时间
    private String opinion;// 审核意见
    private int dd_state;// 是否出库

    public com.erp.pojo.users getS_users() {
        return s_users;
    }

    public void setS_users(com.erp.pojo.users s_users) {
        this.s_users = s_users;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public com.erp.pojo.users getUsers() {
        return users;
    }

    public void setUsers(com.erp.pojo.users users) {
        this.users = users;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Custom getCustom() {
        return custom;
    }

    public void setCustom(Custom custom) {
        this.custom = custom;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getChecTime() {
        return checTime;
    }

    public void setChecTime(Date checTime) {
        this.checTime = checTime;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public int getDd_state() {
        return dd_state;
    }

    public void setDd_state(int dd_state) {
        this.dd_state = dd_state;
    }
}
