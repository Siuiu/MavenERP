package com.erp.dao;

import com.erp.pojo.Inventory;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface WarehouseDao {
    ArrayList<Inventory> showInvent(
                                    @Param("c_name") String c_name,
                                    @Param("brand_name") String brand_name,
                                    @Param("type_name") String type_name,
                                    @Param("product_model") String product_model);

    int getALLwarehouse(@Param("c_name") String c_name,@Param("brand_name") String brand_name,@Param("type_name") String type_name, @Param("product_model") String product_model);
}
