package models;

public class Room {
    private int id;
    private static int id_gen = 1;
    private int roomNumber;
    private String type;
    private int price;

    public Room() {
        id = id_gen++;
    }

    public Room(int roomNumber, String type, int price) {
        this();
        setRoomNumber(roomNumber);
        setType(type);
        setPrice(price);
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

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Room #" + roomNumber + " [" + type + "] - " + price + " KZT";
    }
}