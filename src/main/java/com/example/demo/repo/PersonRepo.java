package com.example.demo.repo;

import com.example.demo.model.Person;
import com.example.demo.model.Weekplans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PersonRepo implements RepoInterface<Person> {
    @Autowired
    JdbcTemplate template;

    @Override
    public List<Person> fetchAll() {
        return null;
    }
    public List<String> fetchAll(Weekplans w) {
        String sql = "SELECT * FROM persons WHERE room_id = ?";
        RowMapper<Person> rowMapper = new BeanPropertyRowMapper<>(Person.class);
        List<String> room_id_List = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                try {
                    Person p = template.queryForObject(sql, rowMapper, w.getMonday());
                    if (p != null) {
                        room_id_List.add(p.getRoom_id() + ": " + p.getFirst_name());
                    } else {
                        room_id_List.add("");
                    }
                } catch (EmptyResultDataAccessException emp){
                    room_id_List.add("");
                }
            } else if (i == 1){
                try {
                    Person p = template.queryForObject(sql, rowMapper, w.getTuesday());
                    if (p != null) {
                        room_id_List.add(p.getRoom_id() + ": " + p.getFirst_name());
                    } else {
                        room_id_List.add("");
                    }
                } catch (EmptyResultDataAccessException emp){
                    room_id_List.add("");
                }
            } else if (i == 2){
                try {
                    Person p = template.queryForObject(sql, rowMapper, w.getWednesday());
                    if (p != null) {
                        room_id_List.add(p.getRoom_id() + ": " + p.getFirst_name());
                    } else {
                        room_id_List.add("");
                    }
                } catch (EmptyResultDataAccessException emp){
                    room_id_List.add("");
                }
            } else if (i == 3){
                try {
                    Person p = template.queryForObject(sql, rowMapper, w.getThursday());
                    if (p != null) {
                        room_id_List.add(p.getRoom_id() + ": " + p.getFirst_name());
                    } else {
                        room_id_List.add("");
                    }
                } catch (EmptyResultDataAccessException emp){
                    room_id_List.add("");
                }
            } else if (i == 4){
                try {
                    Person p = template.queryForObject(sql, rowMapper, w.getFriday());
                    if (p != null) {
                        room_id_List.add(p.getRoom_id() + ": " + p.getFirst_name());
                    } else {
                        room_id_List.add("");
                    }
                } catch (EmptyResultDataAccessException emp){
                    room_id_List.add("");
                }
            } else if (i == 5) {
                try {
                    Person p = template.queryForObject(sql, rowMapper, w.getSaturday());
                    if (p != null) {
                        room_id_List.add(p.getRoom_id() + ": " + p.getFirst_name());
                    } else {
                        room_id_List.add("");
                    }
                } catch (EmptyResultDataAccessException emp){
                    room_id_List.add("");
                }
            } else if (i == 6){
                try {
                    Person p = template.queryForObject(sql, rowMapper, w.getSunday());
                    if (p != null) {
                        room_id_List.add(p.getRoom_id() + ": " + p.getFirst_name());
                    } else {
                        room_id_List.add("");
                    }
                } catch (EmptyResultDataAccessException emp){
                    room_id_List.add("");
                }
            }

        }
        return room_id_List;
    }

    @Override
    public Person fetchById(String room_id) {
        String sql = "SELECT * FROM persons WHERE room_id = ?";
        RowMapper<Person> rowMapper = new BeanPropertyRowMapper<>(Person.class);
        return template.queryForObject(sql, rowMapper, room_id);
    }

    @Override
    public void create(Person p) {
        String sql = "INSERT INTO persons (room_id, first_name, last_name, password, allergies) VALUES (?, ?, ?, ?, ?)";
        template.update(sql, p.getRoom_id(), p.getFirst_name(), p.getLast_name(), p.getPassword(), p.getAllergies());
    }

    @Override
    public Boolean deleteById(String room_id) {
        String sql = "DELETE FROM persons WHERE room_id = ?";
        return template.update(sql, room_id) > 0;
    }

    @Override
    public void update(Person p) {

    }
    public Boolean loginTest(Person p){
        Boolean loginTest = false;
        String sql = "SELECT * FROM persons";
        RowMapper<Person> rowMapper = new BeanPropertyRowMapper<>(Person.class);
        List<Person> personList = template.query(sql, rowMapper);
        for(int i = 0; i < personList.size(); i++){
            if(personList.get(i).getRoom_id().equalsIgnoreCase(p.getRoom_id()) && personList.get(i).getPassword().equals(p.getPassword())){
                loginTest = true;
                i = personList.size() + 1;
            }else{
                loginTest = false;
            }
        }
        return loginTest;
    }

    public Boolean date(String date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime currentTime = LocalDateTime.now();
        String temp = dtf.format(currentTime);
        boolean result;
        if (temp.compareTo(date) == -1) {
            if (currentTime.getHour() >= 16) {
                System.out.println("Can't book, to close to date");
                result = false;
            } else {
                System.out.println("Dinner booked! :D");
                result = true;
            }
        } else{
            result = false;
        }
        System.out.println(result);
        return result;
    }

}
