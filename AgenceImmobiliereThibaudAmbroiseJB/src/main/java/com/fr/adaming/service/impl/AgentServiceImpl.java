package com.fr.adaming.service.impl;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.repository.AgentRepository;
import com.fr.adaming.service.IAgentService;

/**
 * @author Jean-Baptiste
 *
 */
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
				
		try {
		return repository.save(agent);
		}catch (ConstraintViolationException e ) {
			return null;
		}catch (DataIntegrityViolationException e) {
			return null;
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
		if(repository.existsById(agent.getId())) {
			// Changer la valeur de l'attribut "deleted"
			agent.setDeleted(true);
			return repository.save(agent);
		} else {
			System.out.println("DEBUG l'agent à supprimer n'existe pas ");
			return null;
		}
		
	}
	
	@Override
	public Agent findById(long id) {
		return repository.findById(id).get();
	}

}
