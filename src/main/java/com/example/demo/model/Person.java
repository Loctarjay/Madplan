package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {
    @Id
    private String room_id;
    private String first_name;
    private String last_name;
    private String password;
    private String allergies;
    private int last_cooked;

    public Person(){    }

    public Person(String room_id, String password, int last_cooked){
        this.room_id = room_id;
        this.password = password;
        this.last_cooked = last_cooked;
    }

    public Person(String room_id, String first_name, String last_name, String password, String allergies, int last_cooked){
        this.room_id = room_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.allergies = allergies;
        this.last_cooked = last_cooked;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public int getLast_cooked() {
        return last_cooked;
    }

    public void setLast_cooked(int last_cooked) {
        this.last_cooked = last_cooked;
    }
}
