package com.erp.service;


import com.erp.dao.FirmDao;
import com.erp.pojo.Cfirm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FirmService {
    @Resource
    private FirmDao dao;

    //查询所有厂商 用于级联
    public List<Cfirm> cascadeQueries() {
        return dao.cascadeQueries();
    }

    //所有厂商的分页查询与条件查询
    public List<Cfirm> findAllInfo(Integer page, String fname, String founder, Integer cid, Integer status, Integer pid) {
        PageHelper.startPage(page, 5,"f.firm_date DESC ");
        return dao.findAllInfo(fname, founder, cid, status, pid);
    }

    //根据厂商id查询厂商信息
    public Cfirm selectFidInfo(Integer fid) {
        return dao.selectFidInfo(fid);
    }

    //添加厂商
    public Integer insertFirm(String fname, String founder, String tel, String address, Integer cid, String content, Integer status, Integer uid) {
        return dao.insertFirm(fname, founder, tel, address, cid, content, status, uid);
    }

    //修改厂商
    public Integer updateFirm(Integer fid, String fname, String founder, String tel, String address, Integer cid, String content, Integer status) {
        return dao.updateFirm(fid, fname, founder, tel, address, cid, content, status);
    }

    //修改厂商的状态
    public Integer updateStatus(Integer fid, Integer status) {
        return dao.updateStatus(fid, status);
    }
    //查询厂商重名
    public Cfirm queryFirmName(String fname){
        return dao.queryFirmName(fname);
    }
}
