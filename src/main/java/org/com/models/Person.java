package org.com.models;

public abstract class Person {
    private final String name;
    private final String email;
    private final String password;

    public Person(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public String getContactInfo() {
        return name + " (" + email + ")";
    }

}
