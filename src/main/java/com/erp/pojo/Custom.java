package com.erp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 *   客户类
 *   @author ZYX
 *
 * */
public class Custom {
    private int customId;   //客户id
    private String customname;  //客户名称
    private String sex;         //性别
    private String telephone;   //客户联系电话
    private String company;     //所属公司
    private String address;     //所属区域
    private String status; // 状态
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime; // 创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date distracTime; // 分配时间
    private String homeAddress; // 家庭地址
    private String distract; // 描述
    private users users1; // 用户表(员工)
    private users users2; // 用户表(员工)
    private List<Order> orderList  = new ArrayList<>();

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Custom() {
    }

    public Custom(int customId, String customname, String sex, String telephone, String company, String address, String status, String homeAddress, String distract) {
        this.customId = customId;
        this.customname = customname;
        this.sex = sex;
        this.telephone = telephone;
        this.company = company;
        this.address = address;
        this.status = status;
        this.homeAddress = homeAddress;
        this.distract = distract;
    }

    public Custom(String customname, String sex, String telephone, String company, String address, String status, Date createTime, String homeAddress, String distract) {
        this.customname = customname;
        this.sex = sex;
        this.telephone = telephone;
        this.company = company;
        this.address = address;
        this.status = status;
        this.createTime = createTime;
        this.homeAddress = homeAddress;
        this.distract = distract;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCustomId() {
        return customId;
    }

    public void setCustomId(int customId) {
        this.customId = customId;
    }

    public String getCustomname() {
        return customname;
    }

    public void setCustomname(String customname) {
        this.customname = customname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDistracTime() {
        return distracTime;
    }

    public void setDistracTime(Date distracTime) {
        this.distracTime = distracTime;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getDistract() {
        return distract;
    }

    public void setDistract(String distract) {
        this.distract = distract;
    }

    public users getUsers1() {
        return users1;
    }

    public void setUsers1(users users1) {
        this.users1 = users1;
    }

    public users getUsers2() {
        return users2;
    }

    public void setUsers2(users users2) {
        this.users2 = users2;
    }
}
