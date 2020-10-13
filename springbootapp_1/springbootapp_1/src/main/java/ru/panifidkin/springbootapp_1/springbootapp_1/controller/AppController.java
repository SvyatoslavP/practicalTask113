package ru.panifidkin.springbootapp_1.springbootapp_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.panifidkin.springbootapp_1.springbootapp_1.model.UserTO;
import ru.panifidkin.springbootapp_1.springbootapp_1.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class AppController {

    private final UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/list")
    public String ListUsers(Model theModel) {
        UserTO userTO = new UserTO();
        List<UserTO> userList = userService.findAll();
        theModel.addAttribute("userList", userList);
        theModel.addAttribute("user", userTO);
        return "users/list-users";
    }

    @RequestMapping("/getOne")
    @ResponseBody
    public UserTO getOne(@RequestParam("userId") long id) {
        return userService.findById(id);
    }

    @PostMapping("/addNew")
    public String addNew(UserTO theUser) {
        userService.save(theUser);
        return "redirect:/users/list";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(UserTO userTO){
        userService.deleteById(userTO.getId());
        return "redirect:/users/list";
    }
    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

}
























