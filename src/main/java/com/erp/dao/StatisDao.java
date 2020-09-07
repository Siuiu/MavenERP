package com.erp.dao;

import com.erp.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * 数据统计Service
 * @author ZYX
 *
 */
public interface StatisDao {
    List<Order> findSalesStatis(@Param("province") String province,
                          @Param("city") String city,
                          @Param("customName") String customName,
                          @Param("startTime") String startTime,
                          @Param("endTime")String endTime);

    List<Order> findSalesView(String uname);

    List<Purchase> findPurchaseStatis(@Param("province") String province,
                                      @Param("city") String city,
                                      @Param("cfirmname") String cfirmname,
                                      @Param("startTime") String startTime,
                                      @Param("endTime")String endTime);

    List<Purchase> findPurchaseView(String firm_id);

    Purchase  findPurchase(String pid);

    List<Ck_warehouse> findDeliveryStatis(@Param("province") String province,
                                          @Param("city") String city,
                                          @Param("wname") String wname,
                                          @Param("startTime") String startTime,
                                          @Param("endTime")String endTime);

    List<Ck_warehouse> findDeliveryView(String c_name);

    List<Rk_warehouse> findStockStatis(@Param("province") String province,
                                           @Param("city") String city,
                                           @Param("wname") String wname,
                                           @Param("startTime") String startTime,
                                           @Param("endTime")String endTime);

    List<Ck_warehouse> findStockView(String c_name);


    TotalData findData();


    List<TotalData> findOrderData();
    List<TotalData> findPurchaseData();
    List<TotalData> findCKData();
    List<TotalData> findRkData();
}
