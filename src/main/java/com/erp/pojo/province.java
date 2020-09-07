package com.erp.pojo;

import java.util.ArrayList;
import java.util.List;


/*
省份表
 */
public class province {
    //省份id
    private  int id;
    //省份名称
    private  String p_name;
    //省份编号
    private int p_number;
    // city
    private List<city> setcity = new ArrayList<>();


    public List<city> getSetcity() {
        return setcity;
    }

    public void setSetcity(List<city> setcity) {
        this.setcity = setcity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public int getP_number() {
        return p_number;
    }

    public void setP_number(int p_number) {
        this.p_number = p_number;
    }
}
