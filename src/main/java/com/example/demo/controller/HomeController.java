package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.model.Weekplans;
import com.example.demo.repo.PersonRepo;
import com.example.demo.repo.WeekplansRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    PersonRepo pRepo;
    @Autowired
    WeekplansRepo wRepo;

    @GetMapping("/")
    public String index(){
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
            return "redirect:/weekPage";
        }
    }

    @GetMapping("/viewAllergies")
    public String viewAllergies(Model model){
        List<Person> personList = pRepo.fetchAll();
        model.addAttribute("personer", personList);
        return "userVersion/viewAllergies";
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
        List<Weekplans> week_number = wRepo.fetchAll();
        model.addAttribute("week_number", week_number);
        return "userVersion/weekPage";
    }

    @PostMapping("/week_number/{week_number}")
    public String weekPage(Model model, @PathVariable("week_number") int week_number){

        return "redirect:/weekPage";
    }

    @GetMapping("/testPage")
    public String test_page(){
        pRepo.date();
        return "userVersion/testPage";
    }




}
