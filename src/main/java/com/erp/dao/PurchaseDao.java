package com.erp.dao;

import com.erp.pojo.Details;
import com.erp.pojo.Purchase;
import com.erp.pojo.users;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PurchaseDao {
    //查询所有采购单
    List<Purchase> findAllInfo(@Param("pid")String pid,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("minPrice")String minPrice,@Param("maxPrice")String maxPrice,@Param("status")String status,@Param("uid")Integer uid);
    //通过采购单号查找入库详情
    Purchase pidInfo(@Param("pid")String pid);
    //创建采购单
    Integer creatPurchase(Purchase purchase);
    //创建采购单明细
    Integer creatDetails(List<Details> detailsList);
    //删除采购单和相关的明细单
    Integer deletePurchase(@Param("pid")String pid);
    //查询采购部下所有的经理
    List<users> findAllUsers();
    //提交审核
    Integer submitCheck(@Param("checkId")Integer checkId, @Param("pid") String pid);
    //审核采购单的分页和模糊查询
    List<Purchase> findCheckAll(@Param("pid")String pid,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("minPrice")String minPrice,@Param("maxPrice")String maxPrice,@Param("checkId")Integer checkId);
    //采购单的审核
    Integer checkPurchase(@Param("pid")String pid,@Param("checkOpinion")String checkOpinion,@Param("status")String status);
    //删除采购单相关明细单  用于修改
    Integer deleteDetails(@Param("pid")String pid);
    //修改采购单的总金额  用于修改
    Integer updatePurchaseTotalPrice(@Param("pid")String pid,@Param("totalPrice")Double totalPrice);
}
