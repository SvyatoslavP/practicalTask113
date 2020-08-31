package ru.panifidkin.springsecurityapp.service;

import ru.panifidkin.springsecurityapp.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers(int page);

    void add(User user);

    void delete(User user);

    void edit(User user);

    User getById(int id);

    int usersCount();
}
