package com.codecool.shop.dao;

import com.codecool.shop.model.CartItem;

public interface CartDao {
    void add(CartItem cartItem);
    void remove(int id);
}
