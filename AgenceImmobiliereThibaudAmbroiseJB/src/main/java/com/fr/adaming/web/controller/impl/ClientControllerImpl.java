package com.fr.adaming.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Client;
import com.fr.adaming.service.IClientService;
import com.fr.adaming.web.controller.IClientController;
import com.fr.adaming.web.dto.ClientConverter;
import com.fr.adaming.web.dto.ClientDto;

/**
 * @author Thibaud
 *
 */
@RestController
public class ClientControllerImpl implements IClientController {

	@Autowired
	private IClientService service;
	@Autowired
	ClientConverter converter;
	
	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientController#createClient()
	 */
	@GetMapping(path = "/create_client") @Override
	public String create(@RequestBody ClientDto dto) {
		Client client = converter.convertToClass(dto);
		service.save(client);
		return "client created";
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientController#deleteClient()
	 */
	@DeleteMapping(path = "{id}/delete_client") @Override
	public String deleteClient(@PathVariable long id) {
		Client client = service.findById(id);
		service.deleteClient(client);
		return "Client deleted";
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientController#updateClient()
	 */
	@GetMapping(path = "{Client}/update_client") @Override
	public String updateClient(@PathVariable ClientDto dto) {
		Client client = converter.convertToClass(dto);
		service.updateClient(client);
		return "Client updated";
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
