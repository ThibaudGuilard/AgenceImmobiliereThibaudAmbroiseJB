package com.fr.adaming.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fr.adaming.entity.Client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class BienDto {

	
	private long id;
	
	@Positive
	private double prix;
	
	private boolean vendu;
	
	private boolean deleted;
	
	
	private Client clients;


	public BienDto(long id, double prix,  boolean vendu, boolean deleted,
			Client clients) {
		super();
		this.id = id;
		this.prix = prix;
		this.vendu = vendu;
		this.deleted = deleted;
		this.clients = clients;
	}


	public BienDto( double prix,  boolean vendu, boolean deleted, Client clients) {
		super();
		this.prix = prix;
		this.vendu = vendu;
		this.deleted = deleted;
		this.clients = clients;
	}


	public BienDto(double prix,  boolean vendu, boolean deleted) {
		super();
		this.prix = prix;
		this.vendu = vendu;
		this.deleted = deleted;
	}
	public BienDto(double prix, boolean deleted) {
		super();
		this.prix = prix;
		this.deleted = deleted;
	}


	public BienDto(long id, double prix, boolean vendu,boolean deleted) {
		super();
		this.id = id;
		this.prix = prix;
		this.vendu = vendu;
		this.deleted = deleted;
	}

	public BienDto(long id, double prix, boolean vendu) {
		super();
		this.id = id;
		this.prix = prix;
		this.vendu = vendu;
	}


	public BienDto(@NotNull boolean vendu, boolean deleted) {
		super();
		this.vendu = vendu;
		this.deleted = deleted;
	}
	
}
