package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.CartDataTransferObject;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.util.stream.Collectors;

public class CartDaoMem implements CartDao {
    private Map<Product, Integer> cartItems = new HashMap<>();
    ;

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
    public void add(Product product) {
        cartItems.merge(product, 1, Integer::sum);
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();

        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            content.append(entry);
            content.append(", ");
        }

        return content.toString();
    }

    public BigDecimal getTotalPrice() {
        List<BigDecimal> multiplies = new ArrayList<>();
        cartItems.forEach((k, v) -> {
            multiplies.add(k.getDefaultPrice().multiply(BigDecimal.valueOf(v)));
        });
        BigDecimal result = multiplies.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return result;
    }

    public List<CartDataTransferObject> convert() {
        return cartItems.entrySet()
                .stream()
                .map(productIntegerEntry -> new CartDataTransferObject(productIntegerEntry.getKey(), productIntegerEntry.getValue()))
                .collect(Collectors.toList());
    }
}
