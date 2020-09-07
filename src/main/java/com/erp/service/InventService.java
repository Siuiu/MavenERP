package com.erp.service;

import com.erp.dao.WarehouseDao;
import com.erp.pojo.Inventory;
import com.erp.pojo.Warehouse;
import com.erp.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service

public class InventService {
    @Resource
    private WarehouseDao warehouseDao;
    public List<Inventory> showInvent(String c_name, String brand_name, String type_name, String product_model) {
        ArrayList<Inventory> list = warehouseDao.showInvent(c_name, brand_name,type_name,product_model);
        int rowCount = warehouseDao.getALLwarehouse(c_name, brand_name,type_name,product_model);
        return list;
    }
}
