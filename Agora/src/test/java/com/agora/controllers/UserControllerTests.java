package com.agora.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.ServletContext;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.agora.models.LoginTemplate;
import com.agora.models.User;
import com.agora.repositories.UserDAO;
import com.agora.services.HashingService;
import com.agora.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
@WebAppConfiguration
public class UserControllerTests {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void givenWAC_whenServletContext_thenReturnUserController() {
		ServletContext servletContext = webApplicationContext.getServletContext();
		Assert.assertNotNull(servletContext);
		Assert.assertTrue(servletContext instanceof MockServletContext);
		Assert.assertNotNull(webApplicationContext.getBean("userController"));
	}
	
	@Test
	public void testInsertUser() throws Exception {
		int id = 0;
		String json = "{\n" +
                "  \"user_id\": " + id + ",\n" +
                "  \"firstName\": \"tom\",\n" +
                "  \"lastName\": \"cat\",\n" +
                "  \"userName\": \"tc\",\n" +
                "  \"password\": \"pass\",\n" +
                "  \"email\": \"tc@mail.com\",\n" +
                "  \"articles\": " + null + "\n" +
                "}";
		System.out.println(json);
		mockMvc.perform(post("/user")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andExpect(status().isAccepted());
	}
	
	@Test
	public void testGetAllUsers() throws Exception {
		mockMvc.perform(get("/user"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void testGetUserByIdBadId() throws Exception {
		mockMvc.perform(get("/user/4"))
			.andExpect(status().isNoContent());
	}
	
	@Test
	public void testGetUserById() throws Exception {
		mockMvc.perform(get("/user/0"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
//	@Test
//	public void testUserLoginSuccess() throws Exception {
//		String json = "{\n" +
//                "  \"userName\": \"tc\",\n" +
//                "  \"password\": \"pass\"\n" +
//                "}";
//		System.out.println(json);
//		mockMvc.perform(post("/user/login")
//			.contentType(MediaType.APPLICATION_JSON)
//			.content(json))
//			.andExpect(status().isAccepted())
//			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
//	}
	
//	@Test
//	public void testUserLoginFail() throws Exception {
//		String json = "{\n" +
//                "  \"userName\": \"tc\",\n" +
//                "  \"password\": \"pass\"\n" +
//                "}";
//		System.out.println(json);
//		mockMvc.perform(post("/user/login")
//			.contentType(MediaType.APPLICATION_JSON)
//			.content(json))
//			.andExpect(status().isBadRequest());
//	}
	
//	@Test
//	public void testDeleteUserSuccess() throws Exception {
//		mockMvc.perform(delete("/user/0"))
//			.andExpect(status().isAccepted())
//			.andExpect(content().string("true"));
//	}
	
	@Test
	public void testDeleteUserBadId() throws Exception {
		mockMvc.perform(delete("/user/0"))
			.andExpect(status().isBadRequest());
	}

}
