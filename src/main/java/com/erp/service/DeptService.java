package com.erp.service;


import com.erp.dao.DeptDao;
import com.erp.dao.userDao;
import com.erp.pojo.*;
import com.erp.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class DeptService {
    @Resource
    DeptDao dao;
    @Resource
    userDao user;

    public ArrayList<dept> deptlist(String  dept_name,Integer p_id,Integer c_id) {
       return dao.deptlist(dept_name,p_id,c_id);
    }

    public int updateStateSureDept(Integer dept_id) {
        return dao.sureR(dept_id);
    }

    public dept dept_updateshowdept(Integer dept_id) {
        return dao.dept_updateshowdept(dept_id);
    }

    public List<city> showCity(Integer p_id) {
        return dao.showCity(p_id);
    }

    public int addDept(Integer dept_id, String dept_name, Integer p_id, Integer c_id) {
        return dao.addDept(dept_id,dept_name,p_id,c_id);
    }

    public int updateDept(Integer dept_id, String dept_name, Integer p_id, Integer c_id) {
        return dao.updateDept(dept_id,dept_name,p_id,c_id);
    }

    public int updateStateDept(Integer dept_id) {
        return dao.removeR(dept_id);
    }

    public Page joblist(Page page, String job_name, Integer dept_id) {
        ArrayList<job> list = dao.joblist(page.getStartIndex(),job_name, dept_id);
        int rowCount= dao.getjobCount(job_name, dept_id);
        page.setResult(list,rowCount);
        return page;
    }



    public int addJob(String job_name, Integer dept_id) {
        return dao.addJob(job_name,dept_id);
    }

    public job job_updateShowJob(Integer job_id) {
        return dao.job_updateShowJob(job_id);
    }

    public int updateJob(Integer job_id, String job_name, Integer dept_id) {
        return dao.jobUpdate(job_id,job_name,dept_id);
    }

    public int updateremoveJob(Integer job_id) {
        return dao.removeJob(job_id);
    }

    public int updatesureJob(Integer job_id) {
        return dao.sureJob(job_id);
    }

    public ArrayList<module> findAllSunModule(Integer p_id){
        return dao.findAllSunModule(p_id);
    }

    public ArrayList<permission> changeJobShow() {
        permission pers=null;
        module mo=null;
        ArrayList<permission> perlist=new ArrayList<>();
        ArrayList<module> molist=null;


        ArrayList<module> modules = dao.changeJobper_Show();
        Set<Integer> num=new LinkedHashSet<>();
        for (module moa :modules){
            int id = moa.getModule_id();
            num.add(id);
        }
        for(Integer id:num){

            pers=new permission();
            mo=new module();
            mo = user.findmoduleurl(id);
            molist=new ArrayList<>();
            molist=dao.findAllSunModule(id);
            mo.setSetmodel(molist);
            pers.setModule(mo);
            perlist.add(pers);
        }

        return perlist;
    }

    public int addPerJob(ArrayList<job> joblist) {
        job jobs = joblist.get(0);
       int flag=dao.delePer(jobs.getJob_id());
        int tamp=0;
        for (job job:joblist){
          tamp= dao.addPer(job.getJob_id(),job.getP_id());
        }


        return tamp;
    }

    public int find_dept_id(Integer dept_id) {
        return dao.find_dept_id(dept_id);
    }

    public int find_dept_name(String dept_name) {
        return  dao.find_dept_name(dept_name);
    }

    public permission findpersion(Integer job_id) {
        return dao.findpersion(job_id);
    }
}
