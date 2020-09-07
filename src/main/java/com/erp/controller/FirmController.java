package com.erp.controller;

import com.erp.pojo.Cfirm;
import com.erp.pojo.users;
import com.erp.service.FirmService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/firm")
public class FirmController {
    @Resource
    FirmService service;
    /*
    查询所有厂商 用于级联
     */
    @RequestMapping("cascadeQueries")
    @ResponseBody
    public List<Cfirm> cascadeQueries(){
        return service.cascadeQueries();
    }
    //所有厂商的分页查询与条件查询
    @RequestMapping("findAllInfo")
    @ResponseBody
    public PageInfo<Cfirm> findAllInfo(Integer page, String fname, String founder, Integer cid, Integer status, Integer pid){
        List<Cfirm> list=service.findAllInfo(page, fname, founder, cid, status,pid);
        PageInfo info=new PageInfo<>(list);
        return info;
    }
    //根据厂商id查询厂商信息
    @RequestMapping("selectFidInfo")
    @ResponseBody
    public Cfirm selectFidInfo(Integer fid){
        return service.selectFidInfo(fid);
    }
    /*
    添加厂商
     */
    @RequestMapping("insertFirm")
    @ResponseBody
    public Integer insertFirm(String fname, String founder, String tel, String address, Integer cid, String content, Integer status, HttpSession session){
        users user= (users) session.getAttribute("user");
        Integer uid=user.getU_id();
        return service.insertFirm(fname, founder, tel, address, cid, content, status, uid);
    }
    /*
    修改厂商
     */
    @RequestMapping("updateFirm")
    @ResponseBody
    public Integer updateFirm(Integer fid,String fname,String founder, String tel, String address, Integer cid, String content, Integer status, HttpSession session){
        return service.updateFirm(fid,fname, founder, tel, address, cid, content, status);
    }
    /*
    修改厂商的状态
     */
    @RequestMapping("updateStatus")
    @ResponseBody
    public Integer updateStatus(Integer fid, Integer status){
        return service.updateStatus(fid, status);
    }
    /*
    查询厂商重名
     */
    @RequestMapping("queryFirmName")
    @ResponseBody
    public Boolean queryFirmName(String fname){
        Cfirm cfirm=service.queryFirmName(fname);
        if(cfirm==null)return false;
        return true;
    }
}
