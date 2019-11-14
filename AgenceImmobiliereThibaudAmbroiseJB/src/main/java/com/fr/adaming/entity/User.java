package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Email @NotNull @Column(unique = true)
	private String email;
	@NotNull
	private String fullName;
	@Pattern(regexp = "\\d{10}")
	private int telephone;
	private boolean deleted;

}
