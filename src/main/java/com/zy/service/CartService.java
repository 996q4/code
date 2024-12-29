package com.zy.service;

import com.zy.pojo.Cart;
import java.util.List;

public interface CartService {
    int findCartCountByUserId(int user_id);

    List<Cart> findCartListByUserId(int user_id);

    Cart findCartById(int cart_id);

    void addCart(Cart cart);
}