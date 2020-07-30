package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        try {
            sessionFactory.getCurrentSession().save(user);
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        Query query = null;
        try {
            query = sessionFactory.getCurrentSession().createQuery("from User");
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return query.getResultList();
    }

    @Override
    public List<User> getTheUserByHisCar(String number, int series) {
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        Query query = null;
        transaction.begin();
        try {
            query = sessionFactory.getCurrentSession().createQuery(
                    "SELECT u FROM User u " +
                            "JOIN FETCH u.car " +
                            "WHERE car_number = :paramNumber and car_series = :paramSeries");
            query.setParameter("paramNumber", number);
            query.setParameter("paramSeries", series);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return query.getResultList();
    }
}
