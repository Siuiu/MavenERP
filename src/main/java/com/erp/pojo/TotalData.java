package com.erp.pojo;

public class TotalData {
    private int orderNum;
    private int purchaseNum;
    private int ckNum;
    private int rkNum;
    private double money;
    private String name;
    private String id;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(int purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public int getCkNum() {
        return ckNum;
    }

    public void setCkNum(int ckNum) {
        this.ckNum = ckNum;
    }

    public int getRkNum() {
        return rkNum;
    }

    public void setRkNum(int rkNum) {
        this.rkNum = rkNum;
    }
}
