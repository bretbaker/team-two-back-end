package com.agora.repositories;

import com.agora.models.User;
import com.agora.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserDAO {

    public Set<User> findAll() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        Set<User> result = session.createQuery("FROM User u", User.class)
                .getResultStream()
                .collect(Collectors.toSet());

        tx.commit();

        return result;
    }

    public User findUserById(int user_id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
    try {
        User result = session.createQuery("FROM User u where user_id = ?1", User.class)
                .setParameter(1, user_id)
                .getSingleResult();

        tx.commit();
        return result;
    } catch(NoResultException e) {
        e.printStackTrace();
        tx.rollback();
        return null;
    }

    }


    public void save(User user) {
        Session session = HibernateUtil.getSession();

        Transaction tx = session.beginTransaction();

        session.save(user);

        tx.commit();
    }

    public void update(User user) {
        Session session = HibernateUtil.getSession();

        Transaction tx = session.beginTransaction();

        session.merge(user);

        tx.commit();
    }

    public void delete(User user) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        session.delete(user);

        tx.commit();
    }

    public User findByUsername(String username) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            User result = session.createQuery("FROM User u where userName = ?1", User.class)
                    .setParameter(1, username)
                    .getSingleResult();

            tx.commit();
            return result;
        } catch(NoResultException e) {
            e.printStackTrace();
            tx.rollback();
            return null;
        }

    }
}