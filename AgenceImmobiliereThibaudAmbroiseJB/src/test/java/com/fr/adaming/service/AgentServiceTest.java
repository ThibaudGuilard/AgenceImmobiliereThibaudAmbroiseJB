package com.fr.adaming.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Agent;

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

}
