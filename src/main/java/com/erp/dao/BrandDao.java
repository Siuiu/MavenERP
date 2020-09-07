package com.erp.dao;

import com.erp.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/*
品牌接口
 */
public interface BrandDao {
    //查询所有品牌  用于级联
    List<Brand> seBrandAllInfo(@Param("status")Integer status);
    //所有品牌的分页  条件查询
    List<Brand> findAllInfo(@Param("page")Integer page,@Param("bname") String bname,@Param("status")String status);
    //根据品牌id修改品牌
    Integer updateBrand(@Param("bid")Integer bid,@Param("bname") String uname,@Param("status")String status);
    //根据品牌id获取品牌
    Brand getBrand(@Param("bid")Integer bid);
    //添加品牌
    Integer insertBrand(Brand brand);
    //验证品牌名是否重名
    Brand queryBrandName(@Param("bname")String bname);
}
