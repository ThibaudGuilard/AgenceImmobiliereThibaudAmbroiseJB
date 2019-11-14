package com.fr.adaming.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
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
	@Sql(statements = "delete from user where prix = 51234565.55 and vendu = true and deleted = false ", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createValidBien_shouldReturnUserWithIdNotNull() {
		//preparer les inputs
		Bien b= new Bien();
		
		b. setPrix(51234565.55);
		b.setVendu(true);
		b.setDeleted(false);
		//invocation de la methode
		Bien retournedBien = service.saveBien(b);
		// verification des résultats
		
		assertNotNull(retournedBien);
		assertNotNull(retournedBien.getId());
		assertNotNull(retournedBien.getPrix());
		assertNotNull(retournedBien.isVendu());
		assertNotNull(retournedBien.isDeleted());
		
		assertTrue(retournedBien != null);
		assertTrue(b.getPrix()==retournedBien.getPrix());
		assertTrue(b.isVendu()==retournedBien.isVendu());
		assertTrue(b.isDeleted()== retournedBien.isDeleted());
	}
	
}
