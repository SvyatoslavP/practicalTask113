package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl extends HibernateUtil implements UserDao {
    private Session session = openSession();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        transaction = session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS mytableusers" +
                "(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(30)," +
                "lastName VARCHAR(30)," +
                "age TINYINT" +
                ")";
        session.createNativeQuery(sql).executeUpdate();
        transaction.commit();
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        transaction = session.beginTransaction();
        session.createNativeQuery("DROP TABLE IF EXISTS mytableusers").executeUpdate();
        transaction.commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        transaction = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        transaction.commit();
        System.out.println("User : " + name + " added to the table!");
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        transaction = session.beginTransaction();
        session.createNativeQuery("DELETE FROM mytableusers WHERE Id =" + id).executeUpdate();
        transaction.commit();
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Query query = session.createNativeQuery("SELECT * FROM mytableusers").addEntity(User.class);
        List<User> userList = query.list();
        transaction.commit();
        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        transaction = session.beginTransaction();
        session.createNativeQuery("TRUNCATE TABLE mytableusers").executeUpdate();
        transaction.commit();
    }
}
