package com.example.demo.repo;

import com.example.demo.model.Person;
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
            System.out.println("Empty !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
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
        return template.queryForObject(sql, rowMapper, week_number);
    }

    @Override
    public void create(Weekplans weekplans) {

    }

    @Override
    public Boolean deleteById(String id) {
        return null;
    }

    @Override
    public void update(Weekplans weekplans) {

    }
}
