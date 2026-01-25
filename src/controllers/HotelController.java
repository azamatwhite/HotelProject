package controllers;

import repositories.RoomRepository;
import models.Room;
import models.User;

public class HotelController {
private RoomRepository roomRepo;
public HotelController(RoomRepository roomRepo){
  this.roomRepo=roomRepo;
}
public String makeReservation(User user,int roomId,int nights){
  Room room=roomRepo.getRoomByid(roomId);

  if(room==null){
    return "Error : room not fount !";
  }
  Double totalPrice=room.getPrice()*nights;
return "\nRESERVATION DETAILS:" +
           "\nCustomer: " + user.getName() + " " + user.getSurname() +
           "\nPhone: " + user.getPhone() +
           "\nRoom Type: " + room.getType() + 
           "\nStay duration: " + nights + " nights" +
           "\nTotal Price: " + totalPrice + " KZT";


}
}