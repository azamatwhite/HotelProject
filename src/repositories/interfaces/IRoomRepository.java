package repositories.interfaces;
import models.Room;
import java.util.List;

public interface IRoomRepository {
    List<Room> getAllRooms();
    Room getRoomById(int id);
}