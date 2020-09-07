package com.erp.dao;

import com.erp.pojo.*;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.List;

/**
 * @Author cheny
 * @Description 仓库管理
 * @Since 2020/7/19
 */
public interface StorageDao {
    List<Warehouse>findAll(@Param("ck_name") String ck_name,
                           @Param("p_name") String p_name,
                           @Param("c_name") String c_name);

    Warehouse viewDetails(int id);

    int updateWarehouse(@Param("id") int id,
                        @Param("sname") String sname,
                        @Param("saddress") String saddress,
                        @Param("province_id") int province_id,
                        @Param("city_id") int city_id,
                        @Param("usre_id") int usre_id,
                        @Param("sphone") String sphone,
                        @Param("sdetails") String sdetails,
                        @Param("state") String state);

    List<users>findPerson();

    List<Warehouse>findCname(String c_name);

    int addWarehouse(
                     @Param("sname") String sname,
                     @Param("saddress") String saddress,
                     @Param("province_id") Integer province_id,
                     @Param("city_id") Integer city_id,
                     @Param("usre_id") Integer usre_id,
                     @Param("sphone") String sphone,
                     @Param("sdetails") String sdetails,
                     @Param("state") String state,
                     @Param("user_id") Integer user_id);

    Ck_warehouse showDeliveryView(String ck_indent);

    List<Ck_warehouse>ckList(@Param("c_name") String c_name,
                             @Param("indent") String indent,
                             @Param("state") String state);
    int addDelivery(@Param("indent") String indent,
                    @Param("id") Integer id,
                    @Param("user_id") Integer user_id);
    List<Warehouse>findWarehouse();
    int updateDd_state(String indent);
    List<Order>findDdh();
    int cancelOrder(@Param("indent") String indent,
                    @Param("status") int status);
    int state(String indent);
    //查找订单号和仓库id
    List<Ck_warehouse>findIndentAndWarehouse_id();
    //查找产品id和数量
    List<OrderDetails>findIdAndNum(String indent);
    //库存
    Inventory Inventory(@Param("product_id") int product_id,
                        @Param("warehouse_id") int warehouse_id);

    List<OrderDetails> findProduct(String indent);
    int ship(@Param("indent") String indent,
             @Param("productId") Integer id,
             @Param("num") Integer num);

    void updateCkState(String indent);

    Inventory isNumTrue(@Param("indent") String indent,
                        @Param("productId") Integer id,
                        @Param("num") Integer num);
    int scancelShopping(@Param("indent") String indent,
                        @Param("productId") Integer id,
                        @Param("num") Integer num);

    // 修改仓库状态
    int changesStatusWarehouse(@Param("id") int id,@Param("state") String state);

    void updateCKState(String indent);
}
