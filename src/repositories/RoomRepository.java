package repositories;

import data.PostgresDB;
import models.Room;
import repositories.interfaces.IRoomRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository implements IRoomRepository {

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT r.id, r.room_number, r.price, c.name as category_name " +
                "FROM rooms r " +
                "JOIN categories c ON r.category_id = c.id " +
                "ORDER BY r.room_number";

        try (Connection con = PostgresDB.getInstance().getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("id"),
                        rs.getInt("room_number"),
                        rs.getString("category_name"),
                        rs.getDouble("price")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error getting rooms: " + e.getMessage());
        }
        return rooms;
    }

    @Override
    public Room getRoomById(int id) {
        String sql = "SELECT r.id, r.room_number, r.price, c.name as category_name " +
                "FROM rooms r " +
                "JOIN categories c ON r.category_id = c.id " +
                "WHERE r.id = ?";

        try (Connection con = PostgresDB.getInstance().getConnection()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Room(
                        rs.getInt("id"),
                        rs.getInt("room_number"),
                        rs.getString("category_name"),
                        rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error getting room by id: " + e.getMessage());
        }
        return null;
    }
}