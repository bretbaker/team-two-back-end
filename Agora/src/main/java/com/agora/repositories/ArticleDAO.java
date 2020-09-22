package com.agora.repositories;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.agora.models.Article;
import com.agora.util.HibernateUtil;

@Repository
public class ArticleDAO {
	
	public Set<Article> findAll() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        Set<Article> result = session.createQuery("FROM Article a", Article.class)
                .getResultStream()
                .collect(Collectors.toSet());

        tx.commit();

        return result;
    }
	
	public Article findArticleById(int ar_id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
    try {
        Article result = session.createQuery("FROM Article a where article_id = ?1", Article.class)
                .setParameter(1, ar_id)
                .getSingleResult();

        tx.commit();
        return result;
    } catch(NoResultException e) {
        e.printStackTrace();
        tx.rollback();
        return null;
    }
    
	}


    public void save(Article art) {
        Session session = HibernateUtil.getSession();

        Transaction tx = session.beginTransaction();

        session.save(art);

        tx.commit();
    }

    public void update(Article art) {
        Session session = HibernateUtil.getSession();

        Transaction tx = session.beginTransaction();

        session.merge(art);

        tx.commit();
    }

    public void delete(Article art) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        session.delete(art);

        tx.commit();
    }

}
