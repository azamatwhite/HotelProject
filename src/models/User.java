package models;

public class User {
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String role; // "role" өрісі болуы шарт

    // Конструктор 5 аргументті болуы керек
    public User(int id, String name, String surname, String phone, String role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.role = role;
    }

    // Геттерлер мен Сеттерлер
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public String getRole() { return role; } // Бұл әдіс Main-де қолданылады
    public String getSurname() { return surname; }
    public String getPhone() { return phone; }
}