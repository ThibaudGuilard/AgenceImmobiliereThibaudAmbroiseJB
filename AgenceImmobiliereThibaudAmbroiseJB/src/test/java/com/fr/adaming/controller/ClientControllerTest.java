package com.fr.adaming.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fr.adaming.AgenceImmobiliereThibaudAmbroiseJbApplicationTests;
import com.fr.adaming.entity.enume.TypeClient;
import com.fr.adaming.web.dto.ClientDto;

@SpringBootTest
public class ClientControllerTest extends AgenceImmobiliereThibaudAmbroiseJbApplicationTests{

	
	@Test
	public void createValidClient_shouldReturnStatus200AndDtoNotNull() throws UnsupportedEncodingException, Exception {
		
		//Prepare inputs
		ClientDto dto = new ClientDto("email@123456.fr", "fullName", "1122334455", TypeClient.ACHETEUR );
		
		//invoquer la methode
		String result = mvc.perform(post("/api/client/create_client")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		//on peut trouver une methode andDoPrint qui peut etre pratique
		
		ClientDto dtoResult = mapper.readValue(result, ClientDto.class);
		
		assertNotNull(dtoResult);
		assertEquals("email@123456.fr", dto.getEmail());
		
		System.out.println("DEBUG CREATE VALID CLIENT : " + result );
		
	}
	
	@Test
	public void createClientEmailNull_shouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		
		//Prepare inputs
		ClientDto dto = new ClientDto(null, "fullName", "1122334455", TypeClient.ACHETEUR );
		
		//invoquer la methode
		mvc.perform(post("/api/client/create_client")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));
		
	}
	
	@Test
	public void createClientEmailEmpty_shouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		
		//Prepare inputs
		ClientDto dto = new ClientDto("", "fullName", "1122334455", TypeClient.ACHETEUR );
		
		//invoquer la methode
		mvc.perform(post("/api/client/create_client")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));
		
	}
	
	@Test
	public void createClientEmailBlank_shouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		
		//Prepare inputs
		ClientDto dto = new ClientDto(" ", "fullName", "1122334455", TypeClient.ACHETEUR );
		
		//invoquer la methode
		mvc.perform(post("/api/client/create_client")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));
		
	}
	@Test
	public void createClientFullNameNull_shouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		
		//Prepare inputs
		ClientDto dto = new ClientDto("email@123456.fr", null, "1122334455", TypeClient.ACHETEUR );
		
		//invoquer la methode
		mvc.perform(post("/api/client/create_client")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));
		
	}
	
	@Test
	public void createClientFullNameEmpty_shouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		
		//Prepare inputs
		ClientDto dto = new ClientDto("email@123456.fr", "", "1122334455", TypeClient.ACHETEUR );
		
		//invoquer la methode
		mvc.perform(post("/api/client/create_client")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));
		
	}
	
	@Test
	public void createClientFullNameBlank_shouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		
		//Prepare inputs
		ClientDto dto = new ClientDto("email@123456.fr", " ", "1122334455", TypeClient.ACHETEUR );
		
		//invoquer la methode
		mvc.perform(post("/api/client/create_client")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));
		
	}
	
	@Test
	public void createClientTelephoneNull_shouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		
		//Prepare inputs
		ClientDto dto = new ClientDto("email@123456.fr", "fullName", null, TypeClient.ACHETEUR );
		
		//invoquer la methode
		mvc.perform(post("/api/client/create_client")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));
		
	}
	
	@Test
	public void createClientTelephoneEmpty_shouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		
		//Prepare inputs
		ClientDto dto = new ClientDto("email@123456.fr", "fullName", "", TypeClient.ACHETEUR );
		
		//invoquer la methode
		mvc.perform(post("/api/client/create_client")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));
		
	}
	
	@Test
	public void createClientTelephoneBlank_shouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		
		//Prepare inputs
		ClientDto dto = new ClientDto("email@123456.fr", "fullName", " ", TypeClient.ACHETEUR );
		
		//invoquer la methode
		mvc.perform(post("/api/client/create_client")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));
		
	}
	
	@Test
	public void printClientTest_shouldReturnListOfClient() throws Exception {
		String result = mvc.perform(get("/api/client/print").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
		assertNotNull(result);
	}
	
	@Test
	@Sql(statements = { "insert into client(id, deleted,email,full_name,telephone,type) values (112, false, 'client@mail.com', 'John Doe', '8888888', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = {"delete from client where id=112"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void DeleteClientExistant_shouldReturnClientRetourEtDeletedTrue() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		//Prepare inputs
				ClientDto dto = new ClientDto(112, "client@mail.com", "John Doe", "8888888", TypeClient.ACHETEUR, false);
				
				//invoquer la methode
				String result = mvc.perform(get("/api/client/112/delete_client")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
						.andExpect(status()
								.isOk())
						.andReturn()
						.getResponse()
						.getContentAsString();
				
				
				ClientDto dtoResult = mapper.readValue(result, ClientDto.class);
				
				assertTrue(dtoResult.isDeleted());
				
	}
	
	@Test
	public void deleteClientPasExistant_shouldReturnNull() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		//Prepare inputs
				ClientDto dto = new ClientDto(112, "client@mail.com", "John Doe", "8888888", TypeClient.ACHETEUR, false);
				
				//invoquer la methode
				String result = mvc.perform(get("/api/client/112/delete_client")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
						.andExpect(status()
								.isOk())
						.andReturn()
						.getResponse()
						.getContentAsString();
				
				
				assert(result.isEmpty());
				
	}
	
	@Test
	@Sql(statements = { "insert into client(id, deleted,email,full_name,telephone,type) values (112, false, 'client@mail.com', 'John Doe', '8888888', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = {"delete from client where id=112"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientExistantAvecValeursCorrectes_ShouldReturnStatus200EtRetourClientTrue() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		//Prepare inputs
				ClientDto dto = new ClientDto(112, "email@123456.fr", "fullName", "1122334455", TypeClient.VENDEUR, false);
				
				//invoquer la methode
				String result = mvc.perform(post("/api/client/update_client")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
						.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
				
				//on peut trouver une methode andDoPrint qui peut etre pratique
				
				assertEquals("true", result);
	}
	
	@Test
	@Sql(statements = { "insert into client(id, deleted,email,full_name,telephone,type) values (112, false, 'client@mail.com', 'John Doe', '8888888', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = {"delete from client where id=112"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientPasExistantAvecValeursCorrectes_ShouldReturnStatus200EtRetourClientTrue() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		//Prepare inputs
				ClientDto dto = new ClientDto(1120, "email@123456.fr", "fullName", "1122334455", TypeClient.VENDEUR, false);
				
				//invoquer la methode
				String result = mvc.perform(post("/api/client/update_client")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
						.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
				
				//on peut trouver une methode andDoPrint qui peut etre pratique
				
				assertEquals("false", result);
	}


}
