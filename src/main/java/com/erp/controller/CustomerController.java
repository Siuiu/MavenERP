package com.erp.controller;

import com.erp.pojo.Custom;
import com.erp.pojo.job;
import com.erp.pojo.users;
import com.erp.service.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class CustomerController {

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    @Resource
    private CustomerService customerService;

    // 客户的基本信息
    @RequestMapping("/customerList")
    @ResponseBody
    public PageInfo<Custom> customList(int pageNum, int pageSize, String company, String customer, String province, String city, String status, String allocation){
        PageHelper.startPage(pageNum,pageSize);
        if (province != null && province != ""){
            province = province.substring(0,province.length()-1);
        }
        List<Custom> list = customerService.customList(company,customer,province,city,status,allocation);
        PageInfo<Custom> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    // 客户的详情
    @RequestMapping("/market/customerViews")
    @ResponseBody
    public Custom customerView(int customId){
        return customerService.customerView(customId);
    }

    // 获取登录的用户信息(用户名和时间)
    @RequestMapping("/market/customerUser")
    @ResponseBody
    public Map<String,String> customerUser(HttpServletRequest req){
        users user = (users)req.getSession().getAttribute("user");
        String uName = user.getUname();

        String date = df.format(new Date());// new Date()为获取当前系统时间
        Map<String,String> map = new HashMap();
        map.put("uName",uName);
        map.put("date",date);
        return map;
    }

    // 添加客户
    @RequestMapping("/market/customAdd")
    @ResponseBody
    public int customAdd(String customname, String sex, String telephone,
           String company, String address, String status, String createtime,
            String homeAddress, String distract, HttpServletRequest req){
        Date createTime = null;
        try {
            createTime = df.parse(createtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Custom custom = new Custom(customname,sex,telephone,company,address,status,createTime,homeAddress,distract);
        users user = (users)req.getSession().getAttribute("user");
        int createId = user.getU_id();
        int row = customerService.customAdd(custom,createId);
        return row;
    }

    // 修改客户
    @RequestMapping("/market/customUpdate")
    @ResponseBody
    public int customUpdate(int customId, String customname, String sex, String telephone,
                         String company, String address, String status,
                         String homeAddress, String distract){
        Custom custom = new Custom(customId,customname,sex,telephone,company,address,status,homeAddress,distract);
        int row = customerService.customUpdate(custom);
        return row;
    }

    // 修改客户状态
    @RequestMapping("/market/changesStatusCustom")
    @ResponseBody
    public int changeStatusCustom(int customId,String sure){
        int row = customerService.changeStatusCustom(customId,sure);
        return row;
    }

    // 分配客户
    @RequestMapping("/market/allocationCustom")
    @ResponseBody
    public int allocationCustom(int customId,int serviceId){

        String distractime = df.format(new Date());
        int row = customerService.allocationCustom(customId,serviceId,distractime);
        return row;
    }

    // 登录判断职位(在此判断是否含有"组员")
    @RequestMapping("/market/userPosition")
    @ResponseBody
    public int userPosition(HttpServletRequest req){
        users user = (users)req.getSession().getAttribute("user");
        // 获取职位id
        int jobId = user.getJob_id();
        job job = customerService.userPosition(jobId);
        int row = 0;
        int rowOne = job.getJob_name().indexOf("组员");
        int rowTwo = job.getJob_name().indexOf("董事长");
        int rowThree = job.getJob_name().indexOf("经理");
        if(rowOne != -1){
            row = 0;
        }
        if(rowTwo != -1){
            row = 2;
        }
        if(rowThree != -1){
            row = 1;
        }
        return row;
    }

    // 客户浏览(条件查询，分页)
    @RequestMapping("/market/customBrowse")
    @ResponseBody
    public PageInfo<Custom> customBrowse(int pageNum, int pageSize,String position, String company, String customer, String province, String city, HttpServletRequest req){
        PageHelper.startPage(pageNum,pageSize);
        if (province != null && province != ""){
            province = province.substring(0,province.length()-1);
        }
        int serviceId = 0;
        if (position.equals("组员")){
            users user = (users)req.getSession().getAttribute("user");
            serviceId = user.getU_id();
        }
        List<Custom> list = customerService.customBrowse(company,customer,province,city,position,serviceId);
        PageInfo<Custom> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    // 客户重名问题
    @RequestMapping("/market/repeatCustomName")
    @ResponseBody
    public int repeatCustomName(String customname){
        return customerService.repeatCustomName(customname);
    }
}
