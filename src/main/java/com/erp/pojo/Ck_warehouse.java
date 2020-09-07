package com.erp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author cheny
 * @Description  出库类
 * @Since 2020/7/16 16:50
 */
@SuppressWarnings("all")
public class Ck_warehouse {
    private int id; //出库id
    private String indent; //订单编号
    private int warehouse_id; //仓库管理表的id
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date c_date; //出库时间
    private int user_id; //出库人（登录人的id）
    private String state; //状态（1 未发货，2 已发货，3 已回款，4 取消订单，5 已退货）
    private int count; // 总数量
    private Warehouse warehouse;//仓库管理对象
    private Order order;
    private users users;


    public com.erp.pojo.users getUsers() {
        return users;
    }

    public void setUsers(com.erp.pojo.users users) {
        this.users = users;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndent() {
        return indent;
    }

    public void setIndent(String indent) {
        this.indent = indent;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public Date getC_date() {
        return c_date;
    }

    public void setC_date(Date c_date) {
        this.c_date = c_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
