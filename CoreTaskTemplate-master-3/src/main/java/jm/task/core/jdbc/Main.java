package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Svyatoslav","Panifidkin",(byte) 29);
        userService.saveUser("Ivan","Dub4ak",(byte) 34);
        userService.saveUser("Aleksandr","Akimov",(byte) 31);
        userService.saveUser("Timyr","Vergush",(byte) 40);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
