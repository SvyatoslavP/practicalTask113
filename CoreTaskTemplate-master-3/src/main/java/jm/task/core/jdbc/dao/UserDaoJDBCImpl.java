package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getMySQLConnection();
    private static PreparedStatement statement = null;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            statement = connection.prepareStatement("DROP TABLE IF EXISTS mytableusers");
            statement.execute();
            statement = connection.prepareStatement("CREATE TABLE mytableusers" +
                    "(" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(30)," +
                    "lastName VARCHAR(30)," +
                    "age TINYINT" +
                    ")");
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("DROP TABLE IF EXISTS mytableusers");
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
                System.out.println("the table was not deleted!");
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("INSERT INTO mytableusers(name, lastName, age) "
                    + " VALUES(?,?,?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
            connection.commit();
            System.out.println("User : " + name + " added to the table!");

        } catch (Exception e) {
            try {
                connection.rollback();
                System.out.println("User : " + name + " no added to the table!");
                e.printStackTrace();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void removeUserById(long id) {
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("DELETE FROM mytableusers WHERE Id =" + id);
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM mytableusers");
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                for (User user : users) {
                    System.out.println(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("TRUNCATE TABLE mytableusers");
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
                System.out.println("the table was not cleared!");
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
