package com.erp.service;

import com.erp.dao.PurchaseDao;
import com.erp.pojo.Brand;
import com.erp.pojo.Details;
import com.erp.pojo.Purchase;
import com.erp.pojo.users;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/*
采购单
 */
@Service
public class PurchaseService {
    @Resource
    private PurchaseDao dao;
    //采购单所有信息
    public List<Purchase> findAllInfo(Integer page,String pid, String startTime, String endTime, String minPrice, String maxPrice, String status,Integer uid){
        PageHelper.startPage(page,5,"p.purchase_time DESC");
        return dao.findAllInfo(pid,startTime,endTime,minPrice,maxPrice,status,uid);
    }
    //通过采购单id查询采购单信息
    public Purchase pidInfo(String pid){
        return dao.pidInfo(pid);
    }
    //创建采购单
    public Integer creatPurchase(Purchase purchase){
        Integer statusP=dao.creatPurchase(purchase);
        Integer statusD=dao.creatDetails(purchase.getDetailsList());
        if(statusD==0||statusP==0) statusP=0;
        return statusP;
    }
    //删除采购单与相关的明细单
    public Integer deletePurchase(String pid){
        return dao.deletePurchase(pid);
    }
    //查询采购部所有的经理
    public List<users> findAllUsers(){
        return dao.findAllUsers();
    }
    //提交审核
    public Integer submitCheck(Integer checkId,String pid){
        return dao.submitCheck(checkId, pid);
    }
    //审核采购单的分页和模糊查询
    public List<Purchase> findCheckAll(Integer page,String pid, String startTime, String endTime, String minPrice, String maxPrice,Integer checkId){
        PageHelper.startPage(page,5,"p.purchase_time DESC");
        return dao.findCheckAll(pid, startTime, endTime, minPrice, maxPrice,checkId);
    };
    //采购单的审核
    public Integer checkPurchase(String pid,String checkOpinion,String status){
        return dao.checkPurchase(pid, checkOpinion, status);
    }
    //采购单的修改
    public Integer updatePurchase(Purchase purchase){
        //删除旧采购单明细单
        Integer deleteDetails=dao.deleteDetails(purchase.getId());
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
        //修改后的采购单明细
        Integer statusD=dao.creatDetails(purchase.getDetailsList());
        //修改采购单的总金额
        Integer updatePurchaseTotalPrice=dao.updatePurchaseTotalPrice(purchase.getId(),totalPrice);
        if(updatePurchaseTotalPrice<=0||deleteDetails<=0||statusD<=0){
            return 0;
        }
        return 1;
    }
}
