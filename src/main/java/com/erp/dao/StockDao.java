package com.erp.dao;

import com.erp.pojo.Order;
import com.erp.pojo.Purchase;
import com.erp.pojo.Rk_warehouse;
import com.erp.pojo.Warehouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockDao {
    // 入库信息的展示
    List<Rk_warehouse> stockList(@Param("usreId") int usreId,@Param("c_name") String c_name,@Param("rk_indent") String rk_indent,@Param("state") String state);

    // 入库信息的修改
    int changeState(@Param("rk_indent") String rk_indent,@Param("state") String state);
    int changeCGState(@Param("purchase_id") String rk_indent,@Param("cg_state") String cg_state);
    int addInventory(@Param("warehouse_id") int warehouseId,@Param("product_id") int productId,@Param("count") int count);
    int subtractInventory(@Param("warehouse_id") int warehouseId,@Param("product_id") int productId,@Param("count") int count);
    // 查看该仓库对应商品数量
    int findInventory(@Param("warehouse_id") int warehouseId,@Param("product_id") int productId);
    int addDelivered(@Param("indent") String indent,
                    @Param("id") Integer id,
                    @Param("user_id") Integer user_id);
    List<Warehouse>findWarehouse();
    int updateCg_state(String indent);
    List<Purchase>findCgd();
}
