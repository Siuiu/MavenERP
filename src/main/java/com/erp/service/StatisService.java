package com.erp.service;

import com.erp.dao.StatisDao;
import com.erp.pojo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 数据统计Service
 * @author ZYX
 *
 */
@Service
public class StatisService {
    @Resource
    private StatisDao statisDao;
    public List<Order> findSalesStatis(String province, String city, String customName, String startTime, String endTime){
        List<Order> list = statisDao.findSalesStatis(province,city,customName,startTime,endTime);
        return list;
    }

    public List<Order> findSalesView(String uname) {
        List<Order> list = statisDao.findSalesView(uname);
        return list;
    }


    public List<Purchase> findPurchaseStatis(String province, String city, String cfirmname, String startTime, String endTime) {
        List<Purchase> list = statisDao.findPurchaseStatis(province,city,cfirmname,startTime,endTime);
        return list;
    }

    public List<Purchase> findPurchaseView(String firm_id) {
        List<Purchase> list = statisDao.findPurchaseView(firm_id);
        return list;
    }

    public Purchase findPurchase(String pid) {
        Purchase purchase = statisDao.findPurchase(pid);

        return purchase;
    }

    public List<Ck_warehouse> findDeliveryStatis(String province, String city, String wname, String startTime, String endTime) {
        List<Ck_warehouse> list = statisDao.findDeliveryStatis(province,city,wname,startTime,endTime);
        return list;
    }

    public List<Ck_warehouse> findDeliveryView(String c_name) {
        List<Ck_warehouse> list = statisDao.findDeliveryView(c_name);
        return list;
    }

    public List<Rk_warehouse> findStockStatis(String province, String city, String wname, String startTime, String endTime) {
        List<Rk_warehouse> list = statisDao.findStockStatis(province,city,wname,startTime,endTime);
        return list;
    }

    public List<Ck_warehouse> findStockView(String c_name) {
        List<Ck_warehouse> list = statisDao.findStockView(c_name);
        return list;
    }


    public TotalData findData() {
        TotalData totalData = statisDao.findData();
        return totalData;
    }

    public List<TotalData> findDataOne(String tab) {
        List<TotalData> list = new ArrayList<>();
        if ("order".equals(tab)){
          list = statisDao.findOrderData();
        }
        if ("purchase".equals(tab)){
             list = statisDao.findPurchaseData();
        }
        if ("ck".equals(tab)){
             list = statisDao.findCKData();
        }
        if ("rk".equals(tab)){
            list = statisDao.findRkData();
        }
        return list;
    }
}
