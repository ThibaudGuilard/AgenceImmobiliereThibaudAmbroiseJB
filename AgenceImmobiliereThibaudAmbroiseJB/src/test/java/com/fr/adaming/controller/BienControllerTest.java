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
	@Sql(statements = "delete from bien where prix = 55.12", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addValidBien_shouldReturnStatus200AndDtoNotNull() throws UnsupportedEncodingException, Exception {

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
	@Sql(statements = "delete from bien where id =111", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addNonValidBienwithSameId_shouldReturnError() throws UnsupportedEncodingException, Exception {

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
	public void addNonValidBienwithNegativePrix_shouldReturnError() throws UnsupportedEncodingException, Exception {

		// Prepare inputs
		BienDto dto = new BienDto(112, -55.12, false, false);
		
		// invoquer la methode

		String result = mvc
				.perform(post("/api/bien/add").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", result);
	}
	
	@Test
	public void addNonValidBienwithNullPrix_shouldReturnError() throws UnsupportedEncodingException, Exception {

		// Prepare inputs
		BienDto dto = new BienDto(113, 0, false, false);
		
		// invoquer la methode

		String result = mvc
				.perform(post("/api/bien/add").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", result);
	}
	@Test
	public void addNonValidBienwithAnyPrix_shouldReturnError() throws UnsupportedEncodingException, Exception {

		// Prepare inputs
		BienDto dto = new BienDto(false, false);
		
		// invoquer la methode

		String result = mvc
				.perform(post("/api/bien/add").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", result);
	}
	
	
	@Test
	@Sql(statements = "insert into bien (id, deleted, prix,vendu) values (222,false,55.12,false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien where id =222", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Sql(statements = "delete from bien where id =333", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Sql(statements = "insert into bien (id, deleted, prix,vendu) values (334,false,55.12,false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien where id =334", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateNBienWithoutPrix_shouldReturnError() throws UnsupportedEncodingException, Exception {

		// Prepare inputs
		BienDto dto = new BienDto(false, false);
		
		// invoquer la methode
		exception.expect(MismatchedInputException.class);

		String result = mvc
				.perform(post("/api/bien/update").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", result);
	}
	
	@Test
	@Sql(statements = "insert into bien (id, deleted, prix,vendu) values (335,false,55.12,false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien where id =335", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateNBienWithNegativePrix_shouldReturnError() throws UnsupportedEncodingException, Exception {

		// Prepare inputs
		BienDto dto = new BienDto(336, 0, false,false);
		
		// invoquer la methode
		exception.expect(MismatchedInputException.class);

		String result = mvc
				.perform(post("/api/bien/update").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", result);
	}
	
	@Test
	@Sql(statements = "insert into bien (id, deleted, prix,vendu) values (336,false,55.12,false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien where id =336", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateNBienWithNullPrix_shouldReturnError() throws UnsupportedEncodingException, Exception {

		// Prepare inputs
		BienDto dto = new BienDto(335, -55.12, false,false);
		
		// invoquer la methode
		exception.expect(MismatchedInputException.class);

		String result = mvc
				.perform(post("/api/bien/update").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", result);
	}
	

	@Test
	@Sql(statements = "insert into bien (id, deleted, prix,vendu) values (444,false,55.12,false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien where id =444", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Sql(statements = "delete from bien where id =555", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from bien where id =556", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void findAllValidBien_shouldReturnList() throws UnsupportedEncodingException, Exception {

		// invoquer la methode
		String result = mvc
				.perform(get("/api/bien/print").contentType(MediaType.APPLICATION_JSON))
						.andReturn().getResponse().getContentAsString();

		assertNotNull(result);
		assertEquals("[{\"id\":555,\"prix\":55.12,\"vendu\":false,\"deleted\":false,\"clients\":null},{\"id\":556,\"prix\":55.12,\"vendu\":false,\"deleted\":false,\"clients\":null}]", result);
	}
	@Test
	@Sql(statements = "insert into bien (id, deleted, prix,vendu) values (666,false,55.12,false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien where id =666", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void SearchById_shouldReturnBien() throws UnsupportedEncodingException, Exception {

		// Prepare inputs
		BienDto dto = new BienDto(666,55.12, false);

		// invoquer la methode
		String result = mvc
				.perform(get("/api/bien/666/searchbyid").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		BienDto dtoResult = mapper.readValue(result, BienDto.class);

		assertNotNull(dtoResult);
		
	}
}
