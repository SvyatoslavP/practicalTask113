package ru.panifidkin.springbootapp_1.springbootapp_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.panifidkin.springbootapp_1.springbootapp_1.model.User;
import ru.panifidkin.springbootapp_1.springbootapp_1.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class AppController {

    private final UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String showListUsers(Model theModel) {
        List<User> userList = userService.findAll();
        theModel.addAttribute("userList", userList);
        return "users/list-users";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        User user = new User();
        theModel.addAttribute("user", user);
        return "users/user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User theUser) {
        userService.save(theUser);
        return "redirect:/users/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") long theId,
                                    Model theModel) {
        User theUser = userService.findById(theId);
        theModel.addAttribute("user", theUser);
        return "users/user-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("userId") long theId) {
        userService.deleteById(theId);
        return "redirect:/users/list";
    }


}
























