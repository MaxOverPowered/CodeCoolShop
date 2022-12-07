package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class ProductDaoJdbc implements ProductDao {

    private static ProductDaoJdbc instance = null;
    private static DataSource dataSource = null;

    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc(dataSource);
        }
        return instance;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT product_name,product_defaultprice,product_currency,product_description,category.category_name,category.category_description,supplier.suplier_name,product_picture from products\n" +
                    "inner join category on product_category_id=category.category_id\n" +
                    "inner join supplier on product_supplier_id=supplier.suplier_id";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> result = new ArrayList<>();
            if (!rs.next()) return null;
            while (rs.next()) {
                Product product = new Product(rs.getString(1), new BigDecimal(rs.getFloat(2)), rs.getString(3), rs.getString(4), new ProductCategory(rs.getString(5), rs.getString(6)), new Supplier(rs.getString(7)),rs.getString(8));
                System.out.println(product);
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products", e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
