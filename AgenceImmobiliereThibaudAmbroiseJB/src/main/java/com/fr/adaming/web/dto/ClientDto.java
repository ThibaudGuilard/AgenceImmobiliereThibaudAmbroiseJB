package com.fr.adaming.web.dto;

import java.util.List;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.entity.Bien;
import com.fr.adaming.entity.enume.TypeClient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Thibaud
 *
 */
@Getter @Setter @NoArgsConstructor
public class ClientDto {
	
	private long id;
	private String email;
	private String fullName;
	private int telephone;
	private TypeClient type;
	private Agent agent;
	private List<Bien> biens;
	
	public ClientDto(long id, String email, String fullName, int telephone, TypeClient type, Agent agent,
			List<Bien> biens) {
		this.id = id;
		this.email = email;
		this.fullName = fullName;
		this.telephone = telephone;
		this.type = type;
		this.agent = agent;
		this.biens = biens;
	}

}
