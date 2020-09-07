package com.erp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//采购单类
public class Purchase {
    //采购单id
    private String id;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
    //创建时间  专用于方便添加采购单
    private String creatTime;
    //创建人姓名
    private String name;
    //创建人id
    private users creatUser;
    //状态
    private String status;
    //审核人姓名
    private String checkname;
    //审核时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date checktime;
    //总金额
    private Double money;
    // 总数量
    private int total_count;
    // 厂商
    private Cfirm cfirm;
    //审核意见
    private String checkOpinion;
    //采购单明细
    private List<Details> detailsList=new ArrayList<Details>();
    //所在仓库
    private Warehouse warehouse;;
    //是否入库
    private Integer cgStatus;

    public Integer getCgStatus() {
        return cgStatus;
    }

    public void setCgStatus(Integer cgStatus) {
        this.cgStatus = cgStatus;
    }

    public users getCreatUser() {
        return creatUser;
    }

    public void setCreatUser(users creatUser) {
        this.creatUser = creatUser;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public List<Details> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<Details> detailsList) {
        this.detailsList = detailsList;
    }

    public String getCheckOpinion() {
        return checkOpinion;
    }

    public void setCheckOpinion(String checkOpinion) {
        this.checkOpinion = checkOpinion;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public Cfirm getCfirm() {
        return cfirm;
    }

    public void setCfirm(Cfirm cfirm) {
        this.cfirm = cfirm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheckname() {
        return checkname;
    }

    public void setCheckname(String checkname) {
        this.checkname = checkname;
    }

    public Date getChecktime() {
        return checktime;
    }

    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", creatTime='" + creatTime + '\'' +
                ", name='" + name + '\'' +
                ", creatUser=" + creatUser +
                ", status='" + status + '\'' +
                ", checkname='" + checkname + '\'' +
                ", checktime=" + checktime +
                ", money=" + money +
                ", total_count=" + total_count +
                ", cfirm=" + cfirm +
                ", checkOpinion='" + checkOpinion + '\'' +
                ", detailsList=" + detailsList +
                ", warehouse=" + warehouse +
                '}';
    }
}
