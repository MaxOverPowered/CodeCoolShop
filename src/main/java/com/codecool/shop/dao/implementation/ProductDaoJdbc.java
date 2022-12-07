package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoJdbc implements ProductDao {


    private final DataSource dataSource;

    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
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
        List product_list = new ArrayList();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * from products";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSetMetaData metaData = st.getMetaData();
            int count = metaData.getColumnCount();

            ResultSet set = st.executeQuery();
            while (set.next()) {
                Map resultMap = new HashMap();
                for (int i = 0; i < count; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    resultMap.put(columnName, set.getObject(i + 1));
                }
                product_list.add(resultMap);
            }
//                String product_name = set.getString("product_name");
//                String product_defaultPrice = set.getString("product_defaultprice");
//                String product_currency = set.getString("product_currency");
//                String product_description = set.getString("product_description");
//                int product_categoryId = set.getInt("product_category_id");
//                int product_supplierId = set.getInt("product_supplier_id");
//                String product_picture = set.getString("product_picture");
//                System.out.print(product_name+product_defaultPrice+product_picture+product_currency+product_description+product_categoryId+product_supplierId);

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting all products  \"" + "\". Error type: ", e);

        }
        return product_list;
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
