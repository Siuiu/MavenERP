package com.erp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author cheny
 * @Description  仓库管理类
 * @Since 2020/7/16 15:45
 */
@SuppressWarnings("all")
public class Warehouse {
    private int id; //仓库id
    private String c_name; //仓库名称
    private String c_address; //仓库地址
    private int province_id; //仓库区域（省）
    private int usre_id; //负责人id（财务部的员工）
    private String phone; //联系方式
    private String details; //描述
    private String state; //状态
    private int user_id; //创建人id
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date creation_time; //创建时间
    private int city_id; //市id
    private String name;//入库人
    private users users;
    private users user;
    private com.erp.pojo.province province;
    private city city;

    public com.erp.pojo.province getProvince() {
        return province;
    }

    public void setProvince(com.erp.pojo.province province) {
        this.province = province;
    }

    public com.erp.pojo.city getCity() {
        return city;
    }

    public void setCity(com.erp.pojo.city city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public com.erp.pojo.users getUser() {
        return user;
    }

    public void setUser(com.erp.pojo.users user) {
        this.user = user;
    }

    public com.erp.pojo.users getUsers() {
        return users;
    }

    public void setUsers(com.erp.pojo.users users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_address() {
        return c_address;
    }

    public void setC_address(String c_address) {
        this.c_address = c_address;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public int getUsre_id() {
        return usre_id;
    }

    public void setUsre_id(int usre_id) {
        this.usre_id = usre_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Date creation_time) {
        this.creation_time = creation_time;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }
}
