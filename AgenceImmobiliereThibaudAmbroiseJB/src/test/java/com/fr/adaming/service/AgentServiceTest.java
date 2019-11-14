package com.fr.adaming.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.entity.Bien;

@SpringBootTest
public class AgentServiceTest {
	
	@Autowired
	private IAgentService service;
	
	@Sql(statements = { "truncate Agent","insert into agent values (112, 'agent@mail.com', 'John Doe', 88888888, false, 'azertyui', 10/12/2009)","insert into agent values (112, 'agent@mail.com', 'John Doe', 88888888, false, 'azertyui', 10/12/2009)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien where id=112", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)

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
	public void updateBienServicePasEnregistre_shouldReturnFalse() {
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

}
