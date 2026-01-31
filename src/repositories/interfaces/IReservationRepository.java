package repositories.interfaces;
import java.util.List;

public interface IReservationRepository {
    void createReservation(int userId, int roomId, String dateFrom, String dateTo);
    boolean isRoomAvailable(int roomId, String dateFrom, String dateTo);
    List<String> getAllReservationsFullInfo(); // Для пункта 1 (JOIN)
}