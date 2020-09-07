package com.erp.controller;

import com.erp.pojo.*;
import com.erp.service.userService;
import com.erp.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class userController {

@Resource
userService user;

@ResponseBody
@RequestMapping("/userlogin")
public  String userlogin(@RequestBody users use,HttpServletRequest req){

    users u=user.login(use.getUname(),use.getUpassword());
    if (u!=null){
        req.getSession().setAttribute("user",u);
        return "success";
    }
    else{
        return "erro";
    }
}

    @ResponseBody
    @RequestMapping("/findwhos")
    public users findwhos(HttpServletRequest req){
        users u=(users) req.getSession().getAttribute("user");
        return u;
    }

  @ResponseBody
  @RequestMapping("/per")
 public ArrayList<permission> userPermission(HttpServletRequest req){
    users u=(users) req.getSession().getAttribute("user");

      ArrayList<permission> per = user.findper(u.getUname());
        if(per==null){
            return null;
        }

    return per;
}
    @ResponseBody
    @RequestMapping("/sys/users/userlist")
 public Page userlist(Integer u_id,String uname,String dept_name,Integer status_id,int pageIndex){


        Page page = new Page(5, pageIndex);

        page= user.userlist(page,u_id, uname, dept_name, status_id);


  return  page;
}

    @ResponseBody
    @RequestMapping("/sys/users/deptlist")
    public ArrayList<dept> deptlist(){
        return   user.deptlist();
    }

    @ResponseBody
    @RequestMapping("/sys/users/testUid")
    public String  testNid(HttpServletRequest req){
        String u_id = req.getParameter("u_id");
        users u = user.testUid(Integer.parseInt(u_id));
        if(u!=null){
            return  "erro";
        }
       else{
            return  "success";
        }
    }

    @ResponseBody
    @RequestMapping("/sys/users/testUname")
    public String  testName(HttpServletRequest req){
        String uname = req.getParameter("uname");
        users u = user.testUname(uname);
        if(u!=null){
            return  "erro";
        }
        else{
            return  "success";
        }
    }
    @ResponseBody
    @RequestMapping("/sys/users/showjob")
public ArrayList<job> findjob(HttpServletRequest req){

    String deptid = req.getParameter("deptid");
        if(deptid==null){
            deptid=0+"";
        }
    ArrayList<job> list = user.findjob(Integer.parseInt(deptid));
    return list;
}

    @ResponseBody
    @RequestMapping("/sys/users/savauser")
    public int saveuser(@RequestBody users uses){
        int tamp = user.addUser(uses);
        return tamp;
    }

    @ResponseBody
    @RequestMapping("/sys/users/removeR")
    public int updateState(Integer u_id){

        int tamp = user.updateStatususer(u_id);
        return tamp;
    }
    @ResponseBody
    @RequestMapping("/sys/users/sureR")
    public int updateSureState(Integer u_id){
        int tamp = user.updateSureStatususer(u_id);
        return tamp;
    }

    @ResponseBody
    @RequestMapping("/sys/users/update_showuser")
    public users update_showuser(Integer u_id){
       users u=user.Showuser(u_id);
        return u;
    }

    @ResponseBody
    @RequestMapping("/sys/users/updateuser")
    public int updateuser(@RequestBody users uses){
        int tamp = user.updateuser(uses);
        return tamp;
    }

    @ResponseBody
    @RequestMapping("/sys/users/findwho")
    public users findwho(HttpServletRequest req){
        users u=(users) req.getSession().getAttribute("user");
        return u;
    }


}
