package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String INSERT_NEW = "INSERT INTO mytableusers(name, lastName, age) "
            + " VALUES(?,?,?)";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = Util.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE mytableusers" +
                    "(" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(30)," +
                    "lastName VARCHAR(30)," +
                    "age TINYINT" +
                    ")");

        } catch (SQLException e) {
            UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
            userDaoJDBC.dropUsersTable();
            userDaoJDBC.createUsersTable();
            System.out.println("The old table has been cleared.");
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE mytableusers");
        } catch (SQLException e) {
            System.out.println("No such table exists.");
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(INSERT_NEW);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
                System.out.println("User : " + name + " added to the table!");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            long rew = statement.executeUpdate("DELETE FROM mytable WHERE Id =" + id);
            System.out.println(rew);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = Util.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM mytableusers");
            while (resultSet.next()) {
                long id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
                for (User user : users) {
                    System.out.println(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE mytableusers");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
