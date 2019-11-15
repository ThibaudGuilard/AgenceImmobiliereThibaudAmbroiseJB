package com.fr.adaming.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.entity.Client;

/**
 * @author Thibaud Ambroise et JB
 *
 */
@SpringBootTest
public class ClientServiceTest {
	
	@Autowired
	private IClientService service;
	
	@Sql(statements = { "truncate Client","insert into client values (110, 'client@mail.fr', 'John Doe', 88888888, false, 'VENDEUR', 1000)","insert into client values (112, 'client@mail.com', 'John Doe', 88888888, false, 'VENDEUR', 1000)","insert into agent values (1000, 'agent2@mail.com', 'John Doe', 88888888, false, 'azertyui', 10/12/2009)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
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
	

	@Test
	@Sql(statements = "insert into client (id, deleted, email, full_name, telephone) values (1, false, 'client1@mail.fr', 'John Doe', 1122334455)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into client (id, deleted, email, full_name, telephone) values (2, false, 'client2@mail.fr', 'John Doe', 1122334455)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into client (id, deleted, email, full_name, telephone) values (3, false, 'client3@mail.fr', 'John Doe', 1122334455)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate client",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void findAllClientsIfExist_shouldBeNotNullAndOfSize3() {
		List<Client> list = service.findAll();
		assertNotNull(list);
		assertTrue(list.size() == 3);
	}
	
	@Test
	public void findAllClientIfNotExist_shouldReturnEmptyList() {
		assertTrue(service.findAll().isEmpty());
	}

}
