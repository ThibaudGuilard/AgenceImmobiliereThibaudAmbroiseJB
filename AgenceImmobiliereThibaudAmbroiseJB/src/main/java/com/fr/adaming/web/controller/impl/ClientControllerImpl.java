package com.fr.adaming.web.controller.impl;

import java.util.List;

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
	public String create(@RequestBody ClientDto dto) {
		Client client = ClientConverter.convertToClass(dto);
		Client clientRetour = service.save(client);
		if (clientRetour != null) {
		service.save(client);
		return "client created";
		} else {
			return "client already exist";
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientController#deleteClient()
	 */
	@DeleteMapping(path = "{id}/delete_client") @Override
	public String deleteClient(@PathVariable long id) {
		Client client = service.findById(id);
		Client clientRetour = service.deleteClient(client);
		if (clientRetour != null) {
		return "Client deleted";
		}else {
			return "You're trying to delete an inexistant Client";
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.service.IClientController#updateClient()
	 */
	@PostMapping(path = "{Client}/update_client") @Override
	public String updateClient(@PathVariable ClientDto dto) {
		Client client = ClientConverter.convertToClass(dto);
		boolean clientRetour = service.updateClient(client);
		if (clientRetour == true) {
		service.updateClient(client);
		return "Client updated";
		}else {
			return "This Client does'nt exist";
		}
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
