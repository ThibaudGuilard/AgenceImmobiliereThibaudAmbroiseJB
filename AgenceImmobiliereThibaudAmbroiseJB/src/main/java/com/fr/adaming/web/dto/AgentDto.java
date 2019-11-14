package com.fr.adaming.web.dto;

import java.time.LocalDate;
import java.util.List;

import com.fr.adaming.entity.Client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class AgentDto {
	
	private long id;
	private String email;
	private String fullName;
	private int telephone;
	private boolean deleted;
	private String pwd;
	private LocalDate dateRecrutement;
	private List<Client> clients;

}
