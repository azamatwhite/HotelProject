package com.hotel.repositories;

import com.hotel.data.PostgresDB;
import com.hotel.models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public void createUser(User user) {
        String sql = "INSERT INTO users(name, surname, phone) VALUES(?, ?, ?)";
        
        try (Connection con = PostgresDB.getConnection()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            // Если в User нет getPhone, добавь его в модель!
            st.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка SQL: " + e.getMessage());
        }
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection con = PostgresDB.getConnection()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
