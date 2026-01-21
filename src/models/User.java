package models;

public class User {
    private int id;
    private String name;
    private String surname;
    private String phone;

    public User() {
    }

    public User(int id, String name, String surname, String phone) {
        setId(id);
        setName(name);
        setSurname(surname);
        setPhone(phone);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return id + ". " + name + " " + surname + " " + " (" + phone + ")";
    }
}