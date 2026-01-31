package controllers;

import repositories.interfaces.IReservationRepository; // Интерфейсті импорттау
import repositories.interfaces.IRoomRepository;        // Интерфейсті импорттау
import models.Room;
import models.User;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class HotelController {
    private IRoomRepository roomRepo;        // Нақты класс емес, интерфейс қолдану
    private IReservationRepository reservationRepo; 

    // Конструктор интерфейстерді қабылдауы керек
    public HotelController(IRoomRepository roomRepo, IReservationRepository reservationRepo) {
        this.roomRepo = roomRepo;
        this.reservationRepo = reservationRepo;
    }

    public String makeReservation(User user, int roomId, String dateFrom, String dateTo) {
        // RoomRepository-дегі әдіс атауы интерфейске сай getRoomById болуы тиіс
        Room room = roomRepo.getRoomById(roomId); 

        if (room == null) {
            return "Error: Room not found!";
        }

        boolean available = reservationRepo.isRoomAvailable(roomId, dateFrom, dateTo);

        if (!available) {
            return "Error: Room is already booked for these dates.";
        }

        LocalDate start = LocalDate.parse(dateFrom);
        LocalDate end = LocalDate.parse(dateTo);
        long days = ChronoUnit.DAYS.between(start, end);

        if (days <= 0) {
            return "Error: Check-out date must be after check-in date!";
        }

        double totalPrice = room.getPrice() * days;
        reservationRepo.createReservation(user.getId(), roomId, dateFrom, dateTo);

        return "\nRESERVATION DETAILS:" +
               "\nCustomer: " + user.getName() + " " + user.getSurname() +
               "\nPhone: " + user.getPhone() +
               "\nRoom Type: " + room.getType() +
               "\nStay duration: " + days + " nights" +
               "\nTotal Price: " + totalPrice + " KZT";
    }
}