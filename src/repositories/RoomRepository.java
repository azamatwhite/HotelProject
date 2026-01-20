package repositories;

import models.Room;

public class RoomRepository {
    // Мико базаны бітіргенше, біз осындай уақытша әдіс жасаймыз
    public Room findRoomById(int id) {
        // Егер сен 1-ді енгізсең, саған автоматты түрде мына бөлме келеді
        if (id == 1) {
            return new Room(101, "Standard", 15000);
        } else if (id == 2) {
            return new Room(202, "Luxury", 35000);
        }
        return null; // Басқа ID болса, бөлме табылмады деп қайтарады
    }
}