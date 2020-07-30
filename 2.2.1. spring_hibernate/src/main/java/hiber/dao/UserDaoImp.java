package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
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
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> getTheUserByHisCar(String number, int series) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT u FROM User u " +
                        "JOIN FETCH u.car " +
                        "WHERE car_number = :paramNumber and car_series = :paramSeries");
        query.setParameter("paramNumber", number);
        query.setParameter("paramSeries", series);
        return query.getResultList();

    }
}
