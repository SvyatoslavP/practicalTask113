package ru.panifidkin.bootapp.springbootapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.panifidkin.bootapp.springbootapp.model.User;
import ru.panifidkin.bootapp.springbootapp.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/systems")
public class AdminController {

    private int page;

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView allUsers(@RequestParam(defaultValue = "1") int page) {
        this.page = page;
        List<User> userList = userService.allUsers(page);
        int usersCount = userService.usersCount();
        int pageCount = (usersCount + 9) / 10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("systems");
        modelAndView.addObject("userList", userList);
        modelAndView.addObject("usersCount", usersCount);
        modelAndView.addObject("pageCount", pageCount);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/systems?page=" + this.page);
        userService.edit(user);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/systems?page=" + this.page);
        User user = userService.getById(id);
        userService.delete(user);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/systems?page=" + this.page);
        userService.add(user);
        return modelAndView;
    }

}
