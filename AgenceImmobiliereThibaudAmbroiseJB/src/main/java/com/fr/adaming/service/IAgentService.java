package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.entity.Client;

public interface IAgentService {
	
	/**Saves a given agent in the database
	 * @param agent - the given entity
	 * @return agent if the given agent didn't exist in the database - else, returns null
	 */
	public Agent save(Agent agent);

	/**
	 * Finds all agents in database
	 * 
	 * @return List of agents - in case no agents has been inserted, it returns an
	 *         empty list
	 */
	public List<Agent> findAll();

	/**
	 * Updates the values of a given agent
	 * 
	 * @param agent - the given entity
	 * @return true if the given agent has been modified - else, returns false
	 */
	public boolean updateAgent(Agent agent);

	/**
	 * Modifies attribute "deleted" from given agent in database
	 * 
	 * @param agent
	 * @return agent - the given agent
	 */
	public Agent deleteAgent(Agent agent);

	
	/**
	 * Finds an agent by its id
	 * @param id
	 * @return the agent
	 */
	public Agent findById(long id);


}
