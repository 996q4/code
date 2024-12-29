package com.zy.mapper;

import com.zy.pojo.Cart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    /**
     * 查询用户购物车商品数量
     */
    @Select("select count(*) from tb_cart where user_id = #{user_id}")
    int findCartCountByUserId(int user_id);

    /**
     * 查询用户购物车列表
     */
    @Select("select * from tb_cart where user_id = #{user_id}")
    List<Cart> findCartListByUserId(int user_id);

    /**
     * 添加商品到购物车
     */
    @Insert("insert into tb_cart(product_id, user_id, product_name, product_price, " +
            "product_quantity, product_style, product_photo) " +
            "values(#{product_id}, #{user_id}, #{product_name}, #{product_price}, " +
            "#{product_quantity}, #{product_style}, #{product_photo})")
    void addCart(Cart cart);

    @Select("SELECT * FROM tb_cart WHERE cart_id = #{cart_id}")
    Cart findCartById(int cart_id);
}