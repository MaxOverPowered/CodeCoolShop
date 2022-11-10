package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/api/category")
public class CategoryFilterServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            ProductCategoryDaoMem productCategoryDaoMem = ProductCategoryDaoMem.getInstance();
            ProductDaoMem productDaoMem = ProductDaoMem.getInstance();

            ProductCategory category = productCategoryDaoMem.find(categoryId);

            List<Product> productList = productDaoMem.getBy(category);
            List<HashMap<String, String>> products = new ArrayList<>();

            for (Product product : productList) {
                HashMap<String, String> items = new HashMap();
                items.put("id", String.valueOf(product.getId()));
                items.put("name", String.valueOf(product.getName()));
                items.put("description", String.valueOf(product.getDescription()));
                items.put("defaultPrice", String.valueOf(product.getDefaultPrice()));
                items.put("defaultCurrency", String.valueOf(product.getDefaultCurrency()));
                items.put("category", String.valueOf(product.getProductCategory()));
                items.put("supplier", String.valueOf(product.getSupplier()));
                items.put("image", String.valueOf(product.getPicture()));
                products.add(items);
            }

            Gson gson = new Gson();
            String fullJson = gson.toJson(products);

            PrintWriter out = response.getWriter();
            out.println(fullJson);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}