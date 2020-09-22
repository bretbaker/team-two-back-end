package com.agora;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import com.agora.controllers.UserController;
import com.agora.models.Article;
import com.agora.models.LoginTemplate;
import com.agora.models.User;
import com.agora.repositories.UserDAO;
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


public class UserServiceTests {


    private RestAssuredMockMvc mockMvc;
    Set<User> setMock;
    UserService userServiceMock;
    User mockUser1;
    User mockUser2;
    LoginTemplate mockLoginTemplate;

    @Mock
    private UserService userService;

//    @InjectMocks
//    private UserController userController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        setMock = mock(Set.class);
        userServiceMock = mock(UserService.class);
        mockUser1 = new User(1, "user1first", "user1last", "user1username", "user1pass", "user1email", null);
        mockUser2 = new User(2, "user2first", "user2last", "user2username", "user2pass", "user2email", null);
        mockLoginTemplate = new LoginTemplate(mockUser1.getUserName(), mockUser1.getPassword());
        setMock.add(mockUser1);
        setMock.add(mockUser2);
        when(userServiceMock.findUserById(anyInt())).thenReturn(null);
        when(userServiceMock.findUserById(1)).thenReturn(mockUser1);
        when(userServiceMock.findUserById(2)).thenReturn(mockUser2);
        when(userServiceMock.findByUserName(anyObject())).thenReturn(null);
        when(userServiceMock.findByUserName(mockLoginTemplate)).thenReturn(mockUser1);
        when(userServiceMock.checkUsername(anyObject())).thenReturn(false);
        when(userServiceMock.checkUsername(mockLoginTemplate)).thenReturn(true);
    }
	
//    Service Layer Tests
	@Test
	public void testFindAllUsersNone() {
		when(userServiceMock.findAll()).thenReturn(null);
		assertEquals(null, userServiceMock.findAll());
	}
	
	@Test
	public void testFindAllUsers() {
		when(userServiceMock.findAll()).thenReturn(setMock);
		assertEquals(setMock, userServiceMock.findAll());
	}
	
	@Test
	public void testFindUserByIdNone() {
		assertEquals(null, userServiceMock.findUserById(anyInt()));
	}
	
	@Test
	public void testFindUserById() {
		assertEquals(mockUser1, userServiceMock.findUserById(1));
		assertEquals(mockUser2, userServiceMock.findUserById(2));
	}
	
	@Test
	public void testFindUserByUserNameNone() {
		assertEquals(null, userServiceMock.findByUserName(anyObject()));
	}
	
	@Test
	public void testFindUserByUserName() {
		assertEquals(mockUser1, userServiceMock.findByUserName(mockLoginTemplate));
	}
	
	@Test
	public void testCheckUsernameNone() {
		assertFalse(userServiceMock.checkUsername(anyObject()));
	}
	
	@Test
	public void testCheckUsername() {
		assertTrue(userServiceMock.checkUsername(mockLoginTemplate));
	}

	@Test
	public void testUpdateUser() {
		User mockUser3 = new User(1, "user1first", "user1last", "user1username", "user1pass", "user1email", null);
		mockUser1.setFirstName("Tom");
		userServiceMock.update(mockUser1);
		User mockUser4 = userServiceMock.findUserById(1);
		assertNotEquals(mockUser3, mockUser4);
	}
	
//	Getter/Setter Tests
	@Test
	public void testUserGetId() {
		assertEquals(1, userServiceMock.findUserById(1).getId());
	}
	
	@Test
	public void testUserGetFirstName() {
		assertEquals("user1first", userServiceMock.findUserById(1).getFirstName());
	}
	
	@Test
	public void testUserGetLastName() {
		assertEquals("user1last", userServiceMock.findUserById(1).getLastName());
	}
	
	@Test
	public void testUserGetUserName() {
		assertEquals("user1username", userServiceMock.findUserById(1).getUserName());
	}
	
	@Test
	public void testUserGetPassword() {
		assertEquals("user1pass", userServiceMock.findUserById(1).getPassword());
	}
	
	@Test
	public void testUserGetEmail() {
		assertEquals("user1email", userServiceMock.findUserById(1).getEmail());
	}
	



}
