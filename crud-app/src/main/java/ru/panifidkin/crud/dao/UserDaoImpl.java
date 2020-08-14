package ru.panifidkin.crud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.panifidkin.crud.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<User> allUsers(int page) {
        List<User> userList = new ArrayList<>();
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            userList = em.createQuery("FROM User").setFirstResult(10 * (page - 1)).setMaxResults(10).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return userList;
    }

    @Override
    public void add(User user) {
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(User user) {
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            user = em.find(User.class, user.getId());
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void edit(User user) {
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            user.setName(user.getName());
            user.setLastName(user.getLastName());
            user.setAge(user.getAge());
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public User getById(int id) {
        EntityManager em = null;
        User user = null;
        try {
            em = entityManagerFactory.createEntityManager();
            user = em.find(User.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return user;
    }

    @Override
    public int usersCount() {
        EntityManager em = null;
        int count = 0;
        try {
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            count = em.createQuery("select count(*) from User ", Number.class).getSingleResult().intValue();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return count;
    }
}
