package com.fr.adaming.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.entity.Bien;
import com.fr.adaming.entity.Client;
import com.fr.adaming.entity.enume.TypeClient;

/**
 * @author Thibaud
 *
 */
public class ClientConverter {
	
	public Client convertToClass (ClientDto dto) {
		String email = dto.getEmail();
		long id = dto.getId();
		String fullName = dto.getFullName();
		int telephone = dto.getTelephone();
		TypeClient type = dto.getType();
		Agent agent = dto.getAgent();
		List<Bien> biens = dto.getBiens();
		Client client = new Client(id, email, fullName, telephone, type, agent, biens);
		return client;
	}
	
	public ClientDto convertToDto (Client client) {
		String email = client.getEmail();
		long id = client.getId();
		String fullName = client.getFullName();
		int telephone = client.getTelephone();
		Enum<TypeClient> type = client.getType();
		Agent agent = client.getAgent();
		List<Bien> biens = client.getBiens();
		ClientDto dto = new ClientDto(id, email, fullName, telephone, (TypeClient) type, agent, biens);
		return dto;
	}
	
	public List<Client> convertListToClass(List<ClientDto> dtos){
		List<Client> clients = new ArrayList<Client>();
		for (ClientDto dto : dtos) {
			Client client = convertToClass(dto);
			clients.add(client);
		}
		return clients;
	}
	
	public List<ClientDto> convertListToDto(List<Client> clients){
		List<ClientDto> dtos = new ArrayList<ClientDto>();
		for (Client client : clients) {
			ClientDto dto = convertToDto(client);
			dtos.add(dto);
		}
		return dtos;
	}

}
