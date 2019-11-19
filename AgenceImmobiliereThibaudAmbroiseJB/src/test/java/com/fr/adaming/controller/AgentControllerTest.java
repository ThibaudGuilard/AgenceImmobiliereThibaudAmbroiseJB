package com.fr.adaming.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import javax.validation.ConstraintViolationException;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fr.adaming.AgenceImmobiliereThibaudAmbroiseJbApplicationTests;
import com.fr.adaming.web.dto.AgentDto;

@SpringBootTest
public class AgentControllerTest extends AgenceImmobiliereThibaudAmbroiseJbApplicationTests {

	@Test
	@Sql(statements = "delete from agent where email like 'email@email.fr'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createValidAgent_shouldReturnStatus200AndDtoNotNull() throws JsonProcessingException, Exception {

		AgentDto dto = new AgentDto("email@email.fr", "Jean Claude", "1122334455", false, "123456789",
				LocalDate.parse("2017-05-15"));

		ResultActions sendHttpRequestInJson = mvc.perform(post("/api/agent/create_agent")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto)));

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

		AgentDto dto = new AgentDto(null, "Jean Claude", "1122334455", false, "123456789",
				LocalDate.parse("2017-05-15"));

		ResultActions sendHttpRequestInJson = mvc.perform(post("/api/agent/create_agent")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto)));

		String result = sendHttpRequestInJson.andExpect(status().is(400)).andReturn().getResponse()
				.getContentAsString();

		assertTrue(result.isEmpty());

	}

	@Test
	@Sql(statements = "delete from agent where email like 'bla'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createAgentWithEmailNotValid_shouldReturnStatus400EmptyString()
			throws JsonProcessingException, Exception {

		AgentDto dto = new AgentDto("bla", "Jean Claude", "1122334455", false, "123456789",
				LocalDate.parse("2017-05-15"));

		ResultActions sendHttpRequestInJson = mvc.perform(post("/api/agent/create_agent")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto)));

		String result = sendHttpRequestInJson.andExpect(status().is(400)).andReturn().getResponse()
				.getContentAsString();

		assertTrue(result.isEmpty());

	}

	@Test
	@Sql(statements = "delete from agent where email like 'email123@email.fr'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createAgentWithPwdLengthInferiorTo8_shouldReturnEmptyString()
			throws JsonProcessingException, Exception {

		AgentDto dto = new AgentDto("email123@email.fr", "Jean Claude", "1122334455", false, "1234567",
				LocalDate.parse("2017-05-15"));

		ResultActions sendHttpRequestInJson = mvc.perform(post("/api/agent/create_agent")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto)));

		String result = sendHttpRequestInJson.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertTrue(result.isEmpty());

	}

	@Test
	@Sql(statements = "delete from agent where email like 'email123123@email.fr'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createAgentWithPwdLengthSuperiorTo16_shouldReturnshouldReturnEmptyString()
			throws JsonProcessingException, Exception {

		AgentDto dto = new AgentDto("email123123@email.fr", "Jean Claude", "1122334455", false, "12345678901234567",
				LocalDate.parse("2017-05-15"));

		ResultActions sendHttpRequestInJson = mvc.perform(post("/api/agent/create_agent")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto)));

		String result = sendHttpRequestInJson.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertTrue(result.isEmpty());

	}

	@Test
	@Sql(statements = "delete from agent where email like 'email1231234@email.fr'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createAgentWithDateRecrutementInTheFuture_shouldReturnEmptyString()
			throws JsonProcessingException, Exception {

		AgentDto dto = new AgentDto("email1231234@email.fr", "Jean Claude", "1122334455", false, "123456789",
				LocalDate.parse("2020-05-15"));

		ResultActions sendHttpRequestInJson = mvc.perform(post("/api/agent/create_agent")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto)));

		String result = sendHttpRequestInJson.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertTrue(result.isEmpty());

	}

	@Test
	@Sql(statements = "insert into agent values (551, 0, 'a1@aaa.com', 'JPP', 1234567890, '2007-05-15', 'azertyuiop')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into agent values (552, 0, 'a2@aaa.com', 'JPP', 1234567890, '2007-05-15', 'azertyuiop')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into agent values (553, 0, 'a3@aaa.com', 'JPP', 1234567890, '2007-05-15', 'azertyuiop')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from agent where id =551", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from agent where id =552", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from agent where id =553", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void printAgents_shouldReturnStatus200AndStringNotEmpty() throws Exception {

		ResultActions sendHttpRequestInJson = mvc
				.perform(get("/api/agent/print_agents").contentType(MediaType.APPLICATION_JSON));

		String result = sendHttpRequestInJson.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertFalse(result.isEmpty());
		assertNotEquals("[]", result);

	}

	@Test
	public void printAgentsWithNoAgents_shouldReturnEmptyList() throws Exception {

		ResultActions sendHttpRequestInJson = mvc
				.perform(get("/api/agent/print_agents").contentType(MediaType.APPLICATION_JSON));

		String result = sendHttpRequestInJson.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("[]", result);

	}

	@Test
	@Sql(statements = "insert into agent values (551, 0, 'bla@bla.com', 'Jean Claude', 1122334455, '2007-05-15', 'azertyuiop')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from agent where email like 'bla@bla.com' ", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void deleteValidAgent_shouldReturnEmptyString() throws JsonProcessingException, Exception {

		AgentDto dto = new AgentDto(551L, "bla@bla.com", "Jean Claude", "1122334455", false, "azertyuiop",
				LocalDate.parse("2017-05-15"), null);

		ResultActions sendHttpRequestInJson = mvc.perform(post("/api/agent/delete_agent")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto)));

		String result = sendHttpRequestInJson.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		AgentDto dtoResult = mapper.readValue(result, AgentDto.class);

		assertNotNull(dtoResult);
		assertEquals("bla@bla.com", dtoResult.getEmail());
		assertEquals("Jean Claude", dtoResult.getFullName());
		assertEquals("1122334455", dtoResult.getTelephone());
		assertEquals(true, dtoResult.isDeleted());
		assertEquals("azertyuiop", dtoResult.getPwd());
		assertEquals(LocalDate.parse("2017-05-15"), dtoResult.getDateRecrutement());

	}
	
	@Test
	public void deleteAgentThatDoesNotExist_shouldReturn() throws JsonProcessingException, Exception {
		
		AgentDto dto = new AgentDto();
		
		ResultActions sendHttpRequestInJson = mvc.perform(post("/api/agent/delete_agent")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto)));
		
		String result = sendHttpRequestInJson.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		assertTrue(result.isEmpty());
		
	}

}
