package com.fr.adaming.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Agent;

/**
 * @author Thibaud ( update) JB (delete) et Ambroise (add)
 *
 */
@SpringBootTest
public class AgentServiceTest {
	
	@Autowired
	private IAgentService service;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	
	@Test
	@Sql(statements = "delete from agent where full_name like jj and pwd like azertyuiop and email like a@a.fr", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addValideAgent_shouldReturnClientWithIdNotNull() {
		Agent a = new Agent();
		
		a.setFullName("jj");
		a.setPwd("azertyuiop");
		a.setTelephone(1234567890);
		a.setEmail("a@a.fr");
		a.setDeleted(false);
		a.setDateRecrutement(LocalDate.parse("2017-05-15"));
		
		Agent retourned = service.save(a);

		assertNotNull(retourned);
	}
	
	@Test
	@Sql(statements = "insert into agent values (44, 0, 'a@mail.com', 'JPP', 1234567890, '2007-05-15', 'azertyuiop')",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from agent where id = 44" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addNonValideAgentWithSameEmail_shouldReturnError() {
		Agent a = new Agent();
		
		a.setFullName("JPP");
		a.setTelephone(1234567890);
		a.setEmail("a@mail.com");
		a.setDeleted(false);
		
		Agent retourned = service.save(a);

		assertNull(retourned);	
	}
	
	@Test
	@Sql(statements = "insert into agent values (555, 0, 'a@aaa.com', 'JPP', 1234567890, '2007-05-15', 'azertyuiop')",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from agent where id = 555" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addNotValidAgentWithSameId_shouldReturnError() {
		Agent a = new Agent();
		
		a.setId(555);
		a.setFullName("JPP");
		a.setEmail("a@a.fr");
		a.setDeleted(true);
		a.setTelephone(1234567809);
		a.setPwd("azertyuiop");
		
		
		exception.expect(AssertionError.class);
		Agent retourned = service.save(a);
		
		assertNotNull(retourned);	
	
	}
	
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
	
	@Sql(statements = "insert into agent (id, deleted, email, full_name, telephone) values (1, false, 'agent@mail.com', 'John Doe',1122334455)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate agent",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void deleteAgentThatExists_shouldReturnNotNullAndDeletedEqualsTrue() {
		Agent agent = service.deleteAgent(service.findById(1L));
		assertNotNull(agent);
		assertTrue(agent.isDeleted());
	}
	
	@Test
	public void deleteAgentThatDoesNotExist_shouldReturnNullAgent() {
		assertNull(service.deleteAgent(new Agent()));
	}
	

	@Test
	@Sql(statements = "insert into agent (id, deleted, email, full_name, telephone) values (1, false, 'agent1@mail.com', 'John Doe',1122334455)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into agent (id, deleted, email, full_name, telephone) values (2, false, 'agent2@mail.com', 'John Doe',1122334455)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into agent (id, deleted, email, full_name, telephone) values (3, false, 'agent3@mail.com', 'John Doe',1122334455)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate agent",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void findAllAgentsIfExist_shouldBeNotNullAndOfSize3() {
		List<Agent> list = service.findAll();
		assertNotNull(list);
		assertTrue(list.size() == 3);
	}
	
	@Test
	public void findAllAgentIfNotExist_shouldReturnEmptyList() {
		assertTrue(service.findAll().isEmpty());
	}

}
