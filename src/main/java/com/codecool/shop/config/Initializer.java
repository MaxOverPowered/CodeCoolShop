package com.codecool.shop.config;

import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

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

        ProductDao productDataStore = ProductDaoMem.getInstance();
        List<Product> productDataList=new ArrayList<>();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        List<ProductCategory> productCategoryList = new ArrayList<>();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
//        Supplier lenovo = new Supplier("Lenovo", "Computers");
//        supplierDataStore.add(lenovo);
//        Supplier dell = new Supplier("Dell", "Computers");
//        supplierDataStore.add(dell);
//        Supplier asus = new Supplier("Asus", "Computers");
//        supplierDataStore.add(asus);
//        Supplier wilson = new Supplier("Wilson Benesch", "Speakers");
//        supplierDataStore.add(wilson);
//        Supplier fyne = new Supplier("Fyne", "Speakers");
//        supplierDataStore.add(fyne);
//
//        User user = new User(1, "sss", "emailulLuiValerica");
        try {
            databaseManager.setup();
            productDataList=  databaseManager.getProductDataStore();
            productCategoryList=databaseManager.getProductCategoryDataStore();
            System.out.println(productDataList);
            for (Product product:productDataList) {
                System.out.println(product);
                productDataStore.add(product);
            }
            for (ProductCategory category:productCategoryList) {
                productCategoryDataStore.add(category);
            }
//            databaseManager.addNewUser(user);

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }


        //setting up a new product category
//        ProductCategory laptops = new ProductCategory("Laptops", "Hardware");
//        productCategoryDataStore.add(laptops);
//        ProductCategory speakers = new ProductCategory("Speakers", "Hardware");
//        productCategoryDataStore.add(speakers);
        //setting up products and printing it
//        productDataStore.add(new Product("Lenovo ThinkPad X1 Carbon Gen 9", new BigDecimal("2111"), "USD", "Intel Core i5-1135G7, 16GB RAM, 256GB SSD, Intel Iris Xe Graphics, Windows 10 Pro", laptops, lenovo, "lenovo"));
//        productDataStore.add(new Product("Dell XPS 13 Plus 9320", new BigDecimal("2360"), "USD", "Superb 4K screen and powerful 12th-generation Intel Core i7 processor, plus 16GB (gigabytes) of RAM (memory) and 512GB of very fast solid-state drive (SSD) storage.", laptops, dell, "dell"));
//        productDataStore.add(new Product("ASUS ROG Flow X13", new BigDecimal("1200"), "USD", "Powerful gaming has never been so flexible with up to the latest Ryzen 9 5980HS CPU and GeForce RTX 3080", laptops, asus, "asus"));
//        productDataStore.add(new Product("Lenovo Legion 5 Pro", new BigDecimal("1503"), "USD", "Inside the Legion 5 Pro is an Intel Core i7–12700H with 14 cores and 20 threads, an Nvidia RTX 3070 Ti with 8GB of GDDR6 memory, a 512GB NVMe SSD for storage and 16GB of DDR5 4,800Mhz memory. ", laptops, lenovo, "lenovo2"));
//        productDataStore.add(new Product("Dell XPS 17", new BigDecimal("1849"), "USD", "Dell XPS 17 laptop featuring 12th Gen Intel Core processors, up to NVIDIA GeForce RTX 3060 graphics. ", laptops, dell, "dell2"));
//        productDataStore.add(new Product("Wilson Benesch Precision P2.0", new BigDecimal("14000"), "USD", "The Precision P2.0 are available in a wide range of finish options for the side panels, varying from real wood veneers to painted (including a rather fetching green), so there's likely to be something that suits most living environments.", speakers, wilson, "wilsonbenesch"));
//        productDataStore.add(new Product("Fyne F501", new BigDecimal("1499"), "USD", "The F500 range consists of the F500 standmounting design (plus matching stands), two pairs of floorstanders (these F501s and the bigger, more expensive F502s), a centre speaker (F500C) and the F500FX dipole intended for use as rear speakers in a surround-sound set-up. And Fyne has a range of three subwoofers too.", speakers, fyne, "fyne"));
//        productDataStore.add(new Product("Fyne Audio F302i", new BigDecimal("745"), "USD", "These are accommodating speakers in more than just size, too. They’re pretty open about room positioning so long as you don’t stuff them into a corner or right up against a wall. A little bit of toe-in towards the main listening position helps to add a degree of focus to the presentation, but the precise amount isn’t particularly critical to get a good, stable stereo image.", speakers, fyne, "fyne2"));
    }
}