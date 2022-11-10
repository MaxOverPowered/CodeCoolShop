package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import com.codecool.shop.model.Product;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PaymentServlet", urlPatterns = {"/payment"}, loadOnStartup = 5)
public class PaymentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("category", productService.getProductCategory(1));
        context.setVariable("products", productService.getProductsForCategory(1));
        engine.process("product/payment.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        engine.process("product/success.html", context, response.getWriter());
    }
}