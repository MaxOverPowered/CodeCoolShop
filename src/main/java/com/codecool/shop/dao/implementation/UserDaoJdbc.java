package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJdbc implements UserDao {
    private final DataSource dataSource;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO users(username,password,email) VALUES (?,?,?)";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user.getName());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());
//            st.setString(3, user.getEmail());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding user \"" + user.getName() + "\". Error type: ", e);
        }
    }

    @Override
    public User find(String email) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "Select * FROM users WHERE user_email=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return new User(rs.getString(2), rs.getString(3), rs.getString(4));
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading user with email \"" + email + "\". Error type: ", e);
        }
    }

    @Override
    public String check(String username,String password) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "Select * FROM users WHERE username=? AND password=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return rs.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading category with id: ", e);
        }
    }
}
