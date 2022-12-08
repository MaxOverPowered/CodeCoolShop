package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


@WebServlet(name = "cartPost", urlPatterns = {"/api/cartitem"})
public class CartItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String url = request.getRequestURL().toString();

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append(System.lineSeparator());
        }
        String data = buffer.toString();


        Gson gson = new Gson();
        BaseModel convertedData = gson.fromJson(data, BaseModel.class);
        Product clicked = ProductDaoJdbc.getInstance().find(convertedData.getId()); // this is the id
        CartItem cartItem = new CartItem(clicked);
        CartDaoMem shoppingCart = CartDaoMem.getInstance();
        shoppingCart.add(cartItem);
    }
}
