package ru.panifidkin.restapi.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.panifidkin.restapi.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByFirstName(String name);
}
