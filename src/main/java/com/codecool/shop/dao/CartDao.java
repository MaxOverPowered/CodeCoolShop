package com.codecool.shop.dao;

import com.codecool.shop.model.CartDataTransferObject;
import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.util.List;
public interface CartDao {
    void add(Product product);
    BigDecimal getTotalPrice();
    List<CartDataTransferObject> convert();
    void remove(int id);
}
