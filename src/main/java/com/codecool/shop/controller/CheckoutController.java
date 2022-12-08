package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.CartDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {
public int id;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        CartDaoMem cart = CartDaoMem.getInstance();
        this.id = ThreadLocalRandom.current().nextInt();
        context.setVariable("order_id", id);

        context.setVariable("products", cart.convert());
        context.setVariable("total", cart.getTotalPrice());

        engine.process("product/checkout.html", context, resp.getWriter());
    }
}