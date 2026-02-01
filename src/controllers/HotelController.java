package controllers;

import repositories.interfaces.IReservationRepository;
import repositories.interfaces.IRoomRepository;
import models.Room;
import models.User;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;

public class HotelController {
    private IRoomRepository roomRepo;
    private IReservationRepository reservationRepo;

    public HotelController(IRoomRepository roomRepo, IReservationRepository reservationRepo) {
      this.roomRepo=roomRepo;
      this.reservationRepo=reservationRepo;
    }

    public List<Room> getAvailableRooms(String dateFrom, String dateTo) {
        List<Room> allRooms = roomRepo.getAllRooms();
        List<Room> availableRooms = new ArrayList<>();

        for (Room room : allRooms) {
            if (reservationRepo.isRoomAvailable(room.getId(), dateFrom, dateTo)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public String makeReservation(User user,int roomId, String dateFrom, String dateTo) {
      Room room=roomRepo.getRoomById(roomId);

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