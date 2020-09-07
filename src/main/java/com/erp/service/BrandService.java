package com.erp.service;

import com.erp.dao.BrandDao;
import com.erp.pojo.Brand;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/*
品牌业务层
 */
@Service
public class BrandService {
    @Resource
    private BrandDao dao;
    //查询所有品牌信息  用于级联
    public List<Brand> seBrandAllInfo(Integer status){
        return dao.seBrandAllInfo(status);
    }
    //品牌的分页和条件查询
    public List<Brand> findAllInfo(Integer page,String bname,String status){
        PageHelper.startPage(page,5,"b.creat_date desc");
        return dao.findAllInfo(page, bname, status);
    }
    //根据品牌id修改品牌
    public Integer updateBrand(Integer bid,String uname,String status){
        return dao.updateBrand(bid, uname, status);
    }
    //根据品牌id返回品牌信息
    public Brand getBrand(Integer bid){
        return dao.getBrand(bid);
    }
    //添加品牌
    public Integer insertBrand(Brand brand){
        return dao.insertBrand(brand);
    }
    //验证品牌名是否重名
    public Brand queryBrandName(String bname){
        return dao.queryBrandName(bname);
    }
}
