package com.erp.controller;

import com.erp.pojo.*;
import com.erp.service.CommonService;
import com.erp.service.DeptService;
import com.erp.service.StorageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author cheny
 * @Description
 * @Since 2020/7/19
 */
@Controller
@RequestMapping("/storage")
public class StorageController {
    @Resource
    DeptService depts;
    @Resource
    CommonService commonService;
    @Resource
    private StorageService StorageService;
    @RequestMapping("/storage/storagelist")
    @ResponseBody
    public PageInfo<Warehouse>findAll(@RequestParam("ck_name") String ck_name,
                                  @RequestParam("p_name") String p_name,
                                  @RequestParam("c_name") String c_name,
                                  @RequestParam("pageStart") Integer pageStart,
                                  @RequestParam("pageSize") Integer pageSize){
        PageHelper.startPage(pageStart,pageSize);
        List<Warehouse>list = StorageService.findAll(ck_name,p_name,c_name);
        PageInfo<Warehouse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    @RequestMapping("/storage/storageview")
    @ResponseBody
    public Warehouse viewDetails(@RequestParam int id){
        Warehouse Warehouse = StorageService.viewDetails(id);
        return Warehouse;
    }
    @RequestMapping("/storage/findProvince")
    @ResponseBody
    public List<province> findProvince(){
        return commonService.findProvince();
    }
    @RequestMapping("/storage/showcity")
    @ResponseBody
    public List<city> showCity(Integer p_id){
        return depts.showCity(p_id);
    }
    @RequestMapping("/storage/updateWarehouse")
    @ResponseBody
    public int updateWarehouse(int id,
                               String sname,
                               String saddress,
                               int province_id,
                               int city_id,
                               int usre_id,
                               String sphone,
                               String sdetails,
                               String state){
        int row = StorageService.updateWarehouse(id,
                sname,
                saddress,
                province_id,
                city_id,
                usre_id,
                sphone,
                sdetails,
                state);
        return row;
    }
    @RequestMapping("/storage/findPerson")
    @ResponseBody
    public List<users>findPerson(){
        return StorageService.findPerson();
    }
    @RequestMapping("/storage/findCname")
    @ResponseBody
    public String findCname(String c_name){
        List<Warehouse>list=StorageService.findCname(c_name);
        if (list.size()>0){
            return "error";
        }
        return "success";
    }
    @RequestMapping("/storage/addWarehouse")
    @ResponseBody
    public int addWarehouse(String sname,
                            String saddress,
                            Integer province_id,
                            Integer city_id,
                            Integer usre_id,
                            String sphone,
                            String sdetails,
                            String state,
                            HttpSession session){
        users user= (users) session.getAttribute("user");
        Integer user_id=user.getU_id();
        int row = StorageService.addWarehouse(
                sname,
                saddress,
                province_id,
                city_id,
                usre_id,
                sphone,
                sdetails,
                state,
                user_id);
        return row;
    }



    @RequestMapping("/storageBrowse/storagelist")
    @ResponseBody
    public PageInfo<Warehouse>findAlls(@RequestParam("ck_name") String ck_name,
                                      @RequestParam("p_name") String p_name,
                                      @RequestParam("c_name") String c_name,
                                      @RequestParam("pageStart") Integer pageStart,
                                      @RequestParam("pageSize") Integer pageSize){
        PageHelper.startPage(pageStart,pageSize);
        List<Warehouse>list = StorageService.findAll(ck_name,p_name,c_name);
        PageInfo<Warehouse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    @RequestMapping("/storageBrowse/storageview")
    @ResponseBody
    public Warehouse viewDetailss(@RequestParam int id){
        Warehouse Warehouse = StorageService.viewDetails(id);
        return Warehouse;
    }
    @RequestMapping("/storageBrowse/findProvince")
    @ResponseBody
    public List<province> findProvinces(){
        return commonService.findProvince();
    }
    @RequestMapping("/storageBrowse/showcity")
    @ResponseBody
    public List<city> showCitys(Integer p_id){
        return depts.showCity(p_id);
    }


    @RequestMapping("/delivery/deliveryViewOne")
    @ResponseBody
    public Ck_warehouse showDeliveryView(String ck_indent){
        Ck_warehouse ck_warehouse = StorageService.showDeliveryView(ck_indent);

        return ck_warehouse;
    }
    @RequestMapping("/delivery/ckList")
    @ResponseBody
    public PageInfo<Ck_warehouse>ckList(String c_name,String indent,String state,Integer pageStart,Integer pageSize){
        PageHelper.startPage(pageStart,pageSize);
        List<Ck_warehouse>list = StorageService.ckList(c_name,indent,state);
        PageInfo<Ck_warehouse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    @RequestMapping("/delivery/addDelivery")
    @ResponseBody
    public int addDelivery(String indent,Integer id,HttpSession session){
        users user= (users) session.getAttribute("user");
        Integer user_id=user.getU_id();
        int row=StorageService.addDelivery(indent,id,user_id);
        return row;

    }
    @RequestMapping("/delivery/findWarehouse")
    @ResponseBody
    public List<Warehouse>findWarehouse(){
        return StorageService.findWarehouse();
    }
    @RequestMapping("/delivery/findDdh")
    @ResponseBody
    public List<Order>findDdh(){
        return StorageService.findDdh();
    }
    @RequestMapping("/delivery/cancelOrder")
    @ResponseBody
    public String cancelOrder(String indent,int status) {

        return StorageService.cancelOrder(indent,status);
    }
    @RequestMapping("/delivery/findIndentAndWarehouse_id")
    @ResponseBody
    public List<Ck_warehouse>findIndentAndWarehouse_id(){
        return StorageService.findIndentAndWarehouse_id();
    }
    @RequestMapping("/delivery/findIdAndNum")
    @ResponseBody
    public List<OrderDetails>findIdAndNum(String indent){
        return StorageService.findIdAndNum(indent);
    }
    @RequestMapping("/delivery/Inventory")
    @ResponseBody
    public Inventory Inventory(int product_id,int warehouse_id){
        return StorageService.Inventory(product_id,warehouse_id);
    }
    @RequestMapping("/delivery/ship")
    @ResponseBody
    public String ship(String indent){
        int row = StorageService.ship(indent);
        if (row > 0) {
            return "success";
        }
        return "error";
    }
    @RequestMapping("/delivery/cancelShopping")
    @ResponseBody
    public String scancelShopping(String indent){
        int row = StorageService.scancelShopping(indent);
        if (row > 0) {
            return "success";
        }
        return "error";
    }

    // 修改仓库状态
    @RequestMapping("/changesStatusWarehouse")
    @ResponseBody
    public int changesStatusWarehouse(int id,String sure){
        String state;
        if (sure.equals("sure1")){
            state = "2";
        }else {
            state = "1";
        }
        return StorageService.changesStatusWarehouse(id,state);
    }
}
