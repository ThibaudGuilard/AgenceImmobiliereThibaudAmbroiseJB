package com.fr.adaming.service;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import org.springframework.test.context.jdbc.Sql.ExecutionPhase;


import com.fr.adaming.entity.Bien;

@SpringBootTest
public class BienServiceTest {

	@Autowired
	private IBienService service;
	

	@Test
//	@Sql(statements = "insert into bien values (1234567,false,15,false,1)")
	private void deleteBienThatExists_shouldReturnTrue() {
		Bien bien = service.FindParId(1234567L);
		assertTrue(service.deleteBien(bien));		
	}
	
//	@Rule
//	ExpectedException exception = ExpectedException.none();
//	
//	@Test
//	private void deleteBienThatDoesNotExist_shouldReturnFalse() {
//		Bien bien = service.FindParId(5425698754212L);
//		exception.expect(type);		
//	}
	

	@Sql(statements = { "truncate Bien","insert into bien values (112, 200000, false, false)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien where id=112", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)

	
	public void updateBienServiceExistant_shouldReturnTrue() {
		//preparer les inputs
		Bien bien = new Bien();
		bien.setId(112L);
		bien.setPrix(200000);
		bien.setVendu(true);
		bien.setDeleted(false);
		//invoquer la méthode
		
		//récupérer le résultat
	}

}
