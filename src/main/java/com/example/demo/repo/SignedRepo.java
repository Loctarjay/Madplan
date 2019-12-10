package com.example.demo.repo;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

@Repository
public class SignedRepo {
    @Autowired
    JdbcTemplate template;

    public List<Person> checkDays(){
        String sql = "SELECT s.*, p.* FROM signed_up s JOIN persons p ON s.fk_room_id=p.room_id WHERE chosen_date=?";
        RowMapper<Person> rowMapper = new BeanPropertyRowMapper<>(Person.class);
        return template.query(sql, rowMapper, "2019/11/27");
    }

    public void create(String room_id, int week_number, String day){
        String sql = "INSERT INTO signed_up (fk_room_id, chosen_date) VALUES (?, ?)";
        template.update(sql, room_id, getChosenDate(week_number, day));
    }

    public String getChosenDate(int week_number, String day){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, week_number);
        if (day.equals("monday")){
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        } else if (day.equals("tuesday")){
            cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        } else if (day.equals("wednesday")){
            cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        } else if (day.equals("thursday")){
            cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        } else if (day.equals("friday")){
            cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        } else if (day.equals("saturday")){
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        } else if (day.equals("sunday")){
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        }
        DateFormat defaultFormat = DateFormat.getDateInstance();

        return defaultFormat.format(cal.getTime());
    }

}
