package com.fr.adaming.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
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
	

}
