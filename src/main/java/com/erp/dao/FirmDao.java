package com.erp.dao;

import com.erp.pojo.Cfirm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/*
厂商接口
 */
public interface FirmDao {
    //查询所有厂商 用于级联
    List<Cfirm> cascadeQueries();
    //所有厂商的分页查询与条件查询
    List<Cfirm> findAllInfo(@Param("fname")String fname,@Param("founder")String founder,@Param("cid")Integer cid,@Param("status")Integer status,@Param("pid")Integer pid);
    //根据厂商id查询厂商信息
    Cfirm selectFidInfo(@Param("fid")Integer fid);
    //添加厂商
    Integer insertFirm(@Param("fname")String fname,@Param("founder")String founder,@Param("tel")String tel,@Param("address")String address,@Param("cid")Integer cid,@Param("content")String content,@Param("status")Integer status,@Param("uid")Integer uid);
    //修改厂商
    Integer updateFirm(@Param("fid")Integer fid,@Param("fname")String fname,@Param("founder")String founder,@Param("tel")String tel,@Param("address")String address,@Param("cid")Integer cid,@Param("content")String content,@Param("status")Integer status);
    //修改厂商的状态
    Integer updateStatus(@Param("fid")Integer fid,@Param("status")Integer status);
    //查询厂商重名
    Cfirm queryFirmName(@Param("fname")String fname);
}
