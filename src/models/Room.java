package models;

public class Room {
    private int id;
    private int roomNumber;
    private String type;
    private double price;
public Room(){}
public Room(int id,int roomNumber,String type,double price){
setId(id);
setPrice(price);
setRoomNumber(roomNumber);
setType(type);
}
public int getId() {
    return id;
}
public double getPrice() {
    return price;
}
public String getType() {
    return type;
}
public int getRoomNumber() {
    return roomNumber;
}
public void setId(int id) {
    this.id = id;
}
public void setPrice(double price) {
    this.price = price;
}
public void setRoomNumber(int roomNumber) {
    this.roomNumber = roomNumber;
}
public void setType(String type) {
    this.type = type;

}
 @Override
public String toString() {
   
    return "ID: " + id + " | Room #" + roomNumber + " [" + type + "] - " + price + " KZT";
}
}