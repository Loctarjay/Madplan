package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/loginpage")
    public String loginpage(@ModelAttribute Person person){
        if(pRepo.loginTest(person)){
            return "login/test";
        }else {
            return "login/loginpage";
        }
    }






}
