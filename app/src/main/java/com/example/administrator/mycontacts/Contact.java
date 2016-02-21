package com.example.administrator.mycontacts;

/**
 * Created by Administrator on 2/21/2016.
 */
public class Contact {
    private int id; //id is an int woo
    private String name; //name's a string
    private String email; //pretty sure we dont have an email data type so it can be a string
    private String phone; //phone number stays a string since we do no math on it ever

    //This was all plain easy generated java code so I'm not bothering to comment on it
    public Contact(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
