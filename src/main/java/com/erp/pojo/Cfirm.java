package com.erp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Cfirm {
    //厂商id
    private Integer firm_id;
    //厂商名称
    private String firm_name;
    //厂商负责人
    private String firm_founder;
    //厂商联系电话
    private String firm_tel;
    //厂商地址
    private  String firm_address;
    //厂商所属区域
    private String firm_city;
    //厂商描述
    private String firm_content;
    //厂商状态
    private  Integer status;
    //厂商创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date firm_date;
    //厂商创建人
    private String uname;
    //厂商所在省
    private String p_name;
    //厂商所在市
    private String c_name;
    private String id;

    public String getId() {
        return id;
    }

    public String getFirm_address() {
        return firm_address;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public void setFirm_address(String firm_address) {
        this.firm_address = firm_address;
    }

    public String getFirm_content() {
        return firm_content;
    }

    public void setFirm_content(String firm_content) {
        this.firm_content = firm_content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getFirm_id() {
        return firm_id;
    }

    public String getFirm_tel() {
        return firm_tel;
    }

    public void setFirm_tel(String firm_tel) {
        this.firm_tel = firm_tel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getFirm_date() {
        return firm_date;
    }

    public void setFirm_date(Date firm_date) {
        this.firm_date = firm_date;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setFirm_id(Integer firm_id) {
        this.firm_id = firm_id;
    }

    public String getFirm_name() {
        return firm_name;
    }

    public void setFirm_name(String firm_name) {
        this.firm_name = firm_name;
    }

    public String getFirm_founder() {
        return firm_founder;
    }

    public void setFirm_founder(String firm_founder) {
        this.firm_founder = firm_founder;
    }

    public String getFirm_city() {
        return firm_city;
    }

    public void setFirm_city(String firm_city) {
        this.firm_city = firm_city;
    }
}
