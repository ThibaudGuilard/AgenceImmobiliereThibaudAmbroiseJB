package com.fr.adaming.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor 
@Entity
public class Agent extends User {
	
	@Pattern(regexp = "^.{8,16}$")
	private String pwd;
	
	@PastOrPresent
	private LocalDate dateRecrutement;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_agent")
	private List<Client> clients;
	
	public Agent( String email,String fullName, int telephone,boolean deleted, String pwd,LocalDate dateRecrutement) {
		super(email, fullName, telephone, deleted);
		this.pwd = pwd;
		this.dateRecrutement = dateRecrutement;
	}

	@Override
	public String toString() {
		return "Agent [pwd=" + pwd + ", dateRecrutement=" + dateRecrutement + ", getId()=" + getId() + ", getEmail()="
				+ getEmail() + ", getFullName()=" + getFullName() + ", getTelephone()=" + getTelephone() + "]";
	}

	
	
}
