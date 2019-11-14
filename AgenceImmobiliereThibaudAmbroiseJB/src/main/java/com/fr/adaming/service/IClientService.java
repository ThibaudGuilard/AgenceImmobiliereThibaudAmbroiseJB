package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Client;

/**
 * @author Thibaud
 *
 */
public interface IClientService {
	
	

	/**Saves a given client in the database
	 * @param client - the given entity
	 * @return client if the given client didn't exist in the database - else, returns null
	 */
	public Client save(Client client);

	/**
	 * Finds all clients in database
	 * 
	 * @return List of clients - in case no clients has been inserted, it returns an
	 *         empty list
	 */
	public List<Client> findAll();

	/**
	 * Updates the values of a given client
	 * 
	 * @param client - the given entity
	 * @return true if the given client has been modified - else, returns false
	 */
	public boolean updateClient(Client client);

	/**
	 * Modifies attribute "deleted" from given client in database
	 * 
	 * @param client
	 * @return client - the given client
	 */
	public Client deleteClient(Client client);
	
	/** trouve un client par son id
	 * @param id
	 * @return le client correspondant à l'id
	 */
	public Client findById(long id);

}
