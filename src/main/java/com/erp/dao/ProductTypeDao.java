package com.erp.dao;

import com.erp.pojo.Brand;
import com.erp.pojo.ProductType;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ProductTypeDao {
    //通过品牌查询品牌下的型号
    List<ProductType> bidFindAll(@Param("status")Integer status,@Param("bid") Integer bid);
    //分页查询与条件查询
    List<ProductType> findAllInfo(@Param("bid")Integer bid,@Param("tname")String tname,@Param("status")Integer status);
    //根据类型id查询类型信息
    ProductType tidFindInfo(@Param("tid")Integer tid);
    //商品类型的修改
    Integer updateType(@Param("tid")Integer tid,@Param("bid")Integer bid,@Param("tname")String tname,@Param("status")Integer status);
    //添加商品类型
    Integer addType(@Param("bid")Integer bid,@Param("tname")String tname,@Param("status")Integer status,@Param("uid")Integer uid);
    //查询上一级品牌是否为可用状态
    Brand queryParentStatus(@Param("tid")Integer tid);
}
