package com.fr.adaming.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.entity.Client;
import com.fr.adaming.entity.enume.TypeClient;

/**
 * @author Thibaud ( update) JB (delete) et Ambroise (add)
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientServiceTest {
	
	@Autowired
	private IClientService service;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Sql(statements = { "insert into client values (112, false, 'client@mail.com', 'John Doe', 8888888, 'VENDEUR',1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = {"delete from client where id=112","delete from client where id=110","delete from agent where id=1000"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void updateClientServiceExistant_shouldReturnTrue() {
		//preparer les inputs
		Client client = new Client();
		client.setId(112L);
		client.setEmail("client@mail.com");
		client.setFullName("Jane Doe");
		//invoquer la méthode
		boolean retour = service.updateClient(client);
		//vérifier le résultat
		assertTrue(retour);
	}
	
	@Test
	public void updateClientServicePasEnregistre_shouldReturnFalse() {
		//preparer les inputs
		Client client = new Client();
		client.setId(1120L);
		client.setEmail("client@mail.com");
		client.setFullName("Jane Doe");
		//invoquer la méthode
		boolean retour = service.updateClient(client);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	@Test
	public void updateClientServiceEnregistreNomNul_shouldThrowException() {
		//preparer les inputs
		Client client = new Client();
		client.setId(112L);
		client.setEmail("Client@mail.com");
		client.setFullName("");
		//invoquer la méthode
		boolean retour = service.updateClient(client);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	@Test
	public void updateClientServiceEnregistreTypeNul_shouldThrowException() {
		//preparer les inputs
		Client client = new Client();
		client.setId(112L);
		client.setEmail("client@mail.com");
		client.setFullName("Truc");
		client.setType(null);
		//invoquer la méthode
		boolean retour = service.updateClient(client);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	
	@Test
	public void updateClientServiceEnregistreEmailExistant_shouldThrowException() {
		//preparer les inputs
		Client client = new Client();
		client.setId(112L);
		client.setEmail("client2@mail.fr");
		client.setFullName("Machin");
		//invoquer la méthode
		boolean retour = service.updateClient(client);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	@Test
	public void updateClientServiceEnregistreEmailPasValide_shouldThrowException() {
		//preparer les inputs
		Client client = new Client();
		client.setId(112L);
		client.setEmail("clientmail.com");
		client.setFullName("Machin");
		//invoquer la méthode
		boolean retour = service.updateClient(client);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	@Test
	public void updateClientServiceEnregistreEmailNul_shouldThrowException() {
		//preparer les inputs
		Client client = new Client();
		client.setId(112L);
		client.setEmail("");
		client.setFullName("Machin");
		//invoquer la méthode
		boolean retour = service.updateClient(client);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	@Test
	public void updateClientServiceEnregistreTelephonePasConforme_shouldThrowException() {
		//preparer les inputs
		Client client = new Client();
		client.setId(112L);
		client.setEmail("client@mail.fr");
		client.setFullName("Machin");
		client.setTelephone(8888);
		//invoquer la méthode
		boolean retour = service.updateClient(client);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	//ce test ne marche que si j'insère un client qui n'a pas de type (il faut changer la colonne "type" dans la BD et la rendre "nullable"
	@Sql(statements = "insert into client (id, deleted, email, full_name, telephone) values (1, false, 'client@mail.fr', 'John Doe', 1122334455)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate client",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void deleteClientThatExists_shouldReturnNotNullAndDeletedEqualsTrue() {
		Client client = service.deleteClient(service.findById(1L));
		assertNotNull(client);
		assertTrue(client.isDeleted());
	}
	
	@Test
	public void deleteClientThatDoesNotExist_shouldReturnNullAgent() {
		assertNull(service.deleteClient(new Client()));
	}
	

	@org.junit.Test
	@Sql(statements = "truncate client",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = " insert into client (id,deleted, email, full_name,type, telephone) values (787,false, 'c1@mail.fr', 'John Doe',0, 1122334455)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = " insert into client (id,deleted, email, full_name,type, telephone) values (888,false, 'c2@mail.fr', 'John Doe',0, 1122334455)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = " insert into client (id,deleted, email, full_name,type, telephone) values (889,false, 'c3@mail.fr', 'John Doe',0, 1122334455)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate client",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void findAllClientsIfExist_shouldBeNotNullAndOfSize3() {
		List<Client> list = service.findAll();
		assertNotNull(list);
		assertTrue(list.size() == 3);
	}
	
	@org.junit.Test
	public void findAllClientIfNotExist_shouldReturnEmptyList() {
		assertTrue(service.findAll().isEmpty());
	}

	@Test
	@Sql(statements = "delete from client where email like 'aa@a.fr' and full_name like'Jacquies'",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addValideClient_shouldReturnGoodClient() {
		Client c = new Client();
		c.setDeleted(false);
		c.setEmail("aa@a.fr");
		c.setFullName("Jacquies");
		c.setTelephone(1234567890);
		c.setType(TypeClient.ACHETEUR);
		
		Client retourned = service.save(c);
		
		assertNotNull(retourned);
	}

	@Test
	@Sql(statements = "delete from client where id = 888", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = " insert into client (id,deleted, email, full_name,type, telephone) values (888,false, 'c3@mail.fr', 'John Doe',0, 1122334455)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void addNonValidClientAlreadyExistById_shouldReturnFail() {
		Client c = new Client();
		c.setId(888);
		c.setDeleted(false);
		c.setEmail("efjze@zefj.fr");
		c.setFullName("pierro");
		c.setType(TypeClient.ACHETEUR);
		c.setTelephone(1122334455);
		
		exception.expect(AssertionError.class);
		Client retourned =  service.save(c);
		
		assertNotNull(retourned);
	}
	
	@org.junit.Test
	@Sql(statements = "delete from client where id = 55", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = " insert into client (id,deleted, email, full_name,type, telephone) values (55,false, 'c3@mail.fr', 'John Doe',0, 1122334455)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void addNonValidClientSameEmail_shouldReturnError() {
		exception.expect(DataIntegrityViolationException.class);
		
		Client c = new Client();

		c.setDeleted(false);
		c.setEmail("c3@mail.fr");
		c.setFullName("pierro");
		c.setType(TypeClient.ACHETEUR);
		c.setTelephone(1122334455);
		
		
		Client retourned =  service.save(c);
		
		assertNotNull(retourned);
		
		
	}
	

	
	
}
