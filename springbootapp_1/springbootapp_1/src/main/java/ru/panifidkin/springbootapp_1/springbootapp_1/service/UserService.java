package ru.panifidkin.springbootapp_1.springbootapp_1.service;

import ru.panifidkin.springbootapp_1.springbootapp_1.model.UserTO;

import java.util.List;


public interface UserService {
    List<UserTO> findAll();

    UserTO save(UserTO user);

    UserTO findById(Long id);

    void deleteById(Long id);

    UserTO findByName(String name);
}
