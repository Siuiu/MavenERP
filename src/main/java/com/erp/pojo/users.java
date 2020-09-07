package com.erp.pojo;

import java.util.Objects;

public class users {
    private  int u_id;
    private  String uname;
    private String upassword;
    private  String utelephone;
    private int Dept_id;
    private int job_id;
    private String sex;
    private int Status_id;
    private String hiredate;
    private String leavedate;
    private String birthday;
    private dept dept;
    private job job;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        users users = (users) o;
        return u_id == users.u_id &&
                Dept_id == users.Dept_id &&
                job_id == users.job_id &&
                Status_id == users.Status_id &&
                Objects.equals(uname, users.uname) &&
                Objects.equals(upassword, users.upassword) &&
                Objects.equals(utelephone, users.utelephone) &&
                Objects.equals(sex, users.sex) &&
                Objects.equals(hiredate, users.hiredate) &&
                Objects.equals(leavedate, users.leavedate) &&
                Objects.equals(birthday, users.birthday) &&
                Objects.equals(dept, users.dept) &&
                Objects.equals(job, users.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(u_id, uname, upassword, utelephone, Dept_id, job_id, sex, Status_id, hiredate, leavedate, birthday, dept, job);
    }

    public com.erp.pojo.dept getDept() {
        return dept;
    }

    public void setDept(com.erp.pojo.dept dept) {
        this.dept = dept;
    }

    public com.erp.pojo.job getJob() {
        return job;
    }

    public void setJob(com.erp.pojo.job job) {
        this.job = job;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUtelephone() {
        return utelephone;
    }

    public void setUtelephone(String utelephone) {
        this.utelephone = utelephone;
    }

    public int getDept_id() {
        return Dept_id;
    }

    public void setDept_id(int dept_id) {
        Dept_id = dept_id;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getStatus_id() {
        return Status_id;
    }

    public void setStatus_id(int status_id) {
        Status_id = status_id;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(String leavedate) {
        this.leavedate = leavedate;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
