
package com.erp.controller;

import com.erp.pojo.*;
import com.erp.service.StatisService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


/**
 * 数据统计
 *
 * @author ZYX
 */

@Controller
@RequestMapping("/statis")
public class StatisController {
    @Resource
    private StatisService statisService;

    @RequestMapping("/sales/salesStatisList")
    @ResponseBody
    public PageInfo<Order> findSalesStatis(String province,
                                           String city,
                                           String customName,
                                           String startTime,
                                           String endTime,
                                           Integer pageStart,
                                           Integer pageSize) {
        PageHelper.startPage(pageStart, pageSize);
        List<Order> list = statisService.findSalesStatis(province, city, customName, startTime, endTime);
        PageInfo<Order> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @RequestMapping("/sales/salesViewList")
    @ResponseBody
    public PageInfo<Order> findSalesView(String uname,
                                         Integer pageStart,
                                         Integer pageSize) {
        PageHelper.startPage(pageStart, pageSize);
        List<Order> list = statisService.findSalesView(uname);
        PageInfo<Order> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @RequestMapping("/purchase/purchaseStatisList")
    @ResponseBody
    public PageInfo<Purchase> findPurchaseStatis(String province,
                                                 String city,
                                                 String cfirmname,
                                                 String startTime,
                                                 String endTime,
                                                 Integer pageStart,
                                                 Integer pageSize) {
        PageHelper.startPage(pageStart, pageSize);
        List<Purchase> list = statisService.findPurchaseStatis(province, city, cfirmname, startTime, endTime);
        PageInfo<Purchase> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @RequestMapping("/purchase/purchaseViewList")
    @ResponseBody
    public PageInfo<Purchase> findPurchaseView(String firm_id,
                                               Integer pageStart,
                                               Integer pageSize) {
        PageHelper.startPage(pageStart, pageSize);
        List<Purchase> list = statisService.findPurchaseView(firm_id);
        PageInfo<Purchase> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @RequestMapping("/findPurchaseViewOne")
    @ResponseBody
    public Purchase findPurchase(String pid) {
        Purchase purchase = statisService.findPurchase(pid);

        return purchase;
    }
    @RequestMapping("/delivery/deliveryStatisList")
    @ResponseBody
    public  PageInfo<Ck_warehouse> findDeliveryStatis(String province,
                                                 String city,
                                                 String wname,
                                                 String startTime,
                                                 String endTime,
                                                 Integer pageStart,
                                                 Integer pageSize) {
        PageHelper.startPage(pageStart,pageSize);
        List<Ck_warehouse> list = statisService.findDeliveryStatis(province,city,wname,startTime,endTime);
        PageInfo<Ck_warehouse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    @RequestMapping("/delivery/deliveryViewList")
    @ResponseBody
    public PageInfo<Ck_warehouse> findDeliveryView(String c_name,
                                               Integer pageStart,
                                               Integer pageSize) {
        PageHelper.startPage(pageStart, pageSize);
        List<Ck_warehouse> list = statisService.findDeliveryView(c_name);
        PageInfo<Ck_warehouse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    @RequestMapping("/stock/stockStatisList")
    @ResponseBody
    public  PageInfo<Rk_warehouse> findStockStatis(String province,
                                                      String city,
                                                      String wname,
                                                      String startTime,
                                                      String endTime,
                                                      Integer pageStart,
                                                      Integer pageSize) {
        PageHelper.startPage(pageStart,pageSize);
        List<Rk_warehouse> list = statisService.findStockStatis(province,city,wname,startTime,endTime);
        PageInfo<Rk_warehouse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    @RequestMapping("/stock/stockViewList")
    @ResponseBody
    public PageInfo<Ck_warehouse> findStockView(String c_name,
                                                   Integer pageStart,
                                                   Integer pageSize) {
        PageHelper.startPage(pageStart, pageSize);
        List<Ck_warehouse> list = statisService.findStockView(c_name);
        PageInfo<Ck_warehouse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    @RequestMapping("/charts/findData")
    @ResponseBody
    public TotalData findData(){
        TotalData totalData = statisService.findData();
        return totalData;
    }
    @RequestMapping("/charts/findDataOne")
    @ResponseBody
    public List<TotalData> findDataOne(String tab){

        List<TotalData> list = statisService.findDataOne(tab);

        return list;
    }
}

