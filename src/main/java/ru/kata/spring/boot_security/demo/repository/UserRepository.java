package ru.kata.spring.boot_security.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepository {

    @PersistenceUnit
    private final EntityManagerFactory entityManagerFactory;
    public UserRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    private EntityManager entityManager;

    private EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public List<User> getUsersList() {
        return getEntityManager().createQuery("from User").getResultList();
    }

    public void deleteById(long id) {
        User user = getUserById(id);
        if (user != null) {
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(user);
            getEntityManager().getTransaction().commit();
        }
    }

    public void saveUser(User user) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(user);
        getEntityManager().getTransaction().commit();
    }

    public void updateUser(User user) {
        System.out.println("Update user " + user);
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(user);
        getEntityManager().getTransaction().commit();
    }

    public User getUserById(long id) {
        return getEntityManager().find(User.class, id);
    }
    public User getUserByUsername(String username){
        try {
            Query query = getEntityManager().createQuery("from User where username = :paramName");
            query.setParameter("paramName", username);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
