package com.example.demo.model;

public class testObject {
    private int week_number;
    private String day;
    private String fk_room_id;
    private String first_name;
    private String dinner_name;
    private String description;

    public testObject() {
    }

    public testObject(String fk_room_id, int week_number, String day) {
        this.week_number = week_number;
        this.day = day;
        this.fk_room_id = fk_room_id;
    }

    public testObject(int week_number, String day, String fk_room_id, String first_name, String dinner_name, String description) {
        this.week_number = week_number;
        this.day = day;
        this.fk_room_id = fk_room_id;
        this.first_name = first_name;
        this.dinner_name = dinner_name;
        this.description = description;
    }

    public int getWeek_number() {
        return week_number;
    }

    public void setWeek_number(int week_number) {
        this.week_number = week_number;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFk_room_id() {
        return fk_room_id;
    }

    public void setFk_room_id(String fk_room_id) {
        this.fk_room_id = fk_room_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
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
