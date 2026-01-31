package repositories;

import data.PostgresDB;
import models.Room;
import repositories.interfaces.IRoomRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository implements IRoomRepository { // IRoomRepository қосылды
    
    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms ORDER BY room_number";

        // getConnection() әдісі статикалық емес, сондықтан getInstance() арқылы шақырылады
        try (Connection con = PostgresDB.getInstance().getConnection()) { 
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                rooms.add(new Room(
                    rs.getInt("id"),
                    rs.getInt("room_number"),
                    rs.getString("type"),
                    rs.getDouble("price")
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rooms;
    }

    @Override
    public Room getRoomById(int id) {
        String sql = "SELECT * FROM rooms WHERE id = ?";
        try (Connection con = PostgresDB.getInstance().getConnection()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
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