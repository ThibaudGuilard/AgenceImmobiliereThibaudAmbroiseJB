package com.fr.adaming.web.controller.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.web.dto.ClientDto;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	private ObjectMapper mapper;
	
	public void testConvertJsonToJavaObject() throws JsonMappingException, JsonProcessingException {
		String json = "{\"email\" : \"xxxx@gmail.com\", \"fullname\" : \"Jean\", \"type\" : \"VENDEUR\", \"telephone\" : \"1122334455\"}";
		
		ClientDto dto = mapper.readValue(json, ClientDto.class);
		
		assertNotNull(dto);
		assertEquals("com", dto.getEmail());
		assertEquals("Jean", dto.getFullName());
	}
	
//	@Test
//	public void testConvertJavaObjectToJsonAsString() {
//		ClientDto dto = new ClientDto(id, email, fullName, telephone, type, agent, biens);
//		
//		String json = mapper.writeValueAsString(dto);
//		
//		
//	}
	
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
		
		//Prepare inputs
		//(créer un constructeur qui ne prend pas l'id)
		ClientDto dto = new ClientDto(id, email, fullName, telephone, type, agent, biens);
		
		String result = mvc.perform(post("/api/hello")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
//		String result = mvc.perform(post("/api/hello")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content("{\"email\" : \"xxxx@gmail.com\", \"fullname\" : \"Jean\", \"type\" : \"VENDEUR\", \"telephone\" : \"1122334455\"}"))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		ClientDto dtoResult = mapper.readValue(result, ClientDto.class);
		
		assertNotNull(dtoResult);
		assertEquals("", dto.getEmail());
		
		System.out.println("DEBUG CREATE VALID CLIENT : " + result );
		
	}

}
