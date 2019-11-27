package com.example.demo.repo;

import com.example.demo.model.ComboObject;
import com.example.demo.model.Weekplans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComboRepo implements RepoInterface<ComboObject> {
    @Autowired
    JdbcTemplate template;


    @Override
    public List<ComboObject> fetchAll() {
        return null;
    }

    @Override
    public ComboObject fetchById(String id) {
        return null;
    }
    public ComboObject fetchById(ComboObject object){

        System.out.println(!object.getMonday().isEmpty());

        /*
        if (!object.getMonday().isEmpty()) {
            String sql = "SELECT * FROM weekplans JOIN persons WHERE weekplans." + object.getMonday() + "=persons.room_id AND weekplans.week_number=?";
            RowMapper<ComboObject> rowMapper = new BeanPropertyRowMapper<>(ComboObject.class);
            return template.queryForObject(sql, rowMapper, object.getWeek_number());
        }

         */
        return null;
    }

    @Override
    public void create(ComboObject comboObject) {

    }

    @Override
    public Boolean deleteById(String id) {
        return null;
    }

    @Override
    public void update(ComboObject comboObject) {

    }
}
