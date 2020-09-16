package ru.panifidkin.bootapp.springbootapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.panifidkin.bootapp.springbootapp.model.User;
import ru.panifidkin.bootapp.springbootapp.repository.UserRepository;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<User> allUsers(int page) {
        return userRepository.allUsers(page);
    }

    @Override
    @Transactional
    public void add(User user) {
        userRepository.add(user);
    }


    @Override
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void edit(User user) {
        userRepository.edit(user);
    }

    @Override
    @Transactional
    public User getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    @Transactional
    public int usersCount() {
        return userRepository.usersCount();
    }
}
