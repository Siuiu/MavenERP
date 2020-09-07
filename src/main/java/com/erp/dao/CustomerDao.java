package com.erp.dao;

import com.erp.pojo.Custom;
import com.erp.pojo.job;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerDao {
    // 查询所有客户
    List<Custom> customerList(@Param("company") String company,
                              @Param("customer") String customer,
                              @Param("province") String province,
                              @Param("city") String city,
                              @Param("status") String status,
                              @Param("allocation") String allocation);
    // 查询客户详情
    Custom customerView(int customId);

    // 增加客户
    int customAdd(@Param("custom")Custom custom,@Param("createId")int createId);

    // 修改客户
    int customUpdate(Custom custom);

    // 修改客户状态
    int changeStatusCustom(@Param("customId") int customId,@Param("sure") String sure);

    // 分配客户
    int allocationCustom(@Param("customId")int customId,@Param("serviceId")int serviceId,@Param("distractime")String distractime);

    // 获取登录职位
    job userPosition(int jobId);

    // 客户信息浏览
    List<Custom> customBrowse(@Param("company") String company,
                              @Param("customer") String customer,
                              @Param("province") String province,
                              @Param("city") String city,
                              @Param("position") String position,
                              @Param("serviceId") int serviceId);

    // 客户重名问题
    int repeatCustomName(String customname);
}
