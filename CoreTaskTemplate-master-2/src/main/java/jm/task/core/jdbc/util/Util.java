package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String CONNECTION_URL ="jdbc:mysql://localhost:3306/mydatabase?useUnicode=true&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "Fynbevtybt@19912002";
    public static Connection getMySQLConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(CONNECTION_URL,USER_NAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
