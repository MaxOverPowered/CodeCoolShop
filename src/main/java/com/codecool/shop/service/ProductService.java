package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

<<<<<<< HEAD
public class ProductService {
=======
public class ProductService{
>>>>>>> cdc4cebda8b11f8861073ec00b2d5533038d7def
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
    }

<<<<<<< HEAD
    public ProductCategory getProductCategory(int categoryId) {
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId) {
=======
    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
>>>>>>> cdc4cebda8b11f8861073ec00b2d5533038d7def
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

<<<<<<< HEAD
    public List<Product> getAllProduct() {
        return productDao.getAll();
    }

    public List<ProductCategory> getAllCategory() {
        return productCategoryDao.getAll();
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public List<ProductCategory> getAllCategories() {
        return productCategoryDao.getAll();
    }

=======
>>>>>>> cdc4cebda8b11f8861073ec00b2d5533038d7def

}
