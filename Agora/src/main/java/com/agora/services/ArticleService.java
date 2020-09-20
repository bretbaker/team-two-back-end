package com.agora.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agora.models.Article;
import com.agora.repositories.ArticleDAO;

@Service
public class ArticleService {
	
	ArticleDAO articleDAO;

    @Autowired
    public ArticleService(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    public Set<Article> findAll() {
        return articleDAO.findAll();
    }

    public void save(Article art) {
        articleDAO.save(art);
    }

    public void update(Article art) {
        articleDAO.update(art);
    }

    public Article findArticleById(int id) { 
    	return articleDAO.findArticleById(id); 
    }
    public void delete(Article art) { 
    	articleDAO.delete(art); 
    }

    public Article checkUsername(Article art) { 
    	return art; 
    }

}
