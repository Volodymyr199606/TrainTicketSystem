package org.com.models;

public class User extends Person {
    private int id;

    public User(int id, String name, String email, String password) {
        super(name, email, password);
        this.id = id;

    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }


    @Override
    public String toString() {
        return "User{id=" + id +
                ", contact='" + getContactInfo() + '\'' +
                ", role='" + '\'' +
                '}';
    }
}
