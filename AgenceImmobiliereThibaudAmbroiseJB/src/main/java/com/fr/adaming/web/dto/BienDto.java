package com.fr.adaming.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fr.adaming.entity.Client;

public class BienDto {

	
	private Long id;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Positive
	private double prix;
	
	@NotNull
	private boolean vendu;
	
	private boolean deleted;
	
	
	private Client clients;


	public BienDto(Long id, @NotNull @NotEmpty @NotBlank @Positive double prix, @NotNull boolean vendu, boolean deleted,
			Client clients) {
		super();
		this.id = id;
		this.prix = prix;
		this.vendu = vendu;
		this.deleted = deleted;
		this.clients = clients;
	}
	
	
}
