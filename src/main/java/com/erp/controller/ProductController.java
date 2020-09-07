package com.erp.controller;

import com.erp.pojo.Product;
import com.erp.pojo.ProductType;
import com.erp.pojo.users;
import com.erp.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.omg.CORBA.INTERNAL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService service;
    /*
    通过类型id获取商品列表
     */
    @RequestMapping("/tidFindAll")
    @ResponseBody
    public List<Product> tidFindAll(Integer status,Integer tid){
        return service.tidFindAll(status,tid);
    }
    /*
    商品列表的分页展示与条件查
     */
    @RequestMapping("/findAllInfo")
    @ResponseBody
    public PageInfo<Product> findAllInfo(Integer page, Integer bid, Integer tid,String pname,Integer status){
        List<Product> list=service.findAllInfo(page, bid, tid, pname, status);
        PageInfo info=new PageInfo<>(list);
        return info;
    }
    /*
    获取商品信息
     */
    @RequestMapping("/getProduct")
    @ResponseBody
    public Product getProduct(Integer pid){
        return service.getProduct(pid);
    }
    /*
    修改商品
     */
    @RequestMapping("/updateProduct")
    @ResponseBody
    public Integer updateProduct(Integer pid,Integer tid,String pname,Integer fid,Double price,String unit,Integer status){
        return service.updateProduct(pid, tid, pname, fid, price, unit, status);
    }
    /*
   添加商品
    */
    @RequestMapping("/insertProduct")
    @ResponseBody
    public Integer insertProduct(Integer tid, String pname, Integer fid, Double price, String unit, Integer status, HttpSession session){
        users user= (users) session.getAttribute("user");
        Integer uid=user.getU_id();
        return service.insertProduct(uid,tid, pname, fid, price, unit, status);
    }
    /*
   修改商品状态
    */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public Integer updateStatus(Integer pid,Integer status){
        if(status==1){
            ProductType pType=service.queryParentStatus(pid);
            if(pType==null) return 0;
        }
        return service.updateStatus(pid, status);
    }
    /*
    查询商品是否有重名
     */
    @RequestMapping("/queryProductName")
    @ResponseBody
    public Boolean queryProductName(String pname){
        Product product=service.queryProductName(pname);
        if(product==null)return false;
        return true;
    }

}
