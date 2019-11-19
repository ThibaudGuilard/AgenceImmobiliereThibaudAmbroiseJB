package com.fr.adaming.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fr.adaming.AgenceImmobiliereThibaudAmbroiseJbApplicationTests;
import com.fr.adaming.web.dto.AgentDto;

@SpringBootTest
public class AgentControllerTest extends AgenceImmobiliereThibaudAmbroiseJbApplicationTests  {
	
	
	
	@Test
	@Sql(statements = "delete from agent where email like 'email@email.fr'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createValidAgent_shouldReturnStatus200AndDtoNotNull() throws JsonProcessingException, Exception{
		
		AgentDto dto = new AgentDto("email@email.fr", "Jean Claude", "1122334455", false, "123456789", LocalDate.parse("2017-05-15"));		
		
		ResultActions sendHttpRequestInJson = mvc.perform(post("/api/agent/create_agent")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto)));
		
		String result = sendHttpRequestInJson.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		AgentDto dtoResult = mapper.readValue(result, AgentDto.class);
		
		assertNotNull(dtoResult);
		assertEquals("email@email.fr", dtoResult.getEmail());
		assertEquals("Jean Claude", dtoResult.getFullName());
		assertEquals("1122334455", dtoResult.getTelephone());
		assertEquals(false, dtoResult.isDeleted());
		assertEquals("123456789", dtoResult.getPwd());
		assertEquals(LocalDate.parse("2017-05-15"), dtoResult.getDateRecrutement());
		
	}
	
	@Test
	@Sql(statements = "delete from agent where email IS NULL", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createAgentWithEmailNull_shouldReturnStatus400EmptyString() throws JsonProcessingException, Exception {
		
		AgentDto dto = new AgentDto(null, "Jean Claude", 
				"1122334455", false, "123456789", LocalDate.parse("2017-05-15"));
		
		ResultActions sendHttpRequestInJson = mvc.perform(post("/api/agent/create_agent")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto)));
		
		String result = sendHttpRequestInJson.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();
		
		assertTrue(result.isEmpty());
		
	}
	
	@Test
	@Sql(statements = "delete from agent where email like 'bla'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createAgentWithEmailNotValid_shouldReturnStatus400EmptyString() throws JsonProcessingException, Exception {
		
		AgentDto dto = new AgentDto("bla", "Jean Claude", 
				"1122334455", false, "123456789", LocalDate.parse("2017-05-15"));
		
		ResultActions sendHttpRequestInJson = mvc.perform(post("/api/agent/create_agent")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto)));
		
		String result = sendHttpRequestInJson.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();
		
		assertTrue(result.isEmpty());
		
	}
	
	
	@Test
	@Sql(statements = "delete from agent where email like 'email123@email.fr'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createAgentWithPwdLengthInferiorTo8_shouldReturnEmptyString() throws JsonProcessingException, Exception {
				
		AgentDto dto = new AgentDto("email123@email.fr", "Jean Claude", "1122334455", false, "1234567", LocalDate.parse("2017-05-15"));
					
		ResultActions sendHttpRequestInJson = mvc.perform(post("/api/agent/create_agent").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto)));
		
		String result = sendHttpRequestInJson.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		assertTrue(result.isEmpty());
		
	}
	
	@Test
	@Sql(statements = "delete from agent where email like 'email123123@email.fr'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createAgentWithPwdLengthSuperiorTo16_shouldReturnshouldReturnEmptyString() throws JsonProcessingException, Exception {
		

		AgentDto dto = new AgentDto("email123123@email.fr", "Jean Claude", "1122334455", false, "12345678901234567", LocalDate.parse("2017-05-15"));

		
		ResultActions sendHttpRequestInJson = mvc.perform(post("/api/agent/create_agent")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto)));
		
		String result = sendHttpRequestInJson.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		assertTrue(result.isEmpty());
		
	}
	
	@Test
	@Sql(statements = "delete from agent where email like 'email1231234@email.fr'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createAgentWithDateRecrutementInTheFuture_shouldReturnEmptyString() throws JsonProcessingException, Exception {
		
		AgentDto dto = new AgentDto("email1231234@email.fr", "Jean Claude", "1122334455", false, "12345678901234567", LocalDate.parse("2020-05-15"));
		
		ResultActions sendHttpRequestInJson = mvc.perform(post("/api/agent/create_agent")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto)));
		
		String result = sendHttpRequestInJson.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		assertTrue(result.isEmpty());
		
	}

}
