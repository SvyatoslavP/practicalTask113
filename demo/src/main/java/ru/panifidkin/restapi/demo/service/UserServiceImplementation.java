package ru.panifidkin.restapi.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.panifidkin.restapi.demo.mapper.UserMapper;
import ru.panifidkin.restapi.demo.model.User;
import ru.panifidkin.restapi.demo.model.UserTO;
import ru.panifidkin.restapi.demo.repository.UserRepository;

import java.util.List;
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
    public List<UserTO> findAll() {
        return UserMapper.toUsersTO(userRepository.findAll());
    }

    @Override
    public UserTO save(UserTO user) {

        user.setRoles(user.getRoles());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        User saved = userRepository.save(UserMapper.toUser(user));
        return UserMapper.toUserTO(saved);
    }

    @Override
    public UserTO findById(Long id) {
        return UserMapper.toUserTO(userRepository.getOne(id));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserTO findByName(String name) {
        return UserMapper.toUserTO(userRepository.findByFirstName(name));
    }
}
