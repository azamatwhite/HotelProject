package controllers;

import repositories.RoomRepository;
import models.Room;

public class HotelController {
  private RoomRepository roonRepo;
  public HotelController(RoomRepository roomRepo){
    this.roonRepo=roomRepo;
  };

    public String makeReservation(int roomId, int nights) {
        Room room=roomRepo.findRoomById(roomId);
        
        if (room==null) {
            return"Error: room not found!";
        }

    
        double totalPrice=room.getPrice() * nights;
        
        return "Order accepted! Room type: " + room.getType() + 
               "\nNumber of days: " + nights + 
               "\nTotal cost: "+totalPrice+" KZT";
    }
}