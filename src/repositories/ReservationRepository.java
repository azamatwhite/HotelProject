package repositories;

import data.PostgresDB;
import repositories.interfaces.IReservationRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository implements IReservationRepository {

    @Override
    public List<String> getAllReservationsFullInfo() {
        List<String> details = new ArrayList<>();
        // Объединяем 4 таблицы: reservations, users, rooms, categories
        String sql = "SELECT r.id, u.name, u.surname, rm.room_number, c.name as category, r.check_in, r.check_out " +
                     "FROM reservations r " +
                     "JOIN users u ON r.user_id = u.id " +
                     "JOIN rooms rm ON r.room_id = rm.id " +
                     "JOIN categories c ON rm.category_id = c.id";

        try {
            Connection con = PostgresDB.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                details.add(String.format("Res #%d: %s %s | Room %d (%s) | %s - %s",
                        rs.getInt("id"),
                        rs.getString("name"), rs.getString("surname"),
                        rs.getInt("room_number"), rs.getString("category"),
                        rs.getDate("check_in"), rs.getDate("check_out")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return details;
    }

    @Override
    public void createReservation(int userId, int roomId, String dateFrom, String dateTo) {
        String sql = "INSERT INTO reservations(user_id, room_id, check_in, check_out) VALUES(?, ?, ?, ?)";
        try {
            Connection con = PostgresDB.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, roomId);
            st.setDate(3, Date.valueOf(dateFrom));
            st.setDate(4, Date.valueOf(dateTo));
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Booking error: " + e.getMessage());
        }
    }

    @Override
    public boolean isRoomAvailable(int roomId, String dateFrom, String dateTo) {
        String sql = "SELECT * FROM reservations WHERE room_id = ? AND check_in < ? AND check_out > ?";
        try {
            Connection con = PostgresDB.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, roomId);
            st.setDate(2, Date.valueOf(dateTo));
            st.setDate(3, Date.valueOf(dateFrom));
            ResultSet rs = st.executeQuery();
            return !rs.next(); // Если записи есть, комната занята
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}




































//
