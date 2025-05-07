package com.example.kontaktbog;

import java.io.Serializable;

public class Contact implements Serializable {
    private String Name, Email, Phone;

    public Contact(String name, String email, String phone) {
        this.Name = name;
        this.Email = email;
        this.Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getPhone() {
        return Phone;
    }
    public void setPhone(String phone) {
        Phone = phone;
    }
}
