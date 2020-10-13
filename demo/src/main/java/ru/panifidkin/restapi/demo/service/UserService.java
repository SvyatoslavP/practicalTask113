package ru.panifidkin.restapi.demo.service;

import ru.panifidkin.restapi.demo.model.UserTO;

import java.util.List;

public interface UserService {

    List<UserTO> findAll();

    UserTO save(UserTO user);

    UserTO findById(Long id);

    void deleteById(Long id);

    UserTO findByName(String name);

}
