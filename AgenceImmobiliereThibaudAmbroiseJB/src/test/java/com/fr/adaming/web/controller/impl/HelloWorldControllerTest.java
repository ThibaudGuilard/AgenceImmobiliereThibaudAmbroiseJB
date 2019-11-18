package com.fr.adaming.web.controller.impl;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void sayHello_shouldReturnHelloWorld() throws Exception {
		
		String result = mvc.perform(get("/api/hello")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		System.out.println("DEBUG RESULT OF HTTP METHOD : "+ result);
		
		assertEquals("HelloWorld From Spring WEB", result);
	}
	
	@Test
	public void createValidClient_shouldReturnStatus200AndDtoNotNull() throws UnsupportedEncodingException, Exception {
		
		String result = mvc.perform(post("/api/hello")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\" : \"xxxx@gmail.com\", \"fullname\" : \"Jean\", \"type\" : \"VENDEUR\", \"telephone\" : \"1122334455\"}"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		System.out.println("DEBUG CREATE VALID CLIENT : " + result );
		
	}

}
