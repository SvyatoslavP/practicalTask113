package ru.panifidkin.springsecurityapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String showLanding() {

        return "landing";
    }

    @GetMapping("/leaders")
    public String showLeaders() {

        return "leaders";
    }

    @GetMapping("/employees")
    public String showHome() {
        return "home";
    }

}
