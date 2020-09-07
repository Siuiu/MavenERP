package com.erp.controller;

import com.erp.pojo.*;
import com.erp.service.CommonService;
import com.erp.service.DeptService;
import com.erp.service.userService;
import com.erp.utils.Page;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class DeptController {
    @Resource
    DeptService depts;
    @Resource
    CommonService commonService;
    @Resource
    userService user;

@ResponseBody
@RequestMapping("/sys/dept/showdeptlist")
   public ArrayList<dept> deptlist(String dept_name,Integer p_id,Integer c_id){
       return depts.deptlist(dept_name,p_id,c_id);
   }


    @ResponseBody
    @RequestMapping("/sys/dept/sureR")
    public int sureR(Integer dept_id){
    return depts.updateStateSureDept(dept_id);
    }

    @ResponseBody
    @RequestMapping("/sys/dept/removeR")
    public int removeR(Integer dept_id){
        return depts.updateStateDept(dept_id);
    }

    @ResponseBody
    @RequestMapping("/sys/dept/dept_updateshowdept")
    public dept dept_updateshowdept(Integer dept_id){
        return depts.dept_updateshowdept(dept_id);
    }

    @RequestMapping("/sys/dept/findProvince")
    @ResponseBody
    public List<province> findProvince(){
        return commonService.findProvince();
    }

    @RequestMapping("/sys/dept/showcity")
    @ResponseBody
    public List<city> showCity(Integer p_id){
        return depts.showCity(p_id);
    }

    @RequestMapping("/sys/dept/addDept")
    @ResponseBody
    public int addDept(Integer dept_id, String dept_name, Integer p_id, Integer c_id){
        return depts.addDept(dept_id,dept_name,p_id,c_id);
    }

    @RequestMapping("/sys/dept/updateDept")
    @ResponseBody
    public int updateDept(Integer dept_id, String dept_name, Integer p_id, Integer c_id){
        return depts.updateDept(dept_id,dept_name,p_id,c_id);
    }

    @RequestMapping("/sys/dept/find_dept_id")
    @ResponseBody
    public int find_dept_id(Integer dept_id){
        return depts.find_dept_id(dept_id);
    }

    @RequestMapping("/sys/dept/find_dept_name")
    @ResponseBody
    public int find_dept_name(String dept_name){
        return depts.find_dept_name(dept_name);
    }

    //以下是职位模块
    @RequestMapping("/sys/dept/joblist")
    @ResponseBody
    public Page joblist(String job_name, Integer dept_id,Integer pageIndex){

        Page page = new Page(5, pageIndex);
       page= depts.joblist(page,job_name, dept_id);
    return page;
    }
    @RequestMapping("/sys/dept/deptlist")
    @ResponseBody
    public ArrayList<dept> deptlist(){
        return user.deptlist();
    }

    @RequestMapping("/sys/dept/addjob")
    @ResponseBody
    public int addjob(Integer dept_id,String job_name){
       return depts.addJob(job_name,dept_id);
    }


    @RequestMapping("/sys/dept/job_updateShowJob")
    @ResponseBody
    public job job_updateShowJob(Integer job_id){
    return depts.job_updateShowJob(job_id);
    }

    @RequestMapping("/sys/dept/jobUpdate")
    @ResponseBody
    public int jobUpdate(Integer job_id,String job_name,Integer dept_id){
        return depts.updateJob(job_id,job_name,dept_id);
    }

    @RequestMapping("/sys/dept/removeJob")
    @ResponseBody
    public int removeJob(Integer job_id){
        return depts.updateremoveJob(job_id);
    }

    @RequestMapping("/sys/dept/sureJob")
    @ResponseBody
    public int sureJob(Integer job_id){
        return depts.updatesureJob(job_id);
    }

    @RequestMapping("/sys/dept/changeJobShow")
    @ResponseBody
    public ArrayList<permission> changeJobper_Show(){
        return depts.changeJobShow();
    }

    @ResponseBody
    @RequestMapping("/sys/dept/addPer")
    public int addPer(@RequestBody ArrayList<job> joblist){
        return depts.addPerJob(joblist);
    }


    @ResponseBody
    @RequestMapping("/sys/dept/findpersion")
    public permission findpersion(Integer job_id){
        return depts.findpersion(job_id);
    }

    @ResponseBody
    @RequestMapping("/sys/dept/findwho")
    public users findwho(HttpServletRequest req){
        users u=(users) req.getSession().getAttribute("user");
        return u;
    }

}
