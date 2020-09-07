package com.erp.controller;

import com.erp.pojo.ProductType;
import com.erp.pojo.Purchase;
import com.erp.pojo.users;
import com.erp.service.ProductTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/*
商品类型
 */
@Controller
@RequestMapping("/productType")
public class ProdunctTypeController {
    @Resource
    private ProductTypeService service;
    /*
    通过品牌id查找类型
     */
    @RequestMapping("/bidFindAll")
    @ResponseBody
    public List<ProductType> bidFindAll(Integer status,Integer bid){
        return service.bidFindAll(status,bid);
    }
    /*
       分页查询和条件查询
     */
    @RequestMapping("/findAllInfo")
    @ResponseBody
    public PageInfo<ProductType> findAllInfo(Integer page,Integer bid,String tname,Integer status){
        List<ProductType> list=service.findAllInfo(page, bid, tname, status);
        PageInfo<ProductType> info=new PageInfo<>(list);
        return info;
    }
    /*
        根据类型id查询类型信息
     */
    @RequestMapping("/tidFindInfo")
    @ResponseBody
    public ProductType tidFindInfo(Integer tid){
        return service.tidFindInfo(tid);
    }
    //商品类型的修改
    @RequestMapping("/updateType")
    @ResponseBody
    public Integer updateType(Integer tid,Integer bid,String tname,Integer status){
        return service.updateType(tid, bid, tname, status);
    }
    //商品类型的添加
    @RequestMapping("/addType")
    @ResponseBody
    public Integer addType(Integer bid, String tname, Integer status,HttpSession session){
        users user= (users) session.getAttribute("user");
        Integer uid=user.getU_id();
        return service.addType(bid, tname, status,uid);
    }

}
