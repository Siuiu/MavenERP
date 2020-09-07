package com.erp.pojo;

import java.util.HashSet;
import java.util.Set;

public class dept {
    private  int Dept_id;
    private  String Dept_name;
    private  String  Dept_state;
    private  int P_id;
    private  int C_id;
    private Set<job> setjob=new HashSet<job>();
    private province pro;
    private city city;

    public city getCity() {
        return city;
    }

    public void setCity(city city) {
        this.city = city;
    }

    public province getPro() {
        return pro;
    }

    public void setPro(province pro) {
        this.pro = pro;
    }

    public Set<job> getSetjob() {
        return setjob;
    }

    public void setSetjob(Set<job> setjob) {
        this.setjob = setjob;
    }




    public int getDept_id() {
        return Dept_id;
    }

    public void setDept_id(int dept_id) {
        Dept_id = dept_id;
    }

    public String getDept_name() {
        return Dept_name;
    }

    public void setDept_name(String dept_name) {
        Dept_name = dept_name;
    }

    public String getDept_state() {
        return Dept_state;
    }

    public void setDept_state(String dept_state) {
        Dept_state = dept_state;
    }

    public int getP_id() {
        return P_id;
    }

    public void setP_id(int p_id) {
        P_id = p_id;
    }

    public int getC_id() {
        return C_id;
    }

    public void setC_id(int c_id) {
        C_id = c_id;
    }
}
