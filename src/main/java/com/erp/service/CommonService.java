package com.erp.service;

import com.erp.dao.CommonDao;
import com.erp.pojo.city;
import com.erp.pojo.job;
import com.erp.pojo.province;
import com.erp.pojo.users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommonService {

    @Resource
    private CommonDao commonDao;

    // 获取省份
    public List<province> findProvince(){
        return commonDao.findProvince();
    }

    // 获取组员职位
    public List<job> findUserJob(int deptId){
        String job_name = "组员";
        return commonDao.findUserJob(deptId,job_name);
    }
    // 获取经理职位
    public List<job> findJobUser(int deptId) {
        String job_name = "经理";
        return commonDao.findUserJob(deptId,job_name);
    }

    // 获取指定组员的姓名
    public List<users> findUserName(int jobId){
        return commonDao.findUserName(jobId);
    }

}
