package ru.panifidkin.springbootapp_1.springbootapp_1.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.panifidkin.springbootapp_1.springbootapp_1.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
