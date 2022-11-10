package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

public class OrderDaoMem implements OrderDao {
    private Order order;
    private OrderDaoMem orderDaoMem;
    private static OrderDaoMem instance = null;


    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Order order) {
    }


}