package com.fr.adaming.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
				
		try {
		return repository.save(client);
		} catch (DataIntegrityViolationException e) {
			return null;
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
		if(repository.existsById(client.getId())) {
		// Changer la valeur de l'attribut "deleted"
		client.setDeleted(true);
		return repository.save(client);
		}else {
			return null;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientService#findById()
	 */
	@Override
	public Client findById(long id) {
		try {
		return repository.findById(id).get();
		}catch (NoSuchElementException elementException) {
			return null;
		}
	}

}
