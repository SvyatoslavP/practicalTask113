package ru.panifidkin.springsecurityapp.dao;

import org.springframework.stereotype.Component;
import ru.panifidkin.springsecurityapp.model.Role;
import ru.panifidkin.springsecurityapp.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserDaoImpl implements UserDao {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<User> allUsers(int page) {
        List<User> userList = new ArrayList<>();
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            userList = em.createQuery("from User").setFirstResult(10 * (page - 1)).setMaxResults(10).getResultList();
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
        Set<Role> defaultSet = new HashSet<>();
        defaultSet.add(new Role(3L, "ROLE_EMPLOYEE"));
        EntityManager em = null;
        System.out.println("МЕТОД ADD");
        try {
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            user.setRoles(defaultSet);
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
            user.setName(user.getUsername());
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

    @Override
    @Transactional
    public User getUserByName(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.name = :name", User.class);
        User user = query.setParameter("name", name)
                .getSingleResult();
        return user;
    }
}

