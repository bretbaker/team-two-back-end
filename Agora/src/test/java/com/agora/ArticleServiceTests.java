package com.agora;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import com.agora.controllers.UserController;
import com.agora.models.Article;
import com.agora.models.LoginTemplate;
import com.agora.models.User;
import com.agora.repositories.UserDAO;
import com.agora.services.ArticleService;
import com.agora.services.UserService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Set;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ArticleServiceTests {
	
    private RestAssuredMockMvc mockMvc;
    Set<User> setMock;
    Set<Article> setMock2;
    UserService userServiceMock;
    ArticleService articleServiceMock;
    User mockUser1;
    User mockUser2;
    Article mockArticle1;
    Article mockArticle2;
    LoginTemplate mockLoginTemplate;

    @Mock
    private ArticleService articleService;

//    @InjectMocks
//    private ArticleController articleController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        setMock = mock(Set.class);
        setMock2 = mock(Set.class);
        articleServiceMock = mock(ArticleService.class);
        userServiceMock = mock(UserService.class);
        mockUser1 = new User(1, "user1first", "user1last", "user1username", "user1pass", "user1email", null);
        mockUser2 = new User(2, "user2first", "user2last", "user2username", "user2pass", "user2email", null);
        mockArticle1 = new Article(1, mockUser1, "desc1", null, "pa1", "cont1", 1);
        mockArticle2 = new Article(2, mockUser2, "desc2", null, "pa2", "cont2", 1);
        mockLoginTemplate = new LoginTemplate(mockUser1.getUserName(), mockUser1.getPassword());
        setMock.add(mockUser1);
        setMock.add(mockUser2);
        setMock2.add(mockArticle1);
        setMock2.add(mockArticle2);
//        when(userServiceMock.findUserById(anyInt())).thenReturn(null);
//        when(userServiceMock.findUserById(1)).thenReturn(mockUser1);
//        when(userServiceMock.findUserById(2)).thenReturn(mockUser2);
//        when(userServiceMock.findByUserName(anyObject())).thenReturn(null);
//        when(userServiceMock.findByUserName(mockLoginTemplate)).thenReturn(mockUser1);
//        when(userServiceMock.checkUsername(anyObject())).thenReturn(false);
//        when(userServiceMock.checkUsername(mockLoginTemplate)).thenReturn(true);
        when(articleServiceMock.findArticleById(anyInt())).thenReturn(null);
        when(articleServiceMock.findArticleById(1)).thenReturn(mockArticle1);
        when(articleServiceMock.findArticleById(2)).thenReturn(mockArticle2);
    }
    
    @Test
	public void testFindAllArticlesNone() {
    	when(articleServiceMock.findAll()).thenReturn(null);
		assertEquals(null, articleServiceMock.findAll());
	}
    
    @Test
	public void testFindAllArticles() {
    	when(articleServiceMock.findAll()).thenReturn(setMock2);
		assertEquals(setMock2, articleServiceMock.findAll());
	}
    
    @Test
	public void testFindArticleByIdNone() {
		assertEquals(null, articleServiceMock.findArticleById(anyInt()));
	}
	
	@Test
	public void testFindArticleById() {
		assertEquals(mockArticle1, articleServiceMock.findArticleById(1));
		assertEquals(mockArticle2, articleServiceMock.findArticleById(2));
	}
	
	@Test
	public void testUpdateArticle() {
		Article mockArticle3 = new Article(1, mockUser1, "desc1", null, "pa1", "cont1", 1);
		mockArticle1.setDescription("dddddd");
		articleServiceMock.update(mockArticle1);
		Article mockArticle4 = articleServiceMock.findArticleById(1);
		assertNotEquals(mockArticle3, mockArticle4);
	}

}
