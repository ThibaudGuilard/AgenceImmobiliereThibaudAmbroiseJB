package com.fr.adaming.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Client;
import com.fr.adaming.service.IClientService;
import com.fr.adaming.web.controller.IClientController;
import com.fr.adaming.web.dto.ClientDto;
import com.fr.adaming.web.dto.converters.ClientConverter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Thibaud
 *
 */
@RestController
@Api(description = "API pour les opérations sur les clients")
public class ClientControllerImpl implements IClientController {

	@Autowired
	private IClientService service;
	
	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientController#createClient()
	 */
	@Override
	public Client create(ClientDto dto) {
		Client client = ClientConverter.convertToClass(dto);
		return service.save(client);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientController#deleteClient()
	 */
	@Override
	public Client deleteClient(long id) {
		Client client = service.findById(id);
		if (client!=null) {
		Client clientRetour = service.deleteClient(client);
		return clientRetour;
		} else {
			return null;
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientController#updateClient()
	 */ @Override
	public boolean updateClient(ClientDto dto) {
		Client client = ClientConverter.convertToClass(dto);
		boolean clientRetour = service.updateClient(client);
		return clientRetour;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientController#printClient()
	 */
	@Override 
	public List<Client> printClient() {
		return service.findAll();
	}

	
}
