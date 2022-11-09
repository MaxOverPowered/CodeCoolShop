package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
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



@WebServlet(name = "cartPost", urlPatterns = {"/api/add"})
public class PostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //this is a way to get payload from a post request
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append(System.lineSeparator());
        }
        String data = buffer.toString();

        //this is converting from json object(we can just take a class, which has a getId() method, or create a new class
        // with the function for returning a desired variable
        Gson gson = new Gson();
        BaseModel convertedData = gson.fromJson(data, BaseModel.class);

        Product clicked = ProductDaoMem.getInstance().find(convertedData.getId()); // this is the id
        CartItem cartItem = new CartItem(clicked);
        CartDaoMem shoppingCart = CartDaoMem.getInstance();
        shoppingCart.add(cartItem);
    }
}