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
    private String day;

    public Weekplans() {
    }

    public Weekplans(int week_number, String day){
        this.week_number = week_number;
        this.day = day;
    }

    public Weekplans(int week_number) {
        this.week_number = week_number;
    }

    public Weekplans(int week_number, String day, String cook) {
        this.week_number = week_number;
        this.day = day;
        setDayInfo(cook);
    }

    private void setDayInfo(String cook){
        switch (this.day) {
            case "monday":
                this.monday = cook;
                break;
            case "tuesday":
                this.tuesday = cook;
                break;
            case "wednesday":
                this.wednesday = cook;
                break;
            case "thursday":
                this.thursday = cook;
                break;
            case "friday":
                this.friday = cook;
                break;
            case "saturday":
                this.saturday = cook;
                break;
            case "sunday":
                this.sunday = cook;
                break;
        }
    }

    public String getDayInfo(String day){
        switch(day){
            case "monday":
                return getMonday();
            case "tuesday":
                return getTuesday();
            case "wednesday":
                return getWednesday();
            case "thursday":
                return getThursday();
            case "friday":
                return getFriday();
            case "saturday":
                return getSaturday();
            case "sunday":
                return getSunday();
            default:
                return "none";
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
