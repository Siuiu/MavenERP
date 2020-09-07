package com.erp.dao;

import com.erp.pojo.city;
import com.erp.pojo.province;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;


public interface CityDao {


    // 查询所有区域
    List<city> areaList(@Param("p_name") String p_name,
                        @Param("c_name") String c_name);

    ArrayList<city> showProvince(@Param("pageIndex") Integer pageIndex,
                                 @Param("p_name") String p_name,
                                 @Param("c_name") String c_name);

    int getALLcity(@Param("p_name") String p_name, @Param("c_name") String c_name);
}

