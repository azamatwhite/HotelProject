package repositories;

import data.PostgresDB;
import models.Room;
import java.sql.*;
public class RoomRepository {

   public List<Room> getAllRooms(){
    List<Room> rooms=new ArrayList<>();
     String sql = "SELECT * FROM rooms ORDER BY room_number";
    try (Connection con=PostgresDB.getConnection()) {
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(sql);
        while (rs.next()) {
            rooms.add(new Room(
                rs.getInt("id"),
                rs.getInt("room_number"),
                rs.getString("type"),
                rs.getDouble("price")

            ));
            
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    return rooms;
   }
    
   public Room getRoomByid(int id){
   String sql = "SELECT * FROM rooms WHERE id = ?";
    try (Connection con=PostgresDB.getConnection()) {
        PreparedStatement st=con.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            return new Room(
             rs.getInt("id"),
            rs.getInt("room_number"),
            rs.getString("type"),
            rs.getDouble("price")
            );
        }
        
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;

   }
}
