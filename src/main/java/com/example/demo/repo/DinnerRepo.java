package com.example.demo.repo;

import com.example.demo.model.Dinner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

@Repository
public class DinnerRepo implements RepoInterface<Dinner> {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Dinner> fetchAll() {
        return null;
    }

    @Override
    public Dinner fetchById(String id) { return null; }


    public Dinner fetchById(String fk_room_id, int week_number, String day) {
        String sql = "SELECT * FROM dinner WHERE fk_room_id = ? AND chosen_date = ?";
        RowMapper<Dinner> rowMapper = new BeanPropertyRowMapper<>(Dinner.class);
        return template.queryForObject(sql, rowMapper, fk_room_id, getChosenDate(week_number, day));
    }

    @Override
    public void create(Dinner dinner) { }


    public void create(Dinner d, int week_number, String day) {
        String sql = "INSERT INTO dinner (fk_room_id, dinner_name, description, chosen_date) VALUES (?, ?, ?, ?)";
        template.update(sql, d.getFk_room_id(), d.getDinner_name(), d.getDescription(), getChosenDate(week_number, day));
    }

    @Override
    public Boolean deleteById(String id) {
        return null;
    }

    @Override
    public void update(Dinner d) {
        String sql = "UPDATE dinner SET dinner_name = ?, description = ? WHERE fk_room_id = ? AND chosen_date = ?";
        template.update(sql, d.getDinner_name(), d.getDescription(), d.getFk_room_id(), d.getChosen_date());
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
