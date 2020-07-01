package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Svyatoslav","Panifidkin",(byte) 29);
        userDaoJDBC.saveUser("Ivan","Dub4ak",(byte) 34);
        userDaoJDBC.saveUser("Aleksandr","Akimov",(byte) 31);
        userDaoJDBC.saveUser("Timyr","Vergush",(byte) 40);
        userDaoJDBC.getAllUsers();
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
