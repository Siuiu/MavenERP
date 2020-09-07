package com.erp.service;

import com.erp.dao.ProductTypeDao;
import com.erp.pojo.Brand;
import com.erp.pojo.ProductType;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.INTERNAL;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
商品类型业务层
 */
@Service
public class ProductTypeService {
    @Resource
    private ProductTypeDao dao;
    public List<ProductType> bidFindAll(Integer status,Integer bid){
        return dao.bidFindAll(status,bid);
    }
    //分页查询与条件查询
    public List<ProductType> findAllInfo(Integer page,Integer bid,String tname,Integer status){
        PageHelper.startPage(page,5,"t.type_time DESC ");
        return dao.findAllInfo(bid, tname, status);
    }
    //根据类型id查询类型信息
    public ProductType tidFindInfo(Integer tid){
        return dao.tidFindInfo(tid);
    }
    //商品类型的修改
    public Integer updateType(Integer tid,Integer bid,String tname,Integer status){
        if(status==1&&bid==null){
            Brand brand=dao.queryParentStatus(tid);
            if (brand==null)return 0;
        }
        return dao.updateType(tid, bid, tname, status);
    }
    //商品类型的添加
    public Integer addType(Integer bid,String tname,Integer status,Integer uid){
        return dao.addType(bid, tname, status,uid);
    }
}
