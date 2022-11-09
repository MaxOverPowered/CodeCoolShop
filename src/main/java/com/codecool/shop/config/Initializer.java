package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier dell = new Supplier("Dell", "Computers");
        supplierDataStore.add(dell);
        Supplier asus = new Supplier("Asus", "Computers");
        supplierDataStore.add(asus);

        //setting up a new product category
        ProductCategory laptops = new ProductCategory("Laptops", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(laptops);
        ProductCategory speakers = new ProductCategory("Speakers", "Hardware", "A speaker is one type of electroacoustic transducer, that is, a device that converts a electrical audio signal into a corresponding sound.");
        productCategoryDataStore.add(speakers);
        //setting up products and printing it
        productDataStore.add(new Product("Lenovo ThinkPad X1 Carbon Gen 9", new BigDecimal("2111"), "USD", "Intel Core i5-1135G7, 16GB RAM, 256GB SSD, Intel Iris Xe Graphics, Windows 10 Pro", laptops, lenovo, "lenovo"));
        productDataStore.add(new Product("Dell XPS 13 Plus 9320", new BigDecimal("2360"), "USD", "Superb 4K screen and powerful 12th-generation Intel Core i7 processor, plus 16GB (gigabytes) of RAM (memory) and 512GB of very fast solid-state drive (SSD) storage.", laptops, dell, "dell"));
        productDataStore.add(new Product("ASUS ROG Flow X13", new BigDecimal("1200"), "USD", "Powerful gaming has never been so flexible with up to the latest Ryzen 9 5980HS CPU and GeForce RTX 3080", laptops, asus, "asus"));
    }
}
