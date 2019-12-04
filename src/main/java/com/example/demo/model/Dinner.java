package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dinner {
    @Id
    private int id;
    private String fk_room_id;
    private String dinner_name;
    private String description;
    private String chosen_date;

    public Dinner() {
    }

    public Dinner(String fk_room_id, String dinner_name, String description) {
        this.fk_room_id = fk_room_id;
        this.dinner_name = dinner_name;
        this.description = description;
    }

    public Dinner(int id, String fk_room_id, String dinner_name, String description, String chosen_date) {
        this.id = id;
        this.fk_room_id = fk_room_id;
        this.dinner_name = dinner_name;
        this.description = description;
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

    public String getDinner_name() {
        return dinner_name;
    }

    public void setDinner_name(String dinner_name) {
        this.dinner_name = dinner_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChosen_date() {
        return chosen_date;
    }

    public void setChosen_date(String chosen_date) {
        this.chosen_date = chosen_date;
    }


}
