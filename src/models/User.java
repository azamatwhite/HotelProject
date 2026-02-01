package models;

public class User {
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String role;

    public User(int id, String name, String surname, String phone, String role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.role = role;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getPhone() { return phone; }
    public String getRole() { return role; }
}