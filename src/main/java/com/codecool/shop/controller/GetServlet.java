package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(name = "cartPost", urlPatterns = {"/api/add"})
public class GetServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Product clicked = ProductDaoMem.getInstance().find(Integer.parseInt(id));
        CartItem cartItem = new CartItem(clicked);
        CartDaoMem shoppingCart = CartDaoMem.getInstance();
        shoppingCart.add(cartItem);
        System.out.println(shoppingCart);
    }
}