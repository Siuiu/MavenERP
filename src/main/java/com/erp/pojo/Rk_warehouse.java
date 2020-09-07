package com.erp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author cheny
 * @Description  入库类
 * @Since 2020/7/16 17:03
 */
@SuppressWarnings("all")
public class Rk_warehouse {
    private int id; //入库信息的id
    private String rk_indent; //采购单编号
    private int warehouse_id; //仓库管理表的id
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date rk_date; //入库时间
    private int user_id; //出库人（登录人的id）
    private String state; //状态（1 未入库，2 已入库）
    private int count ;// 总数量
    private Purchase purchase; //采购单
    private Warehouse warehouse; //仓库管理对象
    private users users; //users对象

    public com.erp.pojo.users getUsers() {
        return users;
    }

    public void setUsers(com.erp.pojo.users users) {
        this.users = users;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRk_indent() {
        return rk_indent;
    }

    public void setRk_indent(String rk_indent) {
        this.rk_indent = rk_indent;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public Date getRk_date() {
        return rk_date;
    }

    public void setRk_date(Date rk_date) {
        this.rk_date = rk_date;
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
