package com.erp.service;

import com.erp.dao.CustomerDao;
import com.erp.pojo.Custom;
import com.erp.pojo.job;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerService {
    @Resource
    private CustomerDao customerDao;

    // 查询所有客户
    public List<Custom> customList(String company,String customer,String province,String city,String status,String allocation){
        return customerDao.customerList(company,customer,province,city,status,allocation);
    }
    // 查询客户详情
    public Custom customerView(int customId){
        return customerDao.customerView(customId);
    }

    // 增加客户
    public int customAdd(Custom custom,int createId){
        return customerDao.customAdd(custom,createId);
    }

    // 修改客户
    public int customUpdate(Custom custom){
        return customerDao.customUpdate(custom);
    }

    // 修改客户状态
    public int changeStatusCustom(int customId,String sure){
        return customerDao.changeStatusCustom(customId,sure);
    }

    // 分配客户
    public int allocationCustom(int customId,int serviceId,String distractime){
        return customerDao.allocationCustom(customId,serviceId,distractime);
    }

    // 获取登录职位
    public job userPosition(int jobId){
        return customerDao.userPosition(jobId);
    }

    // 客户信息浏览
    public List<Custom> customBrowse(String company,String customer,String province,String city,String position,int serviceId){
        return customerDao.customBrowse(company,customer,province,city,position,serviceId);
    }

    // 客户重名问题
    public int repeatCustomName(String customname) {
        return customerDao.repeatCustomName(customname);
    }
}
