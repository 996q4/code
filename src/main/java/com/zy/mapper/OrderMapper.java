package com.zy.mapper;

import com.zy.pojo.Order;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("insert into tb_order(order_id,user_id,order_price,order_time,is_pay,is_ship,is_receipt," +
            "product_id,product_quantity,product_name,product_photo,product_price) " +
            "values(#{order_id},#{user_id},#{order_price},#{order_time},#{is_pay},#{is_ship}," +
            "#{is_receipt},#{product_id},#{product_quantity},#{product_name},#{product_photo},#{product_price})")
    void addOrder(Order order);

    @Select("select * from tb_order where order_id = #{oid}")
    Order findOrderById(String oid);

    @Update("update tb_order set address_id = #{addressid} where order_id = #{oid}")
    void updateOrderAddress(@Param("oid") String oid, @Param("addressid") int addressid);

    @Update("update tb_order set is_pay = #{status} where order_id = #{oid}")
    void updateOrderIsPay(@Param("oid") String oid, @Param("status") String status);

    @Select("select * from tb_order where user_id = #{user_id} order by order_time Desc")
    List<Order> findOrderByUserId(int user_id);

    @Select("select * from tb_order")
    List<Order> findAllOrder();

    @Select("select count(*) from tb_order")
    int findTotalCount();

    @Select("select * from tb_order limit #{start}, #{rows}")
    List<Order> findByPage(@Param("start") int start, @Param("rows") int rows);

    @Select("select * from tb_order where order_id like concat('%',#{keywords},'%')")
    List<Order> findOrderListByKey(String keywords);

    @Select("select * from tb_order where user_id = #{user_id} and is_receipt = '1'")
    List<Order> findOrderByUserIdAndIsReceipt(int user_id);

    @Update("update tb_order set is_ship = '1' where order_id = #{oid}")
    void updateOrderIsShip(String oid);

    @Update("update tb_order set is_receipt = '1' where order_id = #{oid}")
    void updateOrderIsReceipt(String oid);

    @Delete("delete from tb_order where order_id = #{oid}")
    void delOrderById(String oid);
}