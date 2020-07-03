package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getMySQLConnection();
    private static Statement statement = null;
    private static final String INSERT_NEW = "INSERT INTO mytableusers(name, lastName, age) "
            + " VALUES(?,?,?)";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS mytableusers");
            statement.executeUpdate("CREATE TABLE mytableusers" +
                    "(" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(30)," +
                    "lastName VARCHAR(30)," +
                    "age TINYINT" +
                    ")");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try {
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            statement.executeUpdate("DROP TABLE IF EXISTS mytableusers");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("the table was not deleted!");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_NEW);
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            connection.commit();
            System.out.println("User : " + name + " added to the table!");

        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("User : " + name + " no added to the table!");
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void removeUserById(long id) {
        try {
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            statement.executeUpdate("DELETE FROM mytableusers WHERE Id =" + id);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM mytableusers");
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
                resultSet.close();
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
        try {
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            statement.executeUpdate("TRUNCATE TABLE mytableusers");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("the table was not cleared!");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
