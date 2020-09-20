package ru.panifidkin.springbootapp_1.springbootapp_1.service;

import ru.panifidkin.springbootapp_1.springbootapp_1.model.User;

import java.util.List;


public interface UserService {
    List<User> findAll();

    User save(User user);

    User findById(Long id);

    void deleteById(Long id);
}
