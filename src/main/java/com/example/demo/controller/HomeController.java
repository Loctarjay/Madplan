package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    PersonRepo pRepo;

    @GetMapping("/")
    public String index(){
        return "login/index";
    }

    @GetMapping("/loginpage")
    public String loginpage(){
        return "login/loginpage";
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

    @PostMapping("/loginpage")
    public String loginpage(@ModelAttribute Person person){
        if(pRepo.loginTest(person)){
            return "userVersion/weekPage";
        }else {
            return "login/loginpage";
        }
    }

    @GetMapping("/testPage")
    public String test_page(){
        pRepo.date();
        return "userVersion/testPage";
    }




}
