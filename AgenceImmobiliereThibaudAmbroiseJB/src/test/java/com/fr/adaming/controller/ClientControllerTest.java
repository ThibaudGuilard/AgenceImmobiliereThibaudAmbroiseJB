package com.fr.adaming.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.AgenceImmobiliereThibaudAmbroiseJbApplicationTests;
import com.fr.adaming.entity.enume.TypeClient;
import com.fr.adaming.web.dto.ClientDto;

@SpringBootTest
@RequestMapping(path = "api/client")
public class ClientControllerTest extends AgenceImmobiliereThibaudAmbroiseJbApplicationTests{

	
	// La methode fonctionne pas mais l'esprit est la je crois
	@Test
	public void createValidClient_shouldReturnStatus200AndDtoNotNull() throws UnsupportedEncodingException, Exception {
		
		//Prepare inputs
		ClientDto dto = new ClientDto(" email@1.fr", "fullName", 1234567890, TypeClient.ACHETEUR );
		
		//invoquer la methode
		String result = mvc.perform(post("/create_client")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		//on peut trouver une methode andDoPrint qui peut etre pratique
		
		ClientDto dtoResult = mapper.readValue(result, ClientDto.class);
		
		assertNotNull(dtoResult);
		assertEquals("", dto.getEmail());
		
		System.out.println("DEBUG CREATE VALID CLIENT : " + result );
		
	}

}
