package ru.panifidkin.bootapp.springbootapp.repository;

import ru.panifidkin.bootapp.springbootapp.model.User;

import java.util.List;

public interface UserRepository {

    List<User> allUsers(int page);

    int usersCount();

    void add(User user);

    void delete(User user);

    void edit(User user);

    User getById(int id);

    User getUserByName(String name);
}
