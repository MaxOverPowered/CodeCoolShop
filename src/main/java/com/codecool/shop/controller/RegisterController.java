package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"}, loadOnStartup = 5)
public class RegisterController extends HttpServlet {
    DatabaseManager databaseManager = new DatabaseManager();

    public RegisterController() throws IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("product/register.html", context, resp.getWriter());


//        User user = new User();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("psw");
        User user = new User(username, password, email);
        try {
            databaseManager.setup();
            databaseManager.addNewUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/");

    }
}
