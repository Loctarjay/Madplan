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
        Weekplans wP = wRepo.fetchById(1);
        model.addAttribute("chosen", wP);
        model.addAttribute("person", pRepo.fetchAll(wP));
        return "login/index";
    }

    @PostMapping("/")
    public String index(Model model, @ModelAttribute Weekplans weekplans){
        model.addAttribute("wNumber", wRepo.fetchAll());
        Weekplans wP = wRepo.fetchById(weekplans.getWeek_number());
        model.addAttribute("chosen", wP);
        model.addAttribute("person", pRepo.fetchAll(wP));
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
            loggedIn = pRepo.fetchById(person.getRoom_id());
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
        Weekplans wP = wRepo.fetchById(1);
        model.addAttribute("chosen", wP);
        model.addAttribute("person", pRepo.fetchAll(wP));
        model.addAttribute("loggedIn", loggedIn);
        return "userVersion/weekPage";
    }

    @PostMapping("/weekPage")
    public String weekPage(Model model, @ModelAttribute Weekplans weekplans){
        model.addAttribute("wNumber", wRepo.fetchAll());
        Weekplans wP = wRepo.fetchById(weekplans.getWeek_number());
        model.addAttribute("chosen", wP);
        model.addAttribute("person", pRepo.fetchAll(wP));
        model.addAttribute("loggedIn", loggedIn);
        return "userVersion/weekPage";
    }

    @GetMapping("/viewDinner")
    public String viewDinner(Model model, @ModelAttribute Weekplans weekplans){
        model.addAttribute("day", weekplans);
        model.addAttribute("chosen", wRepo.fetchById(weekplans.getWeek_number()));
        String room_id = wRepo.fetchSpecificDayInfo(weekplans.getWeek_number(), weekplans.getDay());
        model.addAttribute("person", pRepo.fetchById(room_id));
        model.addAttribute("dinner", dRepo.fetchById(room_id, weekplans.getWeek_number(), weekplans.getDay()));
        return "dinner/viewDinner";
    }

    @GetMapping("/viewAllergies")
    public String viewAllergies(Model model, @ModelAttribute Weekplans weekplans){
        model.addAttribute("day", weekplans);
        model.addAttribute("chosen", wRepo.fetchById(weekplans.getWeek_number()));
        String room_id = wRepo.fetchSpecificDayInfo(weekplans.getWeek_number(), weekplans.getDay());
        List<Person> personList = sRepo.checkDays(weekplans.getWeek_number(), weekplans.getDay());
        model.addAttribute("registeredEaters", personList);
        model.addAttribute("dinner", dRepo.fetchById(room_id, weekplans.getWeek_number(), weekplans.getDay()));
        return "userVersion/viewAllergies";
    }

    @GetMapping("/testPage")
    public String test_page(Model model){
        model.addAttribute("person", pRepo.fetchById("A15"));
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

    @GetMapping("/dinnerOption")
    public String dinnerOption(Model model, @ModelAttribute Weekplans weekplans){
        model.addAttribute("chosen", weekplans);

        return "dinner/dinnerOption";
    }

    @GetMapping("/dinnerSignUp")
    public String dinnerSignUp(Model model, @ModelAttribute Weekplans weekplans){
        model.addAttribute("day", weekplans);
        model.addAttribute("chosen", wRepo.fetchById(weekplans.getWeek_number()));
        String room_id = wRepo.fetchSpecificDayInfo(weekplans.getWeek_number(), weekplans.getDay());
        model.addAttribute("person", pRepo.fetchById(room_id));
        model.addAttribute("dinner", dRepo.fetchById(room_id, weekplans.getWeek_number(), weekplans.getDay()));
        model.addAttribute("hourTest", pRepo.date(sRepo.getChosenDate(weekplans.getWeek_number(), weekplans.getDay())));
        return "dinner/dinnerSignUp";
    }

    @PostMapping("/dinnerSignUp")
    public String dinnerSignUp(@ModelAttribute testObject to){
        sRepo.create(loggedIn.getRoom_id(), to.getWeek_number(), to.getDay());
        return "redirect:/weekPage";
    }




}
