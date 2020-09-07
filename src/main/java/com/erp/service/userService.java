package com.erp.service;

import com.erp.dao.userDao;
import com.erp.pojo.*;
import com.erp.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class userService {
    @Resource
    userDao dao;

    public users login(String uname,String upassword){

        return dao.userlogin(uname,upassword);
    }

    public ArrayList<permission> findper(String uname){


        permission pers=null;
        module mo=null;
        ArrayList<permission> perlist=new ArrayList<>();
        ArrayList<module> molist=null;

        users u = dao.findper(uname);
        if(u==null){
            return null;
        }
        Set<permission> list = u.getJob().getSetper();
            Set<Integer> num=new LinkedHashSet<>();


            for (permission per:list){
                int id = per.getModule().getModule_pid();
                num.add(id);
            }
            for(Integer id:num){

                pers=new permission();
                mo=new module();
                mo = dao.findmoduleurl(id);
                if(mo!=null){
                    molist=new ArrayList<>();
                    for (permission p:list){
                        if(p.getModule().getModule_pid()==id){
                            module modu=new module();
                            modu=p.getModule();
                            molist.add(modu);
                        }
                    }
                    mo.setSetmodel(molist);
                    pers.setModule(mo);
                    pers.setComrid(u.getJob_id());
                    perlist.add(pers);
                }

            }


        if(perlist==null){
            return null;
        }
        return perlist;
    }



    public module  findmoduleurl(int id){
        return dao.findmoduleurl(id);
    }

    public Page userlist(Page page, Integer id, String uname, String deptname, Integer state){
        ArrayList<users> list = dao.userlist(page.getStartIndex(),id, uname, deptname, state);
        int rowcount = dao.getEmpUserCount(id, uname, deptname, state);
        page.setResult(list,rowcount);

        return  page;
    }

    public ArrayList<dept> deptlist(){
        return dao.deptlist();
    }

    public users testUid(int uid){
     return    dao.testUid(uid);
    }
    public users testUname(String uname){
        return dao.testUname(uname);
    }

    public ArrayList<job> findjob(int id){
        return dao.findjob(id);
    }

    public int addUser(users user){
        return dao.saveUser(user.getU_id(),user.getUname(),user.getUpassword(),user.getUtelephone(),user.getDept_id(),user.getJob_id(),user.getSex(),user.getBirthday());
    }

    public int updateStatususer(Integer u_id){
        return dao.updateStatus(u_id);
    }

    public users Showuser(Integer u_id) {
        return dao.update_showuser(u_id);
    }

    public int updateuser(users user) {

        return dao.updateuser(user.getU_id(),user.getUpassword(),user.getUtelephone(),user.getDept_id(),user.getJob_id(),user.getSex(),user.getBirthday(),user.getStatus_id());
    }

    public int updateSureStatususer(Integer u_id) {
        return dao.updateSureStatus(u_id);
    }
}
