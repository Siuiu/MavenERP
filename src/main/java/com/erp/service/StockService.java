package com.erp.service;

import com.erp.dao.PurchaseDao;
import com.erp.dao.StockDao;
import com.erp.dao.StorageDao;
import com.erp.pojo.Details;
import com.erp.pojo.Purchase;
import com.erp.pojo.Rk_warehouse;
import com.erp.pojo.Warehouse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StockService {

    @Resource
    private StockDao stockDao;
    @Resource
    private PurchaseDao purchaseDao;

    // 入库信息的展示
    public List<Rk_warehouse> stockList(int usreId, String c_name, String rk_indent, String state) {
        return stockDao.stockList(usreId,c_name,rk_indent,state);
    }

    // 修改入库信息
    public Integer changeState(String rk_indent, String state) {

        // 通过采购单获取数量和商品
        Purchase purchase = purchaseDao.pidInfo(rk_indent);
        // 获取仓库id
        int warehouseId = purchase.getWarehouse().getId();
        // 获取商品id及数量
        List<Details> detailsList = purchase.getDetailsList();
        int isNo =rr(detailsList,warehouseId);
        int rowOne = 0;
        int rowTwo = 0;
        // 入库表修改
        if (state.equals("1")){
            if (isNo==1){
                rowOne = stockDao.changeState(rk_indent,state);
            }
        }else{
            rowOne = stockDao.changeState(rk_indent,state);
        }

        // 采购表修改
        String cg_state;
        if (state.equals("2")){
            cg_state = "1";
            rowTwo = stockDao.changeCGState(rk_indent,cg_state);
        }else {
            cg_state = "0";
            if(isNo == 1){
                rowTwo = stockDao.changeCGState(rk_indent,cg_state);
            }
        }

        int rowThree = 0;
        for (Details details : detailsList) {
            int productId = details.getProduct().getId();
            int count = details.getCount();
            // 修改仓库数量
            if (state.equals("2")){
                rowThree = stockDao.addInventory(warehouseId,productId,count);
            }
            if (state.equals("1")){
                if (isNo==1){
                    rowThree = stockDao.subtractInventory(warehouseId,productId,count);
                }else {
                    rowThree = 0;
                }

            }

        }
        if ((rowOne+rowTwo+rowThree)>=3){
            return 1;
        }else {
            return 0;
        }
    }

    public int rr(List<Details> detailsList,int warehouseId){

        int row = 0;
        for (Details details : detailsList) {
            int productId = details.getProduct().getId();
            int count = details.getCount();
            // 查看该仓库对应商品数量
            int countNow = stockDao.findInventory(warehouseId,productId);
            if (countNow>=count){
                row ++;
            }
        }
        if (row == detailsList.size()){
            return 1;
        }else {
            return 0;
        }

    }

    public List<Warehouse>findWarehouse(){
        return stockDao.findWarehouse();
    }

    public List<Purchase>findCgd(){
        return stockDao.findCgd();
    }
    public int addDelivered(String indent,Integer id,Integer user_id){
        int rowOne= stockDao.addDelivered(indent,id,user_id);
        int rowTwo=stockDao.updateCg_state(indent);
        if ((rowOne+rowTwo)==2){
            return 1;
        }else {
            return 0;
        }

    }
}
