package com.fr.adaming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Client;
import com.fr.adaming.repository.ClientRepository;
import com.fr.adaming.service.IClientService;

/**
 * @author Thibaud
 *
 */
@Service
public class ClientServiceImpl implements IClientService{
	
	@Autowired
	private ClientRepository repository;
	

	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientService#save()
	 */
	@Override
	public Client save(Client client) {
		
		// Vérifier si le client existe dans la BD (email)
//		Client c = dao.findByEmail(client.getEmail());
		
		Client c = new Client();
		c.setEmail(client.getEmail());
		
		
		if(repository.exists(Example.of(c))) {
			//Le client existe dans la BD (FAIL)
			return null;
		}else {
			//Le client n'existe pas (SUCCESS) : enregistrer le client dans la BD et retourner le client
			return repository.save(client);
		}
		
	}


	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientService#findAll()
	 */
	@Override
	public List<Client> findAll(){
		return repository.findAll();
	}
	

	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientService#updateClient()
	 */
	@Override
	public boolean updateClient(Client client) {
		
		// Chercher client par id
		if(repository.existsById(client.getId())) {
			repository.save(client);
			return true;
		}else {
			System.out.println("DEBUG Le client à modifier n'existe pas ");
			return false;
		}
	}
	

	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientService#deleteClient()
	 */
	@Override
	public Client deleteClient(Client client) {
		// Changer la valeur de l'attribut "deleted"
		client.setDeleted(true);
		return repository.save(client);
	}

}
