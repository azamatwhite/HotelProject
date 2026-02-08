package repositories;

import data.PostgresDB;
import models.User;
import repositories.interfaces.IUserRepository;
import java.sql.*;

public class UserRepository implements IUserRepository {

    @Override
    public User createUser(User user) {
        String sql = "INSERT INTO users(name, surname, phone, role) VALUES(?, ?, ?, ?)";
        try {
            Connection con = PostgresDB.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setString(3, user.getPhone());
            st.setString(4, "CLIENT");
            st.execute();
            
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserByPhone(String phone) {
        String sql = "SELECT * FROM users WHERE phone = ?";
        try {
            Connection con = PostgresDB.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, phone);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("phone"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
