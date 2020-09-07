package com.erp.controller;

import com.erp.pojo.Details;
import com.erp.pojo.Purchase;
import com.erp.pojo.users;
import com.erp.service.PurchaseService;
import com.github.pagehelper.PageInfo;
import org.omg.CORBA.INTERNAL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
    /*
    条件查询与采购单分页展示
     */
    @Resource
    PurchaseService service;
    @RequestMapping("/findAllInfo")
    @ResponseBody
    public PageInfo<Purchase> findAllInfo(Integer page, String pid, String startTime, String endTime, String minPrice, String maxPrice, String status,HttpSession session){
        if("".equals(endTime))endTime=null;
        users user= (users) session.getAttribute("user");
        List<Purchase> list;
        if(user.getJob_id()==5||user.getJob_id()==21){
            list=service.findAllInfo(page, pid, startTime, endTime, minPrice, maxPrice, status,null);
        }else {
            list=service.findAllInfo(page, pid, startTime, endTime, minPrice, maxPrice, status,user.getU_id());
        }
        PageInfo<Purchase> info=new PageInfo<>(list);
        return info;
    }
    /*
    获取用户信息
     */
    @RequestMapping("/getUser")
    @ResponseBody
    public users getUser(HttpSession session){
        users user= (users) session.getAttribute("user");
        return user;
    }
    /*
    通过采购单号返回入库详情
     */
    @RequestMapping("/pidInfo")
    @ResponseBody
    public Purchase pidInfo(String pid){
        return service.pidInfo(pid);
    }
    /*
    添加采购单
     */
    @RequestMapping("/addPurchase")
    @ResponseBody
    public Integer addPurchase(@RequestBody Purchase purchase,HttpSession session){
        users user= (users) session.getAttribute("user");
        purchase.setCreatUser(user);
        List<Details> detailsList=purchase.getDetailsList();
        Double totalPrice=0.00;
        for(Details d:detailsList){
            Double sum=0.00;
            sum=d.getCount()*d.getPrice();
            d.setMoney(sum);
            d.setPid(purchase.getId());
            totalPrice+=d.getCount()*d.getPrice();
        }
        purchase.setMoney(totalPrice);
        return service.creatPurchase(purchase);
    }
    /*
    删除采购单及其明细单
     */
    @RequestMapping("/detelePurchase")
    @ResponseBody
    public Integer deletePurchase(String pid){
        return service.deletePurchase(pid);
    }
    /*
    获得所有采购部的经理   用于采购单提交审核
     */
    @RequestMapping("/findAllUsers")
    @ResponseBody
    public List<users> findAllUsers(){
        return service.findAllUsers();
    }
    /*
   采购单提交审核
    */
    @RequestMapping("/submitCheck")
    @ResponseBody
    public Integer submitCheck(Integer checkId,String pid){
        return service.submitCheck(checkId, pid);
    }
    /*
    审核中的采购单的分页与模糊查询
     */
    @RequestMapping("/findCheckAll")
    @ResponseBody
    public PageInfo<Purchase> findCheckAll(Integer page, String pid, String startTime, String endTime, String minPrice, String maxPrice,HttpSession session){
        if("".equals(endTime))endTime=null;
        users user= (users) session.getAttribute("user");
        List<Purchase> list;
        if(user.getJob_id()==21){
            list=service.findCheckAll(page, pid, startTime, endTime, minPrice, maxPrice,null);
        }else {
            list=service.findCheckAll(page, pid, startTime, endTime, minPrice, maxPrice,user.getU_id());
        }
        PageInfo<Purchase> info=new PageInfo<>(list);
        return info;
    }
    /*
    采购单的审核
     */
    @RequestMapping("/checkPurchase")
    @ResponseBody
    public Integer checkPurchase(String pid,String checkOpinion, String status){
        return service.checkPurchase(pid, checkOpinion, status);
    }
    /*
    采购单的修改
     */
    @RequestMapping("/updatePurchase")
    @ResponseBody
    public Integer updatePurchase(@RequestBody Purchase purchase){
        return service.updatePurchase(purchase);
    }
}
