package com.fr.adaming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.entity.Client;
import com.fr.adaming.repository.AgentRepository;
import com.fr.adaming.repository.ClientRepository;
import com.fr.adaming.service.IAgentService;
import com.fr.adaming.service.IClientService;

@Service
public class AgentServiceImpl implements IAgentService{
	
	@Autowired
	private AgentRepository repository;
	

	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.entity.service.IClientService#save()
	 */
	@Override
	public Agent save(Agent agent) {
		
		// Vérifier si l'agent existe dans la BD (email)		
		Agent a = new Agent();
		a.setEmail(a.getEmail());
		
		
		if(repository.exists(Example.of(a))) {
			//L'agent existe dans la BD (FAIL)
			return null;
		}else {
			//L'agent n'existe pas (SUCCESS) : enregistrer l'agent dans la BD et retourner l'agent
			return repository.save(agent);
		}
		
	}


	@Override
	public List<Agent> findAll(){
		return repository.findAll();
	}
	

	@Override
	public boolean updateAgent(Agent agent) {
		
		// Chercher agent par id
		if(repository.existsById(agent.getId())) {
			repository.save(agent);
			return true;
		}else {
			System.out.println("DEBUG l'agent à modifier n'existe pas ");
			return false;
		}
	}

	@Override
	public Agent deleteAgent(Agent agent) {
		// Changer la valeur de l'attribut "deleted"
		agent.setDeleted(true);
		return repository.save(agent);
	}

}
