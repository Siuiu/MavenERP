package com.erp.service;

import com.erp.dao.ProductDao;
import com.erp.pojo.Product;
import com.erp.pojo.ProductType;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductService {
    @Resource
    ProductDao dao;
    //通过类型id获取商品列表
    public List<Product> tidFindAll(Integer status,Integer tid){
        return dao.tidFindAll(status,tid);
    }
    //商品列表的分页展示与条件查
    public List<Product> findAllInfo(Integer page, Integer bid, Integer tid,String pname,Integer status){
        PageHelper.startPage(page,5,"p.creat_time DESC");
        return dao.findAllInfo(bid, tid, pname, status);
    }
    //获取商品信息
    public Product getProduct(Integer pid){
        return dao.getProduct(pid);
    }
    //修改商品
    public Integer updateProduct(Integer pid,Integer tid,String pname,Integer fid,Double price,String unit,Integer status){
        return dao.updateProduct(pid, tid, pname, fid, price, unit, status);
    }
    //添加商品
    public Integer insertProduct(Integer uid,Integer tid,String pname,Integer fid,Double price,String unit,Integer status){
        return dao.insertProduct(uid,tid, pname, fid, price, unit, status);
    }
    //修改商品状态
    public Integer updateStatus(Integer pid,Integer status){
        return dao.updateStatus(pid,status);
    }
    //查询商品类型是否为可用状态
    public ProductType queryParentStatus(Integer pid){
        return dao.queryParentStatus(pid);
    }
    //查询商品是否有重名
    public Product queryProductName(String pname){
        return dao.queryProductName(pname);
    }
}
