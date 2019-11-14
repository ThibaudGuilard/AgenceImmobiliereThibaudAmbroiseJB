package com.fr.adaming.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Bien;

/**
 * @author Thibaud, JB et Ambroise
 *
 */
@SpringBootTest
public class BienServiceTest {

	@Autowired
	private IBienService service;
	
	@Sql(statements = { "truncate Bien","insert into bien values (112, 200000, false, false)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien where id=112", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)

	@Test
	public void updateBienServiceExistant_shouldReturnTrue() {
		//preparer les inputs
		Bien bien = new Bien();
		bien.setId(112L);
		bien.setPrix(200000);
		bien.setVendu(true);
		bien.setDeleted(false);
		//invoquer la méthode
		boolean retour = service.updateBien(bien);
		//vérifier le résultat
		assertTrue(retour);
	}
	
	@Test
	public void updateBienServicePasEnregistre_shouldReturnFalse() {
		//preparer les inputs
		Bien bien = new Bien();
		bien.setId(1120L);
		bien.setPrix(200000);
		bien.setVendu(true);
		bien.setDeleted(false);
		//invoquer la méthode
		boolean retour = service.updateBien(bien);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	@SuppressWarnings("null")
	@Test
	public void updateBienServiceEnregistrePrixNul_shouldThrowException() {
		//preparer les inputs
		Bien bien = new Bien();
		bien.setId(1120L);
		bien.setPrix((Double) null);
		bien.setVendu(true);
		bien.setDeleted(false);
		//invoquer la méthode
		boolean retour = service.updateBien(bien);
		//vérifier le résultat
		assertFalse(retour);
	}
}
