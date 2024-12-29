package com.zy.mapper;

import com.zy.pojo.OrderList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderListMapper {
    @Insert("insert into tb_orderlist(order_id, product_id, product_quantity) values(#{order_id}, #{product_id}, #{product_quantity})")
    void addOrderList(OrderList orderList);
}