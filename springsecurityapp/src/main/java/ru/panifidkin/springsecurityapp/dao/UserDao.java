package ru.panifidkin.springsecurityapp.dao;

import ru.panifidkin.springsecurityapp.model.User;

import java.util.List;

public interface UserDao {
    List<User> allUsers(int page);

    int usersCount();

    void add(User user);

    void delete(User user);

    void edit(User user);

    User getById(int id);

    User getUserByName(String name);
}
