// Ambroise RENE

package com.fr.adaming.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fr.adaming.entity.Client;
import com.fr.adaming.web.dto.ClientDto;

/**
 * @author Thibaud
 *
 */
public interface IClientController {
	
	/** Créé un Client dans la base de donnée à partir d'un Client Dto
	 * @param dto
	 * @return un message si l'opération est effectuée
	 */
	public Client create(@RequestBody ClientDto dto);
	
	/** Met à jour l'attribut "deleted" d'un client à partir d'un ClientDto
	 * @param dto
	 * @return un message si l'opération est effectuée
	 */
	public String deleteClient(@PathVariable long id);
	
	/** met à jour un client à partir d'un ClientDto
	 * @param dto
	 * @return un message si l'opération est effectuée
	 */
	public String updateClient(@PathVariable ClientDto dto);
	
	/**
	 * @return la liste de tout les clients
	 */
	public List<Client> printClient();

}
