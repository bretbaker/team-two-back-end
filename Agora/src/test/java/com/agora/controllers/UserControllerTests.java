package com.agora.controllers;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.agora.repositories.UserDAO;
import com.agora.services.HashingService;
import com.agora.services.UserService;

//@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private UserService userService;
	
	@Mock
	private HashingService hashingService;
	
	@Mock
	private UserDAO userDao; 
	
	@InjectMocks
	private UserController userController; 
	
	@Before
	public void setup() throws Exception {
//		final UserController userController = new UserController(userService, hashingService);
		userDao = Mockito.mock(UserDAO.class);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

//	@Test
//	public void testGetAll() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/user")
//			.accept(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(1)));
//	}

}
