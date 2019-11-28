package com.example.demo.repo;

import com.example.demo.model.Dinner;
import com.example.demo.model.Person;
import com.example.demo.model.testObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    public void date(){
        LocalDateTime currentTime = LocalDateTime.now();
        //System.out.println("Current time of day: " + dtf.format(currentTime));
        //Udskift nedenstående med dato fra database - Skal være dagen man prøver at tilmelde sig til


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime test = LocalDateTime.of(2019,5,25,16,00);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = test.get(weekFields.weekOfWeekBasedYear());
        System.out.println(weekNumber);
        System.out.println(weekFields.dayOfWeek());



        int difference = currentTime.compareTo(test);
        //System.out.println(currentTime.compareTo(test));
        if (test.getHour() >= 16 && difference <= 0 || difference >= 0){
            System.out.println("Can't book, to close to date");
        }else{
            System.out.println("Dinner booked! :D");
        }
    }

    public void dateTester(int week_number){

        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.YEAR, 2019);
        cld.set(Calendar.WEEK_OF_YEAR, week_number);
        int day_number = 0;
        if ("MONDAY".equals(weekFields.dayOfWeek())) {
            day_number = 1;
        } else if ("TUESDAY".equals(weekFields))
        cld.set(Calendar.DAY_OF_WEEK, day_number);
        Date result = cld.getTime();
        System.out.println(result);
    }
}
