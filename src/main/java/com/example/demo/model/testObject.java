package com.example.demo.model;

public class testObject {
    private int week_number;
    private String fk_room_id;
    private String dinner_name;
    private String description;

    public testObject() {
    }

    public testObject(int week_number, String fk_room_id, String dinner_name, String description) {
        this.week_number = week_number;
        this.fk_room_id = fk_room_id;
        this.dinner_name = dinner_name;
        this.description = description;
    }

    public int getWeek_number() {
        return week_number;
    }

    public void setWeek_number(int week_number) {
        this.week_number = week_number;
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
}
