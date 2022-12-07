package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryJdbc implements ProductCategoryDao {
    private static DataSource dataSource = null;
    private static ProductCategoryJdbc instance = null;

    public ProductCategoryJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ProductCategoryJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryJdbc(dataSource);
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        try (Connection conn = dataSource.getConnection();) {
            ResultSet rs;
            String sql = "select * from category where category_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(id, 1);
            rs = conn.createStatement().executeQuery(sql);
            String name =rs.getString(1);
            String department =rs.getString(2);
            ProductCategory productCategory = new ProductCategory(name,department);
            return productCategory;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection();) {
            ResultSet rs;
            String sql = "SELECT category_id,category_name, category_department, category_description FROM category";
            List<ProductCategory> result = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement(sql);
            rs=st.executeQuery();
            while (rs.next()) {
//                rs = conn.createStatement().executeQuery(sql);

                int categoryId = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(4);
//                String department = rs.getString(2);
                ProductCategory productCategory = new ProductCategory(name, description);
                productCategory.setId(categoryId);
                result.add(productCategory);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
