package com.erp.controller;

import com.erp.pojo.Brand;
import com.erp.pojo.users;
import com.erp.service.BrandService;
import com.github.pagehelper.PageInfo;
import freemarker.template.SimpleDate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Brand")
public class BrandController {
    @Resource
    private BrandService service;

    @RequestMapping("/seBrandAllInfo")//查询所有品牌  用于级联
    @ResponseBody
    public List<Brand> seBrandAllInfo(Integer status) {
        return service.seBrandAllInfo(status);
    }

    @RequestMapping("/findAllInfo")//品牌分页与条件查询
    @ResponseBody
    public PageInfo<Brand> findAllInfo(Integer page, String bname, String status) {
        List<Brand> list = service.findAllInfo(page, bname, status);
        PageInfo<Brand> info = new PageInfo<>(list);
        return info;
    }

    @RequestMapping("/updateBrand")
    @ResponseBody
    public Integer updateBrand(Integer bid, String bname, String status) {
        return service.updateBrand(bid, bname, status);
    }

    @RequestMapping("/getBrand")
    @ResponseBody
    public Brand getBrand(Integer bid) {
        return service.getBrand(bid);
    }

    @RequestMapping("/insertBrand")
    @ResponseBody
    public Integer insertBrand(String bname, Integer status, String creatDate, HttpSession session) throws ParseException {
        Brand brand=new Brand();
        brand.setName(bname);
        brand.setStatus(status);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse(creatDate);
        brand.setCreatDate(d);
        users user= (users) session.getAttribute("user");
        brand.setUser(user);
        return service.insertBrand(brand);
    }
    @RequestMapping("/queryBrandName")
    @ResponseBody
    public Boolean queryBrandName(String bname) {
        Brand brand=service.queryBrandName(bname);
        if(brand==null)return false;
        return true;
    }
}
