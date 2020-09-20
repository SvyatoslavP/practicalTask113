package ru.panifidkin.springbootapp_1.springbootapp_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.panifidkin.springbootapp_1.springbootapp_1.model.Role;
import ru.panifidkin.springbootapp_1.springbootapp_1.model.User;
import ru.panifidkin.springbootapp_1.springbootapp_1.repositoty.UserRepository;

import java.util.*;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        Set<Role> defaultSet = new HashSet<>();
        defaultSet.add(new Role(2L, "ROLE_EMPLOYEE"));
        user.setRoles(defaultSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
