package com.example.demo.repo;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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

}
