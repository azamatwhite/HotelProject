package controllers;

import repositories.ReservationRepository;
import repositories.RoomRepository;
import models.Room;
import models.User;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class HotelController {
    private RoomRepository roomRepo;
    private ReservationRepository reservationRepo;

    public HotelController(RoomRepository roomRepo, ReservationRepository reservationRepo) {
      this.roomRepo=roomRepo;
      this.reservationRepo=reservationRepo;
    }

    public String makeReservation(User user,int roomId, String dateFrom, String dateTo) {
        Room room=roomRepo.getRoomByid(roomId);

        if(room==null){
        return "Error : room not found!";
        }

        boolean available = reservationRepo.isRoomAvailable(roomId, dateFrom, dateTo);

        if (!available) {
            return "Error: Room is already booked for this dates.";
        }
        LocalDate start = LocalDate.parse(dateFrom);
        LocalDate end = LocalDate.parse(dateTo);

        long days = ChronoUnit.DAYS.between(start, end);

        if (days <= 0) {
            return "Error: Check out date must be after check in date!";
        }

        double totalPrice=room.getPrice() * days;

        reservationRepo.createReservation(user.getId(), roomId, dateFrom, dateTo);

        return "\nRESERVATION DETAILS:" +
               "\nCustomer: " + user.getName() + " " + user.getSurname() +
               "\nPhone: " + user.getPhone() +
               "\nRoom Type: " + room.getType() +
               "\nStay duration: " + days + " nights" +
               "\nTotal Price: " + totalPrice + " KZT";


        }
}