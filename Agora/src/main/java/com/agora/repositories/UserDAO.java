package com.agora.repositories;

import com.agora.models.User;
import com.agora.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

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
}