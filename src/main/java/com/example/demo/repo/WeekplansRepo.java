package com.example.demo.repo;

import com.example.demo.model.Weekplans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WeekplansRepo implements RepoInterface<Weekplans> {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Weekplans> fetchAll() {
        String sql = "SELECT week_number FROM weekplans ORDER BY week_number";
        RowMapper<Weekplans> rowMapper = new BeanPropertyRowMapper<>(Weekplans.class);
        List<Weekplans> weekNumber = template.query(sql, rowMapper);
        if (!weekNumber.isEmpty()){
            return weekNumber;
        } else{
            for (int i = 1; i <= 52; i++){
                String insertionSql = "INSERT INTO weekplans (week_number) VALUES (?)";
                template.update(insertionSql, i);
            }
            sql = "SELECT week_number FROM weekplans";
            rowMapper = new BeanPropertyRowMapper<>(Weekplans.class);
            weekNumber = template.query(sql, rowMapper);
            return weekNumber;
        }

    }

    @Override
    public Weekplans fetchById(String id) {
        return null;
    }

    public Weekplans fetchById(int week_number) {
        String sql = "SELECT * FROM weekplans WHERE week_number = ? ORDER BY week_number";
        RowMapper<Weekplans> rowMapper = new BeanPropertyRowMapper<>(Weekplans.class);
        Weekplans wp = template.queryForObject(sql, rowMapper, week_number);
        return wp;
    }
    public String fetchSpecificDayInfo(int week_number, String day){
        String sql = "SELECT " + day + " FROM weekplans WHERE week_number = ?";
        RowMapper<Weekplans> rowMapper = new BeanPropertyRowMapper<>(Weekplans.class);
        String dayInfo = template.queryForObject(sql, rowMapper, week_number).getDayInfo(day);
        return dayInfo;
    }


    @Override
    public void create(Weekplans weekplans) {
        String sql = "INSERT INTO weekplans SET week_number = " + weekplans.getWeek_number() +", "+ weekplans.getDay() + " = ? ON DUPLICATE KEY UPDATE " + weekplans.getDay() + " = ?";
        if (weekplans.getDay().equals("monday")) {
            template.update(sql, weekplans.getWeek_number(), weekplans.getMonday());
        } else if (weekplans.getDay().equals("tuesday")){
            System.out.println(weekplans.getTuesday());
            template.update(sql, weekplans.getWeek_number(), weekplans.getTuesday());
        } else if (weekplans.getDay().equals("wednesday")){
            template.update(sql, weekplans.getWeek_number(), weekplans.getWednesday());
        } else if (weekplans.getDay().equals("thursday")){
            template.update(sql, weekplans.getWeek_number(), weekplans.getThursday());
        } else if (weekplans.getDay().equals("friday")){
            template.update(sql, weekplans.getWeek_number(), weekplans.getFriday());
        } else if (weekplans.getDay().equals("saturday")){
            template.update(sql, weekplans.getWeek_number(), weekplans.getSaturday());
        } else if (weekplans.getDay().equals("sunday")){
            template.update(sql, weekplans.getWeek_number(), weekplans.getSunday());
        }
    }

    @Override
    public Boolean deleteById(String id) {
        return null;
    }

    @Override
    public void update(Weekplans weekplans) { }

  /*  public List<Weekplans> upcoming(){

        String sql = "";
        RowMapper<Weekplans> rowMapper = new BeanPropertyRowMapper<>(Weekplans.class);
        return
    }
*/

}
