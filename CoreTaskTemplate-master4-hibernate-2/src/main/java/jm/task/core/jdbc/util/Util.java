package jm.task.core.jdbc.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
    public static SessionFactory buildSessionFactoryInHibernate() {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().
                    configure().build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (HibernateException he) {
            System.out.println("Session Factory creation failure");
            throw he;
        }
    }
}
