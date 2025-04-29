package org.example.dao;

import org.example.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoHibernateImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        em.remove(em.find(User.class, id));
    }

    @Override
    public User findById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) em.createQuery("from User").getResultList();
    }
}
