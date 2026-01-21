package models;

public class Room {
    private int id;
    private int roomNumber;
    private String type;
    private double price;

    public Room() {
    }

    public Room(int id, int roomNumber, String type, double price) {
        setId(id);
        setRoomNumber(roomNumber);
        setType(type);
        setPrice(price);
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Room #" + roomNumber + " [" + type + "] - " + price + " KZT";
    }
}