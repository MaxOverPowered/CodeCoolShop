package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {

    private static DataSource dataSource = null;

    private static SupplierDaoJdbc instance = null;

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc(dataSource);
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT suplier_name FROM supplier WHERE suplier_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return null;
            Supplier supplier = new Supplier(resultSet.getString(1));
//            logger.info("Successfully found supplier");
            return supplier;
        } catch (SQLException e) {
//            logger.warn("Runtime exception was thrown");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT suplier_id,suplier_name FROM supplier";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            List<Supplier> result = new ArrayList<>();
            while (resultSet.next()) {
                int suplierId = resultSet.getInt(1);
                Supplier supplier = new Supplier(resultSet.getString(2));
                supplier.setId(suplierId);
                result.add(supplier);
            }
//            logger.info("Successfully found all suppliers");
            return result;
//        }
//        catch (SQLException e) {
//            logger.warn("Runtime exception was thrown");
//            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
