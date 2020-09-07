package com.erp.dao;

import com.erp.pojo.Product;
import com.erp.pojo.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
商品接口
 */
public interface ProductDao {
    //通过类型id获取商品列表
    List<Product> tidFindAll(@Param("status")Integer status,@Param("tid")Integer tid);
    //商品列表的分页展示与条件查询
    List<Product> findAllInfo(@Param("bid")Integer bid,@Param("tid")Integer tid,@Param("pname")String pname,@Param("status")Integer status);
    //获取商品信息
    Product getProduct(@Param("pid")Integer pid);
    //修改商品
    Integer updateProduct(@Param("pid")Integer pid,@Param("tid")Integer tid,@Param("pname")String pname,@Param("fid")Integer fid,@Param("price")Double price,@Param("unit")String unit,@Param("status")Integer status);
    //添加商品
    Integer insertProduct(@Param("uid")Integer uid,@Param("tid")Integer tid,@Param("pname")String pname,@Param("fid")Integer fid,@Param("price")Double price,@Param("unit")String unit,@Param("status")Integer status);
    //修改商品状态
    Integer updateStatus(@Param("pid")Integer pid,@Param("status")Integer status);
    //查询商品类型是否为可用状态
    ProductType queryParentStatus(@Param("pid") Integer pid);
    //查询商品是否有重名
    Product queryProductName(@Param("pname")String pname);
}
