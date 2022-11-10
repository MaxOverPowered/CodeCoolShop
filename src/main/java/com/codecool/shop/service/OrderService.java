package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.util.List;

public class OrderService {
    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> getOrder(int id){
        return null;
    }
}