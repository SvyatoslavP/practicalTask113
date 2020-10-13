package ru.panifidkin.restapi.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.panifidkin.restapi.demo.model.UserTO;
import ru.panifidkin.restapi.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<UserTO> getUserTO(){

        return userService.findAll();
    }

    @GetMapping("/users/{userName}")
    public UserTO getCustomer(@PathVariable String userName){

        UserTO userTO = userService.findByName(userName);

        if(userTO == null){
            throw new UserNotFoundException("Customer id not found - " + userName);
        }
        return userTO;
    }

    @PostMapping("/users")
    public UserTO addCustomer(@RequestBody UserTO userTO){

        userTO.setId(0L);

        userService.save(userTO);

        return userTO;
    }

    @PutMapping(value = "/users")
    public UserTO updateCustomer(@RequestBody UserTO userTO){

        userService.save(userTO);

        return userTO;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteCustomer(@PathVariable long userId){

        UserTO tempUser = userService.findById(userId);

        if(tempUser == null){
            throw new UserNotFoundException("Customer id not found " + userId);
        }

        userService.deleteById(userId);

        return "Deleted customer id - " + userId;
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

}
