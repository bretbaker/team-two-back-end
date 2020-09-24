package com.agora.controllers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
@WebAppConfiguration
public class ArticleControllerTests {

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
		Assert.assertNotNull(webApplicationContext.getBean("articleController"));
	}
	
	@Test
	public void testInsertArticle() throws Exception {
		int id = 0;
		String json = "{\n" +
				"  \"article_id\": " + id + ",\n" +
                "  \"title\": \"t\",\n" +
                "  \"description\": \"d\",\n" +
                "  \"image\": " + null + ",\n" +
                "  \"publishedAt\": \"pa\",\n" +
                "  \"content\": \"c\",\n" +
                "  \"status\": " + 1 + "\n" +
                "}";
		System.out.println(json);
		mockMvc.perform(post("/article")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andExpect(status().isAccepted());
	}
	
	@Test
	public void testGetAllArticles() throws Exception {
		mockMvc.perform(get("/article"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
//	@Test
//	public void testGetArticleById() throws Exception {
//		mockMvc.perform(get("/article/1"))
//			.andExpect(status().isOk())
//			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
//	}
	
	@Test
	public void testGetArticleByIdBadId() throws Exception {
		mockMvc.perform(get("/article/0"))
			.andExpect(status().isNoContent());
	}
	
	@Test
	public void testDeleteArticleBadId() throws Exception {
		mockMvc.perform(delete("/article/0"))
			.andExpect(status().isBadRequest());
	}

}
