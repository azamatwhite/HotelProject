package models;

public class Room {
    private int id;
    private int roomNumber;
    private String type;
    private double price;

 public Room(){}
 public Room(int id,int room_number,String type,double price){
    setId(id);
    setPrice(price);
    setRoomNumber(room_number);
    setType(type);
 }
 public int getId() {
     return id;
 }
 public void setId(int id) {
     this.id = id;
 }
 public double getPrice() {
     return price;
 }
 public void setPrice(double price) {
     this.price = price;
 }
 public int getRoomNumber() {
     return roomNumber;
 }
 public void setRoomNumber(int roomNumber) {
     this.roomNumber = roomNumber;
 }
 public String getType() {
     return type;
 }
 public void setType(String type) {
     this.type = type;
 }
    @Override
    public String toString() {
        return "Room #" + roomNumber + " [" + type + "] - " + price + " KZT";
    }
}