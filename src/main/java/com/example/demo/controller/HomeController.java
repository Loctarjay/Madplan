package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repo.DinnerRepo;
import com.example.demo.repo.PersonRepo;
import com.example.demo.repo.SignedRepo;
import com.example.demo.repo.WeekplansRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    Person loggedIn;

    @Autowired
    PersonRepo pRepo;
    @Autowired
    WeekplansRepo wRepo;
    @Autowired
    SignedRepo sRepo;
    @Autowired
    DinnerRepo dRepo;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("wNumber", wRepo.fetchAll());
        model.addAttribute("chosen", wRepo.fetchById(1));
        return "login/index";
    }

    @PostMapping("/")
    public String index(Model model, @ModelAttribute Weekplans weekplans){
        model.addAttribute("wNumber", wRepo.fetchAll());
        model.addAttribute("chosen", wRepo.fetchById(weekplans.getWeek_number()));
        return "login/index";
    }

    @GetMapping("/loginpage")
    public String loginpage(){
        return "login/loginpage";
    }

    @PostMapping("/loginpage")
    public String loginpage(@ModelAttribute Person person){
        if(!pRepo.loginTest(person)){
            return "login/loginpage";
        }else {
            loggedIn = person;
            return "redirect:/weekPage";
        }
    }

    @GetMapping("/sign_up_page")
    public String sign_up_page(){
        return "login/sign_up_page";
    }

    @PostMapping("/sign_up_page")
    public String sign_up_page(@ModelAttribute Person person){
        pRepo.create(person);
        return "redirect:/";
    }

    @GetMapping("/weekPage")
    public String weekPage(Model model){
        model.addAttribute("wNumber", wRepo.fetchAll());
        model.addAttribute("chosen", wRepo.fetchById(1));
        model.addAttribute("person", pRepo.fetchById(loggedIn.getRoom_id()));
        return "userVersion/weekPage";
    }

    @PostMapping("/weekPage")
    public String weekPage(Model model, @ModelAttribute Weekplans weekplans){
        model.addAttribute("wNumber", wRepo.fetchAll());
        model.addAttribute("chosen", wRepo.fetchById(weekplans.getWeek_number()));
        model.addAttribute("person", pRepo.fetchById(loggedIn.getRoom_id()));
        return "userVersion/weekPage";
    }

    /*
    @PostMapping("/weekPage")
    public String weekPage(Model model, @PathVariable("week_number") String week_number){
        model.addAttribute("wNumber", wRepo.fetchAll());
        int weekNumber = Integer.parseInt(week_number);
        Weekplans chosenWeek = wRepo.fetchById(weekNumber);
        model.addAttribute("chosen", chosenWeek);
        return "userVersion/weekPage";
    }
    */

    @GetMapping("/viewDinner")
    public String viewDinner(Model model, @ModelAttribute Weekplans weekplans){
        model.addAttribute("day", weekplans);
        model.addAttribute("chosen", wRepo.fetchById(weekplans.getWeek_number()));
        String room_id = wRepo.fetchSpecificDayInfo(weekplans.getWeek_number(), weekplans.getDay());
        model.addAttribute("person", pRepo.fetchById(room_id));
        model.addAttribute("dinner", dRepo.fetchById(room_id, weekplans.getWeek_number(), weekplans.getDay()));
        dRepo.getChosenDate(weekplans.getWeek_number(), weekplans.getDay());
        return "dinner/viewDinner";
    }

    @GetMapping("/viewAllergies")
    public String viewAllergies(Model model, @ModelAttribute Signed_up signed_up){
        List<Person> personList = sRepo.checkDays();
        model.addAttribute("registeredEaters", personList);
        return "userVersion/viewAllergies";
    }

    @GetMapping("/testPage")
    public String test_page(Model model){
        model.addAttribute("person", pRepo.fetchById("A15"));
        pRepo.date();
        return "userVersion/testPage";
    }

    @GetMapping("/createDinner")
    public String createDinner(Model model, @ModelAttribute Weekplans weekplans){
        model.addAttribute("chosen", weekplans);
        model.addAttribute("person", pRepo.fetchById(loggedIn.getRoom_id()));
        model.addAttribute("date", dRepo.getChosenDate(weekplans.getWeek_number(), weekplans.getDay()));
        return "dinner/createDinner";
    }

    @PostMapping("/createDinner")
    public String createDinner(@ModelAttribute testObject to){
        Dinner d = new Dinner(to.getFk_room_id(), to.getDinner_name(), to.getDescription());
        dRepo.create(d, to.getWeek_number(), to.getDay());
        Weekplans wp = new Weekplans(to.getWeek_number(), to.getDay(), to.getFk_room_id());
        wRepo.create(wp);
        return "redirect:/weekPage";
    }

    @GetMapping("/editDinner")
    public String editDinner(Model model, @ModelAttribute Weekplans weekplans){
        model.addAttribute("chosen", weekplans);
        model.addAttribute("person", loggedIn.getFirst_name());
        model.addAttribute("dinner", dRepo.fetchById(loggedIn.getRoom_id(), weekplans.getWeek_number(), weekplans.getDay()));
        return "redirect:dinner/editDinner";
    }
/*
    @PostMapping("dinner/editDinner")
    public String editDinner(@ModelAttribute Weekplans weekplans){
        //dRepo.update();
        return "dinner/editDinner";
    }

 */

    /*
    @PostMapping("/createDinner")
    public String createDinner(Model model, @ModelAttribute testObject testO){
        dRepo.update(testO);
        model.addAttribute("chosen", wRepo.fetchById(testO.getWeek_number()));
        String room_id = wRepo.fetchSpecificDayInfo(testO.getWeek_number(), testO.getDay());
        model.addAttribute("person", pRepo.fetchById(room_id));
        model.addAttribute("dinner", dRepo.fetchById(room_id));
        return "userVersion/createDinner";
    }

     */

    @GetMapping("/dinnerOption")
    public String dinnerOption(Model model, @ModelAttribute Weekplans weekplans){
        model.addAttribute("chosen", weekplans);

        return "dinner/dinnerOption";
    }




}
