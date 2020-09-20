package ru.panifidkin.springbootapp_1.springbootapp_1.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.panifidkin.springbootapp_1.springbootapp_1.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
