package com.fr.adaming.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fr.adaming.AgenceImmobiliereThibaudAmbroiseJbApplicationTests;
import com.fr.adaming.web.dto.BienDto;
import com.fr.adaming.web.dto.BienDtoAdd;

@SpringBootTest
public class BienControllerTest extends AgenceImmobiliereThibaudAmbroiseJbApplicationTests {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void createValidBien_shouldReturnStatus200AndDtoNotNull() throws UnsupportedEncodingException, Exception {

		// Prepare inputs
		BienDtoAdd dto = new BienDtoAdd(55.12, false, false);

		// invoquer la methode
		String result = mvc
				.perform(post("/api/bien/add").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// on peut trouver une methode andDoPrint qui peut etre pratique

		BienDtoAdd dtoResult = mapper.readValue(result, BienDtoAdd.class);

		assertNotNull(dtoResult);
		assertEquals(false, dto.isDeleted());
		assertEquals(false, dto.isVendu());
		assertEquals(55.12, dto.getPrix(), 0.001);
	}

	@Test
	@Sql(statements = "insert into bien (id, deleted, prix,vendu) values (111,false,55.12,false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createNonValidBienwithSameId_shouldReturnError() throws UnsupportedEncodingException, Exception {

		// Prepare inputs
		BienDto dto = new BienDto(111, 55.12, false, false);
		
		// invoquer la methode

		String result = mvc
				.perform(post("/api/bien/add").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("", result);

	}
	
	@Test
	@Sql(statements = "insert into bien (id, deleted, prix,vendu) values (222,false,55.12,false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void updateValidBien_shouldReturnStatus200AndDtoNotNull() throws UnsupportedEncodingException, Exception {

		// Prepare inputs
		BienDto dto = new BienDto(222,500, false, false);

		// invoquer la methode
		String result = mvc
				.perform(post("/api/bien/update").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		BienDto dtoResult = mapper.readValue(result, BienDto.class);

		assertNotNull(dtoResult);
		assertEquals(500, dto.getPrix(), 0.001);
	}
	
	@Test
	@Sql(statements = "insert into bien (id, deleted, prix,vendu) values (333,false,55.12,false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void updateNBienWithoutId_shouldReturnError() throws UnsupportedEncodingException, Exception {

		// Prepare inputs
		BienDto dto = new BienDto(500, false, false);
		
		// invoquer la methode
		exception.expect(MismatchedInputException.class);

		String result = mvc
				.perform(post("/api/bien/update").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


		assertEquals("", result);

	}
	

	@Test
	@Sql(statements = "insert into bien (id, deleted, prix,vendu) values (444,false,55.12,false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void deleteValidBien_shouldReturnDeletedTrue() throws UnsupportedEncodingException, Exception {

		// Prepare inputs
		BienDto dto = new BienDto(444,55.12, false);

		// invoquer la methode
		String result = mvc
				.perform(post("/api/bien/444/delete").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		BienDto dtoResult = mapper.readValue(result, BienDto.class);

		assertTrue(dtoResult.isDeleted());
	}
	
	@Test
	@Sql(statements = "insert into bien (id, deleted, prix,vendu) values (555,false,55.12,false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into bien (id, deleted, prix,vendu) values (556,false,55.12,false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void findAllValidBien_shouldReturnList() throws UnsupportedEncodingException, Exception {

		// invoquer la methode
		String result = mvc
				.perform(get("/api/bien/print").contentType(MediaType.APPLICATION_JSON))
						.andReturn().getResponse().getContentAsString();

		assertNotNull(result);
		assertEquals("[{\"id\":111,\"prix\":55.12,\"vendu\":false,\"deleted\":false,\"clients\":null},{\"id\":222,\"prix\":500.0,\"vendu\":false,\"deleted\":false,\"clients\":null},{\"id\":333,\"prix\":55.12,\"vendu\":false,\"deleted\":false,\"clients\":null},{\"id\":334,\"prix\":55.12,\"vendu\":false,\"deleted\":false,\"clients\":null},{\"id\":444,\"prix\":55.12,\"vendu\":false,\"deleted\":true,\"clients\":null},{\"id\":555,\"prix\":55.12,\"vendu\":false,\"deleted\":false,\"clients\":null},{\"id\":556,\"prix\":55.12,\"vendu\":false,\"deleted\":false,\"clients\":null},{\"id\":666,\"prix\":55.12,\"vendu\":false,\"deleted\":false,\"clients\":null}]", result);
	}
	@Test
	@Sql(statements = "insert into bien (id, deleted, prix,vendu) values (666,false,55.12,false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void SearchById_shouldReturnBien() throws UnsupportedEncodingException, Exception {

		// Prepare inputs
		BienDto dto = new BienDto(444,55.12, false);

		// invoquer la methode
		String result = mvc
				.perform(get("/api/bien/444/searchbyid").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		BienDto dtoResult = mapper.readValue(result, BienDto.class);

		assertNotNull(dtoResult);
		
	}
}
