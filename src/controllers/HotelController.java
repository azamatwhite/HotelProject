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

    public String makeReservation(User user,int roomId,int nights, String dateFrom, String dateTo) {
      Room room=roomRepo.getRoomByid(roomId);

      if(room==null){
        return "Error : room not fount !";
      }
      LocalDate start = LocalDate.parse(dateFrom);
      LocalDate end = LocalDate.parse(dateTo);

      long days = ChronoUnit.DAYS.between(start, end);

      // Проверка на адекватность
      if (days <= 0) {
        return "Ошибка: Дата выезда должна быть позже даты заезда!";
      }

      double totalPrice=room.getPrice()*nights;

      reservationRepo.createReservation(user.getId(), roomId, dateFrom, dateTo);

    return "\nRESERVATION DETAILS:" +
               "\nCustomer: " + user.getName() + " " + user.getSurname() +
               "\nPhone: " + user.getPhone() +
               "\nRoom Type: " + room.getType() +
               "\nStay duration: " + nights + " nights" +
               "\nTotal Price: " + totalPrice + " KZT";


    }
}