package com.fr.adaming.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
	
	public Agent(@Email @NotNull String email, @NotNull String fullName, @Pattern(regexp = "\\d{10}") int telephone,
			boolean deleted, @Pattern(regexp = "^.{8,16}$") String pwd, @PastOrPresent LocalDate dateRecrutement) {
		super(email, fullName, telephone, deleted);
		this.pwd = pwd;
		this.dateRecrutement = dateRecrutement;
	}

}
