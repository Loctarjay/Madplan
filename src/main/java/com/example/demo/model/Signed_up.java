package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Signed_up {

    @Id
    private int id;
    private String fk_room_id;
    private String chosen_date;
    private String day;
    private String week_number;

    public Signed_up(){

    }

    public Signed_up(String fk_room_id, String day, String week_number) {
        this.fk_room_id = fk_room_id;
        this.day = day;
        this.week_number = week_number;
    }

    public Signed_up(int id, String fk_room_id, String chosen_date) {
        this.fk_room_id = fk_room_id;
        this.chosen_date = chosen_date;
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

    public String getChosen_date() {
        return chosen_date;
    }

    public void setChosen_date(String chosen_date) {
        this.chosen_date = chosen_date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeek_number() {
        return week_number;
    }

    public void setWeek_number(String week_number) {
        this.week_number = week_number;
    }
}
