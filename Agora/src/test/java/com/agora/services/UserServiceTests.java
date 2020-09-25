package com.agora.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import com.agora.models.LoginTemplate;
import com.agora.models.User;
import com.agora.services.UserService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import java.util.Set;


public class UserServiceTests {

    Set<User> setMock;
    UserService userServiceMock;
    User mockUser1;
    User mockUser2;
    LoginTemplate mockLoginTemplate;

    @Mock
    private UserService userService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        setMock = mock(Set.class);
        userServiceMock = mock(UserService.class);
        mockUser1 = new User(1, "user1first", "user1last", "user1username", "user1pass", "user1email");
        mockUser2 = new User(2, "user2first", "user2last", "user2username", "user2pass", "user2email");
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
		User mockUser3 = new User(1, "user1first", "user1last", "user1username", "user1pass", "user1email");
		mockUser1.setFirstName("Tom");
		userServiceMock.update(mockUser1);
		User mockUser4 = userServiceMock.findUserById(1);
		assertNotEquals(mockUser3, mockUser4);
	}

}
