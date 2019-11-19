package com.fr.adaming.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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
	public void deleteValidBien_shouldReturnStatus200AndDtoNull() throws UnsupportedEncodingException, Exception {

		// Prepare inputs
		BienDto dto = new BienDto(444,55.12, false);

		// invoquer la methode
		String result = mvc
				.perform(post("/api/bien/delete").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		BienDto dtoResult = mapper.readValue(result, BienDto.class);

		assertNotNull(dtoResult);
		assertTrue(dtoResult.isDeleted());
		

		System.out.println("DEBUG CREATE VALID CLIENT : " + result);
	}
}
