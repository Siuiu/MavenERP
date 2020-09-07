package com.erp.dao;

import com.erp.pojo.city;
import com.erp.pojo.job;
import com.erp.pojo.province;
import com.erp.pojo.users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommonDao {

    // 查找省份
    List<province> findProvince();

    // 查询组员职位
    List<job> findUserJob(@Param("deptId") int deptId, @Param("job_name")String job_name);

    // 查找指定组员名字
    List<users> findUserName(int jobId);


}
