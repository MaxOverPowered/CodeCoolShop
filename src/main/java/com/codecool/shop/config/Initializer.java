package com.codecool.shop.config;

import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryJdbc;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class Initializer implements ServletContextListener {
    DatabaseManager databaseManager = new DatabaseManager();

    public Initializer() throws IOException {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ProductDao productDataStore = ProductDaoJdbc.getInstance();
        List<Product> productDataList = new ArrayList<>();
        ProductCategoryDao productCategoryDataStore = ProductCategoryJdbc.getInstance();
        List<ProductCategory> productCategoryList = new ArrayList<>();
        SupplierDao supplierDataStore = SupplierDaoJdbc.getInstance();
        List<Supplier> supplierList = new ArrayList<>();
        try {
            databaseManager.setup();
            productDataList = databaseManager.getProductDataStore();
            productCategoryList = databaseManager.getProductCategoryDataStore();
            supplierList = databaseManager.getSupplierDataStore();
            System.out.println(productDataList);
            for (Product product : productDataList) {
                System.out.println(product);
                productDataStore.add(product);
            }
            for (ProductCategory category : productCategoryList) {
                productCategoryDataStore.add(category);
            }
            for (Supplier supplier : supplierList) {
                supplierDataStore.add(supplier);
            }
//            databaseManager.addNewUser(user);

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}