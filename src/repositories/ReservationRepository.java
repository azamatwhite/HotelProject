package repositories;

import data.PostgresDB;
import java.sql.*;

public class ReservationRepository {
    public void createReservation(int userId, int roomId, String dateFrom, String dateTo) {
        String sql = "INSERT INTO reservations(user_id, room_id, check_in, check_out) VALUES(?, ?, ?, ?)";

        try (Connection con = PostgresDB.getConnection()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, roomId);
            st.setDate(3, Date.valueOf(dateFrom));
            st.setDate(4, Date.valueOf(dateTo));
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error, cannot book: " + e.getMessage());
        }
    }

    public boolean isRoomAvailable(int roomId, String dateFrom, String dateTo) {
        String sql = "SELECT * FROM reservations WHERE room_id = ? " +
                "AND check_in < ? AND check_out > ?";

        try (Connection con = PostgresDB.getConnection()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, roomId);

            st.setDate(2, Date.valueOf(dateTo));
            st.setDate(3, Date.valueOf(dateFrom));

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}