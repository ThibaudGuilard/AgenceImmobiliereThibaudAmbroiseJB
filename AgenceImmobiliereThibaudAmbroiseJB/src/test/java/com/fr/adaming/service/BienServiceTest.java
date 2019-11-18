package com.fr.adaming.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Bien;

/**
 * @author Thibaud ( update) JB (delete) et Ambroise (add)
 *
 */
@SpringBootTest
public class BienServiceTest {

	@Autowired
	private IBienService service;

	@Rule
	public ExpectedException exception = ExpectedException.none();


	@Test
	@Sql(statements = "delete from bien where prix = 51234565.55 and vendu = true and deleted = false ", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addValidBien_shouldReturnUserWithIdNotNull() {
		// preparer les inputs
		Bien b = new Bien();

		b.setPrix(51234565.55);
		b.setVendu(true);
		b.setDeleted(false);
		// invocation de la methode
		Bien retournedBien = service.saveBien(b);
		// verification des résultats

		assertNotNull(retournedBien);
		assertNotNull(retournedBien.getId());
		assertNotNull(retournedBien.getPrix());
		assertNotNull(retournedBien.isVendu());
		assertNotNull(retournedBien.isDeleted());

		assertTrue(retournedBien != null);
		assertTrue(b.getPrix() == retournedBien.getPrix());
		assertTrue(b.isVendu() == retournedBien.isVendu());
		assertTrue(b.isDeleted() == retournedBien.isDeleted());
	}
	
	@Test
	@Sql(statements = "delete from bien where id =1234568910 ", executionPhase = ExecutionPhase.AFTER_TEST_METHOD )
	@Sql(statements = "insert into bien (id, deleted, prix,vendu)values (1234568910,false,15.5,false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void addBienAlreadyExist_shouldNotReturn() {
		Bien b = new Bien();
		
		b.setId(1234568910L);
		b.setPrix(15.5);
		b.setVendu(false);
		b.setDeleted(false);
		// invocation de la methode
		exception.expect(AssertionError.class);
		Bien retourned = service.saveBien(b);
		// verification des résultats
		
		assertNull(retourned);
	}


	@Sql(statements = "insert into bien (id, deleted, prix, vendu) values (1234567,false,15,false)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void deleteBienThatExists_shouldReturnTrue() {
		Bien bien = service.FindParId(1234567L);
		assertTrue(service.deleteBien(bien));
	}

	@Test
	public void deleteBienThatDoesNotExist_shouldReturnNotSuchElementException() {
		exception.expect(NoSuchElementException.class);
		assertFalse(service.deleteBien(new Bien()));
	}
	

	@Test
	@Sql(statements = "insert into client (id, email, full_name, deleted, telephone, type) values (1, 'emailqsdfqsdf@gmail.com', 'fullName', true, 1234, 1);",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into bien values (1234568,false,15,false,1)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into bien values (1234569,false,15,false,1)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into bien values (12345610,false,15,false,1)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from client",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void findAllBiensIfExist_shouldBeNotNullAndOfSize3() {
		List<Bien> list = service.findAll();
		assertNotNull(list);
		assertThat(list).asList().hasSize(3);	
	}
	
	@Test
	public void findAllBienIfNotExist_shouldReturnEmptyList() {
		assertTrue(service.findAll().isEmpty());
	}



	@Sql(statements = { "delete from Bien","insert into bien values (112, 200000.55, false, false)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien where id=112", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void updateBienServiceExistant_shouldReturnTrue() {
		// preparer les inputs
		Bien bien = new Bien();
		bien.setId(112L);
		bien.setPrix(200000.55);
		bien.setVendu(true);
		bien.setDeleted(false);
		// invoquer la méthode
		boolean retour = service.updateBien(bien);
		// vérifier le résultat
		assertTrue(retour);
	}

	@Test
	public void updateBienServicePasEnregistre_shouldReturnFalse() {
		// preparer les inputs
		Bien bien = new Bien();
		bien.setId(1120L);
		bien.setPrix(200000.55);
		bien.setVendu(true);
		bien.setDeleted(false);
		// invoquer la méthode
		boolean retour = service.updateBien(bien);
		// vérifier le résultat
		assertFalse(retour);
	}

	@SuppressWarnings("null")
	@Test
	public void updateBienServiceEnregistrePrixNul_shouldThrowException() {
		// preparer les inputs
		Bien bien = new Bien();
		bien.setId(1120L);
		bien.setPrix((Double) null);
		bien.setVendu(true);
		bien.setDeleted(false);
		// invoquer la méthode
		boolean retour = service.updateBien(bien);
		// vérifier le résultat
		assertFalse(retour);
	}

}
