package com.fr.adaming.web.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fr.adaming.entity.Client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class AgentDto {
	

	private long id;
	
	@NotNull  
	@Email
	private String email;
	
	private String fullName;
	
	private String telephone;
	
	private boolean deleted;
	
	private String pwd;
	
	private LocalDate dateRecrutement;
	
	private List<Client> clients;

	public AgentDto(long id, String email,String fullName, String telephone, boolean deleted, String pwd, LocalDate dateRecrutement, List<Client> clients) {
		super();
		this.id = id;
		this.email = email;
		this.fullName = fullName;
		this.telephone = telephone;
		this.deleted = deleted;
		this.pwd = pwd;
		this.dateRecrutement = dateRecrutement;
		this.clients = clients;
	}

	public AgentDto( String email, String fullName, String telephone, boolean deleted, String pwd,
			LocalDate dateRecrutement, List<Client> clients) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.telephone = telephone;
		this.deleted = deleted;
		this.pwd = pwd;
		this.dateRecrutement = dateRecrutement;
		this.clients = clients;
	}

	public AgentDto(String email, String fullName, String telephone, boolean deleted, String pwd,
			LocalDate dateRecrutement) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.telephone = telephone;
		this.deleted = deleted;
		this.pwd = pwd;
		this.dateRecrutement = dateRecrutement;
	}
	
	

}
