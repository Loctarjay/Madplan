package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Weekplans {
    @Id
    private int week_number;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;

    public Weekplans() {
    }

    public Weekplans(int week_number) {
        this.week_number = week_number;
    }

    public Weekplans(int week_number, String day, String room_id) {
        this.week_number = week_number;
        setDay(day, room_id);
    }

    private void setDay(String day, String room_id){
        switch (day) {
            case "monday":
                setMonday(room_id);
                break;
            case "tuesday":
                setTuesday(room_id);
                break;
            case "wednesday":
                setWednesday(room_id);
                break;
            case "thursday":
                setThursday(room_id);
                break;
            case "friday":
                setFriday(room_id);
                break;
            case "saturday":
                setSaturday(room_id);
                break;
            case "sunday":
                setSunday(room_id);
                break;
        }
    }

    public int getWeek_number() {
        return week_number;
    }

    public void setWeek_number(int week_number) {
        this.week_number = week_number;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }
}
