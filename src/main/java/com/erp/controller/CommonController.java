package com.erp.controller;

import com.erp.pojo.city;
import com.erp.pojo.job;
import com.erp.pojo.province;
import com.erp.pojo.users;
import com.erp.service.CommonService;
import com.erp.service.DeptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommonController {
    @Resource
    private CommonService commonService;
    @Resource
    private DeptService deptService;

    // 查找省份
    @RequestMapping("/findProvince")
    @ResponseBody
    public List<province> findProvince(){
        return commonService.findProvince();
    }

    // 查看区域
    @RequestMapping("/findCity")
    @ResponseBody
    public List<city> findCity(int id){
        return deptService.showCity(id);
    }

    // 获取组员职位
    @RequestMapping("/market/findUserJob")
    @ResponseBody
    public List<job> findUserJob(int deptId){
        return commonService.findUserJob(deptId);
    }

    // 获取经理职位
    @RequestMapping("/market/findJobUser")
    @ResponseBody
    public List<job> findJobUser(int deptId){
        return commonService.findJobUser(deptId);
    }

    // 获取指定组员姓名
    @RequestMapping("/market/findUserName")
    @ResponseBody
    public List<users> findUserName(int jobId){
        return commonService.findUserName(jobId);
    }

}
