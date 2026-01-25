package repositories;

import data.PostgresDB;
import models.User;
import java.sql.*;


public class UserRepository {
    public void createUser(User user){
        String sql="INSERT INTO users(name, surname, phone) VALUES(?, ?, ?)";
        try (Connection con=PostgresDB.getConnection()) {
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, user.getName());
             st.setString(2, user.getSurname());
              st.setString(3, user.getPhone());
              st.execute();
            
            
        } catch (Exception e) {
            System.out.println("Error SQL : "+ e.getMessage());

        }
    }
        public User getIdbyUser(int id){
            String sql="SELECT * FROM users WHERE id = ?";
            try(Connection con =PostgresDB.getConnection()){
                PreparedStatement st=con.prepareStatement(sql);
                st.setInt(1, id);
                ResultSet rs=st.executeQuery();
                if(rs.next()){
                    return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("phone")

                );
            }
        }
            catch(Exception e){
                System.out.println(e.getStackTrace());

            }
    return null;}

}

