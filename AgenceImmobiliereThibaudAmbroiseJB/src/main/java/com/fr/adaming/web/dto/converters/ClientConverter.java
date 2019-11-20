package com.fr.adaming.web.dto.converters;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.entity.Bien;
import com.fr.adaming.entity.Client;
import com.fr.adaming.entity.enume.TypeClient;
import com.fr.adaming.web.dto.ClientDto;

/**
 * @author Thibaud
 *
 */
public class ClientConverter {
	
	public static Client convertToClass (ClientDto dto) {
		if (dto!= null) {
		String email = dto.getEmail();
		long id = dto.getId();
		String fullName = dto.getFullName();
		String telephone = dto.getTelephone();
		TypeClient type = dto.getType();
		Agent agent = dto.getAgent();
		List<Bien> biens = dto.getBiens();
		Client client = new Client(id, email, fullName, telephone, type, agent, biens);
		return client;
		}
		else {
			return null;
		}
	}
	
	public static ClientDto convertToDto (Client client) {
		if (client != null) {
		String email = client.getEmail();
		long id = client.getId();
		String fullName = client.getFullName();
		String telephone = client.getTelephone();
		Enum<TypeClient> type = client.getType();
		Agent agent = client.getAgent();
		List<Bien> biens = client.getBiens();
		ClientDto dto = new ClientDto(id, email, fullName, telephone, (TypeClient) type, agent, biens);
		return dto;
		} else {
			return null;
		}
	}
	
	
	public static List<Client> convertListToClass(List<ClientDto> dtos){
		List<Client> clients = new ArrayList<Client>();
		for (ClientDto dto : dtos) {
			Client client = convertToClass(dto);
			clients.add(client);
		}
		return clients;
	}
	
	public static List<ClientDto> convertListToDto(List<Client> clients){
		List<ClientDto> dtos = new ArrayList<ClientDto>();
		for (Client client : clients) {
			ClientDto dto = convertToDto(client);
			dtos.add(dto);
		}
		return dtos;
	}

}
