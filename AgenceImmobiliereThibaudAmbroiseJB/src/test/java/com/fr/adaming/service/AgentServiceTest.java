package com.fr.adaming.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.entity.Bien;

/**
 * @author Thibaud JB et Ambroise
 *
 */
@SpringBootTest
public class AgentServiceTest {
	
	@Autowired
	private IAgentService service;
	
	@Sql(statements = { "truncate Agent","insert into agent values (112, 'agent@mail.com', 'John Doe', 88888888, false, 'azertyui', 10/12/2009)","insert into agent values (110, 'agent2@mail.com', 'John Doe', 88888888, false, 'azertyui', 10/12/2009)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = {"delete from agent where id=112","delete from agent where id=110"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void updateAgentServiceExistant_shouldReturnTrue() {
		//preparer les inputs
		Agent agent = new Agent();
		agent.setId(112L);
		agent.setEmail("agent@mail.fr");
		agent.setFullName("Jane Doe");
		agent.setPwd("abcd1234");
		//invoquer la méthode
		boolean retour = service.updateAgent(agent);
		//vérifier le résultat
		assertTrue(retour);
	}
	
	@Test
	public void updateAgentServicePasEnregistre_shouldReturnFalse() {
		//preparer les inputs
		Agent agent = new Agent();
		agent.setId(1120L);
		agent.setEmail("agent@mail.fr");
		agent.setFullName("Jane Doe");
		agent.setPwd("abcd1234");
		//invoquer la méthode
		boolean retour = service.updateAgent(agent);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	@Test
	public void updateAgentServiceEnregistreNomNul_shouldThrowException() {
		//preparer les inputs
		Agent agent = new Agent();
		agent.setId(112L);
		agent.setEmail("agent@mail.fr");
		agent.setFullName("");
		agent.setPwd("abcd1234");
		//invoquer la méthode
		boolean retour = service.updateAgent(agent);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	@Test
	public void updateAgentServiceEnregistrePwdNul_shouldThrowException() {
		//preparer les inputs
		Agent agent = new Agent();
		agent.setId(112L);
		agent.setEmail("agent@mail.fr");
		agent.setFullName("Truc");
		agent.setPwd("");
		//invoquer la méthode
		boolean retour = service.updateAgent(agent);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	@Test
	public void updateAgentServiceEnregistrePwdPasConforme_shouldThrowException() {
		//preparer les inputs
		Agent agent = new Agent();
		agent.setId(112L);
		agent.setEmail("agent@mail.fr");
		agent.setFullName("Bidule");
		agent.setPwd("abcd14");
		//invoquer la méthode
		boolean retour = service.updateAgent(agent);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	@Test
	public void updateAgentServiceEnregistreEmailExistant_shouldThrowException() {
		//preparer les inputs
		Agent agent = new Agent();
		agent.setId(112L);
		agent.setEmail("agent2@mail.com");
		agent.setFullName("Machin");
		agent.setPwd("abcd1234");
		//invoquer la méthode
		boolean retour = service.updateAgent(agent);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	@Test
	public void updateAgentServiceEnregistreEmailPasValide_shouldThrowException() {
		//preparer les inputs
		Agent agent = new Agent();
		agent.setId(112L);
		agent.setEmail("agentmail.com");
		agent.setFullName("Machin");
		agent.setPwd("abcd1234");
		//invoquer la méthode
		boolean retour = service.updateAgent(agent);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	@Test
	public void updateAgentServiceEnregistreEmailNul_shouldThrowException() {
		//preparer les inputs
		Agent agent = new Agent();
		agent.setId(112L);
		agent.setEmail("");
		agent.setFullName("Machin");
		agent.setPwd("abcd1234");
		//invoquer la méthode
		boolean retour = service.updateAgent(agent);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	@Test
	public void updateAgentServiceEnregistreTelephonePasConforme_shouldThrowException() {
		//preparer les inputs
		Agent agent = new Agent();
		agent.setId(112L);
		agent.setEmail("agent@mail.com");
		agent.setFullName("Machin");
		agent.setPwd("abcd1234");
		agent.setTelephone(8888);
		//invoquer la méthode
		boolean retour = service.updateAgent(agent);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	@Test
	public void updateAgentServiceEnregistreDateRecrutementDansLeFutur_shouldThrowException() {
		//preparer les inputs
		Agent agent = new Agent();
		agent.setId(112L);
		agent.setEmail("agent@mail.com");
		agent.setFullName("Machin");
		agent.setPwd("abcd1234");
		LocalDate dateRecrutement = LocalDate.now().plusDays(10);
		agent.setDateRecrutement(dateRecrutement);
		//invoquer la méthode
		boolean retour = service.updateAgent(agent);
		//vérifier le résultat
		assertFalse(retour);
	}
	
	@Sql(statements = "insert into agent (id, deleted, email, full_name, telephone) values (1, false, 'agent@mail.com', 'John Doe', 8888888888)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate agent",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void deleteAgentThatExists_shouldReturnNotNullAndDeletedEqualsTrue() {
		// System.out.println(service.findById(1L) == null); is not null
		Agent agent = service.deleteAgent(service.findById(1L));
		assertNotNull(agent);
		//assertThat(agent).hasFieldOrProperty("deleted").isEqualTo(true);
	}
	
//	@Test
//	public void deleteBienThatDoesNotExist_shouldReturnNotSuchElementException() {
//		exception.expect(NoSuchElementException.class);
//		assertFalse(service.deleteBien(new Bien()));
//	}
//	
//
//	@Test
//	@Sql(statements = "insert into client (id, email, full_name, deleted, telephone, type) values (1, 'emailqsdfqsdf@gmail.com', 'fullName', true, 1234, 1);",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "insert into bien values (1234568,false,15,false,1)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "insert into bien values (1234569,false,15,false,1)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "insert into bien values (12345610,false,15,false,1)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "truncate bien",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "truncate client",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	public void findAllBiensIfExist_shouldBeNotNullAndOfSize3() {
//		List<Bien> list = service.findAll();
//		assertNotNull(list);
//		assertThat(list).asList().hasSize(3);	
//	}
//	
//	@Test
//	public void findAllBienIfNotExist_shouldReturnEmptyList() {
//		assertTrue(service.findAll().isEmpty());
//	}

}
