package com.erp.pojo;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class job {
    private int Job_id;
    private String Job_name;
    private String Job_state;
    private int job_dept_id;
    private int p_id;

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    private dept dept;
    private Set<permission> setper=new LinkedHashSet<>();




    public Set<permission> getSetper() {
        return setper;
    }

    public void setSetper(Set<permission> setper) {
        this.setper = setper;
    }

    public com.erp.pojo.dept getDept() {
        return dept;
    }

    public void setDept(com.erp.pojo.dept dept) {
        this.dept = dept;
    }


    public int getJob_id() {
        return Job_id;
    }

    public void setJob_id(int job_id) {
        Job_id = job_id;
    }

    public String getJob_name() {
        return Job_name;
    }

    public void setJob_name(String job_name) {
        Job_name = job_name;
    }

    public String getJob_state() {
        return Job_state;
    }

    public void setJob_state(String job_state) {
        Job_state = job_state;
    }

    public int getJob_dept_id() {
        return job_dept_id;
    }

    public void setJob_dept_id(int job_dept_id) {
        this.job_dept_id = job_dept_id;
    }
}
