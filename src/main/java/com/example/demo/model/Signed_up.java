package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Signed_up {

    @Id
    private int id;
    private String fk_room_id;
    private String day_of_week;

    public Signed_up(){

    }

    public Signed_up(int id, String fk_room_id, String day_of_week) {
        this.id = id;
        this.fk_room_id = fk_room_id;
        this.day_of_week = day_of_week;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFk_room_id() {
        return fk_room_id;
    }

    public void setFk_room_id(String fk_room_id) {
        this.fk_room_id = fk_room_id;
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }
}
