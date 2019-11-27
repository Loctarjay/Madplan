package com.example.demo.repo;

import com.example.demo.model.Dinner;
import com.example.demo.model.Person;
import com.example.demo.model.testObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
    public Dinner fetchById(String fk_room_id) {
        String sql = "SELECT * FROM dinner WHERE fk_room_id=?";
        RowMapper<Dinner> rowMapper = new BeanPropertyRowMapper<>(Dinner.class);
        return template.queryForObject(sql, rowMapper, fk_room_id);
    }

    @Override
    public void create(Dinner d) {
        String sql = "INSERT INTO dinner (fk_room_id, dinner_name, description) VALUES (?, ?, ?)";
        template.update(sql, d.getFk_room_id(), d.getDinner_name(), d.getDescription());
    }

    @Override
    public Boolean deleteById(String id) {
        return null;
    }

    @Override
    public void update(Dinner dinner) {

    }

    public void update(testObject tO) {
        System.out.println(tO.getFk_room_id());
        System.out.println(tO.getDinner_name());
        System.out.println(tO.getDescription());
        String sql = "UPDATE dinner SET fk_room_id = ?, dinner_name = ?, description = ?";
        template.update(sql, tO.getFk_room_id(), tO.getDinner_name(), tO.getDescription());
    }
}
