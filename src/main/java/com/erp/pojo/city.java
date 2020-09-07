package com.erp.pojo;

/*
区域表
 */
public class city {
    //市id
    private  int id;
    //省份id
    private  int p_id;
    //市名称
    private  String c_name;
    //市编号
    private  int c_number;
    //所属省
    private province pro;

    public province getPro() {
        return pro;
    }

    public void setPro(province pro) {
        this.pro = pro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public int getC_number() {
        return c_number;
    }

    public void setC_number(int c_number) {
        this.c_number = c_number;
    }
}
