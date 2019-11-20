// Ambroise RENE

package com.fr.adaming.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.entity.Client;
import com.fr.adaming.web.dto.ClientDto;

/**
 * @author Thibaud
 *
 */
@RequestMapping(path = "api/client") @CrossOrigin
public interface IClientController {
	
	/** Créé un Client dans la base de donnée à partir d'un Client Dto
	 * @param dto
	 * @return un message si l'opération est effectuée
	 */
	@PostMapping(path = "/create_client")
	public Client create(@Valid @RequestBody ClientDto dto);
	
	/** Met à jour l'attribut "deleted" d'un client à partir d'un ClientDto
	 * @param dto
	 * @return un message si l'opération est effectuée
	 */
	@GetMapping(path = "{id}/delete_client")
	public Client deleteClient(@PathVariable long id);
	
	/** met à jour un client à partir d'un ClientDto
	 * @param dto
	 * @return un message si l'opération est effectuée
	 */
	@PostMapping(path = "/update_client")
	public boolean updateClient(@Valid @RequestBody ClientDto dto);
	
	/**
	 * @return la liste de tout les clients
	 */
	@GetMapping(path = "/print")
	public List<Client> printClient();

}
