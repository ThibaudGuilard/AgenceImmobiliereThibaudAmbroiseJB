package com.fr.adaming.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fr.adaming.entity.Client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BienDtoAdd {
	
	@NotNull
	@Positive
	private double prix;

	@NotNull
	private boolean vendu;

	private boolean deleted;

	private Client clients;

	public BienDtoAdd( double prix, boolean vendu, boolean deleted) {
		super();
		this.prix = prix;
		this.vendu = vendu;
		this.deleted = deleted;
	}



}
