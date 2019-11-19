package com.fr.adaming.web.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Client;
import com.fr.adaming.service.IClientService;
import com.fr.adaming.web.controller.IClientController;
import com.fr.adaming.web.dto.ClientDto;
import com.fr.adaming.web.dto.converters.ClientConverter;

/**
 * @author Thibaud
 *
 */
@RestController
@RequestMapping(path = "api/client")
public class ClientControllerImpl implements IClientController {

	@Autowired
	private IClientService service;
	
	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientController#createClient()
	 */
	@PostMapping(path = "/create_client") @Override
	public Client create(@Valid @RequestBody ClientDto dto) {
		Client client = ClientConverter.convertToClass(dto);
		return service.save(client);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientController#deleteClient()
	 */
	@DeleteMapping(path = "{id}/delete_client") @Override
	public Client deleteClient(@PathVariable long id) {
		Client client = service.findById(id);
		Client clientRetour = service.deleteClient(client);
		return clientRetour;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientController#updateClient()
	 */
	@PostMapping(path = "{Client}/update_client") @Override
	public boolean updateClient(@PathVariable ClientDto dto) {
		Client client = ClientConverter.convertToClass(dto);
		boolean clientRetour = service.updateClient(client);
		return clientRetour;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientController#printClient()
	 */
	@Override @GetMapping(path = "/print")
	public List<Client> printClient() {
		return service.findAll();
	}

	
}
