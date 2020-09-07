package com.erp.service;

import com.erp.dao.StorageDao;
import com.erp.pojo.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author cheny
 * @Description
 * @Since 2020/7/19
 */
@Service
public class StorageService {
    @Resource
    private StorageDao StorageDao;
    public List<Warehouse>findAll(String ck_name,String p_name,String c_name){
        return StorageDao.findAll(ck_name,p_name,c_name);
    }

    public Warehouse viewDetails(int id){
        return StorageDao.viewDetails(id);
    }

    public int updateWarehouse(int id,
                               String sname,
                               String saddress,
                               int province_id,
                               int city_id,
                               int usre_id,
                               String sphone,
                               String sdetails,
                               String state){
        return StorageDao.updateWarehouse(id,
                sname,
                saddress,
                province_id,
                city_id,
                usre_id,
                sphone,
                sdetails,
                state);
    }

    public List<users>findPerson(){
        return StorageDao.findPerson();
    }

    public List<Warehouse>findCname(String c_name){
        return StorageDao.findCname(c_name);
    }

    public int addWarehouse(
                            String sname,
                            String saddress,
                            Integer province_id,
                            Integer city_id,
                            Integer usre_id,
                            String sphone,
                            String sdetails,
                            String state,
                            Integer user_id){
        return StorageDao.addWarehouse(
                sname,
                saddress,
                province_id,
                city_id,
                usre_id,
                sphone,
                sdetails,
                state,
                user_id);
    }

    public Ck_warehouse showDeliveryView(String ck_indent) {
        Ck_warehouse ck_warehouse = StorageDao.showDeliveryView(ck_indent);
        return  ck_warehouse;
    }

    public List<Ck_warehouse>ckList(String c_name,String indent,String state){
        return StorageDao.ckList(c_name,indent,state);
    }

    public int addDelivery(String indent,Integer id,Integer user_id){
        int rowOne=StorageDao.addDelivery(indent,id,user_id);
        int rowTwo=StorageDao.updateDd_state(indent);
        if ((rowOne+rowTwo)==2){
            return 1;
        }else {
            return 0;
        }

    }
    public List<Warehouse>findWarehouse(){
        return StorageDao.findWarehouse();
    }
    public List<Order>findDdh(){
        return StorageDao.findDdh();
    }
    public String cancelOrder(String indent,int status){

        if ( StorageDao.cancelOrder(indent,status) >0){
            return  "success";
        }
        return "failed";
    }
    public List<Ck_warehouse>findIndentAndWarehouse_id(){
        return StorageDao.findIndentAndWarehouse_id();
    }
    public List<OrderDetails>findIdAndNum(String indent){
        return StorageDao.findIdAndNum(indent);
    }
    public Inventory Inventory(int product_id,int warehouse_id){
        return StorageDao.Inventory(product_id,warehouse_id);
    }

    public int ship(String indent) {
        List<OrderDetails> list = StorageDao.findProduct(indent);
        int row = 0;
        int a = 0;
        for (int i = 0; i<list.size(); i++){
            Inventory inventory = StorageDao.isNumTrue(indent,list.get(i).getProduct_id(),list.get(i).getPurchase_num());
            if (inventory != null){
                a++;
            }
           //
        }
        if (a==list.size()){
            for (int i = 0; i<list.size(); i++){
                row = StorageDao.ship(indent,list.get(i).getProduct_id(),list.get(i).getPurchase_num());
            }
        }
        if (row>0){
            // 执行修改状态语句
            StorageDao.updateCkState(indent);
        }
        return row;
    }
    public int scancelShopping(String indent){
        List<OrderDetails> list = StorageDao.findProduct(indent);
        int row = 0;
        for (int i = 0; i<list.size(); i++){
            row = StorageDao.scancelShopping(indent,list.get(i).getProduct_id(),list.get(i).getPurchase_num());
        }
        if (row>0){
            StorageDao.updateCKState(indent);
        }
        return  row;
    }

    // 修改仓库状态
    public int changesStatusWarehouse(int id, String state) {
        return StorageDao.changesStatusWarehouse(id,state);
    }
}
