package com.example.demo.repo;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class PersonRepo implements RepoInterface<Person> {
    @Autowired
    JdbcTemplate template;


    @Override
    public List<Person> fetchAll() {
        String sql = "SELECT * FROM persons";
        RowMapper<Person> rowMapper = new BeanPropertyRowMapper<>(Person.class);
        return template.query(sql, rowMapper);
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
        template.update(sql,p.getRoom_id(), p.getFirst_name(), p.getLast_name(), p.getPassword(), p.getAllergies());
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

    public void date(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Current time of day: " + dtf.format(currentTime));
        //Udskift nedenstående med dato fra database - Skal være dagen man prøver at tilmelde sig til
        LocalDateTime test = LocalDateTime.of(2019,11,25,16,00);

        int difference = currentTime.compareTo(test);
        System.out.println(currentTime.compareTo(test));
        if (test.getHour() >= 16 && difference <= 0 || difference >= 0){
            System.out.println("Can't book, to close to date");
        }else{
            System.out.println("Dinner booked! :D");
        }
    }

}
