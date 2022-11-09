package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartDaoMem implements CartDao {
    private Map<Product, Integer> cartItems = new HashMap<>();;

    private static CartDaoMem instance = null;

    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(CartItem cartItem) {
        cartItems.merge(cartItem.getProduct(), 1, Integer::sum);
    }

    @Override
    public void remove(int id) {

    }
    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();

        for(Map.Entry<Product, Integer> entry: cartItems.entrySet()) {
            content.append(entry);
            content.append(", ");
        }

        return content.toString();
    }

}