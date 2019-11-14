package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.ClientDao;
import com.fr.adaming.entity.Client;

@Service
public class ClientService {
	
	@Autowired
	private ClientDao dao;
	
	/**Saves a given client in the database
	 * @param client - the given entity
	 * @return client if the given client didn't exist in the database - else, returns null
	 */
	public Client save(Client client) {
		
		// Vérifier si le client existe dans la BD (email)
//		Client c = dao.findByEmail(client.getEmail());
		
		Client c = new Client();
		c.setEmail(client.getEmail());
		
		
		if(dao.exists(Example.of(c))) {
			//Le client existe dans la BD (FAIL)
			return null;
		}else {
			//Le client n'existe pas (SUCCESS) : enregistrer le client dans la BD et retourner le client
			return dao.save(client);
		}
		
	}
	
	/** Finds all clients in database
	 * @return List of clients - in case no clients has been inserted, it returns an empty list
	 */
	public List<Client> findAll(){
		return dao.findAll();
	}
	
	/**Updates the values of a given client
	 * @param client - the given entity
	 * @return true if the given client has been modified - else, returns false
	 */
	public boolean updateClient(Client client) {
		
		// Chercher client par id
		if(dao.existsById(client.getId())) {
			dao.save(client);
			return true;
		}else {
			System.out.println("DEBUG Le client à modifier n'existe pas ");
			return false;
		}
	}
	
	/**Modifies attribute "deleted" from given client in database
	 * @param client
	 * @return client - the given client
	 */
	public Client deleteClient(Client client) {
		return dao.delete(client);
	}

}
